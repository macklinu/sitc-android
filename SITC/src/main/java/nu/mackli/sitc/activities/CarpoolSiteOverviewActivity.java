package nu.mackli.sitc.activities;

import com.google.android.gms.maps.MapFragment;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import nu.mackli.sitc.R;

/**
 * Created by macklinu on 2/14/14.
 */
@EActivity(R.layout.activity_carpool_site_overview)
public class CarpoolSiteOverviewActivity extends BaseActivity {

    @FragmentById MapFragment mapFragment;
}
