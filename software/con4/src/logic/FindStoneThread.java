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

    public FindStoneThread()
    {
        t = new Thread();
        t.start();
    }

    public int getChoiceValue()
    {
        return choiceValue;
    }

    public Field getField()
    {
        return field;
    }

    public void setChoiceValue(int choiceValue)
    {
        this.choiceValue = choiceValue;
    }

    public void setField(Field field)
    {
        this.field = field;
    }

    @Override
    public void run()
    {
        choiceValue = findBestStone(field.getField(), true, 6, 1);
    }
    
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
