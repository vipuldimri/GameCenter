/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecenter;

import Gui.Loading;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vipul
 */
public class LoadingTHREAD extends Thread
{
    @Override
    public void run()
    {
        
        for(int i = 0 ; i <=100;i++)
        {
            try {
                Thread.sleep(40);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadingTHREAD.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(i);
        }
        
        
        Loading ll = new Loading();
        ll.setVisible(true);
      
        
    }
    
}
