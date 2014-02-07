package nu.mackli.sitc;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;

import org.androidannotations.annotations.EApplication;

import nu.mackli.sitc.api.parse.ParseConstants;
import nu.mackli.sitc.models.CarpoolSite;

/**
 * Created by macklinu on 1/25/14.
 */
@EApplication
public class SitcApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        registerSubclasses();
        initParse();
    }

    private void initParse() {
        Parse.initialize(this, ParseConstants.APPLICATION_ID, ParseConstants.CLIENT_ID);
        ParseFacebookUtils.initialize(ParseConstants.FACEBOOK_APP_ID);
    }

    private void registerSubclasses() {
        ParseObject.registerSubclass(CarpoolSite.class);
    }
}
