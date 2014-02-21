package nu.mackli.sitc.utils;

import nu.mackli.sitc.R;
import nu.mackli.sitc.models.TestUser;
import nu.mackli.sitc.models.enums.Gender;

/**
 * Created by macklinu on 2/1/14.
 */
public class TestUserUtil {

    public static int determinePlaceholderImage(TestUser testUser) {
        return (testUser.getGender() == Gender.MALE) ?
                R.drawable.male_placeholder :
                R.drawable.female_placeholder;
    }
}
