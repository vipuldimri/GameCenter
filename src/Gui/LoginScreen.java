
package Gui;

import Database.StallFactory;
import Database.StallInterface;
import gamecenter.Stall;
import gamecenter.User;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;
import SerialCommunication.FactoryClass;
import gamecenter.SendEmailThread;

/*
Login frame Class
*/
public class LoginScreen extends javax.swing.JFrame 
{
    //All users from  user table
    ArrayList<User> currentgamezoneusers;
    Stall currentgamezone;
    User currentuser;
    HashMap<String,Boolean> passwordcheck;
    
    
    
    public LoginScreen(ArrayList<User> currentgamezoneusers,Stall currentgamezone,HashMap<String,Boolean> passwordcheck)
    {
    //getting data from thread
    this.currentgamezoneusers = currentgamezoneusers;
    this.currentgamezone = currentgamezone;
    this.passwordcheck = passwordcheck;
    //init components 
    initComponents();
    
    //Displaying the screen in the center of the screen
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    setLocation(x, y);
    
    currentuser = new User();
        
    
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1208, 668));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(153, 0, 0));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Follow Us on Facebook");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Facebook.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Contact Us  - 9999999999");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/StarkLogo.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(265, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(23, 23, 23)))
                .addComponent(jLabel9)
                .addGap(22, 22, 22))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(12, 13, 693, 642);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("User Name");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(860, 130, 110, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Password");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(860, 300, 120, 22);

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(860, 200, 230, 60);

        jButton1.setBackground(new java.awt.Color(153, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Login.png"))); // NOI18N
        jButton1.setText("Log In");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(860, 450, 270, 80);

        jLabel3.setText("Forgot Pasword");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3);
        jLabel3.setBounds(860, 540, 110, 30);

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusGained(evt);
            }
        });
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        jPanel1.add(jPasswordField1);
        jPasswordField1.setBounds(860, 340, 230, 60);

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("*");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(860, 410, 230, 14);

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(860, 270, 230, 14);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/showpassword.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel6MouseReleased(evt);
            }
        });
        jPanel1.add(jLabel6);
        jLabel6.setBounds(1100, 360, 32, 30);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(jLayeredPane1);
        jLayeredPane1.setBounds(860, 500, 100, 100);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1208, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here: Login button Event 

        String username = jTextField1.getText();
        char[] password = jPasswordField1.getPassword();
        String pass ="";
        for(int i = 0 ; i <password.length ; i ++)
        {
            pass = pass + password[i];
        }

     
        //Checking if admin login
        if(username.equals("admin") && pass.equals("admin@admin"))
        {
            MainScreen_Admin admin = new MainScreen_Admin();
            admin.setVisible(true);
            setVisible(false);
            return;
        
        }
        
        
        //Getting current time and then converting it into String for easy comparision
       long millis=System.currentTimeMillis();  
       java.sql.Date currentdate=new java.sql.Date(millis);
       System.out.println("current time is "+currentdate);
       
            java.util.Date DateAmazone = null;
         try
         {

         StallInterface Dao= StallFactory.getInstance();
        DateAmazone= Dao.GetCurrentDate();
        
         }
         catch(Exception e)
         {
                     JOptionPane.showMessageDialog(jPanel1,
                     "Unable to Connect To Internet ,Please check your internet Connection.",
                     "Inane error",
                      JOptionPane.ERROR_MESSAGE);
                      return ;
         }
         
        
       String cur_date =currentdate.toString();
       String Year = cur_date.substring(0,4);
       String Month = cur_date.substring(5,7);
       String Day = cur_date.substring(8);
       // year , month , day represent current time   
        
       
        //Validation 
        if(username.length() == 0)
        {  
            jLabel5.setText("UserName is Required");
            return ;
        }
        if(password.length == 0)
        {  
            jLabel4.setText("Password is Required");
           return ;
        }
     
        //flag used for login
        boolean flagUser = false;
        boolean flagAdmin = false;
        boolean flagSub = false;
        int gamezoneid ;
        
        
        // traversing users list to check password and user name exits or not ;
        for(User u : currentgamezoneusers)
        {
         
            if(u.getUserName().equals(username) && u.getPassword().equals(pass))
            {
                
                // if user name and password match storing the info of logedin user into currentuser
                currentuser.setID(u.getID());
                currentuser.setAddress(u.getAddress());
                currentuser.setContact(u.getContact());
                currentuser.setEmail(u.getEmail());
                currentuser.setName(u.getName());
                currentuser.setPassword(u.getPassword());
                currentuser.setType(u.getType());
                currentuser.setGameZoneID(u.getGameZoneID()); 
                currentuser.setUserName(u.getUserName());
                
                System.out.println(u.getName()  +"    "+username);     
                System.out.println(u.getPassword()+"    "+pass);
                
                
                flagUser = true;
              
               //code for getting Game zone
               //Now getting current stall Endsub data and converting it into String to check if sub is valid or not for the gamezone
                gamezoneid = u.getGameZoneID();
          
            
                //Checking for GameZoneSubcription
                java.sql.Date sub_enddate = currentgamezone.getSubEndDate();
                //Code for checking the subcription for the GameZone
                if(DateAmazone.after(sub_enddate))
                {
                 JOptionPane.showMessageDialog(jPanel1,
                 "Your GameZone Subcription Has Expired On "+sub_enddate+" , Please Contact Admin For Renewals",
                 "Inane error",
                  JOptionPane.ERROR_MESSAGE);
                   return;
           
                }
                //checking if current user is admin or emp    
                if(u.getType().equals("admin"))
                {
                   flagAdmin = true;
                }
                break;
            }
            
        }
            
        
        
          
        
        if(flagAdmin || flagUser)
        {
            // if current user is admin
            if(flagAdmin)
            {
             
                jTextField1.setText("");
                jPasswordField1.setText("");
              
          
           MainScreen_StallOwner mainScreen_StallOwner =  new MainScreen_StallOwner("admin",this,currentgamezoneusers,currentgamezone,currentuser,passwordcheck); 
           mainScreen_StallOwner.setVisible(true);
           setVisible(false);
           //Below code is for Serial communication to start    
           try 
           {
           
               FactoryClass.createObjects(mainScreen_StallOwner);
    
           } catch (ClassNotFoundException ex)
           {
                 JOptionPane.showMessageDialog(jPanel1,
                 "Error in Connecting Hardware.",
                 "Inane error",
                 JOptionPane.ERROR_MESSAGE);
            
           }
           
           
           
            }
            else
            {
                 //if current user is normal employee
                 //here currentStallUsers is null because emp has no rights to see emps
                  jTextField1.setText("");
                  jPasswordField1.setText("");
                   MainScreen_StallOwner mainScreen_StallOwner =  new MainScreen_StallOwner(this,currentgamezone,currentuser);
                      mainScreen_StallOwner.setVisible(true);
                      setVisible(false);
                
                  /////////////////////Code for serial communication//////////
                   try 
                   {
                    FactoryClass.createObjects(mainScreen_StallOwner);
    
                   } catch (ClassNotFoundException ex)
                   {
                       JOptionPane.showMessageDialog(jPanel1,
                        "Error in Connecting Hardware.",
                       "Inane error",
                         JOptionPane.ERROR_MESSAGE);
            
                   }
              
                  
            }
            
        }
        //No user found with provided usernmae and password
        else
        {
            JOptionPane.showMessageDialog(jPanel1,
           "Invalid Password or UserName.",
           "Inane error",
            JOptionPane.ERROR_MESSAGE);
           
        }
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        // TODO add your handling code here:focus on username
        jLabel4.setText("");
        jLabel5.setText("");
    }//GEN-LAST:event_jTextField1FocusGained

    private void jPasswordField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusGained
        // TODO add your handling code here: on password
            jLabel4.setText("");
        jLabel5.setText("");
    }//GEN-LAST:event_jPasswordField1FocusGained

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
        // TODO add your handling code here:
        
        
        System.out.println(evt.getKeyCode());
    }//GEN-LAST:event_jPanel1KeyPressed

    //Below two function is for showpassword peek button
    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        // TODO add your handling code here:show password mouse enter
        
        jPasswordField1.setEchoChar((char) 0);
    }//GEN-LAST:event_jLabel6MousePressed

    private void jLabel6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseReleased
        // TODO add your handling code here:show password mouse released
        
         jPasswordField1.setEchoChar('*');
    }//GEN-LAST:event_jLabel6MouseReleased

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:Key presses for Enter Button
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        { 
  String username = jTextField1.getText();
        char[] password = jPasswordField1.getPassword();
        String pass ="";
        for(int i = 0 ; i <password.length ; i ++)
        {
            pass = pass + password[i];
        }

     
        //Checking if admin login
        if(username.equals("admin") && pass.equals("admin@admin"))
        {
            MainScreen_Admin admin = new MainScreen_Admin();
            admin.setVisible(true);
            setVisible(false);
            return;
        
        }
        
        
        //Getting current time and then converting it into String for easy comparision
       long millis=System.currentTimeMillis();  
       java.sql.Date currentdate=new java.sql.Date(millis);
       System.out.println("current time is "+currentdate);
       
            java.util.Date DateAmazone = null;
         try
         {

         StallInterface Dao= StallFactory.getInstance();
        DateAmazone= Dao.GetCurrentDate();
        
         }
         catch(Exception e)
         {
                     JOptionPane.showMessageDialog(jPanel1,
                     "Unable to Connect To Internet ,Please check your internet Connection.",
                     "Inane error",
                      JOptionPane.ERROR_MESSAGE);
                      return ;
         }
         
        
       String cur_date =currentdate.toString();
       String Year = cur_date.substring(0,4);
       String Month = cur_date.substring(5,7);
       String Day = cur_date.substring(8);
       // year , month , day represent current time   
        
       
        //Validation 
        if(username.length() == 0)
        {  
            jLabel5.setText("UserName is Required");
            return ;
        }
        if(password.length == 0)
        {  
            jLabel4.setText("Password is Required");
           return ;
        }
     
        //flag used for login
        boolean flagUser = false;
        boolean flagAdmin = false;
        boolean flagSub = false;
        int gamezoneid ;
        
        
        // traversing users list to check password and user name exits or not ;
        for(User u : currentgamezoneusers)
        {
         
            if(u.getUserName().equals(username) && u.getPassword().equals(pass))
            {
                
                // if user name and password match storing the info of logedin user into currentuser
                currentuser.setID(u.getID());
                currentuser.setAddress(u.getAddress());
                currentuser.setContact(u.getContact());
                currentuser.setEmail(u.getEmail());
                currentuser.setName(u.getName());
                currentuser.setPassword(u.getPassword());
                currentuser.setType(u.getType());
                currentuser.setGameZoneID(u.getGameZoneID()); 
                currentuser.setUserName(u.getUserName());
                
                System.out.println(u.getName()  +"    "+username);     
                System.out.println(u.getPassword()+"    "+pass);
                
                
                flagUser = true;
              
               //code for getting Game zone
               //Now getting current stall Endsub data and converting it into String to check if sub is valid or not for the gamezone
                gamezoneid = u.getGameZoneID();
          
            
                //Checking for GameZoneSubcription
                java.sql.Date sub_enddate = currentgamezone.getSubEndDate();
                //Code for checking the subcription for the GameZone
                if(DateAmazone.after(sub_enddate))
                {
                      JOptionPane.showMessageDialog(jPanel1,
                      "Your GameZone Subcription Has Expired On "+sub_enddate+" , Please Contact Admin For Renewals",
                      "Inane error",
                       JOptionPane.ERROR_MESSAGE);
                      return;
           
                }
                //checking if current user is admin or emp    
                if(u.getType().equals("admin"))
                {
                   flagAdmin = true;
                }
                break;
            }
            
        }
            
        
        
          
        
        if(flagAdmin || flagUser)
        {
            // if current user is admin
            if(flagAdmin)
            {
             
                jTextField1.setText("");
                jPasswordField1.setText("");
              
          
           MainScreen_StallOwner mainScreen_StallOwner =  new MainScreen_StallOwner("admin",this,currentgamezoneusers,currentgamezone,currentuser,passwordcheck); 
           mainScreen_StallOwner.setVisible(true);
           setVisible(false);
           //Below code is for Serial communication to start    
           try 
           {
            FactoryClass.createObjects(mainScreen_StallOwner);
    
           } catch (ClassNotFoundException ex)
           {
                 
                    System.out.println("Factory class exception "+ex);
            
           }
           
           
           
            }
            else
            {
                 //if current user is normal employee
                 //here currentStallUsers is null because emp has no rights to see emps
                  jTextField1.setText("");
                  jPasswordField1.setText("");
                   MainScreen_StallOwner mainScreen_StallOwner =  new MainScreen_StallOwner(this,currentgamezone,currentuser);
                      mainScreen_StallOwner.setVisible(true);
                      setVisible(false);
                
                  /////////////////////Code for serial communication//////////
                   try 
                   {
                    FactoryClass.createObjects(mainScreen_StallOwner);
    
                   } catch (ClassNotFoundException ex)
                   {
                 
                    System.out.println("Factory class exception "+ex);
            
                   }
              
                  
            }
            
        }
        //No user found with provided usernmae and password
        else
        {
            JOptionPane.showMessageDialog(jPanel1,
           "Invalid Password or UserName.",
           "Inane error",
            JOptionPane.ERROR_MESSAGE);
           
        }
        
        } 
        
        
    }//GEN-LAST:event_jButton1KeyPressed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        { 
                 String username = jTextField1.getText();
        char[] password = jPasswordField1.getPassword();
        String pass ="";
        for(int i = 0 ; i <password.length ; i ++)
        {
            pass = pass + password[i];
        }

     
        //Checking if admin login
             if(username.equals("admin") && pass.equals("admin@admin"))
        {
            MainScreen_Admin admin = new MainScreen_Admin();
            admin.setVisible(true);
            setVisible(false);
            return;
        
        }
        
        
        //Getting current time and then converting it into String for easy comparision
       long millis=System.currentTimeMillis();  
       java.sql.Date currentdate=new java.sql.Date(millis);
       System.out.println("current time is "+currentdate);
       
            java.util.Date DateAmazone = null;
         try
         {

         StallInterface Dao= StallFactory.getInstance();
        DateAmazone= Dao.GetCurrentDate();
        
         }
         catch(Exception e)
         {
                     JOptionPane.showMessageDialog(jPanel1,
                     "Unable to Connect To Internet ,Please check your internet Connection.",
                     "Inane error",
                      JOptionPane.ERROR_MESSAGE);
                      return ;
         }
         
        
       String cur_date =currentdate.toString();
       String Year = cur_date.substring(0,4);
       String Month = cur_date.substring(5,7);
       String Day = cur_date.substring(8);
       // year , month , day represent current time   
        
       
        //Validation 
        if(username.length() == 0)
        {  
            jLabel5.setText("UserName is Required");
            return ;
        }
        if(password.length == 0)
        {  
            jLabel4.setText("Password is Required");
           return ;
        }
     
        //flag used for login
        boolean flagUser = false;
        boolean flagAdmin = false;
        boolean flagSub = false;
        int gamezoneid ;
        
        
        // traversing users list to check password and user name exits or not ;
        for(User u : currentgamezoneusers)
        {
         
            if(u.getUserName().equals(username) && u.getPassword().equals(pass))
            {
                
                // if user name and password match storing the info of logedin user into currentuser
                currentuser.setID(u.getID());
                currentuser.setAddress(u.getAddress());
                currentuser.setContact(u.getContact());
                currentuser.setEmail(u.getEmail());
                currentuser.setName(u.getName());
                currentuser.setPassword(u.getPassword());
                currentuser.setType(u.getType());
                currentuser.setGameZoneID(u.getGameZoneID()); 
                currentuser.setUserName(u.getUserName());
                
                System.out.println(u.getName()  +"    "+username);     
                System.out.println(u.getPassword()+"    "+pass);
                
                
                flagUser = true;
              
               //code for getting Game zone
               //Now getting current stall Endsub data and converting it into String to check if sub is valid or not for the gamezone
                gamezoneid = u.getGameZoneID();
          
            
                //Checking for GameZoneSubcription
                java.sql.Date sub_enddate = currentgamezone.getSubEndDate();
                //Code for checking the subcription for the GameZone
                if(DateAmazone.after(sub_enddate))
                {
                      JOptionPane.showMessageDialog(jPanel1,
                      "Your GameZone Subcription Has Expired On "+sub_enddate+" , Please Contact Admin For Renewals",
                      "Inane error",
                       JOptionPane.ERROR_MESSAGE);
                      return;
           
                }
                //checking if current user is admin or emp    
                if(u.getType().equals("admin"))
                {
                   flagAdmin = true;
                }
                break;
            }
            
        }
            
        
        
          
        
        if(flagAdmin || flagUser)
        {
            // if current user is admin
            if(flagAdmin)
            {
             
                jTextField1.setText("");
                jPasswordField1.setText("");
              
          
           MainScreen_StallOwner mainScreen_StallOwner =  new MainScreen_StallOwner("admin",this,currentgamezoneusers,currentgamezone,currentuser,passwordcheck); 
           mainScreen_StallOwner.setVisible(true);
           setVisible(false);
           //Below code is for Serial communication to start    
           try 
           {
            FactoryClass.createObjects(mainScreen_StallOwner);
    
           } catch (ClassNotFoundException ex)
           {
                 
                    System.out.println("Factory class exception "+ex);
            
           }
           
           
           
            }
            else
            {
                 //if current user is normal employee
                 //here currentStallUsers is null because emp has no rights to see emps
                  jTextField1.setText("");
                  jPasswordField1.setText("");
                   MainScreen_StallOwner mainScreen_StallOwner =  new MainScreen_StallOwner(this,currentgamezone,currentuser);
                      mainScreen_StallOwner.setVisible(true);
                      setVisible(false);
                
                  /////////////////////Code for serial communication//////////
                   try 
                   {
                    FactoryClass.createObjects(mainScreen_StallOwner);
    
                   } catch (ClassNotFoundException ex)
                   {
                 
                    System.out.println("Factory class exception "+ex);
            
                   }
              
                  
            }
            
        }
        //No user found with provided usernmae and password
        else
        {
            JOptionPane.showMessageDialog(jPanel1,
           "Invalid Password or UserName.",
           "Inane error",
            JOptionPane.ERROR_MESSAGE);
           
        }
         
   
        } 
        
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        { 
            //Same Code as Of Login Button
              String username = jTextField1.getText();
        char[] password = jPasswordField1.getPassword();
        String pass ="";
        for(int i = 0 ; i <password.length ; i ++)
        {
            pass = pass + password[i];
        }

     
        //Checking if admin login
        if(username.equals("admin") && pass.equals("admin@admin"))
        {
            MainScreen_Admin admin = new MainScreen_Admin();
            admin.setVisible(true);
            setVisible(false);
            return;
        
        }
        
        
        //Getting current time and then converting it into String for easy comparision
       long millis=System.currentTimeMillis();  
       java.sql.Date currentdate=new java.sql.Date(millis);
       System.out.println("current time is "+currentdate);
       
            java.util.Date DateAmazone = null;
         try
         {

         StallInterface Dao= StallFactory.getInstance();
        DateAmazone= Dao.GetCurrentDate();
        
         }
         catch(Exception e)
         {
                     JOptionPane.showMessageDialog(jPanel1,
                     "Unable to Connect To Internet ,Please check your internet Connection.",
                     "Inane error",
                      JOptionPane.ERROR_MESSAGE);
                      return ;
         }
         
        
       String cur_date =currentdate.toString();
       String Year = cur_date.substring(0,4);
       String Month = cur_date.substring(5,7);
       String Day = cur_date.substring(8);
       // year , month , day represent current time   
        
       
        //Validation 
        if(username.length() == 0)
        {  
            jLabel5.setText("UserName is Required");
            return ;
        }
        if(password.length == 0)
        {  
            jLabel4.setText("Password is Required");
           return ;
        }
     
        //flag used for login
        boolean flagUser = false;
        boolean flagAdmin = false;
        boolean flagSub = false;
        int gamezoneid ;
        
        
        // traversing users list to check password and user name exits or not ;
        for(User u : currentgamezoneusers)
        {
         
            if(u.getUserName().equals(username) && u.getPassword().equals(pass))
            {
                
                // if user name and password match storing the info of logedin user into currentuser
                currentuser.setID(u.getID());
                currentuser.setAddress(u.getAddress());
                currentuser.setContact(u.getContact());
                currentuser.setEmail(u.getEmail());
                currentuser.setName(u.getName());
                currentuser.setPassword(u.getPassword());
                currentuser.setType(u.getType());
                currentuser.setGameZoneID(u.getGameZoneID()); 
                currentuser.setUserName(u.getUserName());
                
                System.out.println(u.getName()  +"    "+username);     
                System.out.println(u.getPassword()+"    "+pass);
                
                
                flagUser = true;
              
               //code for getting Game zone
               //Now getting current stall Endsub data and converting it into String to check if sub is valid or not for the gamezone
                gamezoneid = u.getGameZoneID();
          
            
                //Checking for GameZoneSubcription
                java.sql.Date sub_enddate = currentgamezone.getSubEndDate();
                //Code for checking the subcription for the GameZone
                if(DateAmazone.after(sub_enddate))
                {
                      JOptionPane.showMessageDialog(jPanel1,
                      "Your GameZone Subcription Has Expired On "+sub_enddate+" , Please Contact Admin For Renewals",
                      "Inane error",
                       JOptionPane.ERROR_MESSAGE);
                      return;
           
                }
                //checking if current user is admin or emp    
                if(u.getType().equals("admin"))
                {
                   flagAdmin = true;
                }
                break;
            }
            
        }
            
        
        
          
        
        if(flagAdmin || flagUser)
        {
            // if current user is admin
            if(flagAdmin)
            {
             
                jTextField1.setText("");
                jPasswordField1.setText("");
              
          
           MainScreen_StallOwner mainScreen_StallOwner =  new MainScreen_StallOwner("admin",this,currentgamezoneusers,currentgamezone,currentuser,passwordcheck); 
           mainScreen_StallOwner.setVisible(true);
           setVisible(false);
           //Below code is for Serial communication to start    
           try 
           {
            FactoryClass.createObjects(mainScreen_StallOwner);
    
           } catch (ClassNotFoundException ex)
           {
                 
                    System.out.println("Factory class exception "+ex);
            
           }
           
           
           
            }
            else
            {
                 //if current user is normal employee
                 //here currentStallUsers is null because emp has no rights to see emps
                  jTextField1.setText("");
                  jPasswordField1.setText("");
                   MainScreen_StallOwner mainScreen_StallOwner =  new MainScreen_StallOwner(this,currentgamezone,currentuser);
                      mainScreen_StallOwner.setVisible(true);
                      setVisible(false);
                
                  /////////////////////Code for serial communication//////////
                   try 
                   {
                    FactoryClass.createObjects(mainScreen_StallOwner);
    
                   } catch (ClassNotFoundException ex)
                   {
                 
                    System.out.println("Factory class exception "+ex);
            
                   }
              
                  
            }
            
        }
        //No user found with provided usernmae and password
        else
        {
            JOptionPane.showMessageDialog(jPanel1,
           "Invalid Password or UserName.",
           "Inane error",
            JOptionPane.ERROR_MESSAGE);
           
        }
        
   
        } 
        
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:Forget password code here
        String username = jTextField1.getText().trim();
        if(username.length()==0)
        {
             
                       JOptionPane.showMessageDialog(jPanel1,
                     "Please provide username",
                     "Inane error",
                      JOptionPane.ERROR_MESSAGE);
                      return ;
        }
       
        
        for(User employee : currentgamezoneusers)
        {
            if(employee.getUserName().trim().equalsIgnoreCase(username))
            {
                String ans =JOptionPane.showInputDialog(jPanel1,"Enter Your Registered Phone No ");
                if(ans.trim().equalsIgnoreCase(employee.getContact()))
                {
                    //work done here 
  
                    SendEmailThread sendEmailThread = new SendEmailThread(employee.getEmail(),employee.getPassword(),employee.getName(),currentgamezone.getName());
                    sendEmailThread.start();
                    JOptionPane.showMessageDialog(jPanel1,
                    "Password will be sent to registered E-mail address ",
                    "Recover Password",
                    JOptionPane.PLAIN_MESSAGE);
                    return ;
                }
                return;
            }
        }
        
        
                     
        
       
                     JOptionPane.showMessageDialog(jPanel1,
                     "No Employee Found for this UserName",
                     "Inane error",
                      JOptionPane.ERROR_MESSAGE);
                
        
        
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              //  new LoginScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
