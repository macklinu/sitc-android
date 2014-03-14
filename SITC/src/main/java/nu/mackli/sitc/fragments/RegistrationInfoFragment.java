package nu.mackli.sitc.fragments;

import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;

import com.devspark.appmsg.AppMsg;
import com.doomonafireball.betterpickers.datepicker.DatePickerBuilder;
import com.doomonafireball.betterpickers.datepicker.DatePickerDialogFragment;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Required;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.AnimationRes;

import nu.mackli.sitc.R;
import nu.mackli.sitc.fragments.base.ContractFragment;
import nu.mackli.sitc.interfaces.RegistrationFragmentContract;

/**
 * Created by macklinu on 1/26/14.
 */
@EFragment(R.layout.fragment_registration_info)
public class RegistrationInfoFragment extends ContractFragment<RegistrationFragmentContract>
        implements DatePickerDialogFragment.DatePickerDialogHandler, Validator.ValidationListener {
    public static final String FRAGMENT_TAG = "registrationFragment";

    @Required(order = 0, message = "Give me a fucking first name")
    @ViewById
    EditText firstNameInput;

    @Required(order = 1, message = "Give me a fucking last name")
    @ViewById
    EditText lastNameInput;

    @Required(order = 2, message = "Give me a fucking date of birth")
    @ViewById
    EditText dobInput;

    @ViewById
    EditText phoneInput;

    @ViewById
    EditText usernameInput;

    @Required(order = 3, message = "Give me a valid fucking email")
    @ViewById
    EditText emailInput;

    @ViewById
    EditText passwordInput;

    @AnimationRes
    Animation shake;


    @FragmentArg String firstName;
    @FragmentArg String lastName;
    @FragmentArg String email;

    private boolean isInAfterTextChange;
    private Validator validator;

    @AfterViews
    public void onAfterViews() {
        validator = new Validator(this);
        validator.setValidationListener(this);

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
    public void dobInput() {
        DatePickerBuilder datePickerBuilder = new DatePickerBuilder()
                .setFragmentManager(getFragmentManager())
                .setStyleResId(com.doomonafireball.betterpickers.R.style.BetterPickersDialogFragment_Light)
                .setTargetFragment(this);
        datePickerBuilder.show();
    }

    public void setDateFromDatePicker(int y, int m, int d) {
        String date = String.format("%02d/%02d/%4d", m + 1, d, y);
        dobInput.setText(date);
        phoneInput.requestFocus();
    }


    @Click
    public void nextButton() {
        validator.validate();
        /*
        User user = new User();
        user.setUsername(usernameInput.getText().toString());
        user.setPassword(passwordInput.getText().toString());
        user.setEmail(emailInput.getText().toString());
        user.put(User.PHONE, phoneInput.getText().toString());
        user.put(User.DATE_OF_BIRTH, dobInput.getText().toString());

        final SitcProgressDialog progressDialog = new SitcProgressDialog(getActivity(), "Signing up");
        progressDialog.show();
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                progressDialog.dismiss();
                if (e == null) {
                    getContract().onRegistrationFragmentNext(RegistrationInfoFragment.this);
                } else {
                    Toast.makeText(getActivity(), "User save error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        */
    }

    @AfterTextChange
    public void phoneInputAfterTextChanged(Editable text) {
        if (!isInAfterTextChange) {
            isInAfterTextChange = true;
            if (text.length() > 0) {
                PhoneNumberUtils.formatNanpNumber(text);
            }
            isInAfterTextChange = false;
        }
    }

    @Override
    public void onDialogDateSet(int reference, int y, int m, int d) {
        setDateFromDatePicker(y, m, d);
    }

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        if (failedView instanceof EditText) {
            EditText editText = (EditText) failedView;
            editText.requestFocus();
            editText.startAnimation(shake);
        }
        AppMsg.makeText(getActivity(), failedRule.getFailureMessage(), AppMsg.STYLE_ALERT).show();
    }
}
