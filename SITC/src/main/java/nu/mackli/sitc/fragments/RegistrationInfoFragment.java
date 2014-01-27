package nu.mackli.sitc.fragments;

import android.app.Fragment;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import nu.mackli.sitc.R;

/**
 * Created by macklinu on 1/26/14.
 */
@EFragment(R.layout.fragment_registration_info)
public class RegistrationInfoFragment extends Fragment {

    @ViewById EditText firstNameInput;
    @ViewById EditText lastNameInput;
    @ViewById EditText emailInput;

    @FragmentArg String firstName;
    @FragmentArg String lastName;
    @FragmentArg String email;

    @AfterViews
    public void onAfterViews() {
        firstNameInput.setText(firstName);
        lastNameInput.setText(lastName);
        emailInput.setText(email);
    }
}
