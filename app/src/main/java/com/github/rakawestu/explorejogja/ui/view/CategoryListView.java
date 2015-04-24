package com.github.rakawestu.explorejogja.ui.view;

/**
 * @author rakawm
 */
public interface CategoryListView extends ModelListView, View {
    int getModelsRenderer();

    void showLoading();

    void hideLoading();

    void activateLastCategoryViewListener();

    void disableLastCategoryViewListener();

    void onError();
}
