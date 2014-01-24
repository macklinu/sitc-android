package nu.mackli.sitc.fragments;

import android.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import nu.mackli.sitc.R;
import nu.mackli.sitc.adapters.CustomArrayAdapter;
import nu.mackli.sitc.api.RestCallback;
import nu.mackli.sitc.api.randomuser.RandomUserApi;
import nu.mackli.sitc.models.randomuser.RandomUser;
import nu.mackli.sitc.models.randomuser.Results;
import nu.mackli.sitc.views.ExpandableListItem;
import nu.mackli.sitc.views.ExpandingListView;

/**
 * Created by macklinu on 1/24/14.
 */
@EFragment(R.layout.fragment_volunteer_list)
public class VolunteerListFragment extends Fragment implements RestCallback<RandomUser> {

    private final int CELL_DEFAULT_HEIGHT = 200;
    private final int NUM_OF_CELLS = 30;

    @Bean RandomUserApi api;

    @ViewById ExpandingListView volunteerList;

    ArrayList<Results> results;
    ArrayList<ExpandableListItem> listItems;

    @AfterViews
    public void onAfterView() {
        api.getRandomUsers(3, this);
    }

    @UiThread
    public void createCells() {
        listItems = new ArrayList<ExpandableListItem>();
        for (Results result : results) {
            String name = result.getUser().getName().toString();
            String picture = result.getUser().getPicture();
            listItems.add(new ExpandableListItem(name, picture, CELL_DEFAULT_HEIGHT, "testing"));
        }

        List<ExpandableListItem> mData = new ArrayList<ExpandableListItem>();

        for (int i = 0; i < NUM_OF_CELLS; i++) {
            ExpandableListItem obj = listItems.get(i % listItems.size());
            mData.add(new ExpandableListItem(obj.getTitle(), obj.getImgResource(),
                    obj.getCollapsedHeight(), obj.getText()));
        }

        CustomArrayAdapter adapter = new CustomArrayAdapter(getActivity(), R.layout.list_view_item, mData);

        volunteerList.setAdapter(adapter);
        volunteerList.setDivider(null);
    }

    @Override
    public void onSuccess(RandomUser response) {
        setResults(response.getResults());
        createCells();
    }

    @Override
    public void onError(HttpClientErrorException error) {

    }

    private void setResults(ArrayList<Results> results) {
        this.results = results;
    }
}
