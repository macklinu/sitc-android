package nu.mackli.sitc.fragments;

import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import nu.mackli.sitc.R;
import nu.mackli.sitc.fragments.base.BaseFragment;
import nu.mackli.sitc.models.CarpoolSite;

/**
 * Created by macklinu on 2/14/14.
 */
@EFragment(R.layout.fragment_carpool_site_details)
public class CarpoolSiteDetailsFragment extends BaseFragment {

    @ViewById TextView name;
    @ViewById TextView address;

    private CarpoolSite carpoolSite;

    @AfterViews
    public void onAfterViews() {

    }

    public void setCarpoolSite(CarpoolSite carpoolSite) {
        this.carpoolSite = carpoolSite;
    }

    public void setDetails(CarpoolSite carpoolSite) {
        name.setText(carpoolSite.getName());
        address.setText(carpoolSite.getAddress());
    }
}
