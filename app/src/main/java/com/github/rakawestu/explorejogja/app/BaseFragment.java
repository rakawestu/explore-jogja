package com.github.rakawestu.explorejogja.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.ButterKnife;

/**
 * @author rakawm
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
    }

    private void injectDependencies() {
        ExploreJogjaApp exploreJogjaApp = (ExploreJogjaApp) getActivity().getApplication();
        exploreJogjaApp.inject(this);
    }

    private void injectViews(View view) {
        ButterKnife.inject(this, view);
    }
}
