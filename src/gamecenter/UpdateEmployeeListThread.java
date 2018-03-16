/*
This class is used to update employeelist in background thread
 */
package gamecenter;


import java.util.ArrayList;
public class UpdateEmployeeListThread extends Thread
{
    ArrayList<User> employeelist;
    public UpdateEmployeeListThread( ArrayList<User> employeelist)
    {
        this.employeelist = employeelist;
    }
    @Override
    public void run()
    {
        //update employeelist with new data code 
        
    }
    
}
