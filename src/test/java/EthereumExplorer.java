import jnr.ffi.Struct;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.ipc.IpcService;
import org.web3j.protocol.ipc.WindowsIpcService;

import java.io.IOException;
import java.lang.String;
import java.lang.*;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class EthereumExplorer {
    public static Web3j web3 = Web3j.build(new HttpService("https://main-light.eth.linkpool.io"));

    public static EthBlock.Block getLastBlock() throws ExecutionException, InterruptedException, IOException {
        EthBlock b = web3.ethGetBlockByNumber(DefaultBlockParameter.valueOf(BigInteger.valueOf(13000000)),true).send();
        EthBlock.Block block = b.getBlock();
        return block;
    }

    public static Transaction getTransactionByHash(String key) throws IOException {
        Transaction t = null;
        t = web3.ethGetTransactionByHash(key).send().getResult();
        return t;
    }

    public static EthTransaction searchTransactionByHash (EthBlock.Block block,String hash) throws ExecutionException, InterruptedException {
        EthTransaction transaction = new EthTransaction();
        for(int i=0; i < block.getTransactions().size();i++){
            if(getBlockTransactionByIndex(block, i).getTransaction().get().getHash().equals(hash)){
                transaction = getBlockTransactionByIndex(block, i);
            }
        }
        return transaction;
    }
    public static EthTransaction searchTransactionByBlockHashAndIndex(String blockHash, int index) throws IOException, ExecutionException, InterruptedException {
        EthBlock.Block b =  getBlockbyHash(blockHash);
        return getBlockTransactionByIndex(b , index);
    }
    public static String getBlockNonce(EthBlock.Block b){
        BigInteger toHex=new BigInteger(b.getNonce().toString(),10);
        String s=toHex.toString(16);
        return s;
    }
    public static EthTransaction getBlockTransactionByIndex(EthBlock.Block block, int index) throws ExecutionException, InterruptedException {
        EthTransaction t = web3.ethGetTransactionByBlockHashAndIndex(block.getHash(), BigInteger.valueOf(index)).sendAsync().get();
        return t;
    }


    public static EthBlock.Block getBlockbyHash(String x) throws ExecutionException, InterruptedException, IOException {

        return web3.ethGetBlockByHash(x, true).send().getBlock();
    }
    public static BigInteger getTransactionNonce(EthTransaction t){

        return t.getTransaction().get().getNonce();
    }

    public static BigInteger getAccountBalanceByAddress(String key) throws ExecutionException, InterruptedException {
        EthGetBalance ethGetBalance = web3
                .ethGetBalance(key, DefaultBlockParameterName.LATEST)
                .sendAsync()
                .get();
        BigInteger wei = ethGetBalance.getBalance();
        return wei;
    }

    public static EthBlock.Block getBlockbyNumber(int x) throws ExecutionException, InterruptedException, IOException {

        return (web3.ethGetBlockByNumber(DefaultBlockParameter.valueOf(BigInteger.valueOf(x)), true).send()).getBlock();
    }

    public static EthBlock.Block getPreviousBlock (EthBlock.Block x) throws ExecutionException, InterruptedException, IOException {

        BigInteger z=x.getNumber().subtract(BigInteger.valueOf(1));
        return (web3.ethGetBlockByNumber(DefaultBlockParameter.valueOf(z), true).send()).getBlock();
    }


    public static boolean checkTransactionOnCondition(Transaction t, Condition c){

        switch(c.getAttribute()){
            case "fromaccount": return c.getValue().equals(t.getFrom());
            case "toaccount": return c.getValue().equals(t.getTo());
            case "value": return evaluateCondition(c.getOperator(), t.getValue(), BigInteger.valueOf(Integer.parseInt(c.getValue())));
            case "hash": return t.getHash().equals(c.getValue());
            case "gas": return evaluateCondition(c.getOperator(), t.getGas(), BigInteger.valueOf(Integer.parseInt(c.getValue())));
            case "gasPrice": return evaluateCondition(c.getOperator(), t.getGasPrice(), BigInteger.valueOf(Integer.parseInt(c.getValue())));
            case "nonce": return t.getNonceRaw().equals(c.getValue());
            default:
                System.out.println("You have entered an invalid where condition!");
        }
        return false;
    }
    public static boolean checkBlockOnCondition(EthBlock.Block B,Condition c){
        switch(c.getAttribute()) {
            case "blockheight":return evaluateCondition(c.getOperator(),B.getNumber(),BigInteger.valueOf(Integer.parseInt(c.getValue())));
            case "timestamp":;
            case "transactions":return evaluateCondition(c.getOperator(),BigInteger.valueOf((B.getTransactions()).size()),BigInteger.valueOf(Integer.parseInt(c.getValue())));
            case "minedby":return B.getMiner().equals(c.getValue());
            case "blockreward":;
            case "difficulty":return evaluateCondition(c.getOperator(),B.getDifficulty(),BigInteger.valueOf(Long.parseLong(c.getValue())));
            case "size":return evaluateCondition(c.getOperator(),B.getSize(),BigInteger.valueOf(Integer.parseInt(c.getValue())));
            case "gaslimit":return evaluateCondition(c.getOperator(),B.getGasLimit(),BigInteger.valueOf(Integer.parseInt(c.getValue())));
            case "parenthash":return B.getParentHash().equals(c.getValue());
            default:
                System.out.println("You have entered an invalid where condition!");

        }
        return false;
    }

    public static ArrayList<ArrayList<Object>> parse(String code ) throws IOException, ExecutionException, InterruptedException {
        // Before looking at this method ,Look at Mylistener.java file as it is subclass of MySqlParserBaseListener.java
        // in Mylistener.java you will find instance variables I called here and methods of entering grammar rules I implemented that overrides the super class MySqlParserBaseListener.java
        //If you will call this method,You have to disable scala plugin that it is added lately in intellij ,for that go to file->settings->plugins->(search for scala and disable it)


        code = code.toLowerCase(Locale.ROOT);//standardize sql code to be always lowercase
        MySqlLexer mySqlLexer = new MySqlLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(mySqlLexer);
        MySqlParser mySqlParser = new MySqlParser(tokens);
        ParseTree tree = mySqlParser.sqlStatement();
        ParseTreeWalker walker = new ParseTreeWalker();
        MyListener listener = new MyListener();
        ParseTreeWalker.DEFAULT.walk(listener, tree);

        Hashtable<String, Object> colNameVal = new Hashtable<>();
        String theTable = "";
        String cluster = "";
        if (MyListener.statement.equals("select")) {
            colNameVal = new Hashtable<>();
            System.out.println("term " + MyListener.expressionVector);
            int sizeOfArray = MyListener.expressionVector.size();
            Object[] sqltermsArray = new Object[sizeOfArray];
            for (int i = 0; i < sizeOfArray; i++) {
                sqltermsArray[i] = MyListener.expressionVector.get(i);
            }
            Vector<String> selectElements = MyListener.selectElements;


            switch (MyListener.entity)
            {
                case "transaction": return queryTransactions(sqltermsArray, selectElements);
                case "block": return queryBlock(sqltermsArray, selectElements);
            }
        }
        System.out.println("Invalid query!");
        return null;
    }

    public static boolean evaluateCondition(String operator, BigInteger operand1, BigInteger operand2) {
        switch (operator) {
            case "=":
                return operand1.equals(operand2);
            case ">":
                return operand1.compareTo(operand2) > 0;
            case ">=":
                return operand1.compareTo(operand2) >= 0;
            case "<":
                return operand1.compareTo(operand2) < 0;
            case "<=":
                return operand1.compareTo(operand2) <= 0;
            case "!=":
                return !operand1.equals(operand2);
            default:
                return false;
        }

    }




    public static ArrayList<ArrayList<Object>> queryTransactions(Object[] conditions, Vector<String> selectElements) throws IOException, ExecutionException, InterruptedException {
        EthBlock.Block current = getLastBlock();
        System.out.println("Block " +  getLastBlock().getNumber());
        int j=0;
        ArrayList<ArrayList<Object>> filteredList = new ArrayList<>();
        do{
//            if(j%50==0)
//               System.out.println(current.getNumber());
            List<EthBlock.TransactionResult> bigList = current.getTransactions();
            for(int i = 0; i < bigList.size(); i++){
                Transaction t = (Transaction) bigList.get(i).get();
                Vector<Object> conditionsSatisfied = new Vector<>();
//                System.out.println(t.getHash() + " " + t.getValue());
                for(int k=0; k<conditions.length; k++)
                {
                    if(conditions[k] instanceof Condition)
                        conditionsSatisfied.add(checkTransactionOnCondition(t,(Condition) conditions[k]));
                    else
                        conditionsSatisfied.add(conditions[k]);
                }

                evaluateBrackets(conditionsSatisfied);
                //if the where condition was true for this transaction
                if(conditionsResult(conditionsSatisfied))
                {
                    filteredList.add(new ArrayList<>());
                    //case1: selectElements contains only *
                    if(selectElements.get(0).equals("*"))
                        filteredList.get(filteredList.size()-1).add(t);
                    else //case2: there are select elements
                        filteredList.get(filteredList.size()-1).add(selectElementsValuesForTransaction(t,selectElements));
                }
            }
            current = getPreviousBlock(current);
            j++;
        }while(!current.getParentHash().equals("0x0000000000000000000000000000000000000000000000000000000000000000") && j<100);
        return filteredList;
    }
    public static void evaluateBrackets(Vector<Object> conditionsSatisfied)
    {
        Vector<Object> toBeEvaluated = new Vector<>();
        for(int i=0; i<conditionsSatisfied.size(); i++)
        {
            if(conditionsSatisfied.get(i).equals(")")) {
                {
                    evaluateSmallBracket(conditionsSatisfied,i);
                    i=0;
                }
            }
        }
    }
    public static void evaluateSmallBracket(Vector<Object> conditionsSatisfied, int i)
    {
        Vector<Object> toBeEvaluated = new Vector<>();
        conditionsSatisfied.remove(i--);
        while(!conditionsSatisfied.get(i).equals("("))
        {
            toBeEvaluated.add(0,conditionsSatisfied.remove(i--));
        }
        conditionsSatisfied.remove(i);
        conditionsSatisfied.add(i,conditionsResult(toBeEvaluated));
    }

    private static ArrayList<String> selectElementsValuesForTransaction(Transaction t, Vector<String> selectElements) {
        ArrayList<String> selectValues = new ArrayList<>();
        for(int i=0; i<selectElements.size(); i++)
        {
            switch(selectElements.get(i))
            {
                case "fromaccount": selectValues.add(t.getFrom()); break;
                case "toaccount": selectValues.add(t.getTo()); break;
                case "value": selectValues.add(t.getValue().toString()); break;
                case "hash": selectValues.add(t.getHash()); break;
                case "gas": selectValues.add(t.getGas().toString()); break;
                case "gasPrice":selectValues.add(t.getGasPrice().toString()); break;
                case "nonce": selectValues.add(t.getNonce().toString()); break;
                default:
                    System.out.println("The typed select elements are not valid for this transaction!");
                    return null;
            }
        }
        return selectValues;
    }

    public static boolean conditionsResult(Vector<Object>conditionsSatisfied)  {


        //trivial: make sure there is where condition
        if(conditionsSatisfied.size()==0)
            return true;


        //[true,false,true] [OR,AND] [true, OR, false,
        //order operators on an array based on priority

        String[] operatorsOrdered = new String[3];
        operatorsOrdered[0]="and";
        operatorsOrdered[1]="or";
        operatorsOrdered[2]="xor";
        // put all operands and operators in the same vector [true, "AND", false, "OR", true, "XOR", true]

        //loop on operators from highest to lowest priority
        for(int i=0; i<operatorsOrdered.length; i++){//OR,XOR
            String highestOperator = operatorsOrdered[i];//operator with highest priority in the ones remaining
            for(int j=1; j<conditionsSatisfied.size(); j+=2)
            {
                String operatorInHand = (String) conditionsSatisfied.get(j);
                if(operatorInHand.equals(highestOperator)){//if this is the highest priority operator, apply it
                    switch (highestOperator){
                        case "and": {
                            boolean newAdded = (Boolean) conditionsSatisfied.get(j-1) && (Boolean) conditionsSatisfied.get(j+1);
                            conditionsSatisfied.remove(j-1);
                            conditionsSatisfied.remove(j-1);
                            conditionsSatisfied.remove(j-1);
                            conditionsSatisfied.insertElementAt(newAdded,j-1);
                            j-=2;
                            break;
                        }
                        case "or": {
                            boolean newAdded = (Boolean) conditionsSatisfied.get(j-1) || (Boolean) conditionsSatisfied.get(j+1);
                            conditionsSatisfied.remove(j-1);
                            conditionsSatisfied.remove(j-1);
                            conditionsSatisfied.remove(j-1);
                            conditionsSatisfied.insertElementAt(newAdded,j-1);
                            j-=2;
                            break;
                        }
                        case "xor": {
                            boolean newAdded = (Boolean) conditionsSatisfied.get(j-1) ^ (Boolean) conditionsSatisfied.get(j+1);
                            conditionsSatisfied.remove(j-1);
                            conditionsSatisfied.remove(j-1);
                            conditionsSatisfied.remove(j-1);
                            conditionsSatisfied.insertElementAt(newAdded,j-1);
                            j-=2;
                            break;
                        }
                    }
                }

            }

        }
        //finally the vector will contain only one boolean value containing the result
        return (boolean) conditionsSatisfied.get(0);
    }

    public static ArrayList<ArrayList<Object>> queryBlock (Object[] conditions, Vector<String> selectElements) throws IOException, ExecutionException, InterruptedException {
        EthBlock.Block current = getLastBlock();
        EthBlock.Block oldCurrent;
        ArrayList<ArrayList<Object>> filteredList = new ArrayList<>();
        Vector<String> x = MyListener.selectElements;
        ArrayList<Object> attNames = new ArrayList<Object>();
        for(String s : x){
            attNames.add(s);
        }
        filteredList.add(attNames);
        int j=0;
        do{
            oldCurrent = current;
////            if(j%50==0)
//                System.out.println(current.getNumber());
            Vector<Object> conditionsSatisfied = new Vector<>();//{true,false,true,..}

            for(int k=0; k<conditions.length; k++)
            {
                if(conditions[k] instanceof Condition)
                    conditionsSatisfied.add(checkBlockOnCondition(current,(Condition) conditions[k]));
                else
                    conditionsSatisfied.add(conditions[k]);
            }
            evaluateBrackets(conditionsSatisfied);
            //if the where condition was true for this transaction
            if(conditionsResult(conditionsSatisfied))
            {
                //case1: selectElements contains only *
                if(selectElements.get(0).equals("*"))
                {
                    filteredList.add(new ArrayList<>());
                    filteredList.get(filteredList.size()-1).add(current);
                }
                else //case2: there are select elements
                    filteredList.add(selectElementsValuesForBlock(current,selectElements));
            }
            j++;
            current = getPreviousBlock(current);
        }while(!(current.getParentHash().equals("0x0000000000000000000000000000000000000000000000000000000000000000"))&& j<25);
        return filteredList;
    }

    public static ArrayList<Object> selectElementsValuesForBlock(EthBlock.Block c, Vector<String> selectElements) {
        ArrayList<Object> selectValues = new ArrayList<>();
        for(int i=0; i<selectElements.size(); i++)
        {
            switch(selectElements.get(i))
            {
                case "blockheight":selectValues.add(c.getNumber().toString());break;
                case"timestamp":selectValues.add(c.getTimestampRaw());break;
                case"transactionsnumber":selectValues.add(c.getTransactions().size()+"");break;
                case"minedby":selectValues.add(c.getMiner());break;
                case"difficulty":selectValues.add(c.getDifficulty().toString());break;
                case"size":selectValues.add(c.getSize().toString());break;
                case"gaslimit":selectValues.add(c.getGasLimit().toString());break;
                case"hash":selectValues.add(c.getHash());break;
                default:
                    System.out.println("The typed select elements are not valid for this Block!");
                    return null;
            }
        }
        return selectValues;
    }

//    public static ArrayList<ArrayList<Object>> queryAccount (Condition[] conditions, String[] operators, Vector<String> selectElements) throws IOException, ExecutionException, InterruptedException {
//        int j=0;
//        EthBlock.Block current = getLastBlock();
//        EthBlock.Block oldCurrent;
//        System.out.println("Block " +  getLastBlock().getNumber());
//        ArrayList<ArrayList<Object>> filteredList = new ArrayList<>();
//        do{
//            oldCurrent = current;
//
//
//            Vector<Boolean> conditionsSatisfied = new Vector<>();//{true,false,true,..}
////                System.out.println(t.getHash() + " " + t.getValue());
//            for(int k=0; k<conditions.length; k++)
//            {
//                conditionsSatisfied.add(checkBlockOnCondition(current,conditions[k]));
//            }
//
//            //if the where condition was true for this transaction
//            if(conditionsResult(conditionsSatisfied,operators))
//            {
//                filteredList.add(new ArrayList<>());
//                //case1: selectElements contains only *
//                if(selectElements.get(0).equals("*"))
//                    filteredList.get(filteredList.size()-1).add(current);
//                else //case2: there are select elements
//                    filteredList.get(filteredList.size()-1).add(selectElementsValuesForBlock(current,selectElements));
//            }
//
//            j++;
//        }while((current = getPreviousBlock(current)) != oldCurrent && j<3);
//        return filteredList;
//    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        ArrayList<ArrayList<Object>> arr = parse("select hash from transaction where value = 0 ");
        System.out.println(arr.size());
        int i=0;
        for(Object selectElement : arr)
        {
            i++;
            System.out.println((i) + "" + selectElement);
        }
    }
}
