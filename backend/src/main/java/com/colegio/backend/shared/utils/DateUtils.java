package com.colegio.backend.shared.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final  class DateUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formatDate(LocalDate date) {

        if (date == null) {
            return "-";
        }

        return date.format(DATE_FORMATTER);
    }

}
