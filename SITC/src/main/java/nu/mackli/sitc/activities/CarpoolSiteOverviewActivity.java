package nu.mackli.sitc.activities;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import java.util.List;

import nu.mackli.sitc.R;
import nu.mackli.sitc.models.CarpoolSite;

/**
 * Created by macklinu on 2/14/14.
 */
@EActivity(R.layout.activity_carpool_site_overview)
public class CarpoolSiteOverviewActivity extends BaseActivity {

    @FragmentById MapFragment mapFragment;

    private GoogleMap map;
    private List<CarpoolSite> carpoolSites;

    @AfterViews
    public void onAfterViews() {
        map = mapFragment.getMap();

        ParseQuery<CarpoolSite> query = ParseQuery.getQuery(CarpoolSite.class);
        query.findInBackground(new FindCallback<CarpoolSite>() {
            @Override
            public void done(List<CarpoolSite> carpoolSites, ParseException e) {
                setCarpoolSites(carpoolSites);
            }
        });
    }

    public void setCarpoolSites(List<CarpoolSite> carpoolSites) {
        this.carpoolSites = carpoolSites;
    }
}
