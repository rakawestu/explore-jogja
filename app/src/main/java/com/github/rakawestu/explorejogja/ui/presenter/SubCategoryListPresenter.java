package com.github.rakawestu.explorejogja.ui.presenter;

import com.github.rakawestu.explorejogja.domain.model.SubCategoryList;
import com.github.rakawestu.explorejogja.ui.view.SubCategoryListView;

/**
 * @author rakawm
 */
public interface SubCategoryListPresenter extends Presenter<SubCategoryListView>{

    void onSelectedCategory(String category);

    void onLastSubCategoryShown();

    SubCategoryList getParcelableCollection();

    void restoreParcelableCollection(SubCategoryList subCategoryList);

    void onSubCategorySelected(int position);

    void onRefresh(boolean needProgress);

}
