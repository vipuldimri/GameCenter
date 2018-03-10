package gamecenter;
import java.util.ArrayList;
import Database.*;
import Gui.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
Thread for gtting users  and stall (GameZone ) information from the database .

*/
public class BackGroundThread1 extends Thread
{
   
    //This list contains all the users (login users admin + emp)
    ArrayList<User> users;
 
    //All gamezone data and their Endsub details 
    Stalls_and_SubDate   stalls; 
    BackGroundThread1()
    {

       
        users = new ArrayList<>();
        stalls = new Stalls_and_SubDate();
    }
    @Override
    public void run()
    {
   
         try {
            Connect con = new Connect();
        } catch (Exception ex) {
            Logger.getLogger(BackGroundThread1.class.getName()).log(Level.SEVERE, null, ex);
        }
        try 
        {
            UserInterface Dao   = UserFactory.getInstance();
            users = Dao.getAllUsers();
            stalls = Dao.getAllStall();
        } catch (Exception ex) 
        {
          
            System.out.println("ERROR "+ex);
           
        }   
        
    }
    
}
