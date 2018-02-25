package gamecenter;
import java.util.ArrayList;
import Database.*;
import Gui.*;
import java.sql.Connection;
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
    public void run()
    {
   
         UserInterface Dao   = UserFactory.getInstance();
         users = Dao.getAllUsers();
         stalls = Dao.getAllStall();
        
        
        
    }
    
}
