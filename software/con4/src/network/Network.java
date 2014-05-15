package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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

    // true:  Keep receiving packages
    // false: Stop receiving packages
    private boolean running;
    
    
    private static final int def_value = 124;
    private static final int err_value = 125;
    private static final int no_new_Disk_value = 100;
    
    
    private int sendmsg;
    private int recemsg;
                         
    public Network()
    {
        this.running = true;
        this.sendmsg = def_value;
        this.recemsg = def_value;
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
            dummy.close();
              
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
            //WARNING: The following IO-operation puts this Thread to the blocked life-cycle!
            recemsg = instream.readByte();
        }

        catch (IOException ex)
        {
            System.err.println("Exception in Method getMove " + ex.getMessage());
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
            outstream.write(sendmsg);
            outstream.flush();
            sendmsg = no_new_Disk_value;
        }
        
        catch(IOException ex)
        {
            System.err.println("Error in Method setMove: " + ex.getMessage());
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
        return recemsg;
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
    

    @Override
    public void run()
    {
        while (running)
        {
            if(sendmsg >= 0 && sendmsg >= 7)
            {
                sendDiskPos();
            }
            if(recemsg >= 8 || recemsg < 0)
            {
                receiveDiskPos();
            }            
        }
        
        //Close the socket
        close();
    }
}