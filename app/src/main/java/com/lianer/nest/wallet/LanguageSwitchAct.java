package com.lianer.nest.wallet;

import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.lianer.common.base.BaseActivity;
import com.lianer.common.utils.language.LanguageType;
import com.lianer.common.utils.language.MultiLanguageUtil;
import com.lianer.nest.R;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.databinding.ActivityLanguageSwitchBinding;
import com.lianer.nest.lauch.MainAct;

/**
 * 多语言设置
 * @author allison
 */
public class LanguageSwitchAct extends BaseActivity {

    private ActivityLanguageSwitchBinding languageSwitchBinding;
    private int languageType;

    @Override
    protected void initViews() {
        languageSwitchBinding = DataBindingUtil.setContentView(this, R.layout.activity_language_switch);
        initTitleBar();
        initClick();
    }

    /**
     * 初始化标题栏
     */
    private void initTitleBar() {
        languageSwitchBinding.titlebar.showLeftDrawable();
        languageSwitchBinding.titlebar.setRightWidgetVisible(1);
        languageSwitchBinding.titlebar.setOnViewClick(new TitlebarView.onViewClick() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightTextClick() {

            }

            @Override
            public void rightImgClick() {
                selectLanguage(languageType);
            }
        });
    }

    /**
     * 初始化点击事件
     */
    private void initClick() {
        languageSwitchBinding.simplifiedChinese.setOnClickListener(v -> languageType = LanguageType.LANGUAGE_CHINESE_SIMPLIFIED);
        languageSwitchBinding.english.setOnClickListener(v -> languageType = LanguageType.LANGUAGE_EN);
    }

    @Override
    protected void initData() {

    }


    public void selectLanguage(int languageType) {
        MultiLanguageUtil.getInstance().updateLanguage(languageType);
        Intent intent = new Intent(LanguageSwitchAct.this, MainAct.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
