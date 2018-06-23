package Gui;
import gamecenter.Stall;
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
import java.awt.Color;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import Database.GameZoneDetailsAndName;
import gamecenter.Clock;
import gamecenter.Games;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
public class MainScreen_Admin extends javax.swing.JFrame 
{
    
    //List for all register GameZone
    ArrayList<Stall> GameZoneList;
    ArrayList<User> Selectedgamezoneusers;
    ArrayList<Recharge> Selectedgamezonetransaction; 
    ArrayList<String> gamezonenames;    
    //Containsa all the Users of the System
    //Contains GameZones Details
    JDialog jDialog;
    
    String ErrorMessage;
    
    
    ArrayList<Recharge> transactionlist;
    ArrayList<User> employelist;
    GameNameSetting gameNameSetting;//for selecting the games for gamezone
    
    
    int LastId ;
    public MainScreen_Admin()
    {
        gamezonenames = new ArrayList<>();
        gameNameSetting = null;
      
       try
       {
        GameZoneDetailsAndName details ;   
        StallInterface Dao = StallFactory.getInstance();
        details = Dao.GetAllGameZone();
        GameZoneList = details.GameZoneDetails;
        gamezonenames = details.GameZoneNames;
       
       }
       catch(Exception e)
       {
           //Error in getting GameZone
           
       }
       if(GameZoneList == null || GameZoneList.size() ==0)
       {
           //Error again
       }
   
        initComponents();
        
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    
    
        AddGameZone.setVisible(true);
        ViewGameZone.setVisible(false);
        GameZoneTransaction.setVisible(false);
        
        
      
          
    
       jComboBox1_selectgamezoneemployee.addItem("Select GameZone"); 
       jComboBox_allgamezones.addItem("Select GameZone");
       for(String gamezone : gamezonenames)
       {
        jComboBox1_selectgamezoneemployee.addItem(gamezone);
        jComboBox_allgamezones.addItem(gamezone);
       }
        
               
 
       
              java.util.Date date=new java.util.Date();
		String dip=date.toString();
		Label_clockadmin.setText(dip);
                //clock
                 Timer time=new Timer();
		int delay=5000;
		int period= 1000;
		Clock clock = new Clock(Label_clockadmin);
		time.scheduleAtFixedRate(clock, delay,period);
 
       
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TopParentpanel = new javax.swing.JPanel();
        SidePanel = new javax.swing.JPanel();
        AddGameZonepanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        viewgamezonepanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        TransactionDetailspanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        EmployeeDeatilsPanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel_OwnerName1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        WorkingScreen_layeredPanel = new javax.swing.JLayeredPane();
        AddGameZone = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel_Sub = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField_GameZoneAddress = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jComboBox_sub = new javax.swing.JComboBox<>();
        jTextField_GameZoneName = new javax.swing.JTextField();
        jTextField_GameZoneOwnerName = new javax.swing.JTextField();
        jTextField_GameZoneOwnerContact = new javax.swing.JTextField();
        jTextField_GameZoneOwnerPassword = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField_GameZonemaxemployee = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jTextField_GameZoneEmail = new javax.swing.JTextField();
        Addgamescheckbox = new javax.swing.JCheckBox();
        ViewGameZone = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Gamezone = new javax.swing.JTable();
        GameZoneTransaction = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox_allgamezones = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_transaction = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jDateChooser_todayCollection_from = new com.toedter.calendar.JDateChooser();
        jDateChooser_todayCollection_from1 = new com.toedter.calendar.JDateChooser();
        jDateChooser_todayCollection_to = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        EmployeeDetails = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1_selectgamezoneemployee = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_Emploe = new javax.swing.JTable();
        MainHeadingLabel = new javax.swing.JLabel();
        Label_clockadmin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(1204, 767));
        setResizable(false);

        TopParentpanel.setBackground(new java.awt.Color(110, 89, 222));
        TopParentpanel.setLayout(null);

        SidePanel.setBackground(new java.awt.Color(54, 33, 89));
        SidePanel.setLayout(null);

        AddGameZonepanel.setBackground(new java.awt.Color(54, 33, 89));
        AddGameZonepanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddGameZonepanelMouseClicked(evt);
            }
        });
        AddGameZonepanel.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/newAddemp.png"))); // NOI18N
        AddGameZonepanel.add(jLabel4);
        jLabel4.setBounds(60, 20, 40, 50);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Add GameZone");
        AddGameZonepanel.add(jLabel16);
        jLabel16.setBounds(130, 40, 130, 17);

        SidePanel.add(AddGameZonepanel);
        AddGameZonepanel.setBounds(0, 250, 300, 70);

        viewgamezonepanel.setBackground(new java.awt.Color(54, 33, 89));
        viewgamezonepanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewgamezonepanelMouseClicked(evt);
            }
        });
        viewgamezonepanel.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/newCustomer.png"))); // NOI18N
        viewgamezonepanel.add(jLabel5);
        jLabel5.setBounds(60, 10, 70, 60);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("View GameZones");
        viewgamezonepanel.add(jLabel17);
        jLabel17.setBounds(130, 40, 130, 17);

        SidePanel.add(viewgamezonepanel);
        viewgamezonepanel.setBounds(0, 320, 300, 70);

        TransactionDetailspanel.setBackground(new java.awt.Color(54, 33, 89));
        TransactionDetailspanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TransactionDetailspanelMouseClicked(evt);
            }
        });
        TransactionDetailspanel.setLayout(null);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/newemprecords.png"))); // NOI18N
        TransactionDetailspanel.add(jLabel6);
        jLabel6.setBounds(60, 10, 40, 50);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Transaction Details");
        TransactionDetailspanel.add(jLabel18);
        jLabel18.setBounds(130, 20, 130, 17);

        SidePanel.add(TransactionDetailspanel);
        TransactionDetailspanel.setBounds(0, 420, 300, 70);

        EmployeeDeatilsPanel.setBackground(new java.awt.Color(54, 33, 89));
        EmployeeDeatilsPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EmployeeDeatilsPanelMouseClicked(evt);
            }
        });
        EmployeeDeatilsPanel.setLayout(null);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Employee Details");
        EmployeeDeatilsPanel.add(jLabel20);
        jLabel20.setBounds(130, 10, 130, 17);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/newAddemp.png"))); // NOI18N
        EmployeeDeatilsPanel.add(jLabel2);
        jLabel2.setBounds(60, 0, 50, 40);

        SidePanel.add(EmployeeDeatilsPanel);
        EmployeeDeatilsPanel.setBounds(0, 510, 300, 60);

        jLabel_OwnerName1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_OwnerName1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_OwnerName1.setText("Exit");
        jLabel_OwnerName1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_OwnerName1MouseClicked(evt);
            }
        });
        SidePanel.add(jLabel_OwnerName1);
        jLabel_OwnerName1.setBounds(20, 660, 47, 29);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Stark Technology");
        SidePanel.add(jLabel3);
        jLabel3.setBounds(60, 60, 160, 60);
        SidePanel.add(jSeparator4);
        jSeparator4.setBounds(60, 110, 150, 20);

        TopParentpanel.add(SidePanel);
        SidePanel.setBounds(0, 0, 300, 730);

        AddGameZone.setBackground(new java.awt.Color(255, 255, 255));
        AddGameZone.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("Add New GameZone");
        AddGameZone.add(jLabel7);
        jLabel7.setBounds(319, 14, 320, 40);

        jLabel_Sub.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel_Sub.setText("Sub Yr");
        AddGameZone.add(jLabel_Sub);
        jLabel_Sub.setBounds(490, 210, 100, 50);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Owner Name");
        AddGameZone.add(jLabel11);
        jLabel11.setBounds(70, 150, 220, 60);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Contact No");
        AddGameZone.add(jLabel12);
        jLabel12.setBounds(70, 260, 220, 60);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("GameZone Name");
        AddGameZone.add(jLabel14);
        jLabel14.setBounds(70, 50, 220, 60);

        jTextField_GameZoneAddress.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        AddGameZone.add(jTextField_GameZoneAddress);
        jTextField_GameZoneAddress.setBounds(490, 110, 350, 50);

        jButton1.setText("Add GameZone / Games");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        AddGameZone.add(jButton1);
        jButton1.setBounds(350, 470, 230, 50);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Passsword");
        AddGameZone.add(jLabel9);
        jLabel9.setBounds(70, 350, 170, 60);

        jComboBox_sub.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jComboBox_sub.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 yr", "2 yr", "3 yr", "4 yr", " " }));
        AddGameZone.add(jComboBox_sub);
        jComboBox_sub.setBounds(600, 210, 140, 50);

        jTextField_GameZoneName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        AddGameZone.add(jTextField_GameZoneName);
        jTextField_GameZoneName.setBounds(70, 110, 230, 40);

        jTextField_GameZoneOwnerName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        AddGameZone.add(jTextField_GameZoneOwnerName);
        jTextField_GameZoneOwnerName.setBounds(70, 210, 230, 40);

        jTextField_GameZoneOwnerContact.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        AddGameZone.add(jTextField_GameZoneOwnerContact);
        jTextField_GameZoneOwnerContact.setBounds(70, 310, 230, 40);

        jTextField_GameZoneOwnerPassword.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        AddGameZone.add(jTextField_GameZoneOwnerPassword);
        jTextField_GameZoneOwnerPassword.setBounds(70, 400, 230, 40);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Max employess");
        AddGameZone.add(jLabel15);
        jLabel15.setBounds(490, 370, 220, 60);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("Address");
        AddGameZone.add(jLabel19);
        jLabel19.setBounds(490, 50, 220, 60);

        jTextField_GameZonemaxemployee.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        AddGameZone.add(jTextField_GameZonemaxemployee);
        jTextField_GameZonemaxemployee.setBounds(690, 380, 150, 40);

        jButton3.setText("Add Games");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        AddGameZone.add(jButton3);
        jButton3.setBounds(70, 470, 110, 40);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setText("Email ID");
        AddGameZone.add(jLabel21);
        jLabel21.setBounds(490, 270, 220, 60);

        jTextField_GameZoneEmail.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        AddGameZone.add(jTextField_GameZoneEmail);
        jTextField_GameZoneEmail.setBounds(490, 320, 350, 40);

        Addgamescheckbox.setText("Add Games Only");
        AddGameZone.add(Addgamescheckbox);
        Addgamescheckbox.setBounds(310, 120, 140, 25);

        ViewGameZone.setBackground(new java.awt.Color(255, 255, 255));
        ViewGameZone.setLayout(null);

        jTable_Gamezone.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "OwnerName", "Address", "Contact", "Password", "SubStartDate", "SubEndDate", "MaxEmployee"
            }
        ));
        jTable_Gamezone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_GamezoneMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_Gamezone);

        ViewGameZone.add(jScrollPane2);
        jScrollPane2.setBounds(0, 2, 900, 540);

        GameZoneTransaction.setBackground(new java.awt.Color(255, 255, 255));
        GameZoneTransaction.setLayout(null);

        jLabel8.setText("Total collection");
        GameZoneTransaction.add(jLabel8);
        jLabel8.setBounds(600, 80, 300, 40);

        jComboBox_allgamezones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_allgamezonesActionPerformed(evt);
            }
        });
        GameZoneTransaction.add(jComboBox_allgamezones);
        jComboBox_allgamezones.setBounds(201, 28, 180, 22);

        jTable_transaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "GameName", "amount"
            }
        ));
        jTable_transaction.setEnabled(false);
        jScrollPane4.setViewportView(jTable_transaction);

        GameZoneTransaction.add(jScrollPane4);
        jScrollPane4.setBounds(0, 150, 900, 390);

        jLabel10.setText("Select GameZone");
        GameZoneTransaction.add(jLabel10);
        jLabel10.setBounds(65, 28, 130, 16);
        GameZoneTransaction.add(jDateChooser_todayCollection_from);
        jDateChooser_todayCollection_from.setBounds(60, 80, 150, 40);
        GameZoneTransaction.add(jDateChooser_todayCollection_from1);
        jDateChooser_todayCollection_from1.setBounds(420, 620, 150, 40);
        GameZoneTransaction.add(jDateChooser_todayCollection_to);
        jDateChooser_todayCollection_to.setBounds(250, 80, 150, 40);

        jButton2.setText("Get Records");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        GameZoneTransaction.add(jButton2);
        jButton2.setBounds(430, 80, 160, 40);

        EmployeeDetails.setBackground(new java.awt.Color(255, 255, 255));
        EmployeeDetails.setLayout(null);

        jLabel1.setText("Select GameZone");
        EmployeeDetails.add(jLabel1);
        jLabel1.setBounds(50, 30, 150, 40);

        jComboBox1_selectgamezoneemployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1_selectgamezoneemployeeActionPerformed(evt);
            }
        });
        EmployeeDetails.add(jComboBox1_selectgamezoneemployee);
        jComboBox1_selectgamezoneemployee.setBounds(230, 40, 130, 22);

        jTable_Emploe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Address", "Contact", "Email", "type", "GameZoneId", "Password", "UserName"
            }
        ));
        jScrollPane3.setViewportView(jTable_Emploe);

        EmployeeDetails.add(jScrollPane3);
        jScrollPane3.setBounds(0, 140, 900, 402);

        WorkingScreen_layeredPanel.setLayer(AddGameZone, javax.swing.JLayeredPane.DEFAULT_LAYER);
        WorkingScreen_layeredPanel.setLayer(ViewGameZone, javax.swing.JLayeredPane.DEFAULT_LAYER);
        WorkingScreen_layeredPanel.setLayer(GameZoneTransaction, javax.swing.JLayeredPane.DEFAULT_LAYER);
        WorkingScreen_layeredPanel.setLayer(EmployeeDetails, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout WorkingScreen_layeredPanelLayout = new javax.swing.GroupLayout(WorkingScreen_layeredPanel);
        WorkingScreen_layeredPanel.setLayout(WorkingScreen_layeredPanelLayout);
        WorkingScreen_layeredPanelLayout.setHorizontalGroup(
            WorkingScreen_layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AddGameZone, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
            .addGroup(WorkingScreen_layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ViewGameZone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(WorkingScreen_layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(GameZoneTransaction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(WorkingScreen_layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(EmployeeDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        WorkingScreen_layeredPanelLayout.setVerticalGroup(
            WorkingScreen_layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AddGameZone, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
            .addGroup(WorkingScreen_layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ViewGameZone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(WorkingScreen_layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(GameZoneTransaction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(WorkingScreen_layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(EmployeeDetails, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TopParentpanel.add(WorkingScreen_layeredPanel);
        WorkingScreen_layeredPanel.setBounds(300, 190, 900, 540);

        MainHeadingLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        MainHeadingLabel.setForeground(new java.awt.Color(255, 255, 255));
        MainHeadingLabel.setText("Heading");
        TopParentpanel.add(MainHeadingLabel);
        MainHeadingLabel.setBounds(610, 90, 400, 70);

        Label_clockadmin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Label_clockadmin.setForeground(new java.awt.Color(255, 255, 255));
        Label_clockadmin.setText("jLabel10");
        TopParentpanel.add(Label_clockadmin);
        Label_clockadmin.setBounds(830, 10, 290, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TopParentpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TopParentpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddGameZonepanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddGameZonepanelMouseClicked
        // TODO add your handling code here:Add new GameZone 
        
             
        MainHeadingLabel.setText("Register New GameZone");
        AddGameZone.setVisible(true);
        ViewGameZone.setVisible(false);
        GameZoneTransaction.setVisible(false);
        EmployeeDetails.setVisible(false);
        
        AddGameZonepanel.setBackground(new Color(110, 89,222));
        viewgamezonepanel.setBackground(new Color(54, 33,89));
        TransactionDetailspanel.setBackground(new Color(54, 33,89));
        EmployeeDeatilsPanel.setBackground(new Color(54, 33,89)); 
       
   
        
    }//GEN-LAST:event_AddGameZonepanelMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:Create button  Main Add new GameZoneButton
      
         if(Addgamescheckbox.isSelected())
         {
             
             if(jTextField_GameZoneName.getText().length() == 0 )
             {
                  JOptionPane.showMessageDialog(this,
                  "First Select GameZone",
                  "Inane error",
                  JOptionPane.ERROR_MESSAGE);
                  return;
             
                 
             }
             String gamezonename = jTextField_GameZoneName.getText().trim();
                 
             for(String gamezone : gamezonenames)
                 {
                     if(gamezone.equals(gamezonename))
                     {
                            JOptionPane.showMessageDialog(this,
                            "GameZone Found",
                            "Message",
                            JOptionPane.PLAIN_MESSAGE);
                            
                            ArrayList<String> newgames = new ArrayList<>();
                            String gamesstring ="";
                            while(true)
                            {
                                    String GameZoneG = JOptionPane.showInputDialog(this,"Enter Game Name");
                                    if(GameZoneG == null || GameZoneG.length()==0)
                                    {
                                        JOptionPane.showMessageDialog(this,
                                        "Empty String ",
                                        "Inane error",
                                        JOptionPane.ERROR_MESSAGE);
                                         return;
                                     }else
                                     {
                                         newgames.add(GameZoneG);
                                         gamesstring  = gamesstring + GameZoneG  + " , ";
                                     }
                                       
                               int a=JOptionPane.showConfirmDialog(this,"Add MoreGames ??");  
                               if(a==JOptionPane.CANCEL_OPTION)
                                {                   
                                    break;
                                }  
                               if(a==JOptionPane.NO_OPTION)
                               {
                                   break;
                               }
                               
                                     
                            }
                       
                            
                            
                            
                            JOptionPane.showMessageDialog(this,
                            "Click ok to add Games "+gamesstring,
                            "Message",
                            JOptionPane.PLAIN_MESSAGE);
                            
                            String currentGame="";
                            try
                            {
                        
                              for(String G : newgames)
                              {  
                                   currentGame = G;   
                                   StallInterface Dao = StallFactory.getInstance();
                                   Dao.AddNewGames(gamezonename, G);
                              }
                          
                           
                            }catch(Exception e)
                            {
                                  JOptionPane.showMessageDialog(this,
                                  "Error Adding Game  :  "+ currentGame +"  and games You add After This game  "+e,
                                  "Error",
                                  JOptionPane.ERROR_MESSAGE);
                             return ;
                            }
                         
                               JOptionPane.showMessageDialog(this,
                               "Games ADDED "+gamesstring,
                               "Message",
                               JOptionPane.PLAIN_MESSAGE);
                            
                               return ;
                            
                     }
                
                 }
             
            
                 JOptionPane.showMessageDialog(this,
                  "No gamezone found for this name",
                  "Inane error",
                  JOptionPane.ERROR_MESSAGE);
                  return;
             
             
         }
        
        int a=JOptionPane.showConfirmDialog(this,"Please Check the Infomation before adding new gamezone ,click Yes to proceed and cancel for recheck");  
        if(a==JOptionPane.CANCEL_OPTION)
        {                   
                       return ;
         }  
          if(a==JOptionPane.NO_OPTION)
        {                   
                       return ;
         }  
        
        if(gameNameSetting == null || gameNameSetting.gamelist.isEmpty())
        {
                 JOptionPane.showMessageDialog(this,
                 "Please Select atleast one game for GameZone",
                 "Inane error",
                 JOptionPane.ERROR_MESSAGE);
                 return;
            
        }
      
        
        String Gamezonename = jTextField_GameZoneName.getText();
        String Conact = jTextField_GameZoneOwnerContact.getText();
        String Password = jTextField_GameZoneOwnerPassword.getText();
        String OwnerName = jTextField_GameZoneOwnerName.getText();
        String address = jTextField_GameZoneAddress.getText();
        String Email = jTextField_GameZoneEmail.getText();
        String maxemployee = jTextField_GameZonemaxemployee.getText();
       
        try
        {
               int maxemp = Integer.parseInt(maxemployee);
                if(maxemp > 15 || maxemp < 0)
               {
                 JOptionPane.showMessageDialog(this,
                 "A GameZone has max of 15 employee only",
                 "Inane error",
                 JOptionPane.ERROR_MESSAGE);
                 return;
               }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,
                 "Please enter valid no of  employees",
                 "Inane error",
                 JOptionPane.ERROR_MESSAGE);
                 return;
        }
     
        
        
        if(gamezonenames.contains(Gamezonename))
        {
              JOptionPane.showMessageDialog(this,
                 "GameZone of this name exits",
                 "Inane error",
                 JOptionPane.ERROR_MESSAGE);
                 return;
        }
      
        
          
        
        for(String gamezone : gamezonenames )
        {
            if(Gamezonename.trim().equalsIgnoreCase(gamezone.trim()))
            {
                
                  JOptionPane.showMessageDialog(this,
                 "GameZone of this name exits",
                 "Inane error",
                 JOptionPane.ERROR_MESSAGE);
                 return;
             
            }
            
        }
        
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
        
        //filling stall object with gathered information
        Stall newgamezone = new Stall();
        
        newgamezone.setAddress(address);
        newgamezone.setContact(Conact);
        newgamezone.setName(Gamezonename);
        newgamezone.setPassword(Password);
        newgamezone.setOwnerName(OwnerName);
        newgamezone.setSubStartDate(currentdate);
        newgamezone.setSubEndDate(finalEndsubDate);
        newgamezone.setEmail(Email);
        newgamezone.setMax_Employee(Integer.parseInt(maxemployee));
        
   
        //Completed getting data from GUI and storing into obbject of stall type
        //Now using backgrounf to add New gamezone

        MainAdminInterface Dao = null;
        try 
        {
            Dao = MainAdminFactory.getInstance();
            Dao.AddGameZone(newgamezone,gameNameSetting.gamelist);
        } catch (Exception ex) 
        {
            
            
            JOptionPane.showMessageDialog(this,
                 "Unable to add new GameZone" + ex,
                 "Inane error",
                 JOptionPane.ERROR_MESSAGE);
                 return;
            
        }
      
        JOptionPane.showMessageDialog(this,
        "GameZone Added successfully",
        "Success",
        JOptionPane.PLAIN_MESSAGE);
                  
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void viewgamezonepanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewgamezonepanelMouseClicked
        // TODO add your handling code here:
       MainHeadingLabel.setText("Registered GameZone Details");
        AddGameZone.setVisible(false);
        ViewGameZone.setVisible(true);
        GameZoneTransaction.setVisible(false);
        EmployeeDetails.setVisible(false);
         
        viewgamezonepanel.setBackground(new Color(110, 89,222));
        AddGameZonepanel.setBackground(new Color(54, 33,89));
        TransactionDetailspanel.setBackground(new Color(54, 33,89));
        EmployeeDeatilsPanel.setBackground(new Color(54, 33,89)); 
        
        
        //display registered GameZone
        //Displaying All the Registered GameZones Into JTABLE
        DefaultTableModel m = (DefaultTableModel) jTable_Gamezone.getModel();
        m.setRowCount(0);
        DefaultTableModel  model = (DefaultTableModel) jTable_Gamezone.getModel();
        Object row[] = new Object[9];
        for(int i=0;i < GameZoneList.size();i++)
        {
        row[0] = GameZoneList.get(i).getID();
        row[1] = GameZoneList.get(i).getName(); 
        row[2] = GameZoneList.get(i).getOwnerName(); 
        row[3] = GameZoneList.get(i).getAddress();
        row[4] = GameZoneList.get(i).getContact();
        row[5] = GameZoneList.get(i).getPassword();
        row[6] = GameZoneList.get(i).getSubStartDate();
        row[7] = GameZoneList.get(i).getSubEndDate();
        row[8] = GameZoneList.get(i).getMax_Employee();
      
        model.addRow(row);
        
        
        

        }
        
    }//GEN-LAST:event_viewgamezonepanelMouseClicked

    private void TransactionDetailspanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TransactionDetailspanelMouseClicked

        // TODO add your handling code here:
         MainHeadingLabel.setText("GameZone Transaction details");
         AddGameZone.setVisible(false);
         ViewGameZone.setVisible(false);
         GameZoneTransaction.setVisible(true);
         EmployeeDetails.setVisible(false);
         
         TransactionDetailspanel.setBackground(new Color(110, 89,222));
         AddGameZonepanel.setBackground(new Color(54, 33,89));
         viewgamezonepanel.setBackground(new Color(54, 33,89));
         EmployeeDeatilsPanel.setBackground(new Color(54, 33,89)); 
       
      
  
       
    }//GEN-LAST:event_TransactionDetailspanelMouseClicked

    //Method for get transaction for perticular GameZone
    private void jComboBox_allgamezonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_allgamezonesActionPerformed
        // TODO add your handling code here:action event on conbo box
        
        return;
//        String GamezoneName = jComboBox_allgamezones.getSelectedItem().toString();
//        if(GamezoneName.equals("Select GameZone"))
//        {
//            return ;
//        }
//     
//      
//        //Background worker for getting the data from the server 
//        SwingWorker work = new SwingWorker<String , Integer>() {
//	            @Override
//	            protected  String  doInBackground() throws Exception 
//	            {
//	              
//                     try 
//                      {
//                         
//                      } 
//                     catch (Exception ex) 
//                      {
//                           System.out.println(ex);
//                           
//                      }
//                        //Arguments is table Name
//               
//                       
//                     
//                                          
//                        jDialog.dispose();
//                        return "end";
//	                
//	            }//do backgrounf ENDS
//
//
//	            @Override
//	            protected void done()
//                    {
//                        
//        
//	            }
//	        };
      //  final ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/Images/Loading.gif"));
        //work.execute();
        //JOptionPane pane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, icon,new Object[]{}, null);
        //jDialog = pane.createDialog(this,"Loading Date");
        //jDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      
        //jDialog.show();  this method is depricated there using setVisible method for showing the dialoge box 
        //jDialog.setVisible(true);
 
        

        
        
    }//GEN-LAST:event_jComboBox_allgamezonesActionPerformed

    private void EmployeeDeatilsPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EmployeeDeatilsPanelMouseClicked
        // TODO add your handling code here://Employee details panel onclick button
        
        MainHeadingLabel.setText("GameZone Employee Details");
        AddGameZone.setVisible(false);
        ViewGameZone.setVisible(false);
        GameZoneTransaction.setVisible(false);
        EmployeeDetails.setVisible(true);
         
        EmployeeDeatilsPanel.setBackground(new Color(110, 89,222));
        AddGameZonepanel.setBackground(new Color(54, 33,89));
        viewgamezonepanel.setBackground(new Color(54, 33,89));
        TransactionDetailspanel.setBackground(new Color(54, 33,89)); 
        
        
      
      
        
    }//GEN-LAST:event_EmployeeDeatilsPanelMouseClicked

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

    private void jComboBox1_selectgamezoneemployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1_selectgamezoneemployeeActionPerformed
        // TODO add your handling code here:Employee ComboBox Event
        
        String GamezoneName = jComboBox1_selectgamezoneemployee.getSelectedItem().toString();
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
                           UserInterface Dao = UserFactory.getInstance();
                           employelist  = Dao.getAllUsers(GamezoneName);
  
                       } 
                     catch (Exception ex) 
                      {
                           System.out.println(ex);
                           Logger.getLogger(Background_GetTransactionDetails.class.getName()).log(Level.SEVERE, null, ex);
                      }
                        //Arguments is table Name
               
                             
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
        DefaultTableModel m = (DefaultTableModel) jTable_Emploe.getModel();
        m.setRowCount(0);
        
        DefaultTableModel  model = (DefaultTableModel) jTable_Emploe.getModel();
        Object row[] = new Object[9];
        for(int i=0;i < employelist.size();i++)
        {
        row[0] = employelist.get(i).getID();
        row[1] = employelist.get(i).getName();
        row[2] = employelist.get(i).getAddress();
        row[3] = employelist.get(i).getContact();
        row[4] = employelist.get(i).getEmail();
        row[5] = employelist.get(i).getType();
        row[6] = employelist.get(i).getGameZoneID();
        row[7] = employelist.get(i).getPassword();
        row[8] = employelist.get(i).getUserName();
       
      
        model.addRow(row);
        }
        
        
        
    }//GEN-LAST:event_jComboBox1_selectgamezoneemployeeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    
        if(jComboBox_allgamezones.getSelectedItem().toString().equals("Select GameZone"))
        {
            JOptionPane.showMessageDialog(this,
            "Please select a gamezone.",
            "Inane error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
         if(jDateChooser_todayCollection_from.getDate() == null || jDateChooser_todayCollection_to.getDate() == null)
        {
            JOptionPane.showMessageDialog(this,
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
            gamelistper = Dao.GetGamesPerticularDateRange(jComboBox_allgamezones.getSelectedItem().toString(), date,date2);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,
            "Unable to Connect To Internet ,Please check your internet Connection.",
            "Inane error",
            JOptionPane.ERROR_MESSAGE);
            jLabel8.setText("No internet connection");
            return ;
        }
        if(gamelistper.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
            "No record for  this perticular date.",
            "Inane error",
            JOptionPane.ERROR_MESSAGE);
            jLabel8.setText("No records fouond for perticular date");
            return ;
        }
        else{
            DefaultTableModel m = (DefaultTableModel) jTable_transaction.getModel();
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
                DefaultTableModel  model = (DefaultTableModel) jTable_transaction.getModel();
                Object row[] = new Object[2];

                row[0] = key;
                row[1] = amountmapping.get(key);
               
                amount = amount + amountmapping.get(key);
                  
                model.addRow(row);
            
               }
              
        }
        
        
        jLabel8.setText("Today collection is "+amount);
        
        
        

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        gameNameSetting = new GameNameSetting(this, rootPaneCheckingEnabled);
        gameNameSetting.setVisible(true);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable_GamezoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_GamezoneMouseClicked
        // TODO add your handling code here:
        
        
        
        
        Stall stall = new Stall();
        int row = jTable_Gamezone.getSelectedRow();
           DefaultTableModel model = (DefaultTableModel) jTable_Gamezone.getModel();

        String ID = jTable_Gamezone.getModel().getValueAt(row, 0).toString();
        String Name = jTable_Gamezone.getModel().getValueAt(row, 1).toString();
        String OwnerName = jTable_Gamezone.getModel().getValueAt(row, 2).toString();
        String Address = jTable_Gamezone.getModel().getValueAt(row, 3).toString();
        String Contact = jTable_Gamezone.getModel().getValueAt(row, 4).toString();
        String Password = jTable_Gamezone.getModel().getValueAt(row, 5).toString();
        Date SubStartDate =(Date) jTable_Gamezone.getModel().getValueAt(row, 6);
        Date SubEndDate = (Date)jTable_Gamezone.getModel().getValueAt(row, 7);
        String MaxEmployee = jTable_Gamezone.getModel().getValueAt(row, 8).toString();

        stall.setID(Integer.parseInt(ID ));
        stall.setName(Name);
        stall.setOwnerName(OwnerName);
        stall.setContact(Contact);
        stall.setPassword(Password);
        stall.setSubStartDate(SubStartDate);
        stall.setSubEndDate(SubEndDate);
        stall.setMax_Employee(Integer.parseInt(MaxEmployee ));
        stall.setAddress(Address);

    
        
        int a=JOptionPane.showConfirmDialog(this,"Are you sure ,you want to delete "+Name);  
        if(a==JOptionPane.YES_OPTION)
        {  
//                 
//        Edit_DeleteGameZones edit_DeleteGameZones = new Edit_DeleteGameZones(this, rootPaneCheckingEnabled,stall);
//        edit_DeleteGameZones.setVisible(true);
            try
            {
           StallInterface Dao =  StallFactory.getInstance();
           Dao.DeleteGameZone(Integer.parseInt(ID ), Name);
            }
            catch(Exception e)
            {
             JOptionPane.showMessageDialog(this,
            "Unable to delete GameZone ,Please check your internet Connection." +e,
            "Inane error",
            JOptionPane.ERROR_MESSAGE);
             return;
                //Deletetion fail
            }
          
           
            JOptionPane.showMessageDialog(this,
            "GameZone Deleted",
            "success",
            JOptionPane.PLAIN_MESSAGE);
            model.removeRow(row);
        
         }  
        
    }//GEN-LAST:event_jTable_GamezoneMouseClicked

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
    private javax.swing.JPanel AddGameZone;
    private javax.swing.JPanel AddGameZonepanel;
    private javax.swing.JCheckBox Addgamescheckbox;
    private javax.swing.JPanel EmployeeDeatilsPanel;
    private javax.swing.JPanel EmployeeDetails;
    private javax.swing.JPanel GameZoneTransaction;
    private javax.swing.JLabel Label_clockadmin;
    private javax.swing.JLabel MainHeadingLabel;
    private javax.swing.JPanel SidePanel;
    private javax.swing.JPanel TopParentpanel;
    private javax.swing.JPanel TransactionDetailspanel;
    private javax.swing.JPanel ViewGameZone;
    private javax.swing.JLayeredPane WorkingScreen_layeredPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1_selectgamezoneemployee;
    private javax.swing.JComboBox<String> jComboBox_allgamezones;
    private javax.swing.JComboBox<String> jComboBox_sub;
    private com.toedter.calendar.JDateChooser jDateChooser_todayCollection_from;
    private com.toedter.calendar.JDateChooser jDateChooser_todayCollection_from1;
    private com.toedter.calendar.JDateChooser jDateChooser_todayCollection_to;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_OwnerName1;
    private javax.swing.JLabel jLabel_Sub;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable_Emploe;
    private javax.swing.JTable jTable_Gamezone;
    private javax.swing.JTable jTable_transaction;
    private javax.swing.JTextField jTextField_GameZoneAddress;
    private javax.swing.JTextField jTextField_GameZoneEmail;
    private javax.swing.JTextField jTextField_GameZoneName;
    private javax.swing.JTextField jTextField_GameZoneOwnerContact;
    private javax.swing.JTextField jTextField_GameZoneOwnerName;
    private javax.swing.JTextField jTextField_GameZoneOwnerPassword;
    private javax.swing.JTextField jTextField_GameZonemaxemployee;
    private javax.swing.JPanel viewgamezonepanel;
    // End of variables declaration//GEN-END:variables
}
