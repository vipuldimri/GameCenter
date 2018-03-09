/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import gamecenter.Customers;

/**
 *
 * @author u20o90
 */
public class CustomerImplementation implements CustomerInterface
{
    

     
    @Override
    public boolean registerEmp(Customers cust,String TableName) 
    {
        
        
        final String Register = "INSERT INTO "+TableName+"() VALUES()";
        
        
        
       return true;
    }

    @Override
    public boolean deleteEmp(int ID,String TableName) 
    {
       return true;
    }
    
}
