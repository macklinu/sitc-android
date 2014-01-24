package nu.mackli.sitc.models.randomuser;

import java.util.ArrayList;

/**
 * Created by macklinu on 12/15/13.
 */
public class RandomUser {
    ArrayList<Results> results;

    public ArrayList<Results> getResults() {
        return results;
    }

    public Results getUser() {
        return results.get(0);
    }

}