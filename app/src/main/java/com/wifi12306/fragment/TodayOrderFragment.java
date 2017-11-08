package com.wifi12306.fragment;

import com.wifi12306.R;


/**
 * 今日订单页
 *
 * @author lipengjun
 */

public class TodayOrderFragment extends BaseTabFragment {
    private static final  java.lang.String TAG = "TodayOrderFragment";
    @Override
    public int initViewId() {
        return R.layout.tab_today_order;
    }

    @Override
    public void initView() {
       setTitle("今日订单");
    }

    @Override
    protected void lazyLoad() {
        // TODO tab的首页面不用实现此方法；

    }

}
