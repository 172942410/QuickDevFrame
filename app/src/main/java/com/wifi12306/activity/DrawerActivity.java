package com.wifi12306.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.wifi12306.R;
import com.perry.activity.BaseCompatActivity;
import com.perry.utils.statusbar.StatusBarUtil;

public class DrawerActivity extends BaseCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    public int initLayoutId() {
        return R.layout.activity_drawer;
    }

    @Override
    public void findView() {
        setSwipeBackEnable(false);//设置主页不可以滑动返回；
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
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
