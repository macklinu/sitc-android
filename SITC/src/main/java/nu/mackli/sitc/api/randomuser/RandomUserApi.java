package nu.mackli.sitc.api.randomuser;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;

import nu.mackli.sitc.api.RestCallback;
import nu.mackli.sitc.models.randomuser.RandomUser;


/**
 * Created by macklinu on 12/15/13.
 */
@EBean(scope = EBean.Scope.Singleton)
public class RandomUserApi {
    @RestService
    RandomUserRestClient client;

    @Background
    public void getRandomUsers(int number, RestCallback<RandomUser> callback) {
        try {
            RandomUser user = client.getRandomUsers(number);
            callback.onSuccess(user);
        } catch (HttpClientErrorException e) {
            callback.onError(e);
        }
    }
}
