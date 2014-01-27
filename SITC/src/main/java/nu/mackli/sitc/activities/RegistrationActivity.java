package nu.mackli.sitc.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.util.Log;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.parse.ParseFacebookUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import nu.mackli.sitc.R;
import nu.mackli.sitc.fragments.RegistrationInfoFragment;
import nu.mackli.sitc.fragments.RegistrationInfoFragment_;

/**
 * Created by macklinu on 1/26/14.
 */
@EActivity(R.layout.activity_registration)
public class RegistrationActivity extends Activity {
    public static final int WITH_EMAIL = 0;
    public static final int WITH_FACEBOOK = 1;
    public static final int WITH_TWITTER = 2;

    @Extra int startedWith;

    @AfterViews
    public void onAfterViews() {
        switch (startedWith) {
            case WITH_EMAIL:
                break;
            case WITH_FACEBOOK:
                getFacebookData();
                break;
            case WITH_TWITTER:
                break;
            default:
                // how did we get here?
                break;
        }
    }

    private void getFacebookData() {
        Request request = Request.newMeRequest(ParseFacebookUtils.getSession(), new Request.GraphUserCallback() {
            @Override
            public void onCompleted(GraphUser graphUser, Response response) {
                RegistrationInfoFragment fragment = RegistrationInfoFragment_.builder()
                        .firstName(graphUser.getFirstName())
                        .lastName(graphUser.getLastName())
                        .email(graphUser.asMap().get("email").toString())
                        .build();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.registrationFrame, fragment)
                        .commit();
            }
        });
        request.executeAsync();
    }

}