package com.lianer.common.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lianer.common.R;

import org.greenrobot.eventbus.EventBus;

/**
 * fragment的基类
 * @author allison
 */
public abstract class BaseFragment extends Fragment {
    public Activity mActivity;

    public BaseFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView(inflater, container, savedInstanceState);
        initData();
        return view;

    }


    /**
     * 初始化控件
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);//初始化控件

    /**
     * //初始化数据
     */
    protected void initData() {
    }


    /**
     * 跳转界面
     *
     * @param bundle
     * @param targetActivity
     */
    protected void enterActivity(Bundle bundle, Class<?> targetActivity) {
        Intent intent = new Intent(mActivity, targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转页面带动画的
     * @param bundle
     * @param targetActivity
     */
    public void enterActivityWithAnim(Bundle bundle, Class<?> targetActivity) {
        Intent intent = new Intent(mActivity, targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        mActivity.overridePendingTransition(R.anim.tooltip_enter, 0);
    }


}
