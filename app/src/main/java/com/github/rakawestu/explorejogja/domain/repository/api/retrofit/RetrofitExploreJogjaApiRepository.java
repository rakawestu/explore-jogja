package com.github.rakawestu.explorejogja.domain.repository.api.retrofit;

import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.domain.repository.ExploreJogjaRepository;
import com.github.rakawestu.explorejogja.domain.repository.ResponseMapper;
import com.github.rakawestu.explorejogja.domain.repository.api.model.PlaceDataWrapper;
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
    private ResponseMapper responseMapper;

    public RetrofitExploreJogjaApiRepository(String endpoint, ResponseMapper responseMapper) {
        this.endpoint = endpoint;
        this.responseMapper = responseMapper;

        init();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Place> getPlaceCollection() throws GetPlaceException {
        try {
            PlaceDataWrapper placeDataWrapper = exploreJogjaApi.getPlaceList();
            //Map response from api to domain model
            return responseMapper.mapResponse(placeDataWrapper);
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
