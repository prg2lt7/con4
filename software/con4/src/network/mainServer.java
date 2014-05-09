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
        for(int a = 0 ; a < 125 ; a++)
        {
            try
            {
                System.out.print("Sending number :" + a);
                server.setMove(a);
                
                Thread.sleep(1000);
                
                System.out.println("Received number: " + server.getMove());
            }
            
            catch (InterruptedException ex)
            {
                System.err.println(ex.getMessage());
            }
        }
        System.out.print("Connection closed successfully: " + server.close());       
    }
}
