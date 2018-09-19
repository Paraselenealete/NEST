package com.lianer.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by goldze on 2017/5/14.
 * 常用工具类
 */
public final class Utils {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("should be initialized in application");
    }

    //计算时间差值
    public static String getDateDvalue(Date curDate, Date refreshDate) {
        if (refreshDate == null) {
            return "";
        }
        long l = curDate.getTime() - refreshDate.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (day < 0 || hour < 0 || min < 0 || min < 0) {
            return "1分钟内";
        } else if (day != 0) {
            return day + "天前";
        } else if (hour != 0) {
            return hour + "小时前";
        } else if (min != 0) {
            return min + "分钟前";
        } else {
            return "1分钟内";
        }
    }

    public static String parseDateToStringSec(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss", new Locale("zh", "CN"));
        if (null == date) {
            return sdf.format(new Date());
        }
        return sdf.format(date);
    }

    // String转换为Date
    public static Date parseStringToDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("zh", "CN"));
        try {
            return sdf.parse(str);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}