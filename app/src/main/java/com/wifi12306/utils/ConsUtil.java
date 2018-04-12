package com.wifi12306.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * 常用常量类
 *
 * @author shidongxue
 *
 */
public class ConsUtil {

	public static String packetName = "com.wifi12306";

	/** 应用程序文件夹名称 **/
	public static String appdirname = "wifi12306";
	/** 应用程序文件夹 **/
	public static String dir_appname = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + appdirname;
	/** log文件夹 **/
	public static String dir_applog = dir_appname + "/log";

	public static String dir_appcache = dir_appname + "/cache";
	/** 应用程序本地下载文件夹 **/
	public static String dir_appdownload = dir_appname + "/download";
	/** 应用程序本地储存声音文件夹 **/
	public static String dir_appcache_voice = dir_appname + "/voice";
	/** 应用程序本地储存推送信息文件夹 **/
	public static String dir_appcache_pushmsg = dir_appname + "/pushmsg";
	/** 应用程序本地拍照图片文件夹 **/
	public static String dir_appcache_camera = dir_appname + "/camera";
	/** 应用程序本地储存(大)图片文件夹 **/
	public static String dir_appcache_imagebig = dir_appname + "/imagebig";
	/** 应用程序本地储存(小)图片文件夹 **/
	public static String dir_appcache_imagecompress = dir_appname + "/imagecompress";
	/** 应用程序本地储存(小)图片文件夹 **/
	public static String dir_appcache_imagehead = dir_appname + "/imagehead/";
	/** 应用程序本地储存地图截取文件夹 **/
	public static String dir_appcache_image_snapshot = dir_appname + "/imagesnapshot/";
	/** 应用程序本地存储H5zip包**/
	public static String dir_appcache_h5_inspector = dir_appname + "/h5/";
	/** 应用程序h5解压路径**/
	public static String dir_data_h5_inspector = "/data/data/"+packetName+"/www/";
//	public static String dir_data_h5_inspector = dir_appname +"/www/";
	/** 应用程序data存储h5位置**/
	public static String dir_data_h5 = "/data/data/"+packetName+"/";
	/**assets目录下的文件**/
	public static String dir_assets_h5 = "file:///android_asset/";
	public static String SX_STRING_PLUGIN_FILES_FOLDER = dir_appname + "/PluginFiles";             //cordova插件文件存储路径
	public static String SX_STRING_PLUGIN_IMAGES_FOLDER = dir_appname + "/PluginFiles/Images/";     //插件图片存储路径
	public static String SX_STRING_PLUGIN_VOICE_FOLDER = dir_appname + "/PluginFiles/Voice";       //插件录音存储路径
	public static String SX_STRING_PLUGIN_IMG_FOLDER = dir_appname + "/PluginFiles/Temp/Images/";       //插件图片存储路径
	public static String SX_STRING_PLUGIN_LOCALSTORAGE_IMAGES_FOLDER = dir_appname + "/PluginFiles/LocalStorage/Images/"; //插件LocalStorage图片缓存目录
	public static String SX_STRING_PLUGIN_LOCALSTORAGE_VOICE_FOLDER = dir_appname + "/PluginFiles/LocalStorage/Voice/"; //插件LocalStorage图片缓存目录
	public static String SX_STRING_PLUGIN_LOCALSTORAGE_FOLDER = dir_appname + "/PluginFiles/LocalStorage/"; //插件LocalStorage缓存目录


	public static String SX_STRING_VIDEO_FOLDER = dir_appname + "/im/video/";
	public static String SX_STRING_VIDEOIMG_FOLDER = dir_appname + "/im/videoimg/";
	/*缓存用户行为信息*/
	public static String SX_STRING_USERACTION_FOLDER = dir_applog + "/useractions/";



	/**更新进度条的message.what**/
	public  static final int update_progress = 99;
	/**下载zip包完成之后的msg.what**/
	public  static final int DOWN_ZIP_DONE = 100;
	/**解压zip包完成之后的msg.what**/
	public  static final int ZIP_OPEN_DONE = 101;
	/**下载zip包错误msg.what**/
	public  static final int ZIP_OPEN_ERROR = 500;
	/**下载zip包无网msg.what**/
	public  static final int NO_CLIENT = 501;
	/**下载zip包开始loading框msg.what**/
	public  static final int DOWN_ZIP_START = 102;

	/** 获取html标题**/
	public static final int Html_Title = 30;
	/** h5传过来的自定义标题**/
	public static final int Custom_Html_Title = 31;



	// 取消loading
	public static final int what_dismisloading = 15000;

	public static final int what_chatPercentUpdate = 15002;
	public static final int what_chatUpdateSendFailed = 15003;
	public static final int what_chatUploadImageSuccessed = 15004;
	public static final int what_chatUploadVoiceSuccessed = 15005;
	public static final int what_Share_Succesd = 15006;

	/** 图片处理相关 **/
	/** 相册result_code **/
	public static final int REQ_FROM_PHOTO = 1;
	/** 拍照 result_code **/
	public static final int REQ_FROM_CAMERA = 2;
	/** 图片编辑 result_code **/
	public static final int REQ_FROM_PHOTOEDIT = 3;
	public static final int REQ_FROM_MULTIPHOTO_SELECTED = 14;
	public static final int REQ_FROM_MULTIPHOTO_BACK = 15;//选择照片预览页面返回事件
	public static final int REQ_RESULT_PHOTO_BACK = 16;//从图片界面关闭直接返回绘话页面
	/** 头像裁剪相关 **/
	public static String IMAGE_UNSPECIFIED = "image/*";


	/** 通讯录搜索框跳转code **/
	public static final int REQ_FROM_CONTANTS_TO_SEARCHACT = 11;



	/**
	 * plugin跳转架构选取架构
	 */
	public static final int PLUGIN_ORG_STRUCTURE = 100;

	/**
	 * 创建文件夹
	 *
	 * @param
	 */
	public static void creatAppDir(Context mContext) {
		// 创建应用文件夹
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
		} else {
			ConsUtil.dir_appname = mContext.getCacheDir().getAbsolutePath() + "/" + appdirname;
		}
		// 应用程序文件夹
		File file = new File(ConsUtil.dir_appname);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 应用程序本地log文件夹
		File file0 = new File(ConsUtil.dir_applog);
		if (!file0.exists()) {
			file0.mkdirs();
		}
		// 应用程序本地下载文件夹
		File file1 = new File(ConsUtil.dir_appdownload);
		if (!file1.exists()) {
			file1.mkdirs();
		}
		// 应用程序本地储存声音文件夹
		File file2 = new File(ConsUtil.dir_appcache_voice);
		if (!file2.exists()) {
			file2.mkdirs();
		}
		// 应用程序本地储存(大)图片文件夹
		File file3 = new File(ConsUtil.dir_appcache_imagebig);
		if (!file3.exists()) {
			file3.mkdirs();
		}
		// 应用程序本地储存(小)图片文件夹
		File file4 = new File(ConsUtil.dir_appcache_imagecompress);
		if (!file4.exists()) {
			file4.mkdirs();
		}
		// 应用程序本地储存头图片文件夹
		File file5 = new File(ConsUtil.dir_appcache_imagehead);
		if (!file5.exists()) {
			file5.mkdirs();
		}
		// 应用程序本地储存(大)图片文件夹
		File file6 = new File(ConsUtil.dir_appcache_camera);
		if (!file6.exists()) {
			file6.mkdirs();
		}
		// 应用程序本地储存(大)图片文件夹
		File file7 = new File(ConsUtil.dir_appcache);
		if (!file7.exists()) {
			file7.mkdirs();
		}
		// Plugin文件夹
		File file8 = new File(ConsUtil.SX_STRING_PLUGIN_FILES_FOLDER);
		if (!file8.exists()) {
			file8.mkdirs();
		}
		// Plugin图片文件夹
		File file9 = new File(ConsUtil.SX_STRING_PLUGIN_IMAGES_FOLDER);
		if (!file9.exists()) {
			file9.mkdirs();
		}
		// Plugin录音文件夹
		File file10 = new File(ConsUtil.SX_STRING_PLUGIN_VOICE_FOLDER);
		if (!file10.exists()) {
			file10.mkdirs();
		}
		// Plugin录音文件夹
		File file11 = new File(ConsUtil.SX_STRING_PLUGIN_IMG_FOLDER);
		if (!file11.exists()) {
			file11.mkdirs();
			file11.mkdir();
		}
		File file12 = new File(ConsUtil.SX_STRING_VIDEO_FOLDER);
		if (!file12.exists()) {
			file12.mkdirs();
			file12.mkdir();
		}
		File file13 = new File(ConsUtil.SX_STRING_VIDEOIMG_FOLDER);
		if (!file13.exists()) {
			file13.mkdirs();
			file13.mkdir();
		}
//		File file14 = new File(ConsUtil.SX_STRING_USERACTION_FOLDER);
//		if (!file14.exists()) {
//			file14.mkdirs();
//			file14.mkdir();
//		}


	}


	/** 下载文件进度 **/
	public static final int what_progress = 3004;

	/**
	 * 获取指定日期
	 *
	 * @param longdatetime
	 * @return
	 */
	public static String getDate(long longdatetime) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(longdatetime));
		int minutes = c.get(Calendar.MINUTE);
		int hours = c.get(Calendar.HOUR);
		int years = c.get(Calendar.YEAR);
		int months = c.get(Calendar.MONTH) + 1;
		int days = c.get(Calendar.DAY_OF_MONTH);
		int AM_orPM = c.get(Calendar.AM_PM);
		StringBuffer sbBuffer = new StringBuffer();
		try {
			String A_P_M = "";
			if (AM_orPM == 0) {
				A_P_M = "上午";
			} else if (AM_orPM == 1) {
				A_P_M = "下午";
			}
			if (hours > 12) {
				hours = hours - 12;
			}
			if (minutes < 10) {
				sbBuffer.append(years + "-" + months + "-" + days + " " + A_P_M + " " + hours + ":0" + minutes);
			} else {
				sbBuffer.append(years + "-" + months + "-" + days + " " + A_P_M + " " + hours + ":" + minutes);
			}
		} catch (Exception e) {
		}
		return sbBuffer.toString();
	}


	/**
	 * 判断给定字符串时间是否为今日
	 *
	 * @param sdate
	 * @return boolean
	 */
	public static boolean isToday(String sdate) {
		if (sdate != null && sdate.length() > 0) {
			String current_date = null;
			try {
				SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
				Calendar c = Calendar.getInstance(Locale.CHINA);
				current_date = sDateFormat.format(c.getTime());
				String s_date = sDateFormat.format(new Date(Long.parseLong(sdate)));
				if (current_date.equals(s_date)) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 判断是否是昨天
	 *
	 * @param sdate
	 * @return
	 */
	public static boolean isYesterday(String sdate) {
		if (sdate != null && sdate.length() > 0) {
			String yesterday_date = null;
			try {
				SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
				Calendar c = Calendar.getInstance(Locale.CHINA);
				yesterday_date = sDateFormat.format(new Date((c.getTimeInMillis() - 86400000)));
				String s_date = sDateFormat.format(new Date(Long.parseLong(sdate)));
				if (yesterday_date.equals(s_date)) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	//     根据字段跳转到Native功能模块

	public static final String WebUserInfoDetail = "wifi12306://moduleld/userInfoDetail/";

}
