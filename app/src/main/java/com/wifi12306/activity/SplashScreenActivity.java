package com.wifi12306.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.perry.activity.BaseCompatActivity;
import com.wifi12306.R;

public class SplashScreenActivity extends BaseCompatActivity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            log("msg:"+msg.toString());
            if(msg.what == 0){
                Intent intent = new Intent();
                intent.setClass(SplashScreenActivity.this,CordovaChildActivity.class);
//                intent.putExtra("url","http://www.baidu.com");
//                intent.putExtra("url","http://www.uppit.com.cn/new/12306/index.html");
                intent.putExtra("url","http://106.120.205.115:6023/WorldCup/home/index.html#/");

                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        }
    };
    @Override
    public int initLayoutId() {
        isStatusBarShow = false;
        return R.layout.activity_splash;
    }

    @Override
    public void findView() {
        setSwipeBackEnable(false);//设置主页不可以滑动返回；
        //全屏模式
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void initView() {
        handler.sendEmptyMessageDelayed(0,1000);
    }

}
