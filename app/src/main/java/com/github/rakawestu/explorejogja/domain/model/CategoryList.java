package com.github.rakawestu.explorejogja.domain.model;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rakawm
 */
@Parcel
public class CategoryList {
    public List<Category> categories;

    public CategoryList(){
        categories = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<Category> getCategories(){
        return (List<Category>) ((ArrayList<Category>) categories).clone();
    }

    public void add(Category category) {
        categories.add(category);
    }

    public void add(List<Category> categories) {
        this.categories.addAll(categories);
    }

}
