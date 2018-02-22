
package Database;

import gamecenter.User;
import java.sql.Connection;
import java.util.ArrayList;

public class UserImplements implements UserInterface
{

    static final String GetAllUser = "SELECT * FROM";
    
    Connection conn;
    UserImplements()
    {
         Connect connect = new Connect();
         conn = connect.getconnection();
    }
    
    @Override
    public ArrayList<User> getAllUsers() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
