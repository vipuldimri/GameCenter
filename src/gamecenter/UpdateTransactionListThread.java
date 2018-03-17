/*
this class is used to updatetransaction details in background thread
 */
package gamecenter;
import Database.TransactionFactory;
import Database.TransactionInterface;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class UpdateTransactionListThread extends Thread
{
    
    ArrayList<Recharge> transactiondetails;
    String GameZoneName;
    public UpdateTransactionListThread(ArrayList<Recharge> transactiondetails,String GameZoneName)
    {
        this.transactiondetails = transactiondetails;
        this.GameZoneName = GameZoneName;
    }
    @Override
    public void run()
    {
          final String Query ="";
          TransactionInterface Dao = null;
          try 
          {
         
             Dao = TransactionFactory.getInstance();
             String TransactionTableName = GameZoneName+"_transaction";
             transactiondetails  = Dao.GetTransactionDetails(TransactionTableName);
           
          } 
          catch (Exception ex)
           {
           Logger.getLogger(Background_GetTransactionDetails.class.getName()).log(Level.SEVERE, null, ex);
           }
           
               
           
      
    }
    
}
