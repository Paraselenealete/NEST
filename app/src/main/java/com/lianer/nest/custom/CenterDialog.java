package com.lianer.nest.custom;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Window;
import com.lianer.nest.R;


/**
 * dialog自定义
 * @author allison
 */
public class CenterDialog extends Dialog {
    private Context mContext;

    public CenterDialog(int layout, Context context) {
        super(context, R.style.MyDialog);
        __initDialog(layout);
    }

    private void __initDialog(int layout) {
        setContentView(layout);
        Window window = getWindow();
        setCanceledOnTouchOutside(true);
        if (window != null) {
            window.setBackgroundDrawableResource(R.drawable.dialog_circle_oval_bg);
        }
    }
}
