package com.perry.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.perry.R;
import com.perry.dialog.ECProgressDialog;
import com.perry.swipeback.SwipeBackActivity;
import com.perry.utils.statusbar.StatusBarUtil;


public abstract class BaseCompatActivity extends SwipeBackActivity {
//		AppCompatActivity {
	public static String TAG = "BaseCompatActivity";
	/**
	 * 启动当前activity的Activity
	 */
	protected Bundle savedInstanceState;
	protected Toolbar toolbar;
	/**
	 * 1、在该方法中自动调用initLayoutId(),initView(),findView()，无需开发者手动调用
	 * 2、初始化EventBus.getDefault().register(this);
	 * @param savedInstanceState
     */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		TAG = this.getClass().getSimpleName();
		super.onCreate(savedInstanceState);
		this.savedInstanceState = savedInstanceState;
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(initLayoutId());
//		ActionBar actionBar = getSupportActionBar();
//		if(actionBar != null){
//			actionBar.hide();
//		}
		initToolBar();
		findView();
		initView();
	}

	/**
	 * 返回当前activity对应的layout id
	 * @return
     */
	public abstract int initLayoutId();

	/**
	 * 在该方法中调用findViewById
	 */
	public abstract void findView();

	/**
	 * 在该方法中对view进行数据初始化
	 */
	public abstract void initView();

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.gc();

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}



	/**
	 * 隐藏软键盘
	 */
	protected void hideSoftInput(){
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		if(getWindow().getAttributes().softInputMode==WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED){
			((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
    }

	/**
	 * 返回
	 * @param v
     */
	public void back(View v){
		finish();
	}

	/**
	 * 打印log，通过tag：WIFI_AS
	 * @param message
     */
	public void log(String message){
		Log.i("WIFI_AS", TAG + ": \n   " + message);
		Log.i(TAG, message + "");
	}

	public void showFragment(Fragment f, int id) {
		FragmentTransaction mft = getSupportFragmentManager().beginTransaction();
		if (f.isAdded()) {
			mft.show(f);
		} else {
			mft.add(id, f);
		}
		mft.commitAllowingStateLoss();
	}

	public void hideFragment(Fragment f ) {
		FragmentTransaction mft = getSupportFragmentManager().beginTransaction();
		if (f.isAdded()) {
			mft.hide(f);
		}
		mft.commitAllowingStateLoss();
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		//新 activity 从右往左滑动进入 暂时配合swipe back空间在style中配置了主题；所以可以暂时注释掉此行代码
//		overridePendingTransition(R.anim.push_left_in,0);
	}

	@Override
	public void finish() {
		super.finish();
		//此 activity 从左往右滑动退出 暂时配合swipe back空间在style中配置了主题；所以可以暂时注释掉此行代码
//		overridePendingTransition(0,R.anim.out_left_out);
	}

	/**
	 * 获得toolbar
	 * @return 获得toolbar
	 */
	public Toolbar getToolbar() {
		return toolbar;
	}
	/**
	 * 在子类中必须实现
	 * super.initToolBar();和
	 * 自定义toolbar的id必须定义成:
	 * R.id.toolbar
	 * 才可以继承tool bar 标题栏
	 *
	 */
	protected  void initToolBar(){
		// 设置ToolBar
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		if (toolbar != null) {
			// Title
             toolbar.setTitle("");
			setSupportActionBar(toolbar);
			TextView title = (TextView) findViewById(R.id.toolbar_title);
			title.setText(getTitle());
			toolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onBackPressed();
				}
			});
		}else{
			Log.e(TAG,"toolbar is null");
		}
		initStatusBar(isStatusBarShow);
	}

	protected boolean isStatusBarShow = true;
	protected  void initStatusBar(boolean isShow){
		if (isShow){
//			StatusBarUtil.setColorDiff(this,getResources().getColor(R.color.title_black));
			StatusBarUtil.setColorDiff(this,getResources().getColor(R.color.title_bar_bg));
		}
	}
	public Resources res;
//	public Drawable drawableToolBarBG;

//	public void initToolBarChild() {
//		if (toolbar == null) {
//			toolbar = (Toolbar) findViewById(R.id.toolbar);
//			if (toolbar != null) {
//				setSupportActionBar(toolbar);
//			}
//		}
//		if (toolbar != null) {
//			TextView title = (TextView) findViewById(R.id.toolbar_title);
//			title.setText(getTitle());
//			toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View view) {
//					onBackPressed();
//				}
//			});
//		}
//		if (res == null) {
//			res = getResources();
//		}
//		drawableToolBarBG = res.getDrawable(android.R.color.transparent);
//		if (toolbar != null) {
//			toolbar.setBackgroundDrawable(drawableToolBarBG);
//		}
//	}

	private ECProgressDialog mPostingdialog;
	/**
	 * 创建对话框
	 *
	 * @param content
	 */
	protected void showCustomProcessDialog(String content) {

		if (mPostingdialog != null && mPostingdialog.isShowing()) {
			return;
		}
		mPostingdialog = new ECProgressDialog(this, content);
		mPostingdialog.show();
	}

	/**
	 * 关闭对话框
	 */
	protected void dismissPostingDialog() {
		if (mPostingdialog == null || !mPostingdialog.isShowing()) {
			return;
		}
		mPostingdialog.dismiss();
		mPostingdialog = null;
	}

//	/**
//	 * 获取手机状态栏的高度
//	 *
//	 * @param context
//	 * @return
//	 */
//	public static int getStatusBarHeight(Context context) {
//		int result = 0;
//		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
//		if (resourceId > 0) {
//			result = context.getResources().getDimensionPixelSize(resourceId);
//		}
//		Log.e(TAG, "getStatusBarHeight:" + result);
//		return result;
//	}
}
