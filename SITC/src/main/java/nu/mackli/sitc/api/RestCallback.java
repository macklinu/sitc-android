package nu.mackli.sitc.api;

import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by macklinu on 12/15/13.
 */
public interface RestCallback<T> {

    public void onSuccess(T response);

    public void onError(HttpClientErrorException error);
}
