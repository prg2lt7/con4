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
        System.out.println("Server: staus = " + servstatus);
        
        serv.setMove(3);
    }
}
