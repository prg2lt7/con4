/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import logic.Controller;

/**
 * Is used to handle the events that occour in the GameStone.java class
 *
 * @author Marc
 */
public class GameStoneListener implements MouseListener
{

    private Controller gamecontrol;
    
    // <editor-fold defaultstate="collapsed" desc="Set-Methods">

    public void setGamecontrol(Controller gamecontrol)
    {
        this.gamecontrol = gamecontrol;
    }

  
    
    // </editor-fold>

    /**
     * Creates an Element that listens to events in the MouseComponent
     * @param gamecontrol Controller for logic
     */
    public GameStoneListener(Controller gamecontrol)
    {
        this.gamecontrol = gamecontrol;
    }

    

    @Override
    public void mousePressed(MouseEvent e)
    {
        //Each playstone is a component with a MouseListener added to it,
        //through that we are able to get which stone was clicked
        GameStone a = (GameStone) e.getComponent();
        gamecontrol.setStone(a.getRow());
       

    }

    // <editor-fold defaultstate="collapsed" desc="Not implemented MouseEvents">
    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
// </editor-fold>

}
