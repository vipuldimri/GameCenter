package gamecenter;
import java.util.ArrayList;
import Database.*;
import Gui.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
/*
Thread for gtting users  and stall (GameZone ) information from the database .

*/
public class BackGroundThread1 extends Thread
{
   
    //This list contains all the users (login users admin + emp)
    ArrayList<User> users;
 
    //All gamezone data and their Endsub details 
    Stall currentgamezone;
    
    HashMap<String, Boolean> passwordcheck;
    
    boolean error_flag = false;
    BackGroundThread1()
    {

              
    }
    @Override
    public void run()
    {
   
        try 
        {
            Connect con = new Connect();
        } 
        catch (Exception ex) 
        {
            System.out.println("Error Connectiing");
            error_flag= true;
         
            return ;
        }
        try 
        {
            UserInterface Dao   = UserFactory.getInstance();
            users = Dao.getAllUsers("GameZone2");   ///change this according to gamezone
            //above lines gets all the users for current GameZone;
            currentgamezone = Dao.getGameZoneDetails(2) ; //chnage this according to gamezone id;
            
            passwordcheck = Dao.getUserNames("GameZone2"); //chnage this according to gamezone
            
        } 
        catch (Exception ex) 
        {
           error_flag =true;
           System.out.println("ERROR ");
           
        }   
        
    }
    
}
