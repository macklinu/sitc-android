package nu.mackli.sitc.api.randomuser;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import nu.mackli.sitc.models.randomuser.RandomUser;


/**
 * Created by macklinu on 12/15/13.
 */
@Rest(rootUrl = "http://api.randomuser.me", converters = {GsonHttpMessageConverter.class})
public interface RandomUserRestClient {
    @Get("/?results={number}")
    RandomUser getRandomUsers(int number);

    @Get("/?seed={seed}")
    RandomUser getSeed(String seed);
}
