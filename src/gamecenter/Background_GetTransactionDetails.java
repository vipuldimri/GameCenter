/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecenter;

import Database.TransactionFactory;
import Database.TransactionInterface;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/*

This class is for test purpose not used in the project
*/
public class Background_GetTransactionDetails extends  Thread
{
   public ArrayList<Recharge> transactiondetails;
   CountDownLatch StopGUIrefresh;
   String TableName;
   public String ErrorMessage = "OK" ;
   public Background_GetTransactionDetails(CountDownLatch StopGUIrefresh,String TableName)
   {
       this.StopGUIrefresh = StopGUIrefresh;
       transactiondetails = new ArrayList<>();
       this.TableName = TableName;
   }
    public Background_GetTransactionDetails(String TableName)
    {
     
       transactiondetails = new ArrayList<>();
       this.TableName = TableName;
    }
    public void run()
    {
          TransactionInterface Dao = null;
         try {
           Dao = TransactionFactory.getInstance();
           } catch (Exception ex)
           {
           Logger.getLogger(Background_GetTransactionDetails.class.getName()).log(Level.SEVERE, null, ex);
           }
           //Arguments is table Name
               
            String TransactionTableName = TableName+"_transaction";
       try {
           transactiondetails  = Dao.GetTransactionDetails(TransactionTableName);
       } catch (Exception ex)
       {
           System.out.println("ERROR IN THREAD "+ex);
           ErrorMessage = "TransactionError";
          
       }
        
       StopGUIrefresh.countDown();
        
    }
    
}
