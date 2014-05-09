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

        for(int a = 0 ; a < 125 ; a++)
        {
            try
            {
                serv.setMove(a);
                Thread.sleep(1000);
                System.out.println(serv.getMove());
            }
            
            catch (InterruptedException ex)
            {
                System.err.println(ex.getMessage());
            }
        }
        
    }
}
