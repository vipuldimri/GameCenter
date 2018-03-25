/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SerialCommunication;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import Database.*;
import Gui.*;
import gamecenter.*;
/**
 * @author ACER
 */
public class FactoryClass 
{
    FactoryClass()
    {
     
        commObj = null;
        zigbeePort = "";
        cardRechargerPort = "";
    }
    //private static my.ticketsystem.Database dbObj;
    private static CommPortTest commObj,commObj2;
    private static MainScreen_StallOwner MainGui;
    //private static my.ticketsystem.CollectionPage collectionpage;
    private static String zigbeePort;
    private static String cardRechargerPort;
    
    public static void createObjects(MainScreen_StallOwner MainGuiComing) throws ClassNotFoundException
    {
            getPortsFromConfig();
            MainGui = MainGuiComing;
         
    }

    public static CommPortTest getCommObj()
    {
        return commObj2;
    }
    
    public static MainScreen_StallOwner getMainPageObj()
    {
        return MainGui;
    }

    public static void getPortsFromConfig()
    {
        Properties prop = new Properties();
	InputStream input = null;
        
	try {
		
                //input = FactoryClass.class.getClassLoader().getResourceAsStream("config.properties");
		// prop.load(input);
                ///zigbeePort = prop.getProperty("zigbeeport");
                //cardRechargerPort = prop.getProperty("cardrechargerport");
              
          
                //COM4
                //COM15
                commObj = new CommPortTest("COM4");
                commObj2 = new CommPortTest("COM15");
               
        }
        catch(Exception e)
        {
            e.printStackTrace();
           
        }
        
        
    }
      
}