
package Database;
import static Database.Connect.conn;
import java.util.*;
import gamecenter.*;
import java.sql.ResultSet;
import java.sql.Statement;
public class StallImplements implements StallInterface
{
 static final String AddEmp = "INSERT INTO GameZoneDB.users (Name,Address,Contact,Email,Type,GameZoneID,Password) VALUES(?,?,?,?,?,?,?)";
 static final String GetGameZones = "SELECT * from GameZoneDB.gamezone;";
 
    public String Errormessage ;
    @Override
    public void AddGameZone(StallInterface stall)
    {
        /*
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
   
        */
    }

    @Override
    public GameZoneDetailsAndName GetAllGameZone() throws Exception
    {
                 GameZoneDetailsAndName details = new GameZoneDetailsAndName();
      
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(GetGameZones);
                   while(rs.next())  
                   {
                    Stall newstall = new Stall(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getDate(8));
                    details.GameZoneDetails.add(newstall);
                    details.GameZoneNames.add(rs.getString(2));
                    
                    
                   }
                   
                   Errormessage = "Success";
        
    
        
        return details;
     
    }

    @Override
    public ArrayList<Games> GetGames(String GameZoneName) throws Exception 
    {
    
        String Query="SELECT * FROM GameZoneDB."+GameZoneName+"_games;";
        ArrayList<Games> gameslist = new ArrayList<>();
        
         
      
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(Query);
                   while(rs.next())  
                   {
                    Games game = new Games(rs.getInt(1),rs.getString(2),rs.getString(3));
                  
                    gameslist.add(game);
                    
                   }
                   
                 
        
    
        
        return gameslist;
    }
    
}
