package nu.mackli.sitc.fragments;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import java.util.List;

import nu.mackli.sitc.R;

@EFragment(R.layout.fragment_registration_role)
public class RegistrationRoleFragment extends Fragment {
    public static final String FRAGMENT_TAG = "registrationRoleFragment";

    @AfterViews
    public void afterViews() {
        ParseUser parseUser = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ListCrew");
        query.whereEqualTo("email", parseUser.getEmail());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> crewList, ParseException e) {
                if (e == null) {
                    int size = crewList.size();
                    Log.d("crew list", "Retrieved " + size + " crew members");
                    if (size > 0) {
                        Log.d("crew list", crewList.get(0).getString("email"));
                    }
                } else {
                    Log.d("crew list", "Error: " + e.getMessage());
                }
            }
        });
    }

}
