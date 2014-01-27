package nu.mackli.sitc.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.Window;

import com.parse.ParseFacebookUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.NoTitle;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.WindowFeature;

import nu.mackli.sitc.R;
import nu.mackli.sitc.fragments.LoginFragment;
import nu.mackli.sitc.fragments.LoginFragment_;

/**
 * Created by macklinu on 1/25/14.
 */
@EActivity(R.layout.activity_login)
@Fullscreen
@WindowFeature(Window.FEATURE_NO_TITLE)
public class LoginActivity extends Activity {
    public static final int FACEBOOK_FINISH_AUTH = 100;

    @AfterViews
    public void afterViews() {
        createFragment();
    }

    @OnActivityResult(FACEBOOK_FINISH_AUTH)
    public void onFacebookFinishAuth(int resultCode, Intent data) {
        ParseFacebookUtils.finishAuthentication(FACEBOOK_FINISH_AUTH, resultCode, data);
    }

    private void createFragment() {
        LoginFragment fragment = new LoginFragment_();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.loginFrame, fragment)
                .commit();
    }
}
