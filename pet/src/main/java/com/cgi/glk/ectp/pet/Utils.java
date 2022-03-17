package com.cgi.glk.ectp.pet;

import java.util.stream.Stream;

public class Utils {

    public static <T> T coalesce(final T pValue, final T... pValues) {

        if (pValue != null) {
            return pValue;
        }

        for (final T value: pValues) {
            if (value != null) {
                return value;
            }
        }
        return null;
        /*
        return Stream.of(pValues)
                .filter(v -> v != null)
                .findFirst()
                .orElse(null);

         */
    }
}
