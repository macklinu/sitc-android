package nu.mackli.sitc.activities;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.res.DrawableRes;

import nu.mackli.sitc.R;
import nu.mackli.sitc.fragments.VolunteerListFragment_;
import nu.mackli.sitc.models.CarpoolSite;
import nu.mackli.sitc.views.ParseSpinnerAdapter;

/**
 * Created by macklinu on 1/24/14.
 */
@EActivity(R.layout.activity_main)
public class CarpoolActivity extends BaseActivity implements ActionBar.OnNavigationListener {

    @DrawableRes(R.drawable.ic_action_place) Drawable placeDrawable;

    private ParseSpinnerAdapter spinnerAdapter;



    @AfterViews
    public void afterViews() {
        // spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.carpoolSites, android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter = new ParseSpinnerAdapter(this, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {
                // Here we can configure a ParseQuery to our heart's desire.
                ParseQuery query = new ParseQuery(CarpoolSite.class);
                query.orderByAscending("name");
                return query;
            }
        });

        spinnerAdapter.setTextKey("name");

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
        createFragment(R.id.fragmentFrame, new VolunteerListFragment_());
        return true;
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
