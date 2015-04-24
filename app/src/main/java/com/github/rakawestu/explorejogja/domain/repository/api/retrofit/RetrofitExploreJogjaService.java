package com.github.rakawestu.explorejogja.domain.repository.api.retrofit;

import com.github.rakawestu.explorejogja.domain.repository.api.model.PlaceDataWrapper;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author rakawm
 */
public interface RetrofitExploreJogjaService {
    @GET("/api/explore-jogja/tempat")
    PlaceDataWrapper getPlaceList();

    @GET("/api/explore-jogja/tempat")
    PlaceDataWrapper getPlaceListByCategory(@Query("subtipe") int subtipe);
}
