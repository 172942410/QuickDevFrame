package com.wifi12306.plugins;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.android.phone.mobilesdk.storage.sp.APSharedPreferences;
import com.alipay.android.phone.mobilesdk.storage.sp.SharedPreferencesManager;
import com.wifi12306.bean.plugin.CordovaResCode;
import com.wifi12306.plugins.utils.CordovaContest;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lipengjun on 2018/4/12.
 */

public class StoragePlugin extends BasePlugin {

    private static final String TAG = "StoragePlugin";
    private CallbackContext callbackContext;
    private CordovaContest cordovaContest;
    private CordovaResCode cordovaResCode;
    private Activity activity;
    static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        activity = this.cordova.getActivity();
        cordovaContest = new CordovaContest();
        cordovaResCode = new CordovaResCode();

        String storageName ;
        String key ;
        Object value ;

        if (action.equals(PluginUntil.backupData)){
            Map<Object, Object> map = new HashMap<>();
            for (int i = 0; i < args.length(); i++) {
                final JSONObject jsonObject = args.getJSONObject(i);
                key = jsonObject.optString("key");
                value = jsonObject.opt("value");
                storageName = jsonObject.optString("storageName");
                if(TextUtils.isEmpty(storageName) || TextUtils.isEmpty(key)){
                    Log.e(TAG, "execute: 名字或者key空了");
                    continue;
                }
                final APSharedPreferences preferences = SharedPreferencesManager.getInstance(activity, storageName);
//                if(value instanceof String) {
//                    preferences.putString(key, (String) value);
//                }else if(value instanceof Float) {
//                    preferences.putFloat(key, (Float) value);
//                }else if(value instanceof Integer) {
//                    preferences.putInt(key, (Integer) value);
//                }else if(value instanceof Long) {
//                    preferences.putLong(key, (Long) value);
//                }else if(value instanceof Boolean) {
//                    preferences.putBoolean(key, (Boolean) value);
//                }else{
                boolean isSuccess = preferences.putString(key, String.valueOf(value));
//                }
                map.put(key,isSuccess+"");
                singleThreadExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        preferences.commit();
                    }
                });
            }
//            sendPluginSuccess(activity, callbackContext, map);
            sendPluginSucces(activity, callbackContext, map.toString());
            return true;
        }else if (action.equals(PluginUntil.restoreData)){
            Map<Object, Object> map = new HashMap<>();
            for (int i = 0; i < args.length(); i++) {
                JSONObject jsonObject = args.getJSONObject(i);
                key = jsonObject.optString("key");
//                value = jsonObject.opt("value");
                storageName = jsonObject.optString("storageName");
                APSharedPreferences preferences = SharedPreferencesManager.getInstance(activity, storageName);
                value = preferences.getString(key,"");
                map.put(key,value);
//                sendPluginSucces(activity, callbackContext, (String) value);
            }
//            sendPluginSuccess(activity, callbackContext, map);
            sendPluginSucces(activity, callbackContext, map.toString());
            return true;
//                sendPluginSuccess();
        }else if (action.equals(PluginUntil.removeData)){
            Map<Object, Object> map = new HashMap<>();
            for (int i = 0; i < args.length(); i++) {
                JSONObject jsonObject = args.getJSONObject(i);
                key = jsonObject.optString("key");
                storageName = jsonObject.optString("storageName");
                final APSharedPreferences preferences = SharedPreferencesManager.getInstance(activity, storageName);
                boolean isSuccess = preferences.remove(key);
                singleThreadExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        preferences.commit();
                    }
                });
                map.put(key,isSuccess);
//                sendPluginSucces(activity, callbackContext, (String) value);
            }
            sendPluginSucces(activity, callbackContext, map.toString());
//                sendPluginSuccess(activity, callbackContext,map);
            return true;
        }else if (action.equals(PluginUntil.removeAllData)){
            Map<Object, Object> map = new HashMap<>();
            for (int i = 0; i < args.length(); i++) {
                JSONObject jsonObject = args.getJSONObject(i);
                storageName = jsonObject.optString("storageName");
                final APSharedPreferences preferences = SharedPreferencesManager.getInstance(activity, storageName);
                boolean isSuccess =preferences.clear();
                singleThreadExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        preferences.commit();
                    }
                });
                map.put(storageName,isSuccess);
//                sendPluginSucces(activity, callbackContext, (String) value);
            }
//                sendPluginSuccess(activity, callbackContext,map);
            sendPluginSucces(activity, callbackContext, map.toString());
            return true;
        }

        return super.execute(action, args, callbackContext);
    }
}
