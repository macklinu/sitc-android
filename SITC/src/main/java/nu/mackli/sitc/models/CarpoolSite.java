package nu.mackli.sitc.models;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

/**
 * Created by macklinu on 1/30/14.
 */
@ParseClassName("CarpoolSite")
public class CarpoolSite extends ParseObject {

    public String getName() {
        return getString("name");
    }

    public String getAddress() {
        return getString("address");
    }

    public ParseGeoPoint getCoordinates() {
        return getParseGeoPoint("coordinates");
    }
}
