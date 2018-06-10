
package Database;
import java.util.*;
import gamecenter.Stall;
import gamecenter.Games;
public interface StallInterface 
{
    
    public void AddGameZone(Stall stall)throws Exception;
    public GameZoneDetailsAndName GetAllGameZone() throws Exception;
    
    public ArrayList<Games> GetGames(String GameZoneName)throws Exception;
    
    public String GetAmount(String TableName,String gamename)throws Exception;
    public boolean UpdateAmount(String TableName,String Amount,String GameName)throws Exception;
            
    public  Date GetCurrentDate()throws Exception;
    
    public ArrayList<Games> GetGamesPerticularDate(String GameZoneName,Date date)throws Exception;
    
      
    public ArrayList<Games> GetGamesPerticularDateRange(String GameZoneName,Date startdate,Date Enddate)throws Exception;
    
    public void DeleteGameZone(int ID ,String Name)throws Exception;
    
    public void AddNewGames(String GameZoneName ,String Game)throws Exception;
    
}
