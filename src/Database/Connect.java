package Database;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect 
{
    public static Connection conn ;
    public Connect()throws Exception
    {
        
                   conn = null;
                   String url =  "jdbc:mysql://gamezone.cw0b5ff1vv8q.us-east-2.rds.amazonaws.com:3306/GameZone";
	            Class.forName("com.mysql.jdbc.Driver");
	           conn = DriverManager.getConnection (url,"vasant","gamezone");
	           System.out.println ("Database connection established");

    }
    public static Connection getconnection()
    {
        return conn;
    }  
 
}
