package sophistry.weoweogame.data;

/**
 * Created by Vincent on 1/22/2018.
 */

public class Cell {
    private CellType state;

    public Cell (CellType state) {
        this.state = state;
    }

    public void setState(CellType state) {
        this.state = state;
    }

    public CellType getState() {
        return this.state;
    }
}
