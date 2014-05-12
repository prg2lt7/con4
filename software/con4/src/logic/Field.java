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
        if (x < field.length)
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

        for (y = (field[0].length - 1); y >= 0; y--)
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

    /**
     * Check if someone has won the game. 
     * If no Player has won the Game, 0 is returned. 
     * If a Player has won the Game, then the Players number is returned. 
     * @return value representing winner of the game
     */
    public int isWinner()
    {
        int[] line = new int[4];
        
        // check horizontal lines
        for (int x = 0; x < field.length - 4; x++)
        {
            for (int y = 0; y < field[x].length; y++)
            {
                line[0] = field[x][y];
                line[1] = field[x + 1][y];
                line[2] = field[x + 2][y];
                line[3] = field[x + 3][y];
                if (winLine(line));
                {
                    return field[x][y];
                }
            }
        }
        
        // check vertical lines
        for (int x = 0; x < field.length; x++)
        {
            for (int y = 0; y < field[x].length - 4; y++)
            {
                line[0] = field[x][y];
                line[1] = field[x][y + 1];
                line[2] = field[x][y + 2];
                line[3] = field[x][y + 3];
                if (winLine(line));
                {
                    return field[x][y];
                }
            }
        }
        
        // check diagonal lines from bottom left to top right
        for (int x = 0; x < field.length - 4; x++)
        {
            for (int y = 0; y < field[x].length - 4; y++)
            {
                line[0] = field[x][y];
                line[1] = field[x + 1][y + 1];
                line[2] = field[x + 2][y + 2];
                line[3] = field[x + 3][y + 3];
                if (winLine(line));
                {
                    return field[x][y];
                }
            }
        }
        
        
        // check diagonal lines from top left to bottom right
        for (int x = 0; x < field.length - 4; x++)
        {
            for (int y = 3; y < field[x].length; y++)
            {
                line[0] = field[x][y];
                line[1] = field[x + 1][y - 1];
                line[2] = field[x + 2][y - 2];
                line[3] = field[x + 3][y - 3];
                if (winLine(line));
                {
                    return field[x][y];
                }
            }
        }
        
        return 0;
    }

    /**
     * Check if four elements in one line are equal. 
     * Only the four elements at the beginning are checked. 
     * If the line is too short, false is returned. 
     * @param line line to be checked
     * @return true if all elements are equal
     */
    private boolean winLine(int[] line)
    {
        if (line.length < 4)
        {
            return false;
        }
        if (line[0] == 0)
        {
            return false;
        }
        if (line[0] != line[1])
        {
            return false;
        }
        if (line[0] != line[2])
        {
            return false;
        }
        if (line[0] != line[3])
        {
            return false;
        }
        return true;
    }
}