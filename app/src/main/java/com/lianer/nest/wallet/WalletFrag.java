package com.lianer.nest.wallet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lianer.common.base.BaseFragment;
import com.lianer.nest.R;
import com.lianer.nest.custom.TitlebarView;

/**
 * 钱包页
 * @author allison
 */
public class WalletFrag extends BaseFragment {

    View mView;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_wallet, null);
        return mView;
    }

    @Override
    protected void initData() {
        super.initData();

        initTitleBar();

    }

    private void initTitleBar() {
        TitlebarView titlebarView= mView.findViewById(R.id.titlebar);
        titlebarView.setRightWidgetVisible(0);
        titlebarView.setOnViewClick(new TitlebarView.onViewClick() {
            @Override
            public void leftClick() {
                Toast.makeText(getContext(),"左边",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightTextClick() {
                Toast.makeText(getContext(),"右边文字",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightImgClick() {
                Toast.makeText(getContext(),"右边",Toast.LENGTH_SHORT).show();
            }

        });
    }

}
