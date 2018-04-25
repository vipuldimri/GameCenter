/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author u20o90
 */
public class GameNameSetting extends javax.swing.JDialog {

    /**
     * Creates new form GameNameSetting
     */
    public GameNameSetting(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    setLocation(x, y);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        AddGameButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(581, 487));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Check Game Names For Your Gamezone");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(120, 20, 360, 70);

        jCheckBox1.setText("Air Hockey");
        getContentPane().add(jCheckBox1);
        jCheckBox1.setBounds(110, 100, 90, 30);

        jCheckBox2.setText("Speed Ball");
        getContentPane().add(jCheckBox2);
        jCheckBox2.setBounds(110, 140, 80, 30);

        jCheckBox3.setText("jCheckBox3");
        getContentPane().add(jCheckBox3);
        jCheckBox3.setBounds(110, 180, 110, 23);

        jCheckBox4.setText("jCheckBox4");
        getContentPane().add(jCheckBox4);
        jCheckBox4.setBounds(110, 220, 120, 23);

        jCheckBox5.setText("jCheckBox5");
        getContentPane().add(jCheckBox5);
        jCheckBox5.setBounds(110, 260, 160, 23);

        jCheckBox6.setText("jCheckBox6");
        getContentPane().add(jCheckBox6);
        jCheckBox6.setBounds(360, 210, 120, 23);

        jCheckBox7.setText("jCheckBox6");
        getContentPane().add(jCheckBox7);
        jCheckBox7.setBounds(110, 300, 130, 23);

        jCheckBox8.setText("jCheckBox6");
        getContentPane().add(jCheckBox8);
        jCheckBox8.setBounds(110, 340, 150, 23);

        jCheckBox10.setText("jCheckBox6");
        getContentPane().add(jCheckBox10);
        jCheckBox10.setBounds(360, 290, 150, 23);

        jCheckBox11.setText("jCheckBox6");
        getContentPane().add(jCheckBox11);
        jCheckBox11.setBounds(360, 250, 120, 23);

        jCheckBox12.setText("jCheckBox6");
        getContentPane().add(jCheckBox12);
        jCheckBox12.setBounds(360, 130, 160, 30);

        jCheckBox13.setText("jCheckBox6");
        getContentPane().add(jCheckBox13);
        jCheckBox13.setBounds(360, 100, 150, 23);

        jCheckBox14.setText("jCheckBox6");
        getContentPane().add(jCheckBox14);
        jCheckBox14.setBounds(360, 170, 130, 23);

        AddGameButton.setText("Submit");
        AddGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddGameButtonActionPerformed(evt);
            }
        });
        getContentPane().add(AddGameButton);
        AddGameButton.setBounds(210, 390, 140, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddGameButtonActionPerformed
        // TODO add your handling code here:Submit button add games
        
        
        
    }//GEN-LAST:event_AddGameButtonActionPerformed

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
            java.util.logging.Logger.getLogger(GameNameSetting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameNameSetting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameNameSetting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameNameSetting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GameNameSetting dialog = new GameNameSetting(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddGameButton;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
