package com.lianer.nest.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lianer.common.base.BaseFragment;
import com.lianer.common.base.QuickAdapter;
import com.lianer.common.utils.Utils;
import com.lianer.nest.R;
import com.lianer.nest.custom.CenterDialog;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.dialog.KnowDialog;
import com.lianer.nest.manager.WalletManager;
import com.lianer.nest.wallet.bean.AssetsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 钱包页
 * @author allison
 */
public class WalletFrag extends BaseFragment {

    private View mView;
    private RecyclerView rvAssets;
    private List<AssetsBean> assetsBeanList = new ArrayList<>();
    private QuickAdapter quickAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_wallet, null);
        return mView;
    }

    @Override
    protected void initData() {
        super.initData();

        for (int i = 0; i < 10; i++) {
            AssetsBean assetsBean = new AssetsBean();
            assetsBean.setAssetsName("ETH");
            assetsBean.setAssetsAmount("0.2");
            assetsBeanList.add(assetsBean);
        }

        initTitleBar();

        initRecyclerView();

    }

    private void initTitleBar() {
        TitlebarView titlebarView= mView.findViewById(R.id.titlebar);
        titlebarView.setRightWidgetVisible(0);
        titlebarView.setOnViewClick(new TitlebarView.onViewClick() {
            @Override
            public void leftClick() {
            }

            @Override
            public void rightTextClick() {
            }

            @Override
            public void rightImgClick() {
                Intent intent = new Intent(getContext(), WalletManagerAct.class);
                startActivity(intent);
            }

        });
    }

    /**
     * 初始化recyclerView
     */
    private void initRecyclerView() {
        rvAssets = mView.findViewById(R.id.rv_wallet_assets);
        quickAdapter = new QuickAdapter<AssetsBean>(assetsBeanList) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_wallet_assets;
            }

            @Override
            public void convert(VH holder, AssetsBean data, int position) {

            }

        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //设置布局管理器
        rvAssets.setLayoutManager(layoutManager);
        //设置Adapter
        rvAssets.setAdapter(quickAdapter);
        //设置增加或删除条目的动画
        rvAssets.setItemAnimator( new DefaultItemAnimator());
    }

}
