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

    @Test
    public void nameCannotBeNull() throws Exception {
        editText.setText(null);
        boolean isValid = new FormValidator()
                .name(editText)
                .validate();
        Assert.assertFalse(isValid);
    }

    @Test
    public void testMultipleValidNames() throws Exception {
        EditText nameOne = new EditText(Robolectric.application);
        EditText nameTwo = new EditText(Robolectric.application);

        nameOne.setText("Roger");
        nameTwo.setText("Dennis");

        boolean isValid = new FormValidator()
                .name(nameOne)
                .name(nameTwo)
                .validate();
        Assert.assertTrue(isValid);
    }

    @Test
    public void emailCannotBeBlank() throws Exception {
        editText.setText("");

        boolean isValid = new FormValidator()
                .email(editText)
                .validate();
        Assert.assertFalse(isValid);
    }
}
