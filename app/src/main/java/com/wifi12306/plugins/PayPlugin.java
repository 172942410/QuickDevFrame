package com.wifi12306.plugins;

import android.app.Activity;

import com.wifi12306.bean.plugin.CordovaResCode;
import com.wifi12306.plugins.utils.CordovaContest;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PayPlugin extends BasePlugin {
    public static CallbackContext callbackContext;
    private CordovaResCode cordovaResCode;
    private CordovaContest cordovaContest;
    private Activity activity;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        activity = this.cordova.getActivity();
        cordovaContest = new CordovaContest();
        cordovaResCode = new CordovaResCode();
        if(PluginUntil.getList().contains(action)) {
            if (action.equals(PluginUntil.pay)) {
                boolean Api = getAccessAPIpermissions(activity, callbackContext);
                if (Api) {
                    cordovaResCode = cordovaContest.initWithDictionaryParameters(args);
                    if (cordovaResCode.isOK) {
                        for (int i = 0; i < args.length(); i++) {
                            JSONObject json = args.getJSONObject(i);
                            Object obj = json.opt("outTradeNo");
                            if (obj != null && !obj.equals("")) {
                                Object objtime = cordovaContest.defualt(obj, 0);
                                if (objtime != null && !objtime.equals("")) {

                                } else {
                                    cordovaResCode = cordovaContest.setResultWithErrResultType(0, "outTradeNo");
                                    sendPluginError(activity, callbackContext, cordovaResCode.toString());
                                    return true;
                                }
                            } else {
                                cordovaResCode = cordovaContest.setResultWithErrResultType(0, "outTradeNo");
                                sendPluginError(activity, callbackContext, cordovaResCode.toString());
                                return true;
                            }

                            Object totalFee = json.opt("totalFee");
                            if(totalFee != null && !totalFee.equals("")){
//                                Object objtime = cordovaContest.defualt(totalFee,6);
//                                if(objtime != null && !objtime.equals("")){
                                    //"123.234".matches("^\\d+(\\.\\d{1,2})?$")
                                    boolean total = totalFee.toString().matches("^\\d+(\\.\\d{1,2})?$");
                                    if(total){
                                    }else {
                                        cordovaResCode = cordovaContest.setResultWithErrResultType(0,"totalFee");
                                        sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                        return true;
                                    }
//                                }else {
//                                    cordovaResCode = cordovaContest.setResultWithErrResultType(0,"totalFee");
//                                    sendPluginError(activity,callbackContext,cordovaResCode.toString());
//                                    return true;
//                                }
                            }else {
                                cordovaResCode = cordovaContest.setResultWithErrResultType(0,"totalFee");
                                sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                return true;
                            }

                            Object objshow = json.opt("isShowResultPage");
                            if(objshow != null && !objshow.equals("")){
                                Object objpro = cordovaContest.defualt(objshow,2);
                                if(objpro != null && !objpro.equals("")){
                                    String isShow = objpro.toString();
                                }else {
                                }
                            }else {
                            }

//                            Intent mIntent = new Intent(activity, PayActivCordovaUtilsity.class);
//                            cordova.startActivityForResult(this, mIntent, 1);
                        }
                        return true;
                    } else {
                        sendPluginError(activity, callbackContext, cordovaResCode.toString());
                    }
                } else {
                    sendPluginError(activity, callbackContext, cordovaResCode.toString());
                }
            }
        }
        return super.execute(action, args, callbackContext);
    }

}
