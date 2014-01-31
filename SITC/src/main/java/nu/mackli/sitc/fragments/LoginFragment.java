package nu.mackli.sitc.fragments;

import android.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.res.StringArrayRes;
import org.androidannotations.annotations.res.StringRes;

import java.util.Arrays;
import java.util.List;

import nu.mackli.sitc.R;
import nu.mackli.sitc.activities.CarpoolActivity_;
import nu.mackli.sitc.activities.LoginActivity;
import nu.mackli.sitc.activities.MapActivity_;
import nu.mackli.sitc.activities.RegistrationActivity_;

import static nu.mackli.sitc.activities.RegistrationActivity.WITH_EMAIL;
import static nu.mackli.sitc.activities.RegistrationActivity.WITH_FACEBOOK;
import static nu.mackli.sitc.activities.RegistrationActivity.WITH_TWITTER;

/**
 * Created by macklinu on 1/25/14.
 */
@EFragment(R.layout.fragment_login)
public class LoginFragment extends BaseFragment {

    @StringArrayRes String[] facebookPermissions;

    @AfterViews
    public void onAfterViews() {
    }

    /**
     * Click methods
     */

    @Click
    public void carpoolButton() {
        // go to carpool activity
        CarpoolActivity_
                .intent(getActivity())
                .start();
    }

    @Click
    public void facebookButton() {
        logInWithFacebook();
    }

    @Click
    public void emailButton() {
        logInWithEmail();
        // log in with email
        // move to the RegistrationActivity
    }

    private void logInWithFacebook() {
        List<String> permissions = Arrays.asList(facebookPermissions);
        ParseFacebookUtils.logIn(permissions, getActivity(), LoginActivity.FACEBOOK_FINISH_AUTH, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                // handle facebook login
                // move to data registration view
                startRegistrationActivity(WITH_FACEBOOK);
            }
        });
    }

    private void logInWithEmail() {
        startRegistrationActivity(WITH_EMAIL);
    }

    private void startRegistrationActivity(int startedWith) {
        RegistrationActivity_
                .intent(getActivity())
                .startedWith(startedWith)
                .start();
    }
}
