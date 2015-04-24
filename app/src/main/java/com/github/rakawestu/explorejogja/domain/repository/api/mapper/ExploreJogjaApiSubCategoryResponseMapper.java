package com.github.rakawestu.explorejogja.domain.repository.api.mapper;

import com.github.rakawestu.explorejogja.domain.model.SubCategory;
import com.github.rakawestu.explorejogja.domain.repository.api.model.SubCategoryDataWrapper;

import java.util.List;

/**
 * @author rakawm
 */
public class ExploreJogjaApiSubCategoryResponseMapper implements SubCategoryResponseMapper<SubCategoryDataWrapper>{
    @Override
    public List<SubCategory> mapResponse(SubCategoryDataWrapper response) {
        return response.getSubkategori();
    }
}
