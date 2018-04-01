
/*
class to update password check so that no to employee can have same username
*/
package gamecenter;
import Database.UserFactory;
import Database.UserInterface;
import java.util.HashMap;
public class UpdatePasswordCheck extends Thread
{
    
    String GameZoneName;
    HashMap<String , Boolean> passwordcheck;
    
    public UpdatePasswordCheck(String GameZoneName , HashMap<String,Boolean> passwordcheck)
    {
        this.GameZoneName = GameZoneName;
        this.passwordcheck = passwordcheck;
    }
    @Override
    public void run()
    {
            try{
                
            
            UserInterface Dao   = UserFactory.getInstance();
            passwordcheck = Dao.getUserNames(GameZoneName,passwordcheck);
             }
            catch(Exception e)
            {
                System.out.println(e);
            }
    }
    
}
