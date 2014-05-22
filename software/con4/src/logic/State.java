package logic;

import java.io.Serializable;

/**
 * contains all status informations about current game
 * @author Fabian
 */
public class State implements Serializable
{

    private boolean userMove;
    private boolean gameOver;

    /**
     * default constructor the user has the first move if the game is against
     * the local opponent the user that generates the server has the first move
     */
    public State()
    {
        userMove = true;
        gameOver = false;
    }

    /**
     * changes the player that has the move
     * @return
     */
    public boolean toggleState()
    {
        userMove = !userMove;
        return userMove;
    }

    /**
     * returns information about who has the move
     * @return 
     */
    public int getUserMove()
    {
        if (userMove == true)
        {
            return 1;
        }
        return 2;
    }

    /**
     * returns information about game state
     * if game is over -> true
     * @return 
     */
    public boolean isGameOver()
    {
        return gameOver;
    }

    /**
     * modifies game over attribut
     * @param gO 
     */
    public void setGameOver(boolean gO)
    {
        gameOver = gO;
    }
}
