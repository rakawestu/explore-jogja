package com.github.rakawestu.explorejogja.domain.repository.api.model;

import com.github.rakawestu.explorejogja.domain.model.SubCategory;

import java.util.List;

/**
 * @author rakawm
 */
public class SubCategoryDataWrapper {
    private boolean error;
    private List<SubCategory> subkategori;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<SubCategory> getSubkategori() {
        return subkategori;
    }

    public void setSubkategori(List<SubCategory> subkategori) {
        this.subkategori = subkategori;
    }
}
