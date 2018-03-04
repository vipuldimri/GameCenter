
package Gui;

import Database.TransactionFactory;
import Database.TransactionInterface;
import Database.UserFactory;
import Database.UserInterface;
import gamecenter.Background_GetTransactionDetails;
import gamecenter.Recharge;
import gamecenter.User;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Window;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
//Main screen for every GameZone
public class MainScreen_StallOwner extends javax.swing.JFrame 
{

    User currentuser;
    ArrayList<User> currentStallUsers;
    String Type ;
    LoginScreen l ;
    String currentstallname;
    ArrayList<Recharge> transactiondetails;
    JDialog dialog;
    CountDownLatch loginSignal;
    ArrayList<Recharge> transdetailscomplete;
    
    JDialog refresh;
    ArrayList<String> CurrentStallGames;
    ArrayList<JTextField> testfields;
    
    public MainScreen_StallOwner(LoginScreen l ,User currentuser ,ArrayList<User> currentStallUsers,String currentstallname, ArrayList<String> CurrentStallGames) 
    {
        System.out.println("LL "+l);
        this.currentstallname = currentstallname;
        this.CurrentStallGames = CurrentStallGames;
     
        this.l = l;
      
        this.currentStallUsers = currentStallUsers;
        this.currentuser = currentuser;
        initComponents();
        System.out.println("askljaskj");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        
        
        //int framesizeh = dimension.height*8/10;
        //int framesizew = dimension.width*8/10;
        //setBounds(framesizew/8,framesizeh/8,framesizew,framesizeh);
        setLocation(x, y);
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        buttonGroup1.add(jRadioButton4);
        
        jPanel16.setVisible(false);
        jPanel10.setVisible(false);
        jPanel19.setVisible(false);
        jTabbedPane1.setEnabledAt(2, false);
        //to hide admin
        // Gettingdata.setVisible(false);
        loginSignal = new CountDownLatch(1);
        jLabel_OwnerName.setText(this.currentuser.getName());
        jLabel_GameZoneNAme.setText(currentstallname);
             
        //Background thread for getting transaction details         
        Background_GetTransactionDetails background_GetTransactionDetails = new Background_GetTransactionDetails(loginSignal,currentstallname);
        background_GetTransactionDetails.start();
        try
        {
      
            
            loginSignal.await();
        } catch (InterruptedException ex)
        {
            Logger.getLogger(MainScreen_StallOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        transdetailscomplete = background_GetTransactionDetails.transactiondetails; 
        if(null == transdetailscomplete || background_GetTransactionDetails.ErrorMessage.equals("TransactionError"))
        {
            
              //Error 
                JOptionPane.showMessageDialog(jPanel1,
                "No internet Connection.",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            
            
        }
        
        jLabel_currentEMpName.setText(currentuser.getName());
        jLabel_currentempname2.setText(currentuser.getName());
          if(CurrentStallGames.size() > 0)
        {
            
          jComboBox_GamesList.addItem("Select Game");    
        for(String game : CurrentStallGames)
        {
             jComboBox_GamesList.addItem(game);    
        }
        
        
        }
        
    }
        //employee
        public MainScreen_StallOwner(String str,LoginScreen l ,User currentuser ,ArrayList<User> currentStallUsers,String currentstallname,ArrayList<String> CurrentStallGames)
        {
            
          testfields = new ArrayList<>();
          this.CurrentStallGames = CurrentStallGames;
          
          
          this.currentstallname = currentstallname;
          this.l = l;
          Type = str;
          this.currentStallUsers = currentStallUsers;
          this.currentuser = currentuser;
          initComponents();
          jPanel21.setLayout(new GridLayout(3,3));
          Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
          int x = (int) ((dimension.getWidth() - getWidth()) / 2);
          int y = (int) ((dimension.getHeight() - getHeight()) / 2);
          int framesizeh = dimension.height*8/10;
          int framesizew = dimension.width*8/10;
         
           setLocation(x, y);

          
//          setBounds(framesizew/8,framesizeh/8,framesizew,framesizeh);
//          jPanel1.setBounds(framesizew/8,framesizeh/8,framesizew,framesizeh);
//          jPanel2.setBounds(framesizew/8,framesizeh/8,framesizew,framesizeh);
//          jPanel3.setBounds(framesizew/8,framesizeh/8,framesizew,framesizeh);
//          jPanel4.setBounds(framesizew/8,framesizeh/8,framesizew,framesizeh);
//            pack();
//          
//          
          
          
          buttonGroup1.add(jRadioButton1);
          buttonGroup1.add(jRadioButton2);
          buttonGroup1.add(jRadioButton3);
          buttonGroup1.add(jRadioButton4);
        
          
          // Gettingdata.setVisible(false);
          jPanel16.setVisible(false);
          jPanel10.setVisible(false);
          loginSignal = new CountDownLatch(1);
          jLabel_OwnerName.setText(this.currentuser.getName());
          jLabel_GameZoneNAme.setText(currentstallname);

         //background thread for getting data New Refresh Data
         Background_GetTransactionDetails background_GetTransactionDetails = new Background_GetTransactionDetails(loginSignal,currentstallname);
         background_GetTransactionDetails.start();
         try {
      
            
            loginSignal.await();
        } catch (InterruptedException ex)
        {
            Logger.getLogger(MainScreen_StallOwner.class.getName()).log(Level.SEVERE, null, ex);
        
        }
      
        transdetailscomplete = background_GetTransactionDetails.transactiondetails; 
       
        
        if(null == transdetailscomplete || background_GetTransactionDetails.ErrorMessage.equals("TransactionError"))
        {
            
              //Error 
              JOptionPane.showMessageDialog(jPanel1,
                  "No internet Connection.",
                  "Inane error",
                  JOptionPane.ERROR_MESSAGE);
                  System.exit(0);
            
            
        }
        
        
       
     jLabel_currentEMpName.setText(currentuser.getName());
     jLabel_currentempname2.setText(currentuser.getName());
       if(CurrentStallGames.size() > 0)
        {
            
           
          jComboBox_GamesList.addItem("Select Game");   
          for(String game : CurrentStallGames)
          {
           jComboBox_GamesList.addItem(game);
             
             JLabel j = new JLabel(game);
             JTextField jt = new JTextField();
             jt.setText(game);
             
             JTextField jt2 = new JTextField();
             jt2.setText(game);
             
             testfields.add(jt);
             testfields.add(jt2);
             
             j.setFont(new java.awt.Font("Tahoma", 1, 18)); 
             j.setForeground(new java.awt.Color(255, 255, 255));
             
             jPanel21.add(j);
             jPanel21.add(jt);
             jPanel21.add(jt2);
         
          }
        
        
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel_currentEMpName = new javax.swing.JLabel();
        jComboBox_GamesList = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jLabel_currentEMpName1 = new javax.swing.JLabel();
        jLabel_currentempname2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_transactionDetailsEmp = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jTextField_Name = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextField1_address = new javax.swing.JTextField();
        jTextField1_password = new javax.swing.JTextField();
        jTextField1_email = new javax.swing.JTextField();
        jTextField1_contact = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_EmpRecord = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_transactionDetails = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jDateChooser_StartDate = new com.toedter.calendar.JDateChooser();
        jDateChooser_EndDate = new com.toedter.calendar.JDateChooser();
        jButton7 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel3_totalTransaction = new javax.swing.JLabel();
        jLabel_validDate = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel_GameZoneNAme = new javax.swing.JLabel();
        jLabel_OwnerName = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Screen ");
        setResizable(false);

        jPanel1.setOpaque(false);

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Recharge");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(20, 0, 160, 33);
        jPanel4.add(jSeparator1);
        jSeparator1.setBounds(0, 40, 1190, 2);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Amount");
        jPanel4.add(jLabel2);
        jLabel2.setBounds(350, 160, 100, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Card No");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(360, 90, 80, 20);

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel4.add(jTextField1);
        jTextField1.setBounds(580, 90, 260, 50);

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel4.add(jTextField2);
        jTextField2.setBounds(580, 160, 260, 60);

        jButton1.setText("jButton1");
        jPanel4.add(jButton1);
        jButton1.setBounds(220, 370, 130, 40);

        jButton2.setText("Recharge ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);
        jButton2.setBounds(550, 370, 110, 40);

        jButton3.setText("Reset To Zero");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);
        jButton3.setBounds(840, 370, 130, 50);

        jRadioButton1.setBackground(new java.awt.Color(0, 0, 0));
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("100");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton1);
        jRadioButton1.setBounds(240, 260, 107, 37);

        jRadioButton2.setBackground(new java.awt.Color(0, 0, 0));
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("200");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton2);
        jRadioButton2.setBounds(430, 260, 110, 40);

        jRadioButton3.setBackground(new java.awt.Color(0, 0, 0));
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setText("500");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton3);
        jRadioButton3.setBounds(580, 260, 73, 37);

        jRadioButton4.setBackground(new java.awt.Color(0, 0, 0));
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setText("1000");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton4);
        jRadioButton4.setBounds(780, 260, 89, 37);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Welcome");
        jPanel4.add(jLabel27);
        jLabel27.setBounds(890, 0, 110, 30);

        jLabel_currentEMpName.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_currentEMpName.setText("jLabel28");
        jPanel4.add(jLabel_currentEMpName);
        jLabel_currentEMpName.setBounds(1010, 10, 48, 16);

        jPanel4.add(jComboBox_GamesList);
        jComboBox_GamesList.setBounds(910, 190, 100, 22);

        jPanel2.add(jPanel4);
        jPanel4.setBounds(2, 7, 1200, 440);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Existing Amount");
        jPanel5.add(jLabel4);
        jLabel4.setBounds(0, 0, 100, 30);
        jPanel5.add(jSeparator2);
        jSeparator2.setBounds(0, 30, 660, 2);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Existing Amount ");
        jPanel5.add(jLabel5);
        jLabel5.setBounds(110, 90, 100, 30);

        jTextField3.setText("jTextField3");
        jPanel5.add(jTextField3);
        jTextField3.setBounds(350, 80, 69, 22);

        jPanel2.add(jPanel5);
        jPanel5.setBounds(280, 470, 660, 190);

        jTabbedPane1.addTab("Recharge ", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(null);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setForeground(new java.awt.Color(204, 255, 255));
        jPanel6.setLayout(null);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Today Collection");
        jPanel6.add(jLabel6);
        jLabel6.setBounds(0, 13, 94, 16);
        jPanel6.add(jSeparator3);
        jSeparator3.setBounds(-380, 40, 1430, 2);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Welcome");
        jPanel6.add(jLabel28);
        jLabel28.setBounds(710, 0, 110, 30);

        jLabel_currentEMpName1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_currentEMpName1.setText("jLabel28");
        jPanel6.add(jLabel_currentEMpName1);
        jLabel_currentEMpName1.setBounds(1010, 10, 48, 20);

        jLabel_currentempname2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_currentempname2.setText("jLabel29");
        jPanel6.add(jLabel_currentempname2);
        jLabel_currentempname2.setBounds(890, 10, 48, 16);

        jTable_transactionDetailsEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CardNo", "EmpName", "Amount", "Date"
            }
        ));
        jScrollPane3.setViewportView(jTable_transactionDetailsEmp);

        jPanel6.add(jScrollPane3);
        jScrollPane3.setBounds(0, 100, 1200, 200);

        jPanel3.add(jPanel6);
        jPanel6.setBounds(0, 0, 1200, 280);

        jPanel21.setBackground(new java.awt.Color(0, 0, 0));
        jPanel21.setLayout(new java.awt.GridLayout(6, 3));
        jPanel3.add(jPanel21);
        jPanel21.setBounds(180, 320, 860, 400);

        jTabbedPane1.addTab("Today Collection", jPanel3);

        jPanel8.setLayout(null);

        jPanel7.setBackground(new java.awt.Color(153, 0, 153));
        jPanel7.setLayout(null);

        jPanel10.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel7.setText("Add new Employee");
        jPanel10.add(jLabel7);
        jLabel7.setBounds(240, -10, 380, 100);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Name");
        jPanel10.add(jLabel12);
        jLabel12.setBounds(90, 100, 53, 22);

        jButton4.setText("Submit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton4);
        jButton4.setBounds(370, 490, 120, 40);

        jTextField_Name.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel10.add(jTextField_Name);
        jTextField_Name.setBounds(90, 148, 210, 40);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setText("Password");
        jPanel10.add(jLabel22);
        jLabel22.setBounds(510, 230, 87, 22);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setText("Contact");
        jPanel10.add(jLabel23);
        jLabel23.setBounds(510, 110, 70, 22);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setText("Email");
        jPanel10.add(jLabel24);
        jLabel24.setBounds(90, 220, 49, 22);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setText("Address");
        jPanel10.add(jLabel25);
        jLabel25.setBounds(100, 360, 71, 22);

        jTextField1_address.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel10.add(jTextField1_address);
        jTextField1_address.setBounds(200, 350, 420, 70);

        jTextField1_password.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel10.add(jTextField1_password);
        jTextField1_password.setBounds(510, 260, 220, 50);

        jTextField1_email.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel10.add(jTextField1_email);
        jTextField1_email.setBounds(90, 250, 220, 40);

        jTextField1_contact.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel10.add(jTextField1_contact);
        jTextField1_contact.setBounds(500, 140, 210, 50);

        jPanel16.setLayout(null);

        jLabel8.setText("Update / Delete Employee");
        jPanel16.add(jLabel8);
        jLabel8.setBounds(362, 23, 148, 16);

        jLabel13.setText("Enter Employee Name ");
        jPanel16.add(jLabel13);
        jLabel13.setBounds(95, 76, 130, 16);
        jPanel16.add(jTextField7);
        jTextField7.setBounds(95, 110, 210, 50);

        jButton5.setText("Update");
        jPanel16.add(jButton5);
        jButton5.setBounds(230, 510, 73, 25);

        jButton6.setText("Delete");
        jPanel16.add(jButton6);
        jButton6.setBounds(510, 510, 69, 25);

        jPanel17.setLayout(null);

        jTable_EmpRecord.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Address", "Contact", "Email", "Type", "GameZoneID", "Password"
            }
        ));
        jScrollPane1.setViewportView(jTable_EmpRecord);

        jPanel17.add(jScrollPane1);
        jScrollPane1.setBounds(0, 205, 860, 350);

        jLabel10.setText("Employee Record");
        jPanel17.add(jLabel10);
        jLabel10.setBounds(370, 20, 130, 16);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Search for Employee");
        jPanel17.add(jLabel11);
        jLabel11.setBounds(40, 70, 220, 60);
        jPanel17.add(jTextField4);
        jTextField4.setBounds(40, 120, 190, 40);

        jPanel19.setLayout(null);

        jLabel15.setText("Transaction History");
        jPanel19.add(jLabel15);
        jLabel15.setBounds(340, 10, 110, 16);

        jTable_transactionDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CardNo", "EmpName", "Amount", "Date"
            }
        ));
        jScrollPane2.setViewportView(jTable_transactionDetails);

        jPanel19.add(jScrollPane2);
        jScrollPane2.setBounds(0, 160, 880, 402);

        jPanel20.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel20Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel20Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel19.add(jPanel20);
        jPanel20.setBounds(260, 220, 350, 210);
        jPanel19.add(jDateChooser_StartDate);
        jDateChooser_StartDate.setBounds(150, 70, 100, 22);
        jPanel19.add(jDateChooser_EndDate);
        jDateChooser_EndDate.setBounds(150, 110, 100, 22);

        jButton7.setText("Search");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton7);
        jButton7.setBounds(300, 90, 110, 25);

        jLabel29.setText("Start Date");
        jPanel19.add(jLabel29);
        jLabel29.setBounds(40, 70, 80, 16);

        jLabel30.setText("End Date");
        jPanel19.add(jLabel30);
        jLabel30.setBounds(40, 120, 70, 16);

        jLabel3_totalTransaction.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3_totalTransaction.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel3_totalTransaction.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3_totalTransaction.setText("TotalMoney");
        jPanel19.add(jLabel3_totalTransaction);
        jLabel3_totalTransaction.setBounds(510, 60, 330, 70);

        jLabel_validDate.setForeground(new java.awt.Color(255, 0, 0));
        jPanel19.add(jLabel_validDate);
        jLabel_validDate.setBounds(40, 30, 110, 20);

        jLayeredPane1.setLayer(jPanel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel19, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        jPanel7.add(jPanel11);
        jPanel11.setBounds(340, 180, 860, 560);

        jPanel12.setBackground(new java.awt.Color(102, 0, 153));

        jPanel13.setBackground(new java.awt.Color(0, 0, 0));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ADD EMPLOYEE");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Add Employee.png"))); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(0, 0, 0));
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel14MouseClicked(evt);
            }
        });

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update Record.png"))); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("UPDATE / DELETE");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(0, 0, 0));
        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel15MouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("RECORDS");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/View Record.png"))); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(36, 36, 36)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(0, 0, 0));
        jPanel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel18MouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Transaction Details");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transaction.png"))); // NOI18N

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel14)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel12);
        jPanel12.setBounds(0, 0, 340, 740);

        jPanel9.setBackground(new java.awt.Color(0, 51, 51));

        jLabel_GameZoneNAme.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_GameZoneNAme.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_GameZoneNAme.setText("jLabel26");

        jLabel_OwnerName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_OwnerName.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_OwnerName.setText("jLabel26");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Welcome");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(355, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel_OwnerName, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel_GameZoneNAme, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(286, 286, 286))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel_GameZoneNAme, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_OwnerName, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jPanel7.add(jPanel9);
        jPanel9.setBounds(340, 0, 860, 180);

        jPanel8.add(jPanel7);
        jPanel7.setBounds(0, 0, 1200, 740);

        jTabbedPane1.addTab("Admin", jPanel8);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Refresh ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:Employee tab clicked (Today recharge )
        
        
        
    
        
        DefaultTableModel m = (DefaultTableModel) jTable_transactionDetailsEmp.getModel();
        m.setRowCount(0);

        DefaultTableModel  model = (DefaultTableModel) jTable_transactionDetailsEmp.getModel();
        Object row[] = new Object[5];

        System.out.println("Final "+transdetailscomplete.size());

        for(int i = 0;i < transdetailscomplete.size();i++)
        {
            if( transdetailscomplete.get(i).getEmpName().equals(currentuser.getName()))
            {
                row[0] = transdetailscomplete.get(i).getID();
                row[1] = transdetailscomplete.get(i).getCardNo();
                row[2] = transdetailscomplete.get(i).getEmpName();
                row[3] = transdetailscomplete.get(i).getAmount();
                row[4] = transdetailscomplete.get(i).getDate();

                model.addRow(row);
            }
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseClicked
        // TODO add your handling code here:Transaction Details Panel Table Left Side

        
        jDateChooser_EndDate.setDate(null);
        jDateChooser_StartDate.setDate(null);
        jPanel10.setVisible(false);
        jPanel16.setVisible(false);
        jPanel17.setVisible(false);
        jPanel19.setVisible(true);

        System.out.println(transdetailscomplete.size()+"   size hai bhai  sub thik");
        /*

        SwingWorker work = new SwingWorker<String , Integer>() {
            @Override
            protected  String  doInBackground() throws Exception
            {

                System.out.println("Back ground starts");
                TransactionInterface Dao = TransactionFactory.getInstance();
                //Arguments is table Name
                String TransactionTableName = currentstallname+"_transaction";
                transactiondetails  = Dao.GetTransactionDetails(TransactionTableName);

                System.out.println("Ends");
                System.out.println(transactiondetails.size());
                loginSignal.countDown();
                dialog.dispose();
                return "end";

            }

            @Override
            protected void done()
            {
                System.out.println("Done");
                System.out.println(transactiondetails.size());
                boolean bStatus = false;
                System.out.println("Finished with status " + bStatus);

            }
        };

        work.execute();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);

        final JOptionPane optionPane = new JOptionPane("Loading Data", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

        optionPane.setLocation(1000, 500);

        dialog = new JDialog();
        dialog.setTitle("WAIt ");
        dialog.setModal(true);

        dialog.setContentPane(optionPane);

        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setLocation(x,y);
        dialog.setVisible(true);
        // dialog.setLocation(100, 500);

        System.out.println("Starts");
        try {
            loginSignal.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(MainScreen_StallOwner.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(transactiondetails == null)
        {

            JOptionPane.showMessageDialog(this,
                "Error Connecting Server ");
            return;
        }

        */
        DefaultTableModel m = (DefaultTableModel) jTable_transactionDetails.getModel();
        m.setRowCount(0);

        DefaultTableModel  model = (DefaultTableModel) jTable_transactionDetails.getModel();
        Object row[] = new Object[5];

        System.out.println("Final "+transdetailscomplete.size());

        long totaltransaction = 0l;
        for(int i = 0;i < transdetailscomplete.size();i++)
        {
            row[0] = transdetailscomplete.get(i).getID();
            row[1] = transdetailscomplete.get(i).getCardNo();
            row[2] = transdetailscomplete.get(i).getEmpName();
            row[3] = transdetailscomplete.get(i).getAmount();
            row[4] = transdetailscomplete.get(i).getDate();
            totaltransaction  = transdetailscomplete.get(i).getAmount() + totaltransaction;
            model.addRow(row);
        }

        // transactiondetails
        jLabel3_totalTransaction.setText(""+totaltransaction);
    }//GEN-LAST:event_jPanel18MouseClicked

    private void jPanel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseClicked
        // TODO add your handling code here:
        jPanel10.setVisible(false);
        jPanel16.setVisible(false);
        jPanel19.setVisible(false);
        jPanel17.setVisible(true);

        //to display record into the table
        DefaultTableModel m = (DefaultTableModel) jTable_EmpRecord.getModel();
        m.setRowCount(0);
        DefaultTableModel  model = (DefaultTableModel) jTable_EmpRecord.getModel();
        Object row[] = new Object[8];
        for(int i=0;i < currentStallUsers.size();i++)
        {
            row[0] = currentStallUsers.get(i).getID();
            row[1] = currentStallUsers.get(i).getName();
            row[2] = currentStallUsers.get(i).getAddress();
            row[3] = currentStallUsers.get(i).getContact();
            row[4] = currentStallUsers.get(i).getEmail();
            row[5] = currentStallUsers.get(i).getType();
            row[6] = currentStallUsers.get(i).getGameZoneID();
            row[7] = currentStallUsers.get(i).getPassword();
            model.addRow(row);

        }

    }//GEN-LAST:event_jPanel15MouseClicked

    private void jPanel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseClicked
        // TODO add your handling code here:option2
        jPanel10.setVisible(false);
        jPanel16.setVisible(true);
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);

    }//GEN-LAST:event_jPanel14MouseClicked

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        // TODO add your handling code here:option 1

        jPanel10.setVisible(true);
        jPanel16.setVisible(false);
        jPanel17.setVisible(false);
        jPanel19.setVisible(false);
    }//GEN-LAST:event_jPanel13MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:ADD button to add Employee

        User newuser = new User();
        newuser.setName(jTextField_Name.getText());
        newuser.setType("emp");
        newuser.setGameZoneID(currentuser.getGameZoneID());
        newuser.setAddress(jTextField1_address.getText());
        newuser.setContact(jTextField1_contact.getText());
        newuser.setEmail(jTextField1_email.getText());
        newuser.setPassword(jTextField1_password.getText());

        UserInterface Dao = null;
        try {
            Dao = UserFactory.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(MainScreen_StallOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dao.AddEmp(newuser);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:1000rs
        jTextField2.setText("1000");
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:500rs
        jTextField2.setText("500");
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:200 rs
        jTextField2.setText("200");
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:100 radio button
        jTextField2.setText("100");
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here://Reset button code
        jTextField1.setText("");
        jTextField2.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:Recharge Button
        String cardno = jTextField1.getText();
        String amount = jTextField2.getText();
        String gameselected = jComboBox_GamesList.getSelectedItem().toString();
        
        //validating wheather selected game is propper or not 
        if(gameselected.equals("Select Game"))
        {
                    JOptionPane.showMessageDialog(jPanel1,
                    "Please Select a Game .",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
                return ;
        }
        int amt =0;

        //validation code
        if("".equals(cardno) || "".equals(amount))
        {
            if("".equals(cardno))
            {

                JOptionPane.showMessageDialog(jPanel1,
                    "Please Enter Card No.",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
                return ;

            }else
            {
                JOptionPane.showMessageDialog(jPanel1,
                    "Please Enter Amount.",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
                return ;
            }

        }
        try
        {
            //Converting String amount into Integer value
            amt = Integer.parseInt(amount);

        }catch(NumberFormatException e)
        {

            JOptionPane.showMessageDialog(jPanel1,
                "Please Enter A Valid Amount Amount.",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        //now doing recharge

        Recharge rec = new Recharge();
        long millis=System.currentTimeMillis();
        java.sql.Timestamp date = new Timestamp(millis);
        rec.setAmount(amt);
        rec.setCardNo(cardno);
        rec.setEmpName(currentuser.getName());
        rec.setDate(date);
        rec.setGameName(gameselected);
        
        String TableName = currentstallname+"_transaction";

        TransactionInterface Dao = null ;
        try {
            Dao = TransactionFactory.getInstance();
        } catch (Exception ex) {
            //Error In Transaction
            Logger.getLogger(MainScreen_StallOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("yha a gai");
        try {
            Dao.Recharge(rec, TableName);
        } catch (Exception ex)
        {
            //Error
            System.out.println("Error in recharge");
            JOptionPane.showMessageDialog(jPanel1,
                "Recharge Failed",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(MainScreen_StallOwner.class.getName()).log(Level.SEVERE, null, ex);
            return ;
        }

        JOptionPane.showMessageDialog(jPanel1,
            "Recharge Success",
            "Inane error",
            JOptionPane.ERROR_MESSAGE);
        jTextField1.setText("");
        jTextField2.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:Refresh Button Code
        
        System.out.println("Refresh");
        final ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.gif"));
       
     
        //Background worker for getting the data from the server 
                  SwingWorker work = new SwingWorker<String , Integer>() {
	            @Override
	            protected  String  doInBackground() throws Exception 
	            {
	                //Thing to update when refresh button is clicked 
                        //1 .Transaction Details
                        //2 .CurrentGameZone users
                        //For now only Transaction is refreshed
                        try
                        {
                            
                        TransactionInterface Dao = new TransactionFactory().getInstance();
                        String TransactionTableName = currentstallname+"_transaction";
                        transdetailscomplete = Dao.GetTransactionDetails(TransactionTableName);
                                
                            
                        }
                        catch(Exception ex)
                        {
                            System.out.println(ex);
                        }
             
                    
                        
                        return "end";
	                
	            }//do backgrounf ENDS


	            @Override
	            protected void done()
                    {
                        refresh.dispose();
                      
	            }
	        };
    
             work.execute();
        JOptionPane pane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, icon,new Object[]{}, null);
        refresh = pane.createDialog(this,"Refreshing Date");
        refresh.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      
        //jDialog.show();  this method is depricated there using setVisible method for showing the dialoge box 
        refresh.setVisible(true);
        
        
        
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:Exit button
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:search button to search transaction between perticular dates 
        java.util.Date startdate;
        java.util.Date enddate;
        if(jDateChooser_StartDate.getDate() == null || jDateChooser_EndDate.getDate() == null)
        {
            jLabel_validDate.setText("Enter Valid Dates");
            return;
        }
        
        try{
         startdate  = jDateChooser_StartDate.getDate(); 
         enddate =jDateChooser_EndDate.getDate();
        }
        catch(Exception e)
        {
            
            jLabel_validDate.setText("Enter Valid Dates");
            return;
        }
      
        
        java.sql.Timestamp statTimestamp = convert(startdate);
        java.sql.Timestamp endTimestamp = convert(enddate);

        
        System.out.println(statTimestamp+"  "+endTimestamp);
        
        
        
        DefaultTableModel m = (DefaultTableModel) jTable_transactionDetails.getModel();
        m.setRowCount(0);

        DefaultTableModel  model = (DefaultTableModel) jTable_transactionDetails.getModel();
        Object row[] = new Object[5];

       
            long totaltransaction = 0l;
        for(int i = 0;i < transdetailscomplete.size();i++)
        {
            java.sql.Timestamp trans_date = transdetailscomplete.get(i).getDate();
            
            if(  trans_date.after(statTimestamp) && trans_date.before(endTimestamp)   )
            {
            row[0] = transdetailscomplete.get(i).getID();
            row[1] = transdetailscomplete.get(i).getCardNo();
            row[2] = transdetailscomplete.get(i).getEmpName();
            row[3] = transdetailscomplete.get(i).getAmount();
            row[4] = transdetailscomplete.get(i).getDate();
            totaltransaction  = transdetailscomplete.get(i).getAmount() + totaltransaction;

            model.addRow(row);
            }
        
        }
        
        jLabel3_totalTransaction.setText(""+totaltransaction);
        jLabel_validDate.setText("");
        
    }//GEN-LAST:event_jButton7ActionPerformed
 	public static java.sql.Timestamp convert(java.util.Date date)
	{
		return new java.sql.Timestamp(date.getTime());
	}

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
            java.util.logging.Logger.getLogger(MainScreen_StallOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen_StallOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen_StallOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen_StallOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new MainScreen_StallOwner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox_GamesList;
    private com.toedter.calendar.JDateChooser jDateChooser_EndDate;
    private com.toedter.calendar.JDateChooser jDateChooser_StartDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel3_totalTransaction;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_GameZoneNAme;
    private javax.swing.JLabel jLabel_OwnerName;
    private javax.swing.JLabel jLabel_currentEMpName;
    private javax.swing.JLabel jLabel_currentEMpName1;
    private javax.swing.JLabel jLabel_currentempname2;
    private javax.swing.JLabel jLabel_validDate;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable_EmpRecord;
    private javax.swing.JTable jTable_transactionDetails;
    private javax.swing.JTable jTable_transactionDetailsEmp;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField1_address;
    private javax.swing.JTextField jTextField1_contact;
    private javax.swing.JTextField jTextField1_email;
    private javax.swing.JTextField jTextField1_password;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField_Name;
    // End of variables declaration//GEN-END:variables
}
