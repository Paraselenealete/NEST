package com.lianer.nest.wallet;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.lianer.common.base.BaseActivity;
import com.lianer.common.utils.SPUtils;
import com.lianer.nest.R;
import com.lianer.nest.custom.CenterDialog;
import com.lianer.nest.databinding.ActivityWalletManagerBinding;
import com.lianer.nest.dialog.DeleteDialog;

/**
 * 钱包管理
 * @author allison
 */
public class WalletManagerAct extends BaseActivity {

    private ActivityWalletManagerBinding walletManagerBinding;

    @Override
    protected void initViews() {
        walletManagerBinding = DataBindingUtil.setContentView(this, R.layout.activity_wallet_manager);
    }

    @Override
    protected void initData() {
        walletManagerBinding.languageSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLanguage();
            }
        });
        //删除钱包
        walletManagerBinding.deleteWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteWallet();
            }
        });
    }

    /**
     * 跳转到语言选择页面
     */
    private void navigateToLanguage() {
        Intent intent = new Intent(WalletManagerAct.this, LanguageSwitchAct.class);
        startActivity(intent);
    }

    /**
     * 删除钱包
     */
    private void deleteWallet() {
        new DeleteDialog(new CenterDialog(R.layout.dlg_delete_wallet, this), new DeleteDialog.BtnListener() {
            @Override
            public void delete() {
                SPUtils.getInstance().clear();
            }
        });

    }

}
