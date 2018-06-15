package Database;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect 
{
    public static Connection conn ;
    public Connect()throws Exception
    {
        
                   conn = null;
                   String urlnew ="jdbc:mysql://gamezone2.cn406nlqr1z7.us-east-2.rds.amazonaws.com:3306/GameZoneDB";
	           String url =  "jdbc:mysql://gamezone.cn406nlqr1z7.us-east-2.rds.amazonaws.com:3306/GameZoneDB";
	           Class.forName("com.mysql.jdbc.Driver");
	           conn = DriverManager.getConnection (urlnew,"vipuldimri","gamezone");
	           System.out.println ("Database connection established");

    }
    public static Connection getconnection()
    {
        return conn;
    }  
 
}
