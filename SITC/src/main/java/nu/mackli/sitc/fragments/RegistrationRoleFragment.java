package nu.mackli.sitc.fragments;

import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import nu.mackli.sitc.R;
import nu.mackli.sitc.dialogs.RoleAssignDialog;
import nu.mackli.sitc.dialogs.RoleAssignDialog_;
import nu.mackli.sitc.dialogs.SitcProgressDialog;
import nu.mackli.sitc.fragments.base.ContractFragment;
import nu.mackli.sitc.interfaces.RegistrationFragmentContract;
import nu.mackli.sitc.models.RegistrationUserData;

@EFragment(R.layout.fragment_registration_role)
public class RegistrationRoleFragment extends ContractFragment<RegistrationFragmentContract> {
    public static final String FRAGMENT_TAG = "registrationRoleFragment";

    @ViewById ProgressBar progressBar;

    @FragmentArg
    RegistrationUserData userData;

    @Click
    public void crewButton() {
        final ParseUser parseUser = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ListCrew");
        query.whereEqualTo("email", parseUser.getEmail());
        final SitcProgressDialog progressDialog = new SitcProgressDialog(getActivity(), "Determining crew member status");
        progressDialog.show();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> crewList, ParseException e) {
                progressDialog.dismiss();
                if (e == null) {
                    if (crewList.size() > 0) {
                        Toast.makeText(getActivity(), "You are a crew member!", Toast.LENGTH_SHORT).show();
                    } else {
                        RoleAssignDialog dialog = RoleAssignDialog_
                                .builder()
                                .email(parseUser.getEmail())
                                .build();
                        dialog.show(getActivity().getSupportFragmentManager(),
                                RoleAssignDialog.FRAGMENT_TAG);
                    }
                } else {
                    Log.d("crew list", "Error: " + e.getMessage());
                }
            }
        });
    }

    @Click
    public void volunteerButton() {
        Toast.makeText(getActivity(), "Continue with volunteer sign up flow", Toast.LENGTH_SHORT).show();
    }
}
