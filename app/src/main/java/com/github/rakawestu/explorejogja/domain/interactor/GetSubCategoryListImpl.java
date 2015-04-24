package com.github.rakawestu.explorejogja.domain.interactor;

import com.github.rakawestu.explorejogja.domain.model.SubCategory;
import com.github.rakawestu.explorejogja.domain.repository.api.retrofit.RetrofitExploreJogjaApiRepository;
import com.github.rakawestu.explorejogja.domain.repository.exception.GetPlaceException;
import com.github.rakawestu.explorejogja.executor.InteractorExecutor;
import com.github.rakawestu.explorejogja.executor.MainThreadExecutor;

import java.util.List;

import timber.log.Timber;

/**
 * @author rakawm
 */
public class GetSubCategoryListImpl extends AbstractInteractor implements GetSubCategoryList{

    private RetrofitExploreJogjaApiRepository repository;
    private Callback callback;
    private int tipe;

    public GetSubCategoryListImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor,
                                  RetrofitExploreJogjaApiRepository exploreJogjaRepository) {
        super(interactorExecutor, mainThreadExecutor);
        this.repository = exploreJogjaRepository;
    }

    @Override
    public void execute(int tipe, final Callback callback) {
        this.callback = callback;
        this.tipe = tipe;
        getInteractorExecutor().run(this);
    }

    @Override
    public void run() {
        try {
            final List<SubCategory> subCategories = repository.getSubCategoryCollection(tipe);
            getMainThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    callback.onSubCategoryList(subCategories);
                }
            });
        } catch (GetPlaceException e){
            Timber.e("Error on Get Sub Category interactor");
            getMainThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    callback.onError();
                }
            });
        }
    }
}
