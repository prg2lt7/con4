package network;

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
        System.out.println("Client: staus = " + clientstatus +"/n/n");
        
        System.out.println("Client: Move received was " + client.getMove());
    }
}
