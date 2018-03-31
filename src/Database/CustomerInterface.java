/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author u20o90
 */
import gamecenter.Customers;
import java.util.ArrayList;
public interface CustomerInterface 
{
    public boolean registerCust(Customers cust,String TableName)throws Exception;
    public boolean deleteCust(int ID,String TableName)throws Exception;
    public ArrayList<Customers> getCust(String TableName)throws Exception;
    public ArrayList<Customers> getCust(String TableName,ArrayList<Customers> old)throws Exception;
}
