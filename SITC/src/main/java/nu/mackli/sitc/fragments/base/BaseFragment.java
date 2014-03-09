package nu.mackli.sitc.fragments.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import org.androidannotations.annotations.EFragment;

/**
 * Created by macklinu on 1/31/14.
 */
@EFragment
public class BaseFragment extends Fragment {

    public void createFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(containerViewId, fragment)
                .commit();
    }
}
