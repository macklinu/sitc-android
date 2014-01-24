package nu.mackli.sitc.models.randomuser;

import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by macklinu on 12/15/13.
 */
public class Location {
    String street;
    String city;
    String state;
    String zip;

    @Override
    public String toString() {
        return String.format("%s, %s, %s %s", street, city, state, zip);
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return WordUtils.capitalize(state);
    }

    public String getZip() {
        return zip;
    }
}
