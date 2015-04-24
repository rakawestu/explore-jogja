package com.github.rakawestu.explorejogja.app.dependencyinjection;

import com.github.rakawestu.explorejogja.domain.interactor.GetPlaceList;
import com.github.rakawestu.explorejogja.domain.interactor.GetPlaceListImpl;
import com.github.rakawestu.explorejogja.domain.repository.ExploreJogjaRepository;
import com.github.rakawestu.explorejogja.domain.repository.api.retrofit.RetrofitExploreJogjaApiRepository;
import com.github.rakawestu.explorejogja.executor.InteractorExecutor;
import com.github.rakawestu.explorejogja.executor.MainThreadExecutor;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author rakawm
 */
@Module(
        complete = false,
        library = true
)
public class InteractorModule {
    @Provides
    public GetPlaceList provideGetMarvelCharactersLimit(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, @Named("production_api") RetrofitExploreJogjaApiRepository exploreJogjaRepository) {
        return new GetPlaceListImpl(interactorExecutor, mainThreadExecutor, exploreJogjaRepository);
    }
}
