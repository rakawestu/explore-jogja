package com.github.rakawestu.explorejogja.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.rakawestu.explorejogja.R;
import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.ui.viewholder.AbstractRecyclerViewHolder;
import com.github.rakawestu.explorejogja.ui.viewmodel.PlaceModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * @author rakawm
 */
public class PlaceModelAdapter extends RecyclerView.Adapter<PlaceModelAdapter.ViewHolder>{
    private List<PlaceModel> models;

    public PlaceModelAdapter() {
        models = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View modelView = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_model, parent, false);
        return new ViewHolder(modelView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlaceModel model = models.get(position);
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        /*Picasso.with(holder.view.getContext())
                .load(model.getImageUrl())
                .into(holder.image);*/
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void add(PlaceModel model) {
        models.add(model);
        notifyDataSetChanged();
    }

    public void add(List<PlaceModel> models) {
        this.models.addAll(models);
        notifyDataSetChanged();
    }

    public PlaceModelAdapter(List<PlaceModel> placeModels){
        models =placeModels;
    }

    public class ViewHolder extends AbstractRecyclerViewHolder{
        View view;

        @InjectView(R.id.place_title)
        TextView title;
        @InjectView(R.id.place_image)
        ImageView image;
        @InjectView(R.id.place_description)
        TextView description;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }
}
