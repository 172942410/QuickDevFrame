package com.extendservice.activity;

import android.content.Intent;
import android.view.View;

import com.extendservice.R;
import com.perry.activity.BaseCompatActivity;

/**
 * 选择用户类型
 */

public class ChooseUserActivity extends BaseCompatActivity implements View.OnClickListener{
    @Override
    public int initLayoutId() {
        return R.layout.activity_user_choose;
    }

    @Override
    public void findView() {
        findViewById(R.id.user_business).setOnClickListener(this);
        findViewById(R.id.user_to_serve).setOnClickListener(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.user_business || v.getId() == R.id.user_to_serve){
            Intent intent = new Intent(this,CharacterTypeActivity.class);
            startActivity(intent);
        }
    }
}
