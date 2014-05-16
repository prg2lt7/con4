package network;

import logic.Controller;

/**
 *
 * @author Pascal Häfliger <pascal.haefliger.01@stud.hslu.ch>
 */
public class mainServer
{
    public static void main(String[] args) throws InterruptedException
    {
        Network server = new Network(new Controller());
        server.createGame();

        Thread t = new Thread(server);
        t.start();
        
        //Ping-pong mit anderer Seite
        for(int a = 0 ; a < 7 ; a++)
        {
            System.out.println("Sending: " + a);
            server.sendMsg(a);
            t.sleep(64*a+400);
            System.out.println("Received: " + server.receMsg());
            t.sleep(23*a+631);
        }
        
        server.stopThread();        
        System.out.print("Connection successfully closed");       
    }
}
