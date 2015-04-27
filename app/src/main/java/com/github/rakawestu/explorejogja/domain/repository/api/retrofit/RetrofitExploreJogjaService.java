package com.github.rakawestu.explorejogja.domain.repository.api.retrofit;

import com.github.rakawestu.explorejogja.domain.repository.api.model.CategoryDataWrapper;
import com.github.rakawestu.explorejogja.domain.repository.api.model.PlaceDataWrapper;
import com.github.rakawestu.explorejogja.domain.repository.api.model.SubCategoryDataWrapper;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author rakawm
 */
public interface RetrofitExploreJogjaService {
    @GET("/api/explore-jogja/tempat")
    PlaceDataWrapper getPlaceList();

    @GET("/api/explore-jogja/tempat")
    PlaceDataWrapper getPlaceList(@Query("subtipe") int subtipe);

    @GET("/api/explore-jogja/tipe")
    CategoryDataWrapper getCategories();

    @GET("/api/explore-jogja/subtipe")
    SubCategoryDataWrapper getSubCategories(
            @Query("tipe") int tipe
    );

    @GET("/api/explore-jogja/subtipe")
    SubCategoryDataWrapper getSubCategories();
}
