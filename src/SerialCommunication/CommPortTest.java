/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SerialCommunication;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class CommPortTest implements Runnable, SerialPortEventListener 
{
	SerialPort serialPort;
        /** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { 
			//"COM4", // Windows
                        "COM7",
	};
	/**
	* A BufferedReader which will be fed by a InputStreamReader 
	* converting the bytes into characters 
	* making the displayed results codepage independent
	*/
	private BufferedReader input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;
        
        public String cardNumber;
        
        private String commPort;
        
        Thread readThread;
        public CommPortTest(String commport)
        {
            this.commPort = commport;
            initialize();
        }
        
        @Override
        public void run()
        {
            //System.out.println(Thread.currentThread().getName());
            try 
            {
                Thread.sleep(10000);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(CommPortTest.class.getName()).log(Level.SEVERE, null, ex);
            
            }
            
        }

	public void initialize() 
        {
                // the next line is for Raspberry Pi and 
                // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
                //System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
                MyLog.getLogger().info("comport initialise");                
                
                
		//System.out.printf("comport:%s\n",commPort);
                CommPortIdentifier portId = null;
          
                Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
                
		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) 
                {
                   
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
                        MyLog.getLogger().info(commPort);
			//for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(commPort)) {
                                    MyLog.getLogger().info("COM port is:");
                                    MyLog.getLogger().info(currPortId.getName());
					portId = currPortId;
					break;
				}
		}
		//}
                 
		if (portId == null) 
                {
                        MyLog.getLogger().info("Could not find COM port");
                        JOptionPane.showMessageDialog(null, "Could not find COM port");
			//System.exit(0);
                        return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
                        InputStream is = serialPort.getInputStream();
                        if( is == null)
                        {
			System.out.println("inputstream is null");
                        return;
                        }
                        input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
                        output = serialPort.getOutputStream();
                        
			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);   
                        serialPort.disableReceiveTimeout();
                        serialPort.enableReceiveThreshold(1);
		} 
                catch( gnu.io.PortInUseException e)
                {
                    System.exit(0);
                }
                catch (Exception e) {
			System.err.println(e.toString());
                        MyLog.getLogger().severe(e.getMessage());
		}
                // Multithread to read from both threads
                readThread = new Thread(this);
                readThread.start();
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() 
        {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
                        System.out.println("port closed successfully");
		}
	}
       
       
         public void sendData(String amount) throws IOException
        {
            byte[] arr = amount.getBytes();
            output.write(arr);
            System.out.print("Sending amount:");
            System.out.print(amount);
        }          
	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
        @Override
	public synchronized void serialEvent(SerialPortEvent oEvent)
        {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
			        String inputLine=input.readLine();
                                System.out.println(inputLine);
                                
                                Scanner scanner = new Scanner(inputLine);
                                //System.out.println(scanner.hasNext("UID"));
                                if( scanner.hasNext("###"))
                                {
                                 String hash = scanner.next();
                                 String gamename = scanner.next();
                                 String amt = scanner.next();
                                  
                                    if( "speedball".equals(gamename) )
                                    {
                                        //System.out.println(gamename);
                                        FactoryClass.getMainPageObj().setGameAmount(gamename,amt);
                                    }
                                    else if( "basketball".equals(gamename) )
                                    {
                                        //System.out.println("volleyball setgameamount");
                                        FactoryClass.getMainPageObj().setGameAmount(gamename,amt);
                                    }
                                    else if( "airhockey".equals(gamename) )
                                    {
                                        System.out.println("airhockey setgameamount");
                                        FactoryClass.getMainPageObj().setGameAmount(gamename,amt);
                                    }
                                    else if( "dance".equals(gamename) )
                                    {
                                        System.out.println("dance setgameamount");
                                        FactoryClass.getMainPageObj().setGameAmount(gamename,amt);
                                    }
                                    else if( "randomhit".equals(gamename) )
                                    {
                                        FactoryClass.getMainPageObj().setGameAmount(gamename,amt);
                                    }
                                }
                                else if( scanner.hasNext("UID") )
                                { 
                                    String uid = inputLine;   
                                    FactoryClass.getMainPageObj().setCardNumber(uid.substring(4));
                                }
                                else if( scanner.hasNext("RUPEES"))
                                {
                                    scanner.next();
                                    String rupees = scanner.next();
                                    FactoryClass.getMainPageObj().cardDetails(rupees);
                                }
                                else if( scanner.hasNext("ERROR"))
                                {
                                    MyLog.getLogger().severe(inputLine);
                                    FactoryClass.getMainPageObj().message(inputLine);
                                }
                                else if( scanner.hasNext("LOG"))
                                {
                                    MyLog.getLogger().info(inputLine);
                                }
                                else if( scanner.hasNext("SUCCESS"))
                                {
                                    FactoryClass.getMainPageObj().message(inputLine);
                                }
                               
			}
                        catch( java.io.IOException e)
                        {
                            FactoryClass.getMainPageObj().message("Recharge module removed, Login again ");
                            System.exit(0);
                        }
                        catch (Exception e) {
                            MyLog.getLogger().severe(e.toString());
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}
        
        protected void finalize()
        {
            close();
        }        
}