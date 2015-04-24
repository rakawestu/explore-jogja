package com.github.rakawestu.explorejogja.ui.viewmodel;

import com.github.rakawestu.explorejogja.domain.model.Place;

/**
 * Custom implementation of a model to use with ModelCollectionView
 *
 * @author rakawm
 */
public class PlaceViewModel extends PlaceModel {

    Place place;

    public PlaceViewModel(Place place){
        this.place = place;
    }

    @Override
    public String getImageUrl() {
        return place.getPhotos();
    }

    @Override
    public String getTitle() {
        return place.getJudul();
    }

    @Override
    public String getDescription() {
        return place.getDeskripsi();
    }
}
