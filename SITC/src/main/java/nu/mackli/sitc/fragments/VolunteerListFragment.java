package nu.mackli.sitc.fragments;

import android.widget.ListView;
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

import nu.mackli.sitc.R;
import nu.mackli.sitc.api.RestCallback;
import nu.mackli.sitc.api.randomuser.RandomUserApi;
import nu.mackli.sitc.models.randomuser.RandomUser;
import nu.mackli.sitc.models.randomuser.Results;

/**
 * Created by macklinu on 1/24/14.
 */
@EFragment(R.layout.fragment_volunteer_list)
@OptionsMenu(R.menu.main)
public class VolunteerListFragment extends BaseFragment implements RestCallback<RandomUser> {

    @Bean RandomUserApi api;

    @ViewById ProgressBar progressBar;
    @ViewById ListView volunteerList;

    ArrayList<Results> results;

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
            }

            @Override
            public void onError(HttpClientErrorException error) {

            }

            @Override
            public void onFinish() {

            }
        });
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
    @UiThread
    public void onBegin() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void onSuccess(RandomUser response) {
        setResults(response.getResults());
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
