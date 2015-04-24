package com.github.rakawestu.explorejogja.domain.repository.api.model;

/**
 * @author rakawm
 */
public class Category {
    private String id;
    private String tipe;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
