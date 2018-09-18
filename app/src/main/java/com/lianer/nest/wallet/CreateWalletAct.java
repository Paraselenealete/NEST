package com.lianer.nest.wallet;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lianer.common.base.BaseActivity;
import com.lianer.nest.R;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.databinding.ActivityCreateWalletBinding;

/**
 * 创建钱包
 * @author allison
 */
public class CreateWalletAct extends BaseActivity {

    private ActivityCreateWalletBinding createWalletBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
                Intent intent = new Intent(CreateWalletAct.this, BackupWalletAct.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
    }
}
