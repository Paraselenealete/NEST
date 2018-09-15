package com.lianer.nest.contract;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lianer.common.base.BaseFragment;
import com.lianer.nest.R;

/**
 * 合约页
 * @author allison
 */
public class ContractFrag extends BaseFragment {
    View mView;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_contract, null);
        return mView;
    }

}
