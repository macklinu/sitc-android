package nu.mackli.sitc.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import nu.mackli.sitc.R;
import nu.mackli.sitc.api.randomuser.RandomUserApi;
import nu.mackli.sitc.fragments.VolunteerListFragment;
import nu.mackli.sitc.fragments.VolunteerListFragment_;

/**
 * Created by macklinu on 1/24/14.
 */
@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends Activity implements ActionBar.OnNavigationListener {

    @Bean RandomUserApi api;

    private SpinnerAdapter spinnerAdapter;

    @AfterViews
    public void afterViews() {
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.carpoolSites, android.R.layout.simple_spinner_dropdown_item);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setListNavigationCallbacks(spinnerAdapter, this);
        }

        VolunteerListFragment fragment = new VolunteerListFragment_();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentFrame, fragment)
                .commit();
    }

    @OptionsItem
    public void actionNew() {
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

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        // swap fragment
        return true;
    }
}
