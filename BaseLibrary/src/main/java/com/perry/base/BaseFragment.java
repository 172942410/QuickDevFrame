package com.perry.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
	public String TAG;

	/**
	 * 布局文件对应的id
	 */
	public abstract int initViewId();

	public abstract void initView();


	private int mLayoutId;
	public View view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TAG = this.getClass().getSimpleName();
//		log(TAG + " onCreate------------------------------------------------------------------------------------");
	}


	public void setContentView(int id) {
		this.mLayoutId = id;
	}

 

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView();
	}

	@Override
	public void onAttach(Activity activity) {
//		log(this.getClass().getSimpleName() + " onAttach------------------------------------------------------------------------------------");
		super.onAttach(activity);
	}

	@Override
	public void onDestroy() {
//		log(TAG + " onDestroy------------------------------------------------------------------------------------");
		super.onDestroy();
	}

 

	@Override
	public void onPause() {
//		log(TAG + " onPause------------------------------------------------------------------------------------");
		super.onPause();
	}

	@Override
	public void onResume() {
//		log(TAG + " onResume------------------------------------------------------------------------------------");
		super.onResume();
	}

	@Override
	public void onStop() {
//		log(TAG + " onStop------------------------------------------------------------------------------------");
		super.onStop();
	}

	@Override
	public void onStart() {
//		log(TAG + " onStart------------------------------------------------------------------------------------");
		super.onStart();
	}
 

	@Override
	public void onSaveInstanceState(Bundle outState) {
//		log(TAG + " onSaveInstanceState------------------------------------------------------------------------------------");
		super.onSaveInstanceState(outState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		int id = initViewId();
//		log(TAG + " onCreateView------------------------------------------------------------------------------------");
		view = inflater.inflate(id, container, false);
		return view;
	}


	public void log(String log) {
		Log.i("WIFI_AS", TAG + "    " + log);
		Log.i(TAG, log);
	}
	public View findViewById(int id){
		return  view.findViewById(id);
	}


	    
}
