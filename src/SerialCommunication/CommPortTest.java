
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
import java.util.concurrent.CountDownLatch;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class CommPortTest implements  SerialPortEventListener        
{
        ArrayList<String> AllgamesList;
	SerialPort serialPort;
        /** The port we're normally going to use. */
	private static final String PORT_NAMES[] =
        { 
			//"COM4", // Windows
                        "COM7",
	};
	
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
            AllgamesList = new ArrayList<>();
            initialize();
        }
        
 

	public void initialize() 
        {
                        
                CommPortIdentifier portId = null;
          
                Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements())
                {
                   
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
				if (currPortId.getName().equals(commPort)) 
                                {
                   
					portId = currPortId;
					break;
				}
			}
		//}
                 
		if (portId == null)
                {
                       
                        JOptionPane.showMessageDialog(null, "Could not find COM port");
			System.exit(0);
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
                }catch(Exception ee)
                {
                    System.out.println("error here "+ "");
                }
             
                
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() 
        {
		if (serialPort != null) 
                {
			serialPort.removeEventListener();
			serialPort.close();
                        System.out.println("port closed successfully");
		}
	}
       
       
        public void sendData(String amount) throws IOException
        {
            System.out.println("inside sendData"+Thread.currentThread().getName());
            byte[] arr = amount.getBytes();
            output.write(arr);
            System.out.print("Sending amount:");
            System.out.print(amount);
       
        }          
	/**
	 * Handle an event on the serial port. Read the data and print it.
     * @param oEvent
	 */
        @Override
	public synchronized void serialEvent(SerialPortEvent oEvent) 
        {
	    System.out.println("inside event "+Thread.currentThread().getName());	
            if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) 
            {
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
                                 if(AllgamesList.contains(gamename))
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
                                   // MyLog.getLogger().severe(inputLine);
                                    FactoryClass.getMainPageObj().message(inputLine,"Error");
                                }
                                else if( scanner.hasNext("LOG"))
                                {
                                    //MyLog.getLogger().info(inputLine);
                                }
                                else if( scanner.hasNext("SUCCESS"))
                                {
                                    //What is this inputLine value in different cases
                                    FactoryClass.getMainPageObj().message(inputLine,"Recharge");
                                 
                                }
                               
			}
                        catch( java.io.IOException e)
                        {
                            FactoryClass.getMainPageObj().message("Recharge module removed, Login again ","RechargeModuleError");
                            System.exit(0);
                        }
                        catch (Exception e) 
                        {
                          
				System.err.println(e.toString());
			}
		}
		
	}
        
        protected void finalize()
        {
      
            
            close();
        }        
}