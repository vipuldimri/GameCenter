/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import gamecenter.Recharge;
import gamecenter.Stall;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author vipul
 */
public class TransactionImplementation implements TransactionInterface 
{
    
    static  String Rech = "";
    static String TransDetails = "";
    Connection conn;
    TransactionImplementation()throws Exception
    {

         conn = Connect.getconnection();
    }
    
    @Override
    public void Recharge(gamecenter.Recharge rec, String TableName) throws Exception
    {
        try{
            
        
           Rech = "INSERT INTO "+TableName+" (CardNo,EmpName,Amount,Date)VALUES(?,?,?,?)";
          
            PreparedStatement pstmt = conn.prepareStatement(Rech);
            pstmt.setString(1, rec.getCardNo());
            pstmt.setString(2, rec.getEmpName());
            pstmt.setInt(3,rec.getAmount());
            pstmt.setTimestamp(4, rec.getDate());
         
          
            pstmt.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }

    @Override
    public ArrayList<Recharge> GetTransactionDetails(String TableName)throws Exception
    {
        
        ArrayList<Recharge> transactiondetails = new ArrayList<>();
        
        TransDetails = "SELECT * FROM GameZoneDB."+TableName+" WHERE MONTH(Date) = MONTH(CURRENT_DATE()) AND YEAR(Date) = YEAR(CURRENT_DATE()) ORDER BY  Date desc" ;
        
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(TransDetails);
                   while(rs.next())  
                   {
                       Recharge rec = new Recharge();
                       rec.setID(rs.getInt(1));
                       rec.setEmpName(rs.getString(3));
                       rec.setCardNo(rs.getString(2));
                       rec.setDate(rs.getTimestamp(5));
                       rec.setAmount(rs.getInt(4));
              
                       transactiondetails.add(rec);
                  
                   }
        
        
           
  
        return transactiondetails;
        
    }

    @Override
    public ArrayList<Recharge> GetTransactionDetails(String TableName, ArrayList<Recharge> old) throws Exception 
    {
           
                   old.clear();
                   TransDetails = "SELECT * FROM GameZoneDB."+TableName+"  WHERE MONTH(Date) = MONTH(CURRENT_DATE()) AND YEAR(Date) = YEAR(CURRENT_DATE()) ORDER BY  Date desc";
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(TransDetails);
                   while(rs.next())  
                   {
                       Recharge rec = new Recharge();
                       rec.setID(rs.getInt(1));
                       rec.setEmpName(rs.getString(3));
                       rec.setCardNo(rs.getString(2));
                       rec.setDate(rs.getTimestamp(5));
                       rec.setAmount(rs.getInt(4));
              
                       old.add(rec);
                  
                   }
        
        
           
  
        return old;
    }

    @Override
    public ArrayList<Recharge> GetTransactionDetailsPerticularDates(String TableName,java.sql.Date Start ,java.sql.Date End) throws Exception {
        ArrayList<Recharge> transactiondetails = new ArrayList<>();
        
        TransDetails = "SELECT * FROM GameZoneDB."+TableName+" WHERE Date>= "+Start+" AND Date <= "+End+" ORDER BY  Date desc" ;
        
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(TransDetails);
                   while(rs.next())  
                   {
                       Recharge rec = new Recharge();
                       rec.setID(rs.getInt(1));
                       rec.setEmpName(rs.getString(3));
                       rec.setCardNo(rs.getString(2));
                       rec.setDate(rs.getTimestamp(5));
                       rec.setAmount(rs.getInt(4));
              
                       transactiondetails.add(rec);
                  
                   }
        
        
           
  
        return transactiondetails;
    }
    
}
