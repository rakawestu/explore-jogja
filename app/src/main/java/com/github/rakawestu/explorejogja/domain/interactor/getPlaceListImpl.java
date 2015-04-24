package com.github.rakawestu.explorejogja.domain.interactor;

import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.domain.repository.ExploreJogjaRepository;
import com.github.rakawestu.explorejogja.domain.repository.api.retrofit.RetrofitExploreJogjaApiRepository;
import com.github.rakawestu.explorejogja.domain.repository.exception.GetPlaceException;
import com.github.rakawestu.explorejogja.executor.InteractorExecutor;
import com.github.rakawestu.explorejogja.executor.MainThreadExecutor;

import java.util.List;

import timber.log.Timber;

/**
 * This implementation of the interactor (case use) will use a repository (injected) to get a collection
 * of marvel characters
 *
 * @author rakawm
 */
public class GetPlaceListImpl extends AbstractInteractor implements GetPlaceList{

    private RetrofitExploreJogjaApiRepository repository;
    private Callback callback;

    public GetPlaceListImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor,
                            RetrofitExploreJogjaApiRepository exploreJogjaRepository) {
        super(interactorExecutor, mainThreadExecutor);
        this.repository = exploreJogjaRepository;
    }

    @Override
    public void execute(Callback callback) {
        this.callback = callback;
        getInteractorExecutor().run(this);
    }

    @Override
    public void run() {
        try {
            final List<Place> placeList = repository.getPlaceCollection();

            getMainThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    callback.onPlaceList(placeList);
                }
            });

        } catch (GetPlaceException e) {
            Timber.e("Error on Get Place interactor");
            getMainThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    callback.onError();
                }
            });
        }
    }
}
