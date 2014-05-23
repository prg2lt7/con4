/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

/**
 * Represents one playstone on the connect four playfield.
 * @author Marc
 */
public class GameStone extends JComponent
{
   
    private final int row;
    private Color stoneColor;
    
    public GameStone(int row, MouseListener mouse){
        this.row = row;
        this.addMouseListener(mouse);
        this.stoneColor = Color.WHITE;
    }
    
    public int getRow(){
        return row;
    }
    
    public void setColor(Color color){
        this.stoneColor = color;
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(stoneColor);       
        g.fillOval(5, 5, 25, 25);
        
        // sets the border of the playstones
        g.setColor(Color.BLACK);
        g.drawOval(5,5 , 25, 25);
       
    }
    

   
    
}
