package com.sky.hiwise.design.lesson.network.nio;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private final static String DEFAULT_PATTERN = "HH:mm:ss";

    public static String getCurTimeStr(){
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
        return sdf.format(new Date());
    }

}
