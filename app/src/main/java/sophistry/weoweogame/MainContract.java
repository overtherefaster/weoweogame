package sophistry.weoweogame;

import sophistry.weoweogame.base.BasePresenter;
import sophistry.weoweogame.base.BaseView;

/**
 * Created by Vincent on 1/22/2018.
 */

public interface MainContract {

    interface Presenter extends BasePresenter {
        void onCellTouched(int row, int col);
        void onCellChanged(int row, int col);
        void onCellFinalized(int row, int col);
    }

    interface View extends BaseView<Presenter> {
        void highlightCell(int row, int col);
        void cancelCellHighlight();
        void unhighlightCell(int row, int col);
        void markCellWithO(int row, int col);
        void markCellWithX(int row, int col);
    }

}
