package com.github.rakawestu.explorejogja.ui.presenter;

import com.github.rakawestu.explorejogja.domain.model.CategoryList;
import com.github.rakawestu.explorejogja.domain.model.PlaceList;
import com.github.rakawestu.explorejogja.ui.view.CategoryListView;

/**
 * @author rakawm
 */
public interface CategoryListPresenter extends Presenter<CategoryListView>{

    void onLastCategoryShowed();

    CategoryList getParcelableCollection();

    void restoreParcelableCollection(CategoryList categoryList);

    void onCategorySelected(int position);

    void onRefresh(boolean needProgress);
}
