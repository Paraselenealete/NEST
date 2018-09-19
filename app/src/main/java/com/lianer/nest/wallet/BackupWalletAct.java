package com.lianer.nest.wallet;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.lianer.common.base.BaseActivity;
import com.lianer.common.utils.ToastUtils;
import com.lianer.nest.R;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.databinding.ActivityBackupWalletBinding;

/**
 * 备份钱包
 * @author allison
 */
public class BackupWalletAct extends BaseActivity {

    private ActivityBackupWalletBinding backupWalletBinding;

    @Override
    protected void initViews() {
        backupWalletBinding = DataBindingUtil.setContentView(this, R.layout.activity_backup_wallet);
        backupWalletBinding.titlebar.showLeftDrawable();
        backupWalletBinding.titlebar.setOnViewClick(new TitlebarView.onViewClick() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightTextClick() {

            }

            @Override
            public void rightImgClick() {

            }
        });
        backupWalletBinding.backupWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BackupWalletAct.this, MnemonicAct.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void initData() {
    }
}
