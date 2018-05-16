package Database;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect 
{
    public static Connection conn ;
    public Connect()throws Exception
    {
        
                   conn = null;
	           String url =  "jdbc:mysql://gamezone.cn406nlqr1z7.us-east-2.rds.amazonaws.com:3306/GameZoneDB";
	           Class.forName("com.mysql.jdbc.Driver");
	           conn = DriverManager.getConnection (url,"vipuldimri","gamezone");
	           System.out.println ("Database connection established");

    }
    public static Connection getconnection()
    {
        return conn;
    }  
 
}
