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
    Pair<Integer,Integer> initialTouch;

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
                view.highlightCell(row, col);
                initialTouch = new Pair<>(row,col);
                break;
            case PLAYER_O:
                break;
            case PLAYER_X:
                break;
            default:
                break;
        }

    }

    @Override
    public void onCellFinalized(int row, int col) {
        MarkType state = model.getCellState(row, col);

        switch (state) {
            case EMPTY:
                if (row == initialTouch.first && col == initialTouch.second) {
                    MarkType movedPlayer = model.processTurn(row, col);
                    if (movedPlayer == MarkType.PLAYER_O) {
                        view.markCellWithO(row, col);
                    }
                    else {
                        view.markCellWithX(row, col);
                    }
                }
                else {
                    view.unhighlightCell(initialTouch.first, initialTouch.second);
                }

                // update model with X or O depending on turn
                // update view with X or O depending on turn
                break;
            case PLAYER_O:
                break;
            case PLAYER_X:
                break;
            default:
                break;
        }

    }

    @Override
    public void start() {
        // load model as necessary -- call in view's onResume()
    }
}
