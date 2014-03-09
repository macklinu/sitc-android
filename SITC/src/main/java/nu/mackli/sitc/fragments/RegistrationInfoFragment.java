package nu.mackli.sitc.fragments;

import android.app.DatePickerDialog;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;

import nu.mackli.sitc.R;
import nu.mackli.sitc.fragments.base.ContractFragment;
import nu.mackli.sitc.interfaces.RegistrationFragmentContract;
import nu.mackli.sitc.models.User;

/**
 * Created by macklinu on 1/26/14.
 */
@EFragment(R.layout.fragment_registration_info)
public class RegistrationInfoFragment extends ContractFragment<RegistrationFragmentContract> {
    public static final String FRAGMENT_TAG = "registrationFragment";

    @ViewById EditText firstNameInput;
    @ViewById EditText lastNameInput;
    @ViewById EditText dobInput;
    @ViewById EditText phoneInput;
    @ViewById EditText usernameInput;
    @ViewById EditText emailInput;
    @ViewById EditText passwordInput;

    @FragmentArg String firstName;
    @FragmentArg String lastName;
    @FragmentArg String email;

    private boolean isInAfterTextChange;

    @AfterViews
    public void onAfterViews() {
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
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int y, int m, int d) {
                setDateFromDatePicker(y, m, d);
            }
        }, year, month, day);
        datePicker.setTitle("Pick your birthday");
        datePicker.show();
    }

    public void setDateFromDatePicker(int y, int m, int d) {
        String date = String.format("%02d/%02d/%4d", m + 1, d, y);
        dobInput.setText(date);
        phoneInput.requestFocus();
    }


    @Click
    public void nextButton() {
        User user = new User();
        user.setUsername(usernameInput.getText().toString());
        user.setPassword(passwordInput.getText().toString());
        user.setEmail(emailInput.getText().toString());
        user.put(User.PHONE, phoneInput.getText().toString());
        user.put(User.DATE_OF_BIRTH, dobInput.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    getContract().onRegistrationFragmentNext(FRAGMENT_TAG);
                } else {
                    Toast.makeText(getActivity(), "User save error", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
}
