package com.lianer.nest.wallet;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.lianer.common.base.BaseActivity;
import com.lianer.common.base.QuickAdapter;
import com.lianer.common.utils.SPUtils;
import com.lianer.common.utils.ToastUtils;
import com.lianer.common.utils.Utils;
import com.lianer.nest.R;
import com.lianer.nest.app.Constants;
import com.lianer.nest.custom.CenterDialog;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.databinding.ActivityConfirmMnemonicBinding;
import com.lianer.nest.dialog.KnowDialog;
import com.lianer.nest.lauch.MainAct;
import com.lianer.nest.manager.WalletManager;
import com.lianer.nest.wallet.bean.WalletAddrEventBean;
import com.lianer.nest.wallet.bean.WalletBean;

import org.greenrobot.eventbus.EventBus;
import org.web3j.crypto.CipherException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class ConfirmMnemonicAct extends BaseActivity {

    private ActivityConfirmMnemonicBinding confirmMnemonicBinding;
    private String mnemonics;
    private List<String> mnemonicData = new ArrayList<>();//原助记词
    private List<String> mnemonicRandomData = new ArrayList<>();//随机化助记词
    private List<String> mnemonicSelectData = new ArrayList<>();//选择助记词
    private List<Boolean> mnemonicImgVisible = new ArrayList<>();//助记词选中状态
    private int iamges[] = {R.drawable.point_1,R.drawable.point_2,R.drawable.point_3,R.drawable.point_4,R.drawable.point_5,
            R.drawable.point_6,R.drawable.point_7,R.drawable.point_8,R.drawable.point_9,R.drawable.point_10,R.drawable.point_11,
            R.drawable.point_12};//角标图片
    private QuickAdapter quickAdapter;
    private int selectIndex;//助记词选择索引

    @Override
    protected void initViews() {
//        EventBus.getDefault().register(this);
        confirmMnemonicBinding = DataBindingUtil.setContentView(this, R.layout.activity_confirm_mnemonic);
        confirmMnemonicBinding.titlebar.showLeftDrawable();
        confirmMnemonicBinding.titlebar.setOnViewClick(new TitlebarView.onViewClick() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightTextClick() {

            }

            @Override
            public void rightImgClick() {

            }
        });
        confirmMnemonicBinding.reset.setOnClickListener(v -> resetMnemonic());
        initRecyclerView();
    }

    @Override
    protected void initData() {
        mnemonics = SPUtils.getInstance().getString(Constants.SP_MNEMONICS);
        if (!TextUtils.isEmpty(mnemonics)) {
            String[] mnemonicArray = mnemonics.split(" ");
            Collections.addAll(mnemonicData, mnemonicArray);
            Collections.addAll(mnemonicRandomData, mnemonicArray);
            resetMnemonic();
        }
    }

    /**
     * 重置助记词的顺序
     */
    private void resetMnemonic() {
        if (selectIndex != 0) {
            selectIndex = 0;
            mnemonicSelectData.clear();
            mnemonicImgVisible.clear();
            for (int i = 0; i < mnemonicRandomData.size(); i++) {
                mnemonicImgVisible.add(i, false);
            }
        }
        setResetEnable(false);
        confirmMnemonicBinding.mnemonicErrorWarn.setVisibility(View.INVISIBLE);
        Collections.shuffle(mnemonicRandomData);
        quickAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化recyclerView
     */
    private void initRecyclerView() {
        quickAdapter = new QuickAdapter<String>(mnemonicRandomData) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_mnemonic;
            }

            @Override
            public void convert(VH holder, String data, int position) {
                if (mnemonicImgVisible.size() != 0) {
                    boolean isVisible = mnemonicImgVisible.get(position);
                    holder.getView(R.id.select_index).setVisibility(isVisible ? View.VISIBLE : View.GONE);
                }

                holder.setText(R.id.singel_mnemonic, data);
                holder.getView(R.id.rl_item_mnemonic).setOnClickListener(v -> {
                    if (!mnemonicIsEexist(data)) {
                        mnemonicSelectData.add(selectIndex, data);
                        mnemonicImgVisible.add(selectIndex, true);
                        holder.setImage(R.id.select_index, iamges[selectIndex]);
                        selectIndex++;
                        if (!confirmMnemonicBinding.reset.isEnabled())
                            //当用户点击1个单词后，重置按钮变可点击状态
                            setResetEnable(true);
                    }
                    if (mnemonicSelectData.size() == 12) {
                        if (Utils.eqList(mnemonicSelectData, mnemonicData)) {
                            new KnowDialog(new CenterDialog(R.layout.dlg_messge_warn, ConfirmMnemonicAct.this), () -> {
                                //执行创建钱包流程
                                createWallet();
                            }, getString(R.string.validate_success), getResources().getString(R.string.mnemonic_validate_success));
                        } else {
                            confirmMnemonicBinding.mnemonicErrorWarn.setVisibility(View.VISIBLE);
                        }

                    }
                });
            }

        };
        LinearLayoutManager layoutManager = new GridLayoutManager(ConfirmMnemonicAct.this , 3);
        //设置布局管理器
        confirmMnemonicBinding.rvMnemonic.setLayoutManager(layoutManager);
        //设置Adapter
        confirmMnemonicBinding.rvMnemonic.setAdapter(quickAdapter);
        //设置增加或删除条目的动画
        confirmMnemonicBinding.rvMnemonic.setItemAnimator( new DefaultItemAnimator());
    }

    /**
     * 设置重置按钮可点击状态
     * @param b 可点击状态
     */
    private void setResetEnable(boolean b) {
        confirmMnemonicBinding.reset.setEnabled(b);
    }

    /**
     * 判断助记词是否存在
     * @param data  助记词字符串
     * @return 助记词存在状态
     */
    private boolean mnemonicIsEexist(String data) {
        for (String mnemonic : mnemonicSelectData) {
            if (mnemonic.equals(data)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 创建钱包
     */
    private void createWallet() {
        String password = SPUtils.getInstance().getString(Constants.SP_WALLET_PASSWORD);
        try {
            WalletBean walletBean = WalletManager.shared().generateWallet(password, mnemonics);
            if (walletBean != null) {
                SPUtils.getInstance().put(Constants.SP_WALLET_ADDRESS, walletBean.getAddress());
                Intent intent = new Intent(ConfirmMnemonicAct.this, MainAct.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                EventBus.getDefault().post(new WalletAddrEventBean(walletBean.getAddress()));
            } else {
                ToastUtils.showShort(R.string.wallet_create_fail);
            }
        } catch (CipherException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        selectIndex = 0;
        if (mnemonicData.size() != 0) {
            mnemonicData.clear();
        }
        if (mnemonicRandomData.size() != 0) {
            mnemonicRandomData.clear();
        }
        if (mnemonicSelectData.size() != 0) {
            mnemonicSelectData.clear();
        }
        if (mnemonicImgVisible.size() != 0) {
            mnemonicImgVisible.clear();
        }
//        EventBus.getDefault().unregister(this);
    }
}
