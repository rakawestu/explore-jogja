package com.github.rakawestu.explorejogja.domain.repository.api.model;

import java.util.List;
import com.github.rakawestu.explorejogja.domain.model.Category;
/**
 * @author rakawm
 */
public class CategoryDataWrapper {
    private boolean error;
    private List<Category> kategori;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Category> getKategori() {
        return kategori;
    }

    public void setKategori(List<Category> kategori) {
        this.kategori = kategori;
    }
}
