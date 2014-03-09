package nu.mackli.sitc.activities;

import android.support.v4.app.Fragment;

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
import nu.mackli.sitc.fragments.RegistrationRoleFragment;
import nu.mackli.sitc.fragments.RegistrationRoleFragment_;
import nu.mackli.sitc.interfaces.RegistrationFragmentContract;

/**
 * Created by macklinu on 1/26/14.
 */
@EActivity(R.layout.activity_registration)
public class RegistrationActivity extends BaseActivity implements RegistrationFragmentContract {
    public static final int WITH_EMAIL = 0;
    public static final int WITH_FACEBOOK = 1;

    @Extra int startedWith;

    @AfterViews
    public void onAfterViews() {
        switch (startedWith) {
            case WITH_EMAIL:
                createFragment(R.id.registrationFrame, new RegistrationInfoFragment_());
                break;
            case WITH_FACEBOOK:
                getFacebookData();
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

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.registrationFrame, fragment, RegistrationInfoFragment.FRAGMENT_TAG)
                        .commit();
            }
        });
        request.executeAsync();
    }

    @Override
    public void onRegistrationFragmentNext(String fragmentTag) {
        if (fragmentTag.equalsIgnoreCase(RegistrationInfoFragment.FRAGMENT_TAG)) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.registrationFrame, new RegistrationRoleFragment_(), RegistrationRoleFragment.FRAGMENT_TAG)
                    .commit();
        }
    }

    @Override
    public void onRegistrationFragmentPrevious(String fragmentTag) {

    }
}
