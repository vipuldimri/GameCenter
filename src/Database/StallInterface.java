
package Database;
import java.util.*;
import gamecenter.Stall;
import gamecenter.Games;
public interface StallInterface 
{
    
   // public void AddGameZone(StallInterface stall);
    public GameZoneDetailsAndName GetAllGameZone() throws Exception;
    
    public ArrayList<Games> GetGames(String GameZoneName)throws Exception;
    
    public String GetAmount(String TableName,String gamename)throws Exception;
    public boolean UpdateAmount(String TableName,String Amount,String GameName)throws Exception;
            
    public  Date GetCurrentDate()throws Exception;
}
