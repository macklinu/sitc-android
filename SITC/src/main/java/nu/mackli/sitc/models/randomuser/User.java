package nu.mackli.sitc.models.randomuser;

import com.google.gson.annotations.SerializedName;

/**
 * Created by macklinu on 12/15/13.
 */
public class User {

    @SerializedName("user")
    UserInfo info;

    String seed;
    String version;

    @Override
    public String toString() {
        return info.toString();
    }

    public UserInfo getInfo() {
        return info;
    }
}
