
package Database;
import gamecenter.Stall;

import gamecenter.User;
import java.util.ArrayList;
import java.util.HashMap;
public interface UserInterface 
{
    public ArrayList<User> getAllUsers(String GameZoneName)throws Exception;
    public ArrayList<User> getAllUsers(String GameZoneName,ArrayList<User> old)throws Exception;
    
    
    public Stall getGameZoneDetails(int GameZoneNameId)throws Exception;
    
    
    public boolean AddEmp(User user,String GameZoneName)throws Exception;
    
    public boolean UpdateEmp(User user,String GameZoneName)throws Exception;
   
    public boolean DeleteEmp(int id,String GameZoneName) throws Exception;
    
    
    public HashMap<String,Boolean> getUserNames(String GameZoneName)throws Exception;
        public HashMap<String,Boolean> getUserNames(String GameZoneName,HashMap<String,Boolean> old)throws Exception;

     public void ChangePassword(User user , String newpassword , String GameZoneName) throws Exception;
    
}
