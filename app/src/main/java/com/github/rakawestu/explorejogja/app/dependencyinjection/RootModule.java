package com.github.rakawestu.explorejogja.app.dependencyinjection;

import android.content.Context;
import android.view.LayoutInflater;

import com.github.rakawestu.explorejogja.app.ExploreJogjaApp;
import com.github.rakawestu.explorejogja.ui.PlaceListFragment;
import com.github.rakawestu.explorejogja.ui.activity.MainActivity;
import com.github.rakawestu.explorejogja.ui.presenter.PlaceListPresenterImpl;

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
                PlaceListPresenterImpl.class,
                MainActivity.class,
                MainActivity.class
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
