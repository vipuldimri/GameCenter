/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.ticketsystem;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;

/**
 * @author ACER
 */
public class FactoryClass 
{
    FactoryClass()
    {
        dbObj = null;
        commObj = null;
        zigbeePort = "";
        cardRechargerPort = "";
    }
    private static my.ticketsystem.Database dbObj;
    private static my.ticketsystem.CommPortTest commObj,commObj2;
    private static my.ticketsystem.MainPageUI mainpagegui;
    //private static my.ticketsystem.CollectionPage collectionpage;
    private static String zigbeePort;
    private static String cardRechargerPort;
    
    public static void createObjects() throws ClassNotFoundException
    {
        
        try 
        {
            if(dbObj == null && commObj == null)
            {
                dbObj = new Database();
                
                getPortsFromConfig();
                
                MyLog.getLogger().info("Comport and database objects created");
		
                Thread t=new Thread() 
                {
			public void run()
                        {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try 
                                {
                                    Thread.sleep(1000000);
                                }
                                catch (InterruptedException ie)
                                {
                                }
			
                        }
                        
                        
		};
		t.start();
            }
            else
            {
                MyLog.getLogger().severe("Comport and database instance already exisits !");
            }
            mainpagegui = new MainPageUI();
            MyLog.getLogger().info("Add-User and Collection objects created");
    }
    catch(ClassNotFoundException ex) {
    
        //Logger.getLogger(AddUserGUI.class.getName()).log(Level.SEVERE, null, ex);
            MyLog.getLogger().severe("ClassNotFoundException in FactoryClass");
        }
        catch( Exception e)
        {
            MyLog.getLogger().severe("Exception in CreateObjects function");
        }
        
}
    public static Database getDbObj()
    {
        return dbObj;
    }
    
    public static CommPortTest getCommObj()
    {
        return commObj2;
    }
    
    public static MainPageUI getMainPageObj()
    {
        return mainpagegui;
    }

    public static void getPortsFromConfig()
    {
        Properties prop = new Properties();
	InputStream input = null;
        
	try {
		//input = new FileInputStream("src/config.properties");
                 input = FactoryClass.class.getClassLoader().getResourceAsStream("config.properties");
                    
		// load a properties file
		prop.load(input);
                zigbeePort = prop.getProperty("zigbeeport");
                cardRechargerPort = prop.getProperty("cardrechargerport");
                MyLog.getLogger().info(zigbeePort);
                MyLog.getLogger().info(cardRechargerPort);
                // comport for Today's collection
                commObj = new CommPortTest(zigbeePort);
                // comport for smart card recharge
                commObj2 = new CommPortTest(cardRechargerPort);
     
        }
        catch(java.io.IOException e)
        {
            e.printStackTrace();
            MyLog.getLogger().severe(" ports not available ");
        }
        catch( Exception e)
        {
            MyLog.getLogger().severe(" ports not available ");
        }
        
    }
      
}