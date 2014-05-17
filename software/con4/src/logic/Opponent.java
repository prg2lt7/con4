/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author daniw
 */
public abstract class Opponent
{
    private int value;
    
    public Opponent(int value)
    {
        this.value = value;
    }
    
    /**
     * Get the value of the opponent.
     * @return value
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Set the value of the opponent.
     * @param value 
     */
    public void setValue(int value)
    {
        this.value = value;
    }

    public abstract int move(Field field);
    
}
