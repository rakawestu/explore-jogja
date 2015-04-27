package com.github.rakawestu.explorejogja.ui.view;

import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceDetailsViewModel;

/**
 * @author rakawm
 */
public interface DetailsInforPlaceView extends View {
    void showDetailInfo(PlaceDetailsViewModel place);
}
