
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
            
            
              String transactiontable = "create table "+stall.getName()+"_transaction\n" +
"(\n" +
"  ID      int auto_increment\n" +
"    primary key,\n" +
"  CardNo  varchar(45) null,\n" +
"  EmpName varchar(45) null,\n" +
"  Amount  int         null,\n" +
"  Date    datetime(6) null\n" +
");\n" +
""            ;
        
              Statement statement = conn.createStatement() ;
              statement.execute(transactiontable);
              
         
              
              ///////////////////////////////
              
              
              
              
              String Usertable ="create table "+stall.getName()+"_users\n" +
"(\n" +
"  ID         int auto_increment\n" +
"    primary key,\n" +
"  Name       varchar(20) not null,\n" +
"  Address    varchar(50) null,\n" +
"  Contact    varchar(20) null,\n" +
"  Email      varchar(30) null,\n" +
"  Type       varchar(10) not null,\n" +
"  GameZoneID int         not null,\n" +
"  Password   varchar(20) not null,\n" +
"  UserName   varchar(45) not null,\n" +
"  constraint UserName_UNIQUE\n" +
"  unique (UserName)\n" +
");";
              
            
              
               statement = conn.createStatement() ;
               statement.execute(Usertable);
            
              
              
                    
              
             
              String Customertable ="create table "+stall.getName()+"_customers\n" +
"(\n" +
"  Id      int(100) auto_increment\n" +
"    primary key,\n" +
"  Name    varchar(30) not null,\n" +
"  Contact varchar(30) not null,\n" +
"  EmailId varchar(50) null\n" +
");\n" +
"";
     
              Statement stmt222 = conn.createStatement() ;
              stmt222.execute(Customertable);
       
              
              String Gamestable ="create table "+stall.getName()+"_games\n" +
"(\n" +
"  ID       int auto_increment\n" +
"    primary key,\n" +
"  GameName varchar(45)  not null,\n" +
"  Amount   varchar(200) not null,\n" +
"  Date     date         not null\n" +
");";
              
          
               statement = conn.createStatement() ;
              statement.execute(Gamestable);
              
              
              
              String other_t =  "create table "+stall.getName()+"_transaction\n" +
"(\n" +
"  ID      int auto_increment\n" +
"    primary key,\n" +
"  CardNo  varchar(45) null,\n" +
"  EmpName varchar(45) null,\n" +
"  Amount  int         null,\n" +
"  Date    datetime(6) null\n" +
");\n" +
"";
         statement = conn.createStatement() ;
         statement.execute(other_t);
              
        
        String maxid = "select max(ID) from gamezone;";
        int id=0;
       
      
                   Statement stmt=conn.createStatement();  
                   ResultSet rs = stmt.executeQuery(maxid);
                   while(rs.next())  
                   {
                    id = rs.getInt(1);
                   
                   }
                   
        
        
        
        
        String insertuserdetails = "INSERT into "+stall.getName()+"_users(Name,UserName,Password,Email,Contact,Address,Type,GameZoneID) values (?,?,?,?,?,?,?,?);";
        
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
