package sophistry.weoweogame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import sophistry.weoweogame.util.ActivityUtils;

/**
 * Created by Vincent on 1/22/2018.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create view if container is empty
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            ActivityUtils.addFragmentToContainer(getSupportFragmentManager(), mainFragment, R.id.content_container);
        }

    }
}
