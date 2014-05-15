/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import graphic.GUI;
import graphic.PlayfieldGraphics;
import network.Network;

/**
 *
 * @author Marc
 */
public class main
{

    public static void main(String args[])
    {
        Controller gameControl = new Controller();
        PlayfieldGraphics game = new PlayfieldGraphics(gameControl);
        game.setVisible(true);

    }
}
