//This class will have methods I want to override from MySqlParserBaseListener for grammar rules I Enter

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MyListener extends MySqlParserBaseListener{

    public static String statement="";
    public static String entity = ""; //--
    public static ArrayList<String> selectElements=new ArrayList<>();//--
    public static ArrayList<Object> expressionList=new ArrayList<>(); //UPDATED



    @Override public void enterSqlStatement(MySqlParser.SqlStatementContext ctx) {
        statement="";
        entity="";
        expressionList=new ArrayList<>();


    }
    @Override public void enterSimpleSelect(MySqlParser.SimpleSelectContext ctx) {
        statement="select";
    }
    @Override public void enterLogicalOperator(MySqlParser.LogicalOperatorContext ctx) {
        expressionList.add(ctx.getText());
    }

    @Override public void enterBinaryComparasionPredicate(MySqlParser.BinaryComparasionPredicateContext ctx) {
        String columnName= ctx.getChild(0).getText();
        String operator=ctx.getChild(1).getText();
        String value=ctx.getChild(2).getText();
        int firstChar=(int)(value.charAt(0));
        if(firstChar==39){
            value=value.substring(1, value.length()-1);
        }

        Condition theTerm=new Condition(columnName,operator,value);
        theTerm.setAttribute(columnName);
        theTerm.setOperator(operator);
        theTerm.setValue(value);
        expressionList.add(theTerm);
    }

    public void enterTableSources(MySqlParser.TableSourcesContext ctx){
        String entity = ctx.getChild(0).getText();
        //set entity to block, transaction or account
        this.entity = (entity.equals("block") || entity.equals("transaction") ||
               entity.equals("account"))?  entity : null;

    }

    @Override public void enterSelectElements(MySqlParser.SelectElementsContext ctx) {

        for(int i=0; i< ctx.children.size(); i+=2)
        {
            selectElements.add(ctx.getChild(i).getText());
        }

    }
    @Override public void enterNestedExpressionAtom(MySqlParser.NestedExpressionAtomContext ctx) {
        expressionList.add("(");
    }

    @Override public void exitNestedExpressionAtom(MySqlParser.NestedExpressionAtomContext ctx) {
        expressionList.add(")");
    }












}


