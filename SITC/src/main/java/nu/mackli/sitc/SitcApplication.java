package nu.mackli.sitc;

import android.app.Application;

import com.parse.Parse;

import org.androidannotations.annotations.EApplication;

import nu.mackli.sitc.api.parse.ParseConstants;

/**
 * Created by macklinu on 1/25/14.
 */
@EApplication
public class SitcApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, ParseConstants.APPLICATION_ID, ParseConstants.CLIENT_ID);
    }
}
