package nu.mackli.sitc.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

/**
 * Created by macklinu on 1/31/14.
 */
public class ParseSpinnerAdapter extends ParseQueryAdapter<ParseObject> {
    public ParseSpinnerAdapter(Context context, Class<? extends ParseObject> clazz) {
        super(context, clazz);
    }

    public ParseSpinnerAdapter(Context context, String className) {
        super(context, className);
    }

    public ParseSpinnerAdapter(Context context, Class<? extends ParseObject> clazz, int itemViewResource) {
        super(context, clazz, itemViewResource);
    }

    public ParseSpinnerAdapter(Context context, String className, int itemViewResource) {
        super(context, className, itemViewResource);
    }

    public ParseSpinnerAdapter(Context context, QueryFactory<ParseObject> queryFactory) {
        super(context, queryFactory);
    }

    public ParseSpinnerAdapter(Context context, QueryFactory<ParseObject> queryFactory, int itemViewResource) {
        super(context, queryFactory, itemViewResource);
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), android.R.layout.simple_spinner_item, null);
        }
        super.getItemView(object, v, parent);
        TextView textView = (TextView) v.findViewById(android.R.id.text1);
        textView.setText(object.getString("name"));
        return v;
    }

    @Override
    public ParseObject getItem(int index) {
        return super.getItem(index);
    }
}
