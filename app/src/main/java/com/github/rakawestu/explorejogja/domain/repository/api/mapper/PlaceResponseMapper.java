package com.github.rakawestu.explorejogja.domain.repository.api.mapper;

import com.github.rakawestu.explorejogja.domain.model.Category;
import com.github.rakawestu.explorejogja.domain.model.Place;

import java.util.List;

/**
 * The domain model is different from others resources models, in each case the repository must map
 * the response to a basic model.
 *
 * @author rakawm
 */
public interface PlaceResponseMapper<T> {
    List<Place> mapPlaceResponse(T response);
}
