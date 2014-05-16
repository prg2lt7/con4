/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphic;

import java.awt.Color;
import logic.Field;
import logic.ModelListener;

/**
 *
 * @author Marc
 */
public class View implements ModelListener
{
    private Field field;
    private GameStone[][] graphicalField;

    

    public void setGraphicalField(GameStone[][] graphicalField)
    {
        this.graphicalField = graphicalField;
    }
    private final PlayfieldGraphics graphics;
    
    public View(Field field,  PlayfieldGraphics graphics){
        this.field= field;
        this.graphicalField = graphics.getGraphicField();
        this.graphics = graphics;
        field.addListener(this);
    }
    /**
     * Paints the playfield accordingly to the gamefield which 
     * is stored in the game controller
     * 
     */
    public void repaintPlayfield()
    {
        int[][] k = field.getField();

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
                graphicalField[i][j].setColor(tempColor);

            }
        }

        graphics.repaint();
    }

    @Override
    public void modelChanged()
    {
        repaintPlayfield();
    }
}
