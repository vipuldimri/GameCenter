/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Database.CustomerInterface;
import Database.Customerfactory;
import gamecenter.Customers;
import gamecenter.Stall;
import gamecenter.UpdateCustomerListThread;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author u20o90
 */
public class RegisterCustomer extends javax.swing.JDialog {

    /**
     * Creates new form RegisterCustomer
     */
    ArrayList<Customers> customerlist;
    JLabel noofcustomerslabel;
    JTable customertable;
    Stall currentgamezone;
    boolean RegisterCustomer = false;
      JDialog rechargeloadingdialoge;
    
    public RegisterCustomer(java.awt.Frame parent, boolean modal,JLabel noofcustomerslabel,JTable customertable , ArrayList<Customers> customerlist , Stall currentgamezone) 
    {
        super(parent, modal);
        initComponents();
        this.noofcustomerslabel =  noofcustomerslabel;
        this.customerlist = customerlist;
        this.customertable = customertable;
        this.currentgamezone = currentgamezone;
    
        //To display form in center of the screen
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

        jLabel43 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        RegisterNewcustomer_button1 = new javax.swing.JButton();
        ResetButton_Customer1 = new javax.swing.JButton();
        Name = new javax.swing.JTextField();
        Email = new javax.swing.JTextField();
        Contact = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(596, 564));
        getContentPane().setLayout(null);

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel43.setText("Name");
        getContentPane().add(jLabel43);
        jLabel43.setBounds(150, 100, 140, 50);

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel42.setText("Contact");
        getContentPane().add(jLabel42);
        jLabel42.setBounds(150, 320, 90, 60);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel41.setText("Email");
        getContentPane().add(jLabel41);
        jLabel41.setBounds(150, 210, 80, 50);

        RegisterNewcustomer_button1.setText("Register ");
        RegisterNewcustomer_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterNewcustomer_button1ActionPerformed(evt);
            }
        });
        getContentPane().add(RegisterNewcustomer_button1);
        RegisterNewcustomer_button1.setBounds(110, 440, 160, 60);

        ResetButton_Customer1.setText("Reset");
        ResetButton_Customer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetButton_Customer1ActionPerformed(evt);
            }
        });
        getContentPane().add(ResetButton_Customer1);
        ResetButton_Customer1.setBounds(320, 440, 140, 60);

        Name.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(Name);
        Name.setBounds(150, 160, 300, 40);

        Email.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(Email);
        Email.setBounds(150, 270, 300, 40);

        Contact.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(Contact);
        Contact.setBounds(150, 380, 300, 40);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Register New Customer");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(150, 20, 310, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegisterNewcustomer_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterNewcustomer_button1ActionPerformed
        // TODO add your handling code here:

        String email =Email.getText();
        String name   =Name.getText();
        String contact = Contact.getText();

        if(contact.length() == 0 || name.length() == 0)
        {
                JOptionPane.showMessageDialog(this,
                "Name and Contact are Mandatory",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
                return;

        }

        Customers newcustomer = new Customers();
        newcustomer.setName(name);
        newcustomer.setContact(contact);
        newcustomer.setEmailId(email);

        SwingWorker work = new SwingWorker<String , Integer>()
        {
            @Override
            protected  String  doInBackground() throws Exception
            {

                try
                {
                    CustomerInterface Dao = Customerfactory.getInstance();
                    boolean respponce =  Dao.registerCust(newcustomer, currentgamezone.getName()+"_customers");

                    if(respponce == false)
                    {
                        RegisterCustomer = false;
                    }else
                    {
                        RegisterCustomer = true;
                    }
                } catch (Exception ex)
                {

                    RegisterCustomer = false;
                }

                return "end";

            }//do backgrounf ENDS

            @Override
            protected void done()
            {

                rechargeloadingdialoge.setVisible(false);
            }
        };

        final ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/Images/recharge.gif"));
        work.execute();
        JOptionPane pane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, icon,new Object[]{}, null);
        rechargeloadingdialoge = pane.createDialog(this,"Please wait ");
        rechargeloadingdialoge.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        rechargeloadingdialoge.setVisible(true);

        if(RegisterCustomer == false)
        {
            System.out.println("Error in recharge");
            JOptionPane.showMessageDialog(this,
                "Registeration Failed",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return ;
        }else
        {

            UpdateCustomerListThread updatecustomerlist = new UpdateCustomerListThread(customerlist,currentgamezone.getName());
            updatecustomerlist.start();

            JOptionPane.showMessageDialog(this,
                "Customer Registered Success",
                "Success",
                JOptionPane.PLAIN_MESSAGE);
            //now updating the customer list
        }

        RegisterCustomer = false;

        Email.setText("");
        Name.setText("");
        Contact.setText("");

        //code for filled table with new customer list
        DefaultTableModel m = (DefaultTableModel) customertable.getModel();
        m.setRowCount(0);

        DefaultTableModel  model = (DefaultTableModel) customertable.getModel();
        Object row[] = new Object[4];
        for(int i = 0;i < customerlist.size();i++)
        {
            row[0] = customerlist.get(i).getId();
            row[1] = customerlist.get(i).getName();
            row[2] = customerlist.get(i).getContact();
            row[3] = customerlist.get(i).getEmailId();

            model.addRow(row);

        }
        noofcustomerslabel.setText(customerlist.size()+"");

    }//GEN-LAST:event_RegisterNewcustomer_button1ActionPerformed

    private void ResetButton_Customer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetButton_Customer1ActionPerformed
        // TODO add your handling code here:

        Contact.setText("");
        Name.setText("");
        Email.setText("");
    }//GEN-LAST:event_ResetButton_Customer1ActionPerformed

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
            java.util.logging.Logger.getLogger(RegisterCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Contact;
    private javax.swing.JTextField Email;
    private javax.swing.JTextField Name;
    private javax.swing.JButton RegisterNewcustomer_button1;
    private javax.swing.JButton ResetButton_Customer1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    // End of variables declaration//GEN-END:variables
}
