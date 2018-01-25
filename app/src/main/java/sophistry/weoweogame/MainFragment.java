package sophistry.weoweogame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import sophistry.weoweogame.customviews.TTTBoardView;

/**
 * Created by Vincent on 1/22/2018.
 */

public class MainFragment extends Fragment implements MainContract.View, View.OnTouchListener {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private MainContract.Presenter presenter;
    private TTTBoardView tttBoardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        tttBoardView = rootView.findViewById(R.id.main_board);
        tttBoardView.setOnTouchListener(this);

        return rootView;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int touchX = (int)motionEvent.getX();
        int touchY = (int)motionEvent.getY();

        if (view.getId() == R.id.main_board && tttBoardView.withinBoardBounds(touchX, touchY)) {

            Pair<Integer, Integer> coords =
                    tttBoardView.getCellRowCol(touchX, touchY);

            switch (motionEvent.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    //Log.d ("", "[main_board] ACTION_DOWN - row="+coords.first+"/col="+coords.second);
                    presenter.onCellTouched(coords.first, coords.second);
                    break;

                case MotionEvent.ACTION_MOVE:
                    presenter.onCellChanged(coords.first, coords.second);
                    break;

                case MotionEvent.ACTION_UP:
                    presenter.onCellFinalized(coords.first, coords.second);
                    view.performClick();
                    break;

                default:
                    break;
            }
            return true;
        }
        else {
            cancelCellHighlight();
            return false;
        }

    }



    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void highlightCell(int row, int col) {
        tttBoardView.highlightCell(row, col);
    }

    @Override
    public void cancelCellHighlight() {
        tttBoardView.cancelHighlight();
    }

    @Override
    public void unhighlightCell(int row, int col) {
        tttBoardView.unhighlightCell(row, col);
    }

    @Override
    public void markCellWithO(int row, int col) {
        tttBoardView.markXO(true, row, col);
    }

    @Override
    public void markCellWithX(int row, int col) {
        tttBoardView.markXO(false, row, col);
    }
}
