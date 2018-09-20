package com.lianer.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lianer.common.utils.language.MultiLanguageUtil;
import com.lianer.common.utils.qumi.QMUIStatusBarHelper;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 状态栏设置
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        initViews();
        initData();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase));
    }

    protected abstract void initViews();

    protected abstract void initData();
}
