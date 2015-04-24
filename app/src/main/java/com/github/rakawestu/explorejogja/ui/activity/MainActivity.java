package com.github.rakawestu.explorejogja.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;

import com.github.rakawestu.explorejogja.R;
import com.github.rakawestu.explorejogja.app.BaseActivity;
import com.github.rakawestu.explorejogja.domain.model.Category;
import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.domain.model.SubCategory;
import com.github.rakawestu.explorejogja.ui.reactive.CategorySelectedObservable;
import com.github.rakawestu.explorejogja.ui.reactive.CategorySelectedObserver;
import com.github.rakawestu.explorejogja.ui.reactive.PlaceSelectedObservable;
import com.github.rakawestu.explorejogja.ui.reactive.PlaceSelectedObserver;

import javax.inject.Inject;

/**
 * Main activity of the Application, it decide if launch a new activity or update the fragment
 * depending of the view inflated
 * <p/>
 * This activity is the container of one fragment if is in portrait and 2 if is in landscape
 * The type of the layout inflated will be checked and use for navigation
 *
 * @author rakawm
 */
public class MainActivity extends BaseActivity implements CategorySelectedObserver{
    public static final String TAG_PORTRAIT = "V11-portrait";

    @Inject
    CategorySelectedObservable categorySelectedObservable;

    //The viewTag is the key for the navigation with different sizes
    private String viewTag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        viewTag = (String) view.getTag();
        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        categorySelectedObservable.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        categorySelectedObservable.unregister(this);
    }

    //TODO Change to navigator class with activity context
    private void launchCharacterInfoActivity(Category category) {
        Intent intent = new Intent(this, SubCategoryActivity.class);
        intent.putExtra(SubCategoryActivity.KEY_CATEGORY, category.getId());
        startActivity(intent);
    }


    @Override
    public void categorySelected(Category category) {
        launchCharacterInfoActivity(category);
    }
}
