
package Gui;
import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;    
import Database.Connect;

import Database.CustomerInterface;

import Database.Customerfactory;
import Database.StallFactory;
import Database.StallInterface;
import Database.TransactionFactory;
import Database.TransactionInterface;
import Database.UserFactory;
import Database.UserInterface;
import gamecenter.Background_GetTransactionDetails;
import gamecenter.Recharge;
import gamecenter.User;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import java.awt.Toolkit;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import gamecenter.Customers;
import java.sql.Connection;

import gamecenter.Stall;
import gamecenter.UpdateCustomerListThread;
import gamecenter.UpdateEmployeeListThread;

import java.util.Timer;
import  gamecenter.Clock;
import gamecenter.Games;
import gamecenter.UpdatePasswordCheck;
import gamecenter.UpdateTransactionListThread;
import java.io.IOException;
import SerialCommunication.*;
import org.apache.commons.collections.map.HashedMap;
//Main screen for every GameZone
public class MainScreen_StallOwner extends javax.swing.JFrame 
{
    ///// Things we Get from the Login Page//////////
    User currentuser;
  
    String Type ;
    LoginScreen Loginform ;
    Stall currentgamezone;
    HashMap<String, Integer> UserNameCheck; // to check no two employee get samne Usename and hence Same combination of user name and Password
    ////////////////////////////////////////////
    
    
    ArrayList<User> currentgamezoneusers;      //Users Details for Current Gamezone
    ArrayList<Recharge> transdetailscomplete;  //Transaction Details for current GameZone
    ArrayList<Customers> customerlist;         //Customer Details for current GameZone
    
    HashMap<String,Boolean> passwordcheck;
    ArrayList<Games> gamelist ;
     
    CountDownLatch TransactionDetailsWaitLock; //To lock GUI till we get Transaction Details

    
    

    ArrayList<Recharge> transactiondetails;
    JDialog dialog;
    
    
    JDialog refresh;
    ArrayList<JTextField> testfields;
    
    
    //flags for checking all the operation are working property 
    Boolean rech_flag = false;
    JDialog rechargeloadingdialoge;
    
    Boolean AddEmp_flag = false;
    
    Boolean UpdateEmp_flag = false;
    
    Boolean RegisterCustomer = false;
    
   
  
    
    
    CountDownLatch RechargeWait;
    Boolean ComportRecharge = false;
    
    //Constructor for normal GameZone employee 
    public MainScreen_StallOwner(LoginScreen l ,Stall currentgamezone,User currentuser) 
    {
        testfields = new ArrayList<>();
        gamelist = new ArrayList<>();
        this.Loginform = l;
        this.currentgamezone = currentgamezone;
        this.currentuser = currentuser;
        initComponents();
       
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
                
     
        setLocation(x, y);
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        buttonGroup1.add(jRadioButton4);
        
        AddUpateEmployee.setVisible(false);
        AddEmployee.setVisible(false);
        transaction.setVisible(false);
        EmployeeTab.setEnabledAt(2, false);
        EmployeeTab.setEnabledAt(1, false);
       
        
        TransactionDetailsWaitLock = new CountDownLatch(1);
        jLabel_OwnerName.setText(this.currentuser.getName());
        jLabel_GameZoneNAme.setText(currentgamezone.getName());
             
        //Background thread for getting transaction details for current GameZone        
        Background_GetTransactionDetails background_GetTransactionDetails = new Background_GetTransactionDetails(TransactionDetailsWaitLock,currentgamezone.getName());
        background_GetTransactionDetails.start();
        try
        {
            TransactionDetailsWaitLock.await();
        
        } catch (InterruptedException ex)
        {
            Logger.getLogger(MainScreen_StallOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
              try
              {
                  
              CustomerInterface Dao =  Customerfactory.getInstance();
              customerlist = Dao.getCust( currentgamezone.getName()+"_customers"); 
                  
               StallInterface Dao1 = StallFactory.getInstance();
               gamelist = Dao1.GetGames(currentgamezone.getName());
              }
              catch(Exception e)
              {
                //Error  in Getting Transaction Details
                 JOptionPane.showMessageDialog(jPanel1,
                  "No internet Connection.",
                  "Inane error",
                  JOptionPane.ERROR_MESSAGE);
                  System.exit(0);
               }
        transdetailscomplete = background_GetTransactionDetails.transactiondetails; 
        if(null == transdetailscomplete || background_GetTransactionDetails.ErrorMessage.equals("TransactionError"))
        {
            
                //Error if we are unable to get trasaction details for current gamezone 
                JOptionPane.showMessageDialog(jPanel1,
                "No internet Connection.",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            
            
        }
        
        //Till Above we get Complete Transaction Details
        
        jLabel_currentEMpName.setText(currentuser.getName());
        jLabel_currentempname2.setText(currentuser.getName());
       
       
        rechargeloadingdialoge = new JDialog();
        
       DefaultTableModel m = (DefaultTableModel) TodaysCollectionTable.getModel();
       m.setRowCount(0);
       for(Games game : gamelist)
       {
        DefaultTableModel  model = (DefaultTableModel) TodaysCollectionTable.getModel();
        Object row[] = new Object[2];
      
            row[0] = game.getGameName();
            row[1] = game.getAmount();
            model.addRow(row);
            
        
       }
           
      
    }
    //Cons when Admin of the GameZone Enter
    public MainScreen_StallOwner(String str,LoginScreen l ,ArrayList<User> currentStallUsers,Stall currentgamezone,User currentuser,HashMap<String,Boolean> passwordcheck)
        {
            Type = str;
             gamelist = new ArrayList<>();
            this.Loginform = l;
            this.passwordcheck =passwordcheck;
            this.currentgamezoneusers = currentStallUsers;
            this.currentgamezone = currentgamezone;
            this.currentuser = currentuser;
            initComponents();
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - getWidth()) / 2);
            int y = (int) ((dimension.getHeight() - getHeight()) / 2);
            int framesizeh = dimension.height*8/10;
            int framesizew = dimension.width*8/10;
            setLocation(x, y);
            buttonGroup1.add(jRadioButton1);
            buttonGroup1.add(jRadioButton2);
            buttonGroup1.add(jRadioButton3);
            buttonGroup1.add(jRadioButton4);
        
 
            
            java.util.Date date=new java.util.Date();
            String dip=date.toString();
	    Label_clock.setText(dip);
                //clock
            Timer time=new Timer();
            int delay=5000;
            int period= 1000;
	    Clock clock = new Clock(Label_clock);
		time.scheduleAtFixedRate(clock, delay,period);
                //
                
                   
              jPanel13.setBackground(new Color(110, 89,222));
      
       
        
 
             AddEmployee.setVisible(true);
             AddUpateEmployee.setVisible(false);
             Emprecords.setVisible(false);
             transaction.setVisible(false);
             Customer.setVisible(false);
             OtherTransactions.setVisible(false);
            
            
            

             testfields = new ArrayList<>();
          
       
        
           //Getting Trnsaction Details
           TransactionDetailsWaitLock = new CountDownLatch(1);
           //background thread for getting data New Refresh Data
           Background_GetTransactionDetails background_GetTransactionDetails = new Background_GetTransactionDetails(TransactionDetailsWaitLock,currentgamezone.getName());
           background_GetTransactionDetails.start();
           try 
           {
           TransactionDetailsWaitLock.await();
           }
           catch (InterruptedException ex)
           {
            Logger.getLogger(MainScreen_StallOwner.class.getName()).log(Level.SEVERE, null, ex);
           }
           transdetailscomplete = background_GetTransactionDetails.transactiondetails; 
           try
           {
              
              CustomerInterface Dao =  Customerfactory.getInstance();
              customerlist = Dao.getCust( currentgamezone.getName()+"_customers"); 
              
              StallInterface Dao1 = StallFactory.getInstance();
              gamelist = Dao1.GetGames(currentgamezone.getName());

           }
           catch(Exception e)
           {
               //Error  in Getting Transaction Details
                JOptionPane.showMessageDialog(jPanel1,
                  "No internet Connection.",
                  "Inane error",
                  JOptionPane.ERROR_MESSAGE);
                  System.exit(0);
           }
           
           
           
        
           if(null == transdetailscomplete || background_GetTransactionDetails.ErrorMessage.equals("TransactionError"))
           {
                
              //Error  in Getting Transaction Details
                JOptionPane.showMessageDialog(jPanel1,
                  "No internet Connection.",
                  "Inane error",
                  JOptionPane.ERROR_MESSAGE);
                  System.exit(0);
            
            
           }
        
          jLabel_OwnerName.setText(this.currentuser.getName());
          jLabel_GameZoneNAme.setText(currentgamezone.getName());
       
          jLabel_currentEMpName.setText(currentuser.getName());
          jLabel_currentempname2.setText(currentuser.getName());
       
       
       
        rechargeloadingdialoge = new JDialog();
        jLabel_GameZoneNAme.setText(currentgamezone.getName());
        jLabel_OwnerName.setText(currentuser.getName());
        jLabel_Label.setText("Employee Records");
        
        
       DefaultTableModel m = (DefaultTableModel) TodaysCollectionTable.getModel();
       m.setRowCount(0);
       for(Games game : gamelist)
       {
        DefaultTableModel  model = (DefaultTableModel) TodaysCollectionTable.getModel();
        Object row[] = new Object[2];
      
            row[0] = game.getGameName();
            row[1] = game.getAmount();
            model.addRow(row);
            
        
       }
           
       
  
        currentnoofemployeelabel.setText(currentStallUsers.size()+"");
        Noofcustomerlabel.setText(customerlist.size()+"");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        EmployeeTab = new javax.swing.JTabbedPane();
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
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel_currentEMpName = new javax.swing.JLabel();
        ResetValuestozerobutton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        Textfiled_ExistingAmount = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jLabel_currentempname2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TodaysCollectionTable = new javax.swing.JTable();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        jDateChooser_todayCollection_to = new com.toedter.calendar.JDateChooser();
        jDateChooser_todayCollection_from = new com.toedter.calendar.JDateChooser();
        jButton10 = new javax.swing.JButton();
        todaycollectionamount = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        AddEmployee = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        AddNewEmployee_Button = new javax.swing.JButton();
        AddEmpName_textfield = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        AddEmpAddress_textfield = new javax.swing.JTextField();
        AddEmpPassword_textfield = new javax.swing.JTextField();
        AddEmpEmail_textfield = new javax.swing.JTextField();
        AddEmpContact_textfield = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        AddEmpUserName_textfield = new javax.swing.JTextField();
        UserNameUniqueLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        AddUpateEmployee = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        EmplyeeIdInput = new javax.swing.JTextField();
        UpdateEmplloyeeSearch_Button = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_updatedelete = new javax.swing.JTable();
        Emprecords = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_EmpRecord = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        Employeeidinput = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        transaction = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_transactionDetails = new javax.swing.JTable();
        jDateChooser_StartDate = new com.toedter.calendar.JDateChooser();
        jDateChooser_EndDate = new com.toedter.calendar.JDateChooser();
        jButton7 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel3_totalTransaction = new javax.swing.JLabel();
        jLabel_validDate = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Customer = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextField_regname = new javax.swing.JTextField();
        jTextField_regcontact = new javax.swing.JTextField();
        jTextField_regemail = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable_customers = new javax.swing.JTable();
        RegisterNewcustomer_button = new javax.swing.JButton();
        ResetButton_Customer = new javax.swing.JButton();
        OtherTransactions = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        OthertrasactionsTable = new javax.swing.JTable();
        jLabel36 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel_validDate1 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jDateChooser_EndDate2 = new com.toedter.calendar.JDateChooser();
        jDateChooser_StartDate2 = new com.toedter.calendar.JDateChooser();
        jButton9 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        currentnoofemployeelabel = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel_GameZoneNAme = new javax.swing.JLabel();
        jLabel_OwnerName = new javax.swing.JLabel();
        jLabel_OwnerName1 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        Noofcustomerlabel = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel_Label = new javax.swing.JLabel();
        Label_clock = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Screen ");
        setResizable(false);

        jPanel1.setOpaque(false);

        EmployeeTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EmployeeTabMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));
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
        jLabel2.setBounds(360, 160, 100, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Card No");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(360, 90, 80, 20);

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel4.add(jTextField1);
        jTextField1.setBounds(580, 90, 260, 50);

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel4.add(jTextField2);
        jTextField2.setBounds(580, 160, 260, 60);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refund.png"))); // NOI18N
        jButton1.setText("Refund");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);
        jButton1.setBounds(490, 340, 170, 70);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Recharge.png"))); // NOI18N
        jButton2.setText("Recharge ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);
        jButton2.setBounds(60, 340, 170, 70);

        jRadioButton1.setBackground(new java.awt.Color(153, 153, 255));
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("100");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton1);
        jRadioButton1.setBounds(350, 260, 107, 37);

        jRadioButton2.setBackground(new java.awt.Color(153, 153, 255));
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("200");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton2);
        jRadioButton2.setBounds(500, 260, 110, 40);

        jRadioButton3.setBackground(new java.awt.Color(153, 153, 255));
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setText("500");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton3);
        jRadioButton3.setBounds(670, 260, 73, 37);

        jRadioButton4.setBackground(new java.awt.Color(153, 153, 255));
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setText("1000");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton4);
        jRadioButton4.setBounds(820, 260, 89, 37);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Welcome");
        jPanel4.add(jLabel27);
        jLabel27.setBounds(890, 0, 110, 30);

        jLabel_currentEMpName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_currentEMpName.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_currentEMpName.setText("jLabel28");
        jPanel4.add(jLabel_currentEMpName);
        jLabel_currentEMpName.setBounds(1010, 2, 170, 30);

        ResetValuestozerobutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Clear.png"))); // NOI18N
        ResetValuestozerobutton.setText("Clear");
        ResetValuestozerobutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetValuestozerobuttonActionPerformed(evt);
            }
        });
        jPanel4.add(ResetValuestozerobutton);
        ResetValuestozerobutton.setBounds(270, 340, 180, 70);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Customer.png"))); // NOI18N
        jButton5.setText("Register Customer");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5);
        jButton5.setBounds(700, 340, 230, 70);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Cards.png"))); // NOI18N
        jButton6.setText("Other trasaction");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6);
        jButton6.setBounds(960, 340, 190, 70);

        jPanel2.add(jPanel4);
        jPanel4.setBounds(2, 7, 1200, 440);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Existing Amount");
        jPanel5.add(jLabel4);
        jLabel4.setBounds(0, 0, 180, 30);
        jPanel5.add(jSeparator2);
        jSeparator2.setBounds(0, 30, 660, 2);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Existing Amount ");
        jPanel5.add(jLabel5);
        jLabel5.setBounds(110, 80, 190, 40);

        Textfiled_ExistingAmount.setEditable(false);
        Textfiled_ExistingAmount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel5.add(Textfiled_ExistingAmount);
        Textfiled_ExistingAmount.setBounds(290, 70, 290, 60);

        jPanel2.add(jPanel5);
        jPanel5.setBounds(280, 470, 660, 190);

        EmployeeTab.addTab("Recharge ", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(null);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setForeground(new java.awt.Color(204, 255, 255));
        jPanel6.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Today Collection");
        jPanel6.add(jLabel6);
        jLabel6.setBounds(30, 10, 240, 20);
        jPanel6.add(jSeparator3);
        jSeparator3.setBounds(-380, 40, 1570, 2);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Welcome");
        jPanel6.add(jLabel28);
        jLabel28.setBounds(860, 0, 110, 30);

        jLabel_currentempname2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_currentempname2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_currentempname2.setText("jLabel29");
        jPanel6.add(jLabel_currentempname2);
        jLabel_currentempname2.setBounds(1020, 10, 150, 17);

        jPanel3.add(jPanel6);
        jPanel6.setBounds(0, 10, 1180, 50);

        TodaysCollectionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "GameName", "Amount"
            }
        ));
        TodaysCollectionTable.setEnabled(false);
        jScrollPane3.setViewportView(TodaysCollectionTable);

        jPanel3.add(jScrollPane3);
        jScrollPane3.setBounds(100, 120, 990, 402);
        jPanel3.add(jSeparator5);
        jSeparator5.setBounds(100, 542, 990, 10);

        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("To");
        jPanel3.add(jLabel35);
        jLabel35.setBounds(600, 630, 80, 40);
        jPanel3.add(jDateChooser_todayCollection_to);
        jDateChooser_todayCollection_to.setBounds(630, 620, 150, 40);
        jPanel3.add(jDateChooser_todayCollection_from);
        jDateChooser_todayCollection_from.setBounds(420, 620, 150, 40);

        jButton10.setText("Get Record");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton10);
        jButton10.setBounds(810, 620, 120, 40);

        todaycollectionamount.setForeground(new java.awt.Color(255, 255, 255));
        todaycollectionamount.setText("Total collection is --");
        jPanel3.add(todaycollectionamount);
        todaycollectionamount.setBounds(100, 80, 260, 40);

        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("From");
        jPanel3.add(jLabel43);
        jLabel43.setBounds(370, 630, 80, 40);

        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Get Collection for a interval");
        jPanel3.add(jLabel44);
        jLabel44.setBounds(100, 620, 260, 40);

        EmployeeTab.addTab("Today Collection", jPanel3);

        jPanel8.setLayout(null);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(null);

        AddEmployee.setBackground(new java.awt.Color(255, 255, 255));
        AddEmployee.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Name");
        AddEmployee.add(jLabel12);
        jLabel12.setBounds(90, 30, 53, 22);

        AddNewEmployee_Button.setText("Submit");
        AddNewEmployee_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNewEmployee_ButtonActionPerformed(evt);
            }
        });
        AddEmployee.add(AddNewEmployee_Button);
        AddNewEmployee_Button.setBounds(380, 490, 120, 40);

        AddEmpName_textfield.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AddEmployee.add(AddEmpName_textfield);
        AddEmpName_textfield.setBounds(90, 70, 210, 40);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setText("Password");
        AddEmployee.add(jLabel22);
        jLabel22.setBounds(480, 160, 87, 22);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setText("Contact");
        AddEmployee.add(jLabel23);
        jLabel23.setBounds(480, 30, 70, 22);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setText("Email");
        AddEmployee.add(jLabel24);
        jLabel24.setBounds(90, 150, 49, 22);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setText("UserName");
        AddEmployee.add(jLabel25);
        jLabel25.setBounds(90, 270, 110, 22);

        AddEmpAddress_textfield.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AddEmployee.add(AddEmpAddress_textfield);
        AddEmpAddress_textfield.setBounds(200, 380, 510, 70);

        AddEmpPassword_textfield.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AddEmployee.add(AddEmpPassword_textfield);
        AddEmpPassword_textfield.setBounds(480, 200, 220, 50);

        AddEmpEmail_textfield.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AddEmployee.add(AddEmpEmail_textfield);
        AddEmpEmail_textfield.setBounds(90, 190, 220, 40);

        AddEmpContact_textfield.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AddEmployee.add(AddEmpContact_textfield);
        AddEmpContact_textfield.setBounds(480, 70, 210, 40);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setText("Address");
        AddEmployee.add(jLabel32);
        jLabel32.setBounds(90, 400, 71, 22);

        AddEmpUserName_textfield.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AddEmployee.add(AddEmpUserName_textfield);
        AddEmpUserName_textfield.setBounds(90, 310, 220, 40);

        UserNameUniqueLabel.setForeground(new java.awt.Color(255, 0, 51));
        AddEmployee.add(UserNameUniqueLabel);
        UserNameUniqueLabel.setBounds(350, 320, 120, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");
        AddEmployee.add(jLabel7);
        jLabel7.setBounds(190, 280, 34, 14);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("*");
        AddEmployee.add(jLabel8);
        jLabel8.setBounds(570, 170, 34, 14);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("*");
        AddEmployee.add(jLabel10);
        jLabel10.setBounds(150, 40, 30, 14);

        AddUpateEmployee.setBackground(new java.awt.Color(255, 255, 255));
        AddUpateEmployee.setLayout(null);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Enter Employee Name ");
        AddUpateEmployee.add(jLabel13);
        jLabel13.setBounds(100, 10, 230, 30);
        AddUpateEmployee.add(EmplyeeIdInput);
        EmplyeeIdInput.setBounds(100, 50, 210, 50);

        UpdateEmplloyeeSearch_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        UpdateEmplloyeeSearch_Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        UpdateEmplloyeeSearch_Button.setText("Search");
        UpdateEmplloyeeSearch_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateEmplloyeeSearch_ButtonActionPerformed(evt);
            }
        });
        AddUpateEmployee.add(UpdateEmplloyeeSearch_Button);
        UpdateEmplloyeeSearch_Button.setBounds(410, 40, 160, 60);

        jTable_updatedelete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable_updatedelete.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Address", "Contact", "Email", "Type", "GameZoneId", "Passoword", "UserName"
            }
        ));
        jTable_updatedelete.setSelectionBackground(new java.awt.Color(112, 72, 215));
        jTable_updatedelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_updatedeleteMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable_updatedelete);

        AddUpateEmployee.add(jScrollPane4);
        jScrollPane4.setBounds(0, 140, 860, 420);

        Emprecords.setBackground(new java.awt.Color(255, 255, 255));
        Emprecords.setLayout(null);

        jTable_EmpRecord.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable_EmpRecord.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Address", "Contact", "Email", "Type", "GameZoneID", "Password", "UserName"
            }
        ));
        jTable_EmpRecord.setRowHeight(22);
        jTable_EmpRecord.setRowMargin(3);
        jTable_EmpRecord.setSelectionBackground(new java.awt.Color(112, 72, 215));
        jTable_EmpRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_EmpRecordMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_EmpRecord);

        Emprecords.add(jScrollPane1);
        jScrollPane1.setBounds(0, 205, 860, 350);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Employee Name");
        Emprecords.add(jLabel11);
        jLabel11.setBounds(70, 30, 220, 60);
        Emprecords.add(Employeeidinput);
        Employeeidinput.setBounds(70, 90, 190, 40);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        jButton3.setText("Search ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        Emprecords.add(jButton3);
        jButton3.setBounds(280, 80, 150, 60);

        transaction.setBackground(new java.awt.Color(255, 255, 255));
        transaction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transactionMouseClicked(evt);
            }
        });
        transaction.setLayout(null);

        jTable_transactionDetails.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable_transactionDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CardNo", "EmpName", "Amount", "Date"
            }
        ));
        jTable_transactionDetails.setSelectionBackground(new java.awt.Color(112, 72, 215));
        jScrollPane2.setViewportView(jTable_transactionDetails);

        transaction.add(jScrollPane2);
        jScrollPane2.setBounds(0, 160, 860, 402);
        transaction.add(jDateChooser_StartDate);
        jDateChooser_StartDate.setBounds(121, 50, 150, 40);
        transaction.add(jDateChooser_EndDate);
        jDateChooser_EndDate.setBounds(121, 110, 150, 40);

        jButton7.setText("Search");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        transaction.add(jButton7);
        jButton7.setBounds(300, 73, 150, 40);

        jLabel29.setText("Start Date");
        transaction.add(jLabel29);
        jLabel29.setBounds(40, 70, 80, 16);

        jLabel30.setText("End Date");
        transaction.add(jLabel30);
        jLabel30.setBounds(40, 120, 70, 16);

        jLabel3_totalTransaction.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3_totalTransaction.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel3_totalTransaction.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3_totalTransaction.setText("TotalMoney");
        transaction.add(jLabel3_totalTransaction);
        jLabel3_totalTransaction.setBounds(510, 60, 330, 70);

        jLabel_validDate.setForeground(new java.awt.Color(255, 0, 0));
        transaction.add(jLabel_validDate);
        jLabel_validDate.setBounds(40, 30, 250, 20);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Total Collection");
        transaction.add(jLabel15);
        jLabel15.setBounds(520, 30, 150, 20);

        Customer.setBackground(new java.awt.Color(255, 255, 255));
        Customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CustomerMouseClicked(evt);
            }
        });
        Customer.setLayout(null);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setText("Email");
        Customer.add(jLabel33);
        jLabel33.setBounds(150, 200, 49, 22);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setText("Contact");
        Customer.add(jLabel34);
        jLabel34.setBounds(150, 130, 120, 50);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel37.setText("Name");
        Customer.add(jLabel37);
        jLabel37.setBounds(150, 80, 53, 22);

        jTextField_regname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Customer.add(jTextField_regname);
        jTextField_regname.setBounds(250, 78, 190, 30);

        jTextField_regcontact.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Customer.add(jTextField_regcontact);
        jTextField_regcontact.setBounds(250, 140, 190, 28);

        jTextField_regemail.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Customer.add(jTextField_regemail);
        jTextField_regemail.setBounds(250, 190, 190, 30);

        jTable_customers.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable_customers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Contact", "Email"
            }
        ));
        jTable_customers.setSelectionBackground(new java.awt.Color(112, 72, 215));
        jScrollPane6.setViewportView(jTable_customers);

        Customer.add(jScrollPane6);
        jScrollPane6.setBounds(0, 290, 860, 270);

        RegisterNewcustomer_button.setText("Register ");
        RegisterNewcustomer_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterNewcustomer_buttonActionPerformed(evt);
            }
        });
        Customer.add(RegisterNewcustomer_button);
        RegisterNewcustomer_button.setBounds(540, 120, 90, 25);

        ResetButton_Customer.setText("Reset");
        ResetButton_Customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetButton_CustomerActionPerformed(evt);
            }
        });
        Customer.add(ResetButton_Customer);
        ResetButton_Customer.setBounds(540, 170, 90, 25);

        OtherTransactions.setBackground(new java.awt.Color(255, 255, 255));
        OtherTransactions.setLayout(null);

        OthertrasactionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CustomerName", "CardNo", "ExpireDate", "Method", "Amount", "Date"
            }
        ));
        OthertrasactionsTable.setEnabled(false);
        jScrollPane7.setViewportView(OthertrasactionsTable);

        OtherTransactions.add(jScrollPane7);
        jScrollPane7.setBounds(0, 290, 860, 308);

        jLabel36.setText("Start Date");
        OtherTransactions.add(jLabel36);
        jLabel36.setBounds(40, 70, 80, 16);

        jLabel40.setText("End Date");
        OtherTransactions.add(jLabel40);
        jLabel40.setBounds(40, 120, 70, 16);

        jLabel_validDate1.setForeground(new java.awt.Color(255, 0, 0));
        OtherTransactions.add(jLabel_validDate1);
        jLabel_validDate1.setBounds(40, 30, 230, 20);

        jButton8.setText("Search");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        OtherTransactions.add(jButton8);
        jButton8.setBounds(300, 50, 150, 40);
        OtherTransactions.add(jDateChooser_EndDate2);
        jDateChooser_EndDate2.setBounds(120, 110, 150, 40);
        OtherTransactions.add(jDateChooser_StartDate2);
        jDateChooser_StartDate2.setBounds(121, 50, 150, 40);

        jButton9.setText("Get This Month");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        OtherTransactions.add(jButton9);
        jButton9.setBounds(300, 110, 150, 40);

        jLayeredPane1.setLayer(AddEmployee, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(AddUpateEmployee, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Emprecords, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(transaction, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Customer, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(OtherTransactions, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AddEmployee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(AddUpateEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Emprecords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(transaction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Customer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(OtherTransactions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AddEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(AddUpateEmployee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Emprecords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(transaction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Customer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(OtherTransactions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jPanel12.setBackground(new java.awt.Color(54, 33, 89));

        jPanel13.setBackground(new java.awt.Color(54, 33, 89));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ADD EMPLOYEE");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/newAddemp.png"))); // NOI18N

        currentnoofemployeelabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        currentnoofemployeelabel.setForeground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(currentnoofemployeelabel)
                .addGap(18, 18, 18))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentnoofemployeelabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(54, 33, 89));
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel14MouseClicked(evt);
            }
        });

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/newupdatedelleteemp.png"))); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("UPDATE / DELETE Employee");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel18)
                .addGap(27, 27, 27)
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(54, 33, 89));
        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel15MouseClicked(evt);
            }
        });

        jLabel20.setBackground(new java.awt.Color(54, 33, 89));
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Employees RECORDS");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/newemprecords.png"))); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(54, 33, 89));
        jPanel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel18MouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Transaction Details");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/newtransaction (2).png"))); // NOI18N

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel_GameZoneNAme.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_GameZoneNAme.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_GameZoneNAme.setText("GameZone Name");

        jLabel_OwnerName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_OwnerName.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_OwnerName.setText("User Name");

        jLabel_OwnerName1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_OwnerName1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_OwnerName1.setText("Exit");
        jLabel_OwnerName1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_OwnerName1MouseClicked(evt);
            }
        });

        jPanel22.setBackground(new java.awt.Color(54, 33, 89));
        jPanel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel22MouseClicked(evt);
            }
        });

        jLabel26.setBackground(new java.awt.Color(54, 33, 89));
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Register new Customer");

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/newCustomer.png"))); // NOI18N

        Noofcustomerlabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Noofcustomerlabel.setForeground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addComponent(Noofcustomerlabel)
                        .addContainerGap())))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Noofcustomerlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel10.setBackground(new java.awt.Color(54, 33, 89));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        jLabel39.setBackground(new java.awt.Color(54, 33, 89));
        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Other Trasactions");

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/newtransaction (2).png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel41)
                .addGap(36, 36, 36)
                .addComponent(jLabel39)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel41)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_OwnerName1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_OwnerName, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_GameZoneNAme, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel_GameZoneNAme)
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_OwnerName, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_OwnerName1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jPanel7.add(jPanel12);
        jPanel12.setBounds(0, 0, 340, 740);

        jPanel9.setBackground(new java.awt.Color(110, 89, 222));
        jPanel9.setLayout(null);

        jLabel_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel_Label.setText("Heading");
        jPanel9.add(jLabel_Label);
        jLabel_Label.setBounds(350, 60, 280, 60);

        Label_clock.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Label_clock.setForeground(new java.awt.Color(255, 255, 255));
        Label_clock.setText("Current Time");
        jPanel9.add(Label_clock);
        Label_clock.setBounds(560, 10, 290, 30);

        jPanel7.add(jPanel9);
        jPanel9.setBounds(340, 0, 860, 180);

        jPanel8.add(jPanel7);
        jPanel7.setBounds(0, 0, 1200, 740);

        EmployeeTab.addTab("Admin", jPanel8);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(EmployeeTab, javax.swing.GroupLayout.PREFERRED_SIZE, 1204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(EmployeeTab, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMenuItem3.setText("Send Email");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("ChangePassword");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Refresh ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem4.setText("Logoff");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

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
                        //3 .Customers Details
                        //For now only Transaction is refreshed
                        try
                        {
                            
                        TransactionInterface Dao = new TransactionFactory().getInstance();
                        String TransactionTableName =  currentgamezone.getName()+"_transaction";
                        transdetailscomplete = Dao.GetTransactionDetails(TransactionTableName);
                                
                        CustomerInterface Dao2 = new Customerfactory().getInstance();
                    
                        customerlist  = Dao2.getCust(currentgamezone.getName()+"_customers");
                         
                        UserInterface Dao3 =  new UserFactory().getInstance();
                        currentgamezoneusers = Dao3.getAllUsers(currentgamezone.getName());
                         
                        }
                        catch(Exception ex)
                        {
                               JOptionPane.showMessageDialog(jPanel1,
                               "Error In Refreshing Please Check Your Internet Connection.",
                               "Inane error",
                               JOptionPane.ERROR_MESSAGE);
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
      
         
        refresh.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:Exit button
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    //Method for sending email 
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       
            SwingWorker work = new SwingWorker<String , Integer>() 
                 {
	            @Override
	            protected  String  doInBackground() throws Exception 
	            {
	            
                    try 
                    {
        String from = "starktechnologies2018@gmail.com";
        String password = "sample@123";
      
        String to = currentgamezone.getEmail();
       
        String sub = "Transaction Details for "+java.time.LocalDate.now();
        

        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
           //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           @Override
           protected PasswordAuthentication getPasswordAuthentication()
           {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          //compose message    
          try 
          {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
          
           message.setSubject(sub);
           String mss = "Transaction Details for "+java.time.LocalDate.now()+System.lineSeparator() ;
           for(Games g : gamelist)
           {
               mss += g.getGameName() +" ---  Rs "+g.getAmount()+ " \n";
           }
           mss = mss+System.lineSeparator()+" Regards starktechnologies";
           message.setText(mss);    
   
           Transport.send(message);    
           System.out.println("message sent successfully");    
          } 
          catch (MessagingException e)
          {
              throw new RuntimeException(e);
          }    
          
          
          
                         } catch (Exception ex)
                         {
                       
                             RegisterCustomer = false;
                         }
                 
                        return "end";
	                
	            }
	            @Override
	            protected void done()
                    {
                        
                     rechargeloadingdialoge.setVisible(false);
	            }
	        };
        
                 
        final ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/Images/recharge.gif"));
        work.execute();
        JOptionPane pane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, icon,new Object[]{}, null);
        rechargeloadingdialoge = pane.createDialog(this,"Please wait Sending E-Mail ");
        rechargeloadingdialoge.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        rechargeloadingdialoge.setVisible(true);   
      
          
          
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:Code for Logoff
        setVisible(false);
        dispose();
        
        FactoryClass.commObj.close();
        FactoryClass.commObj2.close();
        
        Loginform.setVisible(true);
        
        
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void EmployeeTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EmployeeTabMouseClicked
        // TODO add your handling code here:employee tab click
//        emp_registernewcustomer.setVisible(false);
//        Emp_owndetailsPanel.setBackground(new Color(110, 89,222));
//        emptab_name.setText(currentuser.getName());
//        emptab_address.setText(currentuser.getAddress());
//        emptab_contact.setText(currentuser.getContact());
//        emptab_emailid.setText(currentuser.getEmail());
//        emptab_password.setText(currentuser.getPassword());
//        emptab_username.setText(currentuser.getUserName());
    }//GEN-LAST:event_EmployeeTabMouseClicked

    private void jPanel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel22MouseClicked
        // TODO add your handling code here:
        jPanel18.setBackground(new Color(54, 33,89));
        jPanel13.setBackground(new Color(54, 33,89));
        jPanel14.setBackground(new Color(54, 33,89));
        jPanel15.setBackground(new Color(54, 33,89));
        jPanel22.setBackground(new Color(110, 89,222));
        jPanel10.setBackground(new Color(54, 33,89));
        
        
        jLabel_Label.setText("Register New Customer");

        AddEmployee.setVisible(false);
        AddUpateEmployee.setVisible(false);
        Emprecords.setVisible(false);
        Customer.setVisible(true);
        transaction.setVisible(false);
        OtherTransactions.setVisible(false);

        DefaultTableModel m = (DefaultTableModel) jTable_customers.getModel();
        m.setRowCount(0);

        DefaultTableModel  model = (DefaultTableModel) jTable_customers.getModel();
        Object row[] = new Object[4];
        for(int i = 0;i < customerlist.size();i++)
        {
            row[0] = customerlist.get(i).getId();
            row[1] = customerlist.get(i).getName();
            row[2] = customerlist.get(i).getContact();
            row[3] = customerlist.get(i).getEmailId();

            model.addRow(row);

        }

    }//GEN-LAST:event_jPanel22MouseClicked

    private void jLabel_OwnerName1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_OwnerName1MouseClicked
        // TODO add your handling code here:exit button
        Connection conn = Connect.getconnection();

        try{conn.close();}
        catch(Exception e)
        {
            System.out.println("e");
        }
        System.exit(0);
    }//GEN-LAST:event_jLabel_OwnerName1MouseClicked

    private void jPanel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseClicked
        // TODO add your handling code here: Transaction Details Panel Table Left Side
        jPanel18.setBackground(new Color(110, 89,222));
        jPanel13.setBackground(new Color(54, 33,89));
        jPanel14.setBackground(new Color(54, 33,89));
        jPanel15.setBackground(new Color(54, 33,89));
        jPanel22.setBackground(new Color(54, 33,89));
          jPanel10.setBackground(new Color(54, 33,89));
        jLabel_Label.setText("Transaction Details");

        jDateChooser_EndDate.setDate(null);
        jDateChooser_StartDate.setDate(null);
        AddEmployee.setVisible(false);
        AddUpateEmployee.setVisible(false);
        Emprecords.setVisible(false);
        Customer.setVisible(false);
        transaction.setVisible(true);
        OtherTransactions.setVisible(false);
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
        // TODO add your handling code here: Employee records
        //13 14 15 18
        //362159
        jPanel18.setBackground(new Color(54, 33,89));
        jPanel13.setBackground(new Color(54, 33,89));
        jPanel14.setBackground(new Color(54, 33,89));
        jPanel15.setBackground(new Color(110, 89,222));
        jPanel22.setBackground(new Color(54, 33,89));
        jPanel10.setBackground(new Color(54, 33,89));
        jLabel_Label.setText("Employees Records");
        AddEmployee.setVisible(false);
        AddUpateEmployee.setVisible(false);
        transaction.setVisible(false);
        Emprecords.setVisible(true);
        Customer.setVisible(false);
        OtherTransactions.setVisible(false);

        //to display record into the table
        DefaultTableModel m = (DefaultTableModel) jTable_EmpRecord.getModel();
        m.setRowCount(0);
        DefaultTableModel  model = (DefaultTableModel) jTable_EmpRecord.getModel();
        Object row[] = new Object[9];
        for(int i=0;i < currentgamezoneusers.size();i++)
        {
            row[0] = currentgamezoneusers.get(i).getID();
            row[1] = currentgamezoneusers.get(i).getName();
            row[2] = currentgamezoneusers.get(i).getAddress();
            row[3] = currentgamezoneusers.get(i).getContact();
            row[4] = currentgamezoneusers.get(i).getEmail();
            row[5] = currentgamezoneusers.get(i).getType();
            row[6] = currentgamezoneusers.get(i).getGameZoneID();
            row[7] = currentgamezoneusers.get(i).getPassword();
            row[8] = currentgamezoneusers.get(i).getUserName();
            model.addRow(row);

        }
    }//GEN-LAST:event_jPanel15MouseClicked

    private void jPanel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseClicked
        // TODO add your handling code here: update and delete employee button click event  (panel)
        jPanel18.setBackground(new Color(54, 33,89));
        jPanel13.setBackground(new Color(54, 33,89));
        jPanel14.setBackground(new Color(110, 89,222));
        jPanel15.setBackground(new Color(54, 33,89));
        jPanel22.setBackground(new Color(54, 33,89));
       jPanel10.setBackground(new Color(54, 33,89));
        jLabel_Label.setText("Update/Delete Employee");

        AddEmployee.setVisible(false);
        AddUpateEmployee.setVisible(true);
        Emprecords.setVisible(false);
        transaction.setVisible(false);
        Customer.setVisible(false);
        OtherTransactions.setVisible(false);

        DefaultTableModel m = (DefaultTableModel) jTable_updatedelete.getModel();
        m.setRowCount(0);
        DefaultTableModel  model = (DefaultTableModel) jTable_updatedelete.getModel();
        Object row[] = new Object[9];
        for(int i=0;i < currentgamezoneusers.size();i++)
        {
            row[0] = currentgamezoneusers.get(i).getID();
            row[1] = currentgamezoneusers.get(i).getName();
            row[2] = currentgamezoneusers.get(i).getAddress();
            row[3] = currentgamezoneusers.get(i).getContact();
            row[4] = currentgamezoneusers.get(i).getEmail();
            row[5] = currentgamezoneusers.get(i).getType();
            row[6] = currentgamezoneusers.get(i).getGameZoneID();
            row[7] = currentgamezoneusers.get(i).getPassword();
            row[8] = currentgamezoneusers.get(i).getUserName();
            model.addRow(row);

        }
    }//GEN-LAST:event_jPanel14MouseClicked

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        // TODO add your handling code here: Add new employee button click event (panel)
        jPanel18.setBackground(new Color(54, 33,89));
        jPanel13.setBackground(new Color(110, 89,222));
        jPanel14.setBackground(new Color(54, 33,89));
        jPanel15.setBackground(new Color(54, 33,89));
        jLabel_Label.setText("Add New Employee");
        jPanel22.setBackground(new Color(54, 33,89));
         jPanel10.setBackground(new Color(54, 33,89));

        AddEmployee.setVisible(true);
        AddUpateEmployee.setVisible(false);
        Emprecords.setVisible(false);
        transaction.setVisible(false);
        Customer.setVisible(false);
        OtherTransactions.setVisible(false);
    }//GEN-LAST:event_jPanel13MouseClicked

    private void CustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_CustomerMouseClicked

    private void ResetButton_CustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetButton_CustomerActionPerformed
        // TODO add your handling code here: reset button register customer
        jTextField_regcontact.setText("");
        jTextField_regemail.setText("");
        jTextField_regname.setText("");
    }//GEN-LAST:event_ResetButton_CustomerActionPerformed

    private void RegisterNewcustomer_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterNewcustomer_buttonActionPerformed
        // TODO add your handling code here:Register Customer button
        String contact =jTextField_regcontact.getText();
        String email   =jTextField_regemail.getText();
        String name = jTextField_regname.getText();

        if(contact.length() == 0 || name.length() == 0)
        {
            JOptionPane.showMessageDialog(jPanel1,
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
            JOptionPane.showMessageDialog(jPanel1,
                "Registeration Failed",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return ;
        }else
        {

            UpdateCustomerListThread updatecustomerlist = new UpdateCustomerListThread(customerlist,currentgamezone.getName());
            updatecustomerlist.start();

            JOptionPane.showMessageDialog(jPanel1,
                "Customer Registered Success",
                "Success",
                JOptionPane.PLAIN_MESSAGE);
            //now updating the customer list
        }

        RegisterCustomer = false;
        jTextField_regcontact.setText("");
        jTextField_regemail.setText("");
        jTextField_regname.setText("");

        //code for filled table with new customer list
        DefaultTableModel m = (DefaultTableModel) jTable_customers.getModel();
        m.setRowCount(0);

        DefaultTableModel  model = (DefaultTableModel) jTable_customers.getModel();
        Object row[] = new Object[4];
        for(int i = 0;i < customerlist.size();i++)
        {
            row[0] = customerlist.get(i).getId();
            row[1] = customerlist.get(i).getName();
            row[2] = customerlist.get(i).getContact();
            row[3] = customerlist.get(i).getEmailId();

            model.addRow(row);

        }
        Noofcustomerlabel.setText(customerlist.size()+"");

    }//GEN-LAST:event_RegisterNewcustomer_buttonActionPerformed

    private void transactionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_transactionMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:search button to search transaction between perticular dates
        java.util.Date startdate;
        java.util.Date enddate;
        if(jDateChooser_StartDate.getDate() == null || jDateChooser_EndDate.getDate() == null)
        {
            jLabel_validDate.setText("Enter Valid Dates");
            return;
        }
          if(jDateChooser_StartDate.getDate().after(jDateChooser_EndDate.getDate()))
        {
          jLabel_validDate1.setText("start Date Can't be greater then end date");
            return;  
        }
        try
        {
            startdate  = jDateChooser_StartDate.getDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(startdate);

            // Set time fields to zero
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            // Put it back in the Date object
            startdate = cal.getTime();
            //startdate  = jDateChooser_StartDate.getDate();

            enddate =jDateChooser_EndDate.getDate();

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(enddate);

            // Set time fields to zero
            cal2.set(Calendar.HOUR_OF_DAY, 24);

            // Put it back in the Date object
            enddate = cal2.getTime();
        }
        catch(Exception e)
        {

            jLabel_validDate.setText("Enter Valid Dates");
            return;
        }

        //Getting data from the amzone database

        ArrayList<Recharge> list  = null;
        try
        {
            TransactionInterface Dao = TransactionFactory.getInstance();
            list = Dao.GetTransactionDetailsPerticularDates(currentgamezone.getName()+"_transaction", new java.sql.Date(startdate.getTime()), new java.sql.Date(enddate.getTime()));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(jPanel1,
                "Unable to Connect To Internet ,Please check your internet Connection.",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return ;

        }

        DefaultTableModel m = (DefaultTableModel) jTable_transactionDetails.getModel();
        m.setRowCount(0);

        DefaultTableModel  model = (DefaultTableModel) jTable_transactionDetails.getModel();
        Object row[] = new Object[5];

        long totaltransaction = 0l;
        for(int i = 0;i < list.size();i++)
        {
            java.sql.Timestamp trans_date = list.get(i).getDate();

            //if(trans_date.after(statTimestamp) && trans_date.before(endTimestamp))
            // {
                row[0] = list.get(i).getID();
                row[1] = list.get(i).getCardNo();
                row[2] = list.get(i).getEmpName();
                row[3] = list.get(i).getAmount();
                row[4] = list.get(i).getDate();
                totaltransaction  = list.get(i).getAmount() + totaltransaction;

                model.addRow(row);
                // }

        }

        jLabel3_totalTransaction.setText(""+totaltransaction);
        jLabel_validDate.setText("");

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        String Name = Employeeidinput.getText();
        Name = Name.trim();

        for(User user : currentgamezoneusers)
        {
            boolean ss = Name.equalsIgnoreCase(user.getName());
            if(user.getName().trim().equalsIgnoreCase(Name))
            {
                DefaultTableModel m = (DefaultTableModel) jTable_EmpRecord.getModel();
                m.setRowCount(0);
                DefaultTableModel  model = (DefaultTableModel) jTable_EmpRecord.getModel();
                Object row[] = new Object[9];
                row[0] = user.getID();
                row[1] = user.getName();
                row[2] = user.getAddress();
                row[3] = user.getContact();
                row[4] = user.getEmail();
                row[5] = user.getType();
                row[6] = user.getGameZoneID();
                row[7] = user.getPassword();
                row[8] = user.getAddress();
                model.addRow(row);

                return;
            }
        }

        JOptionPane.showMessageDialog(jPanel1,
            "No Employee Found For this Name",
            "Inane error",
            JOptionPane.ERROR_MESSAGE);
        return;

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable_EmpRecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_EmpRecordMouseClicked
        // TODO add your handling code here:

        //        if(evt.getButton() == MouseEvent.BUTTON1)
        //        {
            //            return ;
            //        }
        //
        //        int r = jTable_EmpRecord.rowAtPoint(evt.getPoint());
        //        if (r >= 0 && r < jTable_EmpRecord.getRowCount()) {
            //            jTable_EmpRecord.setRowSelectionInterval(r, r);
            //        } else {
            //            jTable_EmpRecord.clearSelection();
            //        }
        //        //for getting locatiopn
        //        PointerInfo a = MouseInfo.getPointerInfo();
        //        Point b = a.getLocation();
        //        int x = (int) b.getX();
        //        int y = (int) b.getY();
        //        jPopupMenu1.setLocation(x, y);
        //        jMenuItem3.setText("Delete Employee");
        //        jMenuItem4.setText("Update Employee");
        //
        //        jPopupMenu1.setVisible(true);

    }//GEN-LAST:event_jTable_EmpRecordMouseClicked

    private void jTable_updatedeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_updatedeleteMouseClicked
        // TODO add your handling code here:Mouse Click on Update / Delete Jtable

        //Code for popup window for deleting and updating the employess

        User user = new User();
        int row = jTable_updatedelete.getSelectedRow();

        String ID = jTable_updatedelete.getModel().getValueAt(row, 0).toString();
        String Name = jTable_updatedelete.getModel().getValueAt(row, 1).toString();
        String addres = jTable_updatedelete.getModel().getValueAt(row, 2).toString();
        String contact = jTable_updatedelete.getModel().getValueAt(row, 3).toString();
        String email = jTable_updatedelete.getModel().getValueAt(row, 4).toString();
        String type = jTable_updatedelete.getModel().getValueAt(row, 5).toString();
        String gamezoneid = jTable_updatedelete.getModel().getValueAt(row, 6).toString();
        String password = jTable_updatedelete.getModel().getValueAt(row, 7).toString();
        String username = jTable_updatedelete.getModel().getValueAt(row, 8).toString();

        user.setID(Integer.parseInt(ID ));
        user.setEmail(email);
        user.setName(Name);
        user.setContact(contact);
        user.setType(type);
        user.setPassword(password);
        user.setAddress(addres);
        user.setGameZoneID(Integer.parseInt(gamezoneid));
        user.setUserName(username);

        Updata_DeleteEmployee newframe = new Updata_DeleteEmployee(user,this, currentgamezone.getName(),currentgamezoneusers,passwordcheck,jTable_updatedelete,currentnoofemployeelabel);
        newframe.setVisible(true);
        setVisible(false);

    }//GEN-LAST:event_jTable_updatedeleteMouseClicked

    private void UpdateEmplloyeeSearch_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateEmplloyeeSearch_ButtonActionPerformed
        // TODO add your handling code here:
        String Name = EmplyeeIdInput.getText();

        Name = Name.trim();

        for(User user : currentgamezoneusers)
        {
            String as =user.getName();
            String asas = Name;
            boolean df= as.equalsIgnoreCase(asas);
            boolean asd = as.equals(asas);
            if(user.getName().trim().equalsIgnoreCase(Name))
            {
                DefaultTableModel m = (DefaultTableModel) jTable_updatedelete.getModel();
                m.setRowCount(0);
                DefaultTableModel  model = (DefaultTableModel) jTable_updatedelete.getModel();
                Object row[] = new Object[9];
                row[0] = user.getID();
                row[1] = user.getName();
                row[2] = user.getAddress();
                row[3] = user.getContact();
                row[4] = user.getEmail();
                row[5] = user.getType();
                row[6] = user.getGameZoneID();
                row[7] = user.getPassword();
                row[8] = user.getAddress();
                model.addRow(row);

                return;
            }
        }
        JOptionPane.showMessageDialog(jPanel1,
            "No Employee Found For this Name",
            "Inane error",
            JOptionPane.ERROR_MESSAGE);
        return;

    }//GEN-LAST:event_UpdateEmplloyeeSearch_ButtonActionPerformed

    //Method to ADD new GameZoneEmployee
    private void AddNewEmployee_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewEmployee_ButtonActionPerformed
        // TODO add your handling code here:ADD button to add Employee

        if(currentgamezoneusers.size() == currentgamezone.getMax_Employee())
        {
            JOptionPane.showMessageDialog(jPanel1,
                "You can not register more then "+currentgamezone.getMax_Employee()+ "Employees ,contact product owner to increase No of employees",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return ;
        }
        String username = AddEmpName_textfield.getText();
        String password = AddEmpPassword_textfield.getText();
        String username2 = AddEmpUserName_textfield.getText();

        if(passwordcheck.containsKey(username2) || username2.equals("admin"))
        {
            JOptionPane.showMessageDialog(jPanel1,
                "This UserName is Taken ",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return ;
        }

        if(username.length() == 0 || password.length() == 0 || username2.length() == 0 )
        {
            JOptionPane.showMessageDialog(jPanel1,
                "Please Enter Mandatory Details",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return ;
        }

        if(AddEmpContact_textfield.getText().length()!=10)
        {
            JOptionPane.showMessageDialog(jPanel1,
                "Invalid Contact No",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return ;
        }

        User newuser = new User();
        newuser.setName(AddEmpName_textfield.getText());
        newuser.setType("emp");
        newuser.setGameZoneID(currentuser.getGameZoneID());
        newuser.setAddress(AddEmpAddress_textfield.getText());
        newuser.setContact(AddEmpContact_textfield.getText());
        newuser.setEmail(AddEmpEmail_textfield.getText());
        newuser.setPassword(AddEmpPassword_textfield.getText());
        newuser.setUserName(username2);

        SwingWorker work = new SwingWorker<String , Integer>()
        {
            @Override
            protected  String  doInBackground() throws Exception
            {

                UserInterface Dao = null;
                try
                {
                    Dao = UserFactory.getInstance();
                    Dao.AddEmp(newuser,currentgamezone.getName());
                    AddEmp_flag = true;
                }
                catch (Exception ex)
                {
                    AddEmp_flag = false;
                    Logger.getLogger(MainScreen_StallOwner.class.getName()).log(Level.SEVERE, null, ex);
                }

                return "end";

            }//do backgrounf ENDS

            @Override
            protected void done()
            {

                rechargeloadingdialoge.dispose();
            }
        };
        final ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/Images/recharge.gif"));
        work.execute();
        JOptionPane pane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, icon,new Object[]{}, null);
        rechargeloadingdialoge = pane.createDialog(this,"Please wait ");
        rechargeloadingdialoge.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        rechargeloadingdialoge.setVisible(true);

        if(AddEmp_flag == false)
        {
            JOptionPane.showMessageDialog(jPanel1,
                "Adding Employee Failed.",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        else
        {
            UpdateEmployeeListThread updateemployeelist = new UpdateEmployeeListThread(currentgamezoneusers,currentgamezone.getName(),rechargeloadingdialoge);
            updateemployeelist.start();

            UpdatePasswordCheck updatepasswordcheck = new UpdatePasswordCheck(currentgamezone.getName(),passwordcheck);
            updatepasswordcheck.start();

            JOptionPane.showMessageDialog(jPanel1,
                "Adding Employee Success.",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            // now updating the customer list

        }

        AddEmpName_textfield.setText("");
        AddEmpAddress_textfield.setText("");
        AddEmpPassword_textfield.setText("");
        AddEmpEmail_textfield.setText("");
        AddEmpContact_textfield.setText("");
        AddEmpUserName_textfield.setText("");
        currentnoofemployeelabel.setText(currentgamezoneusers.size()+"");
        AddEmp_flag = false;
    }//GEN-LAST:event_AddNewEmployee_ButtonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:Register Customer
        RegisterCustomer registerCustomer = new  RegisterCustomer(this, true,Noofcustomerlabel,jTable_customers,customerlist,currentgamezone);
        registerCustomer.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void ResetValuestozerobuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetValuestozerobuttonActionPerformed
        // TODO add your handling code here://Reset button code In Recharge Tab
        jTextField1.setText("");
        jTextField2.setText("");
        Textfiled_ExistingAmount.setText("");
    }//GEN-LAST:event_ResetValuestozerobuttonActionPerformed

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:Recharge Button in rechargeb tab
        String cardno = jTextField1.getText();
        String amount = jTextField2.getText();
        int amt =0;
        //validation code
        if("".equals(cardno) || "".equals(amount))
        {
            if("".equals(cardno))
            {

                JOptionPane.showMessageDialog(jPanel1,
                    "Please Scan The Card .",
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
        //first writting amout to card serial communication code
        RechargeWait = new CountDownLatch(1); //for stoping the gui
        try
        {

            FactoryClass.getCommObj().sendData(amount);
            RechargeWait.await();

        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(jPanel1,
                "Error in Recharge ,Please Check Com Port Connection",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(jPanel1,
                "Something went wrong  "+e,
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        //now doing recharge

        if(ComportRecharge == false)
        {

            JOptionPane.showMessageDialog(jPanel1,
                "Error in HardWare ,Please Check the connection",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return;

        }

        ComportRecharge = false;

        Recharge rec = new Recharge();
        long millis=System.currentTimeMillis();
        java.sql.Timestamp date = new Timestamp(millis);

        rec.setAmount(amt);
        rec.setCardNo(cardno);
        rec.setEmpName(currentuser.getName());
        rec.setDate(date);

        //Details Gather Complete now Recharge
        //step 1 - Record Save to Database
        //step 2 - Recharge to the card (com port recharge)
        //step 3 - update Transaction details

        SwingWorker work = new SwingWorker<String , Integer>()
        {
            @Override
            protected  String  doInBackground() throws Exception
            {
                String TableName = currentgamezone.getName()+"_transaction";

                TransactionInterface Dao = null ;
                try
                {
                    Dao = TransactionFactory.getInstance();
                    Dao.Recharge(rec, TableName);
                    rech_flag = true;
                }
                catch (Exception ex)
                {
                    //Error In Transaction
                    Logger.getLogger(MainScreen_StallOwner.class.getName()).log(Level.SEVERE, null, ex);
                    rech_flag = false;
                }

                return "end";

            }//do backgrounf ENDS

            @Override
            protected void done()
            {

                rechargeloadingdialoge.dispose();
            }
        };

        final ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/Images/recharge.gif"));
        work.execute();
        JOptionPane pane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, icon,new Object[]{}, null);
        rechargeloadingdialoge = pane.createDialog(this,"Please wait ");
        rechargeloadingdialoge.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        rechargeloadingdialoge.setVisible(true);
        //jDialog.show();  this method is depricated there using setVisible method for showing the dialoge box

        if(rech_flag == false)
        {
            System.out.println("Error in recharge");
            JOptionPane.showMessageDialog(jPanel1,
                "Recharge Failed Check Internet Connection",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);

            return ;
        }else
        {

            UpdateTransactionListThread updatetransaction = new UpdateTransactionListThread(transdetailscomplete, currentgamezone.getName());
            updatetransaction.start();
            JOptionPane.showMessageDialog(jPanel1,
                "Recharge Success",
                "Inane error",
                JOptionPane.PLAIN_MESSAGE);
        }

        rech_flag = false;
        //jTextField1.setText("");
        //jTextField2.setText("");
        //Textfiled_ExistingAmount.setText("");

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //TODO add your handling code here:
        String cardno = jTextField1.getText();
        if(cardno.length() == 0 )
        {
            JOptionPane.showMessageDialog(jPanel1,
                "Please scan the card.",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return ;
        }
        if("".equals(cardno))
        {
            JOptionPane.showMessageDialog(jPanel1,
                "Please scan the card",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return ;
        }
        try
        {
            FactoryClass.getCommObj().sendData("reset");
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(jPanel1,
                "Error in ReFund.",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return ;
        }

        JOptionPane.showMessageDialog(jPanel1,
            "Refund Success",
            "Success",
            JOptionPane.PLAIN_MESSAGE);
     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:OtherTrasaction 
        
        OtherTrasactions other = new OtherTrasactions(this, true,currentgamezone);
        other.setVisible(true);
        
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:Other Trasaction perticular date
        
        java.util.Date startdate;
        java.util.Date enddate;
        if(jDateChooser_StartDate2.getDate() == null || jDateChooser_EndDate2.getDate() == null)
        {
            jLabel_validDate1.setText("Enter Valid Dates");
            return;
        }
        if(jDateChooser_StartDate2.getDate().after(jDateChooser_EndDate2.getDate()))
        {
          jLabel_validDate1.setText("start Date Can't be greater then end date");
            return;  
        }
        try
        {
            startdate  = jDateChooser_StartDate2.getDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(startdate);

            // Set time fields to zero
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            // Put it back in the Date object
            startdate = cal.getTime();
            //startdate  = jDateChooser_StartDate.getDate();

            enddate =jDateChooser_EndDate2.getDate();

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(enddate);

            // Set time fields to zero
            cal2.set(Calendar.HOUR_OF_DAY, 24);

            // Put it back in the Date object
            enddate = cal2.getTime();
        }
        catch(Exception e)
        {

            jLabel_validDate1.setText("Enter Valid Dates");
            return;
        }

        //Getting data from the amzone database

        ArrayList<gamecenter.OtherTrasactions> list  = null;
        try
        {
            TransactionInterface Dao = TransactionFactory.getInstance();
            list = Dao.GetOthertrasactionsPerticularDates(currentgamezone.getName()+"_other_transaction", new java.sql.Date(startdate.getTime()), new java.sql.Date(enddate.getTime()));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(jPanel1,
                "Unable to Connect To Internet ,Please check your internet Connection.",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return ;

        }
            DefaultTableModel m = (DefaultTableModel) OthertrasactionsTable.getModel();
           m.setRowCount(0);
           DefaultTableModel  model = (DefaultTableModel) OthertrasactionsTable.getModel();
           Object row[] = new Object[7];
           for(int i=0 ; i<list.size() ; i++)
           {
         
               row[0] = list.get(i).getID();
           row[1] = list.get(i).getCustomerName();
           row[2] = list.get(i).getExpireDate();
           row[3] = list.get(i).getCardNo();
           row[4] =list.get(i).getMethod();
           row[5] = list.get(i).getMoney();
           row[6] = list.get(i).getDate();
      
           model.addRow(row);  
           }
            jLabel_validDate1.setText("");

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        //Other Trasaction this Month
        
        ArrayList<gamecenter.OtherTrasactions> otherTrasactionses= null;
        try
        {
            TransactionInterface Dao = TransactionFactory.getInstance();
          otherTrasactionses=  Dao.GetOthertrasactions(currentgamezone.getName()+"_other_transaction");
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(jPanel1,
            "Internet Connection Error",
            "Inane error",
            JOptionPane.ERROR_MESSAGE);
        return ;
        }
        
           DefaultTableModel m = (DefaultTableModel) OthertrasactionsTable.getModel();
           m.setRowCount(0);
           DefaultTableModel  model = (DefaultTableModel) OthertrasactionsTable.getModel();
           Object row[] = new Object[8];
           for(int i=0 ; i<otherTrasactionses.size() ; i++)
           {
           row[0] = otherTrasactionses.get(i).getID();
           row[1] = otherTrasactionses.get(i).getCustomerName();
           row[2] = otherTrasactionses.get(i).getCardNo();
           row[3] = otherTrasactionses.get(i).getExpireDate();
           row[4] =otherTrasactionses.get(i).getMethod();
           row[5] = otherTrasactionses.get(i).getMoney();
           row[6] = otherTrasactionses.get(i).getDate();
      
           model.addRow(row);  
           }
       
        
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        // TODO add your handling code here:
        jPanel18.setBackground(new Color(54, 33,89));
        jPanel13.setBackground(new Color(54, 33,89));
        jPanel14.setBackground(new Color(54, 33,89));
        jPanel15.setBackground(new Color(54 , 33 , 89));
        jPanel22.setBackground(new Color(54, 33,89));
        jPanel10.setBackground(new Color(110, 89,222));
        jLabel_Label.setText("Employees Records");
        AddEmployee.setVisible(false);
        AddUpateEmployee.setVisible(false);
        transaction.setVisible(false);
        Emprecords.setVisible(false);
        OtherTransactions.setVisible(true);
        Customer.setVisible(false);
          jLabel_Label.setText("Others Transaction Details");
        
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        if(jDateChooser_todayCollection_from.getDate() == null || jDateChooser_todayCollection_to.getDate() == null)
        {
            JOptionPane.showMessageDialog(jPanel1,
            "Please Provide Proper Dates.",
            "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return ;
        }
      
        HashMap<String,Long> amountmapping = new HashMap<>();
       Long amount = 0l;
        java.util.Date date  = jDateChooser_todayCollection_from.getDate();
        java.util.Date date2  = jDateChooser_todayCollection_to.getDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        // Set time fields to zero
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        // Put it back in the Date object
        date = cal.getTime();

        
        cal2.set(Calendar.HOUR_OF_DAY, 23);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);

        // Put it back in the Date object
        date2 = cal2.getTime();
        
        
        ArrayList<Games> gamelistper = null;
        StallInterface Dao = StallFactory.getInstance();
        try
        {
            gamelistper = Dao.GetGamesPerticularDateRange(currentgamezone.getName(), date,date2);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(jPanel1,
            "Unable to Connect To Internet ,Please check your internet Connection.",
            "Inane error",
            JOptionPane.ERROR_MESSAGE);
            todaycollectionamount.setText("No internet connection");
            return ;
        }
        if(gamelistper.isEmpty())
        {
            JOptionPane.showMessageDialog(jPanel1,
            "No record for  this perticular date.",
            "Inane error",
            JOptionPane.ERROR_MESSAGE);
            todaycollectionamount.setText("No records fouond for perticular date");
            return ;
        }
        else{
            DefaultTableModel m = (DefaultTableModel) TodaysCollectionTable.getModel();
            m.setRowCount(0);
            for(Games game : gamelistper)
            {

                if(amountmapping.containsKey(game.getGameName()))
                {
                 long current = Long.parseLong(game.getAmount());
                 long old = amountmapping.get(game.getGameName());
                 long newamount = old + current;
                 amountmapping.put(game.getGameName(), newamount);
                    
                }
                else
                {
                long current = Long.parseLong(game.getAmount());
                amountmapping.put(game.getGameName(), current);
                }
                
              

            }
            
               java.util.Set<String>  set = amountmapping.keySet();
               for(String key : set)
               {
                DefaultTableModel  model = (DefaultTableModel) TodaysCollectionTable.getModel();
                Object row[] = new Object[2];

                row[0] = key;
                row[1] = amountmapping.get(key);
               
                amount = amount + amountmapping.get(key);
                  
                model.addRow(row);
            
               }
              
        }
        
        
             todaycollectionamount.setText("Today collection is "+amount);
        
        
        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        ChangePassword changePassword = new ChangePassword(this, true , currentuser , currentgamezone );
        changePassword.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed
 	public static java.sql.Timestamp convert(java.util.Date date)
	{
		return new java.sql.Timestamp(date.getTime());
	}

        
        //Below Methods are for serial communication ethods
        public void setCardNumber(String cardno)
        {
            jTextField1.setText(cardno);
        }
        public void cardDetails(String rs)
        {
            Textfiled_ExistingAmount.setText(rs);
        }
        public void message(String message,String option)
        {
                     if(option.equals("Recharge"))
                     {
                         ComportRecharge = true;
                         RechargeWait.countDown();
                         return;
                     }
                     if(option.equals("Error"))
                     {
                         RechargeWait.countDown();
                         return;
                     }
                     JOptionPane.showMessageDialog(jPanel1,
                     message,
                     "Inane error",
                     JOptionPane.ERROR_MESSAGE);
        }
        /*
        Method for getting gamename an damount from the machine
        */
        public void setGameAmount(String gamename,String amount)
        {
            gamename = gamename.trim();
            Long updated = 0l;
            try
            {
                StallInterface Dao = StallFactory.getInstance();
                updated = Long.parseLong(amount);
                Dao.UpdateAmount(currentgamezone.getName(), updated+"", gamename);
                
            }catch(Exception ex)
            {
                     JOptionPane.showMessageDialog(jPanel1,
                     "Unable to update today collection",
                     "Inane error",
                     JOptionPane.ERROR_MESSAGE);
                     return;
            }          
       DefaultTableModel m = (DefaultTableModel) TodaysCollectionTable.getModel();
       m.setRowCount(0);
       for(Games game : gamelist)
       {
            if(game.getGameName().equals(gamename))
            {
               game.setAmount(updated+"");
            }
            DefaultTableModel  model = (DefaultTableModel) TodaysCollectionTable.getModel();
            Object row[] = new Object[2];
            row[0] = game.getGameName();
            row[1] = game.getAmount();
            model.addRow(row);
            
        }
 
        }
   
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
    private javax.swing.JTextField AddEmpAddress_textfield;
    private javax.swing.JTextField AddEmpContact_textfield;
    private javax.swing.JTextField AddEmpEmail_textfield;
    private javax.swing.JTextField AddEmpName_textfield;
    private javax.swing.JTextField AddEmpPassword_textfield;
    private javax.swing.JTextField AddEmpUserName_textfield;
    private javax.swing.JPanel AddEmployee;
    private javax.swing.JButton AddNewEmployee_Button;
    private javax.swing.JPanel AddUpateEmployee;
    private javax.swing.JPanel Customer;
    private javax.swing.JTabbedPane EmployeeTab;
    private javax.swing.JTextField Employeeidinput;
    private javax.swing.JTextField EmplyeeIdInput;
    private javax.swing.JPanel Emprecords;
    public javax.swing.JLabel Label_clock;
    private javax.swing.JLabel Noofcustomerlabel;
    private javax.swing.JPanel OtherTransactions;
    private javax.swing.JTable OthertrasactionsTable;
    private javax.swing.JButton RegisterNewcustomer_button;
    private javax.swing.JButton ResetButton_Customer;
    private javax.swing.JButton ResetValuestozerobutton;
    public javax.swing.JTextField Textfiled_ExistingAmount;
    private javax.swing.JTable TodaysCollectionTable;
    private javax.swing.JButton UpdateEmplloyeeSearch_Button;
    private javax.swing.JLabel UserNameUniqueLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JLabel currentnoofemployeelabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser_EndDate;
    private com.toedter.calendar.JDateChooser jDateChooser_EndDate2;
    private com.toedter.calendar.JDateChooser jDateChooser_StartDate;
    private com.toedter.calendar.JDateChooser jDateChooser_StartDate2;
    private com.toedter.calendar.JDateChooser jDateChooser_todayCollection_from;
    private com.toedter.calendar.JDateChooser jDateChooser_todayCollection_to;
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
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel3_totalTransaction;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_GameZoneNAme;
    private javax.swing.JLabel jLabel_Label;
    private javax.swing.JLabel jLabel_OwnerName;
    private javax.swing.JLabel jLabel_OwnerName1;
    private javax.swing.JLabel jLabel_currentEMpName;
    private javax.swing.JLabel jLabel_currentempname2;
    private javax.swing.JLabel jLabel_validDate;
    private javax.swing.JLabel jLabel_validDate1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    public javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable_EmpRecord;
    private javax.swing.JTable jTable_customers;
    private javax.swing.JTable jTable_transactionDetails;
    private javax.swing.JTable jTable_updatedelete;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField_regcontact;
    private javax.swing.JTextField jTextField_regemail;
    private javax.swing.JTextField jTextField_regname;
    private javax.swing.JLabel todaycollectionamount;
    private javax.swing.JPanel transaction;
    // End of variables declaration//GEN-END:variables
}
