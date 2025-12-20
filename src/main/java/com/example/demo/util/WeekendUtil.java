package com.example.demo.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendUtil {

    public static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }
}
