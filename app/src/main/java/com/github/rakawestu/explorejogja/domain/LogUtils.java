package com.github.rakawestu.explorejogja.domain;

/**
 * @author rakawm
 */
public class LogUtils {
    private LogUtils() {
        //You shall not pass
    }

    public static String generateTag(Object object) {
        return object.getClass().getCanonicalName();
    }
}
