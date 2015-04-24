package com.github.rakawestu.explorejogja.domain.model;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rakawm
 */
@Parcel
public class SubCategoryList {
    public List<SubCategory> subCategories;

    public SubCategoryList(){
        subCategories = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<SubCategory> getSubCategories(){
        return (List<SubCategory>) ((ArrayList<SubCategory>) subCategories).clone();
    }

    public void add(SubCategory subCategory){
        subCategories.add(subCategory);
    }

    public void add(List<SubCategory> subCategories){
        this.subCategories.addAll(subCategories);
    }
}
