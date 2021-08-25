import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class TButton extends JButton implements ActionListener {

    Object t;
    JFrame parentFrame;

    public TButton(){

    }

    public TButton(Object t, JFrame parentFrame, String txt){
        this.t  = t;
        this.parentFrame = parentFrame;
        setText("<html><u>"+txt+"</u></html>");
        addActionListener(this);
        revalidate();
        repaint();
        setContentAreaFilled(false);
        setBorderPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setForeground(Color.BLUE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(t instanceof Transaction)
                new TransactionScreen(((Transaction)t).getHash(),parentFrame).setVisible(true);
            else if(t instanceof EthBlock.Block)
                new BlockScreen(((EthBlock.Block)t).getHash(),parentFrame).setVisible(true);
            else if(t instanceof cERC20)
                new TokenScreen(((cERC20)t),parentFrame).setVisible(true);
            else if(t instanceof cERC721)
                new TokenScreen(((cERC721)t),parentFrame).setVisible(true);
            else if(t instanceof EAccount)
                new TokenScreen(((cERC721)t),parentFrame).setVisible(true);
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
