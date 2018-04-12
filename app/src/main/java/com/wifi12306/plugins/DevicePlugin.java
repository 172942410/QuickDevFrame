package com.wifi12306.plugins;

import android.app.Activity;

import com.wifi12306.bean.plugin.CordovaResCode;
import com.wifi12306.plugins.utils.CordovaContest;
import com.wifi12306.plugins.utils.CordovaUtils;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * 12	设备信息 插件调用 plugin
 * <br>use javascript call like:
 * <br>cordova.exec(succ, fail, 'SXDevicePlugin', 'getDeviceNetworkType',[])
 * <br> 方法名:getDeviceNetworkType  &nbsp;&nbsp;//为获取网络状态
 * <br> 方法名:getDeviceSystemType &nbsp;&nbsp;//为获取系统类型
 * <br> 方法名:getDeviceSystemVersion &nbsp;&nbsp;//为获取系统版本
 * <br> 方法名:getDeviceModel &nbsp;&nbsp;//为获取设备机型
 * <br> 方法名:getDeviceUniqueID &nbsp;&nbsp;//为获取设备唯一标识
 * <br> 方法名:getDeviceInfo &nbsp;&nbsp;//为获取设备信息
 * <br> 当点击客户端取消按钮时: 传递给plugin的success方法回调参数为JSONArray字符串:
 * <br>["cancel"]
 * <br> 获取网络状态 getDeviceNetworkType 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "networkType":"wifi"              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 为获取系统类型 getDeviceSystemType 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "sysType":"android"              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 为获取系统版本 getDeviceSystemVersion 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "sysVersion":"4.2.2"              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 为获取设备机型 getDeviceModel 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "model":"华为P8 4.2.2"              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 为获取设备唯一标识 getDeviceUniqueID 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "uniqueId":""              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 为获取设备信息 getDeviceInfo 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "networkType":"WIFI"              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "sysType":"android"          &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "sysVersion":"4.2.2"        &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "model":"华为P8 4.2.2"   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "uniqueId":""   &nbsp;&nbsp;//可能为空
 * <br> }
 * <br> 当回调客户端页面的Intent传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:intent is null"
 * <br> 当回调客户端页面的ArrayList<String> users传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:users is null"
 * <br>
 *
 * <br>Created by issuser on 16/5/16.
 */
public class DevicePlugin extends BasePlugin {
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
        if(PluginUntil.getList().contains(action)){
            if(action.equals(PluginUntil.getDeviceNetworkType)){
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    String NetWork = CordovaUtils.isConnected(activity);
                    Map<String, Object> map = new HashMap<>();
                    map.put("networkType", NetWork);
                    sendPluginSucces(activity,callbackContext,map.toString());
                }
            }else if (action.equals(PluginUntil.getDeviceSystemType)){
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    Map<String, Object> map = new HashMap<>();
                    map.put("sysType", "Android");
                    sendPluginSucces(activity,callbackContext,map.toString());
                }
            }else if (action.equals(PluginUntil.getDeviceSystemVersion)){
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    String SystemVersion = CordovaUtils.GetSystemVersion();
                    Map<String, Object> map = new HashMap<>();
                    map.put("sysVersion", SystemVersion);
                    sendPluginSucces(activity,callbackContext,map.toString());
                }
            }else if (action.equals(PluginUntil.getDeviceModel)){
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    String DeviceModel = CordovaUtils.GetDeviceModel();
                    Map<String, Object> map = new HashMap<>();
                    map.put("model", DeviceModel);
                    sendPluginSucces(activity,callbackContext,map.toString());
                }
            }else if (action.equals(PluginUntil.getDeviceUniqueID)){
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    String UniqueID = CordovaUtils.GetUniqueID(activity);
                    Map<String, Object> map = new HashMap<>();
                    map.put("uniqueId", UniqueID);
                    sendPluginSucces(activity,callbackContext,map.toString());
                }
            }else if (action.equals(PluginUntil.getDeviceInfo)){
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    String NetWork = CordovaUtils.isConnected(activity);
                    String SystemVersion = CordovaUtils.GetSystemVersion();
                    String DeviceModel = CordovaUtils.GetDeviceModel();
                    String UniqueID = CordovaUtils.GetUniqueID(activity);
                    Map<String, Object> map = new HashMap<>();
                    map.put("networkType", NetWork);
                    map.put("sysType", "Android");
                    map.put("sysVersion", SystemVersion);
                    map.put("model", DeviceModel);
                    map.put("uniqueId", UniqueID);
                    sendPluginSucces(activity,callbackContext,map.toString());
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
