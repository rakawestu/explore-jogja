package com.github.rakawestu.explorejogja.ui.presenter;

import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.ui.view.DetailsDescriptionPlaceView;

/**
 * @author rakawm
 */
public interface DetailsDescriptionPlacePresenter extends Presenter<DetailsDescriptionPlaceView> {

    void onPlace(Place place);

}
