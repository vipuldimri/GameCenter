
package Database;

import gamecenter.Stall;
import gamecenter.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserImplements implements UserInterface
{

    
   
    Connection conn;
    UserImplements()throws Exception
    {
        
         conn = Connect.getconnection();
    }
    
    @Override
    public ArrayList<User> getAllUsers(String GameZoneName) throws Exception
    {
    
        final String GetAllUser = "SELECT * FROM GameZoneDB."+GameZoneName+"_users";
        ArrayList<User> users = new ArrayList<>();
        
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(GetAllUser);
                   while(rs.next())  
                   {
                    User newuser = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9));
                    users.add(newuser);
                   }
        
      
        return users;
    }

    @Override
    public Stall getGameZoneDetails(int GameZoneID)
    {
        
         final String Query = "SELECT * FROM GameZoneDB.gamezone where ID = "+GameZoneID+";";
        
         Stall currentgamezone = null ;
         
         try {
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(Query);
                   while(rs.next())  
                   {
                    currentgamezone = new Stall(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getDate(8),rs.getString(9),rs.getInt(10),rs.getInt(11));
                   
                    //End date for every stall corres to their ID
                   }
        
        } catch (Exception ex) 
        {
                System.out.println("AAA "+ex);
        }
        
        return currentgamezone;

    }

    @Override
    public boolean AddEmp(User user,String GameZoneName) throws Exception
    {
             try{
            final String AddEmp = "INSERT INTO GameZoneDB."+GameZoneName+"_users (Name,Address,Contact,Email,Type,GameZoneID,Password,UserName) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(AddEmp);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getAddress());
            pstmt.setString(3, user.getContact());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getType());
            pstmt.setInt(6, user.getGameZoneID());
            pstmt.setString(7, user.getPassword());
            pstmt.setString(8, user.getUserName());
            pstmt.executeUpdate();
             }
             catch(Exception e)
             {
                 System.out.println(e);
             }
  
        return true;
    }

    @Override
    public boolean UpdateEmp(User user,String GameZoneName)throws Exception
    {
        
        String q ="UPDATE `GameZoneDB`.`"+GameZoneName+"_users"+"` \n" +
"SET \n" +
"`Name` =? ,\n" +
"`Address` =? ,\n" +
"`Contact` =? ,\n" +
"`Email` =? ,\n" +
"`Type` =? \n" +
",`GameZoneID` = ? ,`Password` =? ,\n" +
"`UserName` = ? \n" +
"WHERE `ID` =? ;";    

             
               PreparedStatement pstmt = conn.prepareStatement(q);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getAddress());
        pstmt.setString(3, user.getContact());
        pstmt.setString(4, user.getEmail());
        pstmt.setString(5, user.getType());
        pstmt.setInt(6, user.getGameZoneID());
        pstmt.setString(7, user.getPassword());
        pstmt.setString(8, user.getUserName());
        pstmt.setInt(9, user.getID());
       

        pstmt.executeUpdate();
   
        return true;
        
    }

    @Override
    public boolean DeleteEmp(int id,String GameZoneName) throws Exception
    {
        String query = "DELETE FROM `GameZoneDB`.`"+GameZoneName+"_users"+"` WHERE ID = ?;";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, id);


        pstmt.executeUpdate();
        return true;
    }

    @Override
    public ArrayList<User> getAllUsers(String GameZoneName, ArrayList<User> old)throws Exception
    {
        final String GetAllUser = "SELECT * FROM GameZoneDB."+GameZoneName+"_users";
      
                   old.clear();
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(GetAllUser);
                   while(rs.next())  
                   {
                    User newuser = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9));
                    old.add(newuser);
                   }
        
       
        return old;
      
    }

    @Override
    public HashMap<String, Boolean> getUserNames(String GameZoneName) throws Exception 
    {
          final String GetAllUser = "SELECT * FROM GameZoneDB."+GameZoneName+"_users";
          HashMap<String,Boolean> passwordcheck = new HashMap<>();
        
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(GetAllUser);
                   while(rs.next())  
                   {
                  
                    passwordcheck.put(rs.getString(9),true);
                   }
        
      
        return passwordcheck;
    }
        @Override
    public HashMap<String, Boolean> getUserNames(String GameZoneName,HashMap<String,Boolean> old) throws Exception 
    {
          old.clear();
          final String GetAllUser = "SELECT * FROM GameZoneDB."+GameZoneName+"_users";
          
        
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(GetAllUser);
                   while(rs.next())  
                   {
                  
                    old.put(rs.getString(9),true);
                   }
        
      
        return old;
    }
    
}
