package nu.mackli.sitc.models.randomuser;

import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by macklinu on 12/15/13.
 */
public class Name {
    String title;
    String first;
    String last;

    public String getLast() {
        return WordUtils.capitalize(last);
    }

    public String getFirst() {
        return WordUtils.capitalize(first);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", title, first, last);
    }
}
