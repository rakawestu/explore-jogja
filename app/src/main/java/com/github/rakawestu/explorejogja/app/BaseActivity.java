package com.github.rakawestu.explorejogja.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import butterknife.ButterKnife;

/**
 * Base activity for doing the Dependency
 *
 * @author rakawm
 */
public abstract class BaseActivity extends ActionBarActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        injectViews();
    }

    private void injectDependencies() {
        ExploreJogjaApp exploreJogjaApp = (ExploreJogjaApp) getApplication();
        exploreJogjaApp.inject(this);
    }


    private void injectViews() {
        ButterKnife.inject(this);
    }
}
