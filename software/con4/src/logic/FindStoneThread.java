/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author daniw
 */
public class FindStoneThread implements Runnable
{
    Thread t;
    private int choiceValue;
    private static final boolean DEBUG = true;
    private Field field;

    /**
     * Constructor to create a thread that calculates some moves forward
     */
    public FindStoneThread(String name)
    {
        t = new Thread(this, name);
    }

    /**
     * Return the value for the given field
     * @return Value for given field
     */
    public int getChoiceValue()
    {
        return choiceValue;
    }

    /**
     * Returns the field
     * @return field
     */
    public Field getField()
    {
        return field;
    }

    /**
     * Set the field
     * @param field 
     */
    public void setField(Field field)
    {
        this.field = field;
    }

    /**
     * Calculate the value for the given value
     */
    @Override
    public void run()
    {
        choiceValue = findBestStone(field.getField(), true, 6, 1);
    }

    /**
     * Recursive Function to iterate recursively through all possible moves.
     * @param currentfield field before setting the next stone
     * @param myStone value of the opponent's stones
     * @param iteration recursion depth
     * @param playervalue value of the player's stones
     * @return value representing win / lose rate for this move
     */
    private int findBestStone(int[][] currentfield, boolean myStone, int iteration, int playervalue)
    {
        //System.out.println("Finding best stone");
        int count = 0;
        Field tempfield = new Field(currentfield.length, currentfield[0].length);
        tempfield.setField(currentfield);
        if (tempfield.isWinner() == 0)
        {
            count = 0;
        }
        if (tempfield.isWinner() == 2)
        {
            count = 16;
        }
        if (tempfield.isWinner() != 0 && tempfield.isWinner() != 2)
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
                    tempfield.putStone(x, 2);
                }
                else
                {
                    tempfield.putStone(x, playervalue);
                }
                count += findBestStone(tempfield.getField(), !myStone, iteration - 1, playervalue) / 2;
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
}
