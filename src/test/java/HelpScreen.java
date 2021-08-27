import javax.swing.*;
import java.awt.*;

public class HelpScreen extends JFrame{
    private JLabel stuff;

    public HelpScreen(){
        this.setSize(480,480);
        this.getContentPane().setBackground(Color.WHITE);
        this.stuff = new JLabel("<html>Normal Search:<br/>" +
                "You can enter a specific entity identifier (transaction hash, account address or block number) to get the details of this entity.<br/>" +
                "<br/>" +
                "Advanced search:<br/>" +
                "You can query on any of the properties of a specific transaction, block.<br/>" +
                "Block properties: blockheight, timestamp, transactionsnumber, minedby, difficulty, size, gaslimit, hash<br/>" +
                "<br/>" +
                "Transaction properties: fromaccount, toaccount, value, hash, gas, gasPrice, nonce<br/>" +
                "<br/>" +
                "These properties are the only valid select elements and where condition attributes.<br/>" +
                "<br/>" +
                "Example of advanced search statement:<br/>" +
                "select blockheight from block where transactionsnumner>20<br/>" +
                "<br/>" +
                "A select * statement will retrieve the java object of this entity<br/>" +
                "Ex: “select * from Block” will return all the block as java objects.<br/>" +
                "<br/>" +
                "Search for Token<br/>" +
                "Erc20 properties: fromaccount, toaccount, amount<br/>" +
                "Erc721 properties: fromaccount, toaccount, amount, id<br/>" +
                "<br/>" +
                "These properties are the only valid select elements and where condition attributes.<br/>" +
                "<br/>" +
                "Example of advanced search statement:<br/>" +
                "select toaccount from erc20 where amount>20<br/>" +
                "select id from erc721 where amount>20</html>");
        this.getContentPane().add(stuff);
    }
}
