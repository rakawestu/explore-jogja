package com.github.rakawestu.explorejogja.ui.presenter;

import android.content.Context;

import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.ui.view.DetailsDescriptionPlaceView;
import com.github.rakawestu.explorejogja.ui.view.DetailsInforPlaceView;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceDetailsViewModel;

/**
 * @author rakawm
 */
public class DetailsInfoPlacePresenterImpl implements DetailsInfoPlacePresenter{

    DetailsInforPlaceView placeView;

    @Override
    public void onPlace(Place place) {
        PlaceDetailsViewModel model = new PlaceDetailsViewModel(place);
        placeView.showDetailInfo(model);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onViewCreate() {

    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void setView(DetailsInforPlaceView view) {
        this.placeView = view;
    }
}
