package sophistry.weoweogame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sophistry.weoweogame.data.Game;
import sophistry.weoweogame.data.MarkType;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Vincent on 1/28/2018.
 */

public class GameTest {

    Game game;

    @Before
    public void setup() {
         game = new Game();
    }

    @Test
    public void testPlayerSwitchOnNextTurn() {
        MarkType currentPlayer = game.getCurrentPlayerType();
        assertEquals("Player not initialized as O!", currentPlayer, MarkType.PLAYER_O);
        game.processTurn(0,0);
        currentPlayer = game.getCurrentPlayerType();
        assertEquals("No transition to X after O's turn!", currentPlayer, MarkType.PLAYER_X);
    }


    /*  Final board state:
        O O O
        X * *
        * X *
    */
    @Test
    public void testVictory3AcrossTopRow() {

        game.processTurn(0,0); // o
        assertFalse(game.winnerExists());

        game.processTurn(1,0); // x
        assertFalse(game.winnerExists());

        game.processTurn(0,1); // o
        assertFalse(game.winnerExists());

        game.processTurn(2,1); // x
        assertFalse(game.winnerExists());

        game.processTurn(0,2); // o win
        assertTrue(game.winnerExists());

    }


}
