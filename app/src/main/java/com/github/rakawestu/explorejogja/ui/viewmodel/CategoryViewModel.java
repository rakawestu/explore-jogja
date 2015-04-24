package com.github.rakawestu.explorejogja.ui.viewmodel;

import com.github.rakawestu.explorejogja.domain.model.Category;

/**
 * @author rakawm
 */
public class CategoryViewModel extends PlaceModel{
    Category category;

    public CategoryViewModel(Category category){
        this.category = category;
    }


    @Override
    public String getImageUrl() {
        return category.getImage();
    }

    @Override
    public String getTitle() {
        return category.getTipe();
    }

    @Override
    public String getDescription() {
        return "";
    }
}
