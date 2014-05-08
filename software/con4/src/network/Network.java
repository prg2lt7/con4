package network;

import java.net.DatagramSocket;
import java.net.InetAddress;
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
    
    
    /*
    * Socket References are beeing handled by the NetworkHandler and not the Server/Client Classes
    */
    //private DatagramSocket UdpHandler;
    private Socket myTcpServer;
    private Socket myTcpClient;
    
    private InetAddress otherAddress;
    
    
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
    private int status;

        
    /*
    * Constructor
    */
    public Network()
    {
        this.status = 0;      
    }

    
    /**
     *
     * @param ip String of the IP Address you want to connect (e.i. "192.168.4.12")
     * @return The status of the connection (true = sccessful, false = connection failed)
     */
    public boolean joinGame(String ip)
    {
        boolean conStatus = false;
        return conStatus;
    }
    
    /*
    * Creates a new TcpHost and waits for a client
    */
    public void createGame()
    {
        
    }
    
    /*
    * @return Returns the column of the new Disk
    */
    public int getMove()
    {
        int column = 0;
        
        return column;
    }
    
    /*
    * @column The column the new Disk should be dropped
    */
    public void setMove(int column)
    {
        
    }
    
    /*
    * Return the actual state of the Network
    */
    public int getStatus()
    {
        return status;
    }  
           
}