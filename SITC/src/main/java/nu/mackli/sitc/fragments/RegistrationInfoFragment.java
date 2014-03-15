package nu.mackli.sitc.fragments;

import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.devspark.appmsg.AppMsg;
import com.doomonafireball.betterpickers.datepicker.DatePickerBuilder;
import com.doomonafireball.betterpickers.datepicker.DatePickerDialogFragment;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Regex;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;

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
import nu.mackli.sitc.utils.FormRegex;

/**
 * Created by macklinu on 1/26/14.
 */
@EFragment(R.layout.fragment_registration_info)
public class RegistrationInfoFragment extends ContractFragment<RegistrationFragmentContract>
        implements DatePickerDialogFragment.DatePickerDialogHandler, Validator.ValidationListener {
    public static final String FRAGMENT_TAG = "registrationFragment";

    @Required(order = 0, messageResId = R.string.error_first_name)
    @ViewById
    EditText firstNameInput;

    @Required(order = 1, messageResId = R.string.error_last_name)
    @ViewById
    EditText lastNameInput;

    @Required(order = 2, messageResId = R.string.error_date_of_birth)
    @ViewById
    EditText dobInput;

    @Required(order = 3, messageResId = R.string.error_phone)
    @Regex(order = 4, pattern = FormRegex.PHONE, messageResId = R.string.error_phone_valid)
    @ViewById
    EditText phoneInput;

    @Required(order = 5, messageResId = R.string.error_username)
    @ViewById
    EditText usernameInput;

    @Required(order = 6, messageResId = R.string.error_email)
    @Email(order = 7, messageResId = R.string.error_email_valid)
    @ViewById
    AutoCompleteTextView emailInput;

    @Password(order = 8, messageResId = R.string.error_password)
    @TextRule(order = 9, minLength = 6, messageResId = R.string.error_password_length)
    @ViewById
    EditText passwordInput;

    @ViewById
    Button nextButton;

    @FragmentArg
    String firstName;

    @FragmentArg
    String lastName;

    @FragmentArg
    String email;

    @AnimationRes
    Animation shake;

    private boolean isInAfterTextChange;
    private Validator validator;
    private AppMsg msg;

    @AfterViews
    public void onAfterViews() {
        validator = new Validator(this);
        validator.setValidationListener(this);

        firstNameInput.setText(firstName);
        lastNameInput.setText(lastName);
        emailInput.setText(email);
    }


    @Override
    public void onDialogDateSet(int reference, int y, int m, int d) {
        setDateFromDatePicker(y, m, d);
    }

    @Override
    public void onValidationSucceeded() {
        if (msg != null && msg.isShowing()) {
            msg.cancel();
        }
        msg = AppMsg.makeText(getActivity(), "You did it!", AppMsg.STYLE_INFO);
        msg.show();
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        if (failedView instanceof EditText) {
            EditText editText = (EditText) failedView;
            if (!editText.requestFocus()) {
                editText.performClick();
            }
            editText.startAnimation(shake);
        }
        if (msg != null && msg.isShowing()) {
            msg.cancel();
        }
        msg = AppMsg.makeText(getActivity(), failedRule.getFailureMessage(), AppMsg.STYLE_ALERT);
        msg.show();
    }

    @Click
    public void dobInput() {
        DatePickerBuilder datePickerBuilder = new DatePickerBuilder()
                .setFragmentManager(getFragmentManager())
                .setStyleResId(com.doomonafireball.betterpickers.R.style.BetterPickersDialogFragment_Light)
                .setTargetFragment(this);
        datePickerBuilder.show();
    }

    @Click
    public void nextButton() {
        validator.validate();
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

    public void setDateFromDatePicker(int y, int m, int d) {
        String date = String.format("%02d/%02d/%4d", m + 1, d, y);
        dobInput.setText(date);
        phoneInput.requestFocus();
    }
}
