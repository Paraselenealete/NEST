package com.lianer.nest.wallet;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.lianer.common.base.BaseActivity;
import com.lianer.common.utils.SPUtils;
import com.lianer.common.utils.ToastUtils;
import com.lianer.nest.R;
import com.lianer.nest.app.Constants;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.databinding.ActivityCreateWalletBinding;
import com.lianer.nest.manager.WalletManager;

/**
 * 创建钱包
 * @author allison
 */
public class CreateWalletAct extends BaseActivity {

    private ActivityCreateWalletBinding createWalletBinding;
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
        createWalletBinding.createWallet.setOnClickListener(v -> createWallet());
        createWalletBinding.password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isPsd = !TextUtils.isEmpty(s);
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
                isRepsd = !TextUtils.isEmpty(s);
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
            String  mnemonics = WalletManager.shared().generateMnemonics();
            if (mnemonics != null) {
                SPUtils.getInstance().put(Constants.SP_MNEMONICS, mnemonics);
                SPUtils.getInstance().put(Constants.SP_WALLET_PASSWORD, password);
                Intent intent = new Intent(CreateWalletAct.this, BackupWalletAct.class);
                intent.putExtra(Constants.SP_MNEMONICS, mnemonics);
                startActivity(intent);
            }
        }
    }

    private boolean validateInput(String password, String repassword) {
        if (!TextUtils.equals(password,repassword)){
            ToastUtils.showShort(R.string.second_psd_not_match);
            return false;
        }
        return true;
    }

    @Override
    protected void initData() {
    }
}
