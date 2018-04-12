package com.wifi12306.plugins;

import android.app.Activity;

import com.wifi12306.bean.plugin.ConfigBean;
import com.wifi12306.bean.plugin.CordovaResCode;
import com.wifi12306.plugins.utils.CordovaContest;
import com.wifi12306.plugins.utils.CordovaUtils;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;


/**
 * 配置api权限 插件调用 plugin
 * <br>use javascript call like:
 * <br>cordova.exec(succ, fail, 'SXConfigPlugin', 'config',[])
 * <br> 方法名:config  &nbsp;&nbsp;//为检测api是否有权限
 * <br> 当点击客户端取消按钮时: 传递给plugin的success方法回调参数为JSONArray字符串:
 * <br>["cancel"]
 * <br> 单选 getContacts 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
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
public class ConfigPlugin extends BasePlugin {

    private CallbackContext callbackContext;
    private CordovaContest cordovaContest;
    private Activity activity;
    private CordovaResCode cordovaResCode;
    private ConfigBean sxConfigBean = new ConfigBean();

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        activity = this.cordova.getActivity();
        if(PluginUntil.getList().contains(action)){
            if(action.equals(PluginUntil.config)){
                cordovaContest = new CordovaContest();
                cordovaResCode = cordovaContest.initWithDictionaryParameters(args);

                    sendPluginError(activity,callbackContext, CordovaUtils.RESCODE_LOCAL_WEB_PAGE,CordovaUtils.RESMSG_NO_NEED_CALL_CONFIG_API);
                    return true;
            }
        }else {
            cordovaResCode = cordovaContest.setResultWithErrResultType(2,"");
            sendPluginError(activity,callbackContext,cordovaResCode.toString());
            return true;
        }
        return false;
    }

}
