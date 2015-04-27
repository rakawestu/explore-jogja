package com.github.rakawestu.explorejogja.ui.presenter;

import android.content.Context;

import com.github.rakawestu.explorejogja.app.BasePresenter;
import com.github.rakawestu.explorejogja.domain.interactor.GetSubCategoryList;
import com.github.rakawestu.explorejogja.domain.model.Category;
import com.github.rakawestu.explorejogja.domain.model.SubCategory;
import com.github.rakawestu.explorejogja.domain.model.SubCategoryList;
import com.github.rakawestu.explorejogja.ui.reactive.SubCategorySelectedObservable;
import com.github.rakawestu.explorejogja.ui.reactive.SubCategorySelectedObserver;
import com.github.rakawestu.explorejogja.ui.view.SubCategoryListView;
import com.github.rakawestu.explorejogja.ui.viewmodel.CategoryViewModel;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceModel;
import com.github.rakawestu.explorejogja.ui.viewmodel.SubCategoryViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import timber.log.Timber;

/**
 * @author rakawm
 */
public class SubCategoryListPresenterImpl extends BasePresenter implements SubCategoryListPresenter{

    private SubCategoryListView subCategoryListView;
    private GetSubCategoryList getSubCategoryList;
    private SubCategoryList subCategoryListCollection;
    private SubCategorySelectedObservable subCategorySelectedObservable;
    private Context context;
    private String tipe;
    public SubCategoryListPresenterImpl(Context context, GetSubCategoryList getSubCategoryList,
                                        SubCategorySelectedObservable subCategorySelectedObservable) {
        super(context);
        this.context = context;
        this.getSubCategoryList = getSubCategoryList;
        this.subCategorySelectedObservable = subCategorySelectedObservable;
    }

    @Override
    public void onSelectedCategory(String category) {
        tipe = category;
        searchForSubCategories(category, true);
    }

    @Override
    public void onLastSubCategoryShown() {

    }

    @Override
    public SubCategoryList getParcelableCollection() {
        return subCategoryListCollection;
    }

    @Override
    public void restoreParcelableCollection(SubCategoryList subCategoryList) {
        this.subCategoryListCollection = subCategoryList;
        subCategoryListView.add(convertToModelViewList(subCategoryList.getSubCategories()));
    }

    @Override
    public void onSubCategorySelected(int position) {
        Collection<SubCategory> subCategoryCollections = subCategoryListCollection.getSubCategories();
        SubCategory selected = (SubCategory) subCategoryCollections.toArray()[position];
        subCategorySelectedObservable.notifyObservers(selected);
    }

    @Override
    public void onRefresh(boolean needProgress) {
        subCategoryListCollection = new SubCategoryList();
        subCategoryListView.refresh(true);
        searchForSubCategories(tipe, needProgress);
    }

    @Override
    public void initialize() {
        subCategoryListCollection = new SubCategoryList();
    }

    @Override
    public void onViewCreate() {
        subCategoryListView.activateLastSubCategoryViewListener();
    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void setView(SubCategoryListView view) {
        this.subCategoryListView = view;
    }

    private void searchForSubCategories(final String tipe, boolean needProgress){
        int type = Integer.valueOf(tipe);
        if(needProgress){
            subCategoryListView.showLoading();
        }
        getSubCategoryList.execute(type, new GetSubCategoryList.Callback() {
            @Override
            public void onSubCategoryList(List<SubCategory> subCategories) {
                subCategoryListCollection.add(subCategories);
                subCategoryListView.add(convertToModelViewList(subCategories));
                subCategoryListView.hideLoading();
                subCategoryListView.hideSwipeRefresh();
                subCategoryListView.activateLastSubCategoryViewListener();
            }

            @Override
            public void onError() {
                Timber.e("Error in interactor GetSubCategoryList");
                subCategoryListView.hideLoading();
                subCategoryListView.hideSwipeRefresh();
                subCategoryListView.onError();
                subCategoryListView.activateLastSubCategoryViewListener();
            }
        });
    }

    private List<PlaceModel> convertToModelViewList(List<SubCategory> subCategories){
        List<PlaceModel> modelList = new ArrayList<>();
        for(SubCategory subCategory: subCategories){
            modelList.add(new SubCategoryViewModel(subCategory));
        }
        return modelList;
    }
}
