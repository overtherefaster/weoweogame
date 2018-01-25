package sophistry.weoweogame.data;

/**
 * Created by Vincent on 1/22/2018.
 */

public class Game {

    Cell[][] board = new Cell[3][3];
    Player playerOne;
    Player playerTwo;
    Player currentPlayer;
    GameState currentState;

    public Game () {
        initGameState();
        initPlayers();
        restartGame();
    }

    private void initGameState() {
        currentState = GameState.IN_PROGRESS;
    }
    private void initPlayers() {
        playerOne = new Player("O-Player", "O", MarkType.PLAYER_O);
        playerTwo = new Player("X-Player", "X", MarkType.PLAYER_X);
        currentPlayer = playerOne;
    }

    private void restartGame() {
        clearBoard();
    }

    private void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Cell(MarkType.EMPTY, i, j);
            }
        }
    }

    private boolean gameIsOver() {
        return false;
    }

    private void handleGameOver() {
        currentState = GameState.FINISHED;
    }

    public MarkType processTurn(int row, int col) {
        MarkType movingPlayer = null;

        if (isValid(row, col)) {
            movingPlayer = currentPlayer.type;
            markCell(row, col);
            flipCurrentPlayerTurn();

            if (gameIsOver()) {
                handleGameOver();
            }
        }

        // Return the player type that just moved
        return movingPlayer;
    }

    private void markCell(int row, int col) {
        board[row][col].setState(currentPlayer.type);
    }

    private void flipCurrentPlayerTurn () {
        if (currentPlayer.type == MarkType.PLAYER_O) {
            currentPlayer = playerTwo;
        }
        else {
            currentPlayer = playerOne;
        }
    }

    private boolean isValid(int row, int col) {
        return board[row][col].getState() == MarkType.EMPTY && row < board.length && col < board[0].length;
    }

    public Cell getCell (int row, int col) {
        return board[row][col];
    }
    public MarkType getCellState (int row, int col) {
        return board[row][col].getState();
    }



}
