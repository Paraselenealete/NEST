package com.lianer.nest.app;

import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.lianer.common.base.BaseApplication;

import com.lianer.common.common.LanguageType;
import com.lianer.common.utils.KLog;
import com.lianer.common.utils.Utils;
import com.lianer.common.utils.language.MultiLanguageUtil;

import java.util.Locale;

public class NestApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //开启打印日志
        KLog.init(true);

        MultiLanguageUtil.init(this);
    }
}
