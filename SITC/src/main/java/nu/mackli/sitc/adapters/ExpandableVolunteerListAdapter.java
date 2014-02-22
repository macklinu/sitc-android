package nu.mackli.sitc.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.haarman.listviewanimations.itemmanipulation.ExpandableListItemAdapter;

import java.util.List;

import nu.mackli.sitc.models.TestUser;

/**
 * Created by macklinu on 2/22/14.
 */
public class ExpandableVolunteerListAdapter extends ExpandableListItemAdapter<TestUser> {
    protected ExpandableVolunteerListAdapter(Context context) {
        super(context);
    }

    protected ExpandableVolunteerListAdapter(Context context, List<TestUser> items) {
        super(context, items);
    }

    protected ExpandableVolunteerListAdapter(Context context, int layoutResId, int titleParentResId, int contentParentResId) {
        super(context, layoutResId, titleParentResId, contentParentResId);
    }

    protected ExpandableVolunteerListAdapter(Context context, int layoutResId, int titleParentResId, int contentParentResId, List<TestUser> items) {
        super(context, layoutResId, titleParentResId, contentParentResId, items);
    }

    @Override
    public View getTitleView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getContentView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
