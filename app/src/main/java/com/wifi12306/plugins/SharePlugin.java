package com.wifi12306.plugins;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.wifi12306.bean.plugin.CordovaResCode;
import com.wifi12306.plugins.utils.CordovaContest;
import com.wifi12306.plugins.utils.CordovaUtils;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 分享接口 插件调用 plugin
 * <br>use javascript call like:
 * <br>cordova.exec(succ, fail, 'SXSharePlugin', 'share',[])
 * <br> 方法名:share  &nbsp;&nbsp;//默认内容分享
 * <br> 方法名:shareCustomContent &nbsp;&nbsp;//自定义内容
 * <br> 方法名:onMenuShareCustomContent  &nbsp;&nbsp;//获取"分享"按钮点击状态及开启自定义分享
 * <br> 方法名:closeMenuShareCustomContent &nbsp;&nbsp;//关闭自定义分享
 * <br> 当点击客户端取消按钮时: 传递给plugin的success方法回调参数为JSONArray字符串:
 * <br>["cancel"]
 * <br> 默认内容分享 share 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 自定义内容 shareCustomContent 当客户端请求成功后: 传递给plugin的success方法数据为Arraylist的jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 获取"分享"按钮点击状态及开启自定义分享 onMenuShareCustomContent 当客户端请求成功后: 传递给plugin的success方法数据为Arraylist的jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 关闭自定义分享 closeMenuShareCustomContent 当客户端请求成功后: 传递给plugin的success方法数据为Arraylist的jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 当回调客户端页面的Intent传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:intent is null"
 * <br> 当回调客户端页面的ArrayList<String> users传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:users is null"
 * <br>
 *
 * <br>Created by issuser on 16/5/16.
 */
public class SharePlugin extends BasePlugin {
    private static final String TAG = "SXSharePlugin";
    public static CallbackContext callbackContext;
    private CordovaContest cordovaContest;
    private CordovaResCode cordovaResCode;
    private Activity activity;
    private String desc;
    private String title;
    private String dataUrl;
    private String imgUrl;
    private String type;
    private String link;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        activity = this.cordova.getActivity();
        cordovaContest = new CordovaContest();
        cordovaResCode = new CordovaResCode();
        if(PluginUntil.getList().contains(action)){
            if(action.equals(PluginUntil.share)){
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
//                    Intent intent = new Intent(activity, ContactActivity.class);
//                    intent.putExtra("tab_type", ConsUtil.d_tab_type_f2);
//                cordova.getActivity().startActivityForResult(intent,200);
//                    cordova.startActivityForResult(this,intent,300);
                    return true;
                }
            }else if (action.equals(PluginUntil.shareCustomContent)){
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    cordovaResCode = cordovaContest.initWithDictionaryParameters(args);
                    if(cordovaResCode.isOK){
                        for (int i = 0; i < args.length(); i++) {
                            JSONObject json = args.getJSONObject(i);
//                        cordovaContest.defualt(json,0);
                            Object title = json.opt("title");
                            if(title != null && !title.equals("")){
                                Object descObj = cordovaContest.defualt(title,0);
                                String descStr = descObj.toString();
                            }else {
                                Log.e(TAG,"为空时  : " + "为空");
                            }
                            Object desc = json.opt("desc");
                            if(desc != null && !desc.equals("")){
                                Object descObj = cordovaContest.defualt(desc,0);
                                String descStr = descObj.toString();
                            }else {
                                Log.e(TAG,"为空时  : " + "为空");
                            }
                            Object link = json.opt("link");
                            if(link != null && !link.equals("")){
                                Object descObj = cordovaContest.defualt(link,0);
                                String descStr = descObj.toString();
                            }else {
                                Log.e(TAG,"为空时  : " + "为空");
                            }
                            Object imgUrl = json.opt("imgUrl");
                            if(imgUrl != null && !imgUrl.equals("")){
                                Object descObj = cordovaContest.defualt(imgUrl,0);
                                String descStr = descObj.toString();
                            }else {
                                Log.e(TAG,"为空时  : " + "为空");
                            }
                            Object type = json.opt("type");
                            if(type != null && !type.equals("")){
                                Object descObj = cordovaContest.defualt(type,1);
                                String descStr = descObj.toString();
                                if (descStr.equals("1") || descStr.equals("2")) {
                                    dataUrl = json.getString("dataUrl");
                                    cordovaResCode = cordovaContest.judgeString(dataUrl);
                                    if (cordovaResCode.isOK) {
//                                        Intent intent = new Intent(activity, ContactActivity.class);
//                                        intent.putExtra("type", "plugin");
//                                        intent.putExtra("tab_type", ConsUtil.d_tab_type_f2);
//                                        cordova.startActivityForResult(SXSharePlugin.this,intent,300);
                                        return true;
                                    } else {
                                        sendPluginError(activity, callbackContext, cordovaResCode.toString());
                                        return true;
                                    }
                                } else {
//                                    Intent intent = new Intent(activity, ContactActivity.class);
//                                    intent.putExtra("tab_type", ConsUtil.d_tab_type_f2);
//                                    cordova.getActivity().startActivityForResult(intent,300);
                                }
                            }else {
                                Log.e(TAG,"为空时  : " + "为空");
//                                Intent intent = new Intent(activity, ContactActivity.class);
//                                intent.putExtra("tab_type", ConsUtil.d_tab_type_f2);
//                                cordova.startActivityForResult(SXSharePlugin.this,intent,300);
                                return true;
                            }
                        }
                    }else {
                        sendPluginError(activity,callbackContext,cordovaResCode.toString());
                    }
                }
            }else if(action.equals(PluginUntil.onMenuShareCustomContent)){
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    cordovaResCode = cordovaContest.initWithDictionaryParameters(args);
                    if(cordovaResCode.isOK){
                        for (int i = 0; i < args.length(); i++) {
                            JSONObject json = args.getJSONObject(i);
                            Object title = json.opt("title");
                            if(title != null && !title.equals("")){
                                Object descObj = cordovaContest.defualt(title,0);
                                String descStr = descObj.toString();
                            }else {
                                Log.e(TAG,"为空时  : " + "为空");
                            }
                            Object desc = json.opt("desc");
                            if(desc != null && !desc.equals("")){
                                Object descObj = cordovaContest.defualt(desc,0);
                                String descStr = descObj.toString();
                            }else {
                                Log.e(TAG,"为空时  : " + "为空");
                            }
                            Object link = json.opt("link");
                            if(link != null && !link.equals("")){
                                Object descObj = cordovaContest.defualt(link,0);
                                String descStr = descObj.toString();
                            }else {
                                Log.e(TAG,"为空时  : " + "为空");
                            }
                            Object imgUrl = json.opt("imgUrl");
                            if(imgUrl != null && !imgUrl.equals("")){
                                Object descObj = cordovaContest.defualt(imgUrl,0);
                                String descStr = descObj.toString();
                            }else {
                                Log.e(TAG,"为空时  : " + "为空");
                            }
                            Object type = json.opt("type");
                            if(type != null && !type.equals("")){
                                Object descObj = cordovaContest.defualt(type,1);
                                String descStr = descObj.toString();
                                if (descStr.equals("1") || descStr.equals("2")) {
                                    dataUrl = json.getString("dataUrl");
                                    cordovaResCode = cordovaContest.judgeString(dataUrl);
                                    if (cordovaResCode.isOK) {
                                        sendPluginEnable(activity,callbackContext, CordovaUtils.RESMSG_ENABLE_CUSTIOM_SHARE);
                                        return true;
                                    } else {
                                        sendPluginError(activity, callbackContext, cordovaResCode.toString());
                                        return true;
                                    }
                                } else {
                                    sendPluginEnable(activity,callbackContext, CordovaUtils.RESMSG_ENABLE_CUSTIOM_SHARE);
                                }
                            }else {
                                Log.e(TAG,"为空时  : " + "为空");
                                sendPluginEnable(activity,callbackContext, CordovaUtils.RESMSG_ENABLE_CUSTIOM_SHARE);
                                return true;
                            }
                        }

                        return true;
                    }else {
                        sendPluginError(activity,callbackContext,cordovaResCode.toString());
                    }
                }
            }else if (action.equals(PluginUntil.closeMenuShareCustomContent)){
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    sendPluginSucces(activity,callbackContext);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == 300){
            if(intent == null){
                SharePlugin.sendPluginCancel(activity,callbackContext,CordovaUtils.RESMSG_CANCLE_SHARE);
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }
}

