package nu.mackli.sitc.utils;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import nu.mackli.sitc.R;
import nu.mackli.sitc.SitcApplication;
import nu.mackli.sitc.SitcApplication_;
import nu.mackli.sitc.SitcTestRunner;

/**
 * Created by macklinu on 2/14/14.
 */
@RunWith(SitcTestRunner.class)
public class GoogleMapsUtilTest {

    private SitcApplication app;
    private GoogleMapsUtil googleMapsUtil;

    @Before
    public void setUp() throws Exception {
        app = SitcApplication_.getInstance();
        app.onCreate();
        googleMapsUtil = GoogleMapsUtil_.getInstance_(Robolectric.application);
    }

    @Test
    public void googleMapsAddressUrl() throws Exception {
        LatLng latLng = new LatLng(-45, 90);
        String address = "1755 Clark St, Detroit, MI 48216";
        String url = googleMapsUtil.getUrl(address, latLng);
        Assert.assertEquals(url, "http://maps.google.com/maps?saddr=1755+Clark+St,+Detroit,+MI+48216&daddr=-45.0,90.0");
    }

    @Test
    public void googleMapsLatLngUrl() throws Exception {
        LatLng startLatLng = new LatLng(-45, 90);
        LatLng endLatLng = new LatLng(-30, 77);
        String url = googleMapsUtil.getUrl(startLatLng, endLatLng);
        Assert.assertEquals(url, "http://maps.google.com/maps?saddr=-45.0,90.0&daddr=-30.0,77.0");
    }

    @Test
    public void googleMapsUrl() throws Exception {
        Assert.assertEquals(googleMapsUtil.getUrl(), app.getResources().getString(R.string.googleMapsUrlBlank));
    }

}
