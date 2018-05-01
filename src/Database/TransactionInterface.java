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

import gamecenter.OtherTrasactions;
import java.util.ArrayList;
import gamecenter.Recharge;
public interface TransactionInterface 
{
    
    
    public void Recharge(Recharge rec,String TableName)throws Exception;
    public void RechargeOtherMethod(OtherTrasactions rec,String TableName)throws Exception;
    
    
    public ArrayList<Recharge> GetTransactionDetails(String TableName)throws Exception;
    public ArrayList<Recharge> GetTransactionDetails(String TableName,ArrayList<Recharge> old)throws Exception;
    public ArrayList<Recharge> GetTransactionDetailsPerticularDates(String TableName,java.sql.Date Start ,java.sql.Date End)throws Exception;
    public ArrayList<OtherTrasactions> GetOthertrasactions(String TabName)throws Exception;
    public ArrayList<OtherTrasactions> GetOthertrasactions(String TableName,ArrayList<OtherTrasactions> old)throws Exception;
    public ArrayList<OtherTrasactions> GetOthertrasactionsPerticularDates(String TableName,java.sql.Date Start ,java.sql.Date End)throws Exception;
}
