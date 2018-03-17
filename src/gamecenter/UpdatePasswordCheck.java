/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecenter;

/**
 *
 * @author vipul
 */
import Database.UserFactory;
import Database.UserInterface;
import java.util.HashMap;
public class UpdatePasswordCheck extends Thread
{
    
    String GameZoneName;
    HashMap<String , Boolean> passwordcheck;
    
    public UpdatePasswordCheck(String GameZoneName , HashMap<String , Boolean> passwordcheck)
    {
        this.GameZoneName = GameZoneName;
        this.passwordcheck = passwordcheck;
    }
    @Override
    public void run()
    {
            try{
                
            
            UserInterface Dao   = UserFactory.getInstance();
            passwordcheck = Dao.getUserNames("GameZone1",passwordcheck);
             }
            catch(Exception e)
            {
                System.out.println(e);
            }
    }
    
}
