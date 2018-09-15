package com.lianer.nest.luach;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lianer.common.base.BaseActivity;
import com.lianer.common.common.LanguageType;
import com.lianer.common.utils.Utils;
import com.lianer.common.utils.language.MultiLanguageUtil;
import com.lianer.nest.R;

import java.util.Locale;


/**
 * 引导页
 * @author allison
 */
public class GuideAct extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        findViewById(R.id.en).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiLanguageUtil.getInstance().updateLanguage(com.lianer.common.utils.language.LanguageType.LANGUAGE_EN);
                Intent intent = new Intent(GuideAct.this, MainAct.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });
        findViewById(R.id.zh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiLanguageUtil.getInstance().updateLanguage(com.lianer.common.utils.language.LanguageType.LANGUAGE_CHINESE_SIMPLIFIED);
                Intent intent = new Intent(GuideAct.this, MainAct.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });
    }
}
