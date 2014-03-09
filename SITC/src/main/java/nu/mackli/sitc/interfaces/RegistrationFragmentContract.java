package nu.mackli.sitc.interfaces;

import android.support.v4.app.Fragment;

import nu.mackli.sitc.fragments.base.ContractFragment;

/**
 * Created by macklinu on 3/9/14.
 */
public interface RegistrationFragmentContract {
    public void onRegistrationFragmentNext(Fragment fragment);
    public void onRegistrationFragmentPrevious(Fragment fragment);
}
