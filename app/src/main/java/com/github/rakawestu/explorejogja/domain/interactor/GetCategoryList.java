package com.github.rakawestu.explorejogja.domain.interactor;

import com.github.rakawestu.explorejogja.domain.model.Category;

import java.util.List;

/**
 * @author rakawm
 */
public interface GetCategoryList {
    void execute(final Callback callback);

    interface Callback {

        void onCategoryList(List<Category> categories);

        void onError();
    }
}
