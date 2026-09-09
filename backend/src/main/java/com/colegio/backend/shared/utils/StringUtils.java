package com.colegio.backend.shared.utils;

public final class StringUtils {

    private StringUtils() {
    }

    public static String joinNames(String first, String second) {

        if (first == null || first.isBlank()) {
            return second;
        }

        if (second == null || second.isBlank()) {
            return first;
        }

        return first + " " + second;
    }

    public static String safe(String value, String fallback) {
        return (value == null || value.isBlank()) ? fallback : value;
    }

    public static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

}