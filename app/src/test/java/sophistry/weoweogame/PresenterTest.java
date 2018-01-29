package sophistry.weoweogame;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sophistry.weoweogame.data.Game;
import sophistry.weoweogame.data.MarkType;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Vincent on 1/28/2018.
 */

public class PresenterTest {

    @Mock
    Game game;

    @Mock
    MainFragment view;

    MainPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(game, view);
    }

    @Test
    public void testMoveProcessedOnTouchRelease() {
        when(game.processTurn(0,0)).thenReturn(MarkType.PLAYER_O);
        presenter.onCellFinalized(0, 0);
        verify(game, times(1)).processTurn(0, 0);
        verify(view, times(1)).markCellWithO(0, 0);
        verify(view, times(1)).cancelCellHighlight();
    }

}
