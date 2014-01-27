package nu.mackli.sitc.fragments;

import android.app.Fragment;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.res.StringArrayRes;

import java.util.Arrays;
import java.util.List;

import nu.mackli.sitc.R;
import nu.mackli.sitc.activities.LoginActivity;

/**
 * Created by macklinu on 1/25/14.
 */
@EFragment(R.layout.fragment_login)
public class LoginFragment extends Fragment {

    @StringArrayRes String[] facebookPermissions;

    @Click
    public void facebookButton() {
        logInWithFacebook();
    }

    @Click
    public void twitterButton() {
        logInWithTwitter();
    }

    private void logInWithTwitter() {
        ParseTwitterUtils.logIn(getActivity(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                // handle twitter login
                // move to data registration view
            }
        });
    }

    private void logInWithFacebook() {
        List<String> permissions = Arrays.asList(facebookPermissions);
        ParseFacebookUtils.logIn(permissions, getActivity(), LoginActivity.FACEBOOK_FINISH_AUTH, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                // handle facebook login
                // move to data registration view
            }
        });
    }
}
