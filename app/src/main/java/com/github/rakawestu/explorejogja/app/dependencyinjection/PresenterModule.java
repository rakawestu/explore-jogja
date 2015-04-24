package com.github.rakawestu.explorejogja.app.dependencyinjection;

import android.content.Context;

import com.github.rakawestu.explorejogja.domain.interactor.GetPlaceList;
import com.github.rakawestu.explorejogja.ui.presenter.PlaceListPresenter;
import com.github.rakawestu.explorejogja.ui.presenter.PlaceListPresenterImpl;
import com.github.rakawestu.explorejogja.ui.reactive.PlaceSelectedObservable;

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
    public PlaceListPresenter provideCharacterCollectionPresenter(Context context, GetPlaceList getPlaceList, PlaceSelectedObservable placeSelectedObservable) {
        return new PlaceListPresenterImpl(context, getPlaceList, placeSelectedObservable);
    }
}
