
package Database;
import java.util.*;
import gamecenter.*;
public class StallImplements implements StallInterface
{
 static final String AddEmp = "INSERT INTO GameZoneDB.users (Name,Address,Contact,Email,Type,GameZoneID,Password) VALUES(?,?,?,?,?,?,?)";
    
    @Override
    public void AddGameZone(StallInterface stall)
    {
        /*
           try {
           
            PreparedStatement pstmt = conn.prepareStatement(AddEmp);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getAddress());
            pstmt.setString(3, user.getContact());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getType());
            pstmt.setInt(6, user.getGameZoneID());
            pstmt.setString(7, user.getPassword());
            pstmt.executeUpdate();
           
           
           
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            Logger.getLogger(UserImplements.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
   
        */
    }

    @Override
    public ArrayList<Stall> GetAllGameZone() 
    {
        ArrayList<Stall> gamzonelist =  null;
        
        
        return gamzonelist;
     
    }
    
}
