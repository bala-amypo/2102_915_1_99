package com.example.demo.util;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtil {
    public static String formatDate(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ISO_DATE) : null;
    }
    
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ISO_DATE_TIME) : null;
    }
}