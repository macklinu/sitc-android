package nu.mackli.sitc.activities;

import android.content.Intent;
import android.net.Uri;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.FragmentById;

import java.util.List;

import nu.mackli.sitc.R;
import nu.mackli.sitc.fragments.CarpoolSiteDetailsFragment;
import nu.mackli.sitc.models.CarpoolSite;

/**
 * Created by macklinu on 2/14/14.
 */
@EActivity(R.layout.activity_carpool_site)
public class CarpoolSiteActivity extends BaseActivity {

    @FragmentById MapFragment carpoolSiteMap;
    @FragmentById CarpoolSiteDetailsFragment carpoolSiteDetails;

    @Extra String carpoolSiteObjectId;

    private GoogleMap map;
    private LatLng latLng;
    private CarpoolSite carpoolSite;

    @AfterViews
    public void onAfterViews() {
        ParseQuery<CarpoolSite> query = ParseQuery.getQuery(CarpoolSite.class);
        query.whereEqualTo("objectId", carpoolSiteObjectId);
        query.findInBackground(new FindCallback<CarpoolSite>() {
            @Override
            public void done(List<CarpoolSite> carpoolSites, ParseException e) {
                setCarpoolSite(carpoolSites.get(0));
                setupMap();
                carpoolSiteDetails.setDetails(carpoolSite);
            }
        });
    }

    @Click
    public void directions() {
        String directions = String.format("http://maps.google.com/maps?saddr=1520+Woodward+Ave+Detroit+MI,+48226&daddr=%s,%s", latLng.latitude, latLng.longitude);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(directions));
        startActivity(intent);
    }

    public void setCarpoolSite(CarpoolSite carpoolSite) {
        this.carpoolSite = carpoolSite;
    }

    private void setupMap() {
        map = carpoolSiteMap.getMap();

        latLng = new LatLng(carpoolSite.getLatitude(), carpoolSite.getLongitude());
        map.addMarker(new MarkerOptions()
                .position(latLng)
                .flat(true));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(14)
                .build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                1000,
                new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onCancel() {

                    }
                });
    }
}
