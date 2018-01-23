package sophistry.weoweogame.util;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Vincent on 1/22/2018.
 */

public class ActivityUtils {

    public static void addFragmentToContainer (@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int containerId) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(containerId, fragment);
        ft.commit();
    }
}
