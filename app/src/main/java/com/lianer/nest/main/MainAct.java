package com.lianer.nest.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;


import com.lianer.common.base.BaseActivity;
import com.lianer.nest.R;
import com.lianer.nest.User;
import com.lianer.nest.databinding.ActivityMainBinding;

public class MainAct extends BaseActivity {

    private ActivityMainBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        user = new User("lisheng", "libai");

        binding.setUser(user);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setLastName("haha");
            }
        });
    }

}
