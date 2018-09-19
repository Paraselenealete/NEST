package com.lianer.nest.dialog;


import android.app.Dialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lianer.nest.R;

/**
 * 确定删除钱包
 * @author allison
 */
public class DeleteDialog implements View.OnClickListener {
    private Dialog mDialog;
    private BtnListener mListener;
    private TextView tvDelete, tvCancel;

    public DeleteDialog(Dialog mDialog, BtnListener listener) {
        this.mListener = listener;
        this.mDialog = mDialog;
        __init();
    }

    private void __init() {
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(false);
        tvDelete = mDialog.findViewById(R.id.delete);
        tvCancel = mDialog.findViewById(R.id.cancel);

        tvDelete.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        mDialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                mDialog.dismiss();
                break;
            case R.id.delete:
                mDialog.dismiss();
                if (mListener != null) {
                    mListener.delete();
                }
                break;
        }

    }

    public interface BtnListener {
        void delete();
    }
}
