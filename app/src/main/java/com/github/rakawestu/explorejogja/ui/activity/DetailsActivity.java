package com.github.rakawestu.explorejogja.ui.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.common.view.SlidingTabLayout;
import com.github.rakawestu.explorejogja.R;
import com.github.rakawestu.explorejogja.app.BaseActivity;
import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.ui.fragment.DetailsDescriptionFragment;
import com.github.rakawestu.explorejogja.ui.fragment.DetailsInfoFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author rakawm
 */
public class DetailsActivity extends BaseActivity {

    public static final String KEY_PLACE = "place";
    SlidingTabLayout slidingTabLayout;
    TextView title;
    ImageView image;
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        slidingTabLayout = (SlidingTabLayout)findViewById(R.id.sliding_tabs);
        title = (TextView)findViewById(R.id.place_title);
        image = (ImageView)findViewById(R.id.image_view_place);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        setupTabbedView();
    }

    private void setupTabbedView() {
        //Get place data from intent
        Parcelable parcelable = getIntent().getExtras().getParcelable(KEY_PLACE);
        Place place = Parcels.unwrap(parcelable);
        title.setText(place.getJudul());
        if(place.getPhotos()!=null&&place.getPhotos().startsWith("http://")){
            Picasso.with(this)
                    .load(place.getPhotos())
                    .into(image);
        } else {
            image.setImageResource(R.drawable.no_image);
        }
        TabbedViewFragmentAdapter adapter = new TabbedViewFragmentAdapter(getSupportFragmentManager(), place);
        viewPager.setAdapter(adapter);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);
    }

    private class TabbedViewFragmentAdapter extends FragmentPagerAdapter{

        Place place;

        public TabbedViewFragmentAdapter(FragmentManager fm, Place place) {
            super(fm);
            this.place = place;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    Fragment fragment = DetailsDescriptionFragment.newInstance(place);
                    return fragment;
                case 1:
                    DetailsInfoFragment fragment1 = DetailsInfoFragment.newInstance(place);
                    return fragment1;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Description";
                case 1:
                    return "Info";
            }
            return null;
        }
    }

}
