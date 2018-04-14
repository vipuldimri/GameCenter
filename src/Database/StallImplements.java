
package Database;
import static Database.Connect.conn;
import java.util.*;
import gamecenter.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class StallImplements implements StallInterface
{
 static final String AddEmp = "INSERT INTO GameZoneDB.users (Name,Address,Contact,Email,Type,GameZoneID,Password) VALUES(?,?,?,?,?,?,?)";
 static final String GetGameZones = "SELECT * from GameZoneDB.gamezone;";
 
    public String Errormessage ;
 
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
                    Stall newstall = new Stall(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getDate(8),rs.getString(9),rs.getInt(10),rs.getInt(11));
                    details.GameZoneDetails.add(newstall);
                    details.GameZoneNames.add(rs.getString(2));
                    
                    
                   }
                   
                   Errormessage = "Success";
        
    
        
        return details;
     
    }

    @Override
    public ArrayList<Games> GetGames(String GameZoneName) throws Exception 
    {
        String Query="SELECT * FROM GameZoneDB."+GameZoneName+"_games  WHERE Date = curdate();";
        ArrayList<Games> gameslist = new ArrayList<>();
        Statement stmt=conn.createStatement();  
        ResultSet rs = stmt.executeQuery(Query);
                   
                   while(rs.next())  
                   {
                    Games game = new Games(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4));
                    gameslist.add(game);
                   }
               //Code for new Day    
               if(gameslist.isEmpty() == true)
               {                
                  //Query for Creating new entry for a day 
                  String Creatingnewentry = "SELECT DISTINCT  Amount from "+GameZoneName+"_games ;";
                  ArrayList<String> GamesAvailable = new ArrayList<>();
                  Statement stmt3=conn.createStatement();  
                  ResultSet rs2 = stmt.executeQuery(Creatingnewentry);
                  while(rs2.next())  
                  {
                  GamesAvailable.add(rs2.getString(1));
                  }  
                  
                  for(String gamename : GamesAvailable)
                  {
                      
                      
                  }
               
               }else
               {
                   //Return If Current Date and no need to change the dater and Time 
                   return gameslist;
               }
                 //Now getting data
                 Statement stmt2=conn.createStatement();  
                 ResultSet rs2 = stmt.executeQuery(Query);
                 
                 while(rs.next())  
                 {
                    Games game = new Games(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4));
                    gameslist.add(game);
                 }

        return gameslist;
    }

    @Override
    public String GetAmount(String GameZoneName,String gamename) throws Exception 
    {
                //string Query="SELECT * FROM GameZoneDB."+GameZoneName+"_games  WHERE Date = curdate();";
                  String queryforgettingoldamount = "SELECT Amount from "+GameZoneName+"_games WHERE GameName = '"+gamename+"';";

                   String amount = "";
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(queryforgettingoldamount);
                   while(rs.next())  
                   {
                    amount = rs.getString(1);
                 
                   }
                   return amount;
    }

    @Override
    public boolean UpdateAmount(String GameZoneName, String Amount,String GameName) throws Exception 
    {
            final String UpdateAmount = "UPDATE "+GameZoneName+"_games SET Amount = '"+Amount+"' WHERE GameName = '"+GameName+"' AND Date = curdate()";
            PreparedStatement pstmt = conn.prepareStatement(UpdateAmount);
            pstmt.executeUpdate();
        
             return true;
       
    }

    @Override
    public  Date GetCurrentDate() throws Exception 
    {
        String Query_getCurrentDatefromAmazoneDatabase="select curdate();";
        
        Statement stmt=conn.createStatement();  
        ResultSet rs = stmt.executeQuery(Query_getCurrentDatefromAmazoneDatabase);
                
                   java.util.Date current_date =null;
                   while(rs.next())  
                   {
                    current_date = rs.getDate(1);
                   }
                   
        return current_date;
    }
    
}
