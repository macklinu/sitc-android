package nu.mackli.sitc;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by macklinu on 2/16/14.
 */
public class SitcTestRunner extends RobolectricTestRunner {
    public SitcTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }
}
