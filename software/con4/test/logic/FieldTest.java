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
        assertArrayEquals(expResult, result);
        
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
        assertArrayEquals(expResult, instance.getField());
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
        
        // Empty Field
        Field instance = new Field();
        assertEquals(0, instance.isWinner());
        
        // Horizontal
        instance = new Field();
        instance.putStone(1, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(1, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(1, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(1, 1);
        assertEquals(1, instance.isWinner());
        
        // Vertical
        instance = new Field();
        instance.putStone(3, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(3, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(3, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(3, 1);
        assertEquals(1, instance.isWinner());
        
        // Diagonal "Slash"
        instance = new Field();
        assertEquals(0, instance.isWinner());
        instance.putStone(2, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(3, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(4, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(4, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(5, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(5, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(5, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(3, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(4, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(5, 1);
        assertEquals(1, instance.isWinner());
        
        // Diagonal "Backslash"
        instance = new Field();
        assertEquals(0, instance.isWinner());
        instance.putStone(3, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(3, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(3, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(4, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(4, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(5, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(3, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(4, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(5, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(6, 2);
        assertEquals(2, instance.isWinner());
        
        // Game
        instance = new Field();
        assertEquals(0, instance.isWinner());
        instance.putStone(3, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(4, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(2, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(1, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(4, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(3, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(5, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(2, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(3, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(1, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(1, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(2, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(4, 1);
        assertEquals(0, instance.isWinner());
        instance.putStone(3, 2);
        assertEquals(0, instance.isWinner());
        instance.putStone(2, 1);
        assertEquals(1, instance.isWinner());
    }

    /**
     * Test of winLine method, of class Field.
     */
    @Test
    public void testwinLine() throws Exception
    {
        System.out.println("winLine");
        Field instance = new Field();
        Method m = Field.class.getDeclaredMethod("winLine", Class.forName("[I"));
        m.setAccessible(true);
        int[] testarray1  = {1, 1, 1};
        assertEquals(false, (boolean) m.invoke(instance, testarray1));
        int[] testarray2  = {1, 1, 1, 1};
        assertEquals(true , (boolean) m.invoke(instance, testarray2));
        int[] testarray3  = {0, 1, 1, 1};
        assertEquals(false, (boolean) m.invoke(instance, testarray3));
        int[] testarray4  = {1, 0, 1, 1};
        assertEquals(false, (boolean) m.invoke(instance, testarray4));
        int[] testarray5  = {1, 1, 0, 1};
        assertEquals(false, (boolean) m.invoke(instance, testarray5));
        int[] testarray6  = {1, 1, 1, 0};
        assertEquals(false, (boolean) m.invoke(instance, testarray6));
        int[] testarray7  = {2, 1, 1, 1};
        assertEquals(false, (boolean) m.invoke(instance, testarray7));
        int[] testarray8  = {1, 2, 1, 1};
        assertEquals(false, (boolean) m.invoke(instance, testarray8));
        int[] testarray9  = {1, 1, 2, 1};
        assertEquals(false, (boolean) m.invoke(instance, testarray9));
        int[] testarray10 = {1, 1, 1, 2};
        assertEquals(false, (boolean) m.invoke(instance, testarray10));
        int[] testarray11 = {2, 2, 2, 2};
        assertEquals(true , (boolean) m.invoke(instance, testarray11));
        int[] testarray12 = {1, 1, 1, 1, 0};
        assertEquals(true , (boolean) m.invoke(instance, testarray12));
        int[] testarray13 = {1, 1, 1, 1, 1};
        assertEquals(true , (boolean) m.invoke(instance, testarray13));
        int[] testarray14 = {1, 1, 1, 1, 2};
        assertEquals(true , (boolean) m.invoke(instance, testarray14));
        int[] testarray15 = {0, 1, 1, 1, 1};
        assertEquals(false, (boolean) m.invoke(instance, testarray15));
        int[] testarray16 = {1, 1, 1, 1, 1};
        assertEquals(true , (boolean) m.invoke(instance, testarray16));
        int[] testarray17 = {2, 1, 1, 1, 1};
        assertEquals(false, (boolean) m.invoke(instance, testarray17));
        int[] testarray18 = {1, 2, 1, 1, 1};
        assertEquals(false, (boolean) m.invoke(instance, testarray18));
        int[] testarray19 = {0, 0, 0, 0};
        assertEquals(false, (boolean) m.invoke(instance, testarray19));
        int[] testarray20 = {0, 0, 0, 0, 1, 1, 1, 1};
        assertEquals(false, (boolean) m.invoke(instance, testarray20));
        int[] testarray21 = {1, 0, 0, 0};
        assertEquals(false, (boolean) m.invoke(instance, testarray21));
        int[] testarray22 = {0, 1, 0, 0};
        assertEquals(false, (boolean) m.invoke(instance, testarray22));
        int[] testarray23 = {0, 0, 1, 0};
        assertEquals(false, (boolean) m.invoke(instance, testarray23));
        int[] testarray24 = {0, 0, 0, 1};
        assertEquals(false, (boolean) m.invoke(instance, testarray24));
    }
}
