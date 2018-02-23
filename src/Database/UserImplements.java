
package Database;

import gamecenter.Stall;
import gamecenter.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserImplements implements UserInterface
{

    static final String GetAllUser = "SELECT * FROM gamecenter.users";
    static final String GetAllStalls = "SELECT * FROM gamecenter.gamezone;";
    Connection conn;
    UserImplements()
    {
         Connect connect = new Connect();
         conn = connect.getconnection();
    }
    
    @Override
    public ArrayList<User> getAllUsers() 
    {
        ArrayList<User> users = new ArrayList<>();
         try {
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(GetAllUser);
                   while(rs.next())  
                   {
                    //User newuser = new User();
                    //users.add(newuser);
                   }
        
        } catch (Exception ex) 
        {
                System.out.println("AAA "+ex);
        }
        return users;
    }

    @Override
    public ArrayList<Stall> getAllStall()
    {
      
     ArrayList<Stall> stalls = new ArrayList<>();
         try {
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(GetAllStalls);
                   while(rs.next())  
                   {
                    Stall newstall = new Stall();
                    stalls.add(newstall);
                   }
        
        } catch (Exception ex) 
        {
                System.out.println("AAA "+ex);
        }
        try {
            conn.close();
        } catch (Exception ex) 
        {
                System.out.println("BBB "+ex);
     
        }
        return stalls;
    }
    
}
