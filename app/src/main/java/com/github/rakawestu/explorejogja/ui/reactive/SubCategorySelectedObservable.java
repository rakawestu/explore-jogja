package com.github.rakawestu.explorejogja.ui.reactive;

import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.domain.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rakawm
 */
public class SubCategorySelectedObservable implements Observable<SubCategorySelectedObserver>{

    List<SubCategorySelectedObserver> subCategorySelectedObservers;

    public SubCategorySelectedObservable(){
        subCategorySelectedObservers = new ArrayList<>();
    }

    @Override
    public void register(SubCategorySelectedObserver observer) {
        if(!subCategorySelectedObservers.contains(observer)){
            subCategorySelectedObservers.add(observer);
        }
    }

    @Override
    public void unregister(SubCategorySelectedObserver observer) {
        subCategorySelectedObservers.remove(observer);
    }

    public void notifyObservers(SubCategory subCategory) {
        for (SubCategorySelectedObserver observer: subCategorySelectedObservers) {
            observer.onSubCategorySelected(subCategory);
        }
    }
}
