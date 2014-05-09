package network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Pascal Häfliger <pascal.haefliger.01@stud.hslu.ch>
 */
public class Network extends Thread
{
    // Tcp Port
    private static final int port = 29000;
 
    // Socket used as Client/Serve
    private Socket myTcpClient;
    
    private DataOutputStream outstream;
    private DataInputStream instream;

    // true:  Keep receiving packages
    // false: Stop receiving packages
    private boolean running;
            
            
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
            System.err.println("Client: Connection as Client failed");
            System.err.println("Error: " +ex.getMessage());
            
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
            System.err.println("Server: Connection failed");
            System.err.println("Error: " +ex.getMessage());
            return false;
        }
    }
    
    
    /*
    *
    * @return Returns the column of the new Disk
    */
    public int getMove()
    {
        String receivemessage="255";
        while (running)
        {
            System.out.println(receivemessage);
            return (Integer.parseInt(receivemessage));
        }
        
        return 255;
    }
    
    /*
    *
    * @column The column the new Disk should be dropped
    */
    public void setMove(int column)
    {
        try
        {            
            int sendmessage = column;
            outstream.write(sendmessage);
            outstream.flush();
            System.out.println("Server sending: " + sendmessage);
        }
        
        catch(IOException ex)
        {
            System.err.println("Server: Error in Method setMove");
            System.err.println("Server: Error: " + ex.getMessage());
        }            
    }  
    
    private void initStream() throws IOException
    {
        instream = new DataInputStream(myTcpClient.getInputStream());
        outstream = new DataOutputStream(myTcpClient.getOutputStream());        
    }
}