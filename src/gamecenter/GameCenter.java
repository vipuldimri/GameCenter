
package gamecenter;

import Gui.LoginScreen;
import Gui.Splash;


/*
This is the Main class Staring og the Project
Threading is used for getting the data from the database


*/
public class GameCenter {

    
    public static void main(String[] args) throws InterruptedException 
    {
        //Creating instance of splash loading screen
        Splash splash = new Splash();
        splash.setVisible(true);
        BackGroundThread1 backgroundthread1 = new BackGroundThread1();
        //Starting thread and getting users and stall details fro the database
        backgroundthread1.start();
        
        for(int i= 0 ; i <= 100 ; i ++)
        {
            Thread.sleep(60);
            splash.Loading_Label.setText("Loading .... "+i+" %");
            if(i==100)
            {
                backgroundthread1.join();
                splash.setVisible(false);
                //Sending Users and stall details to the login screen
                LoginScreen l = new LoginScreen(backgroundthread1.users,backgroundthread1.stalls);
                l.setVisible(true);
                break;
            }
        }
    }
    
}
