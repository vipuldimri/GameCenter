package gamecenter;
import java.util.ArrayList;
import Database.*;
import Gui.*;
import java.sql.Connection;
public class GettingUserThread extends Thread
{
   
    ArrayList<User> users;
    ArrayList<Stall> stalls;
    GettingUserThread()
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
