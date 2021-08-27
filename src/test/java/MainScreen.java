/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import org.web3j.exceptions.MessageDecodingException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Youssef Ziad
 */
public class MainScreen extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form MainScreen
     */
    public MainScreen() {

        initComponents();
        showhide = new JButton();
        showhide.setContentAreaFilled(false);
        showhide.setBorderPainted(false);
        showhide.setIcon(shower);
        ExPosBox.setVisible(false);
        TypeL4.setVisible(false);
        AddButton.setVisible(false);
        X2Label.setVisible(false);
        TypeL1.setVisible(false);
        TypeL.setVisible(false);
        FormatBox.setVisible(false);
        showhide.addActionListener(this);
        Background.setLayout(null);
        Background.add(showhide);
        showhide.setBounds(5,260,30,30);
        ShowOrHide();
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
        SearchingFor = new javax.swing.JLabel();
        SearchForBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        SearchBox1 = new javax.swing.JTextField();
        EnterSearchTerm = new javax.swing.JLabel();
        TypeL = new javax.swing.JLabel();
        SearchBox2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        EnterSearchTerm2 = new javax.swing.JLabel();
        SearchBox3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        EnterSearchTerm3 = new javax.swing.JLabel();
        TypeL1 = new javax.swing.JLabel();
        FormatBox = new javax.swing.JComboBox<>();
        TypeL2 = new javax.swing.JLabel();
        HashBox = new javax.swing.JTextField();
        TypeL3 = new javax.swing.JLabel();
        MethodBox = new javax.swing.JTextField();
        MethodBox1 = new javax.swing.JTextField();
        ValPosBox = new javax.swing.JTextField();
        TypeL4 = new javax.swing.JLabel();
        ExPosBox = new javax.swing.JTextField();
        TypeL5 = new javax.swing.JLabel();
        AddPosBox1 = new javax.swing.JTextField();
        AddButton = new javax.swing.JButton();
        X2Label = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        SkyScraper.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        SkyScraper.setForeground(new java.awt.Color(255, 255, 255));
        SkyScraper.setText("SkyScraper");
        jPanel1.add(SkyScraper);
        SkyScraper.setBounds(30, 20, 190, 42);

        SearchingFor.setForeground(new java.awt.Color(255, 255, 255));
        SearchingFor.setText("Searching for:");
        jPanel1.add(SearchingFor);
        SearchingFor.setBounds(30, 130, 80, 20);

        SearchForBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Account", "Block", "Transaction" }));
        SearchForBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchForBoxActionPerformed(evt);
            }
        });
        jPanel1.add(SearchForBox);
        SearchForBox.setBounds(140, 130, 150, 20);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search For Token");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 260, 230, 30);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/loupe.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(550, 80, 40, 40);

        SearchBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(SearchBox1);
        SearchBox1.setBounds(140, 80, 400, 40);

        EnterSearchTerm.setForeground(new java.awt.Color(255, 255, 255));
        EnterSearchTerm.setText("Enter Search Term:");
        jPanel1.add(EnterSearchTerm);
        EnterSearchTerm.setBounds(30, 90, 160, 20);

        TypeL.setForeground(new java.awt.Color(255, 255, 255));
        TypeL.setText("Extra Positions:");
        jPanel1.add(TypeL);
        TypeL.setBounds(30, 600, 160, 20);

        SearchBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBox2ActionPerformed(evt);
            }
        });
        jPanel1.add(SearchBox2);
        SearchBox2.setBounds(140, 210, 400, 40);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/loupe.png"))); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(550, 210, 40, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Advanced Search");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 180, 170, 30);

        EnterSearchTerm2.setForeground(new java.awt.Color(255, 255, 255));
        EnterSearchTerm2.setText("Enter Query:");
        jPanel1.add(EnterSearchTerm2);
        EnterSearchTerm2.setBounds(30, 220, 160, 20);

        SearchBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBox3ActionPerformed(evt);
            }
        });
        jPanel1.add(SearchBox3);
        SearchBox3.setBounds(140, 290, 400, 40);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/loupe.png"))); // NOI18N
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(550, 290, 40, 40);

        EnterSearchTerm3.setForeground(new java.awt.Color(255, 255, 255));
        EnterSearchTerm3.setText("Enter Query:");
        jPanel1.add(EnterSearchTerm3);
        EnterSearchTerm3.setBounds(30, 300, 160, 20);

        TypeL1.setForeground(new java.awt.Color(255, 255, 255));
        TypeL1.setText("Contract Format:");
        jPanel1.add(TypeL1);
        TypeL1.setBounds(30, 340, 160, 20);

        FormatBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ERC-20", "ERC-721" }));
        FormatBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FormatBoxActionPerformed(evt);
            }
        });
        jPanel1.add(FormatBox);
        FormatBox.setBounds(140, 340, 100, 20);

        TypeL2.setForeground(new java.awt.Color(255, 255, 255));
        TypeL2.setText("Address Position:");
        jPanel1.add(TypeL2);
        TypeL2.setBounds(30, 390, 160, 20);

        HashBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HashBoxActionPerformed(evt);
            }
        });
        jPanel1.add(HashBox);
        HashBox.setBounds(140, 430, 400, 40);

        TypeL3.setForeground(new java.awt.Color(255, 255, 255));
        TypeL3.setText("Contract Hash:");
        jPanel1.add(TypeL3);
        TypeL3.setBounds(30, 440, 160, 20);

        MethodBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MethodBoxActionPerformed(evt);
            }
        });
        jPanel1.add(MethodBox);
        MethodBox.setBounds(140, 480, 400, 40);

        MethodBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MethodBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(MethodBox1);
        MethodBox1.setBounds(140, 530, 400, 40);

        ValPosBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValPosBoxActionPerformed(evt);
            }
        });
        jPanel1.add(ValPosBox);
        ValPosBox.setBounds(430, 380, 110, 40);

        TypeL4.setForeground(new java.awt.Color(255, 255, 255));
        TypeL4.setText("ID Position:");
        jPanel1.add(TypeL4);
        TypeL4.setBounds(330, 390, 160, 20);

        ExPosBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExPosBoxActionPerformed(evt);
            }
        });
        jPanel1.add(ExPosBox);
        ExPosBox.setBounds(140, 590, 110, 40);

        TypeL5.setForeground(new java.awt.Color(255, 255, 255));
        TypeL5.setText("Method Names:");
        jPanel1.add(TypeL5);
        TypeL5.setBounds(30, 490, 160, 20);

        AddPosBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPosBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(AddPosBox1);
        AddPosBox1.setBounds(140, 380, 110, 40);

        AddButton.setText("Add");
        jPanel1.add(AddButton);
        AddButton.setBounds(270, 600, 51, 23);

        X2Label.setForeground(new java.awt.Color(255, 255, 255));
        X2Label.setText("[ExtraPositions]");
        jPanel1.add(X2Label);
        X2Label.setBounds(340, 610, 90, 14);

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

    public void ShowOrHide(){
        boolean toset = !SearchBox3.isVisible();
        EnterSearchTerm3.setVisible(toset);
        SearchBox3.setVisible(toset);
        jButton3.setVisible(toset);
        TypeL2.setVisible(toset);
        TypeL3.setVisible(toset);
        TypeL4.setVisible(toset);
        TypeL5.setVisible(toset);
        AddPosBox1.setVisible(toset);
        ValPosBox.setVisible(toset);
        MethodBox.setVisible(toset);
        MethodBox1.setVisible(toset);
        HashBox.setVisible(toset);
    }

    private void SearchForBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchForBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchForBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
        AccountScreen ac = null;
        TransactionScreen tc = null;
        BlockScreen bc = null;
        if(SearchForBox.getSelectedItem().equals("Account")) {
            try {
                ac = new AccountScreen(SearchBox1.getText(), this);
                ac.setVisible(true);
            } catch (MessageDecodingException|NullPointerException e){
                JOptionPane.showMessageDialog(this,"Please enter a proper account hash");
                this.setVisible(true);
                return;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(SearchForBox.getSelectedItem().equals("Transaction")){
            try {
                tc = new TransactionScreen(SearchBox1.getText(),this);
                tc.setVisible(true);
            } catch (MessageDecodingException|NullPointerException e){
                JOptionPane.showMessageDialog(this,"Please enter a proper transaction hash");
                this.setVisible(true);
                return;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                try {
                    bc = new BlockScreen(Integer.parseInt(SearchBox1.getText()), this);
                }
                catch(NumberFormatException e2){
                    bc = new BlockScreen(SearchBox1.getText(), this);
                }
                bc.setVisible(true);
            } catch (MessageDecodingException|NullPointerException e){
                JOptionPane.showMessageDialog(this,"Please enter a proper block identifier");
                this.setVisible(true);
                return;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void SearchBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchBox1ActionPerformed

    private void SearchBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchBox2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            AdvancedScreen as = new AdvancedScreen(SearchBox2.getText(),this,0,null);
            as.setVisible(true);
            setVisible(false);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExplorerException e) {
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void SearchBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBox3ActionPerformed

    }//GEN-LAST:event_SearchBox3ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> x2 = new ArrayList<Integer>();
        ArrayList<String> ty = new ArrayList<String>();
        ArrayList<String> M = new ArrayList<String>();
        ArrayList<String> caa = new ArrayList<String>();
        if(!SearchBox3.getText().contains("erc")){
            JOptionPane.showMessageDialog(this,"Please enter a valid token query");
            return;
        }
        try {
            x.add(Integer.parseInt(AddPosBox1.getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Address Position must be a number!");
            return;
        }
        try {
            x.add(Integer.parseInt(ValPosBox.getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"ID Position must be a number!");
            return;
        }
        try {
            x2.add(Integer.parseInt(ValPosBox.getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"ID Position must be a number!");
            return;
        }
        ty.add("address");
        ty.add("num");
        M.add(MethodBox.getText());
        M.add(MethodBox1.getText());
        caa.add(HashBox.getText());
        ArrayList<ArrayList> stuff = new ArrayList<ArrayList>();
        stuff.add(x);
        stuff.add(x2);
        stuff.add(ty);
        stuff.add(M);
        stuff.add(caa);
        try {
            AdvancedScreen as = new AdvancedScreen(SearchBox3.getText(), this, SearchBox3.getText().contains("erc20") ? 1 : 2, stuff);
            as.setVisible(true);
            setVisible(false);
        } catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExplorerException e) {
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void FormatBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FormatBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FormatBoxActionPerformed

    private void HashBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HashBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HashBoxActionPerformed

    private void MethodBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MethodBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MethodBoxActionPerformed

    private void MethodBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MethodBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MethodBox1ActionPerformed

    private void ValPosBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValPosBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ValPosBoxActionPerformed

    private void ExPosBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExPosBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ExPosBoxActionPerformed

    private void AddPosBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPosBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddPosBox1ActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JTextField AddPosBox1;
    private javax.swing.JLabel Background;
    private javax.swing.JLabel EnterSearchTerm;
    private javax.swing.JLabel EnterSearchTerm2;
    private javax.swing.JLabel EnterSearchTerm3;
    private javax.swing.JTextField ExPosBox;
    private javax.swing.JComboBox<String> FormatBox;
    private javax.swing.JTextField HashBox;
    private javax.swing.JTextField MethodBox;
    private javax.swing.JTextField MethodBox1;
    private javax.swing.JTextField SearchBox1;
    private javax.swing.JTextField SearchBox2;
    private javax.swing.JTextField SearchBox3;
    private javax.swing.JComboBox<String> SearchForBox;
    private javax.swing.JLabel SearchingFor;
    private javax.swing.JLabel SkyScraper;
    private javax.swing.JLabel TypeL;
    private javax.swing.JLabel TypeL1;
    private javax.swing.JLabel TypeL2;
    private javax.swing.JLabel TypeL3;
    private javax.swing.JLabel TypeL4;
    private javax.swing.JLabel TypeL5;
    private javax.swing.JTextField ValPosBox;
    private javax.swing.JLabel X2Label;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    private JButton showhide;
    private ImageIcon shower = new ImageIcon(getClass().getResource("/triangle-right-arrow.png"));
    private ImageIcon hider = new ImageIcon(getClass().getResource("/drop-down-arrow.png"));

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton x = ((JButton) e.getSource());
        x.setIcon(x.getIcon().equals(shower)?hider:shower);
        ShowOrHide();
    }
}
