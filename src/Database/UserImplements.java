
package Database;

import gamecenter.Stall;
import gamecenter.User;
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

    
    static  final String AddEmp = "INSERT INTO GameZoneDB.users (Name,Address,Contact,Email,Type,GameZoneID,Password) VALUES(?,?,?,?,?,?,?)";
    Connection conn;
    UserImplements()throws Exception
    {
        
         conn = Connect.getconnection();
    }
    
    @Override
    public ArrayList<User> getAllUsers(String GameZoneName) 
    {
    
        final String GetAllUser = "SELECT * FROM GameZoneDB."+GameZoneName+"_users";
        ArrayList<User> users = new ArrayList<>();
         try {
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(GetAllUser);
                   while(rs.next())  
                   {
                    User newuser = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9));
                    users.add(newuser);
                   }
        
        } catch (Exception ex) 
        {
                System.out.println("AAA "+ex);
        }
        return users;
    }

    @Override
    public Stall getGameZoneDetails(int GameZoneID)
    {
        
         final String Query = "SELECT * FROM GameZoneDB.gamezone where ID = 1;";
        
         Stall currentgamezone = null ;
         /*
         try {
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(Query);
                   while(rs.next())  
                   {
                    currentgamezone = new Stall(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getDate(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13));
                   
                    //End date for every stall corres to their ID
                   }
        
        } catch (Exception ex) 
        {
                System.out.println("AAA "+ex);
        }
    */
        return currentgamezone;

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
   
        
   
        return true;
    }

    @Override
    public boolean UpdateEmp(User user)throws Exception
    {
        String  query = "UPDATE `GameZoneDB`.`users` SET `Name` = ?,`Address` = ?,`Contact` = ?,`Email` = ?,`Type` =? ,`GameZoneID` = ?,`Password` =? WHERE `ID` = ?;";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getAddress());
        pstmt.setString(3, user.getContact());
        pstmt.setString(4, user.getEmail());
        pstmt.setString(5, user.getType());
        pstmt.setInt(6, user.getGameZoneID());
        pstmt.setString(7, user.getPassword());
        pstmt.setInt(8, user.getID());

        pstmt.executeUpdate();
        return true;
        
    }

    @Override
    public boolean DeleteEmp(int id) throws Exception
    {
        String query = "DELETE FROM `GameZoneDB`.`users` WHERE ID = ?;";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, id);


        pstmt.executeUpdate();
        
        
              return true;
    }
    
}
