
package Database;

import static Database.TransactionImplementation.Rech;
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
        conn = Connect.getconnection();
    }
    @Override
    public void AddGameZone(Stall newgamezone,int ID) 
    {
         /*
        Steps
        1. Add data to GameZone Table
        2. Add data to UserTable
        3. create name_transaction Table
        */
        
        String insert = "INSERT INTO GameZoneDB.gamezone(Name,OwnerName,Address,Contact,Password,SubStartDate,SubEndDate,BasketBall,SpeedBall,AirHockey,Dance,CatchLight)VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
        
       String insert2 = "INSERT INTO GameZoneDB.gamezone(ID,Name,OwnerName,Address,Contact,Password,SubStartDate,SubEndDate,Basket Ball,Speed Ball,Air Hockey,Dance,Catch Light)VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
        String insert3 ="INSERT INTO `GameZoneDB`.`gamezone`(`Name`,`OwnerName`,`Address`,`Contact`,`Password`,`SubStartDate`,`SubEndDate`,`Basket Ball`,`Speed Ball`,`Air Hockey`,`Dance`,`Catch Light`)VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
        try
        { 
        
         //conn.setAutoCommit(false);
         PreparedStatement pstmt = conn.prepareStatement(insert3);
         pstmt.setString(1, newgamezone.getName());
         pstmt.setString(2, newgamezone.getOwnerName());
         pstmt.setString(3,newgamezone.getAddress());
         pstmt.setString(4, newgamezone.getContact());
         pstmt.setString(5, newgamezone.getPassword());
         pstmt.setDate(6,newgamezone.getSubStartDate());
         pstmt.setDate(7, newgamezone.getSubEndDate()); 
         pstmt.setInt(8, newgamezone.getBasket_Ball()); 
         pstmt.setInt(9, newgamezone.getSpeed_Ball()); 
         pstmt.setInt(10, newgamezone.getAir_Hockey()); 
         pstmt.setInt(11, newgamezone.getDance()); 
         pstmt.setInt(12, newgamezone.getCatch_Light()); 

         pstmt.executeUpdate();
         
            System.out.println("GameZone ADDED");
         
              //above code updated for games 
              //Below code works Fine Creating a Table
              String TableName = newgamezone.getName();
              String FinalTableName = TableName+"_transaction";
              String temp ="CREATE TABLE "+FinalTableName;
              
              
              String sql2 =temp+" (ID int(11) NOT NULL AUTO_INCREMENT,CardNo varchar(45) DEFAULT NULL,EmpName varchar(45) DEFAULT NULL,Amount int(11) DEFAULT NULL,Date datetime(6) DEFAULT NULL,GameName varchar(45) DEFAULT NULL,PRIMARY KEY (ID))";
              
              Statement stmt2 = conn.createStatement() ;
            // create a new table
              stmt2.execute(sql2);
              
              
         String insertuser  = "INSERT INTO GameZoneDB.users (Name, Address,Contact,Email,Type,GameZoneID, Password) VALUES(?,?,?,?,?,?,?);";
         PreparedStatement pstmt2 = conn.prepareStatement(insertuser);
         pstmt2.setString(1, newgamezone.getOwnerName());
         pstmt2.setString(2, newgamezone.getAddress());
         pstmt2.setString(3,newgamezone.getContact());
         pstmt2.setString(4, "dummy@gamezone.com");
         pstmt2.setString(5, "admin");
         pstmt2.setInt(6 ,ID+1);
         pstmt2.setString(7, newgamezone.getPassword()); 
         
         pstmt2.executeUpdate();
         System.out.println("ALL DONe");
         //conn.commit();    
}
        catch(Exception e)
        {

            System.out.println("ERROR "+e);
            
        }
           
        
       }
    
    
}
