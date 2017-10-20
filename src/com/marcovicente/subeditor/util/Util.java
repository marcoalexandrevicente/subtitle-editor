package com.marcovicente.subeditor.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static com.marcovicente.subeditor.util.Constants.DATE_FORMAT;
import static com.marcovicente.subeditor.util.Constants.TIMEZONE;

public class Util {


    public static Date getDateFromString(String dateStr){
        try {
            return DATE_FORMAT.parse(dateStr.trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String formatDateAsString(Date date){
        return DATE_FORMAT.format(date);
    }

    public static String formatIntervalAsString(Date start, Date end){
        return formatDateAsString(start) + Constants.DATE_SEPARATOR + formatDateAsString(end);
    }

    public static Calendar getCalendar(Date date){
        Calendar calendar = Calendar.getInstance(TIMEZONE);
        calendar.setTime(date);

        return calendar;
    }
}
