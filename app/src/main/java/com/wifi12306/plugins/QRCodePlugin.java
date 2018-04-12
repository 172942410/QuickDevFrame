package com.wifi12306.plugins;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import com.wifi12306.bean.plugin.CordovaResCode;
import com.wifi12306.plugins.utils.CordovaContest;
import com.wifi12306.plugins.utils.CordovaUtils;
import com.wifi12306.utils.ConsUtil;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 二维码接口 插件调用 plugin
 * <br>use javascript call like:
 * <br>cordova.exec(succ, fail, 'SXQRCodePlugin', 'scanQRCode',[])
 * <br> 方法名:scanQRCode  &nbsp;&nbsp;//调起司信扫一扫
 * <br> 方法名:generateQRCode &nbsp;&nbsp;//生成二维码
 * <br> 当点击客户端取消按钮时: 传递给plugin的success方法回调参数为JSONArray字符串:
 * <br>["cancel"]
 * <br> 调起司信扫一扫 scanQRCode 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "scanResult":""              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 生成二维码 generateQRCode 当客户端请求成功后: 传递给plugin的success方法数据为Arraylist的jsonObject字符串:
 * <br> [{
 * <br>  &nbsp;&nbsp;&nbsp;  "localId":""              &nbsp;&nbsp;//不可为空
 * <br> }]
 * <br> 当回调客户端页面的Intent传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:intent is null"
 * <br> 当回调客户端页面的ArrayList<String> users传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:users is null"
 * <br>
 *
 * <br>Created by issuser on 16/5/16.
 */
public class QRCodePlugin extends BasePlugin {
    public static CallbackContext callbackContext;
    private CordovaResCode cordovaResCode;
    private Activity activity;
    private CordovaContest cordovaContest;

    private String url;
    private int widthPix;
    private String iconUrl;
//    private boolean isAddIcon;
    private String path = Environment.getExternalStorageDirectory() +"/sixin/camera/";
    private Uri imageURI;
    private String qrcodepath;
    public static boolean needHandleResult;
    private String strHttp;
    private long stringToDate;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Map<Object, Object> map = new HashMap<>();
                        map.put("localId", qrcodepath);
                    sendPluginSuccess(activity,callbackContext,map);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        cordovaResCode = new CordovaResCode();
        activity = this.cordova.getActivity();
        cordovaContest = new CordovaContest();
        if(PluginUntil.getList().contains(action)){
            if(action.equals(PluginUntil.scanQRCode)){
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    int scanType = 0;
                    for (int i = 0; i < args.length(); i++) {
                        JSONObject jsob = args.getJSONObject(i);
                        Object objres = jsob.opt("needHandleResult");
                        boolean objboo = cordovaContest.judgeEmpty(objres,2);
                        if(objboo){
                            needHandleResult = Boolean.valueOf(objres.toString());
                        }else {
                            needHandleResult = CordovaUtils.DEFAULT_BOOL_NEED_HANDLE_RESULT;
                        }
                        Object objisclose = jsob.opt("scanType");
                        if(objisclose != null && !objisclose.equals("")){
                            Object objshowpro = cordovaContest.defualt(objisclose,1);
                            if(objshowpro != null && !objshowpro.equals("")){
                                scanType = Integer.valueOf(objshowpro.toString());
                            }else {
                                scanType =0;
                            }
                        }else {
                            scanType =0;
                        }
                    }
//                    if(isCameraPermission()){
//                        Intent intent = new Intent(cordova.getActivity(), CaptureActivity.class);
//                        intent.putExtra("pluginCode","1");
//                        intent.putExtra("scanType", scanType + "");
//                        cordova.getActivity().startActivityForResult(intent,200);
//                    }else {
//                        sendPluginEnable(activity,CordovaUtils.);
//                    }
//                cordova.startActivityForResult(SXQRCodePlugin.this, intent, 200);
                    return true;
                }
            }else if (action.equals(PluginUntil.generateQRCode)) {
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    String string = null;
                    for (int i = 0; i < args.length(); i++) {
                        JSONObject jsob = args.getJSONObject(i);
                        Object objstr = jsob.opt("string");
                        if(objstr != null && !objstr.equals("")){
                            String time = objstr.toString();
                            cordovaResCode = cordovaContest.judgeNull(time,"string");
                            if(cordovaResCode.isOK){
                                boolean objstring = cordovaContest.necessary(objstr,0);
                                if(objstring){
                                    string = time;
                                }else {
                                    cordovaResCode = cordovaContest.setResultWithErrResultType(0,"string");
                                    sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                    return true;
                                }
                            }else {
                                cordovaResCode = cordovaContest.setResultWithErrResultType(1,"string");
                                sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                return true;
                            }
                        }else {
                            cordovaResCode = cordovaContest.setResultWithErrResultType(1,"string");
                            sendPluginError(activity,callbackContext,cordovaResCode.toString());
                            return true;
                        }
                        Object objres = jsob.opt("resolution");
                        if(objres != null && !objres.equals("")){
                            Object objlution = cordovaContest.defualt(objres,1);
                            if(objlution != null && !objlution.equals("")){
                                int intstr = Integer.valueOf(objlution.toString());
                                if (intstr >= 100 && intstr <= 1280) {
                                    widthPix = intstr;
                                } else {
                                    widthPix = CordovaUtils.DEFAULT_VALUE_QRCODE_RESOLUTION;
                                }
                            }else {
                                widthPix = CordovaUtils.DEFAULT_VALUE_QRCODE_RESOLUTION;
                            }
                        }else {
                            widthPix = CordovaUtils.DEFAULT_VALUE_QRCODE_RESOLUTION;
                        }
                        Object objicon = jsob.opt("iconUrl");
                        if(objicon != null && !objicon.equals("")){
                            Object objurl = cordovaContest.defualt(objicon,0);
                            if(objurl != null && !objurl.equals("")){
                                iconUrl = objurl.toString();
                                try {
                                    File file = new File(path + "SXIMG_QRCode");
                                    if (!file.exists()) {
                                        file.mkdirs();
                                    }
//                                    imageURI = UpLoadImageUtil.getImageURI(iconUrl,file);
                                    if(imageURI == null && imageURI.equals("")){
                                        //                             sendPluginError(activity,callbackContext, CordovaUtils.RESCODE_GET_QRCODE_ICON_FAILED, CordovaUtils.RESMSG_GET_QRCODE_ICON_FAILED);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }
                    byte[] bytes = new byte[0];
                    //获取系统当前时间
                    Date d = new Date();
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
                    String format = sf.format(d);
                    ContentResolver resolver = activity.getContentResolver();
                    Bitmap myBitmap = null;
                    if(imageURI != null && !imageURI.equals("")){
                        try {
//                            bytes = UpLoadImageUtil.readStream(resolver.openInputStream(Uri.parse(imageURI.toString())));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //将字节数组转换为ImageView可调用的Bitmap对象
//                        myBitmap = UpLoadImageUtil.getPicFromBytes(bytes, null);
                    }

                    stringToDate = getStringToDate(format);
                    qrcodepath = ConsUtil.SX_STRING_PLUGIN_IMG_FOLDER + "SXIMG_" + stringToDate +".png";
//                    boolean bitmap = Utils.createQRImage(activity, string, widthPix, widthPix, myBitmap, qrcodepath);
//                    if (bitmap) {
//                        Message msg = new Message();
//                        msg.what = 1;
//                        handler.handleMessage(msg);
//                    }else {
//                        sendPluginError(activity,callbackContext, CordovaUtils.RESCODE_SAVE_FILE_FAILED, CordovaUtils.RESMSG_SAVE_IMAGE_FAILED);
//                    }
                    return true;
                }
            }
        }else {
            cordovaResCode = cordovaContest.setResultWithErrResultType(2,action);
            sendPluginError(activity,callbackContext,cordovaResCode.toString());
            return true;
        }

        return super.execute(action, args, callbackContext);
    }

    /**
     * 作用：用户是否同意打开相机权限
     *
     * @return true 同意 false 拒绝
     */
    public boolean isCameraPermission() {

        try {
            Camera.open().release();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == 200){
            if(null != intent){
                if(needHandleResult){
                    String result = intent.getExtras().getString("result");//得到新Activity 关闭后返回的数据
                    Map<String, String> map = new HashMap<>();
                    map.put("scanResult", result);
                    sendPluginSucces(activity,callbackContext,map.toString());
                }
            }else {
                sendPluginCancel(activity,callbackContext, CordovaUtils.RESMSG_CANCEL_SCAN_QRCODE);
            }
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }

    /*将字符串转为时间戳*/
        public static long getStringToDate(String time) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            Date date = new Date();
            try{
                date = sdf.parse(time);
            } catch(ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                return date.getTime();
        }

}
