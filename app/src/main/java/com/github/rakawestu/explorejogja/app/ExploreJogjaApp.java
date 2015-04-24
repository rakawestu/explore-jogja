package com.github.rakawestu.explorejogja.app;

import android.app.Application;
import android.content.Context;

import com.github.rakawestu.explorejogja.app.dependencyinjection.RootModule;

import dagger.ObjectGraph;

/**
 * @author rakawm
 */
public class ExploreJogjaApp extends Application {
    private ObjectGraph objectGraph;

    /**
     * Static method for get application context
     *
     * @param context
     * @return
     */
    public static ExploreJogjaApp get(Context context) {
        return (ExploreJogjaApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new RootModule(this));
        objectGraph.inject(this);
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }

    /**
     * Add a new module to the dependency graph
     *
     * @param modules
     */
    public void addModules(Object... modules) {
        objectGraph.plus(modules);
    }
}
