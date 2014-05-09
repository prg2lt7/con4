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
        System.out.println("Client: staus = " + clientstatus +"\n\n");
        
        for (int a=125 ; a >= 1 ; a--)
        {
            try
            {
                System.out.println(client.getMove());
                Thread.sleep(100);
                client.setMove(a);
            }
            
            catch (InterruptedException ex)
            {
                System.err.println(ex.getMessage());
            }  
        }
        
    }
}
