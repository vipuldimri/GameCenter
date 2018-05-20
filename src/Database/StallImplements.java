
package Database;
import static Database.Connect.conn;
import java.util.*;
import gamecenter.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class StallImplements implements StallInterface
{
 
   static final String GetGameZones = "SELECT * from GameZoneDB.gamezone;";
 
    public String Errormessage ;
 
   @Override
    public void AddGameZone(Stall stall)throws Exception
    {
            final String AddGameZone = "insert  into gamezone(Address,Contact,Email,OwnerName,Max_Employee,Name,SubStartDate,Password,SubEndDate) VALUES(?,?,?,?,?,?,?,?,?)";
                       
            PreparedStatement pstmt = conn.prepareStatement(AddGameZone);
            pstmt.setString(1,stall.getAddress() );
            pstmt.setString(2, stall.getContact());
            pstmt.setString(3, stall.getEmail());
            pstmt.setString(4, stall.getOwnerName());
            pstmt.setInt(5, stall.getMax_Employee());
            pstmt.setString(6, stall.getName());
            pstmt.setDate(7, stall.getSubStartDate());
            pstmt.setString(8, stall.getPassword());
            pstmt.setDate(9, stall.getSubEndDate());
            pstmt.executeUpdate();
           
            
            //Adding to gamezone table conpleted
           
           
           
       
   
        
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
                  String Creatingnewentry = "select MAX(Date) from "+GameZoneName+"_games ;";
                  java.sql.Date maxdate = null;
                  ArrayList<String> GamesAvailable = new ArrayList<>();
                  Statement stmt3=conn.createStatement();  
                  ResultSet rs2 = stmt.executeQuery(Creatingnewentry);
                  while(rs2.next())  
                  {
                   maxdate = rs2.getDate(1);
                  }  
                  long millis=System.currentTimeMillis();  
                  java.sql.Date currentdate=new java.sql.Date(millis);    
                  if(maxdate.before(currentdate))
                  {
                  //create new entry  select DISTINCT(GameName) from GameZone2_games;
                  String getallgames = "select DISTINCT(GameName) from "+GameZoneName+"_games ;";
                  Statement stmt4=conn.createStatement();  
                  ResultSet rs3 = stmt.executeQuery(getallgames);
                  while(rs3.next())  
                  {
                   GamesAvailable.add(rs3.getString(1));
                  }
                  
                  for(String gamename : GamesAvailable)
                  {
                   final String addgamerow = "INSERT INTO "+GameZoneName+"_games (GameName,Amount,Date) VALUES ('"+gamename+"','0',curdate());";
                   PreparedStatement pstmt = conn.prepareStatement(addgamerow);
                   pstmt.executeUpdate();
        
                      
                  }
                  
                  
                  }else
                  {
                      
                  }
                
               
               }else
               {
                   //Return If Current Date and no need to change the dater and Time 
                   return gameslist;
               }
                 //Now getting data
                 Statement stmt2=conn.createStatement();  
                 ResultSet rs4 = stmt.executeQuery(Query);
                 
                 while(rs4.next())  
                 {
                    Games game = new Games(rs4.getInt(1),rs4.getString(2),rs4.getString(3),rs4.getDate(4));
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

    @Override
    public ArrayList<Games> GetGamesPerticularDate(String GameZoneName, Date date) throws Exception {
       
        java.sql.Date d = new java.sql.Date(date.getTime());
        String Query="SELECT * FROM GameZoneDB."+GameZoneName+"_games  WHERE Date = '"+d+"';";
        
        ArrayList<Games> gameslist = new ArrayList<>();
        Statement stmt=conn.createStatement();  
        ResultSet rs = stmt.executeQuery(Query);
                   
                   while(rs.next())  
                   {
                    Games game = new Games(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4));
                    gameslist.add(game);
                   }
                   
                   
                   return gameslist;
    }

    @Override
    public ArrayList<Games> GetGamesPerticularDateRange(String GameZoneName, Date startdate, Date Enddate) throws Exception 
    {
        java.sql.Date sdate = new java.sql.Date(startdate.getTime());
        java.sql.Date edate = new java.sql.Date(Enddate.getTime());
        
        
        String Query="SELECT * FROM GameZoneDB."+GameZoneName+"_games  WHERE Date >= '"+sdate+"' AND Date <= '"+edate+"';";
       System.out.println(Query);
        ArrayList<Games> gameslist = new ArrayList<>();
        Statement stmt=conn.createStatement();  
        ResultSet rs = stmt.executeQuery(Query);
                   
                   while(rs.next())  
                   {
                    Games game = new Games(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4));
                    gameslist.add(game);
                   }
                   
                   
                   return gameslist;
       
    }

    @Override
    public void DeleteGameZone(int ID, String Name) throws Exception 
    {
       String customer = "DROP TABLE "+Name+"_customers";
       String users = "DROP TABLE "+Name+"_users";
       String trasaction = "DROP TABLE "+Name+"_transaction";
       String othertrasaction = "DROP TABLE "+Name+"_other_transaction";
       String FromGameZone = "DELETE FROM gamezone where ID = "+ID;
       String games ="DROP TABLE "+Name+"_games";
       
       
       
        
      PreparedStatement preparedStmt = conn.prepareStatement(customer);
      preparedStmt.execute();
      
       preparedStmt = null;
      preparedStmt = conn.prepareStatement(users);
      preparedStmt.execute();
      
       preparedStmt = null;
      preparedStmt = conn.prepareStatement(trasaction);
      preparedStmt.execute();
      
      preparedStmt = null;
       preparedStmt = conn.prepareStatement(othertrasaction);
      preparedStmt.execute();
    
      preparedStmt = null;
       preparedStmt = conn.prepareStatement(games);
      preparedStmt.execute();
      
      preparedStmt = null;
       preparedStmt = conn.prepareStatement(FromGameZone);
      preparedStmt.execute();
    }
    
}
