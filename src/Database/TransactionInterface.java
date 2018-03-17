/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author vipul
 */
import java.util.ArrayList;
import gamecenter.Recharge;
public interface TransactionInterface 
{
    
    
    public void Recharge(Recharge rec,String TableName)throws Exception;
    public ArrayList<Recharge> GetTransactionDetails(String TableName)throws Exception;
    public ArrayList<Recharge> GetTransactionDetails(String TableName,ArrayList<Recharge> old)throws Exception;
}
