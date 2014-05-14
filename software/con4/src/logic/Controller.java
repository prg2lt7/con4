package logic;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 *class Controller controls the game play.
 *checks if move is valid or invalid.
 * @author Fabian
 */
public class Controller
{

    private static Game game;
    private static Opponent opponent;

    /**
     * default constructor creates new Game
     */
    public Controller()
    {
        game = new Game();
        opponent = new LocalOpponent(2);
        
    }
    
    /**
     * Player or opponent intends to set a stone at position x,y
     * Method checks for possibilty to set stone at requested position
     * returns if stone could be set to position (true) or not (false)
     * @param xPosition
     * @return 
     */
    public Game setStone(int xPosition)
    {
        //is field full?
        //checks if there is any free position (0) in a column
        int x = 0;
        while ((game.getField().getField()[x][game.getField().getField()[x].length - 1] != 0) &&
                (x < game.getField().getField().length))
        {
            x++;
        }
        
        //if field not full (at least one free position (0) - continue gameplay!
        if (game.getField().getField()[x][game.getField().getField()[x].length - 1] == 0)
        {
            opponent.move(game.getField());
        }
        else //game over (draw)
        {
            game.getState().setGameOver(true);
        }
        return game; 
        
    }
    
    public boolean saveGame()
    {
        return true;
    }
    
    public Game loadGame()
    {
        return game;
    }    
}