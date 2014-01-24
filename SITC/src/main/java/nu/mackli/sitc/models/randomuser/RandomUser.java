package nu.mackli.sitc.models.randomuser;

import java.util.ArrayList;

/**
 * Created by macklinu on 12/15/13.
 */
public class RandomUser {
    ArrayList<User> results;

    public ArrayList<User> getResults() {
        return results;
    }

    public User getUser() {
        return results.get(0);
    }

}