package nu.mackli.sitc.activities;

import android.app.ActionBar;
import android.support.v4.view.ViewPager;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import nu.mackli.sitc.R;
import nu.mackli.sitc.adapters.CarpoolUserPagerAdapter;
import nu.mackli.sitc.models.TestUser;

/**
 * Created by macklinu on 2/1/14.
 */
@EActivity(R.layout.activity_carpool_user)
public class CarpoolUserActivity extends BaseActivity {
    public static final int NUM_PAGES = 2;

    @ViewById ViewPager carpoolUserPager;
    @Extra String testUserObjectId;

    private ActionBar actionBar;

    private TestUser testUser;

    @AfterViews
    public void onAfterViews() {
        carpoolUserPager.setAdapter(new CarpoolUserPagerAdapter(getSupportFragmentManager()));
        ParseQuery<TestUser> query = ParseQuery.getQuery(TestUser.class);
        setUpActionBar();
        query.getInBackground(testUserObjectId, new GetCallback<TestUser>() {
            @Override
            public void done(TestUser testUser, ParseException e) {
                CarpoolUserActivity.this.testUser = testUser;
                updateDetailFragment();
            }
        });
    }

    @OptionsItem(android.R.id.home)
    public void onBackClick() {
        super.onBackPressed();
    }

    @UiThread
    public void updateDetailFragment() {
    }

    private void setUpActionBar() {
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                carpoolUserPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                // hide the given tab
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                // probably ignore this event
            }
        };

        // Add 3 tabs, specifying the tab's text and TabListener
        actionBar.addTab(
                actionBar.newTab()
                        .setText("Details")
                        .setTabListener(tabListener));
        actionBar.addTab(
                actionBar.newTab()
                        .setText("History")
                        .setTabListener(tabListener));

        carpoolUserPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between pages, select the
                // corresponding tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });
    }
}
