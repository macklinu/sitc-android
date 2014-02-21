package nu.mackli.sitc.utils;

import com.google.android.gms.maps.model.LatLng;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.res.StringRes;

/**
 * Created by macklinu on 2/14/14.
 */
@EBean
public class GoogleMapsUtil {

    @StringRes String googleMapsUrlBlank;
    @StringRes String googleMapsUrlFromAddress;
    @StringRes String googleMapsUrlFromCoordinates;

    public String getUrl(String startAddress, LatLng destLatLong) {
        startAddress = convertAddress(startAddress);
        return String.format(googleMapsUrlFromAddress, startAddress, destLatLong.latitude, destLatLong.longitude);
    }

    public String getUrl(LatLng startLatLng, LatLng destLatLong) {
        return String.format(googleMapsUrlFromCoordinates, startLatLng.latitude, startLatLng.longitude,
                destLatLong.latitude, destLatLong.longitude);
    }

    public String getUrl() {
        return googleMapsUrlBlank;
    }

    private String convertAddress(String address) {
        return address.replaceAll("\\s+", "+");
    }
}
