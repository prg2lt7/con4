package logic;

/**
 * Class Game contains all game parameters.
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

    /**
     * constructor for customized field size (x*y)
     * @param width
     * @param height 
     */
    public Game(int width, int height)
    {
        field = new Field(width, height);
        state = new State();
    }

    
    
    //Getter und Setter Methods
    
    /**
     * returns the field of the Game
     * @return 
     */
    public Field getField()
    {
        return field;
    }
    
    /**
     * returns game status (which palyer has the move)
     * @return 
     */
    public State getState()
    {
        return state;
    }
}
