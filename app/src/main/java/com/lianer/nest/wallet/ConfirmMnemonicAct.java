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
import com.lianer.nest.R;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.databinding.ActivityConfirmMnemonicBinding;
import com.lianer.nest.lauch.MainAct;
import com.lianer.nest.manager.WalletManager;
import com.lianer.nest.wallet.bean.WalletBean;

import org.web3j.crypto.CipherException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConfirmMnemonicAct extends BaseActivity {

    private ActivityConfirmMnemonicBinding confirmMnemonicBinding;
    private String mnemonics;
    private List<String> mnemonicData = new ArrayList<>();
    private List<String> mnemonicSelectData = new ArrayList<>();
    private QuickAdapter quickAdapter;
    private int selectIndex;

    @Override
    protected void initViews() {
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
        confirmMnemonicBinding.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetMnemonic();
            }
        });
        initRecyclerView();
    }

    /**
     * 重置助记词的顺序
     */
    private void resetMnemonic() {
        if (selectIndex != 0) {
            selectIndex = 0;
            mnemonicSelectData.clear();
        }
        Collections.shuffle(mnemonicData);
        quickAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        quickAdapter = new QuickAdapter<String>(mnemonicData) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_mnemonic;
            }

            @Override
            public void convert(VH holder, String data, int position) {
                holder.setText(R.id.singel_mnemonic, data);
                holder.getView(R.id.rl_item_mnemonic).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mnemonicIsEexist(data)) {
                            mnemonicSelectData.add(selectIndex, data);
                            holder.setImage(R.id.select_index, R.drawable.add_assets);
                            selectIndex++;
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
     * 判断助记词是否存在
     * @param data
     * @return
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
        String password = SPUtils.getInstance().getString("password");
        try {
            WalletBean walletBean = WalletManager.shared().generateWallet(password, mnemonics);
            if (walletBean != null) {
                SPUtils.getInstance().put("walletAddress", walletBean.getAddress());
                Intent intent = new Intent(ConfirmMnemonicAct.this, MainAct.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                ToastUtils.showShort(R.string.wallet_create_fail);
            }
        } catch (CipherException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {
        mnemonics = SPUtils.getInstance().getString("mnemonics");
        if (!TextUtils.isEmpty(mnemonics)) {
            String[] mnemonicArray = mnemonics.split(" ");
            Collections.addAll(mnemonicData, mnemonicArray);
            resetMnemonic();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        selectIndex = 0;
        if (mnemonicData.size() != 0) {
            mnemonicData.clear();
        }
        if (mnemonicSelectData.size() != 0) {
            mnemonicSelectData.clear();
        }
    }
}
