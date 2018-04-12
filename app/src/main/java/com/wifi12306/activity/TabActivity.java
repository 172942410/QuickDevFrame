package com.wifi12306.activity;

import android.content.Intent;
import android.view.View;

import com.wifi12306.R;
import com.wifi12306.fragment.HistoryOrderFragment;
import com.wifi12306.fragment.MainMeFragment;
import com.wifi12306.fragment.TodayOrderFragment;
import com.perry.tabpage.ChildTabFragment;
import com.perry.tabpage.TabNavActivity;
import com.perry.utils.statusbar.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import static com.wifi12306.activity.CordovaWebViewActivity.dir_assets_h5;

public class TabActivity extends TabNavActivity {

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
    public void cordovaButton(View view) {
        Intent intent = new Intent(this,CordovaWebViewActivity.class);
        String url ="https://www.baidu.com";
        intent.putExtra("url", url);
        startActivity(intent);
    }
    public void cordovaAssetsButton(View view){
        Intent intent = new Intent(this,CordovaWebViewActivity.class);
        String url =dir_assets_h5+"jssdk/demo/index.html";
        intent.putExtra("url", url);
        startActivity(intent);
    }
    public void test(View view ){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private List<ChildTabFragment> fragments;
    /** 今日订单，历史订单,我的 **/
    private TodayOrderFragment todayOrderFragment;//今日订单
    private HistoryOrderFragment historyOrderFragment;//历史订单
    private MainMeFragment mainMeFragment;//我的
    @Override
    protected List<ChildTabFragment> initFragments() {
        fragments = new ArrayList<ChildTabFragment>();
        todayOrderFragment = new TodayOrderFragment();
//        mainSiXinFragment.setIconId(R.drawable.tab_rongxin);
        todayOrderFragment.setIconIdShade(R.drawable.tabbar_msg_n,R.drawable.tabbar_msg_p);
        fragments.add(todayOrderFragment);
        historyOrderFragment =  new HistoryOrderFragment();
//        mainContactsFragment.setIconId(R.drawable.tab_contacts);
        historyOrderFragment.setIconIdShade(R.drawable.tabbar_contacts_n,R.drawable.tabbar_contacts_p);
        fragments.add(historyOrderFragment);
        mainMeFragment = new MainMeFragment();
        mainMeFragment.setIconIdShade(R.drawable.tabbar_me_n,R.drawable.tabbar_me_p);
        fragments.add(mainMeFragment);
        return fragments;
    }
}
