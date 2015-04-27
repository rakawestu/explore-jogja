package com.github.rakawestu.explorejogja.app.dependencyinjection;

import android.content.Context;
import android.view.LayoutInflater;

import com.github.rakawestu.explorejogja.app.ExploreJogjaApp;
import com.github.rakawestu.explorejogja.ui.activity.DetailsActivity;
import com.github.rakawestu.explorejogja.ui.activity.PlaceActivity;
import com.github.rakawestu.explorejogja.ui.activity.SubCategoryActivity;
import com.github.rakawestu.explorejogja.ui.fragment.CategoryListFragment;
import com.github.rakawestu.explorejogja.ui.fragment.DetailsDescriptionFragment;
import com.github.rakawestu.explorejogja.ui.fragment.DetailsInfoFragment;
import com.github.rakawestu.explorejogja.ui.fragment.PlaceListFragment;
import com.github.rakawestu.explorejogja.ui.activity.MainActivity;
import com.github.rakawestu.explorejogja.ui.fragment.SubCategoryListFragment;
import com.github.rakawestu.explorejogja.ui.presenter.CategoryListPresenterImpl;
import com.github.rakawestu.explorejogja.ui.presenter.DetailsDescriptionPlacePresenterImpl;
import com.github.rakawestu.explorejogja.ui.presenter.DetailsInfoPlacePresenterImpl;
import com.github.rakawestu.explorejogja.ui.presenter.PlaceListPresenterImpl;
import com.github.rakawestu.explorejogja.ui.presenter.SubCategoryListPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author rakawm
 */
@Module(
        includes = {
                ExecutorModule.class,
                InteractorModule.class,
                RepositoryModule.class,
                ReactiveModule.class,
                PresenterModule.class
        },
        injects = {
                ExploreJogjaApp.class,
                PlaceListFragment.class,
                CategoryListFragment.class,
                SubCategoryListFragment.class,
                SubCategoryListPresenterImpl.class,
                CategoryListPresenterImpl.class,
                PlaceListPresenterImpl.class,
                DetailsDescriptionFragment.class,
                DetailsInfoFragment.class,
                DetailsDescriptionPlacePresenterImpl.class,
                DetailsInfoPlacePresenterImpl.class,
                MainActivity.class,
                SubCategoryActivity.class,
                PlaceActivity.class,
                DetailsActivity.class
        },
        library = true
)
public class RootModule {

    private final Context context;

    public RootModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return context;
    }

    @Provides
    public LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(context);
    }
}
