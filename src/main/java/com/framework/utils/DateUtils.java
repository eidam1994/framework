package com.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description: 日期工具类
 * @author: xingyuzhang
 * @create: 2020-09-21 16:30
 */
public class DateUtils {

    /**
     * 获取昨天的日期  yyyy-MM-dd
     * @return
     */
    public static String getTwoDaysAgoString() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -2);
        Date d = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }

}
