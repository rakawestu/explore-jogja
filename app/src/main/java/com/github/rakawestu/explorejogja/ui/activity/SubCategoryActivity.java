package com.github.rakawestu.explorejogja.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.github.rakawestu.explorejogja.R;
import com.github.rakawestu.explorejogja.app.BaseActivity;
import com.github.rakawestu.explorejogja.domain.model.SubCategory;
import com.github.rakawestu.explorejogja.ui.fragment.SubCategoryListFragment;
import com.github.rakawestu.explorejogja.ui.reactive.SubCategorySelectedObservable;
import com.github.rakawestu.explorejogja.ui.reactive.SubCategorySelectedObserver;

import javax.inject.Inject;

/**
 * @author rakawm
 */
public class SubCategoryActivity extends BaseActivity implements SubCategorySelectedObserver{
    public static final String KEY_CATEGORY = "category";

    @Inject
    SubCategorySelectedObservable observable;

    @Override
    protected void onResume() {
        super.onResume();
        observable.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        observable.unregister(this);
    }

    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        addSubCategoryFragment();
    }

    public void addSubCategoryFragment(){
        String categoryId = getIntent().getStringExtra(KEY_CATEGORY);
        //TODO: Go to fragment
        SubCategoryListFragment fragment = SubCategoryListFragment.newInstance(categoryId);
        getFragmentManager()
                .beginTransaction()
                .add(R.id.frame_sub_category, fragment)
                .addToBackStack("")
                .commit();
    }

    @Override
    public void onSubCategorySelected(SubCategory subCategory) {
        launchPlaceActivity(subCategory);
    }

    public void launchPlaceActivity(SubCategory subCategory){
        Intent intent = new Intent(this, PlaceActivity.class);
        intent.putExtra(PlaceActivity.KEY_SUBCATEGORY, subCategory.getId());
        startActivity(intent);
    }
}
