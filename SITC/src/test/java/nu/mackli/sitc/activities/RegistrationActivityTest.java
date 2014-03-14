package nu.mackli.sitc.activities;

import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;

import com.facebook.model.GraphUser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import nu.mackli.sitc.R;
import nu.mackli.sitc.SitcTestRunner;
import nu.mackli.sitc.fragments.RegistrationInfoFragment;
import nu.mackli.sitc.fragments.RegistrationInfoFragment_;

import static org.mockito.Mockito.mock;

/**
 * Created by macklinu on 2/16/14.
 */
@RunWith(SitcTestRunner.class)
public class RegistrationActivityTest {

    private RegistrationActivity registrationActivity;
    private RegistrationInfoFragment infoFragment;
    private RegistrationInfoFragment facebookFragment;
    private GraphUser graphUser;

    @Before
    public void setUp() throws Exception {
        registrationActivity = Robolectric.buildActivity(RegistrationActivity_.class)
                .create()
                .start()
                .resume()
                .visible()
                .get();

        graphUser = mock(GraphUser.class);

        infoFragment = new RegistrationInfoFragment_();

        FragmentTransaction ft = registrationActivity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.registrationFrame, infoFragment).commit();
    }

    @Test
    public void infoFragmentShouldNotBeNull() throws Exception {
        Assert.assertNotNull(infoFragment);
    }

    @Test
    public void allInfoFragmentFieldsShouldBeBlank() throws Exception {
        String firstName = ((EditText) Robolectric.shadowOf(registrationActivity).findViewById(R.id.firstNameInput)).getText().toString();
        String lastName = ((EditText) Robolectric.shadowOf(registrationActivity).findViewById(R.id.lastNameInput)).getText().toString();
        String password = ((EditText) Robolectric.shadowOf(registrationActivity).findViewById(R.id.passwordInput)).getText().toString();
        String email = ((EditText) Robolectric.shadowOf(registrationActivity).findViewById(R.id.emailInput)).getText().toString();
        String dob = ((EditText) Robolectric.shadowOf(registrationActivity).findViewById(R.id.dobInput)).getText().toString();
        String phone = ((EditText) Robolectric.shadowOf(registrationActivity).findViewById(R.id.phoneInput)).getText().toString();

        Assert.assertEquals(firstName, "");
        Assert.assertEquals(lastName, "");
        Assert.assertEquals(password, "");
        Assert.assertEquals(email, "");
        Assert.assertEquals(dob, "");
        Assert.assertEquals(phone, "");
    }

    @Test
    public void facebookFragmentFieldsShouldBePassedFromActivity() throws Exception {
        facebookFragment = RegistrationInfoFragment_.builder()
                .firstName("Bob")
                .lastName("Smith")
                .email("bob.smith@gmail.com")
                .build();

        Assert.assertNotNull(facebookFragment);

        FragmentTransaction ft = registrationActivity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.registrationFrame, facebookFragment).commit();

        String firstName = ((EditText) Robolectric.shadowOf(registrationActivity).findViewById(R.id.firstNameInput)).getText().toString();
        String lastName = ((EditText) Robolectric.shadowOf(registrationActivity).findViewById(R.id.lastNameInput)).getText().toString();
        String password = ((EditText) Robolectric.shadowOf(registrationActivity).findViewById(R.id.passwordInput)).getText().toString();
        String email = ((EditText) Robolectric.shadowOf(registrationActivity).findViewById(R.id.emailInput)).getText().toString();
        String dob = ((EditText) Robolectric.shadowOf(registrationActivity).findViewById(R.id.dobInput)).getText().toString();
        String phone = ((EditText) Robolectric.shadowOf(registrationActivity).findViewById(R.id.phoneInput)).getText().toString();

        Assert.assertEquals(firstName, "Bob");
        Assert.assertEquals(lastName, "Smith");
        Assert.assertEquals(password, "");
        Assert.assertEquals(email, "bob.smith@gmail.com");
        Assert.assertEquals(dob, "");
        Assert.assertEquals(phone, "");
    }

    @Test
    public void setDateFromDatePickerTest() throws Exception {
        EditText dobInput = ((EditText) Robolectric.shadowOf(registrationActivity).findViewById(R.id.dobInput));
        infoFragment.setDateFromDatePicker(2013, 1, 3);
        String date = dobInput.getText().toString();
        Assert.assertEquals(date, "02/03/2013");
    }
}