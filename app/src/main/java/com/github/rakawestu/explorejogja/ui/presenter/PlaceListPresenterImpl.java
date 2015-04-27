package com.github.rakawestu.explorejogja.ui.presenter;

import android.content.Context;

import com.github.rakawestu.explorejogja.app.BasePresenter;
import com.github.rakawestu.explorejogja.domain.interactor.GetPlaceList;
import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.domain.model.PlaceList;
import com.github.rakawestu.explorejogja.ui.reactive.PlaceSelectedObservable;
import com.github.rakawestu.explorejogja.ui.view.PlaceListView;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceModel;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import timber.log.Timber;

/**
 * @author rakawm
 */
public class PlaceListPresenterImpl extends BasePresenter implements PlaceListPresenter{

    private PlaceListView placeListView;
    private GetPlaceList getPlaceList;
    private PlaceList placeListCollection;
    private PlaceSelectedObservable placeSelectedObservable;
    private Context context;
    private String subtipe;

    public PlaceListPresenterImpl(Context context, GetPlaceList getPlaceList, PlaceSelectedObservable placeSelectedObservable) {
        super(context);
        this.context = context;
        this.getPlaceList = getPlaceList;
        this.placeSelectedObservable = placeSelectedObservable;
    }

    @Override
    public void initialize(){
        placeListCollection = new PlaceList();
        //
    }

    @Override
    public void onViewCreate() {
        placeListView.activateLastPlaceViewListener();
    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void setView(PlaceListView view) {
        this.placeListView = view;
    }

    @Override
    public void onSelectedSubCategory(String category) {
        this.subtipe = category;
        searchForPlace(category, true);
    }

    @Override
    public void onLastPlaceShowed() {
        //searchForPlace();
    }

    @Override
    public PlaceList getParcelableCollection() {
        return placeListCollection;
    }

    @Override
    public void restoreParcelableCollection(PlaceList placeList) {
        this.placeListCollection = placeList;
        placeListView.add(convertToModelViewList(placeList.getPlaceList()));
    }

    @Override
    public void onPlaceSelected(int position) {
        Collection<Place> places = placeListCollection.getPlaceList();
        Place place = (Place)places.toArray()[position];
        placeSelectedObservable.notifyObservers(place);
    }

    @Override
    public void onRefresh(boolean needRefresh) {
        placeListCollection = new PlaceList();
        placeListView.refresh(true);
        searchForPlace(subtipe, needRefresh);
    }

    private void searchForPlace(String subtipe, boolean needProgress){
        if(needProgress){
            placeListView.showLoading();
        }
        int subcategory = Integer.valueOf(subtipe);
        getPlaceList.execute(subcategory, new GetPlaceList.Callback() {
            @Override
            public void onPlaceList(List<Place> placeList) {
                placeListCollection.addAll(placeList);
                placeListView.add(convertToModelViewList(placeList));
                placeListView.hideLoading();
                placeListView.hideSwipeRefresh();
                placeListView.activateLastPlaceViewListener();
            }

            @Override
            public void onError() {
                Timber.e("Error on interactor getPlaceList");
                placeListView.onError();
                placeListView.hideLoading();
                placeListView.hideSwipeRefresh();
                placeListView.activateLastPlaceViewListener();
            }
        });
    }

    private List<PlaceModel> convertToModelViewList(List<Place> places){
        List<PlaceModel> modelList = new ArrayList<>();
        for(Place place : places){
            modelList.add(new PlaceViewModel(place));
        }
        return modelList;
    }
}
