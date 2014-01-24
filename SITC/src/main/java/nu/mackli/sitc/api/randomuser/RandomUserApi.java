package nu.mackli.cards.api.randomuser;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.web.client.HttpClientErrorException;

import nu.mackli.cards.api.RestCallback;
import nu.mackli.cards.models.randomuser.RandomUser;

/**
 * Created by macklinu on 12/15/13.
 */
@EBean(scope = EBean.Scope.Singleton)
public class RandomUserApi {
    @RestService
    RandomUserRestClient client;

    @Background
    public void getRandomUser(RestCallback<RandomUser> callback) {
        try {
            RandomUser user = client.getRandomUser();
            callback.onSuccess(user);
        } catch (HttpClientErrorException e) {
            callback.onError(e);
        }
    }
}
