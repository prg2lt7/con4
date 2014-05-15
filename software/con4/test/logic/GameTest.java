package logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *Test class for Game.class
 * @author Fabian
 */
public class GameTest
{
    
    public GameTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
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
     * Test of getField method, of class Game.
     */
    @Test
    public void testGetField()
    {
        System.out.println("getField");
        Game instance = new Game();
        int[][] expResult = new int[7][6];
        int[][] result = instance.getField().getField();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Game.
     */
    @Test
    public void testGetState()
    {
        System.out.println("getState");
        Game instance = new Game();
        State expResult = new State();
        State result = instance.getState();
        assertEquals(expResult, result);
    }
    
}
