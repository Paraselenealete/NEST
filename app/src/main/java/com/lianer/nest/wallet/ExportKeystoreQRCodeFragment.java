package com.lianer.nest.wallet;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lianer.common.base.BaseFragment;
import com.lianer.common.utils.Singleton;
import com.lianer.nest.R;
import com.lianer.nest.manager.HLWalletManager;
import com.lianer.nest.model.HLWallet;
import com.lianer.nest.utils.QRCodeUtil;


public class ExportKeystoreQRCodeFragment extends BaseFragment implements View.OnClickListener {

    View mView;
    Button BtnShow;
    ImageView showQRCode;
    String keystore;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_export_keystore_qr_code, null);

        BtnShow = mView.findViewById(R.id.btn_show);
        showQRCode =  mView.findViewById(R.id.show_qr_code);
        BtnShow.setOnClickListener(this);

        return mView;
    }



    @Override
    protected void initData() {
        super.initData();
        HLWallet currentWallet = HLWalletManager.shared().getCurrentWallet(getContext());
        keystore = Singleton.gson().toJson(currentWallet.walletFile);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_show:
                showQRCode.setImageBitmap(QRCodeUtil.createQRCodeBitmap(keystore,720,720));
                break;
        }
    }
}
