/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.lang.reflect.Method;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daniw
 */
public class FieldTest extends Exception
{
    
    public FieldTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getField method, of class Field.
     */
    @Test
    public void testGetField()
    {
        System.out.println("getField");
        Field instance = new Field();
        int[][] expResult = new int[7][6];
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                expResult[i][j] = 0;
            }
        }
        int[][] result = instance.getField();
        assertEquals(expResult, result);
        
        expResult[3][0] = 1;
        assertEquals(true, instance.putStone(3, 1));
        result = instance.getField();
        //assertEquals(expResult, null);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of putStone method, of class Field.
     */
    @Test
    public void testPutStone()
    {
        System.out.println("putStone");
        Field instance = new Field();
        int[][] expResult = new int[7][6];
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                expResult[i][j] = 0;
            }
        }
        expResult[3][0] = 1;
        assertEquals(true, instance.putStone(3, 1));
        assertEquals(expResult, instance.getField());
    }

    /**
     * Test of toString method, of class Field.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        Field instance = new Field();
        instance.putStone(3, 1);
        instance.putStone(4, 2);
        instance.putStone(2, 1);
        instance.putStone(1, 2);
        instance.putStone(4, 1);
        instance.putStone(3, 2);
        instance.putStone(5, 1);
        instance.putStone(2, 2);
        instance.putStone(3, 1);
        instance.putStone(1, 2);
        instance.putStone(1, 1);
        instance.putStone(2, 2);
        instance.putStone(4, 1);
        instance.putStone(3, 2);
        instance.putStone(2, 1);
        String expResult = "+-+-+-+-+-+-+-+\n| | | | | | | |\n+-+-+-+-+-+-+-+\n| | | | | | | |\n+-+-+-+-+-+-+-+\n| | |X|O| | | |\n+-+-+-+-+-+-+-+\n| |X|O|X|X| | |\n+-+-+-+-+-+-+-+\n| |O|O|O|X| | |\n+-+-+-+-+-+-+-+\n| |O|X|X|O|X| |\n+-+-+-+-+-+-+-+\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of isWinner method, of class Field.
     */
    @Test
    public void testIsWinner()
    {
        System.out.println("isWinner");
        Field instance = new Field();
        
        // Horizontal
        instance.putStone(0, 1);
        instance.putStone(1, 1);
        instance.putStone(2, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(3, 1);
        assertEquals(1, instance.isWinner());
        
        // Vertical
        instance.putStone(3, 1);
        instance.putStone(3, 1);
        instance.putStone(3, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(3, 1);
        assertEquals(1, instance.isWinner());
        
        // Game
        instance.putStone(3, 1);
        instance.putStone(4, 2);
        instance.putStone(2, 1);
        instance.putStone(1, 2);
        instance.putStone(4, 1);
        instance.putStone(3, 2);
        instance.putStone(5, 1);
        instance.putStone(2, 2);
        instance.putStone(3, 1);
        instance.putStone(1, 2);
        instance.putStone(1, 1);
        instance.putStone(2, 2);
        instance.putStone(4, 1);
        instance.putStone(3, 2);
        //assertEquals(0, instance.isWinner());
        instance.putStone(2, 1);
        //assertEquals(1, instance.isWinner());
    }

    /**
     * Test of winLine method, of class Field.
     */
    @Test
    public void testwinLine() throws Exception
    {
        System.out.println("winLine");
        Field instance = new Field();
        Method m = Field.class.getDeclaredMethod("winLine");
        m.setAccessible(true);
        int[] testarray = new int[3];
        m.invoke(instance, testarray);
        
    }
}
