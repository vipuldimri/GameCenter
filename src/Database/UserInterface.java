
package Database;
import gamecenter.Stall;

import gamecenter.User;
import java.util.ArrayList;
public interface UserInterface 
{
    public ArrayList<User> getAllUsers(String GameZoneName)throws Exception;
    public ArrayList<User> getAllUsers(String GameZoneName,ArrayList<User> old)throws Exception;
    
    
    public Stall getGameZoneDetails(int GameZoneNameId)throws Exception;
    
    
    public boolean AddEmp(User user,String GameZoneName)throws Exception;
    
    public boolean UpdateEmp(User user,String GameZoneName)throws Exception;
   
    public boolean DeleteEmp(int id,String GameZoneName) throws Exception;
}
