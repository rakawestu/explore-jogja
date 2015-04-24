package com.github.rakawestu.explorejogja.app.dependencyinjection;

import com.github.rakawestu.explorejogja.ui.reactive.PlaceSelectedObservable;

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
}
