package com.github.rakawestu.explorejogja.ui.view;

import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceDetailsViewModel;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceViewModel;

/**
 * @author rakawm
 */
public interface DetailsDescriptionPlaceView extends View {
    void showDetailDescription(PlaceDetailsViewModel place);
}
