/*
 * Class Game contains all game parameters.
 */
package logic;

/**
 *
 * @author Fabian
 */
public class Game
{

    private static Field field;
    private static State state;

    /**
     * default constructor creates game with default sized field (7*6)
     */
    public Game()
    {
        field = new Field();
        state = new State();

    }

    public Game(int width, int height)
    {
        field = new Field(width, height);
        state = new State();
    }

}
