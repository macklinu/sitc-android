package nu.mackli.sitc.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseQuery;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import nu.mackli.sitc.R;
import nu.mackli.sitc.models.CarpoolSite;

/**
 * Created by macklinu on 1/30/14.
 */
@EActivity(R.layout.activity_map)
public class MapActivity extends BaseActivity {

    @ViewById TextView siteName;
    @ViewById TextView siteAddress;

    private CarpoolSite carpoolSite;
    private GoogleMap googleMap;
    private GoogleMapOptions options;
    private LatLng latLng;

    @AfterViews
    public void onAfterViews() {
        ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getView().setVisibility(View.INVISIBLE);
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        ParseQuery<CarpoolSite> query = ParseQuery.getQuery(CarpoolSite.class);
        query.getInBackground("as48mQTk1Z", new GetCallback<CarpoolSite>() {
            @Override
            public void done(CarpoolSite carpoolSite, ParseException e) {
                if (e == null) {
                    MapActivity.this.carpoolSite = carpoolSite;
                    prepareMap();
                } else {
                    // do something else
                }
            }
        });
    }

    @Background
    public void prepareMap() {
        ParseGeoPoint geoPoint = carpoolSite.getCoordinates();
        latLng = new LatLng(geoPoint.getLatitude(), geoPoint.getLongitude());
        updateMap();
    }

    @UiThread
    public void updateMap() {
        String name = carpoolSite.getName();
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(14)
                .build();
        ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getView().setVisibility(View.VISIBLE);
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                2000,
                new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onCancel() {

                    }
                });
        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .flat(true)
                .snippet(carpoolSite.getAddress())
                .title(name));

        siteName.setText(name);
        siteAddress.setText(carpoolSite.getAddress());
    }

    @Click
    public void directions() {
        String directions = String.format("http://maps.google.com/maps?saddr=1739+Sainte+Anne+Detroit+MI,+48216&daddr=%s,%s", latLng.latitude, latLng.longitude);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(directions));
        startActivity(intent);
    }
}
