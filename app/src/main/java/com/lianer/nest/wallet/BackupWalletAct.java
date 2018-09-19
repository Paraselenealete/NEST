package com.lianer.nest.wallet;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.lianer.common.base.BaseActivity;
import com.lianer.common.utils.SPUtils;
import com.lianer.common.utils.ToastUtils;
import com.lianer.nest.R;
import com.lianer.nest.custom.CenterDialog;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.databinding.ActivityBackupWalletBinding;
import com.lianer.nest.dialog.InputWalletPswDialog;

/**
 * 备份钱包
 * @author allison
 */
public class BackupWalletAct extends BaseActivity {

    private ActivityBackupWalletBinding backupWalletBinding;
    private String password;
    private InputWalletPswDialog inputWalletPswDialog;

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
                inputWalletPswDialog = new InputWalletPswDialog(new CenterDialog(R.layout.dlg_input_wallet_psd, BackupWalletAct.this), new InputWalletPswDialog.BtnListener() {
                    @Override
                    public void sure() {
                        password = SPUtils.getInstance().getString("password");
                        if (isWalletPsdEquel(password)) {
                            Intent intent = new Intent(BackupWalletAct.this, MnemonicAct.class);
                            startActivity(intent);
                        } else {
                            ToastUtils.showShort("请输入正确的钱包密码");
                        }
                    }
                });
            }
        });
    }

    /**
     * 判断钱包密码是否正确
     * @param password
     * @return
     */
    private boolean isWalletPsdEquel(String password) {
        String inputPsd = inputWalletPswDialog.getWalletPsd();
        if (inputPsd.equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    protected void initData() {
    }
}
