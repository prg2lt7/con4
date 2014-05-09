package network;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pascal Häfliger <pascal.haefliger.01@stud.hslu.ch>
 */
public class mainClient
{
    public static void main(String[] args)
    {
        Network client = new Network();
           
        boolean clientstatus = client.joinGame("localhost");
        System.out.println("Client: staus = " + clientstatus +"\n\n");
        
        //Ping-pong mit anderer Seite
        
        client.start();
        
        for (int a=125 ; a >= 1 ; a--)
        {
            System.out.println("Number received : " + client.receiveMessage());

            System.out.println("Sending number :" + a);
            client.setMessage(a);   
            
            
            try
            {
                client.sleep(5000);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(mainClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        client.stopThread();
        System.out.print("Connection successfully closed");
    }
}
