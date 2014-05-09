package network;

/**
 *
 * @author Pascal Häfliger <pascal.haefliger.01@stud.hslu.ch>
 */
public class mainServ
{
    public static void main(String[] args)
    {
        Network serv = new Network();
    
        boolean servstatus = serv.createGame();
        System.out.println("Server: staus = " + servstatus +"\n\n");

        //Ping-pong mit anderer Seite
        for(int a = 0 ; a < 125 ; a++)
        {
            try
            {
                System.out.print("Sending number :" + a);
                serv.setMove(a);
                
                Thread.sleep(1000);
                
                System.out.println("Received number: " + serv.getMove());
            }
            
            catch (InterruptedException ex)
            {
                System.err.println(ex.getMessage());
            }
        }
        
    }
}
