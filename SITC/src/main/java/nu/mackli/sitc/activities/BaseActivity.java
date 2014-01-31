package nu.mackli.sitc.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import org.androidannotations.annotations.EActivity;

/**
 * Created by macklinu on 1/31/14.
 */
@EActivity
public class BaseActivity extends FragmentActivity {

    public void createFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(containerViewId, fragment)
                .commit();
    }
}
