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
    private int difficulty = 3;

    /**
     * 
     * @param value 
     */
    public LocalOpponent(int value)
    {
        super();
        this.value = value;
        this.difficulty = 1;
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
     * @return Column in which the stone should be placed. 
     */
    @Override
    public int move(Field field)
    {
        int x;
        int y;
        boolean filled = false;
        boolean full = true;
        
        // check if field is full
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
        
        // decide, which stone to set next
        switch (difficulty)
        {
            // fill randomly
            case 1:
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
                break;
            
            // look into the future
            case 2:
                int[] choice1 = new int[field.getField().length];
                for (int i = 0; i < field.getField().length; i++)
                {
                    findBestStone1(field.getField(), true, 20);
                }
                int max1 = choice1[0];
                x = 0;
                for (int i = 0; i < field.getField().length; i++)
                {
                    System.out.println(choice1[i]);
                    if (choice1[i] > max1)
                    {
                        max1 = choice1[i];
                        x = i;
                    }
                }
                break;
            
            // Analyze only winners
            case 3:
                int[] choice2 = new int[field.getField().length];
                for (int i = 0; i < field.getField().length; i++)
                {
                    findBestStone2(field.getField(), true, 20, 1);
                }
                int max2 = choice2[0];
                x = 0;
                for (int i = 0; i < field.getField().length; i++)
                {
                    if (choice2[i] > max2)
                    {
                        System.out.println(choice2[i]);
                        max2 = choice2[i];
                        x = i;
                    }
                }
                break;
            
            // fill from left
            default:
                x = 0;
                while (field.getField()[x][field.getField()[x].length - 1] != 0)
                {
                    x++;
                }
                break;
        }
        
        return x;
    }
    
    private int findBestStone1(int[][] currentfield, boolean myStone, int iteration)
    {
        int count = 0;
        Field tempfield = new Field(currentfield.length, currentfield[0].length);
        tempfield.setField(currentfield);
        count += analyzeField1(currentfield);
        if (iteration > 0 && tempfield.isWinner() == 0)
        {
            for (int x = 0; x < tempfield.getField().length; x++)
            {
                tempfield.setField(currentfield);
                tempfield.putStone(x, value);
                count += findBestStone1(tempfield.getField(), !myStone, iteration - 1)/2;
            }
        }
        return count;
    }

    private int findBestStone2(int[][] currentfield, boolean myStone, int iteration, int opponentvalue)
    {
        int count = 0;
        Field tempfield = new Field(currentfield.length, currentfield[0].length);
        tempfield.setField(currentfield);
        if (tempfield.getWinner() == 0)
        {
            count = 0;
        }
        if (tempfield.getWinner() == value)
        {
            count = 1000000;
        }
        if (tempfield.getWinner() != 0 && tempfield.getWinner() != value)
        {
            count = -1000000;
        }
        if (iteration > 0 && tempfield.isWinner() == 0)
        {
            for (int x = 0; x < tempfield.getField().length; x++)
            {
                tempfield.setField(currentfield);
                if (myStone)
                {
                    tempfield.putStone(x, value);
                }
                else
                {
                    tempfield.putStone(x, opponentvalue);
                }
                count += findBestStone2(tempfield.getField(), !myStone, iteration - 1, opponentvalue)/2;
            }
        }
        return count;
    }
    
    private int analyzeField1(int[][] field)
    {
        int[] line = new int[4];
        int x;
        int y;
        int count = 0;
        
        // check horizontal lines
        x = 0;
        while (x < field.length - 4)
        {
            y = 0;
            while (y < field[x].length)
            {
                line[0] = field[x][y];
                line[1] = field[x + 1][y];
                line[2] = field[x + 2][y];
                line[3] = field[x + 3][y];
                count += analyzeLine(line);
                y++;
            }
            x++;
        }
        
        // check vertical lines
        x = 0;
        while (x < field.length)
        {
            y = 0;
            while (y < field[x].length - 4)
            {
                line[0] = field[x][y];
                line[1] = field[x][y + 1];
                line[2] = field[x][y + 2];
                line[3] = field[x][y + 3];
                count += analyzeLine(line);
                y++;
            }
            x++;
        }
        
        // check diagonal lines from bottom left to top right
        x = 0;
        while (x < field.length - 3)
        {
            y = 0;
            while (y < field[x].length - 4)
            {
                line[0] = field[x][y];
                line[1] = field[x + 1][y + 1];
                line[2] = field[x + 2][y + 2];
                line[3] = field[x + 3][y + 3];
                count += analyzeLine(line);
                y++;
            }
            x++;
        }
        
        
        // check diagonal lines from top left to bottom right
        x = 0;
        while (x < field.length - 3)
        {
            y = 3;
            while (y < field[x].length)
            {
                line[0] = field[x][y];
                line[1] = field[x + 1][y - 1];
                line[2] = field[x + 2][y - 2];
                line[3] = field[x + 3][y - 3];
                count += analyzeLine(line);
                y++;
            }
            x++;
        }
        
        return count;
    }
    
    private int analyzeLine(int[] line)
    {
        // line empty
        if (line[0] == 0 && line[1] == 0 && line[2] == 0 && line[3] == 0)
        {
            return 0;
        }
        
        // Four of my own in one row
        if (line[0] == value && line[1] == value && line[2] == value && line[3] == value)
        {
            return 100000;
        }
        
        // Four of my opponent's in one row
        if (line[0] == line[1] && line[0] == line[2] && line[0] == line[3])
        {
            return -100000;
        }
        
        // Three of my own in a line with one open gap
        if ((line[0] == value && line[2] == value && line[3] == value && line[1] == 0) ||
            (line[0] == value && line[1] == value && line[3] == value && line[2] == 0))
        {
            return 0;
        }
        
        // Three of my opponent's in a line with one open gap
        if ((line[0] == line[2] && line[0] == line[3] && line[1] == 0) ||
            (line[0] == line[1] && line[0] == line[3] && line[2] == 0))
        {
            return -0;
        }
        
        // Three of my own in a row, open end
        if ((line[0] == value && line[1] == value && line[2] == value && line[3] == 0) ||
            (line[0] == 0 && line[1] == value && line[2] == value && line[3] == value))
        {
            return 0;
        }
        
        // Three of my opponent's in a row, open end
        if ((line[0] == line[1] && line[0] == line[2] && line[3] == 0) || 
            (line[0] == 0 && line[1] == line[2] && line[1] == line[3]))
        {
            return -0;
        }
        
        // Three of my own in a row, terminated
        if ((line[0] == value && line[1] == value && line[2] == value && line[3] != value) ||
            (line[0] != value && line[1] == value && line[2] == value && line[3] == value))
        {
            return 0;
        }
        
        // Three of my opponent's in a row, terminated
        if ((line[0] == line[1] && line[0] == line[2] && line[3] != line[0]) || 
            (line[0] != line[1] && line[1] == line[2] && line[1] == line[3]))
        {
            return -0;
        }
        
        return 0;
    }
}
