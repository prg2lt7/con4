package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private boolean running;
            
    /***
     * 
     */       
    public Network()
    {
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
     * @return Status of the closing procedure
     *          true: connection successfully closed
     *          flase: failed to close the connection correctly
     */
    public boolean close()
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
    
    
    /**
     *
     * @return Returns the column of the new Disk:
     *           125 Error
     *           120 Loop stopped (running=false)
     *           1,2,..,7 Legal move
     */  
    public int getMove()
    {
        while (running)
        {
            try
            {
                return(instream.readByte());
            }
            
            catch (IOException ex)
            {
                System.err.println("Exception in Method getMove " + ex.getMessage());
                return 125;
            }
        }  
        //Loop stopped
        return 120;
    }
   
    
    /**
     *
     * @param column The column the new Disk should be dropped
     */  
    public void setMove(int column)
    {
        try
        {            
            int sendmessage = column;
            outstream.write(sendmessage);
            outstream.flush();
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
}