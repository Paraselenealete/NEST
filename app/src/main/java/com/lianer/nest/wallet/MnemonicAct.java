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
import com.lianer.nest.R;
import com.lianer.nest.custom.CenterDialog;
import com.lianer.nest.custom.TitlebarView;
import com.lianer.nest.databinding.ActivityMnemonicBinding;
import com.lianer.nest.dialog.KnowDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 备份助记词
 * @author allison
 */
public class MnemonicAct extends BaseActivity {

    private ActivityMnemonicBinding mnemonicBinding;
    private List<String> mnemonicData = new ArrayList<>();
    private QuickAdapter quickAdapter;

    @Override
    protected void initViews() {
        mnemonicBinding = DataBindingUtil.setContentView(this, R.layout.activity_mnemonic);
        mnemonicBinding.titlebar.showLeftDrawable();
        mnemonicBinding.titlebar.setOnViewClick(new TitlebarView.onViewClick() {
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
        mnemonicBinding.backupMnemonic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MnemonicAct.this, ConfirmMnemonicAct.class);
                startActivity(intent);
            }
        });
        initRecyclerView();
        initDialog();
    }

    /**
     * 初始化截图警告弹出框
     */
    private void initDialog() {
        new KnowDialog(new CenterDialog(R.layout.dlg_dont_screenshot, MnemonicAct.this),
                new KnowDialog.BtnListener() {
                    @Override
                    public void iKnow() {
                    }
                },
                getResources().getString(R.string.dont_screenshot),
                getResources().getString(R.string.dont_screenshot_warn));
    }

    @Override
    protected void initData() {
        String mnemonics = SPUtils.getInstance().getString("mnemonics");
        if (!TextUtils.isEmpty(mnemonics)) {
            String[] mnemonicArray = mnemonics.split(" ");
            Collections.addAll(mnemonicData, mnemonicArray);
            quickAdapter.notifyDataSetChanged();
        }
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
            }

        };
        LinearLayoutManager layoutManager = new GridLayoutManager(MnemonicAct.this , 3);
        //设置布局管理器
        mnemonicBinding.rvMnemonic.setLayoutManager(layoutManager);
        //设置Adapter
        mnemonicBinding.rvMnemonic.setAdapter(quickAdapter);
        //设置增加或删除条目的动画
        mnemonicBinding.rvMnemonic.setItemAnimator( new DefaultItemAnimator());
    }
}
