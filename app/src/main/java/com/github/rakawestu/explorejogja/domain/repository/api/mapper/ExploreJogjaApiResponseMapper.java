package com.github.rakawestu.explorejogja.domain.repository.api.mapper;

import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.domain.repository.ResponseMapper;
import com.github.rakawestu.explorejogja.domain.repository.api.model.PlaceDataWrapper;

import java.util.Collections;
import java.util.List;

/**
 * @author rakawm
 */
public class ExploreJogjaApiResponseMapper implements ResponseMapper<PlaceDataWrapper> {

    @Override
    public List<Place> mapResponse(PlaceDataWrapper response) {
        return response.getTempat();
    }
}
