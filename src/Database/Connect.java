/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vipul
 */
public class Connect 
{
    Connection conn ;
    public Connect()
    {
        
              conn = null;
	       try
	       {
	           String url = "jdbc:mysql://localhost:3306/gamecenter";
	           Class.forName("com.mysql.jdbc.Driver");
	           conn = DriverManager.getConnection (url,"vipul","mysql");
	           System.out.println ("Database connection established");
	       }
	       catch (Exception e)
	       {
                   System.out.println("Error connection not Establish Please check your internet connection");
	           System.out.println(e.getMessage());
	       }
	       finally
	       {
	           
	       }
               
            
    }
    public Connection getconnection()
    {
        return conn;
    }  
 
}
