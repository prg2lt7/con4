package logic;

import java.io.Serializable;

/**
 * Class Game contains all game parameters.
 * @author Fabian
 */
public class Game implements Serializable
{
    private Field field;
    private State state;

    /**
     * Default constructor. Creates game with default sized field (7*6)
     */
    public Game()
    {
        field = new Field();
        state = new State();

    }
    
    /**
     * Custom contructor. Creates game with custom field size
     * @param width
     * @param height 
     */
    public Game(int width, int height)
    {
        field = new Field(width, height);
        state = new State();
    }

    /**
     * returns field informations of current game
     * @return 
     */
    public Field getField()
    {
        return field;
    }

    /**
     * returns game status of current game
     * @return 
     */
    public State getState()
    {
        return state;
    }

    
    
    






}
