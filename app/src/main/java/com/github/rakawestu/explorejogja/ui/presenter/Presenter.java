package com.github.rakawestu.explorejogja.ui.presenter;

import com.github.rakawestu.explorejogja.ui.view.View;

/**
 * Presenter with lifecycle
 *
 * @author rajawn
 */
public interface Presenter<T extends View> {

    void initialize();

    void onViewCreate();

    void onViewResume();

    void onViewDestroy();

    void setView(T view);
}

