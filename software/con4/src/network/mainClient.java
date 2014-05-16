package network;

import logic.Controller;

/**
 *
 * @author Pascal Häfliger <pascal.haefliger.01@stud.hslu.ch>
 */
public class mainClient
{
    public static void main(String[] args) throws InterruptedException
    {
        Network client = new Network(new Controller());
        client.joinGame("localhost");

        Thread t = new Thread(client);
        t.start();
        
        //Ping-pong mit anderer Seite
        for(int a = 7 ; a > 0 ; a--)
        {
            System.out.println("Received: " + client.receMsg()); 
            t.sleep(39*a+419);
            System.out.println("Sending: " + a);
            client.sendMsg(a);
            t.sleep(61*a+523);
        }
        
        client.stopThread();        
        System.out.print("Connection successfully closed");       
    }
}