package com.github.rakawestu.explorejogja.domain.repository.api.mapper;

import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.domain.repository.api.model.PlaceDataWrapper;

import java.util.List;

/**
 * @author rakawm
 */
public class ExploreJogjaApiPlaceResponseMapper implements PlaceResponseMapper<PlaceDataWrapper> {

    @Override
    public List<Place> mapPlaceResponse(PlaceDataWrapper response) {
        return response.getTempat();
    }
}
