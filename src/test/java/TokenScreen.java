/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.web3j.protocol.core.methods.response.Transaction;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Youssef Ziad
 */
public class TokenScreen extends JFrame {

    public JFrame parentFrame;

    /**
     * Creates new form MainScreen
     */
    public TokenScreen() {

        initComponents();
    }

    public TokenScreen(Object t, JFrame m) throws ExecutionException, InterruptedException, IOException {
        initComponents();
        parentFrame = m;
        if(t instanceof cERC721) {
            cERC721 c7 = (cERC721) t;
            From.setText(c7.getFrom());
            To.setText(c7.getTo());
            Amount.setText(c7.getAmount() + "");
            IDLbl.setText(c7.getID() + "");
        }
        else{
            cERC20 c2 = (cERC20) t;
            From.setText(c2.getFrom());
            To.setText(c2.getTo());
            Amount.setText(c2.getAmount() + "");
            IDLbl.setVisible(false);
            IDLbl1.setVisible(false);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        SkyScraper = new javax.swing.JLabel();
        From = new javax.swing.JLabel();
        TransactionDetails = new javax.swing.JLabel();
        From1 = new javax.swing.JLabel();
        To1 = new javax.swing.JLabel();
        To = new javax.swing.JLabel();
        Amount1 = new javax.swing.JLabel();
        Amount = new javax.swing.JLabel();
        IDLbl1 = new javax.swing.JLabel();
        IDLbl = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        SkyScraper.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        SkyScraper.setForeground(new java.awt.Color(255, 255, 255));
        SkyScraper.setText("SkyScraper");
        jPanel1.add(SkyScraper);
        SkyScraper.setBounds(30, 20, 190, 42);

        From.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        From.setForeground(new java.awt.Color(255, 255, 255));
        From.setText("[From]");
        jPanel1.add(From);
        From.setBounds(180, 120, 340, 15);

        TransactionDetails.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TransactionDetails.setForeground(new java.awt.Color(255, 255, 255));
        TransactionDetails.setText("Token Details:");
        jPanel1.add(TransactionDetails);
        TransactionDetails.setBounds(30, 90, 180, 17);

        From1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        From1.setForeground(new java.awt.Color(255, 255, 255));
        From1.setText("From:");
        jPanel1.add(From1);
        From1.setBounds(60, 120, 130, 15);

        To1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        To1.setForeground(new java.awt.Color(255, 255, 255));
        To1.setText("To:");
        jPanel1.add(To1);
        To1.setBounds(60, 140, 130, 15);

        To.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        To.setForeground(new java.awt.Color(255, 255, 255));
        To.setText("[To]");
        jPanel1.add(To);
        To.setBounds(180, 140, 340, 15);

        Amount1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Amount1.setForeground(new java.awt.Color(255, 255, 255));
        Amount1.setText("Amount:");
        jPanel1.add(Amount1);
        Amount1.setBounds(60, 160, 130, 15);

        Amount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Amount.setForeground(new java.awt.Color(255, 255, 255));
        Amount.setText("[Amount]");
        jPanel1.add(Amount);
        Amount.setBounds(180, 160, 340, 15);

        IDLbl1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        IDLbl1.setForeground(new java.awt.Color(255, 255, 255));
        IDLbl1.setText("ID:");
        jPanel1.add(IDLbl1);
        IDLbl1.setBounds(60, 180, 130, 15);

        IDLbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        IDLbl.setForeground(new java.awt.Color(255, 255, 255));
        IDLbl.setText("[ID]");
        jPanel1.add(IDLbl);
        IDLbl.setBounds(180, 180, 340, 15);

        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        jPanel1.add(BackButton);
        BackButton.setBounds(1280, 730, 73, 23);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/etherbkg.png"))); // NOI18N
        Background.setLabelFor(jPanel1);
        jPanel1.add(Background);
        Background.setBounds(0, 0, 1366, 768);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        parentFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_BackButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TransactionScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransactionScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransactionScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransactionScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Amount;
    private javax.swing.JLabel Amount1;
    private javax.swing.JButton BackButton;
    private javax.swing.JLabel Background;
    private javax.swing.JLabel From;
    private javax.swing.JLabel From1;
    private javax.swing.JLabel IDLbl;
    private javax.swing.JLabel IDLbl1;
    private javax.swing.JLabel SkyScraper;
    private javax.swing.JLabel To;
    private javax.swing.JLabel To1;
    private javax.swing.JLabel TransactionDetails;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}