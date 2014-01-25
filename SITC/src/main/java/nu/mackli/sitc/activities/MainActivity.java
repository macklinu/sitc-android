package nu.mackli.sitc.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import nu.mackli.sitc.R;
import nu.mackli.sitc.fragments.VolunteerListFragment;
import nu.mackli.sitc.fragments.VolunteerListFragment_;

/**
 * Created by macklinu on 1/24/14.
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity implements ActionBar.OnNavigationListener {

    private SpinnerAdapter spinnerAdapter;

    @AfterViews
    public void afterViews() {
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.carpoolSites, android.R.layout.simple_spinner_dropdown_item);
        setUpActionBar();
    }

    /**
     * Called when the Activity is first created
     * @param itemPosition
     * @param itemId
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        // swap fragment
        createFragment();
        return true;
    }

    private void createFragment() {
        VolunteerListFragment fragment = new VolunteerListFragment_();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentFrame, fragment)
                .commit();
    }

    private void setUpActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setListNavigationCallbacks(spinnerAdapter, this);
        }
    }
}
