package nu.mackli.sitc.fragments;

import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import de.hdodenhof.circleimageview.CircleImageView;
import nu.mackli.sitc.R;
import nu.mackli.sitc.models.TestUser;
import nu.mackli.sitc.utils.TestUserUtil;

/**
 * Created by macklinu on 2/1/14.
 */
@EFragment(R.layout.fragment_carpool_user_detail)
public class CarpoolUserDetailFragment extends BaseFragment {

    @ViewById CircleImageView profilePic;
    @ViewById TextView name;

    @FragmentArg String userName;

    private TestUser testUser;

    @AfterViews
    public void onAfterViews() {
        name.setText(testUser.getFullName());
        setProfilePicImage(testUser);
    }

    public void setTestUser(TestUser testUser) {
        this.testUser = testUser;
    }

    public void setProfilePicImage(TestUser testUser) {
        profilePic.setImageResource(TestUserUtil.determinePlaceholderImage(testUser));
    }
}
