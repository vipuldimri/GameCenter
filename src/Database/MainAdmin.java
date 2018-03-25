
package Database;

import static Database.Connect.conn;
import static Database.StallImplements.GetGameZones;
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
    public void AddGameZone(Stall newgamezone) 
    {
        /*
        1 . Add into GameZone List
        2 . create its userttable
        3 . create its transaction table
        4 . create its customer table
        5 .
        */
        
    
        String insert3 ="INSERT INTO `GameZoneDB`.`gamezone`(`Name`,`OwnerName`,`Address`,`Contact`,`Password`,`SubStartDate`,`SubEndDate`)VALUES(?,?,?,?,?,?,?);";
        
        
        String insertuser ="(`Name`,`Address`,`Contact`,`Email`,`Type`,`GameZoneID`,`Password`,`UserName`)VALUES(?,?,?,?,?,?,?,?);";

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
         pstmt.executeUpdate();
         
            System.out.println("GameZone ADDED");
         
              //above code updated for games 
              //Below code works Fine Creating a Table
              String TableName = newgamezone.getName();
              String FinalTableName = TableName+"_transaction";
              String temp ="CREATE TABLE "+FinalTableName;
              
              
              String sql2 =temp+" (ID int(11) NOT NULL AUTO_INCREMENT,CardNo varchar(45) DEFAULT NULL,EmpName varchar(45) DEFAULT NULL,Amount int(11) DEFAULT NULL,Date datetime(6) DEFAULT NULL,PRIMARY KEY (ID))";
              
              Statement stmt2 = conn.createStatement() ;
            // create a new table
              stmt2.execute(sql2);
              
         
              
              ///////////////////////////////
              
              
              String TableName2 = newgamezone.getName();
              String FinalTableName2 = TableName+"_users";
              String temp2 ="CREATE TABLE "+FinalTableName2;
              
            
              String query = temp2+"(ID INT AUTO_INCREMENT PRIMARY KEY,Name       VARCHAR(20) NOT NULL,Address    VARCHAR(50) NULL, Contact    VARCHAR(20) NULL, Email      VARCHAR(30) NULL,Type       VARCHAR(10) NOT NULL,GameZoneID INT         NOT NULL,Password   VARCHAR(20) NOT NULL,UserName   VARCHAR(45) NOT NULL, CONSTRAINT UserName_UNIQUE UNIQUE (UserName))";
              //String sql22 =temp2+qu;
              Statement stmt22 = conn.createStatement() ;
              stmt22.execute(query);
              //////////////////////
              
              
                    
              
             
              String FinalTableName3 = TableName+"_customers";
              String temp3 ="CREATE TABLE "+FinalTableName3;
              
            
              String query2 = temp3+"(Id      INT(100) AUTO_INCREMENT PRIMARY KEY, Name    VARCHAR(30) NOT NULL,Contact VARCHAR(30) NOT NULL, EmailId VARCHAR(50) NULL)";
              //String sql22 =temp2+qu;
              Statement stmt222 = conn.createStatement() ;
              stmt222.execute(query2);
              //////////////////////////////////
              
              
              String FinalTableName4 = TableName+"_games";
              String temp4 ="CREATE TABLE "+FinalTableName4;
              
            
              String query3 = temp4+"(Id  INT(100) AUTO_INCREMENT PRIMARY KEY, Name    VARCHAR(30) NOT NULL,Contact VARCHAR(30) NOT NULL, EmailId VARCHAR(50) NULL)";
              //String sql22 =temp2+qu;
              Statement stmt2222 = conn.createStatement() ;
              stmt222.execute(query3);
              
              /////////////////////////////////////////////
              
              
              int gamezoneid = 0 ;
              String getmaxidquery = "SELECT MAX(ID) AS LargestPrice FROM gamezone";
              
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(getmaxidquery);
                   while(rs.next())  
                   {
                    gamezoneid  = rs.getInt(1);
                    break;

                   }
              
              //////////////////////////////////////////////
             String query4 = "INSERT INTO `"+FinalTableName2+"` "+insertuser;    
             PreparedStatement pstmtuser = conn.prepareStatement(query4);
             pstmtuser.setString(1, newgamezone.getOwnerName());
             pstmtuser.setString(2, newgamezone.getAddress());
             pstmtuser.setString(3,newgamezone.getContact());
             pstmtuser.setString(4, "dummy@gmail.com");
             pstmtuser.setString(5, "admin");
             pstmtuser.setInt(6, gamezoneid);
             pstmtuser.setString(7, newgamezone.getPassword());
             pstmtuser.setString(8, newgamezone.getOwnerName()); 
             pstmtuser.executeUpdate();

        }  
        catch(Exception e)
        {

            System.out.println("ERROR "+e);
            
        }
           
        
       }
    
    
}
