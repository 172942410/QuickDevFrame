package com.extendservice.activity;

import android.content.Intent;
import android.view.View;

import com.perry.activity.BaseCompatActivity;
import com.perry.utils.statusbar.StatusBarUtil;
import com.extendservice.R;

public class MainActivity extends BaseCompatActivity {

    @Override
    public int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void findView() {

    }

    @Override
    public void initView() {

    }

    public void changeColor(View view){
        StatusBarUtil.setColor(this,getResources().getColor(R.color.text_black));
    }

    public void showDialog(View view){
        showCustomProcessDialog("测试加载");
    }


    public void login(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
