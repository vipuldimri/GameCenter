/*
this class is used to update customer list in background thread
*/
package gamecenter;

import Database.CustomerInterface;
import Database.Customerfactory;
import Database.TransactionFactory;
import Database.TransactionInterface;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class UpdateCustomerListThread extends Thread
{
    ArrayList<Customers> customerlist;
    String GameZoneName;
    public UpdateCustomerListThread( ArrayList<Customers> customerlist,String GameZoneName)
    {
        this.customerlist = customerlist;
        this.GameZoneName = GameZoneName;
    }
    
    @Override
    public void run()
    {
          final String Query ="";
          CustomerInterface Dao = null;
          try 
          {
         
             Dao = Customerfactory.getInstance();
             String TransactionTableName = GameZoneName+"_customers";
             customerlist  = Dao.getEmp(TransactionTableName);
           
          } 
          catch (Exception ex)
           {
           Logger.getLogger(Background_GetTransactionDetails.class.getName()).log(Level.SEVERE, null, ex);
           }
           
               
        
        
    }
    
}
