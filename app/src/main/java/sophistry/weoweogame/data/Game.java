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

    int moveCnt = 0;

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

    private boolean winnerExists() {
        boolean row1 = board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2]) && board[0][0].getState() != MarkType.EMPTY;
        boolean row2 = board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2]) && board[1][0].getState() != MarkType.EMPTY;
        boolean row3 = board[2][0].equals(board[2][1]) && board[2][1].equals(board[2][2]) && board[2][0].getState() != MarkType.EMPTY;

        boolean col1 = board[0][0].equals(board[1][0]) && board[1][0].equals(board[2][0]) && board[0][0].getState() != MarkType.EMPTY;
        boolean col2 = board[0][1].equals(board[1][1]) && board[1][1].equals(board[2][1]) && board[0][1].getState() != MarkType.EMPTY;
        boolean col3 = board[0][2].equals(board[1][2]) && board[1][2].equals(board[2][2]) && board[0][2].getState() != MarkType.EMPTY;

        boolean diag1 = board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && board[0][0].getState() != MarkType.EMPTY;
        boolean diag2 = board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2]) && board[2][0].getState() != MarkType.EMPTY;

        return row1 || row2 || row3 || col1 || col2 || col3 || diag1 || diag2;
    }

    private void handleGameOver(boolean winnerExists) {
        if (winnerExists) {
            // currentPlayer is the winner
        }
        else {
            // draw
        }
        currentState = GameState.FINISHED;
    }

    public MarkType processTurn(int row, int col) {
        MarkType movingPlayer = null;

        if (isValid(row, col)) {
            movingPlayer = currentPlayer.type;
            markCell(row, col);
            moveCnt++;

            boolean winnerExists = winnerExists();

            if (winnerExists || moveCnt == 9) {
                handleGameOver(winnerExists);
            }
            else {
                flipCurrentPlayerTurn();
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
    
    public MarkType getCellState (int row, int col) {
        return board[row][col].getState();
    }

    public String[][] boardToString() {
        String[][] strBoard = new String[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getState() == MarkType.PLAYER_O) {
                    strBoard[i][j] = "O";
                }
                else if (board[i][j].getState() == MarkType.PLAYER_X) {
                    strBoard[i][j] = "X";
                }
            }
        }

        return strBoard;
    }


}
