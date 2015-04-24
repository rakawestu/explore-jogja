package com.github.rakawestu.explorejogja.ui.viewmodel;

import com.github.rakawestu.explorejogja.domain.model.SubCategory;

/**
 * @author rakawm
 */
public class SubCategoryViewModel extends PlaceModel {
    SubCategory subCategory;

    public SubCategoryViewModel(SubCategory subCategory){
        this.subCategory = subCategory;
    }

    @Override
    public String getImageUrl() {
        return subCategory.getImage();
    }

    @Override
    public String getTitle() {
        return subCategory.getSubtipe();
    }

    @Override
    public String getDescription() {
        return "";
    }
}
