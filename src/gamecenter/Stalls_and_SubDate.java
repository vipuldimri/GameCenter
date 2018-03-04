/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecenter;

import java.util.*;
/*
This class is used for getting all the gamezone info and their EndSub date
*/
public class Stalls_and_SubDate 
{
    
    //Has record for all the GamesZones
    public ArrayList<Stall> stalls;
    //Contains Endsub for all the Gamezones
    public HashMap<Integer, java.sql.Date> subdate;
    
    public HashMap<Integer,String> stallIdandName;
    
    public HashMap<String,String> PasswordCheck;
    

    public Stalls_and_SubDate()
    {
        stalls = new ArrayList<>();
        subdate = new HashMap<>();
        stallIdandName = new HashMap<>();
        PasswordCheck = new HashMap<>();
    }
    
}
