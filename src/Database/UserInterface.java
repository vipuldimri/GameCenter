
package Database;
import gamecenter.Stall;

import gamecenter.User;
import java.util.ArrayList;
public interface UserInterface 
{
    public ArrayList<User> getAllUsers(String GameZoneName);
    public Stall getGameZoneDetails(String GameZoneName);
    public boolean AddEmp(User user);
    
    public boolean UpdateEmp(User user)throws Exception;
   
    public boolean DeleteEmp(int id) throws Exception;
}
