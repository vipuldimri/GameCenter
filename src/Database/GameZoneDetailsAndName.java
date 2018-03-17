/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author u20o90
 */
import gamecenter.Stall;
import java.util.ArrayList;
public class GameZoneDetailsAndName
{
    public ArrayList<String> GameZoneNames;
    public ArrayList<Stall>  GameZoneDetails;
    
    GameZoneDetailsAndName()
    {
        GameZoneNames = new ArrayList<>();
        GameZoneDetails = new ArrayList<>();
        
    }
    
}
