package com.github.rakawestu.explorejogja.domain.repository.api.mapper;

import com.github.rakawestu.explorejogja.domain.model.Category;
import com.github.rakawestu.explorejogja.domain.repository.api.model.CategoryDataWrapper;

import java.util.List;

/**
 * @author rakawm
 */
public class ExploreJogjaApiCategoryResponseMapper implements CategoryResponseMapper<CategoryDataWrapper> {


    @Override
    public List<Category> mapResponse(CategoryDataWrapper response) {
        return response.getKategori();
    }
}
