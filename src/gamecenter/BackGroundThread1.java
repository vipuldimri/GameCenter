package gamecenter;
import java.util.ArrayList;
import Database.*;
import Gui.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Properties;
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
    boolean confiflag = false;
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
            String Gamezonename = "";
            String Id="";
            try 
		{
			Properties prop2 = new Properties();
			prop2.load(new FileInputStream("c://stark//config.properties"));
			Id = prop2.getProperty("ID");
                        Gamezonename = prop2.getProperty("gamezonename");
		
		}catch(Exception eta)
                {
		   error_flag = true;
                   confiflag = true;
		}
            UserInterface Dao   = UserFactory.getInstance();
            users = Dao.getAllUsers(Gamezonename);   ///change this according to gamezone
            //above lines gets all the users for current GameZone;
            int id =  Integer.parseInt(Id);
            currentgamezone = Dao.getGameZoneDetails(id) ; //chnage this according to gamezone id;
            
            passwordcheck = Dao.getUserNames(Gamezonename); //chnage this according to gamezone
            
        } 
        catch (Exception ex) 
        {
           error_flag =true;
           System.out.println("ERROR ");
           System.exit(0);
           
        }   
        
    }
    
}
