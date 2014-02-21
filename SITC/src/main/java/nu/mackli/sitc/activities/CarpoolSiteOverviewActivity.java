package nu.mackli.sitc.activities;

import android.content.Intent;
import android.util.Log;
import android.view.Window;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseQuery;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.WindowFeature;

import java.util.HashMap;
import java.util.List;

import nu.mackli.sitc.R;
import nu.mackli.sitc.models.CarpoolSite;

/**
 * Created by macklinu on 2/14/14.
 */
@EActivity(R.layout.activity_carpool_site_overview)
@WindowFeature({Window.FEATURE_NO_TITLE})
public class CarpoolSiteOverviewActivity extends BaseActivity {

    @FragmentById MapFragment mapFragment;

    private GoogleMap map;
    private List<CarpoolSite> carpoolSites;

    private HashMap<Marker, CarpoolSite> markerSiteMap;

    @AfterViews
    public void onAfterViews() {
        map = mapFragment.getMap();
        markerSiteMap = new HashMap<Marker, CarpoolSite>();

        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                Log.d("map time", cameraPosition.toString());
            }
        });

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                CarpoolSite site = markerSiteMap.get(marker);
                CarpoolSiteActivity_
                        .intent(CarpoolSiteOverviewActivity.this)
                        .carpoolSiteObjectId(site.getObjectId())
                        .flags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .start();
            }
        });

        ParseQuery<CarpoolSite> query = ParseQuery.getQuery(CarpoolSite.class);
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
        query.findInBackground(new FindCallback<CarpoolSite>() {
            @Override
            public void done(List<CarpoolSite> carpoolSites, ParseException e) {
                setCarpoolSites(carpoolSites);
                loopThroughCarpoolSites();
            }
        });
    }

    public void setCarpoolSites(List<CarpoolSite> carpoolSites) {
        this.carpoolSites = carpoolSites;
    }

    private void loopThroughCarpoolSites() {
        LatLngBounds.Builder boundBuilder = new LatLngBounds.Builder();
        for (CarpoolSite site: carpoolSites) {
            ParseGeoPoint coordinates = site.getCoordinates();
            LatLng latLng = new LatLng(coordinates.getLatitude(), coordinates.getLongitude());
            boundBuilder.include(latLng);
            Marker m = map.addMarker(new MarkerOptions()
                    .position(latLng)
                    .flat(true)
                    .title(site.getName()));
            markerSiteMap.put(m, site);
        }

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(boundBuilder.build().getCenter())
                .zoom(9)
                .build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                2000,
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
