package nu.mackli.sitc.utils;

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
        if (textView.getText() != null) {
            checks.add(true);
        } else {
            checks.add(false);
        }
        return this;
    }

    public boolean validate() {
        for (Boolean b : checks) {
            if (!b) return false;
        }
        return true;
    }
}
