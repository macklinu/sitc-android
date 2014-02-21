package nu.mackli.sitc.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import org.androidannotations.annotations.EViewGroup;

import nu.mackli.sitc.R;

/**
 * Created by macklinu on 2/21/14.
 */
@EViewGroup(R.layout.view_paint_play_plant_button)
public class PaintPlayPlantButton extends LinearLayout {
    public PaintPlayPlantButton(Context context) {
        super(context);
    }

    public PaintPlayPlantButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintPlayPlantButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
