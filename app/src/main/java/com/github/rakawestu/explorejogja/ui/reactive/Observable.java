package com.github.rakawestu.explorejogja.ui.reactive;

/**
 * @author rakawm
 */
public interface Observable<T> {

    void register(T observer);

    void unregister(T observer);
}