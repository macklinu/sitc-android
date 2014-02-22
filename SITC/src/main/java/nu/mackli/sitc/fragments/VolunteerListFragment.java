package nu.mackli.sitc.fragments;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import nu.mackli.sitc.R;

/**
 * Created by macklinu on 1/24/14.
 */
@EFragment(R.layout.fragment_volunteer_list)
@OptionsMenu(R.menu.main)
public class VolunteerListFragment extends BaseFragment {

    @AfterViews
    public void onAfterView() {
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

}
