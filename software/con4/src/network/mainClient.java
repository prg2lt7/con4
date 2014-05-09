package network;

/**
 *
 * @author Pascal Häfliger <pascal.haefliger.01@stud.hslu.ch>
 */
public class mainClient
{
    public static void main(String[] args)
    {
        Network cl = new Network();
            
        boolean clstatus = cl.joinGame("localhost");
        System.out.println("Client: Status" + clstatus);
    
        System.out.println("The move was: " + cl.getMove());
    }
}
