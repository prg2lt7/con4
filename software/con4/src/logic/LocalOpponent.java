/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Random;

/**
 *
 * @author daniw
 */
public class LocalOpponent extends Opponent
{
    private int value;
    private int difficulty;
    
    /**
     * 
     * @param value 
     */
    public LocalOpponent(int value)
    {
        this.value = value;
        this.difficulty = 0;
    }

    /**
     * 
     * @return 
     */
    public int getDifficulty()
    {
        return difficulty;
    }

    /**
     * 
     * @param difficulty 
     */
    public void setDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }

    /**
     * 
     * @return 
     */
    public int getValue()
    {
        return value;
    }

    /**
     * 
     * @param value 
     */
    public void setValue(int value)
    {
        this.value = value;
    }
    
    /**
     * 
     * @param field
     * @return 
     */
    @Override
    public int move(Field field)
    {
        int x;
        int y;
        boolean filled = false;
        Random randomGen = new Random();
        do
        {
            x = randomGen.nextInt(field.getField().length);
            y = 0;
            while (y < field.getField()[x].length && field.getField()[x][y] != 0)
            {    
                y++;
            }
            if (y < field.getField()[x].length)
            {
                filled = true;
            }
        } while (!filled);
        return x;
    }
}
