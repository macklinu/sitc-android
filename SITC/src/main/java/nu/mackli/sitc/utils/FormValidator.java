package nu.mackli.sitc.utils;

import android.util.Patterns;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by macklinu on 3/9/14.
 */
public class FormValidator {
    ArrayList<Boolean> checks;
    String errorMessage;

    public FormValidator() {
        checks = new ArrayList<Boolean>();
    }

    public FormValidator name(TextView textView) {
        String text = textView.getText().toString();
        if (text.length() > 0) {
            checks.add(true);
        } else {
            checks.add(false);
        }
        return this;
    }

    public FormValidator email(TextView textView) {
        String text = textView.getText().toString();
        if (text != null) {
            if (Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                checks.add(true);
                return this;
            } else {
                checks.add(false);
                textView.setError("Enter a valid email address");
                return this;
            }
        } else {
            checks.add(false);
            return this;
        }
    }

    public boolean validate() {
        for (boolean b : checks) {
            if (b == Boolean.FALSE) {
                return false;
            }
        }
        return true;
    }
}
