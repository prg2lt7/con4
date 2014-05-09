package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Pascal Häfliger <pascal.haefliger.01@stud.hslu.ch>
 */
public class Network extends Thread
{
    // Tcp Port
    private static final int port = 29000;
 
    // Socket used as Client/Server
    private Socket myTcpClient;
    
    private DataOutputStream outstream;
    private DataInputStream instream;

    // true:  Keep receiving packages
    // false: Stop receiving packages
    private boolean listening;
    
    // true:  Keep this thread running
    // false: Stop this read and thread dies      
    private boolean running;
    
    
    // Für Thread
    private int receivemessage = 99;
    private int sendmessage = 99;
    
                  
    public Network()
    {
        super();
        this.listening = true;
        this.running = true;   
    }
    
    
    /**
     *
     * @param ip String of the IP Address you want to connect (e.i. "192.168.4.12")
     * @return The status of this TCP connection (true = successful, false = connection failed & aborted)
     */
    public boolean joinGame(String ip)
    {
        System.out.println("Client: The destination address is: " + ip);
        try
        {
            myTcpClient = new Socket(ip,port);
            System.out.println("Client: Connected");
            
            initStream();
            System.out.println("Client: Stream initialized");

            return true;
        }
        
        catch (IOException ex)
        {
            System.err.println("Client: Connection failed: " + ex.getMessage());            
            return false;
        }
    }
    
    
    /**
     *
     * @return The status of this TCP connection (true = successful, false = connection failed & aborted)
     */
    public boolean createGame()
    {
        try
        {
            System.out.println("Server: Waiting for accept();");
            ServerSocket dummy = new ServerSocket(port);
            myTcpClient = dummy.accept();
            System.out.println("Server: Connected");
              
            initStream();
            System.out.println("Server: Stream initialized");
            
            return true;
        }
        
        catch (IOException ex)
        {
            System.err.println("Server: Connection failed: " + ex.getMessage());
            return false;
        }
    }
   
    
    /**
     *
     * @return Returns the column of the new Disk:
           125 Error
           124 Initialize value
           120 Loop stopped (listening=false)
           100 none new Disk received
           1,2,..,7 Legal move
     */  
    private synchronized void receiveDiskPos()
    {
        while (listening)
        {
            try
            {
                //WARNING: The following IO-operation puts this Thread to the blocked life-cycle!
                receivemessage = instream.readByte();
                
                //old function for none-threadning was:
                //return(instream.readByte());
            }
            
            catch (IOException ex)
            {
                System.err.println("Exception in Method getMove " + ex.getMessage());
                //return 125;
            }
            
        }  
        //Loop stopped
        //return 120;
    }
   
    
    /**
     *
     * @param column The column the new Disk should be dropped
     */  
    private synchronized void sendDiskPos()
    {
        try
        {            
            outstream.write(sendmessage);
            outstream.flush();
            sendmessage = 100;
        }
        
        catch(IOException ex)
        {
            System.err.println("Error in Method setMove: " + ex.getMessage());
        }            
    }
    
    
    /**
     * 
     * @throws IOException 
     */
    private void initStream() throws IOException
    {
        instream = new DataInputStream(myTcpClient.getInputStream());
        outstream = new DataOutputStream(myTcpClient.getOutputStream());        
    }
        
    
    /**
     *
     * @param listening State of the method "getMove"
     *          true: Method is looping
     *          false: Loop will stop
     */
    public void setListening(boolean listening)
    {
        this.listening = listening;
    }
      
    
    /**
     *
     * @param sendmessage the message as an integer you would like to send (e.x.: 1,2,..,7)
     */
    public void setMessage(int sendmessage)
    {
        this.sendmessage = sendmessage;
    }
    
    
    /**
     *
     * @return the value as an integer it received from the other side
     */
    public int receiveMessage()
    {
        return receivemessage;
    }
    
    
    @Override
    public void run()
    {
        while(running)
        {
            receiveDiskPos();
            sendDiskPos();
        }
        this.close();
    }
    
     
    /**
     * 
     * @return Status of the closing procedure
     *          true: connection successfully closed
     *          false: failed to close the connection correctly
     */
    private boolean close()
    {
        try
        {
            myTcpClient.close();
            return true;
        } 
        
        catch (IOException ex)
        {
            System.err.println("Exception while closing the connection: " + ex.getMessage());
            return false;
        }
    }
    
    
    public void stopThread()
    {
        this.running = false;
    }
    
}