import org.web3j.protocol.core.methods.response.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class TButton extends JButton implements ActionListener {

    Transaction t;
    JFrame parentFrame;

    public TButton(Transaction t, JFrame parentFrame){
        this.t  = t;
        this.parentFrame = parentFrame;
        setText(t.getHash());
        addActionListener(this);
        revalidate();
        repaint();
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            new TransactionScreen(t.getHash(),parentFrame).setVisible(true);
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
