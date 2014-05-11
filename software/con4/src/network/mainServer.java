package network;

import java.util.logging.Level;
import java.util.logging.Logger;

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

        //Ping-pong mit anderer Seite
        for (int a=0 ; a < 125 ; a++)
        {
            System.out.println("Number received : " + server.receiveDiskPos());

            System.out.println("Sending number :" + a);
            server.sendDiskPos(a);
            
            Thread.sleep(1000);
        }
        server.close();
        System.out.print("Connection successfully closed");       
    }
}
