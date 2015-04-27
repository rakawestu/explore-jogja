package com.github.rakawestu.explorejogja.ui.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.rakawestu.explorejogja.R;
import com.github.rakawestu.explorejogja.app.BaseFragment;
import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.ui.activity.DetailsActivity;
import com.github.rakawestu.explorejogja.ui.presenter.DetailsDescriptionPlacePresenter;
import com.github.rakawestu.explorejogja.ui.view.DetailsDescriptionPlaceView;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceDetailsViewModel;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * @author rakawm
 */
public class DetailsDescriptionFragment extends BaseFragment implements DetailsDescriptionPlaceView{

    @Inject
    DetailsDescriptionPlacePresenter presenter;
    @InjectView(R.id.place_description)
    TextView description;

    @Override
    public void showDetailDescription(PlaceDetailsViewModel place) {
        description.setText(place.getDescription());
    }

    public static DetailsDescriptionFragment newInstance(Place place){
        Bundle args = new Bundle();
        Parcelable placeParcel = Parcels.wrap(place);
        args.putParcelable(DetailsActivity.KEY_PLACE, placeParcel);
        DetailsDescriptionFragment fragment = new DetailsDescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.place_details_description, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewCreate();
        presenter.setView(this);

        //Get character info
        if (getArguments() != null) {
            Place place= getPlaceFromArgs();
            presenter.onPlace(place);
        }
    }

    private Place getPlaceFromArgs(){
        Parcelable parcelable = getArguments().getParcelable(DetailsActivity.KEY_PLACE);
        return Parcels.unwrap(parcelable);
    }
}
