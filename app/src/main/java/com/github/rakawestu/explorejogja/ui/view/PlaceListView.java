package com.github.rakawestu.explorejogja.ui.view;

/**
 * @author rakawm
 */
public interface PlaceListView extends ModelListView, View {

    int getModelsRenderer();

    void showLoading();

    void hideLoading();

    void activateLastPlaceViewListener();

    void disableLastPlaceViewListener();

    void onError();
}
