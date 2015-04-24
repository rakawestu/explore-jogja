package com.github.rakawestu.explorejogja.ui.view;

/**
 * @author rakawm
 */
public interface SubCategoryListView extends ModelListView,View{
    int getModelsRenderer();

    void showLoading();

    void hideLoading();

    void activateLastSubCategoryViewListener();

    void disableLastSubCategoryViewListener();

    void onError();
}
