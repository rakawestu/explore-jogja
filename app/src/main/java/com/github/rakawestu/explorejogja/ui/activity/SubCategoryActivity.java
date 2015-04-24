package com.github.rakawestu.explorejogja.ui.activity;

import android.os.Bundle;

import com.github.rakawestu.explorejogja.R;
import com.github.rakawestu.explorejogja.app.BaseActivity;
import com.github.rakawestu.explorejogja.ui.fragment.SubCategoryListFragment;

/**
 * @author rakawm
 */
public class SubCategoryActivity extends BaseActivity{
    public static final String KEY_CATEGORY = "category";

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
}
