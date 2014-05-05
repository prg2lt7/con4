/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author daniw
 */
public class Field
{
    private static final int DEFAULTWIDTH = 7;
    private static final int DEFAULTHEIGHT = 6;
    int[][] field;

    public Field()
    {
        field = new int[DEFAULTWIDTH][DEFAULTHEIGHT];
        for (int i = 0; i < DEFAULTWIDTH; i++)
        {
            for (int j = 0; j < DEFAULTHEIGHT; j++)
            {
                field[i][j] = 0;
            }
        }
    }
    
    public Field(int width, int height)
    {
        if (width <= 0)
        {
            width = DEFAULTWIDTH;
        }
        if (height <= 0)
        {
            height = DEFAULTHEIGHT;
        }
        field = new int[width][height];
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                field[i][j] = 0;
            }
        }
    }

    public int[][] getField()
    {
        return field;
    }
    
    public boolean putStone(int x, int value)
    {
        int y = 0;
        while (y < field[x].length && field[x][y] != 0)
        {
            y++;
        }
        if (y < field[x].length)
        {
            field[x][y] = value;
            return true;
        }
        return false;
    }
    
    public boolean putStone(int x, int y, int value)
    {
        if (field[x][y] == 0)
        {
            field[x][y] = value;
            return true;
        }
        return false;
    }
}
