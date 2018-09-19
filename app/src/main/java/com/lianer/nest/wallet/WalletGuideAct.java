package com.lianer.nest.wallet;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.lianer.common.base.BaseActivity;
import com.lianer.nest.R;
import com.lianer.nest.databinding.ActivityWalletGuideBinding;
import com.lianer.nest.lauch.MainAct;

/**
 * 钱包引导（创建新钱包/导入已有钱包）
 * @author allison
 */
public class WalletGuideAct extends BaseActivity{

    private ActivityWalletGuideBinding walletGuideBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        walletGuideBinding = DataBindingUtil.setContentView(this, R.layout.activity_wallet_guide);
        walletGuideBinding.createWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletGuideAct.this, CreateWalletAct.class);
                startActivity(intent);
            }
        });
        walletGuideBinding.importWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(WalletGuideAct.this, ImportWalletAct.class);
                startActivity(intent2);
            }
        });
    }

    @Override
    protected void initData() { }
}
