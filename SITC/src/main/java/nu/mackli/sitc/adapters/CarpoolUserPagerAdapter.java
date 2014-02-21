package nu.mackli.sitc.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import nu.mackli.sitc.activities.CarpoolUserActivity;
import nu.mackli.sitc.fragments.CarpoolUserDetailFragment_;
import nu.mackli.sitc.fragments.CarpoolUserHistoryFragment_;

/**
 * Created by macklinu on 2/5/14.
 */
public class CarpoolUserPagerAdapter extends FragmentPagerAdapter {
    public CarpoolUserPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0: return new CarpoolUserDetailFragment_();
            case 1: return new CarpoolUserHistoryFragment_();
            default: return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return CarpoolUserActivity.NUM_PAGES;
    }
}
