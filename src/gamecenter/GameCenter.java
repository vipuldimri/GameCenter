
package gamecenter;

import Gui.LoginScreen;
import Gui.Splash;

public class GameCenter {

    
    public static void main(String[] args) throws InterruptedException 
    {
  
        Splash splash = new Splash();
        splash.setVisible(true);
        BackGroundThread1 backgroundthread1 = new BackGroundThread1();
        backgroundthread1.start();
        for(int i= 0 ; i <= 100 ; i ++)
        {
            Thread.sleep(60);
            splash.Loading_Label.setText("Loading .... "+i+" %");
            if(i==100)
            {
                backgroundthread1.join();
                splash.setVisible(false);
                LoginScreen l = new LoginScreen(backgroundthread1.users,backgroundthread1.stalls);
                l.setVisible(true);
                break;
            }
        }
    }
    
}
