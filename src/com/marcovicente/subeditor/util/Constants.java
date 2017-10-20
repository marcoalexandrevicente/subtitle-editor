package com.marcovicente.subeditor.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Constants {

    final public static TimeZone TIMEZONE = TimeZone.getTimeZone("UTC");

    final public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss,SSS");

    static {
        DATE_FORMAT.setTimeZone(TIMEZONE);
    }

    final public static String DATE_SEPARATOR = " --> ";
}
