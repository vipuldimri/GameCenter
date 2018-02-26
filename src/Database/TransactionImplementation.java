/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import static Database.UserImplements.AddEmp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vipul
 */
public class TransactionImplementation implements TransactionInterface 
{
    
    static  String Rech = "";
    Connection conn;
    TransactionImplementation()
    {
        
         Connect connect = new Connect();
         conn = connect.getconnection();
    }
    
    @Override
    public void Recharge(gamecenter.Recharge rec, String TableName) 
    {
       
        Rech = "INSERT INTO "+TableName+" (CardNo,EmpName,Amount,Date)VALUES(?,?,?,?)";
          try {
           
            PreparedStatement pstmt = conn.prepareStatement(Rech);
            pstmt.setString(1, rec.getCardNo());
            pstmt.setString(2, rec.getEmpName());
            pstmt.setInt(3,rec.getAmount());
            pstmt.setTimestamp(4, rec.getDate());
          
            pstmt.executeUpdate();
           
           
           
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
            
        }
          
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransactionImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        
    }
    
}
