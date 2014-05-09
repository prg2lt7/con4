package logic;

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
    
    public Game setStone()
    {
        //is field full?
        int x = 0;
        while ((game.getField().getField()[x][game.getField().getField()[x].length - 1] != 0) && (x < game.getField().getField().length))
        {
            x++;
        }
        
        //if field not full - play
        if (game.getField().getField()[x][game.getField().getField()[x].length - 1] == 0)
        {
            opponent.move(game.getField());
        }
        else
        {
            //game over (draw)
        }
        System.out.println(game.getField());
        
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