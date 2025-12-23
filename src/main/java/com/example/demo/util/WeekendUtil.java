package com.example.demo.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendUtil {

    // Private constructor to prevent object creation
    private WeekendUtil() {
    }

    // Check whether the given date is a weekend
    public static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    // Get next working day (optional helper)
    public static LocalDate nextWorkingDay(LocalDate date) {
        LocalDate nextDay = date.plusDays(1);
        while (isWeekend(nextDay)) {
            nextDay = nextDay.plusDays(1);
        }
        return nextDay;
    }
}
