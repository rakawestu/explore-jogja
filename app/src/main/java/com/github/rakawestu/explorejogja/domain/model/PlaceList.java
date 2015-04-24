package com.github.rakawestu.explorejogja.domain.model;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rakawm
 */
@Parcel
public class PlaceList {
    public List<Place> placeList;

    public PlaceList(){
        placeList = new ArrayList<Place>();
    }

    @SuppressWarnings("unchecked")
    public List<Place> getPlaceList() {
        return (List<Place>) ((ArrayList<Place>) placeList).clone();
    }

    public void add(Place place) {
        this.placeList.add(place);
    }

    public void addAll(List<Place> placeList) {
        this.placeList.addAll(placeList);
    }
}
