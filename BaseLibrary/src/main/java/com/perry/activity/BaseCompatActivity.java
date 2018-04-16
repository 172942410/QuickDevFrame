package com.perry.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
import com.perry.utils.ActivityController;
import com.perry.utils.statusbar.StatusBarUtil;

import java.util.ArrayList;


public abstract class BaseCompatActivity extends SwipeBackActivity {
//		AppCompatActivity {
	public static String TAG = BaseCompatActivity.class.getSimpleName();
	/**
	 * 启动当前activity的Activity
	 */
	protected Bundle savedInstanceState;
	protected Toolbar toolbar;
	TextView textViewToolbarTitle;
	/**
	 * 1、在该方法中自动调用initLayoutId(),initView(),findView()，无需开发者手动调用
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
//		verifyStoragePermissions(this);
		ActivityController.addActivity(this,getClass());
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
		ActivityController.removeActivity(this);
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
//		Log.i("WIFI_AS", TAG + ": \n   " + message);
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

	@Override
	public void setTitle(CharSequence title) {
		super.setTitle(title);
		if(textViewToolbarTitle != null) {
			textViewToolbarTitle.setText(title);
		}
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
			textViewToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
			textViewToolbarTitle.setText(getTitle());
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

	/**
	 * 这个字段在子Activity类的 initLayoutId 方法中设置为false会隐藏状态栏
	 * 前提是该Activity 已经设置了全屏模式 代码onCreate中设置：getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	 * 或在主题 style中设置 <item name="android:windowFullscreen">true</item>
	 */
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
	 * 显示对话框的内容修改
	 * @param content
	 */
	protected void showCustomProcessDialogText(String content){
		if (mPostingdialog != null && mPostingdialog.isShowing()) {
			mPostingdialog.setPressText(content);
		}
	}
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

	/**
	 * 用户权限处理,
	 * 如果全部获取, 则直接过.
	 * 如果权限缺失, 则提示Dialog.
	 *
	 * @param requestCode  请求码
	 * @param permissions  权限
	 * @param grantResults 结果
	 */
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		Log.e(TAG,"requestCode:"+requestCode+",");
		for(String str : permissions){
			Log.e(TAG,"权限有:"+str);
		}
		for(int a : grantResults){
			Log.e(TAG,"权限值有："+a);
		}
		verifyStoragePermissionsAgain(this);
	}
	// Storage Permissions
	private static final int REQUEST_EXTERNAL_STORAGE = 1;
	/**
	 * Checks if the app has permission to write to device storage
	 * If the app does not has permission then the user will be prompted to
	 * grant permissions
	 * 华为手机读取sd卡权限
	 * @param activity
	 */
	public void verifyStoragePermissions(Activity activity) {
		ArrayList<String> per = new ArrayList<String>();
		//悬浮窗权限
//		int permissionAlert = ActivityCompat.checkSelfPermission(activity, Manifest.permission.SYSTEM_ALERT_WINDOW);//悬浮窗权限；必须要
//		if(permissionAlert != PackageManager.PERMISSION_GRANTED){
//			per.add(Manifest.permission.SYSTEM_ALERT_WINDOW);
//			if(Build.VERSION.SDK_INT >= 23) {
//				if (!Settings.canDrawOverlays(this)) {
//					Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
//					startActivityForResult(intent, 10);
//				}
//			}
//		}
//		//粗略位置权限
//		int permissionLocation = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);//位置权限；必须要
//		if(permissionLocation != PackageManager.PERMISSION_GRANTED){
//			per.add(Manifest.permission.ACCESS_COARSE_LOCATION);
//		}
//		//gps精确位置权限
//		int permissionLocationGPS = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);//位置权限 GPS；必须要
//		if(permissionLocationGPS != PackageManager.PERMISSION_GRANTED){
//			per.add(Manifest.permission.ACCESS_FINE_LOCATION);
//		}
//		//phone 手机状态权限
//		int permissionPhone = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE);//手机状态权限；必须要
//		if(permissionPhone != PackageManager.PERMISSION_GRANTED){
//			per.add(Manifest.permission.READ_PHONE_STATE);
//		}
		// Check if we have write permission
		int permissionWrite = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
		if(permissionWrite != PackageManager.PERMISSION_GRANTED){
			per.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
		}
		//检查是否有 读取sd卡权限
		int permissionRead = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
		if(permissionRead != PackageManager.PERMISSION_GRANTED){
			per.add(Manifest.permission.READ_EXTERNAL_STORAGE);
		}
		//检查是否有 调用摄像头权限
		int permissionCamera = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
		if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
			per.add(Manifest.permission.CAMERA);
		}
//		int permissionReadSms = ActivityCompat.checkSelfPermission(activity,Manifest.permission.READ_SMS);
//		if (permissionReadSms != PackageManager.PERMISSION_GRANTED) {
//			per.add(Manifest.permission.READ_SMS);
//		}
//		int permissionReceiveSms = ActivityCompat.checkSelfPermission(activity,Manifest.permission.RECEIVE_SMS);
//		if (permissionReceiveSms != PackageManager.PERMISSION_GRANTED) {
//			per.add(Manifest.permission.RECEIVE_SMS);
//		}
//		Log.e(TAG,"1 permissionAlert:"+permissionAlert+",permissionLocation:"+permissionLocation+",permissionLocationGPS:"+permissionLocationGPS+",permissionPhone:"+permissionPhone+",permissionWrite:"+permissionWrite+",permissionRead:"+permissionRead+",permissionCamera:"+permissionCamera);
//		if(per.size()>0){
//			Log.e(TAG,"所需要打开的权限:"+per.toString());
//			String[] permiss = new String[per.size()];
////            permiss =
//			per.toArray(permiss);
////            String[] permiss = (String[]) per.toArray();
//			// We don't have permission so prompt the user
//			ActivityCompat.requestPermissions(activity, permiss, REQUEST_EXTERNAL_STORAGE);
//		}
//		else{
//			//正常启动
//			gotoWelcome();
//		}
		if(per.size()>0){
			Log.e(TAG,"所需要打开的权限:"+per.toString());
			String[] permiss = new String[per.size()];
//            permiss =
			per.toArray(permiss);
//            String[] permiss = (String[]) per.toArray();
			// We don't have permission so prompt the user
			ActivityCompat.requestPermissions(activity, permiss, REQUEST_EXTERNAL_STORAGE);
		}
		else{
			//正常启动
		}
	}
	public void verifyStoragePermissionsAgain(Activity activity) {
		//悬浮窗权限
		int permissionAlert = ActivityCompat.checkSelfPermission(activity, Manifest.permission.SYSTEM_ALERT_WINDOW);//悬浮窗权限；必须要
		//粗略位置权限
//		int permissionLocation = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);//位置权限；必须要
		//gps精确位置权限
//		int permissionLocationGPS = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);//位置权限 GPS；必须要
		//phone 手机状态权限
//		int permissionPhone = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE);//手机状态权限；必须要
		// Check if we have write permission
		int permissionWrite = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
		//检查是否有 读取sd卡权限
		int permissionRead = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
		//检查是否有 调用摄像头权限
		int permissionCamera = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

//		int permissionReadSms = ActivityCompat.checkSelfPermission(activity,Manifest.permission.READ_SMS);

//		int permissionReceiveSms = ActivityCompat.checkSelfPermission(activity,Manifest.permission.RECEIVE_SMS);

//		Log.e(TAG,"2 permissionAlert:"+permissionAlert+",permissionLocation:"+permissionLocation+",permissionLocationGPS:"+permissionLocationGPS+",permissionPhone:"+permissionPhone+",permissionWrite:"+permissionWrite+",permissionRead:"+permissionRead+",permissionCamera:"+permissionCamera);
//		if( permissionLocation != PackageManager.PERMISSION_GRANTED || permissionLocationGPS != PackageManager.PERMISSION_GRANTED || permissionPhone != PackageManager.PERMISSION_GRANTED || permissionWrite!= PackageManager.PERMISSION_GRANTED || permissionRead!= PackageManager.PERMISSION_GRANTED || permissionCamera!= PackageManager.PERMISSION_GRANTED){
//			finish();
//		}else{
//			//启动下一个页面
//			gotoHomePage();
//		}
		if(permissionRead!= PackageManager.PERMISSION_GRANTED || permissionCamera!= PackageManager.PERMISSION_GRANTED || permissionWrite != PackageManager.PERMISSION_GRANTED){
			finish();
		}else{
			//启动下一个页面
//			gotoHomePage();
		}
	}

//	/**
//	 * 点击电话号拨打电话
//	 */
//	public void actionDial(View view){
//		if(view == null){
//			Toast.makeText(this, "你触发的事件 view 为空了", Toast.LENGTH_SHORT).show();
//			return;
//		}
//		if(view instanceof TextView) {
//			String telNo = ((TextView) view).getText().toString();
//			if(telNo != null && telNo.length()>2) {
//				Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telNo));
//				startActivity(phoneIntent);
//			}
//		}else{
//			Toast.makeText(this, "你点击的事件不是TextView的耶："+view.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
//		}
//	}



}
