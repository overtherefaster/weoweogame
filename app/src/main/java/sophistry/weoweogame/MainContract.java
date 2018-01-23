package sophistry.weoweogame;

import sophistry.weoweogame.base.BasePresenter;
import sophistry.weoweogame.base.BaseView;

/**
 * Created by Vincent on 1/22/2018.
 */

public interface MainContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

    }

}
