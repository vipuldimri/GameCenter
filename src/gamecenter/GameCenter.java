
package gamecenter;

import Gui.LoginScreen;
import Gui.Splash;

public class GameCenter {

    
    public static void main(String[] args) throws InterruptedException 
    {
  
        Splash splash = new Splash();
        splash.setVisible(true);
        GettingUserThread gettingUserThread = new GettingUserThread();
        gettingUserThread.start();
        for(int i= 0 ; i <= 100 ; i ++)
        {
            Thread.sleep(60);
            splash.Loading_Label.setText("Loading .... "+i+" %");
            if(i==100)
            {
                gettingUserThread.join();
                splash.setVisible(false);
                new LoginScreen().setVisible(true);
                break;
            }
        }
    }
    
}
