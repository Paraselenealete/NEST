package com.lianer.nest.wallet;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.lianer.common.base.BaseActivity;
import com.lianer.common.base.QuickAdapter;
import com.lianer.nest.R;
import com.lianer.nest.databinding.ActivityAddTokenTypeBinding;
import com.lianer.nest.wallet.bean.TokenTypeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 增加币种
 * @author allison
 */
public class AddTokenTypeAct extends BaseActivity {

    private ActivityAddTokenTypeBinding addTokenTypeBinding;
    private List<TokenTypeBean> tokenTypeBeans = new ArrayList<>();
    private QuickAdapter quickAdapter;


    @Override
    protected void initViews() {
        addTokenTypeBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_token_type);
        addTokenTypeBinding.searchBack.setOnClickListener(v -> finish());
        initRecyclerView();
    }

    @Override
    protected void initData() {

    }

    /**
     * 初始化recyclerView
     */
    private void initRecyclerView() {
        //TODO 模式测试数据
        for (int i = 0; i < 10; i++) {
            TokenTypeBean assetsBean = new TokenTypeBean();
            assetsBean.setTokenLogo("");
            assetsBean.setTokenName("ETH");
            assetsBean.setTokenAmount("0.2");
            tokenTypeBeans.add(assetsBean);
        }


        quickAdapter = new QuickAdapter<TokenTypeBean>(tokenTypeBeans) {

            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_token_type;
            }

            @Override
            public void convert(VH holder, TokenTypeBean data, int position) {

            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        addTokenTypeBinding.rvTokenType.setLayoutManager(layoutManager);
        //设置Adapter
        addTokenTypeBinding.rvTokenType.setAdapter(quickAdapter);
        //设置增加或删除条目的动画
        addTokenTypeBinding.rvTokenType.setItemAnimator( new DefaultItemAnimator());
    }
}
