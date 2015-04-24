package com.github.rakawestu.explorejogja.domain.repository.api.retrofit;

import com.github.rakawestu.explorejogja.domain.model.Category;
import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.domain.model.SubCategory;
import com.github.rakawestu.explorejogja.domain.repository.api.mapper.CategoryResponseMapper;
import com.github.rakawestu.explorejogja.domain.repository.ExploreJogjaRepository;
import com.github.rakawestu.explorejogja.domain.repository.api.mapper.PlaceResponseMapper;
import com.github.rakawestu.explorejogja.domain.repository.api.mapper.SubCategoryResponseMapper;
import com.github.rakawestu.explorejogja.domain.repository.api.model.CategoryDataWrapper;
import com.github.rakawestu.explorejogja.domain.repository.api.model.PlaceDataWrapper;
import com.github.rakawestu.explorejogja.domain.repository.api.model.SubCategoryDataWrapper;
import com.github.rakawestu.explorejogja.domain.repository.exception.GetPlaceException;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import timber.log.Timber;

/**
 * Implementation of MarvelRepository using Retrofit as client, every call is synchronous
 * because the asynchronous is done by the concrete interactor (use case) for this repository
 *
 * @author rakawm
 */
public class RetrofitExploreJogjaApiRepository implements ExploreJogjaRepository{

    private String endpoint;
    private RetrofitExploreJogjaService exploreJogjaApi;
    private PlaceResponseMapper responseMapper;
    private CategoryResponseMapper categoryResponseMapper;
    private SubCategoryResponseMapper subCategoryResponseMapper;

    public RetrofitExploreJogjaApiRepository(String endpoint, PlaceResponseMapper responseMapper,
                                             CategoryResponseMapper categoryResponseMapper, SubCategoryResponseMapper subCategoryResponseMapper) {
        this.endpoint = endpoint;
        this.responseMapper = responseMapper;
        this.categoryResponseMapper = categoryResponseMapper;
        this.subCategoryResponseMapper = subCategoryResponseMapper;
        init();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Place> getPlaceCollection() throws GetPlaceException {
        try {
            PlaceDataWrapper placeDataWrapper = exploreJogjaApi.getPlaceList();
            //Map response from api to domain model
            return responseMapper.mapPlaceResponse(placeDataWrapper);
        } catch (RetrofitError retrofitError) {
            Timber.e("Error on explore jogja repository");
            GetPlaceException getPlaceException = new GetPlaceException();
            getPlaceException.setStackTrace(retrofitError.getStackTrace());
            throw getPlaceException;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Category> getCategoryCollection() throws GetPlaceException {
        try {
            CategoryDataWrapper categoryDataWrapper = exploreJogjaApi.getCategories();
            return categoryResponseMapper.mapResponse(categoryDataWrapper);
        } catch (RetrofitError retrofitError) {
            Timber.e("Error on explore jogja repository");
            GetPlaceException getPlaceException = new GetPlaceException();
            getPlaceException.setStackTrace(retrofitError.getStackTrace());
            throw getPlaceException;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SubCategory> getSubCategoryCollection(int tipe) throws  GetPlaceException {
        try {
            SubCategoryDataWrapper subCategoryDataWrapper = exploreJogjaApi.getSubCategories(tipe);
            return subCategoryResponseMapper.mapResponse(subCategoryDataWrapper);
        } catch (RetrofitError retrofitError) {
            Timber.e("Error on explore jogja repository");
            GetPlaceException getPlaceException = new GetPlaceException();
            getPlaceException.setStackTrace(retrofitError.getStackTrace());
            throw getPlaceException;
        }
    }

    private void init() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .build();

        exploreJogjaApi = restAdapter.create(RetrofitExploreJogjaService.class);
    }


}
