package com.wifi12306.plugins;

import android.app.Activity;

import com.wifi12306.bean.plugin.CordovaResCode;
import com.wifi12306.plugins.utils.CordovaContest;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * 13	其他附加接口 插件调用 plugin	获取司信客户端版本号接口
 * <br>use javascript call like:
 * <br>cordova.exec(succ, fail, 'SXAdditionsPlugin', 'getAppVerInfo',[])
 * <br> 方法名:getAppVerInfo  &nbsp;&nbsp;//获取客户端的版本号
 * <br> 当点击客户端取消按钮时: 传递给plugin的success方法回调参数为JSONArray字符串:
 * <br>["cancel"]
 * <br> 单选 getAppVerInfo 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "version":"1.0" //版本号             &nbsp;&nbsp;//不可为空
 * <br> 当回调客户端页面的Intent传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:intent is null"
 * <br> 当回调客户端页面的ArrayList<String> users传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:users is null"
 * <br>
 *
 * <br>Created by issuser on 16/5/16.
 */
public class AdditionsPlugin extends BasePlugin {
    private CallbackContext callbackContext;
    private Activity activity;
    private CordovaResCode cordovaResCode;
    private CordovaContest cordovaContest;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        activity = this.cordova.getActivity();
        cordovaResCode = new CordovaResCode();
        cordovaContest = new CordovaContest();
        isNetworkAvailable(activity,callbackContext);
        if(PluginUntil.getList().contains(action)){
            if(action.equals(PluginUntil.getAppVersion)){
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    if(cordovaResCode.isOK){
//                        String AppVersion = PhoneInfo.getAppVersion(activity);
                        Map<String, Object> map = new HashMap<>();
//                        map.put("version",AppVersion);
                        sendPluginSucces(activity,callbackContext,map.toString());
                    }else {
                        sendPluginError(activity,callbackContext,cordovaResCode.toString());
                    }
                }
            }
        }else {
            cordovaResCode = cordovaContest.setResultWithErrResultType(2,action);
            sendPluginError(activity,callbackContext,cordovaResCode.toString());
            return true;
        }
        return super.execute(action, args, callbackContext);
    }
}
