
package Database;

import static Database.TransactionImplementation.Rech;
import static Database.UserImplements.GetAllUser;
import gamecenter.Stall;
import gamecenter.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainAdmin implements MainAdminInterface
{

    Connection conn;
    MainAdmin() throws Exception
    {
        Connect connect = new Connect();
        conn = connect.getconnection();
    }
    @Override
    public void AddGameZone(Stall newgamezone) 
    {
         /*
        Steps
        1. Add data to GameZone Table
        2. Add data to UserTable
        3. create name_transaction Table
        */
        
        String insert = "INSERT INTO GameZoneDB.gamezone(Name,OwnerName,Address,Contact,Password,SubStartDate,SubEndDate)VALUES(?,?,?,?,?,?,?);";
        try
        { 
        
         //conn.setAutoCommit(false);
         PreparedStatement pstmt = conn.prepareStatement(insert);
         pstmt.setString(1, newgamezone.getName());
         pstmt.setString(2, newgamezone.getOwnerName());
         pstmt.setString(3,newgamezone.getAddress());
         pstmt.setString(4, newgamezone.getContact());
         pstmt.setString(5, newgamezone.getPassword());
         pstmt.setDate(6,newgamezone.getSubStartDate());
         pstmt.setDate(7, newgamezone.getSubEndDate()); 
         pstmt.executeUpdate();
       
              //Below code works Fine Creating a Table
              String TableName = newgamezone.getName();
              String FinalTableName = TableName+"_transaction";
              String temp ="CREATE TABLE IF NOT EXISTS "+FinalTableName+" (\n";
              String sql =temp
                + "  ID int(11) NOT NULL AUTO_INCREMENT,\n"
                + "  CardNo varchar(45) DEFAULT NULL,\n" 
                + "  EmpName varchar(45) DEFAULT NULL,\n"
                + "  Amount int(11) DEFAULT NULL,\n"
                + "  Date  datetime(6) DEFAULT NULL,\n"
                + "  PRIMARY KEY (ID));";
                   
              Statement stmt2 = conn.createStatement() ;
            // create a new table
              stmt2.execute(sql);
              
              
         String insertuser  = "INSERT INTO GameZoneDB.users (Name, Address,Contact,Email,Type,GameZoneID, Password) VALUES(?,?,?,?,?,?,?);";
         PreparedStatement pstmt2 = conn.prepareStatement(insertuser);
         pstmt2.setString(1, newgamezone.getOwnerName());
         pstmt2.setString(2, newgamezone.getAddress());
         pstmt2.setString(3,newgamezone.getContact());
         pstmt2.setString(4, "dummy@gamezone.com");
         pstmt2.setString(5, "admin");
         pstmt2.setInt(6 ,55 );
         pstmt2.setString(7, newgamezone.getPassword()); 
         
         pstmt2.executeUpdate();
         System.out.println("ALL DONe");
         //conn.commit();    
}
        catch(Exception e)
        {

            System.out.println("ERROR "+e);
            
        }
           
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
