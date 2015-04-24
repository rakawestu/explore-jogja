package com.github.rakawestu.explorejogja.app.dependencyinjection;

import com.github.rakawestu.explorejogja.ui.reactive.CategorySelectedObservable;
import com.github.rakawestu.explorejogja.ui.reactive.PlaceSelectedObservable;
import com.github.rakawestu.explorejogja.ui.reactive.SubCategorySelectedObservable;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author rakawm
 */
@Module(
        complete = false,
        library = true
)
public class ReactiveModule {
    @Singleton
    @Provides
    PlaceSelectedObservable proviPlaceSelectedObservable() {
        return new PlaceSelectedObservable();
    }

    @Singleton
    @Provides
    CategorySelectedObservable proviCategorySelectedObservable() {
        return new CategorySelectedObservable();
    }

    @Singleton
    @Provides
    SubCategorySelectedObservable proviSubCategorySelectedObservable() {
        return new SubCategorySelectedObservable();
    }
}
