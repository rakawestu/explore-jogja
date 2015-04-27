package com.github.rakawestu.explorejogja.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.rakawestu.explorejogja.R;
import com.github.rakawestu.explorejogja.app.BaseFragment;
import com.github.rakawestu.explorejogja.ui.adapter.CategoryModelAdapter;
import com.github.rakawestu.explorejogja.ui.adapter.PlaceModelAdapter;
import com.github.rakawestu.explorejogja.ui.custom.recycler.ClickRecyclerView;
import com.github.rakawestu.explorejogja.ui.presenter.CategoryListPresenter;
import com.github.rakawestu.explorejogja.ui.presenter.PlaceListPresenter;
import com.github.rakawestu.explorejogja.ui.view.CategoryListView;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceModel;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import timber.log.Timber;

/**
 * @author rakawm
 */
public class CategoryListFragment extends BaseFragment implements CategoryListView{

    private static final String EXTRA_PLACE_COLLECTION = "extraPlaceCollection";

    @Inject
    CategoryListPresenter categoryListPresenter;

    @InjectView(R.id.collection_view)
    ClickRecyclerView collectionView;
    @InjectView(R.id.loading)
    ProgressBar loading;
    @InjectView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private CategoryModelAdapter modelAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Timber.i("on create()");
        super.onCreate(savedInstanceState);
        modelAdapter = new CategoryModelAdapter();
        mLayoutManager = new LinearLayoutManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_place_list, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeCollectionView();
        categoryListPresenter.setView(this);
        categoryListPresenter.onViewCreate();

        if (savedInstanceState == null) {
            Timber.i("First time running");
            categoryListPresenter.initialize();
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    categoryListPresenter.onRefresh(false);
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
    public void activateLastCategoryViewListener() {
        enableSearchOnFinish();
    }

    @Override
    public void disableLastCategoryViewListener() {
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
        modelAdapter = new CategoryModelAdapter();
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
                categoryListPresenter.onLastCategoryShowed();
            }
        }
    }

    private class CharacterClickListener implements ClickRecyclerView.OnItemClickListener {

        @Override
        public void onItemClick(RecyclerView parent, View view, int position, long id) {
            categoryListPresenter.onCategorySelected(position);
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
