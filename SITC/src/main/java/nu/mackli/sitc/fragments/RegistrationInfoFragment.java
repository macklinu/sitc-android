package nu.mackli.sitc.fragments;

import android.app.DatePickerDialog;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.view.KeyEvent;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;

import nu.mackli.sitc.R;

/**
 * Created by macklinu on 1/26/14.
 */
@EFragment(R.layout.fragment_registration_info)
public class RegistrationInfoFragment extends BaseFragment {

    @ViewById EditText firstNameInput;
    @ViewById EditText lastNameInput;
    @ViewById EditText emailInput;
    @ViewById EditText passwordInput;
    @ViewById EditText dobInput;
    @ViewById EditText phoneInput;

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
                String date = String.format("%02d/%02d/%4d", m + 1, d, y);
                dobInput.setText(date);
                phoneInput.requestFocus();
            }
        }, year, month, day);
        datePicker.setTitle("Pick your birthday");
        datePicker.show();
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
