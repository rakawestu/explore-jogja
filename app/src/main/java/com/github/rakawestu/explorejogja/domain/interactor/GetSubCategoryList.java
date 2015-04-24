package com.github.rakawestu.explorejogja.domain.interactor;

import com.github.rakawestu.explorejogja.domain.model.SubCategory;

import java.util.List;

/**
 * @author rakawm
 */
public interface GetSubCategoryList {

    void execute(int tipe , final Callback callback);

    interface Callback {

        void onSubCategoryList(List<SubCategory> subCategories);

        void onError();

    }
}
