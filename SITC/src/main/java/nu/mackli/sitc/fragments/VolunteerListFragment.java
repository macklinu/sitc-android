package nu.mackli.sitc.fragments;

import android.app.ProgressDialog;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import nu.mackli.sitc.R;
import nu.mackli.sitc.activities.CarpoolUserActivity_;
import nu.mackli.sitc.adapters.ParseTestUserAdapter;
import nu.mackli.sitc.models.CarpoolSite;
import nu.mackli.sitc.models.TestUser;

/**
 * Created by macklinu on 1/24/14.
 */
@EFragment(R.layout.fragment_volunteer_list)
public class VolunteerListFragment extends BaseFragment {

    @ViewById ProgressBar progressBar;
    @ViewById ListView volunteerList;

    @FragmentArg String carpoolSiteId;

    private ParseTestUserAdapter adapter;

    private ProgressDialog dialog;

    @AfterViews
    public void onAfterViews() {
        dialog = new ProgressDialog(getActivity());
        adapter = new ParseTestUserAdapter(getActivity(), new ParseQueryAdapter.QueryFactory<ParseObject>() {

            @Override
            public ParseQuery<ParseObject> create() {
                ParseQuery<ParseObject> query = new ParseQuery(TestUser.class);
                ParseObject obj = ParseObject.createWithoutData(CarpoolSite.class, carpoolSiteId);
                query.whereEqualTo("primaryCarpoolSite", obj)
                        .orderByAscending(TestUser.LAST_NAME);
                return query;
            }
        });
        volunteerList.setAdapter(adapter);
    }

    @OptionsItem
    public void actionSearch() {
        // search for person
    }

    @OptionsItem
    public void actionSettings() {
        // open settings
    }

    @ItemClick
    public void volunteerListItemClicked(TestUser testUser) {
        CarpoolUserActivity_
                .intent(getActivity())
                .testUserObjectId(testUser.getObjectId())
                .start();
    }
}
