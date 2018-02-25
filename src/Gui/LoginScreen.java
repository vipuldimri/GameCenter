
package Gui;
import gamecenter.Stall;
import gamecenter.User;
import gamecenter.Stalls_and_SubDate;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
public class LoginScreen extends javax.swing.JFrame 
{
    //All users from  user table
    ArrayList<User> users;
    Stalls_and_SubDate stalls;
   
    
    
    
    HashMap<Integer, String> passwordcheck;
    HashMap<Integer, String> stallMap;
    
    
    
    
    Stall currentstall;
   
    
    User currentuser;
    ArrayList<User> currentStallUsers;

    
    public LoginScreen(ArrayList<User> users,Stalls_and_SubDate stalls)
    {
    this.users = users;
    this.stalls = stalls;
    initComponents();
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    setLocation(x, y);
    
    
     currentStallUsers = new ArrayList<>();
     currentstall = new Stall();
     currentuser = new User();
     
    
    
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(12, 13, 695, 642);

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
        jPanel1.add(jButton1);
        jButton1.setBounds(860, 450, 270, 80);

        jLabel3.setText("Forgot Pasword");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(860, 540, 90, 16);

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusGained(evt);
            }
        });
        jPanel1.add(jPasswordField1);
        jPasswordField1.setBounds(860, 340, 230, 60);

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("*");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(860, 410, 230, 16);

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(860, 270, 230, 16);

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
        // TODO add your handling code here:Login button Event
        String username = jTextField1.getText();
        char[] password = jPasswordField1.getPassword();
        String pass ="";
        for(int i = 0 ; i <password.length ; i ++)
        {
            pass = pass + password[i];
        }

     
       long millis=System.currentTimeMillis();  
       java.sql.Date currentdate=new java.sql.Date(millis);  
       String cur_date =currentdate.toString();
       String Year = cur_date.substring(0,4);
       String Month = cur_date.substring(5,7);
       String Day = cur_date.substring(8);
       
        
       
        
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
     
        boolean flagUser = false;
        boolean flagAdmin = false;
        boolean flagSub = false;
        int gamezoneid ;
        
        for(User u : users)
        {
         
            if(u.getName().equals(username) && u.getPassword().equals(pass))
            {
                
                currentuser.setID(u.getID());
                currentuser.setAddress(u.getAddress());
                currentuser.setContact(u.getContact());
                
                currentuser.setEmail(u.getEmail());
                currentuser.setName(u.getName());
                currentuser.setPassword(u.getPassword());
                currentuser.setType(u.getType());
                currentuser.setGameZoneID(u.getGameZoneID()); 
                
                System.out.println(u.getName()  +"    "+username);     
                System.out.println(u.getPassword()+"    "+pass);
                
                
                flagUser = true;
                gamezoneid = u.getGameZoneID();
                java.sql.Date sub_enddate = stalls.subdate.get(gamezoneid);
                
                System.out.println(cur_date);
                System.out.println(sub_enddate);
                
                
                
                String s =sub_enddate.toString();
                String end_Year = s.substring(0,4);
                String end_Month = s.substring(5,7);
                String end_Day = s.substring(8);
                
                    int y = 0 ;
                    int m =0;
                    int d = 0;
                    int e_y = 0;
                    int e_m = 0;
                    int e_d = 0;
                
                try
                {
                     y = Integer.parseInt(Year);
                     m = Integer.parseInt(Month);
                     d = Integer.parseInt(Day);
                     e_y = Integer.parseInt(end_Year);
                     e_m = Integer.parseInt(end_Month);
                     e_d = Integer.parseInt(end_Day);
                              
                    
                }catch(NumberFormatException e)
                {
                    System.out.println(e);
                    
                }
                
                
                //code for checking sub is valid or not 
                if(y > e_y )
                {
                    JOptionPane.showMessageDialog(jPanel1,
                     "Your Subscription has Expired Plz Contact The service Provider.",
                     "Inane error",
                      JOptionPane.ERROR_MESSAGE);
                
                     return ;
                    
                }
                if( m > e_m && y == e_y )
                {
                    JOptionPane.showMessageDialog(jPanel1,
                     "Your Subscription has Expired Plz Contact The service Provider.",
                     "Inane error",
                      JOptionPane.ERROR_MESSAGE);
                
                     return ;
                    
                }
                if(   d > e_d  &&  y == e_y &&   m == e_m)
                {
                    JOptionPane.showMessageDialog(jPanel1,
                     "Your Subscription has Expired Plz Contact The service Provider.",
                     "Inane error",
                      JOptionPane.ERROR_MESSAGE);
                
                     return ;
                    
                }
                
                
                
               
              
                 
                    
                if(u.getType().equals("admin"))
                {
                  
                    flagAdmin = true;
                }
                break;
            }
            
        }
            
        
        
          
        
        if(flagAdmin || flagUser)
        {
            if(flagAdmin)
            {
              
                
                for (User u : users) 
                {
                  if(u.getGameZoneID() == currentuser.getGameZoneID())
                  {
                      currentStallUsers.add(u);
                      
                  }
                    
                }
                jTextField1.setText("");
                jPasswordField1.setText("");
                new MainScreen_StallOwner("admin",this,currentuser,currentStallUsers).setVisible(true);
                setVisible(false);
            }else{
                 //here currentStallUsers is null because emp has no rights to see emps
                jTextField1.setText("");
                jPasswordField1.setText("");
                 new MainScreen_StallOwner(this,currentuser,currentStallUsers).setVisible(true);
                setVisible(false);
            }
            
        }
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
