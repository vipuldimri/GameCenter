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
public interface CustomerInterface 
{
    public boolean registerEmp(Customers cust);
    public boolean deleteEmp(int ID);
    
}
