package com.github.rakawestu.explorejogja.domain.repository;

import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.domain.repository.exception.GetPlaceException;

import java.util.List;

/**
 * Repository to get explore jogja information.
 * @author rakawm
 */
public interface ExploreJogjaRepository {
    List<Place> getPlaceCollection() throws GetPlaceException;
}
