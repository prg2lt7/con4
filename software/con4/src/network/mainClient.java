package network;

/**
 *
 * @author Pascal Häfliger <pascal.haefliger.01@stud.hslu.ch>
 */
public class mainClient
{
    public static void main(String[] args) throws InterruptedException
    {
        Network client = new Network();
        
        System.out.println("Server: staus = "
                + client.joinGame("localhost")
                + "\n\n");

        Thread t = new Thread(client);
        t.start();
        
        //Ping-pong mit anderer Seite
        for(int a = 7 ; a > 1 ; a--)
        {
            System.out.println("Received: " + client.receMsg()); 
            t.sleep(1000);
            System.out.println("Sending: " + a);
            client.sendMsg(a);            
        }
        client.stopThread();
        
        System.out.print("Connection successfully closed");       
    }
}