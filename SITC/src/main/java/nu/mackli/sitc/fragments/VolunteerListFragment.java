package nu.mackli.sitc.fragments;

import android.app.Fragment;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
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
@OptionsMenu(R.menu.main)
public class VolunteerListFragment extends BaseFragment implements RestCallback<RandomUser> {

    private final int CELL_DEFAULT_HEIGHT = 200;
    private final int NUM_OF_CELLS = 15;

    @Bean RandomUserApi api;

    @ViewById ExpandingListView volunteerList;
    @ViewById ProgressBar progressBar;

    ArrayList<Results> results;
    ArrayList<ExpandableListItem> listItems;
    List<ExpandableListItem> mData;

    CustomArrayAdapter adapter;

    @AfterViews
    public void onAfterView() {
        api.getRandomUsers(5, this);
    }

    @OptionsItem
    public void actionNew() {
        // add person
        api.getRandomUsers(1, new RestCallback<RandomUser>() {
            @Override
            public void onBegin() {

            }


            @Override
            public void onSuccess(RandomUser response) {
                addUser(response);
            }

            @Override
            public void onError(HttpClientErrorException error) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    @UiThread
    public void addUser(RandomUser response) {
        Results result = response.getResults().get(0);
        String name = result.getUser().getName().toString();
        String picture = result.getUser().getPicture();
        mData.add(0, new ExpandableListItem(name, picture, CELL_DEFAULT_HEIGHT, "testing"));
        adapter.notifyDataSetChanged();
    }

    @OptionsItem
    public void actionSearch() {
        // search for person
    }

    @OptionsItem
    public void actionSettings() {
        // open settings
    }

    @UiThread
    public void createCells() {
        listItems = new ArrayList<ExpandableListItem>();
        for (Results result : results) {
            String name = result.getUser().getName().toString();
            String picture = result.getUser().getPicture();
            listItems.add(new ExpandableListItem(name, picture, CELL_DEFAULT_HEIGHT, "testing"));
        }

        mData = new ArrayList<ExpandableListItem>();

        for (int i = 0; i < NUM_OF_CELLS; i++) {
            ExpandableListItem obj = listItems.get(i % listItems.size());
            mData.add(new ExpandableListItem(obj.getTitle(), obj.getImgResource(),
                    obj.getCollapsedHeight(), obj.getText()));
        }

        adapter = new CustomArrayAdapter(getActivity(), R.layout.list_view_item, mData);

        volunteerList.setAdapter(adapter);
    }

    @Override
    @UiThread
    public void onBegin() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void onSuccess(RandomUser response) {
        setResults(response.getResults());
        createCells();
    }

    @Override
    public void onError(HttpClientErrorException error) {

    }

    @Override
    @UiThread
    public void onFinish() {
        progressBar.setVisibility(ProgressBar.GONE);
    }

    private void setResults(ArrayList<Results> results) {
        this.results = results;
    }
}
