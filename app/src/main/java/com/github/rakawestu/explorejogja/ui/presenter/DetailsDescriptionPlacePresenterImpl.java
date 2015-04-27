package com.github.rakawestu.explorejogja.ui.presenter;

import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.ui.view.DetailsDescriptionPlaceView;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceDetailsViewModel;

/**
 * @author rakawm
 */
public class DetailsDescriptionPlacePresenterImpl implements DetailsDescriptionPlacePresenter{

    DetailsDescriptionPlaceView placeView;

    @Override
    public void onPlace(Place place) {
        PlaceDetailsViewModel model = new PlaceDetailsViewModel(place);
        placeView.showDetailDescription(model);
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
    public void setView(DetailsDescriptionPlaceView view) {
        this.placeView = view;
    }
}
