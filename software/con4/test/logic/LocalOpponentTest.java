/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

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
public class LocalOpponentTest
{
    
    public LocalOpponentTest()
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
     * Test of getDifficulty method, of class LocalOpponent.
     */
    @Test
    public void testGetDifficulty()
    {
        System.out.println("getDifficulty");
        LocalOpponent op = new LocalOpponent(1);
        int expResult = 3;
        int result = op.getDifficulty();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDifficulty method, of class LocalOpponent.
     */
    @Test
    public void testSetDifficulty()
    {
        System.out.println("setDifficulty");
        LocalOpponent op = new LocalOpponent(1);
        op.setDifficulty(2);
        int expResult = 2;
        int result = op.getDifficulty();
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class LocalOpponent.
     */
    @Test
    public void testMove()
    {
        System.out.println("move");
        Field field = new Field();
        LocalOpponent op = new LocalOpponent(1);
        boolean expResult = true;
        int returnvalue = op.move(field);
        boolean result = (returnvalue > 0 && returnvalue < 7);
        assertEquals(expResult, result);
    }
}
