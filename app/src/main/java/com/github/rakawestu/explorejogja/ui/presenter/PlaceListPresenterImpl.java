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

    public PlaceListPresenterImpl(Context context, GetPlaceList getPlaceList, PlaceSelectedObservable placeSelectedObservable) {
        super(context);
        this.context = context;
        this.getPlaceList = getPlaceList;
        this.placeSelectedObservable = placeSelectedObservable;
    }

    @Override
    public void initialize(){
        placeListCollection = new PlaceList();
        searchForPlace();
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

    }

    private void searchForPlace(){
        placeListView.showLoading();

        getPlaceList.execute(new GetPlaceList.Callback() {
            @Override
            public void onPlaceList(List<Place> placeList) {
                placeListCollection.addAll(placeList);
                placeListView.add(convertToModelViewList(placeList));
                placeListView.hideLoading();
                placeListView.activateLastPlaceViewListener();
            }

            @Override
            public void onError() {
                Timber.e("Error on interactor getPlaceList");
                placeListView.onError();
                placeListView.hideLoading();
                placeListView.onError();
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
