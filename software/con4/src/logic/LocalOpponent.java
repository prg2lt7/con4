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
public class LocalOpponent extends Player
{
    private int value;
    
    public LocalOpponent(int value)
    {
        this.value = value;
    }
    
    @Override
    public int play(int[][] field)
    {
        int x;
        int y;
        boolean filled = false;
        Random randomGen = new Random();
        do
        {
            x = randomGen.nextInt(field.length);
            y = 0;
            while (y < field[x].length && field[x][y] != 0)
            {    
                y++;
            }
            if (y < field[x].length)
            {
                //field[x][y] = value;
                filled = true;
            }
        } while (filled);
        return x;
    }
}
