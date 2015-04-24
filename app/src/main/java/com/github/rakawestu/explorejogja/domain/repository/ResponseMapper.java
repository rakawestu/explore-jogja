package com.github.rakawestu.explorejogja.domain.repository;

import com.github.rakawestu.explorejogja.domain.model.Place;

import java.util.List;

/**
 * The domain model is different from others resources models, in each case the repository must map
 * the response to a basic model.
 *
 * @author rakawm
 */
public interface ResponseMapper<T> {
    List<Place> mapResponse(T response);
}
