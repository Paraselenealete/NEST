package com.lianer.nest.wallet;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.lianer.common.base.BaseActivity;
import com.lianer.common.utils.SPUtils;
import com.lianer.common.utils.ToastUtils;
import com.lianer.nest.R;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.databinding.ActivityCreateWalletBinding;
import com.lianer.nest.manager.WalletManager;

import org.web3j.crypto.WalletFile;

import io.reactivex.Flowable;

/**
 * 创建钱包
 * @author allison
 */
public class CreateWalletAct extends BaseActivity {

    private ActivityCreateWalletBinding createWalletBinding;
    private String mnemonics;
    private boolean isPsd, isRepsd;

    @Override
    protected void initViews() {
        createWalletBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_wallet);
        createWalletBinding.titlebar.showLeftDrawable();
        createWalletBinding.titlebar.setOnViewClick(new TitlebarView.onViewClick() {
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
        createWalletBinding.createWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWallet();
            }
        });
        createWalletBinding.password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    isPsd = true;
                }
                setCreateWalletEnable(isPsd && isRepsd);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        createWalletBinding.repassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    isRepsd = true;
                }
                setCreateWalletEnable(isPsd && isRepsd);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setCreateWalletEnable(boolean isWalletCreate) {
        createWalletBinding.createWallet.setEnabled(isWalletCreate);
    }

    private void createWallet() {
        String password = createWalletBinding.password.getText().toString().trim();
        String repassword = createWalletBinding.repassword.getText().toString().trim();

        if (validateInput(password, repassword)) {
            mnemonics = WalletManager.shared().generateMnemonics();
            if (mnemonics != null) {
                SPUtils.getInstance().put("mnemonics", mnemonics);
                SPUtils.getInstance().put("password", password);
                Intent intent = new Intent(CreateWalletAct.this, BackupWalletAct.class);
                intent.putExtra("mnemonics", mnemonics);
                startActivity(intent);
                finish();
            }
        }
    }

    private boolean validateInput(String password, String repassword) {
        if (TextUtils.isEmpty(password)){
            ToastUtils.showShort("密码不能为空");
            return false;
        }
        if (TextUtils.isEmpty(repassword)){
            ToastUtils.showShort("请重新输入密码");
            return false;
        }
        if (!TextUtils.equals(password,repassword)){
            ToastUtils.showShort("两次密码不一致");
            return false;
        }
        return true;
    }

    @Override
    protected void initData() {
    }
}
