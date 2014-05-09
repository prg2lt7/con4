package network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Pascal Häfliger <pascal.haefliger.01@stud.hslu.ch>
 */
public class Network extends Thread
{
    /*
    * Tcp Port
    */
    private static final int port = 29000;
    
    /* 
    * Duration in [ms] to be active as UdpServer
    * else switching to udp client
    */
    //private static final int timeout = 4000;
    
    private Socket myTcpClient;
    
    //private InetAddress otherAddress;
    
    
    /*
    * 0x : Udp Listen for requests
    * 
    * 1x : Udp Send requests
    *
    * 2x : TcpClient
    *
    * 3x : TcPServer
    *
    * 9x : Error, Exception
    */
    //private int status;

        
    /*
    * Constructor
    */
    public Network()
    {
        //this.status = 0;      
    }

    
    /**
     *
     * @param ip String of the IP Address you want to connect (e.i. "192.168.4.12")
     * @return The status of the connection (true = sccessful, false = connection failed & aborted)
     */
    public boolean joinGame(String ip)
    {
        System.out.println("Client: The destination address is: " + ip);
        try
        {
            myTcpClient = new Socket(ip,port);
            System.out.println("Client: Connected");
            return true;
        }
        
        catch (IOException ex)
        {
            System.err.println("Client: Connection as Client failed");
            return false;
        }
    }
    

    /**
     *
     * @return The status of the connection (true = sccessful, false = connection failed & aborted)
     */
    public boolean createGame()
    {
        try
        {
            System.out.println("Server: Waiting for accept();");
            ServerSocket dummy = new ServerSocket(port);
            myTcpClient = dummy.accept();
            
            System.out.println("Server: Connected");
            return true;
        }
        
        catch (IOException ex)
        {
            System.err.println("Server: Connection failed");
            return true;
        }
    }
    
    
    /*
    *
    * @return Returns the column of the new Disk
    */
    public int getMove()
    {
        int column = -1;
        //Link Writer to the Socket
        
        try
        {
            //PrintWriter outStream = new PrintWriter(myTcpClient.getOutputStream()); 
         
            //Link Writer to the Socket
            BufferedReader inStream = new BufferedReader( 
                new InputStreamReader(myTcpClient.getInputStream()));

            //Send data
            //outStream.println("hslu.ch"); 
            //outStream.flush(); 
            String line; 

            //Data beeing read by Server
            while ((line = inStream.readLine()) != null)
            { 
                System.out.println(line);
            }
                 
            column = Integer.parseInt(line);
            System.out.println(column);
            
        }
        catch (IOException ex)
        {
            System.err.println("Error in Method setMove");
            System.err.println(ex.getMessage());
        }
        
        return column;
    }
    
    /*
    *
    * @column The column the new Disk should be dropped
    */
    public void setMove(int column)
    {
        try
        {
            DataOutputStream dout = new DataOutputStream(myTcpClient.getOutputStream()); 
            String data = (""+column);
            dout.write(data.getBytes()); 
            System.out.println("Server sending: " + data);
        }
        
        catch(IOException ex)
        {
            System.err.println("Server: Error in Method setMove");
            System.err.println("Server: Error: " + ex.getMessage());
        }            
    }
    
    /*
    *
    * Return the actual state of the Network connection
    */
    /*
    public int getStatus()
    {
        return status;
    }
    */
           
}