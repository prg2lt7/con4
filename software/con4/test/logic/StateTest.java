/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author Fabian
 */
public class StateTest
{
    
    public StateTest()
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
     * Test of toggleState method, of class State.
     * default state is true, toggleState() toggles to false.
     */
    @Test
    public void testToggleState()
    {
        System.out.println("toggleState");
        State instance = new State();
        boolean expResult = false;
        boolean result = instance.toggleState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserMove method, of class State.
     * contructor creates usermove = true -> 1
     */
    @Test
    public void testGetUserMove()
    {
        System.out.println("getUserMove");
        State instance = new State();
        int expResult = 1;
        int result = instance.getUserMove();
        assertEquals(expResult, result);
    }

    /**
     * Test of isGameOver method, of class State.
     */
    @Test
    public void testIsGameOver()
    {
        System.out.println("isGameOver");
        State instance = new State();
        boolean expResult = false;
        boolean result = instance.isGameOver();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGameOver method, of class State.
     */
    @Test
    public void testSetGameOver()
    {
        System.out.println("setGameOver");
        boolean gO = true;
        boolean expResult = true;
        State instance = new State();
        instance.setGameOver(gO);
        boolean result = instance.isGameOver();
        assertEquals(expResult, result);
        
    }
    
}
