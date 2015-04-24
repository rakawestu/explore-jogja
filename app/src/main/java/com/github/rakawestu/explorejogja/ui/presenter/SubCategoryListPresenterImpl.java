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

    public SubCategoryListPresenterImpl(Context context, GetSubCategoryList getSubCategoryList,
                                        SubCategorySelectedObservable subCategorySelectedObservable) {
        super(context);
        this.context = context;
        this.getSubCategoryList = getSubCategoryList;
        this.subCategorySelectedObservable = subCategorySelectedObservable;
    }

    @Override
    public void onSelectedCategory(String category) {
        searchForSubCategories(category);
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

    private void searchForSubCategories(final String tipe){
        int type = Integer.valueOf(tipe);
        subCategoryListView.showLoading();
        getSubCategoryList.execute(type, new GetSubCategoryList.Callback() {
            @Override
            public void onSubCategoryList(List<SubCategory> subCategories) {
                subCategoryListCollection.add(subCategories);
                subCategoryListView.add(convertToModelViewList(subCategories));
                subCategoryListView.hideLoading();
                subCategoryListView.activateLastSubCategoryViewListener();
            }

            @Override
            public void onError() {
                Timber.e("Error in interactor GetSubCategoryList");
                subCategoryListView.hideLoading();
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
