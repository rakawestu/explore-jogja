package com.github.rakawestu.explorejogja.ui.viewmodel;

import com.github.rakawestu.explorejogja.domain.model.Place;

/**
 * @author rakawm
 */
public class PlaceDetailsViewModel extends PlaceDetails{

    Place place;

    public PlaceDetailsViewModel(Place place){
        this.place = place;
    }

    @Override
    public String getTitle() {
        return place.getJudul();
    }

    @Override
    public String getDescription() {
        return place.getDeskripsi();
    }

    @Override
    public String getPrice() {
        return place.getPrice_range();
    }

    @Override
    public String getOpeningHours() {
        return place.getOpening_hours();
    }

    @Override
    public String getCp() {
        return place.getCp_web();
    }

    @Override
    public String getFacility() {
        return place.getFacility();
    }

    @Override
    public String getImage() {
        return place.getPhotos();
    }
}
