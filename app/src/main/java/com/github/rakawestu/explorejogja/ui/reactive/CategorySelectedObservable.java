package com.github.rakawestu.explorejogja.ui.reactive;

import com.github.rakawestu.explorejogja.domain.model.Category;
import com.github.rakawestu.explorejogja.domain.model.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rakawm
 */
public class CategorySelectedObservable implements Observable<CategorySelectedObserver>{
    List<CategorySelectedObserver> observers;

    public CategorySelectedObservable(){
        observers = new ArrayList<>();
    }

    @Override
    public void register(CategorySelectedObserver observer) {
        if(!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void unregister(CategorySelectedObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Category category) {
        for (CategorySelectedObserver observer : observers) {
            observer.categorySelected(category);
        }
    }
}
