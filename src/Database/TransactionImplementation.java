/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import gamecenter.OtherTrasactions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import gamecenter.Recharge;
import gamecenter.Stall;
import java.sql.Date;
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
        
        
           Rech = "INSERT INTO "+TableName+" (CardNo,EmpName,Amount,Date)VALUES(?,?,?,?)";
          
            PreparedStatement pstmt = conn.prepareStatement(Rech);
            pstmt.setString(1, rec.getCardNo());
            pstmt.setString(2, rec.getEmpName());
            pstmt.setInt(3,rec.getAmount());
            pstmt.setTimestamp(4, rec.getDate());
         
          
            pstmt.executeUpdate();
        
       
        
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
    public ArrayList<Recharge> GetTransactionDetailsPerticularDates(String TableName,java.sql.Date Start ,java.sql.Date End) throws Exception 
    {
        ArrayList<Recharge> transactiondetails = new ArrayList<>();
        
        TransDetails = "SELECT * FROM GameZoneDB."+TableName+" WHERE DATE(Date)>= '"+Start+"' AND DATE(Date) <= '"+End+"'" ;
        
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
    public ArrayList<OtherTrasactions> GetOthertrasactions(String TabName) throws Exception 
    {
     ArrayList<OtherTrasactions> transactiondetails = new ArrayList<>();
        
        TransDetails = "SELECT * FROM GameZoneDB."+TabName+" WHERE MONTH(Date) = MONTH(CURRENT_DATE()) AND YEAR(Date) = YEAR(CURRENT_DATE()) ORDER BY  Date desc" ;
        
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(TransDetails);
                   while(rs.next())  
                   {
                       OtherTrasactions otherTrasactions = new OtherTrasactions();
                       otherTrasactions.setID(rs.getInt(1));
                       otherTrasactions.setCustomerName(rs.getString(2));
                       otherTrasactions.setPhoneNo(rs.getString(3));
                       otherTrasactions.setCardNo(rs.getString(4));
                       otherTrasactions.setMethod(rs.getString(5));
                       otherTrasactions.setMoney(rs.getString(6));
                       otherTrasactions.setDate(rs.getTimestamp(7));
              
                       transactiondetails.add(otherTrasactions);
                  
                   }
        
        
           
  
        return transactiondetails;
        
        
    }

    @Override
    public ArrayList<OtherTrasactions> GetOthertrasactions(String TableName, ArrayList<OtherTrasactions> old) throws Exception {
               
            old.clear();
            TransDetails = "SELECT * FROM GameZoneDB."+TableName+"  WHERE MONTH(Date) = MONTH(CURRENT_DATE()) AND YEAR(Date) = YEAR(CURRENT_DATE()) ORDER BY  Date desc";
            Statement stmt=conn.createStatement();  
            ResultSet rs = stmt.executeQuery(TransDetails);
            while(rs.next())  
                      {
                       OtherTrasactions otherTrasactions = new OtherTrasactions();
                       otherTrasactions.setID(rs.getInt(1));
                       otherTrasactions.setCustomerName(rs.getString(2));
                       otherTrasactions.setPhoneNo(rs.getString(3));
                       otherTrasactions.setCardNo(rs.getString(4));
                       otherTrasactions.setMethod(rs.getString(5));
                       otherTrasactions.setMoney(rs.getString(6));
                       otherTrasactions.setDate(rs.getTimestamp(7));
              
                       old.add(otherTrasactions);
                  
                   }
        
        
           
  
        return old;
    }

    @Override
    public ArrayList<OtherTrasactions> GetOthertrasactionsPerticularDates(String TableName, Date Start, Date End) throws Exception
    {
        ArrayList<OtherTrasactions> transactiondetails = new ArrayList<>();
        
         TransDetails = "SELECT * FROM GameZoneDB."+TableName+" WHERE DATE(Date)>= '"+Start+"' AND DATE(Date) <= '"+End+"'" ;
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(TransDetails);
                   while(rs.next())  
                   {
                       OtherTrasactions otherTrasactions = new OtherTrasactions();
                       otherTrasactions.setID(rs.getInt(1));
                       otherTrasactions.setCustomerName(rs.getString(2));
                       otherTrasactions.setPhoneNo(rs.getString(3));
                       otherTrasactions.setCardNo(rs.getString(4));
                       otherTrasactions.setMethod(rs.getString(5));
                       otherTrasactions.setMoney(rs.getString(6));
                       otherTrasactions.setDate(rs.getTimestamp(7));
                       transactiondetails.add(otherTrasactions);
                  
                   }
        
        
           
  
        return transactiondetails;
    }

    @Override
    public void RechargeOtherMethod(OtherTrasactions rec, String TableName) throws Exception {
        
           Rech = "INSERT INTO "+TableName+" (CustomerName, PhoneNo, CardNo, Method, Money, Date) VALUES (?,?,?,?,?,?)";
           PreparedStatement pstmt = conn.prepareStatement(Rech);
           pstmt.setString(1, rec.getCustomerName());
           pstmt.setString(2, rec.getPhoneNo());
           pstmt.setString(3,rec.getCardNo());
           pstmt.setString(4,rec.getMethod());
           pstmt.setString(5,rec.getMoney());
           pstmt.setTimestamp(6, rec.getDate());
         
          
            pstmt.executeUpdate();
         
    }
    
}
