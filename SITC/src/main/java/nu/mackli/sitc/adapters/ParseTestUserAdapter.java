package nu.mackli.sitc.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import de.hdodenhof.circleimageview.CircleImageView;
import nu.mackli.sitc.R;
import nu.mackli.sitc.models.TestUser;
import nu.mackli.sitc.utils.TestUserUtil;

/**
 * Created by macklinu on 1/31/14.
 */
public class ParseTestUserAdapter extends ParseQueryAdapter<ParseObject> {
    public ParseTestUserAdapter(Context context, Class<? extends ParseObject> clazz) {
        super(context, clazz);
    }

    public ParseTestUserAdapter(Context context, String className) {
        super(context, className);
    }

    public ParseTestUserAdapter(Context context, Class<? extends ParseObject> clazz, int itemViewResource) {
        super(context, clazz, itemViewResource);
    }

    public ParseTestUserAdapter(Context context, String className, int itemViewResource) {
        super(context, className, itemViewResource);
    }

    public ParseTestUserAdapter(Context context, QueryFactory<ParseObject> queryFactory) {
        super(context, queryFactory);
    }

    public ParseTestUserAdapter(Context context, QueryFactory<ParseObject> queryFactory, int itemViewResource) {
        super(context, queryFactory, itemViewResource);
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        TestUser user = (TestUser) object;
        if (v == null) {
            v = View.inflate(getContext(), R.layout.list_view_item, null);
        }
        super.getItemView(object, v, parent);
        CircleImageView image = (CircleImageView) v.findViewById(R.id.image);
        TextView name = (TextView) v.findViewById(R.id.name);
        int placeholderImageId = TestUserUtil.determinePlaceholderImage(user);
        name.setText(user.getFullName());
        image.setImageResource(placeholderImageId);
        return v;
    }

    @Override
    public ParseObject getItem(int index) {
        return super.getItem(index);
    }
}
