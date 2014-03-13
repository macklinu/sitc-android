package nu.mackli.sitc.utils;

import android.widget.EditText;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;


/**
 * Created by macklinu on 3/9/14.
 */
@RunWith(RobolectricTestRunner.class)
public class FormValidatorTest {
    private EditText editText;

    @Before
    public void setUp() throws Exception {
        editText = new EditText(Robolectric.application);
    }

    @Test
    public void nameMustBeGreaterThanZero() throws Exception {
        editText.setText("Dave");
        boolean isValid = new FormValidator()
                .name(editText)
                .validate();
        Assert.assertTrue(isValid);
    }

    @Test
    public void nameCannotBeLengthZero() throws Exception {
        editText.setText("");
        boolean isValid = new FormValidator()
                .name(editText)
                .validate();
        Assert.assertFalse(isValid);
    }
}
