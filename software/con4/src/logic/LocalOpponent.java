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
    private int difficulty;
    private static final boolean DEBUG = true;

    /**
     * Constructor to create a local opponent. 
     * @param value represents the color of the opponent
     */
    public LocalOpponent(int value)
    {
        super(value);
        this.difficulty = 3;
    }

    /**
     * Constructor to create a local opponent. 
     * @param value represents the color of the opponent
     * @param difficulty defines the used algorithm to calculate the next stone
     */
    public LocalOpponent(int value, int difficulty)
    {
        super(value);
        this.difficulty = difficulty;
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
        
        // check if someone has won the game
        if (field.isWinner() != 0)
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
                Field tempfield1 = new Field(field.getField().length, field.getField()[0].length);
                //tempfield.setField(field.getField());
                int[] choice1 = new int[field.getField().length];
                for (int i = 0; i < field.getField().length; i++)
                {
                    tempfield1.setField(field.getField());
                    tempfield1.putStone(i, super.getValue());
                    choice1[i] = findBestStone1(tempfield1.getField(), false, 6);
                }
                int max1 = choice1[0];
                x = 0;
                for (int i = 0; i < field.getField().length; i++)
                {
                    if (DEBUG)
                    {
                        System.out.print(choice1[i] + " ");
                    }
                    if (choice1[i] > max1)
                    {
                        max1 = choice1[i];
                        x = i;
                    }
                }
                if (DEBUG)
                {
                    System.out.println("");
                }
                break;
            
            // Analyze only winners
            case 3:
                long mytime = System.currentTimeMillis();
                Field tempfield2 = new Field(field.getField().length, field.getField()[0].length);
                int[] choice2 = new int[field.getField().length];
                for (int i = 0; i < field.getField().length; i++)
                {
                    tempfield2.setField(field.getField());
                    tempfield2.putStone(i, super.getValue());
                    choice2[i] = findBestStone2(tempfield2.getField(), false, 6, 1);
                }
                int max2 = -2147483648;
                x = -1;
                for (int i = 0; i < field.getField().length; i++)
                {
                    if (DEBUG)
                    {
                        System.out.print(choice2[i] + " ");
                    }
                    if (choice2[i] > max2)
                    {
                        if (field.getField()[i][field.getField()[i].length - 1] == 0)
                        {
                            max2 = choice2[i];
                            x = i;
                        }
                    }
                }
                if (DEBUG)
                {
                    System.out.println("  " + x);
                    System.out.println(System.currentTimeMillis() - mytime);
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
    
    /**
     * Recursive function  to find the best stone, approach 1.
     * @param currentfield Field in which the stone should be set
     * @param myStone declared if the opponent plays the next stone
     * @param iteration counter do define how deep the recursion should be done
     * @return chosen stone position
     */
    private int findBestStone1(int[][] currentfield, boolean myStone, int iteration)
    {
        int count = 0;
        Field tempfield = new Field(currentfield.length, currentfield[0].length);
        tempfield.setField(currentfield);
        count += analyzeField1(currentfield);
        //System.out.println("Iteration: " + iteration);
        if (iteration > 0 && tempfield.isWinner() == 0)
        {
            for (int x = 0; x < tempfield.getField().length; x++)
            {
                tempfield.setField(currentfield);
                tempfield.putStone(x, super.getValue());
                count += findBestStone1(tempfield.getField(), !myStone, iteration - 1)/10;
            }
        }
        return count;
    }
    
    /**
     * Recursive function  to find the best stone, approach 1.
     * @param currentfield Field in which the stone should be set
     * @param myStone declared if the opponent plays the next stone
     * @param iteration counter do define how deep the recursion should be done
     * @return chosen stone position
     * @param playervalue value of the player
     * @return 
     */
    private int findBestStone2(int[][] currentfield, boolean myStone, int iteration, int playervalue)
    {
        int count = 0;
        Field tempfield = new Field(currentfield.length, currentfield[0].length);
        tempfield.setField(currentfield);
        if (tempfield.isWinner() == 0)
        {
            count = 0;
        }
        if (tempfield.isWinner() == super.getValue())
        {
            count = 16;
        }
        if (tempfield.isWinner() != 0 && tempfield.isWinner() != super.getValue())
        {
            count = -256;
        }
        if (iteration > 0 && tempfield.isWinner() == 0)
        {
            for (int x = 0; x < tempfield.getField().length; x++)
            {
                tempfield.setField(currentfield);
                if (myStone)
                {
                    tempfield.putStone(x, super.getValue());
                }
                else
                {
                    tempfield.putStone(x, playervalue);
                }
                count += findBestStone2(tempfield.getField(), !myStone, iteration - 1, playervalue)/2;
            }
        }
        else
        {
            if (DEBUG)
            {
                if (tempfield.isWinner() != 0 && iteration > 4)
                {
                    System.out.println(iteration + " " + tempfield.isWinner());
                    System.out.println(tempfield);
                }
            }
        }
        return count;
    }
    
    /**
     * Function to analyze a field. 
     * All combinations of four stoned are analyzed. 
     * For each combination a value is calculated and then summarized. 
     * @param field field to analyze
     * @return value for actual field
     */
    private int analyzeField1(int[][] field)
    {
        int[] line = new int[4];
        int x;
        int y;
        int count = 0;
        
        // check horizontal lines
        x = 0;
        while (x < field.length - 3)
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
            while (y < field[x].length - 3)
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
            while (y < field[x].length - 3)
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
    
    /**
     * Function to analyze a line containing four elements. 
     * Some compositions of stones are valued. 
     * This value defines, how positive this composition for the opponent. 
     * @param line line to analyze
     * @return value that represents how positive this composition is for the opponent
     */
    private int analyzeLine(int[] line)
    {
        // line empty
        if (line[0] == 0 && line[1] == 0 && line[2] == 0 && line[3] == 0)
        {
            return 0;
        }
        
        // Four of my own in one row
        if (line[0] == super.getValue() && line[1] == super.getValue() && line[2] == super.getValue() && line[3] == super.getValue())
        {
            return 50000;
        }
        
        // Four of my opponent's in one row
        if (line[0] == line[1] && line[0] == line[2] && line[0] == line[3])
        {
            return -1000000;
        }
        
        // Three of my own in a line with one open gap
        if ((line[0] == super.getValue() && line[2] == super.getValue() && line[3] == super.getValue() && line[1] == 0) ||
            (line[0] == super.getValue() && line[1] == super.getValue() && line[3] == super.getValue() && line[2] == 0))
        {
            return 10000;
        }
        
        // Three of my opponent's in a line with one open gap
        if ((line[0] == line[2] && line[0] == line[3] && line[1] == 0) ||
            (line[0] == line[1] && line[0] == line[3] && line[2] == 0))
        {
            return -10000;
        }
        
        // Three of my own in a row, open end
        if ((line[0] == super.getValue() && line[1] == super.getValue() && line[2] == super.getValue() && line[3] == 0) ||
            (line[0] == 0 && line[1] == super.getValue() && line[2] == super.getValue() && line[3] == super.getValue()))
        {
            return 5000;
        }
        
        // Three of my opponent's in a row, open end
        if ((line[0] == line[1] && line[0] == line[2] && line[3] == 0) || 
            (line[0] == 0 && line[1] == line[2] && line[1] == line[3]))
        {
            return -15000;
        }
        
        // Three of my own in a row, terminated
        if ((line[0] == super.getValue() && line[1] == super.getValue() && line[2] == super.getValue() && line[3] != super.getValue()) ||
            (line[0] != super.getValue() && line[1] == super.getValue() && line[2] == super.getValue() && line[3] == super.getValue()))
        {
            return -1000;
        }
        
        // Three of my opponent's in a row, terminated
        if ((line[0] == line[1] && line[0] == line[2] && line[3] != line[0]) || 
            (line[0] != line[1] && line[1] == line[2] && line[1] == line[3]))
        {
            return 3000;
        }
        
        // two of my own in a row, surrounded by open fields
        if ((line[0] == 0 && line[1] == super.getValue() && line[2] == super.getValue() && line[3] == 0))
        {
            return 4000;
        }
        
        // two of my opponent's in a row, surrounded by open fields
        if ((line[1] == line[2] && line[0] != 0 && line[1] != 0))
        {
            return -7000;
        }
        
        return 0;
    }
}
