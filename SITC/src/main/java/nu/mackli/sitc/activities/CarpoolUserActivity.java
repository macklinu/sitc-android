package nu.mackli.sitc.activities;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.UiThread;

import nu.mackli.sitc.R;
import nu.mackli.sitc.fragments.CarpoolUserDetailFragment;
import nu.mackli.sitc.models.TestUser;

/**
 * Created by macklinu on 2/1/14.
 */
@EActivity(R.layout.activity_carpool_user)
public class CarpoolUserActivity extends BaseActivity {

    @FragmentById CarpoolUserDetailFragment detailFragment;

    @Extra String testUserObjectId;

    private TestUser testUser;

    @AfterViews
    public void onAfterViews() {
        ParseQuery<TestUser> query = ParseQuery.getQuery(TestUser.class);
        query.getInBackground(testUserObjectId, new GetCallback<TestUser>() {
            @Override
            public void done(TestUser testUser, ParseException e) {
                CarpoolUserActivity.this.testUser = testUser;
                updateDetailFragment();
            }
        });
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OptionsItem(android.R.id.home)
    public void onBackClick() {
        super.onBackPressed();
    }

    @UiThread
    public void updateDetailFragment() {
        detailFragment.setName(testUser.getFullName());
        detailFragment.setProfilePicImage(testUser);
    }
}
