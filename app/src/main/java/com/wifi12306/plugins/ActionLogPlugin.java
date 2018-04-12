package com.wifi12306.plugins;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.wifi12306.bean.plugin.CordovaResCode;
import com.wifi12306.bean.plugin.NetworkStartBean;
import com.wifi12306.plugins.utils.CordovaContest;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.json.JSONException;

/**
 * Created by songchunxiao on 2018/2/7.
 */

public class ActionLogPlugin extends BasePlugin {

    private CallbackContext callbackContext;
    private CordovaContest cordovaContest;
    private CordovaResCode cordovaResCode;
    private Activity activity;
    private NetworkStartBean sxNetworkStartBean;


    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        activity = this.cordova.getActivity();
        cordovaContest = new CordovaContest();
        cordovaResCode = new CordovaResCode();
        if (action.equals(PluginUntil.writeActionLog)){
            Toast.makeText(activity,"arg:"+args.toString() +"\n",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.execute(action, args, callbackContext);
    }
}
