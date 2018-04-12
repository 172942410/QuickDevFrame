package com.wifi12306.plugins;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.wifi12306.bean.plugin.CordovaResCode;
import com.wifi12306.plugins.utils.CordovaContest;
import com.wifi12306.plugins.utils.CordovaUtils;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by issuser on 16/6/1.
 */
public class BasePlugin extends CordovaPlugin {
    private static final String TAG = "SXPlugin";
    private CordovaContest cordovaContest = new CordovaContest();
    private static CordovaResCode cordovaResCode = new CordovaResCode();

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        return super.execute(action, args, callbackContext);
    }

    public boolean isNetworkAvailable(Activity activity, CallbackContext callbackContext){
        boolean netWork = CordovaUtils.isNetworkAvailable(activity);
        if(!netWork){
            sendPluginError(activity,callbackContext, CordovaUtils.RESCODE_NO_NETWORK, CordovaUtils.RESMSG_NO_NETWORK);
            return false;
        }else {
            return true;
        }
    }

    public boolean getAccessAPIpermissions(Activity activity, CallbackContext callbackContext){
//            sendPluginError(activity,callbackContext, CordovaUtils.RESCODE_NOT_AUTHORIZED, CordovaUtils.RESMSG_NOT_AUTHORIZED);
            return true;
    }

    public static void sendPluginSuccess(Activity activity, CallbackContext callbackContext, Map<Object, Object> map){
        sendPlugin(activity, callbackContext, map, true);
    }

    public static void sendPluginSucces(Activity activity, CallbackContext callbackContext) {
        cordovaResCode.resCode = CordovaUtils.ERRCODE_SUCCESS;
        cordovaResCode.resMsg = CordovaUtils.ERRMSG_SUCCESS;
        sendPlugin(activity,callbackContext,cordovaResCode.toString(),true);
    }

    public static void sendPluginSucces(Activity activity, CallbackContext callbackContext, String string){
        sendPlugin(activity,callbackContext,string,true);
        Log.i("BasePlugin","sendPluginSucces...");
    }

    public static void sendPluginEnable(Activity activity, CallbackContext callbackContext, String resMsg){
        cordovaResCode.resCode = CordovaUtils.RESCODE_ENABLE;
        cordovaResCode.resMsg = resMsg;
        sendPlugin(activity,callbackContext,cordovaResCode.toString(),true);
    }

    public static void sendPluginTrigger(Activity activity, CallbackContext callbackContext, String resMsg){
        cordovaResCode.resCode = CordovaUtils.RESCODE_TRIGGER;
        cordovaResCode.resMsg = resMsg;
        sendPlugin(activity,callbackContext,cordovaResCode.toString(),true);
    }

    public static void sendPluginError(Activity activity, CallbackContext callbackContext, String resCode, String resMsg) {
        cordovaResCode.resCode = resCode;
        cordovaResCode.resMsg = resMsg;
        Log.i("BasePlugin","sendPluginError...");
        sendPlugin(activity, callbackContext, cordovaResCode.toString(), false);
    }

    public static void sendPluginError(Activity activity, CallbackContext callbackContext, String string) {
        sendPlugin(activity, callbackContext, string, false);
    }

    public static void sendPluginError(Activity activity, CallbackContext callbackContext, Map<Object, Object> map){
        sendPlugin(activity, callbackContext, map, false);
    }

    public static void sendPluginCancel(Activity activity, CallbackContext callbackContext, String resMsg){
        cordovaResCode.resCode = CordovaUtils.RESCODE_CANCEL;
        cordovaResCode.resMsg = resMsg;
        sendPlugin(activity, callbackContext, cordovaResCode.toString(), false);
    }

    public static void sendPluginRefuse(Activity activity, CallbackContext callbackContext, String resMsg){
        cordovaResCode.resCode = CordovaUtils.RESCODE_REFUSE;
        cordovaResCode.resMsg = resMsg;
        sendPlugin(activity, callbackContext, cordovaResCode.toString(), false);
    }

    private static void sendPlugin(Activity activity, CallbackContext callbackContext, String string, boolean success){
//        try {
//            JSONObject jsonObject = new JSONObject(string);
            if(success){
                callbackContext.success(string);
            }else {
                callbackContext.error(string);
            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }

    private static void sendPlugin(Activity activity, CallbackContext callbackContext, Map<Object,Object> map, boolean success){
        JSONObject jsonObject = new JSONObject(map);
        Log.e(TAG,"sendPlugin,success:"+success+",jsonObject:"+jsonObject);
        if(success){
            callbackContext.success(jsonObject);
        }else {
            callbackContext.error(jsonObject);
        }

    }

    //弹出框
    public static void showDig(Activity activity, String content){
//        DialogSingleAsk dialog = new DialogSingleAsk(activity, null);
//        dialog.setTitleText("Debug模式");
//        dialog.setContentText(content);
//        dialog.setOKText("确定");
//        dialog.show();
        new AlertDialog.Builder(activity).setTitle("Debug模式")//设置对话框标题
                .setMessage(content)//设置显示的内容
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        // TODO Auto-generated method stub
                        dialog.dismiss();//对话框关闭。
                    }
                }).show();//在按键响应事件中显示此对话框

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
