package logic;

/**
 *
 * @author Fabian
 */
public class State
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
     * changes the move
     *
     * @return
     */
    public boolean toggleState()
    {
        userMove = !userMove;
        return userMove;
    }

    public boolean getUserMove()
    {
        return userMove;
    }

    public boolean isGameOver()
    {
        return gameOver;
    }

    public void setGameOver(boolean gO)
    {
        gameOver = gO;
    }
}
