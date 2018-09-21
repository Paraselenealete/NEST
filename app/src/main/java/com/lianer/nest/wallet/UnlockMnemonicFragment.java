package com.lianer.nest.wallet;

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

import com.lianer.common.base.BaseFragment;
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
import io.github.novacrypto.bip39.MnemonicValidator;
import io.github.novacrypto.bip39.Validation.InvalidChecksumException;
import io.github.novacrypto.bip39.Validation.InvalidWordCountException;
import io.github.novacrypto.bip39.Validation.UnexpectedWhiteSpaceException;
import io.github.novacrypto.bip39.Validation.WordNotFoundException;
import io.github.novacrypto.bip39.wordlists.English;
import io.reactivex.Flowable;

/**
 * <pre>
 * Create by  :    L
 * Create Time:    2018/6/22
 * Brief Desc :
 * </pre>
 */
public class UnlockMnemonicFragment extends BaseFragment implements View.OnClickListener {

    View mView;
    EditText inputMnemonic;
    EditText inputPassword;
    EditText inputRePassword;
    Button btnUnlock;
    TextView userAgreement;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_unlock_mnemonics, null);
        inputMnemonic= mView.findViewById(R.id.et_mnemonic);
        inputPassword = mView.findViewById(R.id.et_password);
        inputRePassword = mView.findViewById(R.id.et_re_password);
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
        String mnemonics = inputMnemonic.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String repassword = inputPassword.getText().toString().trim();
        Flowable
                .just(mnemonics)
                .filter(s -> validateInput(s,password,repassword))
                .flatMap(s -> InitWalletManager.shared().importMnemonic(getActivity(),password,s))
                .compose(ScheduleCompat.apply())
                .subscribe(new HLSubscriber<HLWallet>(getActivity(),true) {
                    @Override
                    protected void success(HLWallet data) {
                        Snackbar.make(mView,"Unlock Success", Snackbar.LENGTH_LONG).show();
                        Flowable.just(1)
                                .delay(2000, TimeUnit.MILLISECONDS)
                                .compose(ScheduleCompat.apply())
                                .subscribe(integer -> {
                                    HLWallet currentWallet = HLWalletManager.shared().getCurrentWallet(getContext());
                                    Log.w("wallet",currentWallet.getAddress());
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

    private boolean validateInput(String mnemonics, String password, String repassword) {
        // validate empty
        if (TextUtils.isEmpty(mnemonics) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)){
            ScheduleCompat.snackInMain(mView,"please input data");
            return false;
        }
        // validate password
        if (!TextUtils.equals(password,repassword)){
            ScheduleCompat.snackInMain(mView,"password does not match");
            return false;
        }
        // validate mnemonic
        try {
            MnemonicValidator.ofWordList(English.INSTANCE).validate(mnemonics);
        } catch (InvalidChecksumException e) {
            e.printStackTrace();
            ScheduleCompat.snackInMain(mView,e.getMessage());
            return false;
        } catch (InvalidWordCountException e) {
            e.printStackTrace();
            ScheduleCompat.snackInMain(mView,e.getMessage());
            return false;
        } catch (WordNotFoundException e) {
            e.printStackTrace();
            ScheduleCompat.snackInMain(mView,e.getMessage());
            return false;
        } catch (UnexpectedWhiteSpaceException e) {
            e.printStackTrace();
            ScheduleCompat.snackInMain(mView,e.getMessage());
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
