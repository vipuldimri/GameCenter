/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import gamecenter.Customers;
import gamecenter.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author u20o90
 */
public class CustomerImplementation implements CustomerInterface
{
    Connection conn;
    CustomerImplementation()
    {
        conn = Connect.getconnection();
    }

     
    @Override
    public boolean registerCust(Customers cust,String TableName) throws Exception
    {
        
        
        final String Register = "INSERT INTO `GameZone`.`"+TableName+"`(`Name`,`Contact`,`EmailId`)VALUES(?,?,?);";
        
             PreparedStatement pstmt = conn.prepareStatement(Register);
             pstmt.setString(1, cust.getName());
             pstmt.setString(2, cust.getContact());
             pstmt.setString(3, cust.getEmailId());
             pstmt.executeUpdate();
        
       return true;
    }

    @Override
    public boolean deleteCust(int ID,String TableName)throws Exception 
    {
       return true;
    }

    @Override
    public ArrayList<Customers> getCust(String TableName) throws Exception {
        ArrayList<Customers> custlist = new ArrayList<>();
        
        String Query ="SELECT * FROM GameZone."+TableName+";";
        Statement stmt=conn.createStatement();  
        ResultSet rs = stmt.executeQuery(Query);
        while(rs.next())  
        {
          Customers cust = new Customers(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
          custlist.add(cust);
        }
        
        return custlist;
    }

    @Override
    public ArrayList<Customers> getCust(String TableName, ArrayList<Customers> old ) throws Exception 
    {
       
        old.clear();
        String Query ="SELECT * FROM GameZone."+TableName+";";
        Statement stmt=conn.createStatement();  
        ResultSet rs = stmt.executeQuery(Query);
        while(rs.next())  
        {
          Customers cust = new Customers(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
          old.add(cust);
        }
        
        return old;
    }
    
}
