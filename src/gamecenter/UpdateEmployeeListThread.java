/*
This class is used to update employeelist in background thread
 */
package gamecenter;


import Database.TransactionFactory;
import Database.TransactionInterface;
import Database.UserFactory;
import Database.UserInterface;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
public class UpdateEmployeeListThread extends Thread
{
    ArrayList<User> employeelist;
    String GameZoneName;
     JDialog update;
    public UpdateEmployeeListThread(ArrayList<User> employeelist , String GameZoneName, JDialog update)
    {
        this.update=update;
        this.employeelist = employeelist;
        this.GameZoneName = GameZoneName;
    }
    @Override
    public void run()
    {
          final String Query ="";
          UserInterface Dao = null;
          try 
          {
         
             Dao = UserFactory.getInstance();
             employeelist  = Dao.getAllUsers(GameZoneName,employeelist);
           
          } 
           catch (Exception ex)
           {
           Logger.getLogger(Background_GetTransactionDetails.class.getName()).log(Level.SEVERE, null, ex);
           }
           
               
        update.dispose();
    }
    
}
