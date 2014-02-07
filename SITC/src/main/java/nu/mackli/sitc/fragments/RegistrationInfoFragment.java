package nu.mackli.sitc.fragments;

import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import nu.mackli.sitc.R;
import nu.mackli.sitc.interfaces.FormValidationInterface;

/**
 * Created by macklinu on 1/26/14.
 */
@EFragment(R.layout.fragment_registration_info)
public class RegistrationInfoFragment extends BaseFragment {

    @ViewById EditText firstNameInput;
    @ViewById EditText lastNameInput;
    @ViewById EditText emailInput;
    @ViewById EditText passwordInput;

    @FragmentArg String firstName;
    @FragmentArg String lastName;
    @FragmentArg String email;

    private FormValidationInterface formValidationInterface;

    @AfterViews
    public void onAfterViews() {
        setViewsText();
    }

    public void setInterface(FormValidationInterface formValidationInterface) {
        this.formValidationInterface = formValidationInterface;
    }

    private void setViewsText() {
        firstNameInput.setText(firstName);
        lastNameInput.setText(lastName);
        emailInput.setText(email);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Click
    public void registerButton() {

        /*
        ParseUser user = ParseUser.getCurrentUser();

        String fName = firstNameInput.getText().toString();
        String lName = lastNameInput.getText().toString();
        String email = emailInput.getText().toString();
        String pword = passwordInput.getText().toString();

        user.setEmail(email);
        user.setPassword(pword);
        user.put("firstName", fName);
        user.put("lastName", lName);
        user.saveInBackground();
        */
    }
}
