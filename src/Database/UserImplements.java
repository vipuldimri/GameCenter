
package Database;

import gamecenter.Stall;
import gamecenter.User;
import gamecenter.Stalls_and_SubDate;
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
                    User newuser = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8));
                    users.add(newuser);
                   }
        
        } catch (Exception ex) 
        {
                System.out.println("AAA "+ex);
        }
        return users;
    }

    @Override
    public Stalls_and_SubDate getAllStall()
    {
         Stalls_and_SubDate obj = new Stalls_and_SubDate();
         try {
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(GetAllStalls);
                   while(rs.next())  
                   {
                    Stall newstall = new Stall(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getDate(8));
                    obj.stalls.add(newstall);
                    obj.subdate.put(rs.getInt(1), rs.getDate(8));
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
        return obj;
    }
    
}
