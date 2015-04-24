package com.github.rakawestu.explorejogja.executor;

/**
 * The interactors callbacks are executed in a different thread, in Android the ui thread to perform
 * view changes
 *
 * @author rakawm
 */
public interface MainThreadExecutor {

    void execute(Runnable runnable);
}
