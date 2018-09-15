package com.lianer.nest.app;

import com.lianer.common.base.BaseApplication;

import com.lianer.common.utils.KLog;

public class NestApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //开启打印日志
        KLog.init(true);
    }
}
