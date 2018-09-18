package com.lianer.nest.invest;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lianer.common.base.BaseFragment;
import com.lianer.nest.R;
import com.lianer.nest.custom.TitlebarView;

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

    @Override
    protected void initData() {
        super.initData();
        TitlebarView titlebarView= mView.findViewById(R.id.titlebar);
        titlebarView.setOnViewClick(new TitlebarView.onViewClick() {
            @Override
            public void leftClick() {
                Toast.makeText(getContext(),"左边",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightTextClick() {
            }

            @Override
            public void rightImgClick() {
            }

        });
    }
}
