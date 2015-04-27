package com.github.rakawestu.explorejogja.ui.presenter;

import com.github.rakawestu.explorejogja.domain.model.PlaceList;
import com.github.rakawestu.explorejogja.ui.view.PlaceListView;

/**
 * @author rakawm
 */
public interface PlaceListPresenter extends Presenter<PlaceListView>{

    void onSelectedSubCategory(String category);

    void onLastPlaceShowed();

    PlaceList getParcelableCollection();

    void restoreParcelableCollection(PlaceList placeList);

    void onPlaceSelected(int position);

    void onRefresh(boolean needRefresh);
}
