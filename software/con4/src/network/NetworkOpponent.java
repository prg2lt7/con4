package network;

import logic.Field;
import logic.Opponent;



/**
 *
 * @author Pascal Häfliger <pascal.haefliger.01@stud.hslu.ch>
 */
public class NetworkOpponent extends Opponent
{    
    Network network;
    
    public NetworkOpponent(Network network)
    {
        this.network = network;
        
    }
            
    @Override
    public int move(Field field)
    {
        return(100);
    }
    
}
