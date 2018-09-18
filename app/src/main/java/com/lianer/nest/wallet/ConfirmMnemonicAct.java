package com.lianer.nest.wallet;

import android.databinding.DataBindingUtil;

import com.lianer.common.base.BaseActivity;
import com.lianer.nest.R;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.databinding.ActivityConfirmMnemonicBinding;

public class ConfirmMnemonicAct extends BaseActivity {

    private ActivityConfirmMnemonicBinding confirmMnemonicBinding;

    @Override
    protected void initViews() {
        confirmMnemonicBinding = DataBindingUtil.setContentView(this, R.layout.activity_confirm_mnemonic);
        confirmMnemonicBinding.titlebar.showLeftDrawable();
        confirmMnemonicBinding.titlebar.setOnViewClick(new TitlebarView.onViewClick() {
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
    }

    @Override
    protected void initData() {

    }
}
