package com.github.rakawestu.explorejogja.domain.repository.api.mapper;

import com.github.rakawestu.explorejogja.domain.model.SubCategory;

import java.util.List;

/**
 * @author rakawm
 */
public interface SubCategoryResponseMapper<T> {
    List<SubCategory> mapResponse(T response);
}
