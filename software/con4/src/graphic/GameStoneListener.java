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
import logic.Game;

/**
 * Is used to handle the events that occour in the GameStone.java class
 *
 * @author Marc
 */
public class GameStoneListener implements MouseListener
{

    Controller gamecontrol;

    public void setGamecontrol(Controller gamecontrol)
    {
        this.gamecontrol = gamecontrol;
    }

    public void setGraphicField(GameStone[][] graphicField)
    {
        this.graphicField = graphicField;
    }
    GameStone[][] graphicField;
    PlayfieldGraphics graphics;

    public GameStoneListener(Controller gamecontrol, GameStone[][] graphicField, PlayfieldGraphics graphics)
    {
        this.gamecontrol = gamecontrol;
        this.graphicField = graphicField;
        this.graphics = graphics;
    }

    public void paintPlayfield(Game gameField)
    {
        int[][] k = gameField.getField().getField();

        for (int i = 0; i < k.length; i++)
        {
            for (int j = 0; j < k[0].length; j++)
            {
                if (k[i][j] == 1)
                {
                    graphicField[i][j].setColor(Color.yellow);
                } else if (k[i][j] == 2)
                {
                    graphicField[i][j].setColor(Color.red);

                } else if (k[i][j] == -1)
                {
                    graphicField[i][j].setColor(Color.green);
                }
                else if (k[i][j] == 0)
                {
                    graphicField[i][j].setColor(Color.white);
                }

            }
        }

        graphics.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        GameStone a = (GameStone) e.getComponent();
        Game gameField = gamecontrol.setStone(a.getRow());
        paintPlayfield(gameField);

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

}
