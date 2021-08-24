import jnr.ffi.Struct;
import jnr.ffi.annotations.In;
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
    public static Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));

    public static EthBlock.Block getLastBlock() throws InterruptedException, IOException {
        EthBlock b = web3.ethGetBlockByNumber(DefaultBlockParameter.valueOf(BigInteger.valueOf(12000000)),true).send();
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


    public static boolean checkTransactionOnCondition(Transaction t, Condition c) throws ExplorerException {

        switch(c.getAttribute()){
            case "fromaccount": return c.getValue().equals(t.getFrom());
            case "toaccount": return c.getValue().equals(t.getTo());
            case "value": return evaluateCondition(c.getOperator(), t.getValue(), BigInteger.valueOf(Integer.parseInt(c.getValue())));
            case "hash": return t.getHash().equals(c.getValue());
            case "gas": return evaluateCondition(c.getOperator(), t.getGas(), BigInteger.valueOf(Integer.parseInt(c.getValue())));
            case "gasPrice": return evaluateCondition(c.getOperator(), t.getGasPrice(), BigInteger.valueOf(Integer.parseInt(c.getValue())));
            case "nonce": return t.getNonceRaw().equals(c.getValue());
            default:

                    throw new ExplorerException("You have entered an invalid where condition!");

        }
    }
    public static boolean checkBlockOnCondition(EthBlock.Block B,Condition c) throws ExplorerException {
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

                    throw new ExplorerException("You have entered an invalid where condition!");


        }
    }

    public static ArrayList<ArrayList> parse(String code ) throws IOException, ExecutionException, InterruptedException, ExplorerException {
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

        if(MyListener.entity == null)

                throw new ExplorerException("Please enter a valid entity to query on!");


        if (MyListener.statement.equals("select")) {
            colNameVal = new Hashtable<>();
            int sizeOfArray = MyListener.expressionList.size();
            Object[] sqltermsArray = new Object[sizeOfArray];
            for (int i = 0; i < sizeOfArray; i++) {
                sqltermsArray[i] = MyListener.expressionList.get(i);
            }
            ArrayList<String> selectElements = MyListener.selectElements;


            switch (MyListener.entity)
            {
                case "transaction": return queryTransactions(sqltermsArray, selectElements);
                case "block": return queryBlock(sqltermsArray, selectElements);
                case "erc721":
                    for(int i=0; i<selectElements.size(); i++)
                    {
                        if(!selectElements.get(i).equals("from") && !selectElements.get(i).equals("to") &&
                                !selectElements.get(i).equals("amount") && !selectElements.get(i).equals("id"))
                        {

                                throw new ExplorerException("A valid select element for a token query must be 'toAccount' or 'fromAccount' or 'amount' or 'id'! ");

                        }
                    }
                    for(int i=0; i<sqltermsArray.length; i++)
                    {
                        if(!(sqltermsArray[i] instanceof Condition))
                        {
                            if(!((Condition)sqltermsArray[i]).getAttribute().equals("from") &&
                                    !((Condition)sqltermsArray[i]).getAttribute().equals("to") &&
                                    !((Condition)sqltermsArray[i]).getAttribute().equals("amount"))

                                    throw new ExplorerException("A valid select element must be 'toAccount' or 'fromAccount' or 'amount'! ");

                        }
                    }
                    ArrayList<ArrayList> selectElementsAndConditions = new ArrayList<>();
                    selectElementsAndConditions.add(MyListener.selectElements);
                    selectElementsAndConditions.add(MyListener.expressionList);
                    return selectElementsAndConditions;

                case "erc20":
                    for(int i=0; i<selectElements.size(); i++)
                    {
                        if(!selectElements.get(i).equals("from") && !selectElements.get(i).equals("to") &&
                                !selectElements.get(i).equals("amount"))
                        {

                                throw new ExplorerException("A valid select element for a token query must be 'toAccount' or 'fromAccount' or 'amount'! ");

                        }
                    }
                    for(int i=0; i<sqltermsArray.length; i++)
                    {
                        if(!(sqltermsArray[i] instanceof Condition))
                        {
                            if(!((Condition)sqltermsArray[i]).getAttribute().equals("from") &&
                                    !((Condition)sqltermsArray[i]).getAttribute().equals("to") &&
                                    !((Condition)sqltermsArray[i]).getAttribute().equals("amount"))

                                    throw new ExplorerException("A valid select element must be 'toAccount' or 'fromAccount' or 'amount'! ");

                        }
                    }
                    selectElementsAndConditions = new ArrayList<>();
                    selectElementsAndConditions.add(MyListener.selectElements);
                    selectElementsAndConditions.add(MyListener.expressionList);
                    return selectElementsAndConditions;
            }
        }

            throw new ExplorerException("Please enter a select statement!");

    }

    public static boolean evaluateCondition(String operator, Comparable operand1, Comparable operand2) {

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

    public static boolean evaluateConditionDouble(String operator, Comparable operand1, Comparable operand2) {

        switch (operator) {
            case "=":
                return operand1.equals(((Integer)operand2).doubleValue());
            case ">":
                return operand1.compareTo(((Integer)operand2).doubleValue()) > 0;
            case ">=":
                return operand1.compareTo(((Integer)operand2).doubleValue()) >= 0;
            case "<":
                return operand1.compareTo(((Integer)operand2).doubleValue()) < 0;
            case "<=":
                return operand1.compareTo(((Integer)operand2).doubleValue()) <= 0;
            case "!=":
                return !operand1.equals(((Integer)operand2).doubleValue());
            default:
                return false;
        }

    }





    public static ArrayList<ArrayList> queryTransactions(Object[] conditions, ArrayList<String> selectElements) throws IOException, ExecutionException, InterruptedException, ExplorerException {
        EthBlock.Block current = getLastBlock();
        System.out.println("Block " +  getLastBlock().getNumber());
        int j=0;
        ArrayList<ArrayList> filteredList = new ArrayList<>();
        filteredList.add(MyListener.selectElements);
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
        }while(!current.getParentHash().equals("0x0000000000000000000000000000000000000000000000000000000000000000") && j<10000);
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

    private static ArrayList<String> selectElementsValuesForTransaction(Transaction t, ArrayList<String> selectElements) throws ExplorerException {
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

                        throw new ExplorerException("The typed select elements are not valid for this transaction!");

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

    public static ArrayList<ArrayList> queryBlock (Object[] conditions, ArrayList<String> selectElements) throws IOException, ExecutionException, InterruptedException, ExplorerException {
        EthBlock.Block current = getLastBlock();
        EthBlock.Block oldCurrent;
        ArrayList<ArrayList> filteredList = new ArrayList<>();
        filteredList.add(MyListener.selectElements);
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
        }while(!(current.getParentHash().equals("0x0000000000000000000000000000000000000000000000000000000000000000"))&& j<10000);
        return filteredList;
    }

    public static ArrayList<Object> selectElementsValuesForBlock(EthBlock.Block c, ArrayList<String> selectElements) throws ExplorerException {
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

                        throw new ExplorerException("The typed select elements are not valid for this Block!");

            }
        }
        return selectValues;
    }


    public static ArrayList<ArrayList> summary20(ArrayList<Integer> x,ArrayList<String> ty,String ca,String m, ArrayList selectElements, ArrayList conditions) throws IOException, ExecutionException, InterruptedException, ExplorerException {
        int j=0;
        EthBlock.Block current = getLastBlock();
        EthBlock.Block oldCurrent = current;
        ArrayList<ArrayList> R = new ArrayList<ArrayList>();
        R.add(MyListener.selectElements);
        do{
            List<EthBlock.TransactionResult> bigList = current.getTransactions();
            for(int i = 0; i < bigList.size(); i++){
                Transaction t = (Transaction) bigList.get(i).get();
                if(t!=null)
                    if(t.getTo()!=null)
                        if(t.getTo().equals(ca))
                            if( t.getInput()!=null){
                                if (t.getInput().substring(0,10).equals(m)) {
                                    ArrayList<String>tr=fetchcontractdata(x,t,ty);
                                    cERC20 n=new cERC20(t.getFrom(),tr.get(0),Double.parseDouble(tr.get(1)));
                                    Vector<Object> conditionsSatisfied = new Vector<>();//{true,false,true,..}

                                    for(int k=0; k<conditions.size(); k++)
                                    {
                                        if(conditions.get(k) instanceof Condition)
                                            conditionsSatisfied.add(checkERC20OnCondition(n,(Condition) conditions.get(k)));
                                        else
                                            conditionsSatisfied.add(conditions.get(k));
                                    }
                                    evaluateBrackets(conditionsSatisfied);
                                    //if the where condition was true for this transaction
                                    if(conditionsResult(conditionsSatisfied))
                                    {
                                        //case1: selectElements contains only *
                                        if(selectElements.get(0).equals("*"))
                                        {
                                            R.add(new ArrayList<>());
                                            R.get(R.size()-1).add(n);
                                        }
                                        else //case2: there are select elements
                                            R.add(selectElementsValuesForERC20(n,selectElements));
                                    }
                                }
                            }
            }}
        while((current = getPreviousBlock(current)) != oldCurrent && j<1);
        return R;
    }

    private static ArrayList selectElementsValuesForERC20(cERC20 n, ArrayList selectElements) throws ExplorerException {
        ArrayList<String> selectValues = new ArrayList<>();
        for(int i=0; i<selectElements.size(); i++)
        {
            switch(selectElements.get(i).toString())
            {
                case "fromaccount": selectValues.add(n.getFrom()); break;
                case "toaccount": selectValues.add(n.getTo()); break;
                case "amount": selectValues.add(((Double)n.getAmount()).toString()); break;
                default:
                    throw new ExplorerException("The typed select elements are not valid for this token!");
            }
        }
        return selectValues;
    }

    private static Object checkERC20OnCondition(cERC20 n, Condition c) throws ExplorerException {
        switch(c.getAttribute()){
            case "fromaccount": return c.getValue().equals(n.getFrom());
            case "toaccount": return c.getValue().equals(n.getTo());
            case "amount": return evaluateConditionDouble(c.getOperator(), n.getAmount(), BigInteger.valueOf(Integer.parseInt(c.getValue())));

            default:
                throw new ExplorerException("You have entered an invalid where condition!");
        }
    }

    public static ArrayList<ArrayList> summary721(ArrayList<Integer> x,ArrayList<Integer> x2,ArrayList<String> ty,ArrayList<String>M,String ca, ArrayList selectElements, ArrayList conditions) throws IOException, ExecutionException, InterruptedException, ExplorerException {
        int j=0;
        EthBlock.Block current = getLastBlock();
        EthBlock.Block oldCurrent = current;
        ArrayList<ArrayList> R = new ArrayList<ArrayList>();
        R.add(MyListener.selectElements);

        do{
            List<EthBlock.TransactionResult> bigList = current.getTransactions();

            for(int i = 0; i < bigList.size(); i++){

                Transaction t = (Transaction) bigList.get(i).get();

                if(t!=null)
                    if(t.getTo()!=null)
                        if(t.getTo().equals(ca))
                        {
                            if( t.getInput()!=null){
                                if (t.getInput().substring(0,10).equals(M.get(0))||t.getInput().substring(0,10).equals(M.get(1))) {
                                    //  System.out.println("aiii");
                                    String[] test=web3.ethGetTransactionReceipt(t.getHash() ).send().getTransactionReceipt().toString().split("status='0x");
                                    if(test.length<2 )
                                        continue;
                                    if(test[1].charAt(0)=='0')
                                        continue;
                                    ArrayList<String>tr=fetchcontractdata721(x,x2,t,ty,M);

                                    cERC721 n=new cERC721(tr.get(0),tr.get(1),Integer.parseInt(tr.get(2)),t.getValue().doubleValue()/Math.pow(10,18));

                                    Vector<Object> conditionsSatisfied = new Vector<>();//{true,false,true,..}

                                    for(int k=0; k<conditions.size(); k++)
                                    {
                                        if(conditions.get(k) instanceof Condition)
                                            conditionsSatisfied.add(checkERC721OnCondition(n,(Condition) conditions.get(k)));
                                        else
                                            conditionsSatisfied.add(conditions.get(k));
                                    }
                                    evaluateBrackets(conditionsSatisfied);
                                    //if the where condition was true for this transaction
                                    if(conditionsResult(conditionsSatisfied))
                                    {
                                        //case1: selectElements contains only *
                                        if(selectElements.get(0).equals("*"))
                                        {
                                            R.add(new ArrayList<>());
                                            R.get(R.size()-1).add(n);
                                        }
                                        else //case2: there are select elements
                                            R.add(selectElementsValuesForERC721(n,selectElements));

                                    }
                                }
                            }
                        }

            }
            j++;
        }
        while((current = getPreviousBlock(current)) != oldCurrent && j<4);
        return R;
    }

    private static Object checkERC721OnCondition(cERC721 n, Condition c) throws ExplorerException {
        switch(c.getAttribute()){
            case "fromaccount": return c.getValue().equals(n.getFrom());
            case "toaccount": return c.getValue().equals(n.getTo());
            case "amount": return evaluateConditionDouble(c.getOperator(), n.getAmount(), (Integer.parseInt(c.getValue())));
            case "id": return evaluateCondition(c.getOperator(), n.getID(), (Integer.parseInt(c.getValue())));

            default:
                throw new ExplorerException("You have entered an invalid where condition!");
        }
    }

    private static ArrayList selectElementsValuesForERC721(cERC721 n, ArrayList selectElements) throws ExplorerException {
        ArrayList<String> selectValues = new ArrayList<>();
        for(int i=0; i<selectElements.size(); i++)
        {
            switch(selectElements.get(i).toString())
            {
                case "fromaccount": selectValues.add(n.getFrom()); break;
                case "toaccount": selectValues.add(n.getTo()); break;
                case "amount": selectValues.add(((Double)n.getAmount()).toString()); break;
                case "id": selectValues.add(((Integer)n.getID()).toString());
                break;
                default:
                  throw new ExplorerException("The typed select elements are not valid for this token!");
            }
        }
        return selectValues;
    }

    public static ArrayList<String> fetchcontractdata(ArrayList<Integer> x,Transaction t,ArrayList<String> ty){
        String s=t.getInput();
        s=s.substring(10);
        ArrayList<String> r=new ArrayList<String>();
        for(int i=0;i<x.size();i++){
            int k=x.get(i);

            String z=s.substring(k*64,(k+1)*64);
            if(ty.get(i).equals("address"))
                r.add("0x"+z.substring(24));
            else {
                double div=Math.pow(10,18);
                double d= getDecimal(z) /div;
                r.add(d+"");
            }
        }
        return r;

    }
    public static ArrayList<String> fetchcontractdata721(ArrayList<Integer> x1,ArrayList<Integer> x2,Transaction t,ArrayList<String> ty,ArrayList<String>M) throws IOException {
        String s=t.getInput();
        String m=s.substring(0,10);
        s=s.substring(10);
        ArrayList<String> r=new ArrayList<String>();
        if(m.equals(M.get(0))){
            r.add(t.getFrom());
            for(int i=0;i<x1.size();i++){
                int k=x1.get(i);

                String z=s.substring(k*64,(k+1)*64);
                if(ty.get(i).equals("address"))
                    r.add("0x"+z.substring(24));
                else {
                    double div=Math.pow(10,18);
                    double d= getDecimal(z) ;
                    r.add(d+"");
                }
            }}
        else
        {
            if(m.equals(M.get(1))){

                for(int i=0;i<x2.size();i++){
                    int k=x2.get(i);

                    String z=s.substring(k*64,(k+1)*64);
                    if(ty.get(i).equals("address")){
                        r.add("0x"+z.substring(24));
                        r.add(t.getFrom());  }
                    else {
                        double div=Math.pow(10,18);
                        double d= getDecimal(z) ;
                        r.add(d+"");
                    }
                }
                String[] s1=  web3.ethGetTransactionReceipt(t.getHash() ).send() .getTransactionReceipt().get().toString().split("Log");
                s1=s1[1].split(",");
                double div=Math.pow(10,18);
                int d= (int) (getDecimal(s1[s1.length-2].substring(3,67)) );
                r.add(d+"");

            }
        }
        return r;

    }
    public static double getDecimal(String hex){
        String digits = "0123456789ABCDEF";
        hex = hex.toUpperCase();
        double val = 0;
        for (int i = 0; i < hex.length(); i++)
        {
            char c = hex.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException, ExplorerException {
//        ArrayList<ArrayList> arr = parse("select from transaction");
//        System.out.println(arr.size());
//        int i=0;
//        for(Object selectElement : arr)
//        {
//            i++;
//            System.out.println((i) + "" + selectElement);
//        }
        ArrayList<Integer> x=new ArrayList<>();
        ArrayList<Integer> y=new ArrayList<>();
        ArrayList<String> ty=new ArrayList<>();
        x.add(1);
        y.add(2);
        ty.add("address");
        ty.add("num");
        x.add(2);

        //System.out.println(fetchcontractdata(x,tt.send().getTransaction().get(),ty));
        ArrayList<String> mm=new ArrayList<>();
        mm.add("0x23b872dd");
        mm.add("0xab834bab");

        ArrayList selectElements = new ArrayList();
        selectElements.add("*");
        Condition condition = new Condition("amount", ">", "0");

        ArrayList conditions = new ArrayList();
        conditions.add(condition);

        // ArrayList<cERC20>a=null;
        ArrayList<ArrayList>a=summary721(x,y,ty,mm,"0x7be8076f4ea4a4ad08075c2508e481d6c946d12b",selectElements,conditions);

        System.out.println(a.size());
//        System.out.println(a);
        for (int i=0;i<a.size();i++){
            System.out.println(a.get(i));
        }
    }
}
