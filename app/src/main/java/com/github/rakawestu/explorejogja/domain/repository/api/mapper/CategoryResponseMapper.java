package com.github.rakawestu.explorejogja.domain.repository.api.mapper;



import com.github.rakawestu.explorejogja.domain.model.Category;

import java.util.List;

/**
 * @author rakawm
 */
public interface CategoryResponseMapper<T> {
    List<Category> mapResponse(T response);
}
