package nu.mackli.cards.api.randomuser;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import nu.mackli.cards.models.randomuser.RandomUser;

/**
 * Created by macklinu on 12/15/13.
 */
@Rest(rootUrl = "http://api.randomuser.me/0.2", converters = {GsonHttpMessageConverter.class})
public interface RandomUserRestClient {
    @Get("/")
    RandomUser getRandomUser();
}
