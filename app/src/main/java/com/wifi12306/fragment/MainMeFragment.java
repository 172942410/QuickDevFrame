package com.wifi12306.fragment;

import android.content.Intent;
import android.view.View;

import com.wifi12306.activity.SettingActivity;
import com.wifi12306.R;

/**
 * 我的模块首页
 * 
 * @author lipengjun
 */

public class MainMeFragment extends BaseTabFragment implements View.OnClickListener{

	private static final String TAG = "MainMeFragment";

	@Override
	public int initViewId() {
		return R.layout.fragment_usercenter;
	}

	@Override
	public void initView() {
		setTitle("我的");
		findViewById(R.id.system_setting).setOnClickListener(this);
	}

	@Override
	protected void lazyLoad() {

	}
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.system_setting:
				Intent intent = new Intent(getActivity(),SettingActivity.class);
				getActivity().startActivity(intent);
				break;
			default:
				break;
		}
	}
}
