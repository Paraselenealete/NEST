package com.lianer.nest.wallet;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lianer.common.base.BaseFragment;
import com.lianer.common.utils.Singleton;
import com.lianer.nest.R;
import com.lianer.nest.config.Tag;
import com.lianer.nest.lauch.MainAct;
import com.lianer.nest.manager.HLWalletManager;
import com.lianer.nest.manager.InitWalletManager;
import com.lianer.nest.model.HLWallet;
import com.lianer.nest.stuff.HLError;
import com.lianer.nest.stuff.HLSubscriber;
import com.lianer.nest.stuff.ScheduleCompat;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;


public class ExportKeystoreFileFragment extends BaseFragment implements View.OnClickListener {

    View mView;
    Button BtnCopy;
    TextView showKeystore;
    String keystore;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_export_keystore_file, null);

        BtnCopy = mView.findViewById(R.id.btn_copy);
        showKeystore =  mView.findViewById(R.id.show_keystore);
        BtnCopy.setOnClickListener(this);

        return mView;
    }



    @Override
    protected void initData() {
        super.initData();
        HLWallet currentWallet = HLWalletManager.shared().getCurrentWallet(getContext());
        keystore = Singleton.gson().toJson(currentWallet.walletFile);
        showKeystore.setText(keystore);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_copy:
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(keystore);
                Snackbar.make(mView,"复制成功", Snackbar.LENGTH_LONG).show();
                break;
        }
    }
}
