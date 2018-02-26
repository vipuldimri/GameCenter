package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Connect 
{
    Connection conn ;
    public Connect()
    {
        
              conn = null;
	       try
	       {
                   String test = "";
	           String url = "jdbc:mysql://gamezone.cn406nlqr1z7.us-east-2.rds.amazonaws.com:3306/GameZoneDB";
	           Class.forName("com.mysql.jdbc.Driver");
	           conn = DriverManager.getConnection (url,"vipuldimri","gamezone");
	           System.out.println ("Database connection established");
	       }
	       catch (Exception e)
	       {
                   System.out.println("Error connection not Establish Please check your internet connection");
	           System.out.println(e.getMessage());
	       }
	       finally
	       {
	           
	       }
               
            
    }
    public Connection getconnection()
    {
        return conn;
    }  
 
}
