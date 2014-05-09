package network;

/**
 *
 * @author Pascal Häfliger <pascal.haefliger.01@stud.hslu.ch>
 */
public class mainServer
{
    public static void main(String[] args)
    {
        Network server = new Network();
    
        boolean serverstatus = server.createGame();
        System.out.println("Server: staus = " + serverstatus +"\n\n");

        //Ping-pong mit anderer Seite
        server.start();
        
        for (int a=0 ; a < 125 ; a++)
        {
            System.out.println("Number received : " + server.receiveMessage());

            System.out.println("Sending number :" + a);
            server.setMessage(a);
            
        }
        server.stopThread();
        System.out.print("Connection successfully closed");       
    }
}
