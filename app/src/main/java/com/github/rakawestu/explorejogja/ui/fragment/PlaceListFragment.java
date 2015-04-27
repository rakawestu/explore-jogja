package com.github.rakawestu.explorejogja.ui.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.rakawestu.explorejogja.R;
import com.github.rakawestu.explorejogja.app.BaseFragment;
import com.github.rakawestu.explorejogja.domain.model.PlaceList;
import com.github.rakawestu.explorejogja.ui.adapter.PlaceModelAdapter;
import com.github.rakawestu.explorejogja.ui.custom.recycler.ClickRecyclerView;
import com.github.rakawestu.explorejogja.ui.presenter.PlaceListPresenter;
import com.github.rakawestu.explorejogja.ui.view.PlaceListView;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceModel;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import timber.log.Timber;

/**
 * @author rakawm
 */
public class PlaceListFragment extends BaseFragment implements PlaceListView{

    private static final String EXTRA_PLACE_COLLECTION = "extraPlaceCollection";
    public static final String KEY_SUBCATEGORY = "subcategory";

    @Inject
    PlaceListPresenter placeListPresenter;

    @InjectView(R.id.collection_view)
    ClickRecyclerView collectionView;
    @InjectView(R.id.loading)
    ProgressBar loading;
    @InjectView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private PlaceModelAdapter modelAdapter;
    private LinearLayoutManager mLayoutManager;

    public static PlaceListFragment newInstance(String subcategory){
        Bundle args = new Bundle();
        args.putString(KEY_SUBCATEGORY, subcategory);
        PlaceListFragment fragment = new PlaceListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * In android the view is not a simple view, there is some cases when the functionality of the
     * view is more than the excepted, in this case for example the view save the state
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Get the actual state of the characters
        PlaceList placeList = placeListPresenter.getParcelableCollection();

        //Parcel the object to be saved in the bundle
        Parcelable placesWrapped = Parcels.wrap(placeList);

        //Save the parcelable
        outState.putParcelable(EXTRA_PLACE_COLLECTION, placesWrapped);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            Timber.i("onViewStateRestored");
            //Get parcelable from bundle
            Parcelable placesWrapped = savedInstanceState.getParcelable(EXTRA_PLACE_COLLECTION);
            PlaceList marvelCharacters = Parcels.unwrap(placesWrapped);
            placeListPresenter.restoreParcelableCollection(marvelCharacters);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Timber.i("on create()");
        super.onCreate(savedInstanceState);
        modelAdapter = new PlaceModelAdapter();
        mLayoutManager = new LinearLayoutManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_place_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeCollectionView();
        placeListPresenter.setView(this);
        placeListPresenter.onViewCreate();

        if (savedInstanceState == null) {
            Timber.i("First time running");
            placeListPresenter.initialize();
            if(getArguments()!=null){
                placeListPresenter.onSelectedSubCategory(getArguments().getString(KEY_SUBCATEGORY));
            }
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    placeListPresenter.onRefresh(false);
                }
            });
        }

        addClickListenerToCharacterList();

    }

    private void initializeCollectionView() {
        collectionView.setAdapter(modelAdapter);
        collectionView.setLayoutManager(mLayoutManager);
        collectionView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public int getModelsRenderer() {
        return modelAdapter.getItemCount();
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void activateLastPlaceViewListener() {
        enableSearchOnFinish();
    }

    @Override
    public void disableLastPlaceViewListener() {
        disableSearchOnFinish();
    }

    @Override
    public void onError() {
        SnackbarManager.show(
                Snackbar.with(getActivity())
                        .text("Connection Error")
                        .animation(true)
                        .actionLabel("Tutup")
                        .dismissOnActionClicked(true)
                        .duration(Snackbar.SnackbarDuration.LENGTH_SHORT)
        );
    }

    @Override
    public void add(PlaceModel model) {
        modelAdapter.add(model);
    }

    @Override
    public void add(List<PlaceModel> models) {
        modelAdapter.add(models);
    }

    @Override
    public void remove(PlaceModel model) {

    }

    @Override
    public void refresh(boolean needProgress) {
        modelAdapter = new PlaceModelAdapter();
    }

    @Override
    public void hideSwipeRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    private class FinishScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition() + 1;
            int modelsCount = modelAdapter.getItemCount();

            if (lastVisibleItemPosition == modelsCount) {
                Timber.i("finish scroll!");
                placeListPresenter.onLastPlaceShowed();
            }
        }
    }

    private class CharacterClickListener implements ClickRecyclerView.OnItemClickListener {

        @Override
        public void onItemClick(RecyclerView parent, View view, int position, long id) {
            placeListPresenter.onPlaceSelected(position);
        }
    }

    private void addClickListenerToCharacterList() {
        collectionView.setOnItemClickListener(new CharacterClickListener());
    }

    private void enableSearchOnFinish() {
        collectionView.setOnScrollListener(new FinishScrollListener());
    }

    private void disableSearchOnFinish() {
        collectionView.setOnScrollListener(null);
    }
}
