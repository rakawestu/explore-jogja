package com.github.rakawestu.explorejogja.domain.repository.api.model;

import com.github.rakawestu.explorejogja.domain.model.Place;

import java.util.List;

/**
 * @author rakawm
 */
public class PlaceDataWrapper {
    private boolean error;
    private List<Place> tempat;

    public List<Place> getTempat() {
        return tempat;
    }

    public void setTempat(List<Place> tempat) {
        this.tempat = tempat;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
