package com.lianer.nest.dialog;


import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import com.lianer.nest.R;

/**
 * 部署合约费用
 * @author allison
 * 使用demo
 * new InputWalletPswDialog(new CenterDialog(R.layout.dlg_input_wallet_psd, MainAct.this), new InputWalletPswDialog.BtnListener() {
@Override
public void iKnow() {
ToastUtils.showLong("我知道了");
}
});
 */
public class DeployContractCostDialog implements View.OnClickListener {
    private Dialog mDialog;
    private BtnListener mListener;

    public DeployContractCostDialog(Dialog mDialog, BtnListener listener) {
        this.mListener = listener;
        this.mDialog = mDialog;
        __init();
    }

    private void __init() {
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(false);
        TextView tvKnow = mDialog.findViewById(R.id.i_know);

        tvKnow.setOnClickListener(this);
        mDialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.i_know:
                mDialog.dismiss();
                if (mListener != null) {
                    mListener.iKnow();
                }
                break;
        }

    }

    public interface BtnListener {
        void iKnow();
    }
}
