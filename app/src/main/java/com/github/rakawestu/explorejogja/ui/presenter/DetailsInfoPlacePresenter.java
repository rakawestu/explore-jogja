package com.github.rakawestu.explorejogja.ui.presenter;

import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.ui.view.DetailsInforPlaceView;

/**
 * @author rakawm
 */
public interface DetailsInfoPlacePresenter extends Presenter<DetailsInforPlaceView>{

    void onPlace(Place place);

}
