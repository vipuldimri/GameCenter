package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Connect 
{
    Connection conn ;
    public Connect()throws Exception
    {
        
                   conn = null;
	           String url =  "jdbc:mysql://gamezone.cn406nlqr1z7.us-east-2.rds.amazonaws.com:3306/GameZoneDB";
	           Class.forName("com.mysql.jdbc.Driver");
	           conn = DriverManager.getConnection (url,"vipuldimri","gamezone");
	           System.out.println ("Database connection established");
        
       
	 
	       
               
            
    }
    public Connection getconnection()
    {
        return conn;
    }  
 
}
