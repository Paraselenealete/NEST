package com.lianer.nest.borrow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lianer.common.base.BaseFragment;
import com.lianer.nest.R;

/**
 * 借币页
 */
public class BorrowFrag extends BaseFragment {

    View mView;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_borrow, null);
        return mView;
    }
}
