package nu.mackli.sitc.models;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import nu.mackli.sitc.models.enums.Gender;

/**
 * Created by macklinu on 1/31/14.
 */
@ParseClassName("TestUsers")
public class TestUser extends ParseObject {
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String PRIMARY_CARPOOL_SITE = "primaryCarpoolSite";

    public String getFirstName() {
        return getString(FIRST_NAME);
    }

    public String getLastName() {
        return getString(LAST_NAME);
    }

    public String getFullName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }

    public String getEmail() {
        return getString("email");
    }

    public Gender getGender() {
        if (getString("gender").equalsIgnoreCase("male")) return Gender.MALE;
        else return Gender.FEMALE;
    }

    public String getAddress() {
        return getString("address");
    }

    public ParseGeoPoint getCoordinates() {
        return getParseGeoPoint("coordinates");
    }

    public CarpoolSite getPrimaryCarpoolSite() {
        return (CarpoolSite) getParseObject(PRIMARY_CARPOOL_SITE);
    }
}
