package gamecenter;
import java.util.ArrayList;
import Database.*;
import Gui.*;
import java.sql.Connection;
public class BackGroundThread1 extends Thread
{
   
    ArrayList<User> users;
 
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
