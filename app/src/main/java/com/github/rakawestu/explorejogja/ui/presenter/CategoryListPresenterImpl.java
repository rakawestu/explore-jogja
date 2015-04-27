package com.github.rakawestu.explorejogja.ui.presenter;

import android.content.Context;

import com.github.rakawestu.explorejogja.app.BasePresenter;
import com.github.rakawestu.explorejogja.domain.interactor.GetCategoryList;
import com.github.rakawestu.explorejogja.domain.model.Category;
import com.github.rakawestu.explorejogja.domain.model.CategoryList;
import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.domain.model.PlaceList;
import com.github.rakawestu.explorejogja.ui.reactive.CategorySelectedObservable;
import com.github.rakawestu.explorejogja.ui.view.CategoryListView;
import com.github.rakawestu.explorejogja.ui.viewmodel.CategoryViewModel;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceModel;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import timber.log.Timber;

/**
 * @author rakawm
 */
public class CategoryListPresenterImpl extends BasePresenter implements CategoryListPresenter{
    private CategoryListView categoryListView;
    private GetCategoryList getCategoryList;
    private CategoryList categoryListCollection;
    private CategorySelectedObservable categorySelectedObservable;
    private Context context;

    public CategoryListPresenterImpl(Context context, GetCategoryList getCategoryList,
                                     CategorySelectedObservable categorySelectedObservable) {
        super(context);
        this.context = context;
        this.getCategoryList = getCategoryList;
        this.categorySelectedObservable = categorySelectedObservable;
    }

    @Override
    public void initialize(){
        categoryListCollection = new CategoryList();
        searchForCategories(true);
    }

    @Override
    public void onViewCreate() {
        categoryListView.activateLastCategoryViewListener();
    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void setView(CategoryListView view) {
        this.categoryListView = view;
    }

    @Override
    public void onLastCategoryShowed() {

    }

    @Override
    public CategoryList getParcelableCollection() {
        return categoryListCollection;
    }

    @Override
    public void restoreParcelableCollection(CategoryList categoryList) {
        this.categoryListCollection = categoryList;
        categoryListView.add(convertToModelViewList(categoryList.getCategories()));
    }

    @Override
    public void onCategorySelected(int position) {
        Collection<Category> categories = categoryListCollection.getCategories();
        Category category = (Category) categories.toArray()[position];
        categorySelectedObservable.notifyObservers(category);
    }

    @Override
    public void onRefresh(boolean needProgress) {
        categoryListCollection = new CategoryList();
        categoryListView.refresh(true);
        searchForCategories(needProgress);
    }

    private void searchForCategories(boolean needProgress){
        if(needProgress){
            categoryListView.showLoading();
        }
        getCategoryList.execute(new GetCategoryList.Callback() {
            @Override
            public void onCategoryList(List<Category> categories) {
                categoryListCollection.add(categories);
                categoryListView.add(convertToModelViewList(categories));
                categoryListView.hideLoading();
                categoryListView.hideSwipeRefresh();
                categoryListView.activateLastCategoryViewListener();
            }

            @Override
            public void onError() {
                Timber.e("Error in interactor GetCategoryList");
                categoryListView.hideLoading();
                categoryListView.hideSwipeRefresh();
                categoryListView.onError();
                categoryListView.activateLastCategoryViewListener();
            }
        });
    }

    private List<PlaceModel> convertToModelViewList(List<Category> categories){
        List<PlaceModel> modelList = new ArrayList<>();
        for(Category category: categories){
            modelList.add(new CategoryViewModel(category));
        }
        return modelList;
    }
}
