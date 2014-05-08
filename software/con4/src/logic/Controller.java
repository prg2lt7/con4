/*
 * class Controller controls the game play.
 * checks if move is valid or invalid.
 */
package logic;

/**
 *
 * @author Fabian
 */
public class Controller
{

    private static Game game; //typ ist Game, gibt es aber noch nicht

    /**
     * default constructor creates new Game
     */
    public Controller()
    {
        game = new Game();
    }

}
