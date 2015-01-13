package com.aldoborrero.tinder.api.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class Dates {
    
    public static final DateFormat ISO_8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'", Locale.US) {{
        setTimeZone(TimeZone.getTimeZone("UTC"));
    }};
    
}
