/*
This class is used to Send Email After Specific Period Of Time
*/
package gamecenter;

public class EmailTaskDaily implements Runnable
{
/*
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    */
    @Override
    public void run() 
    {
    
        System.out.println(Thread.currentThread().getName());
        System.out.println("Start");
        System.out.println("Stop");
        
    }
    
    
}
