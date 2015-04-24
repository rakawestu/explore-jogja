package com.github.rakawestu.explorejogja.domain.interactor;


import com.github.rakawestu.explorejogja.domain.model.Category;
import com.github.rakawestu.explorejogja.domain.repository.api.retrofit.RetrofitExploreJogjaApiRepository;
import com.github.rakawestu.explorejogja.domain.repository.exception.GetPlaceException;
import com.github.rakawestu.explorejogja.executor.InteractorExecutor;
import com.github.rakawestu.explorejogja.executor.MainThreadExecutor;

import java.util.List;

import timber.log.Timber;

/**
 * @author rakawm
 */
public class GetCategoryListImpl extends AbstractInteractor implements GetCategoryList{
    private RetrofitExploreJogjaApiRepository repository;
    private Callback callback;

    public GetCategoryListImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor,
                           RetrofitExploreJogjaApiRepository exploreJogjaRepository){
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
            final List<Category> categories = repository.getCategoryCollection();
            getMainThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    callback.onCategoryList(categories);
                }
            });
        } catch (GetPlaceException e){
            Timber.e("Error on Get Category interactor");
            getMainThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    callback.onError();
                }
            });
        }
    }
}
