package com.lianer.nest.lauch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.lianer.common.base.BaseActivity;
import com.lianer.nest.R;

/**
 * 启动页
 * @author allison
 */
public class LauchAct extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lauch);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LauchAct.this, MainAct.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}
