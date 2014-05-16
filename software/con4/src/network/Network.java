package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import logic.Controller;

/**
 * @author Pascal Häfliger <pascal.haefliger.01@stud.hslu.ch>
 */
public class Network implements Runnable
{
    // Tcp Port
    private static final int port = 29000;
 
    // Socket used as Client/Server
    private Socket myTcpClient;
    
    private DataOutputStream outstream;
    private DataInputStream instream;
    
    private Controller control;

    // true:  Keep receiving packages
    // false: Stop receiving packages
    private boolean running;
    
    
    private static final int def_value = 124;
    private static final int err_value = 125;
    private static final int no_new_Disk_value = 100;
    
    
    private int sendmsg;
    private int recemsg;
                         
    public Network(Controller control)
    {
        this.running = true;
        this.sendmsg = def_value;
        this.recemsg = def_value;
        this.control = control;
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
            //dummy.close();
              
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
     * @throws IOException 
     */
    private void initStream() throws IOException
    {
        instream = new DataInputStream(myTcpClient.getInputStream());
        outstream = new DataOutputStream(myTcpClient.getOutputStream());        
    }
   
    
    /**
     *
     * @return Returns the column of the new Disk:
     */  
    private void receiveDiskPos()
    {
        try
        {
            int buffer;
            
            //WARNING: The IO-operation ".readByte()" puts this Thread to the blocked life-cycle  untill IO input is done!
            buffer = instream.readByte();
            
            if( (buffer>=0) && (buffer<=7) )
            {
                // Store only when message within bound
                recemsg = buffer;
                control.setReceivedFlag(true);
            }
            else
            {
                recemsg = no_new_Disk_value;
            }
        }

        catch (IOException ex)
        {
            System.err.println("Exception in Method receiveDiskPos " + ex.getMessage());
            recemsg =  err_value;
        }  
    }
   
    
    /**
     *
     * @param column The column the new Disk should be dropped
     */  
    private void sendDiskPos()
    {
        try
        {            
            if( (sendmsg>=0) && (sendmsg<=7))
            {
                // Send only when message within bound
                outstream.write(sendmsg);
                outstream.flush();
                sendmsg = no_new_Disk_value;
            }
        }
        
        catch(IOException ex)
        {
            System.err.println("Error in Method sendDiskPos: " + ex.getMessage());
            sendmsg = err_value;
        }            
    }
    
    
    /**
     * 
     * @param sendMsg The message that you would like send over the network
     */
    public void sendMsg(int sendMsg)
    {
        this.sendmsg = sendMsg;     
    }
    
    
    /**
     * 
     * @return The message that you received over the network.
     */
    public int receMsg()
    {
        int buffer2 = recemsg;
        recemsg = no_new_Disk_value;
        control.setReceivedFlag(false);
        return buffer2;
    }

    
    /**
     * Stops the Thread
     */
    public void stopThread()
    {
        this.running = false;
    }
      
          
    /**
     * 
     * @return Status of the closing procedure
     *          true: connection successfully closed
     *          false: failed to close the connection correctly
     */
    private boolean closeConnection()
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
    

    @Override
    public void run()
    {
        while (running)
        {
            sendDiskPos();
            receiveDiskPos();          
        }
        closeConnection();
    }
}