package com.lianer.nest.wallet;

import android.databinding.DataBindingUtil;

import com.lianer.common.base.BaseActivity;
import com.lianer.common.base.BaseFragment;
import com.lianer.nest.R;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.databinding.ActivityImportWalletBinding;
import com.lianer.nest.wallet.adapter.FragmentAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * 导入钱包）（助记词、keystore）
 * @author allison
 */
public class ImportWalletAct extends BaseActivity {

    private ActivityImportWalletBinding importWalletBinding;

    @Override
    protected void initViews() {
        importWalletBinding = DataBindingUtil.setContentView(this, R.layout.activity_import_wallet);
        importWalletBinding.titlebar.showLeftDrawable();
        importWalletBinding.titlebar.setOnViewClick(new TitlebarView.onViewClick() {
            @Override
            public void leftClick() {
                onBackPressed();
            }

            @Override
            public void rightTextClick() {

            }

            @Override
            public void rightImgClick() {

            }
        });

        List<BaseFragment> fragments = Arrays.asList(
                new UnlockMnemonicFragment(),
                new UnlockKeystoreFragment()
        );
        List<String> labels = Arrays.asList(getString(R.string.mnemonic),getString(R.string.keystore));
        importWalletBinding.viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),fragments,labels));
        importWalletBinding.tabLayout.setupWithViewPager(importWalletBinding.viewPager);

    }

    @Override
    protected void initData() {

    }
}
