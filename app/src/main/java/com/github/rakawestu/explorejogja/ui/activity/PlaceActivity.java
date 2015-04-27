package com.github.rakawestu.explorejogja.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.github.rakawestu.explorejogja.R;
import com.github.rakawestu.explorejogja.app.BaseActivity;
import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.ui.fragment.PlaceListFragment;
import com.github.rakawestu.explorejogja.ui.reactive.PlaceSelectedObservable;
import com.github.rakawestu.explorejogja.ui.reactive.PlaceSelectedObserver;

import org.parceler.Parcels;

import javax.inject.Inject;

/**
 * @author rakawm
 */
public class PlaceActivity extends BaseActivity implements PlaceSelectedObserver{
    public static final String KEY_SUBCATEGORY = "subcategory";

    @Inject
    PlaceSelectedObservable placeSelectedObservable;

    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        addPlaceFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        placeSelectedObservable.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        placeSelectedObservable.unregister(this);
    }


    private void addPlaceFragment() {
        String subCategoryId = getIntent().getStringExtra(KEY_SUBCATEGORY);

        PlaceListFragment fragment = PlaceListFragment.newInstance(subCategoryId);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_sub_category, fragment)
                .commit();

    }

    @Override
    public void placeSelected(Place place) {
        launchDetailActivity(place);
    }

    private void launchDetailActivity(Place place){
        Intent intent = new Intent(this, DetailsActivity.class);
        Parcelable parcelable = Parcels.wrap(place);
        intent.putExtra(DetailsActivity.KEY_PLACE, parcelable);
        startActivity(intent);
    }
}
