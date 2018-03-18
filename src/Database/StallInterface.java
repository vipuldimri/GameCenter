
package Database;
import java.util.*;
import gamecenter.Stall;
import gamecenter.Games;
public interface StallInterface 
{
    
    public void AddGameZone(StallInterface stall);
    public GameZoneDetailsAndName GetAllGameZone() throws Exception;
    
    public ArrayList<Games> GetGames(String GameZoneName)throws Exception;
 
 
            
    
}
