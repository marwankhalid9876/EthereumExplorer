/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.utils.Convert;

import javax.swing.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Youssef Ziad
 */
public class AccountScreen extends JFrame {

    private JFrame parentFrame;

    /**
     * Creates new form MainScreen
     */
    public AccountScreen() {

        initComponents();
    }

    public AccountScreen(String key, JFrame m) throws ExecutionException, InterruptedException, IOException {
        initComponents();
        parentFrame = m;
        Hash.setText(key);
        BigInteger result = EthereumExplorer.getAccountBalanceByAddress(key);
        Balance.setText(result+" Wei");
        Dollars.setText(Convert.fromWei(String.valueOf(result), Convert.Unit.WEI)+"$");
        revalidate();
        repaint();
    }

    public AccountScreen(EAccount a, JFrame m) throws ExecutionException, InterruptedException, IOException {
        initComponents();
        parentFrame = m;
        Hash.setText(a.hash);
        BigInteger result = EthereumExplorer.getAccountBalanceByAddress(a.hash);
        Balance.setText(result+" Wei");
        Dollars.setText(Convert.fromWei(String.valueOf(result), Convert.Unit.WEI)+"$");
        revalidate();
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        SkyScraper = new JLabel();
        Transactions = new JLabel();
        Dollars = new JLabel();
        Hash = new JLabel();
        AccountDetails = new JLabel();
        Hash1 = new JLabel();
        ValueDollars = new JLabel();
        Balance2 = new JLabel();
        Balance = new JLabel();
        BackButton = new JButton();
        Background = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        SkyScraper.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        SkyScraper.setForeground(new java.awt.Color(255, 255, 255));
        SkyScraper.setText("SkyScraper");
        jPanel1.add(SkyScraper);
        SkyScraper.setBounds(30, 20, 190, 42);

        Transactions.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Transactions.setForeground(new java.awt.Color(255, 255, 255));
        Transactions.setText("Transactions:");
        jPanel1.add(Transactions);
        Transactions.setBounds(30, 190, 130, 17);

        Dollars.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Dollars.setForeground(new java.awt.Color(255, 255, 255));
        Dollars.setText("[Dollars]");
        jPanel1.add(Dollars);
        Dollars.setBounds(180, 160, 260, 15);

        Hash.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Hash.setForeground(new java.awt.Color(255, 255, 255));
        Hash.setText("[Hash]");
        jPanel1.add(Hash);
        Hash.setBounds(180, 120, 260, 15);

        AccountDetails.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AccountDetails.setForeground(new java.awt.Color(255, 255, 255));
        AccountDetails.setText("Account Details:");
        jPanel1.add(AccountDetails);
        AccountDetails.setBounds(30, 90, 130, 17);

        Hash1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Hash1.setForeground(new java.awt.Color(255, 255, 255));
        Hash1.setText("Hash:");
        jPanel1.add(Hash1);
        Hash1.setBounds(60, 120, 130, 15);

        ValueDollars.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ValueDollars.setForeground(new java.awt.Color(255, 255, 255));
        ValueDollars.setText("Value In Dollars:");
        jPanel1.add(ValueDollars);
        ValueDollars.setBounds(60, 160, 130, 15);

        Balance2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Balance2.setForeground(new java.awt.Color(255, 255, 255));
        Balance2.setText("Balance:");
        jPanel1.add(Balance2);
        Balance2.setBounds(60, 140, 130, 15);

        Balance.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Balance.setForeground(new java.awt.Color(255, 255, 255));
        Balance.setText("[Balance]");
        jPanel1.add(Balance);
        Balance.setBounds(180, 140, 260, 15);

        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        jPanel1.add(BackButton);
        BackButton.setBounds(1280, 730, 73, 23);

        Background.setIcon(new ImageIcon(getClass().getResource("/etherbkg.png"))); // NOI18N
        Background.setLabelFor(jPanel1);
        jPanel1.add(Background);
        Background.setBounds(0, 0, 1366, 768);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(AccountScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccountScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel AccountDetails;
    private JButton BackButton;
    private JLabel Background;
    private JLabel Balance;
    private JLabel Balance2;
    private JLabel Dollars;
    private JLabel Hash;
    private JLabel Hash1;
    private JLabel SkyScraper;
    private JLabel Transactions;
    private JLabel ValueDollars;
    private JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
