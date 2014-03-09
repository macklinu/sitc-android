package nu.mackli.sitc.fragments;

import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import java.util.List;

import nu.mackli.sitc.R;
import nu.mackli.sitc.fragments.base.ContractFragment;
import nu.mackli.sitc.interfaces.RegistrationFragmentContract;

@EFragment(R.layout.fragment_registration_role)
public class RegistrationRoleFragment extends ContractFragment<RegistrationFragmentContract> implements FindCallback<ParseObject> {
    public static final String FRAGMENT_TAG = "registrationRoleFragment";

    @Click
    public void crewButton() {
        ParseUser parseUser = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ListCrew");
        query.whereEqualTo("email", parseUser.getEmail());
        query.findInBackground(this);
    }

    @Click
    public void volunteerButton() {
        Toast.makeText(getActivity(), "Continue with volunteer sign up flow", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void done(List<ParseObject> crewList, ParseException e) {
        if (e == null) {
            if (crewList.size() > 0) {
                Toast.makeText(getActivity(), "You are a crew member!", Toast.LENGTH_SHORT).show();
            } else {
                // show dialog fragment
            }
        } else {
            Log.d("crew list", "Error: " + e.getMessage());
        }
    }
}
