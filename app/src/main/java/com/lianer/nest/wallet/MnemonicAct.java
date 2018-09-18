package com.lianer.nest.wallet;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.lianer.common.base.BaseActivity;
import com.lianer.nest.R;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.databinding.ActivityMnemonicBinding;

/**
 * 备份助记词
 * @author allison
 */
public class MnemonicAct extends BaseActivity {

    private ActivityMnemonicBinding mnemonicBinding;

    @Override
    protected void initViews() {
        mnemonicBinding = DataBindingUtil.setContentView(this, R.layout.activity_mnemonic);
        mnemonicBinding.titlebar.showLeftDrawable();
        mnemonicBinding.titlebar.setOnViewClick(new TitlebarView.onViewClick() {
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
        mnemonicBinding.backupMnemonic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MnemonicAct.this, ConfirmMnemonicAct.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
