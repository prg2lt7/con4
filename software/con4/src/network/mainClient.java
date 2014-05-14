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

        //Ping-pong mit anderer Seite
        for (int a=125 ; a >0 ; a--)
        {
            System.out.println("Sending number :" + a);
            client.sendDiskPos(a);

            Thread.sleep(1000);  
            System.out.println("Number received : " + client.receiveDiskPos());
        }   
        client.close();
        System.out.print("Connection successfully closed");       
    }
}