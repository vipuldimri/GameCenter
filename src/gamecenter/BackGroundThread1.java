package gamecenter;
import java.util.ArrayList;
import Database.*;
import Gui.*;
import java.sql.Connection;
public class BackGroundThread1 extends Thread
{
   
    ArrayList<User> users;
    ArrayList<Stall> stalls;
    BackGroundThread1()
    {
        users = new ArrayList<>();
    }
    public void run()
    {
   
         UserInterface Dao   = UserFactory.getInstance();
         users = Dao.getAllUsers();
         stalls = Dao.getAllStall();
        
        
        
    }
    
}
