package com.lianer.nest.lauch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lianer.common.utils.language.MultiLanguageUtil;
import com.lianer.common.utils.qumi.QMUIStatusBarHelper;
import com.lianer.nest.R;
import com.lianer.nest.borrow.BorrowFrag;
import com.lianer.nest.contract.ContractFrag;
import com.lianer.nest.databinding.ActivityMainBinding;
import com.lianer.nest.invest.InvestFrag;
import com.lianer.nest.wallet.CreateWalletAct;
import com.lianer.nest.wallet.WalletFrag;
import com.lianer.nest.wallet.WalletGuideAct;
import com.lianer.nest.wallet.bean.WalletAddrEventBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面
 * @author allison
 */
public class MainAct extends FragmentActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    private Fragment[] mFragmentArray = new Fragment[4];
    private int mSelectIndex = 0;
    private int mCurrentIndex = 0;
    List<ImageView> mImageViews = new ArrayList<>();
    List<TextView> mTextViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 状态栏设置
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initData();
        initClick();

    }

    private void initClick() {
        binding.lyFirst.setOnClickListener(this);
        binding.lySecond.setOnClickListener(this);
        binding.lyThird.setOnClickListener(this);
        binding.lyFourth.setOnClickListener(this);
    }

    /**
     * 初始化页面数据
     */
    private void initData() {
        mImageViews.add(binding.imgBottomFirst);
        mImageViews.add(binding.imgBottomSecond);
        mImageViews.add(binding.imgBottomThird);
        mImageViews.add(binding.imgBottomFourth);

        mTextViews.add(binding.txBottomFirst);
        mTextViews.add(binding.txBottomSecond);
        mTextViews.add(binding.txBottomThird);
        mTextViews.add(binding.txBottomFourth);

        mFragmentArray[0] = new InvestFrag();
        mFragmentArray[1] = new BorrowFrag();
        mFragmentArray[2] = new ContractFrag();
        mFragmentArray[3] = new WalletFrag();
        binding.imgBottomFirst.setSelected(true);
        binding.txBottomFirst.setTextColor(ContextCompat.getColor(this, R.color.clr_00BDFD));
        FragmentManager mManager = getSupportFragmentManager();
        FragmentTransaction mTransaction = mManager.beginTransaction();
        mTransaction.add(R.id.main_content, mFragmentArray[0]);
        mTransaction.show(mFragmentArray[0]);
        mTransaction.commit();
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ly_first:
                if (mCurrentIndex != 0) {
                    mSelectIndex = 0;
                } else {
                    return;
                }
                changeFragment();
                break;
            case R.id.ly_second:
                if (mCurrentIndex != 1) {
                    mSelectIndex = 1;
                } else {
                    return;
                }
                changeFragment();
                Intent intent1 = new Intent(MainAct.this, WalletGuideAct.class);
                startActivity(intent1);
                break;
            case R.id.ly_third:
                if (mCurrentIndex != 2) {
                    mSelectIndex = 2;
                } else {
                    return;
                }
                changeFragment();
                break;
            case R.id.ly_fourth:
                if (mCurrentIndex != 3) {
                    mSelectIndex = 3;
                } else {
                    return;
                }
                changeFragment();
                break;

        }

    }

    private void changeFragment() {
        if (mFragmentArray != null && mFragmentArray.length > 0) {
            //切换对应的页面
            if (mCurrentIndex != mSelectIndex) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.hide(mFragmentArray[mCurrentIndex]);
                if (mFragmentArray[mSelectIndex] != null && !mFragmentArray[mSelectIndex].isAdded()) {
                    transaction.add(R.id.main_content, mFragmentArray[mSelectIndex]);
                }
                transaction.show(mFragmentArray[mSelectIndex]);
                transaction.commitAllowingStateLoss();
//                transaction.commit();
                mImageViews.get(mCurrentIndex).setSelected(false);
                mImageViews.get(mSelectIndex).setSelected(true);

                mTextViews.get(mCurrentIndex).setTextColor(ContextCompat.getColor(this, R.color.clr_666666));
                mTextViews.get(mSelectIndex).setTextColor(ContextCompat.getColor(this, R.color.clr_059EFF));
            }
            mCurrentIndex = mSelectIndex;
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase));
    }
}
