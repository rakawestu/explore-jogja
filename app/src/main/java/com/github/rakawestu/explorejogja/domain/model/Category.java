package com.github.rakawestu.explorejogja.domain.model;

import org.parceler.Parcel;

/**
 * @author rakawm
 */
@Parcel
public class Category {
    String id;
    String tipe;
    String image;

    public Category(){

    }

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

    public Category(String id, String tipe, String image){
        this.id = id;
        this.tipe = tipe;
        this.image = image;
    }
}
