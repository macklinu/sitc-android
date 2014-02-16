package nu.mackli.sitc.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.res.StringArrayRes;
import org.androidannotations.annotations.res.StringRes;

import java.util.List;

import nu.mackli.sitc.R;
import nu.mackli.sitc.fragments.CarpoolSiteDetailsFragment;
import nu.mackli.sitc.models.CarpoolSite;
import nu.mackli.sitc.utils.GoogleMapsUtil;

/**
 * Created by macklinu on 2/14/14.
 */
@EActivity(R.layout.activity_carpool_site)
public class CarpoolSiteActivity extends BaseActivity {

    @Bean GoogleMapsUtil googleMapsUtil;

    @FragmentById MapFragment carpoolSiteMap;
    @FragmentById CarpoolSiteDetailsFragment carpoolSiteDetails;

    @Extra String carpoolSiteObjectId;

    @StringArrayRes String[] directionsDialogItems;
    @StringRes String googleMapsUrlBlank;
    @StringRes String googleMapsUrlFromAddress;
    @StringRes String googleMapsUrlFromCoordinates;

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
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.directionsDialogTitle)
                .setItems(directionsDialogItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                // use current location
                                openGoogleMaps(googleMapsUtil.getUrl("1739 Sainte Anne, Detroit, MI 48216", latLng));
                                break;
                            case 1:
                                // use primary carpool site
                                openGoogleMaps(googleMapsUtil.getUrl());
                                break;
                            case 2:
                                // just open maps
                                openGoogleMaps(googleMapsUtil.getUrl());
                                break;
                            default:
                                // error
                                break;
                        }
                    }
                })
                .show();
    }

    private void openGoogleMaps(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(url));
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
