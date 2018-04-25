package com.wey.schedule.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
    
    public static String getDateWithFormat(String format) {
        Date d = new Date();
        return getDateWithFormat(d, format);
    }
    
    public static String getDateWithFormat(Date d, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(d);
    }
    
}
