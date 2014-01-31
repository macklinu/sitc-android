package nu.mackli.sitc.activities;

import android.content.Intent;
import android.view.Window;

import com.parse.ParseFacebookUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.WindowFeature;

import nu.mackli.sitc.R;
import nu.mackli.sitc.fragments.LoginFragment_;

/**
 * Created by macklinu on 1/25/14.
 */
@EActivity(R.layout.activity_login)
@Fullscreen
@WindowFeature(Window.FEATURE_NO_TITLE)
public class LoginActivity extends BaseActivity {
    public static final int FACEBOOK_FINISH_AUTH = 100;

    @AfterViews
    public void afterViews() {
        // if user is logged in
        // log in the user
        // else
        // create login fragment
        createFragment(R.id.loginFrame, new LoginFragment_());
    }

    @OnActivityResult(FACEBOOK_FINISH_AUTH)
    public void onFacebookFinishAuth(int resultCode, Intent data) {
        ParseFacebookUtils.finishAuthentication(FACEBOOK_FINISH_AUTH, resultCode, data);
    }
}
