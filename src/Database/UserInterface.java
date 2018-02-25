
package Database;
import gamecenter.Stall;
import gamecenter.Stalls_and_SubDate;
import gamecenter.User;
import java.util.ArrayList;
public interface UserInterface 
{
    public ArrayList<User> getAllUsers();
    public Stalls_and_SubDate getAllStall();
    public boolean AddEmp(User user);
    
}
