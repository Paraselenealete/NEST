package com.lianer.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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


    /**
     * 判断两个集合的元素和顺序是否相同
     * @param list1
     * @param list2
     * @return
     */
    public static boolean eqList(List<String> list1, List<String> list2){
        boolean bl = true;
        if(list1.size() == list2.size()){
            for(int i=0; i<list1.size(); i++){
                if((list1.get(i)).equals(list2.get(i))){
                    continue;
                } else {
                    bl = false;
                    break;
                }
            }
        } else {
            bl = false;
        }
        return bl;
    }

}