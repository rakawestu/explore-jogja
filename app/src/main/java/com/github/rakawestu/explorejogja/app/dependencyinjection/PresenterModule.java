package com.github.rakawestu.explorejogja.app.dependencyinjection;

import android.content.Context;

import com.github.rakawestu.explorejogja.domain.interactor.GetCategoryList;
import com.github.rakawestu.explorejogja.domain.interactor.GetPlaceList;
import com.github.rakawestu.explorejogja.domain.interactor.GetSubCategoryList;
import com.github.rakawestu.explorejogja.domain.model.SubCategoryList;
import com.github.rakawestu.explorejogja.ui.presenter.CategoryListPresenter;
import com.github.rakawestu.explorejogja.ui.presenter.CategoryListPresenterImpl;
import com.github.rakawestu.explorejogja.ui.presenter.PlaceListPresenter;
import com.github.rakawestu.explorejogja.ui.presenter.PlaceListPresenterImpl;
import com.github.rakawestu.explorejogja.ui.presenter.SubCategoryListPresenter;
import com.github.rakawestu.explorejogja.ui.presenter.SubCategoryListPresenterImpl;
import com.github.rakawestu.explorejogja.ui.reactive.CategorySelectedObservable;
import com.github.rakawestu.explorejogja.ui.reactive.PlaceSelectedObservable;
import com.github.rakawestu.explorejogja.ui.reactive.SubCategorySelectedObservable;

import dagger.Module;
import dagger.Provides;

/**
 * @author rakawm
 */
@Module(
        complete = false,
        library = true
)
public class PresenterModule {
    @Provides
    public PlaceListPresenter providePlaceListPresenter(Context context, GetPlaceList getPlaceList, PlaceSelectedObservable placeSelectedObservable) {
        return new PlaceListPresenterImpl(context, getPlaceList, placeSelectedObservable);
    }

    @Provides
    public CategoryListPresenter provideCategoryListPresenter(Context context, GetCategoryList getCategoryList, CategorySelectedObservable categorySelectedObservable) {
        return new CategoryListPresenterImpl(context, getCategoryList, categorySelectedObservable);
    }

    @Provides
    public SubCategoryListPresenter provideSuBCategoryListPresenter(Context context, GetSubCategoryList getSubCategoryList, SubCategorySelectedObservable subCategorySelectedObservable) {
        return new SubCategoryListPresenterImpl(context, getSubCategoryList, subCategorySelectedObservable);
    }
}
