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
    private int[][] field;

    /**
     * Constructor for a field. 
     * This constructor uses the default values for width and height of the 
     * field. 
     */
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

    /**
     * Constructor for a field. 
     * height and width can be specified. If either of them is lower than 4, 
     * it is replaced with its default value. 
     * @param width width of the field
     * @param height height of the field
     */
    public Field(int width, int height)
    {
        if (width < 4)
        {
            width = DEFAULTWIDTH;
        }
        if (height < 4)
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

    /**
     * Returns the entire field as a two dimensional array. 
     * @return field
     */
    public int[][] getField()
    {
        return field;
    }

    /**
     * Put one stone in a column.
     * @param x column in which the stone has to be placed
     * @param value color of the stone to be inserted, indicates player
     * @return boolean value that indicates if a stone has been placed
     *         true if stone is placed in column x
     *         false if x out of range or if column x was full
     */
    public boolean putStone(int x, int value)
    {
        int y = 0;
        if (x >= field.length)
        {
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
        return false;
    }

    /**
     * Override for the toString method.
     * Returns the field in the format as follows: 
     * +-+-+-+-+-+-+-+
     * | | | | | | | |
     * +-+-+-+-+-+-+-+
     * | | | | | | | |
     * +-+-+-+-+-+-+-+
     * | | |X|O| | | |
     * +-+-+-+-+-+-+-+
     * | |X|O|X|X| | |
     * +-+-+-+-+-+-+-+
     * | |O|O|O|X| | |
     * +-+-+-+-+-+-+-+
     * | |O|X|X|O|X| |
     * +-+-+-+-+-+-+-+
     * @return String representation of the field
     */
    @Override
    public String toString()
    {
        int x;
        int y;
        String s = "+";

        for (x = 0; x < field.length; x++)
        {
            s += "-+";
        }
        s += "\n";

        for (y = 0; y < field[0].length; y++)
        {
            s += "|";
            for (x = 0; x < field.length; x++)
            {
                switch (field[x][y])
                {
                    case 0:
                        s += " |";
                        break;
                    case 1:
                        s += "X|";
                        break;
                    case 2:
                        s += "O|";
                        break;
                    default:
                        s += "#|";
                        break;
                }
            }
            s += "\n";

            s += "+";
            for (x = 0; x < field.length; x++)
            {
                s += "-+";
            }
            s += "\n";
        }
        return s;
    }
}