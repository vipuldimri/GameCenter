package Gui;
import gamecenter.Stall;
import gamecenter.Stalls_and_SubDate;
import gamecenter.User;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Database.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import gamecenter.Background_GetTransactionDetails;
import gamecenter.Recharge;
import java.util.concurrent.CountDownLatch;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
public class MainScreen_Admin extends javax.swing.JFrame 
{
    //Containsa all the Users of the System
    ArrayList<User> users;
    //Contains GameZones Details
    Stalls_and_SubDate stalls;
   JDialog jDialog;
    
    String ErrorMessage;
    TransactionInterface Dao;
    ArrayList<Recharge> transactionlist;
    public MainScreen_Admin( ArrayList<User> users , Stalls_and_SubDate stalls)
    {
        this.stalls = stalls;
        this.users = users;
        initComponents();
        
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    
    
        jPanel3.setVisible(true);
        jPanel4.setVisible(false);
        jPanel5.setVisible(false);
        jComboBox_allgamezones.addItem("Select GameZone");
       
    }
    
    //Employee view
      public MainScreen_Admin(String str) {
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel_Sub = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField_GameZoneAddress = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jComboBox_sub = new javax.swing.JComboBox<>();
        jTextField_GameZoneName = new javax.swing.JTextField();
        jTextField_GameZoneOwnerName = new javax.swing.JTextField();
        jTextField_GameZoneOwnerContact = new javax.swing.JTextField();
        jTextField_GameZoneOwnerPassword = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Gamezone = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_transaction = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jComboBox_allgamezones = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(1204, 767));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(102, 0, 153));
        jPanel2.setLayout(null);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });
        jPanel6.setLayout(null);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Add GameZone");
        jPanel6.add(jLabel1);
        jLabel1.setBounds(130, 30, 87, 16);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Add gamezone.png"))); // NOI18N
        jPanel6.add(jLabel4);
        jLabel4.setBounds(30, 10, 80, 80);

        jPanel2.add(jPanel6);
        jPanel6.setBounds(0, 300, 300, 110);

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });
        jPanel7.setLayout(null);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("View Registeed GameZones");
        jPanel7.add(jLabel2);
        jLabel2.setBounds(120, 30, 170, 20);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/View gamezones.png"))); // NOI18N
        jPanel7.add(jLabel5);
        jLabel5.setBounds(30, 20, 70, 60);

        jPanel2.add(jPanel7);
        jPanel7.setBounds(0, 450, 300, 100);

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });
        jPanel8.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Transaction Records");
        jPanel8.add(jLabel3);
        jLabel3.setBounds(140, 20, 140, 30);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/viewrecordadmin.png"))); // NOI18N
        jPanel8.add(jLabel6);
        jLabel6.setBounds(30, 10, 70, 70);

        jPanel2.add(jPanel8);
        jPanel8.setBounds(0, 600, 300, 100);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 300, 730);

        jPanel3.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("Add New GameZone");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(319, 14, 320, 40);

        jLabel_Sub.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_Sub.setText("Sub Yr");
        jPanel3.add(jLabel_Sub);
        jLabel_Sub.setBounds(500, 350, 140, 50);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setText("Owner Name");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(70, 190, 220, 60);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setText("Contact No");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(70, 300, 220, 60);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setText("Address");
        jPanel3.add(jLabel13);
        jLabel13.setBounds(500, 70, 220, 60);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setText("GameZone Name");
        jPanel3.add(jLabel14);
        jLabel14.setBounds(70, 70, 220, 60);

        jTextField_GameZoneAddress.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel3.add(jTextField_GameZoneAddress);
        jTextField_GameZoneAddress.setBounds(500, 130, 350, 80);

        jButton1.setText("Add GameZone");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(350, 455, 230, 50);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setText("Passsword");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(500, 210, 170, 60);

        jComboBox_sub.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jComboBox_sub.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 yr", "2 yr", "3 yr", "4 yr", " " }));
        jPanel3.add(jComboBox_sub);
        jComboBox_sub.setBounds(610, 360, 140, 40);

        jTextField_GameZoneName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel3.add(jTextField_GameZoneName);
        jTextField_GameZoneName.setBounds(70, 140, 230, 40);

        jTextField_GameZoneOwnerName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel3.add(jTextField_GameZoneOwnerName);
        jTextField_GameZoneOwnerName.setBounds(70, 260, 230, 40);

        jTextField_GameZoneOwnerContact.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel3.add(jTextField_GameZoneOwnerContact);
        jTextField_GameZoneOwnerContact.setBounds(70, 370, 230, 40);

        jTextField_GameZoneOwnerPassword.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel3.add(jTextField_GameZoneOwnerPassword);
        jTextField_GameZoneOwnerPassword.setBounds(500, 270, 230, 40);

        jPanel4.setLayout(null);

        jTable_Gamezone.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "OwnerName", "Address", "Contact", "Password", "SubStartDate", "SubEndDate"
            }
        ));
        jScrollPane2.setViewportView(jTable_Gamezone);

        jPanel4.add(jScrollPane2);
        jScrollPane2.setBounds(0, 242, 900, 300);

        jTable_transaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CardN0", "EmpName", "Amount", "Date"
            }
        ));
        jScrollPane1.setViewportView(jTable_transaction);

        jLabel8.setText("Select GameZone");

        jComboBox_allgamezones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_allgamezonesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel8)
                .addGap(53, 53, 53)
                .addComponent(jComboBox_allgamezones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox_allgamezones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLayeredPane1.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jLayeredPane1);
        jLayeredPane1.setBounds(300, 190, 900, 540);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:Add new GameZone 
        jPanel3.setVisible(true);
        jPanel4.setVisible(false);
        jPanel5.setVisible(false);
        
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:Create button 
        /*
        Code for Submit Button ADD GameZone
        */
        String Gamezonename = jTextField_GameZoneName.getText();
        String Conact = jTextField_GameZoneOwnerContact.getText();
        String Password = jTextField_GameZoneOwnerPassword.getText();
        String OwnerName = jTextField_GameZoneOwnerName.getText();
        String address = jTextField_GameZoneAddress.getText();
        
        String select = jComboBox_sub.getSelectedItem().toString();
        int subyears = Integer.parseInt(select.substring(0,1));
        
        long millis=System.currentTimeMillis();  
        java.sql.Date currentdate=new java.sql.Date(millis);
        String currentdateString = currentdate.toString();
        String TempStr = currentdateString.substring(4);
        //2015-03-30 format is this 
        int currentyr = Integer.parseInt(currentdateString.substring(0,4));
        int Endsubyr = currentyr + subyears;
        
        String FinalEndsubString = Endsubyr+TempStr;
        java.sql.Date finalEndsubDate   =  java.sql.Date.valueOf(FinalEndsubString);        
        
        
        Stall newgamezone = new Stall();
        newgamezone.setAddress(address);
        newgamezone.setContact(Conact);
        newgamezone.setName(Gamezonename);
        newgamezone.setPassword(Password);
        newgamezone.setOwnerName(OwnerName);
        newgamezone.setSubStartDate(currentdate);
        newgamezone.setSubEndDate(finalEndsubDate);
        
        //Completed getting data from GUI and storing into obbject of stall type
        
        //Now using backgrounf to add New gamezone
        
        
        
        
        MainAdminInterface Dao = null;
        try {
            Dao = MainAdminFactory.getInstance();
        } catch (Exception ex) 
        {
            //inCase of any errot in Adding new GameZone
            Logger.getLogger(MainScreen_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dao.AddGameZone(newgamezone);
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:
        jPanel3.setVisible(false);
        jPanel4.setVisible(true);
        jPanel5.setVisible(false);
        
        
        
        //display registered GameZone
       
        
        //Displaying All the Registered GameZones Into JTABLE
        DefaultTableModel m = (DefaultTableModel) jTable_Gamezone.getModel();
        m.setRowCount(0);
        DefaultTableModel  model = (DefaultTableModel) jTable_Gamezone.getModel();
        Object row[] = new Object[8];
        for(int i=0;i < stalls.stalls.size();i++)
        {
        row[0] = stalls.stalls.get(i).getID();
        row[1] = stalls.stalls.get(i).getName(); 
        row[2] = stalls.stalls.get(i).getOwnerName(); 
        row[3] = stalls.stalls.get(i).getAddress();
        row[4] = stalls.stalls.get(i).getContact();
        row[5] = stalls.stalls.get(i).getPassword();
        row[6] = stalls.stalls.get(i).getSubStartDate();
        row[7] = stalls.stalls.get(i).getSubEndDate();
      
        model.addRow(row);
        
        
        

        }
        
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        // TODO add your handling code here:
        jPanel3.setVisible(false);
        jPanel4.setVisible(false);
        jPanel5.setVisible(true);
          
       for(Stall gamezone :stalls.stalls)
       {
        jComboBox_allgamezones.addItem(gamezone.getName());
       }
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jComboBox_allgamezonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_allgamezonesActionPerformed
        // TODO add your handling code here:action event on conbo box
        String GamezoneName = jComboBox_allgamezones.getSelectedItem().toString();
        if(GamezoneName.equals("Select GameZone"))
        {
            return ;
        }
     
      
        //Background worker for getting the data from the server 
        SwingWorker work = new SwingWorker<String , Integer>() {
	            @Override
	            protected  String  doInBackground() throws Exception 
	            {
	              
                     try 
                      {
                       Dao = TransactionFactory.getInstance();
                       } 
                     catch (Exception ex) 
                      {
                           System.out.println(ex);
                           Logger.getLogger(Background_GetTransactionDetails.class.getName()).log(Level.SEVERE, null, ex);
                      }
                        //Arguments is table Name
               
                        String TransactionTableName = GamezoneName+"_transaction";
                        try 
                       {
                       transactionlist  = Dao.GetTransactionDetails(TransactionTableName);
                       } catch (Exception ex)
                         {
                           System.out.println("ERROR IN THREAD "+ex);
                               ErrorMessage = "TransactionError";
          
                              }
                                  ;           
                                  jDialog.dispose();
                        return "end";
	                
	            }//do backgrounf ENDS


	            @Override
	            protected void done()
                    {
                        
        
	            }
	        };
        final ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/Images/Loading.gif"));
        work.execute();
        JOptionPane pane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, icon,new Object[]{}, null);
        jDialog = pane.createDialog(this,"Loading Date");
        jDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      
        //jDialog.show();  this method is depricated there using setVisible method for showing the dialoge box 
        jDialog.setVisible(true);
 
                
        //Filling the Jtable 
        DefaultTableModel m = (DefaultTableModel) jTable_transaction.getModel();
        m.setRowCount(0);
        DefaultTableModel  model = (DefaultTableModel) jTable_transaction.getModel();
        Object row[] = new Object[5];
        for(int i=0;i < transactionlist.size();i++)
        {
        row[0] = transactionlist.get(i).getID();
        row[1] = transactionlist.get(i).getCardNo(); 
        row[2] = transactionlist.get(i).getEmpName(); 
        row[3] = transactionlist.get(i).getAmount();
        row[4] = transactionlist.get(i).getDate();
      
      
        model.addRow(row);
        
        
        

        }
        
    }//GEN-LAST:event_jComboBox_allgamezonesActionPerformed

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
            java.util.logging.Logger.getLogger(MainScreen_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new MainScreen_Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox_allgamezones;
    private javax.swing.JComboBox<String> jComboBox_sub;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Sub;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Gamezone;
    private javax.swing.JTable jTable_transaction;
    private javax.swing.JTextField jTextField_GameZoneAddress;
    private javax.swing.JTextField jTextField_GameZoneName;
    private javax.swing.JTextField jTextField_GameZoneOwnerContact;
    private javax.swing.JTextField jTextField_GameZoneOwnerName;
    private javax.swing.JTextField jTextField_GameZoneOwnerPassword;
    // End of variables declaration//GEN-END:variables
}
