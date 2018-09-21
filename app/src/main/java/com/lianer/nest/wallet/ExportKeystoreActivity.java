package com.lianer.nest.wallet;

import android.databinding.DataBindingUtil;

import com.lianer.common.base.BaseActivity;
import com.lianer.common.base.BaseFragment;
import com.lianer.nest.R;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.databinding.ActivityExportKeystoreBinding;
import com.lianer.nest.databinding.ActivityImportWalletBinding;
import com.lianer.nest.wallet.adapter.FragmentAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * 导出助记词
 * @author bowen
 */
public class ExportKeystoreActivity extends BaseActivity {

    private ActivityExportKeystoreBinding mBinding;

    @Override
    protected void initViews() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_export_keystore);
        mBinding.titlebar.showLeftDrawable();
        mBinding.titlebar.setOnViewClick(new TitlebarView.onViewClick() {
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
                new ExportKeystoreFileFragment(),
                new ExportKeystoreQRCodeFragment()
        );
        List<String> labels = Arrays.asList(getString(R.string.keystore_file),getString(R.string.QR_code));
        mBinding.viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),fragments,labels));
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);

    }

    @Override
    protected void initData() {

    }
}
