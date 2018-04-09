/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import Database.UserFactory;
import Database.UserInterface;
import gamecenter.UpdateEmployeeListThread;
import gamecenter.UpdatePasswordCheck;
import gamecenter.User;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import java.util.ArrayList;
import java.util.HashMap;
class Updata_DeleteEmployee extends javax.swing.JFrame 
{

    User current;
    JFrame previous;
    User updateemp;
    ArrayList<User> employeelist;
    boolean updateEmp_flag = false;
    boolean deleteEmp_flag = false;
    JDialog update;
    String currentstallname;
    HashMap<String, Boolean> passwordcheck;
    
    
    
    public Updata_DeleteEmployee(User current,JFrame previous,String currentstallname,ArrayList<User> employeelist,HashMap<String, Boolean> passwordcheck)
    {
        this.currentstallname = currentstallname;
        this.current = current;
        this.previous = previous;
        this.employeelist = employeelist;
        this.passwordcheck = passwordcheck;
        initComponents();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        
        jTextField1_empname.setText(current.getName());
        jTextField1_empemail.setText(current.getEmail());
        jTextField1_empaddress.setText(current.getAddress());
        jTextField1_empcontact.setText(current.getContact());
        jTextField1_emppassword.setText(current.getPassword());
        jTextField1_usernam.setText(current.getUserName());
        
        update = new JDialog();
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField1_empname = new javax.swing.JTextField();
        jTextField1_empcontact = new javax.swing.JTextField();
        jTextField1_empaddress = new javax.swing.JTextField();
        jTextField1_empemail = new javax.swing.JTextField();
        jTextField1_emppassword = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jTextField1_usernam = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(500, 500));
        jPanel1.setLayout(null);

        jButton1.setText("Update Emplyee ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(130, 420, 150, 50);

        jButton2.setText("Delete Employee");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(343, 430, 140, 40);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Password");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(130, 160, 150, 22);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Contact");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(480, 50, 90, 22);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Address");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(480, 280, 140, 22);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Email");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(480, 170, 49, 22);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Employee Name");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(130, 40, 180, 40);

        jTextField1_empname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jTextField1_empname);
        jTextField1_empname.setBounds(130, 90, 220, 40);

        jTextField1_empcontact.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jTextField1_empcontact);
        jTextField1_empcontact.setBounds(480, 90, 220, 40);

        jTextField1_empaddress.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jTextField1_empaddress);
        jTextField1_empaddress.setBounds(480, 320, 310, 80);

        jTextField1_empemail.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jTextField1_empemail);
        jTextField1_empemail.setBounds(480, 200, 220, 40);

        jTextField1_emppassword.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jTextField1_emppassword);
        jTextField1_emppassword.setBounds(130, 210, 220, 40);

        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(560, 430, 140, 40);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("UserName");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(130, 280, 140, 22);

        jTextField1_usernam.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jTextField1_usernam);
        jTextField1_usernam.setBounds(130, 320, 220, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:update button event 
        
        
        String username = jTextField1_empname.getText();
        String password = jTextField1_emppassword.getText();
        String usernamee  =jTextField1_usernam.getText();
        
        
        String address = jTextField1_empaddress.getText();
        String email = jTextField1_empemail.getText();
        String contact = jTextField1_empcontact.getText();
      
        if(passwordcheck.containsKey(usernamee) || usernamee.equals("admin") )
        {
            if(current.getUserName().equals(usernamee))
            {
                //old user fine can change
            }else{
            JOptionPane.showMessageDialog(jPanel1,
            "This UserName Is Taken",
            "Inane error",
            JOptionPane.ERROR_MESSAGE);
            return ;
            }
        }
        
        if(username.length() == 0 || password.length() == 0 || usernamee.length() == 0)
        {
           JOptionPane.showMessageDialog(jPanel1,
           "Enter Complete Details.",
           "Inane error",
            JOptionPane.ERROR_MESSAGE);
            return ;
        }
        if(current.getType().equals("admin") && !current.getEmail().equals(email))
        {
           JOptionPane.showMessageDialog(jPanel1,
           "Admin E-mail Id can not be change please contact us for this change.",
           "Inane error",
           JOptionPane.ERROR_MESSAGE);
           return ;
        }


        
        updateemp = new User();
        updateemp.setName(username);
        updateemp.setType(current.getType());
        updateemp.setAddress(address);
        updateemp.setContact(contact);
        updateemp.setEmail(email);
        updateemp.setPassword(password);
        updateemp.setID(current.getID());
        updateemp.setGameZoneID(current.getGameZoneID());
        updateemp.setUserName(usernamee);

        
              SwingWorker work = new SwingWorker<String , Integer>() 
                 {
	            @Override
	            protected  String  doInBackground() throws Exception 
	            {
	             

                   
                    UserInterface Dao = null;
                    try 
                    {
                    Dao = UserFactory.getInstance();
                    Dao.UpdateEmp(updateemp,currentstallname);
                    updateEmp_flag = true;
                    
                    } catch (Exception ex) 
                    {
                    Logger.getLogger(MainScreen_StallOwner.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("HERE");
                    updateEmp_flag = false;
                   }
                

                        return "end";
	                
	            }//do backgrounf ENDS


	            @Override
	            protected void done()
                    {
                        
                      update.dispose();
	            }
	        };
        final ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/Images/recharge.gif"));
        work.execute();
        JOptionPane pane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, icon,new Object[]{}, null);
        update = pane.createDialog(this,"Please wait ");
        update.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      
        //jDialog.show();  this method is depricated there using setVisible method for showing the dialoge box 
        update.setVisible(true);   
                 
        
        if(updateEmp_flag == false)
        {
            JOptionPane.showMessageDialog(jPanel1,
                     "Update Employee Failed.",
                     "Inane error",
                      JOptionPane.ERROR_MESSAGE);
                   return;
        }
        else
        {
                      UpdateEmployeeListThread updateEmployeeListThread = new UpdateEmployeeListThread(employeelist,currentstallname);
                      updateEmployeeListThread.start();
                      
                      UpdatePasswordCheck updatepasswordcheck = new UpdatePasswordCheck(currentstallname,passwordcheck);
                      updatepasswordcheck.start();
                      
                      
                      JOptionPane.showMessageDialog(jPanel1,
                      "Update Employee Success.",
                      "Inane error",
                      JOptionPane.ERROR_MESSAGE);
                     
        }
        
        updateEmp_flag = false;
        previous.setVisible(true);
        dispose();
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:Delete Button Event

       int dialogButton = JOptionPane.YES_NO_OPTION;
       int dialogResult = JOptionPane.showConfirmDialog (null, "Delete confirm  ???","Warning",dialogButton);
       if(dialogResult == JOptionPane.NO_OPTION)
       {
                     return ;
       }
        if(current.getType().equals("admin"))
        {
                    JOptionPane.showMessageDialog(jPanel1,
                    "Can't delete Admin",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
                    return;
        }
        
                 //above for confirming the 
                 SwingWorker work = new SwingWorker<String , Integer>() 
                 {
	            @Override
	            protected  String  doInBackground() throws Exception 
	            {

                    UserInterface Dao = null;
                    try 
                    {
                    Dao = UserFactory.getInstance();
                    Dao.DeleteEmp(current.getID(),currentstallname);
                    deleteEmp_flag = true;
                    
                    } catch (Exception ex) 
                    {
                        deleteEmp_flag = false;
                    Logger.getLogger(MainScreen_StallOwner.class.getName()).log(Level.SEVERE, null, ex);
                    }
                

                        return "end";
	                
	            }//do backgrounf ENDS


	            @Override
	            protected void done()
                    {
                        
                      update.dispose();
	            }
	        };
        final ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/Images/recharge.gif"));
        work.execute();
        JOptionPane pane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, icon,new Object[]{}, null);
        update = pane.createDialog(this,"Please wait ");
        update.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      
        //jDialog.show();  this method is depricated there using setVisible method for showing the dialoge box 
        update.setVisible(true);   
                 
        
        if(deleteEmp_flag == false)
        {
                     JOptionPane.showMessageDialog(jPanel1,
                     "Delete Employee Failed.",
                     "Inane error",
                      JOptionPane.ERROR_MESSAGE);
                     return;
        }else
        {
            
                      UpdateEmployeeListThread updateEmployeeListThread = new UpdateEmployeeListThread(employeelist,currentstallname);
                      updateEmployeeListThread.start();
                      
                      UpdatePasswordCheck updatePasswordCheck = new UpdatePasswordCheck(currentstallname, passwordcheck);
                      updatePasswordCheck.start();
                      
                      
                     JOptionPane.showMessageDialog(jPanel1,
                     "Employee Deleted.",
                     "Inane error",
                     JOptionPane.ERROR_MESSAGE);
        }
        deleteEmp_flag = false;
        previous.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:cancel button
        setVisible(false);
        previous.setVisible(true);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
      previous.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Updata_DeleteEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Updata_DeleteEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Updata_DeleteEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Updata_DeleteEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Updata_DeleteEmployee();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1_empaddress;
    private javax.swing.JTextField jTextField1_empcontact;
    private javax.swing.JTextField jTextField1_empemail;
    private javax.swing.JTextField jTextField1_empname;
    private javax.swing.JTextField jTextField1_emppassword;
    private javax.swing.JTextField jTextField1_usernam;
    // End of variables declaration//GEN-END:variables
}
