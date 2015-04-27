package com.github.rakawestu.explorejogja.domain.repository;

import com.github.rakawestu.explorejogja.domain.model.Category;
import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.domain.model.SubCategory;
import com.github.rakawestu.explorejogja.domain.repository.exception.GetPlaceException;

import java.util.List;

/**
 * Repository to get explore jogja information.
 * @author rakawm
 */
public interface ExploreJogjaRepository {
    List<Place> getPlaceCollection(int subtipe) throws GetPlaceException;

    List<Category> getCategoryCollection() throws GetPlaceException;

    List<SubCategory> getSubCategoryCollection(int tipe) throws GetPlaceException;
}
