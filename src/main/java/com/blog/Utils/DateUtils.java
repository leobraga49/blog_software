package com.blog.Utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    public static String formatPublishedDate(LocalDateTime publishedDate) {
        LocalDateTime now = LocalDateTime.now();
        long years = ChronoUnit.YEARS.between(publishedDate, now);
        long months = ChronoUnit.MONTHS.between(publishedDate, now);
        long days = ChronoUnit.DAYS.between(publishedDate, now);
        long hours = ChronoUnit.HOURS.between(publishedDate, now);
        long minutes = ChronoUnit.MINUTES.between(publishedDate, now);

        if (years > 0) return years + " years ago";
        if (months > 0) return months + " months ago";
        if (days > 0) return days + " days ago";
        if (hours > 0) return hours + " hours ago";
        return minutes + " minutes ago";
    }
}