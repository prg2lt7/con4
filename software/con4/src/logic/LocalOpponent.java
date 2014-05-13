/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Random;
import sun.security.util.Length;

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
     * Function that calculates the next stone. 
     * If the Field is filled, the Function will return -1. 
     * @param field Field in which the stone should be placed. 
     * @return Colomn in which the stone should be placed. 
     */
    @Override
    public int move(Field field)
    {
        int x;
        int y;
        boolean filled = false;
        boolean full = true;
        for (x = 0; x < field.getField().length; x++)
        {
            if (field.getField()[x][field.getField()[x].length - 1] == 0)
            {
                full = false;
            }
        }
        if (full)
        {
            return -1;
        }
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
