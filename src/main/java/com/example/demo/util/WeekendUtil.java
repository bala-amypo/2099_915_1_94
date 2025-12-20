package com.example.demo.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendUtil {

    private WeekendUtil() {
        // Utility class – no object creation
    }

    /**
     * Checks whether today is weekend
     */
    public static boolean isWeekendToday() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        return today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY;
    }

    /**
     * Checks whether given date is weekend
     */
    public static boolean isWeekend(LocalDate date) {
        if (date == null) {
            return false;
        }
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    /**
     * Returns true if weekday
     */
    public static boolean isWeekday(LocalDate date) {
        return date != null && !isWeekend(date);
    }
}
