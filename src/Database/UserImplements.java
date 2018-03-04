
package Database;

import gamecenter.Stall;
import gamecenter.User;
import gamecenter.Stalls_and_SubDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserImplements implements UserInterface
{

    static final String GetAllUser = "SELECT * FROM GameZoneDB.users";
    static final String GetAllStalls = "SELECT * FROM GameZoneDB.gamezone;";
    static  final String AddEmp = "INSERT INTO GameZoneDB.users (Name,Address,Contact,Email,Type,GameZoneID,Password) VALUES(?,?,?,?,?,?,?)";
    Connection conn;
    UserImplements()throws Exception
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
                    Stall newstall = new Stall(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getDate(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13));
                    obj.stalls.add(newstall);
                    obj.subdate.put(rs.getInt(1), rs.getDate(8));
                    obj.stallIdandName.put(rs.getInt(1),rs.getString(2) );
                    obj.PasswordCheck.put(rs.getString(2), rs.getString(8));
                    //End date for every stall corres to their ID
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

    @Override
    public boolean AddEmp(User user) 
    {
        System.out.println("Inside ADD");
         
        try {
           
            PreparedStatement pstmt = conn.prepareStatement(AddEmp);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getAddress());
            pstmt.setString(3, user.getContact());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getType());
            pstmt.setInt(6, user.getGameZoneID());
            pstmt.setString(7, user.getPassword());
            pstmt.executeUpdate();
           
           
           
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
   
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Inserted");
        
        return true;
    }
    
}
