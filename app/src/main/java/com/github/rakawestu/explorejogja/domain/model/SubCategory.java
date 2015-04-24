package com.github.rakawestu.explorejogja.domain.model;

import org.parceler.Parcel;

/**
 * @author rakawm
 */
@Parcel
public class SubCategory {
    String id;
    String subtipe;
    String image;

    public SubCategory(){

    }

    public SubCategory(String id, String subtipe, String image){
        this.id = id;
        this.subtipe = subtipe;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubtipe() {
        return subtipe;
    }

    public void setSubtipe(String subtipe) {
        this.subtipe = subtipe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
