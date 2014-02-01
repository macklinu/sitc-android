package nu.mackli.sitc.fragments;

import android.widget.ListView;
import android.widget.ProgressBar;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import nu.mackli.sitc.R;
import nu.mackli.sitc.adapters.ParseTestUserAdapter;
import nu.mackli.sitc.api.randomuser.RandomUserApi;
import nu.mackli.sitc.models.CarpoolSite;
import nu.mackli.sitc.models.TestUser;

/**
 * Created by macklinu on 1/24/14.
 */
@EFragment(R.layout.fragment_volunteer_list)
public class VolunteerListFragment extends BaseFragment {

    @Bean RandomUserApi api;

    @ViewById ProgressBar progressBar;
    @ViewById ListView volunteerList;

    @FragmentArg String carpoolSiteId;

    private ParseTestUserAdapter adapter;

    @AfterViews
    public void onAfterView() {
        adapter = new ParseTestUserAdapter(getActivity(), new ParseQueryAdapter.QueryFactory<ParseObject>() {

            @Override
            public ParseQuery<ParseObject> create() {
                ParseQuery query = new ParseQuery(TestUser.class);
                ParseObject obj = ParseObject.createWithoutData(CarpoolSite.class, carpoolSiteId);
                query.whereEqualTo("primaryCarpoolSite", obj);
                query.orderByAscending(TestUser.LAST_NAME);
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

}
