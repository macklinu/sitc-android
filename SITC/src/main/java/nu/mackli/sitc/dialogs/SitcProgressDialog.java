package nu.mackli.sitc.dialogs;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by macklinu on 3/9/14.
 */
public class SitcProgressDialog extends ProgressDialog {
    public SitcProgressDialog(Context context, String message) {
        super(context);
        setCancelable(false);
        setIndeterminate(true);
        setMessage(message);
    }
}
