/*
this class is used to updatetransaction details in background thread
 */
package gamecenter;
import java.util.ArrayList;
public class UpdateTransactionListThread extends Thread
{
    
    ArrayList<Recharge> transactiondetails;
    public UpdateTransactionListThread(ArrayList<Recharge> transactiondetails)
    {
        this.transactiondetails = transactiondetails;
    }
    @Override
    public void run()
    {
        
    }
    
}
