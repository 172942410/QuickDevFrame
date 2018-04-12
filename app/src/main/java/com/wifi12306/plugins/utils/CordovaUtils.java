package com.wifi12306.plugins.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by issuser on 16/5/11.
 */
public class CordovaUtils {

    public static String SX_VALUE_PLUGIN_SDK_VERSION="1.0.0";                       //客户端Plugin SDK版本号

    public static String X_STRING_FILE_NAME_FORMAT = "yyyyMMddHHmmss";              //存储文件命名格式

    public static String ERRCODE_SUCCESS = "0";                                     //回调success消息代码
    public static String RESCODE_ENABLE = "01";                                     //回调enable消息代码
    public static String RESCODE_TRIGGER = "02";                                    //回调trigger消息代码
    public static String RESCODE_CANCEL = "03";                                     //回调cancel消息代码
    public static String RESCODE_REFUSE = "04";                                     //回调refuse消息代码

    public static String RESCODE_NOT_AUTHORIZED = "40000";                          //未通过api权限验证错误代码
//    public static String RESCODE_NOT_AUTHORIZED_FOR_USERINFO = "40001";             //未通过获取用户信息的api权限验证错误代码
    public static String RESCODE_NOT_FOUND_PLUGIN_METHOD = "40001";                 //无法找到客户端插件方法错误代码
    public static String RESCODE_NEED_WAIT_CONFIGREQUEST_CALLBACK = "40002";        //configRequest接口还未配置成功，需要等待成功回调，暂时无法调用startRequest接口
    public static String RESCODE_LOCAL_WEB_PAGE = "40003";                          //当前访问网页为本地html,相关接口无法使用

    public static String RESCODE_NO_PARAMETER="40100";                              //无参数错误代码
    public static String RESCODE_INVALID_PARAMETER_FORMAT="40101";                  //无效的参数格式错误代码
    public static String RESCODE_REQUIRED_PARAMETER_MISSING="40102";                //必要参数缺失错误代码

    public static String RESCODE_NO_NETWORK = "41000";                              //无网络错误代码
    public static String RESCODE_REQUEST_FAILED = "41001";                          //请求失败错误代码
    public static String RESCODE_INVALID_APPID = "40102";                           //无效的appId错误代码
    public static String RESCODE_INVALID_DOMAIN_NAME = "40103";                     //无效的访问域名错误代码
    public static String RESCODE_INVALID_SIGNATURE = "40104";                       //无效的签名错误代码
    public static String RESCODE_AUTHENTICATION_SERVER_ERROR = "40105";             //api权限验证服务器系统出错错误代码
    public static String RESCODE_SAVE_FILE_FAILED = "41006";                        //保存文件失败错误代码
    public static String RESCODE_TOO_MANY_IMAGES = "41007";                         //批量上传和下载图片数量超过最大数量错误代码
    public static String RESCODE_UPLOAD_FILE_FAILED = "41008";                      //上传文件失败错误代码
    public static String RESCODE_DOWNLOAD_FILE_FAILED = "41009";                    //下载文件失败错误代码
    public static String RESCODE_INVALID_LOCALID = "41010";                         //无效的localId
    public static String RESCODE_NO_RECORDING = "41011";                            //没有可以停止的录音错误代码
    public static String RESCODE_INITIALIZE_PLAYER_FAILED = "41012";                //初始化播放器失败错误代码
    public static String RESCODE_NO_VOICE_PLAYING = "41013";                        //没有正在播放的语音错误代码
    public static String RESCODE_LOCATION_SERVICES_DISABLED = "41014";              //定位服务不可用错误代码
    public static String RESCODE_INVALID_LOCATION = "41015";                        //无效的地理位置错误代码
    public static String RESCODE_SYSTEM_GET_LOCATION_FAILED = "41016";              //系统获取地理位置失败错误代码
    public static String RESCODE_SYSTEM_GET_ADDRESS_FAILED = "41017";               //系统获取地址失败错误代码
    public static String RESCODE_SHAKE_DISABLED = "41018";                          //当前设备不支持摇一摇错误代码
    public static String RESCODE_ALL_MENUID_INVALID = "41019";                      //menuList数组中所有menuId无效
    public static String RESCODE_INVALID_INSTITUTIONID = "41020";                   //无效的institutionId
    //    public static String RESCODE_NO_RECENT_CONTACTS = "41013";                //无最近联系人
    public static String RESCODE_INVALID_USERID = "41021";                          //无效的userId
    public static String RESCODE_INVALID_ORGID = "41022";                           //无效的orgId
    public static String RESCODE_CACHE_FILE_FAILED = "41023";                       //缓存文件失败错误代码
    public static String RESCODE_READ_CACHE_FAILED = "41024";                       //读取缓存失败错误代码


    public static String ERRMSG_SUCCESS = "OK";

    public static String RESMSG_ENABLE_CUSTIOM_SHARE = "Custom share enabled";     //启用自定义共享
    public static String RESMSG_ENABLE_RECORD = "Recording success and listen to the recording end enabled";//录音成功和录音结束的启用
    public static String RESMSG_ENABLE_PLAY_VOICE = "Playing success and listen to the playing end enabled";//录音成功和播放录音结束的启用
//    public static String RESMSG_ENABLE_VOICE_RECORD_END = "Listen to the recording end enabled"; //录音结束启用
//    public static String RESMSG_NO_RECORDING = "No recording can stop";
//    public static String RESMSG_ENABLE_VOICE_PLAY_END = "Listen to the playing end enabled";     //录音播放结束启用
    public static String RESMSG_ENABLE_SHAKE = "Shake enabled";                                  //摇一摇启用

    public static String RESMSG_TRIGGER_SHARE = "User has been tappped on share button";         //用户一直在tappped分享按钮
    public static String RESMSG_TRIGGER_SHAKE = "Shake has been completed";                      //摇一摇已经完成

    public static String RESMSG_CANCLE_REQUEST = "Network request has been canceled";            //网络请求取消了
    public static String RESMSG_CANCLE_SHARE = "User has been canceled share";                   //用户取消了分享
    public static String RESMSG_CANCEL_CHOOSE_IMAGE = "User have been canceled choose image";    //用户已取消选择图像
    public static String RESMSG_CANCEL_UPLOAD_IMAGE = "User has been canceled upload image";     //用户已取消下载
    public static String RESMSG_CANCEL_UPLOAD_IMAGES = "User has been canceled upload images";    //用户已取消批量下载
    public static String RESMSG_CANCEL_DOWNLOAD_IMAGE = "User has been canceled download image";    //用户已取消上传
    public static String RESMSG_CANCEL_DOWNLOAD_IMAGES = "User has been canceled download images";    //用户已取消批量上传
    public static String RESMSG_CANCEL_UPLOAD_VOICE = "User has been canceled upload voice";    //用户已取消上传录音
    public static String RESMSG_CANCEL_DOWNLOAD_VOICE = "User has been canceled download voice";    //用户已取消下载录音
    public static String RESMSG_CANCEL_ALERT_MESSAGE = "User has been tapped on cancel button";    //用户已取消选择图像
    public static String RESMSG_CANCEL_SHOW_LOADING = "User has been canceled show loading";
    public static String RESMSG_CANCEL_SCAN_QRCODE = "User has been canceled scan QR code";      //用户已取消扫一扫
    public static String RESMSG_CANCEL_CHOOSE_CONTACTS = "User has been canceled choose contacts";//用户已取消选择联系人
    public static String RESMSG_CANCEL_CHOOSE_ORGANIZATION = "User has been canceled choose organization";//用户取消了选择组织

    public static String RESMSG_REFUSE_MICROPHONE = "User has been refused to allow access to microphone";//用户一直拒绝允许访问麦克风
    public static String RESMSG_REFUSE_LOCATIONE = "User has been refused to allow access to location serves";//用户一直拒绝允许访问位置服务

    public static String RESMSG_NOT_AUTHORIZED = "No access to the JS-SDK api";                   //没有访问JS-SDK api
//    public static String RESMSG_NOT_AUTHORIZED_FOR_USERINFO = "No access to the user info JS-SDK api";//没有访问用户信息JS-SDK api
    public static String RESMSG_NOT_FOUND_PLUGIN_METHOD = "Cannot find the plugin method";            //缺少需要的参数
    public static String RESMSG_NO_NEED_CALL_CONFIG_API = "Current web page is local web page, no need to call ‘config’ interface";            //缺少需要的参数

    public static String RESMSG_NO_PARAMETER = "No parameters";                                       //没有参数
    public static String RESMSG_INVALID_PARAMETER_FORMAT = "Invalid parameter format";                //无效的参数格式
    public static String RESMSG_REQUIRED_PARAMETER_MISSING = "Required parameter missing";            //缺少需要的参数

    public static String RESMSG_NO_NETWORK = "No network";                                            //没有网络
    public static String RESMSG_REQUEST_FAILED = "Request failed";                                    //请求失败
    public static String RESMSG_INVALID_APPID = "Invalid appId";
    public static String RESMSG_INVALID_DOMAIN_NAME = "Invalid domain name";
    public static String RESMSG_INVALID_SIGNATURE = "Invalid signature";
    public static String RESMSG_AUTHENTICATION_SERVER_ERROR = "Authentication server error";
    public static String RESMSG_SAVE_IMAGE_FAILED = "Save image failed";                              //保存图片失败
    public static String ESMSG_TOO_MANY_IMAGES = "Images number is more than the maximum limit";
    public static String RESMSG_UPLOAD_IMAGE_FAILED = "Upload image failed";                          //上传图片失败
    public static String RESMSG_DOWNLOAD_IMAGE_FAILED = "Download image failed";                      //下载图片失败
    public static String RESMSG_SAVE_VOICE_FAILED = "Save voice failed";                              //保存录音失败
    public static String RESMSG_UPLOAD_VOICE_FAILED = "Upload voice failed";                          //上传录音失败
    public static String RESMSG_DOWNLOAD_VOICE_FAILED = "Download voice failed";                      //下载录音失败
    public static String RESMSG_INVALID_LOCALID = "Invalid localId";
    public static String RESMSG_NO_RECORDING = "No recording can stop";                               //下载录音失败
    public static String RESMSG_INITIALIZE_PLAYER_FAILED = "Initialize player failed";
    public static String RESMSG_READ_DATA_STREAM_FAILED = "Read data stream failed";
    public static String RESMSG_NO_VOICE_PAUSE = "Voice can not pause";
    public static String RESMSG_NO_VOICE_STOP = "Voice can not stop";
    public static String RESMSG_LOCATION_SERVICES_DISABLED = "Location services not available";       //位置服务不可用
    public static String RESMSG_INVALID_LOCATION = "Invalid location";                                //无效的位置
    public static String RESMSG_SYSTEM_GET_LOCATION_FAILED = "System get location failed";            //系统得到位置失败
    public static String RESMSG_SYSTEM_GET_ADDRESS_FAILED = "System get address failed";              //获取系统地址失败
    public static String RESMSG_SHAKE_DISABLED = "The current device does not support a shake";
    public static String RESMSG_GET_QRCODE_ICON_FAILED = "Get QRCode icon failed";                    //得到二维码图标失败
    public static String RESMSG_ALL_MENUID_INVALID = "The ‘menuList’ has no available menuId";
    public static String RESMSG_INVALID_INSTITUTIONID = "Invalid institutionId";
    public static String RESMSG_INVALID_USERID = "Invalid userId";
    public static String RESMSG_INVALID_ORGID = "Invalid orgId";
    public static String RESMSG_CACHE_IMAGE_FAILED = "Cache image failed";
    public static String RESMSG_CACHE_VOICE_FAILED = "Cache voice failed";
    public static String RESMSG_READ_CACHE_DATA_FAILED = "Data cache not found";
    public static String RESMSG_READ_CACHE_IMAGE_FAILED = "Image cache not found";
    public static String RESMSG_READ_CACHE_VOICE_FAILED = "Voice cache not found";

    public static int DEFAULT_VALUE_TIMEOUT_INTERVAL = 20;                                       //默认网络请求超时时间
    public static int DEFAULT_VALUE_SCALE = 0;                                                   //默认地图缩放级别
    public static int DEFAULT_VALUE_IMG_COUNT = 9;                                               //默认图片选取数量
    public static int DEFAULT_VALUE_RECORD_TIME = 60;                                            //默认录音时长
    public static int DEFAULT_VALUE_QRCODE_RESOLUTION = 400;                                     //默认二维码的生成分辨率

    public static boolean DEFAULT_BOOL_DEBUG = false;                                             //默认不开启调试模式
    public static boolean DEFAULT_BOOL_GET_USERINFO = false;                                      //默认不调用获取用户信息接口
    public static boolean DEFAULT_BOOL_VALIDATES_SECURE_CERTIFICATE = false;                       //默认开启验证网络请求安全证书
    public static boolean DEFAULT_BOOL_SHOW_LOADING = true;                                       //默认开启网络请求时显示loading
    public static boolean DEFAULT_BOOL_USE_STATIC_PARAMS = true;                                  //默认网络请求时使用静态参数
    public static boolean DEFAULT_BOOL_SHOW_PROGRESS_TIPS = true;                                 //默认下载上传时显示进度条
    public static boolean DEFAULT_BOOL_GET_ADDRESS = false;                                       //默认获取位置时不获取地址
    public static boolean DEFAULT_BOOL_NEED_HANDLE_RESULT = true;                                 //默认扫码结果由司信处理
    public static boolean DEFAULT_BOOL_ADD_ICON = false;                                          //默认不给二维码添加icon
    public static boolean DEFAULT_BOOL_NEED_COMPLETION_HANDLER = false;                           //默认关闭窗口后不做其他处理
    public static boolean DEFAULT_BOOL_GET_DETALL_USER_INFO = false;                              //默认不获取当前用户信息

    public static int MIN_VALUE_TIMEOUT_INTERVAL = 5;                                             //最小网络请求超时时间
    public static int MAX_VALUE_TIMEOUT_INTERVAL = 120;                                           //最大网络请求超时时间
    public static int MAX_VALUE_IMG_COUNT = 9;                                                    //最大图片选取数量
    public static int MAX_VALUE_RECORD_TIME = 60;                                                 //最大录音时长
    public static int MAX_VALUE_COMPRESSED_RESOLUTION = 1280;                                     //最大压缩图片的分辨率
    public static int MAX_VALUE_COMPRESSED_AVATAR_RESOLUTION = 360;                               //最大头像压缩图片的分辨率
    public static int MAX_VALUE_SCALE = 30;                                                       //最大地图缩放级别

    public static String SX_VALUE_DISTANCE_FILTER = "10";                                       //CLLocationManager的distanceFilter值
    public static String SX_VALUE_SCALE_MULTIPLE = "20";                                        //用于地图缩放级别
    public static String SX_VALUE_QRICON_BORDER_WIDTH = "3";                                    //二维码icon和白色圆角框之间的间隙
    public static int SX_VALUE_GET_QRICON_TIMEOUTINTERVAL = 8;                                  //下载二维码icon超时时间
    public static String SX_VALUE_SHARE_ITEM_TAG = "1";                                         //分享功能按钮tag
    public static String SX_VALUE_VIEW_INSTITUTION_ITEM_TAG = "11";                             //查看机构号功能按钮tag

    public static String DEFAULT_TYPE_REQUEST_METHOD = "ITTRequestMethodPost";                  //默认请求方法
    public static String DEFAULT_TYPE_SHARE = "SXShareTypeLink";                                //默认分享类型
    public static String DEFAULT_TYPE_OPTION_SIZE = "SXOptionsSizeTypeOriginalAndCompressed";   //默认图片质量类型
    public static String DEFAULT_TYPE_OPTION_SOURCE = "SXOptionsSourceTypeCameraAndAlbum";      //默认图片来源类似
    public static String DEFAULT_TYPE_COORDINATE = "SXCoordinateTypeWGS84";                     //默认获取地理位置的坐标类型
    public static String DEFAULT_TYPE_CODE_SCAN = "SXCodeScanTypeQRCodeAndBarCode";             //默认扫码类型
    public static String DEFAULT_TYPE_CONTACTS_SELECT = "SXContactsSelectTypeMultiselect";      //默认联系人选择方式

    public static String SX_PLUGIN_IMAGE_NAME_FORMAT = "SXIMG_";                                //插件图片命名格式
    public static String SX_PLUGIN_VOICE_NAME_FORMAT = "SXVOICE_";                              //插件录音文件命名格式

    //    public static String SX_PLUGIN_IMAGE_NAME_INDEX_FORMAT(X, Y)     [NSString stringWithFormat:@"%@_%lu", X, Y]   //插件图片加下标命名格式
    public static String SX_PLUGIN_COMPRESSED_IMAGE_NAME_FORMAT = "_compressed";                //插件压缩图片命名格式
//    public static String SX_RESMSG_REASION_SHOW_FORMAT(X, Y)         [NSString stringWithFormat:@"%@ (Reason:%@)", X, Y] //错误消息加显示原因格式
//    public static String SX_RESMSG_ERRKEY_SHOW_FORMAT(X, Y)          [NSString stringWithFormat:@"%@ (error key:'%@')", X, Y]  //错误消息加显示错误字段名格式

    public static String Plugin_Share = "1"; //分享
    public static String Plugin_Collection = "2"; //收藏
    public static String Plugin_Email = "3"; //邮件
    public static String Plugin_Browser = "4"; //浏览器

    /**
     * 判断当前是否网络连接
     * 网络状态
     * @param context
     * @return 状态码
     */
    public static String isConnected(Activity context) {
        String stateCode = null;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnectedOrConnecting()) {
            switch (ni.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    stateCode = "Wifi";
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    switch (ni.getSubtype()) {
                        case TelephonyManager.NETWORK_TYPE_GPRS: //联通2g
                        case TelephonyManager.NETWORK_TYPE_CDMA: //电信2g
                        case TelephonyManager.NETWORK_TYPE_EDGE: //移动2g
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                        case TelephonyManager. NETWORK_TYPE_IDEN:
                            stateCode = "2G";
                            break;
                        case TelephonyManager.NETWORK_TYPE_EVDO_A: //电信3g
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        case TelephonyManager.NETWORK_TYPE_EHRPD:
                        case TelephonyManager.NETWORK_TYPE_HSPAP:
                            stateCode = "3G";
                            break;
                        case TelephonyManager.NETWORK_TYPE_LTE:
                            stateCode = "4G";
                            break;
                        default:
                            stateCode = "无网络连接";
                    }
                    break;
                default:
                    stateCode = "无网络连接";
            }

        }
        return stateCode;
    }

    /**
     * 获取手机系统版本
     * @return
     */
    public static String GetSystemVersion(){
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机设备机型
     * @return
     */
    public static String GetDeviceModel(){
        return android.os.Build.MODEL;
    }

    /**
     * 获取设备唯一标识
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String GetUniqueID(Activity context){
        TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return TelephonyMgr.getDeviceId();
    }

    /**
     * 检查当前网络是否可用
     *
     * @return
     */

    public static boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }
        }
        return false;
    }



    public static String getApplicationName(Context activity) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = activity.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(activity.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName =
                (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }

    /**
     *  返回true 表示可以使用  返回false表示不可以使用
     */
    public static boolean cameraIsCanUse() {
        boolean isCanUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            Camera.Parameters mParameters = mCamera.getParameters(); //针对魅族手机
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            isCanUse = false;
        }

        if (mCamera != null) {
            try {
                mCamera.release();
            } catch (Exception e) {
                e.printStackTrace();
                return isCanUse;
            }
        }
        return isCanUse;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        联通：130、131、132、152、155、156、185、186
        电信：133、153、180、189、（1349卫通）
        总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
        */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    public static boolean isValidEmail(String mail) {
        Pattern pattern = Pattern
                .compile("^[A-Za-z0-9][\\w\\._]*[a-zA-Z0-9]+@[A-Za-z0-9-_]+\\.([A-Za-z]{2,4})");
        Matcher mc = pattern.matcher(mail);
        return mc.matches();
    }

    //判断email格式是否正确

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();

    }

}

