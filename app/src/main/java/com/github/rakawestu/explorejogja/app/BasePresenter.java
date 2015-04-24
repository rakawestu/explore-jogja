package com.github.rakawestu.explorejogja.app;

import android.content.Context;

/**
 * @author rakawm
 */
public class BasePresenter {

    public BasePresenter(Context context) {
        ((ExploreJogjaApp) context.getApplicationContext()).inject(this);

    }
}
