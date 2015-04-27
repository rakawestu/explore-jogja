package com.github.rakawestu.explorejogja.ui.view;

import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceModel;

import java.util.List;

/**
 * Model collection view is designed to be used in different scenarios, in this concrete case
 * the collection is of place or another things, but the view can be the same.
 * @author rakawm
 */
public interface ModelListView {
    void add(PlaceModel model);

    void add(List<PlaceModel> models);

    void remove(PlaceModel model);

    void refresh(boolean needProgress);

    void hideSwipeRefresh();
}
