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
import com.github.rakawestu.explorejogja.ui.presenter.DetailsInfoPlacePresenter;
import com.github.rakawestu.explorejogja.ui.view.DetailsInforPlaceView;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceDetailsViewModel;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * @author rakawm
 */
public class DetailsInfoFragment extends BaseFragment implements DetailsInforPlaceView{

    @Inject
    DetailsInfoPlacePresenter presenter;
    @InjectView(R.id.place_info)
    TextView infoText;

    public static DetailsInfoFragment newInstance(Place place){
        Bundle args = new Bundle();
        Parcelable placeParcel = Parcels.wrap(place);
        args.putParcelable(DetailsActivity.KEY_PLACE, placeParcel);
        DetailsInfoFragment fragment = new DetailsInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showDetailInfo(PlaceDetailsViewModel place) {
        String info = "";
        String enter = "\n";
        if(place.getPrice()!=null&&!place.getPrice().equals("")){
            info+="Price range: " + place.getPrice()+enter;
        }
        if(place.getOpeningHours()!=null&&!place.getOpeningHours().equals("")){
            info+="Opening hours: "+place.getOpeningHours()+enter;
        }
        if(place.getCp()!=null&&!place.getCp().equals("")){
            info+="CP: " + place.getCp()+enter;
        }
        if(place.getFacility()!=null&&!place.getFacility().equals("")){
            info+="Facility: " + place.getFacility()+enter;
        }
        infoText.setText(info);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.place_details_info, container, false);
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
