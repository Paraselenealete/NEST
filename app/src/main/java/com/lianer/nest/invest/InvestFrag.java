package com.lianer.nest.invest;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lianer.common.base.BaseFragment;
import com.lianer.nest.R;

/**
 * 投资页
 * @author allison
 */
public class InvestFrag extends BaseFragment {

    View mView;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_invest, null);
        return mView;
    }
}
