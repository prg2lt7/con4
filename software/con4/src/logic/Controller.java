package logic;

import java.io.*;
import network.Network;

/**
 * class Controller controls the game play
 * @author Fabian
 */
public class Controller
{

    private static Game game;
    private static Opponent opponent;
    private boolean flag;

    /**
     * default constructor creates new Game
     */
    public Controller()
    {
        game = new Game();
        opponent = new LocalOpponent(2);

    }

    /**
     * returns current game
     * @return 
     */
    public Game getGame()
    {
        return game;
    }

    public static void setOpponentDifficulty(int difficulty)
    {
        if (opponent.getClass() == LocalOpponent.class)
        {
            LocalOpponent op = (LocalOpponent) opponent;
            op.setDifficulty(difficulty);
        }
    }

    /**
     * Player or opponent intends to set a stone at position x,y Method checks
     * for possibilty to set stone at requested position
     * @param xPosition
     */
    public void setStone(int xPosition)
    {
        if (game.getField().isWinner() == 0)
        {
            //Is game already over (opponent won)
            if (game.getState().isGameOver() == true)
            {

            }
        //is field full?
            //checks if there is any free position (0) in a column
            int x = 0;
            while ((game.getField().getField()[x][game.getField().getField()[x].length - 1] != 0)
                    && (x < game.getField().getField().length))
            {
                x++;
            }

            game.getField().putStone(xPosition, game.getState().getUserMove());
            //checks if user wins game with this move
            if (game.getField().isWinner() != 0)
            {
                game.getState().setGameOver(true);
            }
            //if field not full (at least one free position (0) - continue gameplay!
            if (game.getField().getField()[x][game.getField().getField()[x].length - 1] == 0)
            {
                game.getField().putStone(opponent.move(game.getField()), opponent.getValue());
                //checks if opponent won game with this move
                if (game.getField().isWinner() != 0)
                {
                    game.getState().setGameOver(true);
                }
            } else //game over (draw)
            {
                game.getState().setGameOver(true);
            }
        }
    }

    public void networkGame(Network net, boolean server)
    {

    }

    /**
     * Resets the playfield
     */
    public void resetPlayfield()
    {
        game.getField().resetField();
    }

    /**
     * network opponent sets flag if opponent made his move
     * @param flag
     */
    public void setReceivedFlag(boolean flag)
    {
        this.flag = flag;
    }

    /**
     * saves current game to file
     * @return
     */
    public boolean saveGame()
    {
        String fileName = "saveGame.dat";
        File gameFile = new File(fileName);
        gameFile.delete();
        if (!gameFile.exists())
        {
            try
            {
                //Open a file to write game into
                FileOutputStream saveFile = new FileOutputStream(fileName);

                //Create an ObjectOutputStream to put game-object into file.
                ObjectOutputStream save = new ObjectOutputStream(saveFile);

                //Save current game to file via FileOutputStream
                save.writeObject(game);

                //Close the file.
                save.close();
                return true;
            } catch (IOException ex)
            {
                System.out.println("File already exists. Creating file failed");
            }
        }
        return false;
    }

    /**
     * loads last saved game
     * @return
     */
    public Game loadGame()
    {
        String fileName = "saveGame.dat";
        try
        {
            //Open file to read from
            FileInputStream saveFile = new FileInputStream(fileName);

            //Create an ObjectInputStream to get object from saved file.
            ObjectInputStream save = new ObjectInputStream(saveFile);

            //Restoring game from file. Cast object to Game.
            game = (Game) save.readObject();

            //Close the file.
            save.close();
        } catch (Exception ex)
        {
            ex.printStackTrace(); // If there was an error, print the info.
        }
        return game;
    }
}
