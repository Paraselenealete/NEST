package com.lianer.nest.wallet;

import android.content.Intent;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lianer.common.base.BaseFragment;
import com.lianer.nest.R;
import com.lianer.nest.config.Tag;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.lauch.MainAct;
import com.lianer.nest.manager.InitWalletManager;
import com.lianer.nest.model.HLWallet;
import com.lianer.nest.stuff.HLError;
import com.lianer.nest.stuff.HLSubscriber;
import com.lianer.nest.stuff.ScheduleCompat;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * <pre>
 * Create by  :    L
 * Create Time:    2018/6/22
 * Brief Desc :
 * </pre>
 */
public class UnlockKeystoreFragment extends BaseFragment implements View.OnClickListener {

    View mView;
    EditText inputKeystore;
    EditText inputPassword;
    Button btnUnlock;
    TextView userAgreement;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_unlock_keystore, null);
        inputKeystore= mView.findViewById(R.id.et_keystore);
        inputPassword = mView.findViewById(R.id.et_password);
        btnUnlock = mView.findViewById(R.id.btn_unlock);
        userAgreement =  mView.findViewById(R.id.user_agreement);

        btnUnlock.setOnClickListener(this);
        userAgreement.setOnClickListener(this);
        return mView;
    }

    @Override
    protected void initData() {
        super.initData();

    }

    private void attemptUnlock() {
        String keystore = inputKeystore.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        Flowable
                .just(keystore)
                .filter(s -> validateInput(s,password))
                .flatMap(s -> InitWalletManager.shared().importKeystore(getActivity(),s,password))
                .compose(ScheduleCompat.apply())
                .subscribe(new HLSubscriber<HLWallet>(getActivity(),true) {
                    @Override
                    protected void success(HLWallet data) {
                        Snackbar.make(mView,"Unlock Success", Snackbar.LENGTH_LONG).show();
                        Flowable.just(1)
                                .delay(2000, TimeUnit.MILLISECONDS)
                                .compose(ScheduleCompat.apply())
                                .subscribe(integer -> {
                                    startActivity(new Intent(getActivity(),MainAct.class).putExtra(Tag.INDEX,1));
                                    getActivity().finish();
                                });
                    }

                    @Override
                    protected void failure(HLError error) {
                        Snackbar.make(mView,error.getMessage(), Snackbar.LENGTH_LONG)
                                .show();
                    }
                });
    }

    private boolean validateInput(String privateKey, String password) {
        // validate empty
        if (TextUtils.isEmpty(privateKey) || TextUtils.isEmpty(password)){
            ScheduleCompat.snackInMain(mView,"please input data");
            return false;
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_unlock:
                attemptUnlock();
                break;
            case R.id.user_agreement:
                Snackbar.make(mView,"User Agreement", Snackbar.LENGTH_LONG).show();
                break;
        }
    }
}
