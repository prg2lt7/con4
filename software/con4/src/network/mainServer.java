package network;

/**
 *
 * @author Pascal Häfliger <pascal.haefliger.01@stud.hslu.ch>
 */
public class mainServer
{
    public static void main(String[] args) throws InterruptedException
    {
        Network server = new Network();

        System.out.println("Server: staus = "
                + server.createGame()
                + "\n\n");

        Thread t = new Thread(server);
        t.start();
        
        //Ping-pong mit anderer Seite
        for(int a = 0 ; a < 127 ; a++)
        {
            server.sendMsg(a);
            t.sleep(1000);
            System.out.println("Received: " + server.receMsg());            
        }
        server.stopThread();
        
        System.out.print("Connection successfully closed");       
    }
}
