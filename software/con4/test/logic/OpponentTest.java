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
public class OpponentTest
{
    
    public OpponentTest()
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
     * Test of getValue method, of class Opponent.
     */
    @Test
    public void testGetValue()
    {
        System.out.println("getValue");
        Opponent op = new LocalOpponent(1);
        int expResult = 1;
        int result = op.getValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of setValue method, of class Opponent.
     */
    @Test
    public void testSetValue()
    {
        System.out.println("setValue");
        Opponent op = new LocalOpponent(1);
        op.setValue(2);
        int expResult = 2;
        int result = op.getValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Opponent.
     */
    @Test
    public void testMove()
    {
        System.out.println("move");
        Field field = new Field();
        Opponent op = new OpponentImpl();
        int expResult = 0;
        int result = op.move(field);
        assertEquals(expResult, result);
    }

    public class OpponentImpl extends Opponent
    {

        public OpponentImpl()
        {
            super(0);
        }

        public int move(Field field)
        {
            return 0;
        }
    }
}
