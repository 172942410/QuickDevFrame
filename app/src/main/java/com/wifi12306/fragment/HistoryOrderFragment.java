package com.wifi12306.fragment;

import com.wifi12306.R;


/**
 * 历史订单页
 *
 * @author lipengjun
 */

public class HistoryOrderFragment extends BaseTabFragment
{
	private String TAG = "HistoryOrderFragment";

    @Override
    public int initViewId() {
        return R.layout.tab_history_order;
    }

    @Override
    public void initView() {
        setTitle("历史订单");
        isInit = true;
        lazyLoad();
    }

	@Override
	public void onResume() {
		super.onResume();
    }

	@Override
	protected void lazyLoad() {

	}

}
