/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.awt.Color;
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

    Controller gamecontrol;
    GameStone[][] graphicField;
    PlayfieldGraphics graphics;
    
    // <editor-fold defaultstate="collapsed" desc="Set-Methods">

    public void setGamecontrol(Controller gamecontrol)
    {
        this.gamecontrol = gamecontrol;
    }

    public void setGraphicField(GameStone[][] graphicField)
    {
        this.graphicField = graphicField;
    }
    
    // </editor-fold>

    /**
     * 
     * @param gamecontrol
     * @param graphicField
     * @param graphics 
     */
    public GameStoneListener(Controller gamecontrol, GameStone[][] graphicField, PlayfieldGraphics graphics)
    {
        this.gamecontrol = gamecontrol;
        this.graphicField = graphicField;
        this.graphics = graphics;
    }

    /**
     * Paints the playfield accordingly to the gamefield which 
     * is stored in the game controller
     * 
     */
    public void paintPlayfield()
    {
        int[][] k = gamecontrol.getGame().getField().getField();

        //iterates through all fields and sets the color
        for (int i = 0; i < k.length; i++)
        {
            for (int j = 0; j < k[0].length; j++)
            {
                //used to story which color the element should have
                Color tempColor;

                //New Color-Definitions have to be added here to have any effect
                switch (k[i][j])
                {
                    case -1:
                        tempColor = Color.GREEN;
                        break;

                    case 1:
                        tempColor = Color.YELLOW;
                        break;
                    case 2:
                        tempColor = Color.RED;
                        break;

                    default:
                        tempColor = Color.WHITE;
                }
                
                //The component gets the color from the logical playfield
                graphicField[i][j].setColor(tempColor);

            }
        }

        graphics.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        //Each playstone is a component with a MouseListener added to it,
        //through that we are able to get which stone was clicked
        GameStone a = (GameStone) e.getComponent();
        gamecontrol.setStone(a.getRow());
        paintPlayfield();

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
