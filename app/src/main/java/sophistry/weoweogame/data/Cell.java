package sophistry.weoweogame.data;

/**
 * Created by Vincent on 1/22/2018.
 */

public class Cell {
    private MarkType state;
    private int row;
    private int col;

    public Cell (MarkType state, int row, int col) {
        this.state = state;
        this.row = row;
        this.col = col;
    }

    public void setState(MarkType state) {
        this.state = state;
    }

    public MarkType getState() {
        return this.state;
    }
}
