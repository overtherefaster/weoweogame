package sophistry.weoweogame.data;

/**
 * Created by Vincent on 1/22/2018.
 */

public class Game {

    Cell[][] board = new Cell[3][3];
    Player playerOne;
    Player playerTwo;

    GameState currentState;


    private enum GameState { IN_PROGRESS, FINISHED };

    private void restartGame() {

    }

    private void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j].setState(CellType.EMPTY);
            }
        }
    }



}
