package com.github.rakawestu.explorejogja.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.rakawestu.explorejogja.R;
import com.github.rakawestu.explorejogja.app.BaseFragment;
import com.github.rakawestu.explorejogja.domain.model.SubCategory;
import com.github.rakawestu.explorejogja.ui.adapter.CategoryModelAdapter;
import com.github.rakawestu.explorejogja.ui.custom.recycler.ClickRecyclerView;
import com.github.rakawestu.explorejogja.ui.presenter.CategoryListPresenter;
import com.github.rakawestu.explorejogja.ui.presenter.SubCategoryListPresenter;
import com.github.rakawestu.explorejogja.ui.view.SubCategoryListView;
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
public class SubCategoryListFragment extends BaseFragment implements SubCategoryListView{

    public static final String KEY_CATEGORY = "category";

    @Inject
    SubCategoryListPresenter subCategoryListPresenter;

    @InjectView(R.id.collection_view)
    ClickRecyclerView collectionView;
    @InjectView(R.id.loading)
    ProgressBar loading;

    private CategoryModelAdapter modelAdapter;
    private LinearLayoutManager mLayoutManager;

    public static SubCategoryListFragment newInstance(String category){
        Bundle args = new Bundle();
        args.putString(KEY_CATEGORY, category);
        SubCategoryListFragment fragment = new SubCategoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeCollectionView();
        subCategoryListPresenter.setView(this);
        subCategoryListPresenter.onViewCreate();

        if (savedInstanceState == null) {
            Timber.i("First time running");
            subCategoryListPresenter.initialize();
            if(getArguments()!=null){
                subCategoryListPresenter.onSelectedCategory(getArguments().getString(KEY_CATEGORY));
            }
        }
        //addClickListenerToCharacterList();
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
    public void activateLastSubCategoryViewListener() {
        enableSearchOnFinish();
    }

    @Override
    public void disableLastSubCategoryViewListener() {
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

    private class FinishScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition() + 1;
            int modelsCount = modelAdapter.getItemCount();

            if (lastVisibleItemPosition == modelsCount) {
                Timber.i("finish scroll!");
                subCategoryListPresenter.onLastSubCategoryShown();
            }
        }
    }

    private class CharacterClickListener implements ClickRecyclerView.OnItemClickListener {

        @Override
        public void onItemClick(RecyclerView parent, View view, int position, long id) {
            subCategoryListPresenter.onSubCategorySelected(position);
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
