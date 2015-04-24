package com.github.rakawestu.explorejogja.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * ViewHolder for inject views using ButterKnife
 *
 * @author rakawm
 */
public abstract class AbstractRecyclerViewHolder extends RecyclerView.ViewHolder{

    public AbstractRecyclerViewHolder(View view){
        super(view);
        ButterKnife.inject(this, view);
    }
}
