package sophistry.weoweogame;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import sophistry.weoweogame.data.MarkType;
import sophistry.weoweogame.data.Game;

/**
 * Created by Vincent on 1/22/2018.
 */

public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;
    Game model;
    Pair<Integer,Integer> currentTouch;

    public MainPresenter (@NonNull Game game, @NonNull MainContract.View view) {
        this.model = game;
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void onCellTouched(int row, int col) {
        MarkType state = model.getCellState(row, col);

        switch (state) {
            case EMPTY:
                currentTouch = new Pair<>(row,col);
                view.highlightCell(row, col);
                break;
            default:
                break;
        }
    }

    @Override
    public void onCellChanged(int row, int col) {
        MarkType state = model.getCellState(row, col);

        switch (state) {
            case EMPTY:
                // Check if user has moved to another cell
                if (!(row == currentTouch.first && col == currentTouch.second)) {
                    currentTouch = new Pair<>(row, col);
                    view.highlightCell(row, col);
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onCellFinalized(int row, int col) {

        MarkType movedPlayer = model.processTurn(row, col);

        if (movedPlayer == MarkType.PLAYER_O) {
            view.markCellWithO(row, col);
        }
        else if (movedPlayer == MarkType.PLAYER_X) {
            view.markCellWithX(row, col);
        }

        view.cancelCellHighlight();
        //view.unhighlightCell(currentTouch.first, currentTouch.second);
        // update model with X or O depending on turn
        // update view with X or O depending on turn


    }

    @Override
    public void start() {
        // load model as necessary -- call in view's onResume()
    }
}
