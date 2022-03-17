package com.cgi.glk.ectp.pet;

import java.util.stream.Stream;

public class Utils {

    public static <T> T coalesce(final T pVal, final T... pVals) {
        if (pVal != null) {
            return pVal;
        }

        return Stream.of(pVals)
                .filter(v -> v != null)
                .findFirst()
                .orElse(null);
    }
}
