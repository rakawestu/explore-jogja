package com.github.rakawestu.explorejogja.ui.activity;

import android.os.Bundle;

import com.github.rakawestu.explorejogja.R;
import com.github.rakawestu.explorejogja.app.BaseActivity;
import com.github.rakawestu.explorejogja.ui.fragment.PlaceListFragment;

/**
 * @author rakawm
 */
public class PlaceActivity extends BaseActivity{
    public static final String KEY_SUBCATEGORY = "subcategory";
    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        addPlaceFragment();
    }

    private void addPlaceFragment() {
        String subCategoryId = getIntent().getStringExtra(KEY_SUBCATEGORY);

        PlaceListFragment fragment = PlaceListFragment.newInstance(subCategoryId);
        getFragmentManager()
                .beginTransaction()
                .add(R.id.frame_sub_category, fragment)
                .addToBackStack("")
                .commit();

    }
}
