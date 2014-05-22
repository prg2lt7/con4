/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import network.Network;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for all controller functions
 * @author Fabian
 */
public class ControllerTest
{
    
    public ControllerTest()
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
     * Test of setStone method, of class Controller.
     */
    @Test
    public void testSetStone()
    {
        System.out.println("setStone");
        int xPosition = 4;
        Controller instance = new Controller();
        instance.setStone(xPosition);
        int expResult = 1;  //User set stone
        int result = instance.getGame().getField().getField()[4][0];
        assertEquals(expResult, result);
    }

    /**
     * Test of getGame method, of class Controller.
     * becuase of indentity difficulties tests only tests for game parameters
     */
    @Test
    public void testGetGame()
    {
        System.out.println("getGame - game over");
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.getGame().getState().isGameOver();
        assertEquals(expResult, result);
        System.out.println("getGame - move");
        int expResult1 = 1;
        int result1 = instance.getGame().getState().getUserMove();
        assertEquals(expResult1, result1);
    }

    /**
     * Test of networkGame method, of class Controller.
     */
    @Test
    public void testNetworkGame()
    {
        System.out.println("networkGame");
        Network net = null;
        boolean server = false;
        Controller instance = new Controller();
        instance.networkGame(net, server);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetPlayfield method, of class Controller.
     * sets 2 stone from player side, local opponent sets 2 stones
     * 4 stones are set on the field
     * resets field and checks for clean field
     */
    @Test
    public void testResetPlayfield()
    {
        System.out.println("resetPlayfield");
        Controller instance = new Controller();
        instance.setStone(5);
        instance.setStone(2);
        
        instance.resetPlayfield();
        for (int x = 0; x < instance.getGame().getField().getField().length; x++)
        {
            for (int y = 0; y < instance.getGame().getField().getField()[x].length; y++)
            {
                assertEquals(0, instance.getGame().getField().getField()[x][y]);
            }
        }
    }

    /**
     * Test of setReceivedFlag method, of class Controller.
     */
    @Test
    public void testSetReceivedFlag()
    {
        System.out.println("setReceivedFlag");
        boolean flag = false;
        Controller instance = new Controller();
        instance.setReceivedFlag(flag);
    }

    /**
     * Test of saveGame method, of class Controller.
     * saves game to file.
     */
    @Test
    public void testSaveGame()
    {
        System.out.println("saveGame");
        Controller instance = new Controller();
        boolean expResult = true;
        boolean result = instance.saveGame();
        assertEquals(expResult, result);
    }

    /**
     * Test of loadGame method, of class Controller.
     */
    @Test
    public void testLoadGame()
    {
        System.out.println("loadGame");
        Controller instance = new Controller();
        Game expResult = null;
        Game result = instance.loadGame();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
