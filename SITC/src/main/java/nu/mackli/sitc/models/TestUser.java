package nu.mackli.sitc.models;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

/**
 * Created by macklinu on 1/31/14.
 */
@ParseClassName("TestUsers")
public class TestUser extends ParseObject {

    private enum Gender {
        MALE,
        FEMALE
    }

    public String getFirstName() {
        return getString("firstName");
    }

    public String getLastName() {
        return getString("lastName");
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
}
