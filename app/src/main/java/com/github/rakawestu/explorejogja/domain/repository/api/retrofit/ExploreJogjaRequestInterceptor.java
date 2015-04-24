package com.github.rakawestu.explorejogja.domain.repository.api.retrofit;

import retrofit.RequestInterceptor;

/**
 * Interceptor to add the auth key, and generate the hash for every request
 * The data is exposed in the constructor
 * @author rakawm
 */
public class ExploreJogjaRequestInterceptor implements RequestInterceptor {
    @Override
    public void intercept(RequestFacade request) {

    }
}
