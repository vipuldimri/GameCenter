
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


public class CommPortTest implements SerialPortEventListener {
	SerialPort serialPort;
        
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
            initialize();
        }
        
     
	public void initialize() 
        {
               
                
		
                CommPortIdentifier portId = null;
          
                Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
                
		while (portEnum.hasMoreElements()) {
                   
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
                       
				if (currPortId.getName().equals(commPort)) {
                                    
					portId = currPortId;
					break;
				}
			}
		
		if (portId == null) {
                        
                        JOptionPane.showMessageDialog(null, "Could not find COM port");
		
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
                       
		}
                // Multithread to read from both threads
             
	}


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
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
			        String inputLine=input.readLine();
                                System.out.println(inputLine);
                                
                                Scanner scanner = new Scanner(inputLine);
                               
                           
                               
			}
                        catch( java.io.IOException e)
                        {
                            
                        }
                        catch (Exception e) {
                            
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}
        
        protected void finalize()
        {
            close();
        }        
}