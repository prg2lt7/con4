package logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import network.Network;


/**
 *class Controller controls the game play.
 *checks if move is valid or invalid.
 * @author Fabian
 */
public class Controller
{

    private static Game game;
    private static Opponent opponent;

    /**
     * default constructor creates new Game
     */
    public Controller()
    {
        game = new Game();
        opponent = new LocalOpponent(2);
        
    }
    
    /**
     * Player or opponent intends to set a stone at position x,y
     * Method checks for possibilty to set stone at requested position
     * returns if stone could be set to position (true) or not (false)
     * @param xPosition
     * @return 
     */
    public Game setStone(int xPosition)
    {
        //is field full?
        //checks if there is any free position (0) in a column
        int x = 0;
        while ((game.getField().getField()[x][game.getField().getField()[x].length - 1] != 0) &&
                (x < game.getField().getField().length))
        {
            x++;
        }
        game.getField().putStone(xPosition, game.getState().getUserMove());
        
        //if field not full (at least one free position (0) - continue gameplay!
        if (game.getField().getField()[x][game.getField().getField()[x].length - 1] == 0)
        {
            game.getField().putStone(opponent.move(game.getField()), opponent.getValue());
        }  
        else //game over (draw)
        {
            game.getState().setGameOver(true);
        }
        return game; 
        
    }
    
    public void networkGame(Network net, boolean server)
    {
        
    }
    
    public boolean saveGame()
    {
        String fileName = "saveGame.dat";
        File gameFile = new File(fileName);
        if (!gameFile.exists())
        {
            try
            {
                // Open a file to write game into
                FileOutputStream saveFile = new FileOutputStream(fileName);

                // Create an ObjectOutputStream to put game-object into file.
                ObjectOutputStream save = new ObjectOutputStream(saveFile);

                // Save current game to file via FileOutputStream
                save.writeObject(game);
                
                // Close the file.
                save.close();
                return true;
            }
            catch (IOException ex)
            {
                System.out.println("File already exists. Creating file failed");
            }
        }
        return false;
    }
    
    public Game loadGame()
    {
        return game;
    }
    
    public static void main (String[] args){
        Controller c = new Controller();
        Game g;
        g = c.setStone(3);
        System.out.println(g.getField());
        g = c.setStone(5);
        System.out.println(g.getField());
        g = c.setStone(4);
        System.out.println(g.getField());
        g = c.setStone(2);
        System.out.println(g.getField());
        g = c.setStone(1);
        System.out.println(g.getField());
        g = c.setStone(0);
        System.out.println(g.getField());
        g = c.setStone(6);
        System.out.println(g.getField());
    }
}
