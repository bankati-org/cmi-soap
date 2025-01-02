package com.bankati.cmi.transaction.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static String LocalDateTimetoString(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(FORMATTER) : null;
    }

    public static LocalDateTime toLocalDateTime(String dateTime) {
        return dateTime != null ? LocalDateTime.parse(dateTime, FORMATTER) : null;
    }
}
