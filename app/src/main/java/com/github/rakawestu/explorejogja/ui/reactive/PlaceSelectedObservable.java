package com.github.rakawestu.explorejogja.ui.reactive;

import com.github.rakawestu.explorejogja.domain.model.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable to place selected, this observable call all observer subscribed when a
 * place is selected
 */
public class PlaceSelectedObservable implements Observable<PlaceSelectedObserver>{

    List<PlaceSelectedObserver> placeSelectedObservers;

    public PlaceSelectedObservable(){
        placeSelectedObservers = new ArrayList<>();
    }

    @Override
    public void register(PlaceSelectedObserver observer) {
        //To avoid duplicated register
        if (!placeSelectedObservers.contains(observer)){
            placeSelectedObservers.add(observer);
        }
    }

    @Override
    public void unregister(PlaceSelectedObserver observer) {
        placeSelectedObservers.remove(observer);
    }

    public void notifyObservers(Place place) {
        for (PlaceSelectedObserver observer : placeSelectedObservers) {
            observer.placeSelected(place);
        }
    }
}
