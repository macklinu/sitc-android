package nu.mackli.sitc.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import nu.mackli.sitc.R;
import nu.mackli.sitc.api.randomuser.RandomUserApi;

/**
 * Created by macklinu on 1/24/14.
 */
@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends Activity {

    @Bean RandomUserApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        }
    }

    @OptionsItem
    public void actionAdd() {
        // add person
    }

    @OptionsItem
    public void actionSearch() {
        // search for person
    }

    @OptionsItem
    public void actionSettings() {
        // open settings
    }
}
