package com.github.rakawestu.explorejogja.domain.interactor;

import com.github.rakawestu.explorejogja.domain.model.Place;

import java.util.List;

/**
 * Interface to define this interactor.
 * @author rakawm
 */
public interface GetPlaceList {
    void execute(final Callback callback);

    interface Callback {

        void onPlaceList(List<Place> placeList);

        void onError();
    }
}
