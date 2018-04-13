package com.wifi12306.activity;

public class CordovaChildActivity extends CordovaWebViewActivity {


    @Override
    public void findView() {
        setSwipeBackEnable(false);//设置主页不可以滑动返回；
        super.findView();
    }

    @Override
    public void initView() {
        super.initView();
    }

}
