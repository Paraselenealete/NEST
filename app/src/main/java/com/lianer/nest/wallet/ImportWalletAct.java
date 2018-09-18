package com.lianer.nest.wallet;

import android.databinding.DataBindingUtil;

import com.lianer.common.base.BaseActivity;
import com.lianer.nest.R;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.databinding.ActivityImportWalletBinding;

/**
 * 导入钱包）（助记词、keystore）
 * @author allison
 */
public class ImportWalletAct extends BaseActivity {

    private ActivityImportWalletBinding importWalletBinding;

    @Override
    protected void initViews() {
        importWalletBinding = DataBindingUtil.setContentView(this, R.layout.activity_import_wallet);
        importWalletBinding.titlebar.setOnViewClick(new TitlebarView.onViewClick() {
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
