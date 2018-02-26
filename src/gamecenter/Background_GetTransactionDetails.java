/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecenter;

import Database.TransactionFactory;
import Database.TransactionInterface;
import java.util.ArrayList;

/**
 *
 * @author vipul
 */
public class Background_GetTransactionDetails extends  Thread
{
   public ArrayList<Recharge> transactiondetails;
   public Background_GetTransactionDetails()
   {
       transactiondetails = new ArrayList<>();
   }
    public void run()
    {
         TransactionInterface Dao = TransactionFactory.getInstance();
         transactiondetails = Dao.GetTransactionDetails("");
    }
    
}
