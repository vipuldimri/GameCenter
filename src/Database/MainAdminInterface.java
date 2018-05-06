/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author vipul
 */
import Gui.GameNameSetting;
import gamecenter.Stall;
import java.util.ArrayList;
public interface MainAdminInterface 
{
    public void AddGameZone(Stall newgamezone,ArrayList<String>  games)throws Exception;
    
}
