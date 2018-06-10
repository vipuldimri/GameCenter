
package Database;

import static Database.Connect.conn;
import static Database.StallImplements.GetGameZones;
import static Database.TransactionImplementation.Rech;
import Gui.GameNameSetting;
import gamecenter.Stall;
import gamecenter.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    public void AddGameZone(Stall stall,ArrayList<String> games) throws Exception
    {
        /*
        1 . Add into GameZone List
        2 . create its userttable
        3 . create its transaction table
        4 . create its customer table
        5 .
        */
        
        
            final String AddGameZone = "insert  into gamezone(Address,Contact,Email,OwnerName,Max_Employee,Name,SubStartDate,Password,SubEndDate) VALUES(?,?,?,?,?,?,?,?,?)";
                       
            PreparedStatement pstmt = conn.prepareStatement(AddGameZone);
            pstmt.setString(1,stall.getAddress() );
            pstmt.setString(2, stall.getContact());
            pstmt.setString(3, stall.getEmail());
            pstmt.setString(4, stall.getOwnerName());
            pstmt.setInt(5, stall.getMax_Employee());
            pstmt.setString(6, stall.getName());
            pstmt.setDate(7, stall.getSubStartDate());
            pstmt.setString(8, stall.getPassword());
            pstmt.setDate(9, stall.getSubEndDate());
            pstmt.executeUpdate();
            
            //Adding new gamezone done into gamezone table 
           
            
         
         
             //above code updated for games 
              //Below code works Fine Creating a Table
              String TableName = stall.getName();
              String FinalTableName = TableName+"_transaction";
              String temp ="CREATE TABLE "+FinalTableName;
              
              
              String sql2 =temp+"( ID  int auto_increment primary key,CardNo  varchar(45) null,EmpName varchar(45) null, Amount  int  null,Date    datetime(6) null);";
              
        
              Statement stmt2 = conn.createStatement() ;
              // create a new table
              stmt2.execute(sql2);
              
         
              
              ///////////////////////////////
              
              
              String TableName2 = stall.getName();
              String FinalTableName2 = TableName+"_users";
              String temp2 ="CREATE TABLE '"+FinalTableName2+"'";
              
            
              String query = temp2+"(ID INT AUTO_INCREMENT PRIMARY KEY,Name       VARCHAR(20) NOT NULL,Address    VARCHAR(50) NULL, Contact    VARCHAR(20) NULL, Email      VARCHAR(30) NULL,Type       VARCHAR(10) NOT NULL,GameZoneID INT         NOT NULL,Password   VARCHAR(20) NOT NULL,UserName   VARCHAR(45) NOT NULL, CONSTRAINT UserName_UNIQUE UNIQUE (UserName))";
              //String sql22 =temp2+qu;
              Statement stmt22 = conn.createStatement() ;
              stmt22.execute(query);
              //////////////////////
              
              
                    
              
             
              String FinalTableName3 = TableName+"_customers";
              String temp3 ="CREATE TABLE '"+FinalTableName3+"'";
              String query2 = temp3+"(Id      INT(100) AUTO_INCREMENT PRIMARY KEY, Name    VARCHAR(30) NOT NULL,Contact VARCHAR(30) NOT NULL, EmailId VARCHAR(50) NULL)";
              Statement stmt222 = conn.createStatement() ;
              stmt222.execute(query2);
       
              
              String temp4 ="create table '"+stall.getName()+"_games'\n" +
"(\n" +
" ID       int auto_increment\n" +
"   primary key,\n" +
" GameName varchar(45)  not null,\n" +
" Amount   varchar(200) not null,\n" +
" Date     date         not null\n" +
");\n" +
"";
              
          
              Statement stmt2222 = conn.createStatement() ;
              stmt2222.execute(temp4);
              
              
              
              String other_t =  "create table '"+stall.getName()+"_other_transaction'\n" +
"(\n" +
"  ID           int auto_increment\n" +
"    primary key,\n" +
"  CustomerName varchar(20)  not null,\n" +
"  CardNo       varchar(30)  null,\n" +
"  Method       varchar(50)  not null,\n" +
"  Money        varchar(100) not null,\n" +
"  Date         datetime     not null,\n" +
"  ExpireDate   date         null\n" +
");\n" +
"";
        Statement stmt22222 = conn.createStatement() ;
        stmt22222.execute(other_t);
              
        
        String maxid = "select max(ID) from gamezone;";
        int id=0;
       
      
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(maxid);
                   while(rs.next())  
                   {
                    id = rs.getInt(1);
                   
                   }
                   
        
        
        
        
        String insertuserdetails = "INSERT into '"+stall.getName()+"_users'(Name,UserName,Password,Email,Contact,Address,Type,GameZoneID) values (?,?,?,?,?,?,?,?);";
        
        PreparedStatement pstmt2 = conn.prepareStatement(insertuserdetails);
        pstmt2.setString(1,stall.getOwnerName());
        pstmt2.setString(2, stall.getOwnerName());
        pstmt2.setString(3, stall.getPassword());
        pstmt2.setString(4, stall.getEmail());
        pstmt2.setString(5, stall.getContact());
        pstmt2.setString(6, stall.getAddress());
        pstmt2.setString(7, "admin");
        pstmt2.setInt(8,id);
        pstmt2.executeUpdate();
        
        
        //now entering the game into games  table
        
        for(String gamename : games)
        {
            //now entering game one by one into database table
        String insertgame = "INSERT INTO "+stall.getName()+"_games(GameName,Amount,Date) VALUES(?,?,CURRENT_DATE())";
               
        PreparedStatement pstmt3 = conn.prepareStatement(insertgame);
        pstmt3.setString(1,gamename);
        pstmt3.setString(2,"0");
        pstmt3.executeUpdate();
            
            
        }
              
              
       }
    
    
}
