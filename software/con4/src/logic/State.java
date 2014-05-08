package logic;

/**
 * 
 * @author Fabian
 */
public class State
{
    private boolean usermove;
    /**
     * default constructor
     * the user has the first move if the game is against the local opponent
     * the user that generates the server has the first move
     */
    public State()
    {
        usermove = true;
    }
    
    /**
     * changes the move 
     * @return 
     */
    public boolean toggleState(){
        usermove = !usermove;
        return usermove;
    }
}