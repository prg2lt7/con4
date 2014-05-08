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
            
        boolean clstatus = cl.joinGame("127.0.0.0");
        System.out.println(clstatus);
        
    }
}
