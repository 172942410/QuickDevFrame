package com.wifi12306.plugins;


import android.app.Activity;
import android.util.Log;

import com.perry.http.Listener.AppCallback;
import com.perry.http.Listener.LoadingDialogImpl;
import com.perry.http.manager.RequestManager;
import com.perry.http.request.GetCordovaNetworkRequest;
import com.perry.http.utils.VolleyUtil;
import com.wifi12306.bean.plugin.CordovaResCode;
import com.wifi12306.bean.plugin.NetworkCancelBean;
import com.wifi12306.bean.plugin.NetworkConfigBean;
import com.wifi12306.bean.plugin.NetworkStartBean;
import com.wifi12306.bean.plugin.SucDetailDataBean;
import com.wifi12306.plugins.utils.CordovaContest;
import com.wifi12306.plugins.utils.CordovaUtils;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;


/**
 * 网络请求 插件调用 plugin
 * <br>use javascript call like:
 * <br>`(succ, fail, 'SXNetworkPlugin', 'configRequest',[])
 * <br> 方法名:configRequest  &nbsp;&nbsp;//配置网络请求
 * <br> 方法名:startRequest &nbsp;&nbsp;//通过司信客户端请求网络
 * <br> 方法名:cancelRequest &nbsp;&nbsp;//取消网络请求
 * <br> 当点击客户端取消按钮时: 传递给plugin的success方法回调参数为JSONArray字符串:
 * <br>["cancel"]
 * <br> 配置网络请求 configRequest 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 通过司信客户端请求网络 startRequest 当客户端请求成功后: 传递给plugin的success方法数据为Arraylist的jsonObject字符串:
 * <br> {
 * <br> //后台接口返回的json直接传给H5
 * <br> }
 * <br> 取消网络请求 cancelRequest 当客户端请求成功后: 传递给plugin的success方法数据为Arraylist的jsonObject字符串:
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
public class NetworkPlugin extends BasePlugin {

    private CallbackContext callbackContext;
    private CordovaContest cordovaContest;
    private CordovaResCode cordovaResCode;
    private Activity activity;
    private NetworkStartBean sxNetworkStartBean;

    @Override
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        activity = this.cordova.getActivity();
        cordovaContest = new CordovaContest();
        cordovaResCode = new CordovaResCode();
        if(PluginUntil.getList().contains(action)){
//配置网络环境
            if (action.equals(PluginUntil.configRequest)) {
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    cordovaResCode = cordovaContest.initWithDictionaryParameters(args);
                    if (cordovaResCode.isOK) {
                        NetworkConfigBean sxNetworkConfigBean = new NetworkConfigBean();
                        for (int i = 0; i < args.length(); i++) {
                            JSONObject jsob = args.getJSONObject(i);
                            Object objshow = jsob.opt("staticParams");
                            if(objshow != null && !objshow.equals("")){
                                Object objshowpro = cordovaContest.defualt(objshow,4);
                                if(objshowpro != null && !objshowpro.equals("")){
                                    JSONObject jso = jsob.getJSONObject("staticParams");
                                    Iterator<String> keys = jso.keys();
                                    while (keys.hasNext()) {
                                        String strkey = keys.next();
                                    }
                                }else {
                                }
                            }else {
                            }
                            Object objtime = jsob.opt("timeoutInterval");
                            if(objtime != null && !objtime.equals("")){
                                Object objshowpro = cordovaContest.defualt(objtime,1);
                                if(objshowpro != null && !objshowpro.equals("")){
                                    if(Integer.valueOf(objshowpro.toString()) >= CordovaUtils.MIN_VALUE_TIMEOUT_INTERVAL && Integer.valueOf(objshowpro.toString()) <= CordovaUtils.MAX_VALUE_TIMEOUT_INTERVAL){
                                    }else {
                                    }
                                }else {
                                }
                            }else {
                            }
                            boolean isValidatesSecureCertificate;
                            Object objisValidate = jsob.opt("isValidatesSecureCertificate");
                            if(objisValidate != null && !objisValidate.equals("")){
                                Object objshowpro = cordovaContest.defualt(objisValidate,2);
                                if(objshowpro != null && !objshowpro.equals("")){
                                    isValidatesSecureCertificate = Boolean.valueOf(objshowpro.toString());
                                }else {
                                    isValidatesSecureCertificate = true;
                                }
                            }else {
                                isValidatesSecureCertificate = true;
                            }
                            Object objisValidatesign = jsob.opt("isValidatesSign");
                            if(objisValidatesign != null && !objisValidatesign.equals("")){
                                Object objshowpro = cordovaContest.defualt(objisValidatesign,2);
                                if(objshowpro != null && !objshowpro.equals("")){
                                }else {
                                }
                            }else {
                            }
                        }
                        sendPluginSucces(activity,callbackContext,cordovaResCode.toString());
                    }else {
                        sendPluginError(activity,callbackContext,cordovaResCode.toString());
                    }
                }
            }else if (action.equals(PluginUntil.startRequest)) {
                //请求网络接口'''']
                boolean netWork = CordovaUtils.isNetworkAvailable(activity);
                if(netWork){
                    boolean Api = getAccessAPIpermissions(activity,callbackContext);
                    if(Api){
                        cordovaResCode = cordovaContest.initWithDictionaryParameters(args);
                        if (cordovaResCode.isOK) {
                            sxNetworkStartBean = new NetworkStartBean();
                            for (int i = 0; i < args.length(); i++) {
                                JSONObject jsob = args.getJSONObject(i);
                                Object objshow = jsob.opt("isShowLoading");
                                if(objshow != null && !objshow.equals("")){
                                    Object objshowpro = cordovaContest.defualt(objshow,2);
                                    if(objshowpro != null && !objshowpro.equals("")){
                                        sxNetworkStartBean.isShowLoading = Boolean.valueOf(objshowpro.toString());
                                    }else {
                                        sxNetworkStartBean.isShowLoading = CordovaUtils.DEFAULT_BOOL_SHOW_LOADING;
                                    }
                                }else {
                                    sxNetworkStartBean.isShowLoading = CordovaUtils.DEFAULT_BOOL_SHOW_LOADING;
                                }
                                Object objmethod = jsob.opt("method");
                                if(objmethod != null && !objmethod.equals("")){
                                    Object objshowpro = cordovaContest.defualt(objmethod,1);
                                    if(objshowpro != null && !objshowpro.equals("")){
                                        sxNetworkStartBean.method = Integer.valueOf(objshowpro.toString());
                                    }else {
                                        sxNetworkStartBean.method = 0;
                                    }
                                }else {
                                    sxNetworkStartBean.method = 0;
                                }
                                Object objisuser = jsob.opt("isUseStaticParams");
                                if(objisuser != null && !objisuser.equals("")){
                                    Object objshowpro = cordovaContest.defualt(objisuser,2);
                                    if(objshowpro != null && !objshowpro.equals("")){
                                        sxNetworkStartBean.isUseStaticParams = Boolean.valueOf(objshowpro.toString());

                                    }else {
                                        sxNetworkStartBean.isUseStaticParams = CordovaUtils.DEFAULT_BOOL_USE_STATIC_PARAMS;
                                    }
                                }else {
                                    sxNetworkStartBean.isUseStaticParams = CordovaUtils.DEFAULT_BOOL_USE_STATIC_PARAMS;
                                }
                                Object objappid = jsob.opt("url");
                                if(objappid != null && !objappid.equals("")){
                                    String appId = objappid.toString();
                                    cordovaResCode = cordovaContest.judgeNull(appId,"url");
                                    if(cordovaResCode.isOK){
                                        boolean strappid = cordovaContest.necessary(objappid,0);
                                        if(strappid){
                                            sxNetworkStartBean.url = appId;
                                        }else {
                                            cordovaResCode = cordovaContest.setResultWithErrResultType(0,"url");
                                            sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                            return true;
                                        }
                                    }else {
                                        cordovaResCode = cordovaContest.setResultWithErrResultType(1,"url");
                                        sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                        return true;
                                    }
                                }else {
                                    cordovaResCode = cordovaContest.setResultWithErrResultType(1,"url");
                                    sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                    return true;
                                }
                                Object objcancel = jsob.opt("cancelTag");
                                if(objcancel != null && !objcancel.equals("")){
                                    Object objshowpro = cordovaContest.defualt(objcancel,0);
                                    if(objshowpro != null && !objshowpro.equals("")){
                                        sxNetworkStartBean.cancelTag = objshowpro.toString();
                                    }else {
                                        sxNetworkStartBean.cancelTag = "0";
                                    }
                                }else {
                                    sxNetworkStartBean.cancelTag = "0";
                                }
                                Object objrequest = jsob.opt("requestParams");
                                if(objrequest != null && !objrequest.equals("")){
                                    Object objshowpro = cordovaContest.defualt(objrequest,4);
                                    if(objshowpro != null && !objshowpro.equals("")){
                                        JSONObject jso = jsob.getJSONObject("requestParams");
                                        Iterator<String> keys = jso.keys();
                                        while (keys.hasNext()) {
                                            String strkey = keys.next();
                                        }
                                    }else {

                                    }
                                }else {

                                }
                                Object objisclose = jsob.opt("isCloseSignVerification");
                                if(objisclose != null && !objisclose.equals("")){
                                    Object objshowpro = cordovaContest.defualt(objisclose,2);
                                    if(objshowpro != null && !objshowpro.equals("")){
                                    }else {
                                    }
                                }else {
                                }
                            }
                            try {
                                if(sxNetworkStartBean.isShowLoading){
                                    repuestshowLoadingNetwork(sxNetworkStartBean.url,sxNetworkStartBean.method,callbackContext);
                                }else {
                                    repuestNetwork(sxNetworkStartBean.url,sxNetworkStartBean.method,callbackContext);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            //                requestNetworkData(sxNetworkStartBean.url,sxNetworkStartBean.method,false,callbackContext);
                            return true;
                        }else {
                            sendPluginError(activity,callbackContext,cordovaResCode.toString());
                        }
                    }
                }else {
                    sendPluginError(activity,callbackContext,CordovaUtils.RESCODE_NO_NETWORK,CordovaUtils.RESMSG_NO_NETWORK);
                }
            }else if (action.equals(PluginUntil.cancelRequest)) {
                //请求取消网络接口
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if (Api) {
                    cordovaResCode = cordovaContest.initWithDictionaryParameters(args);
                    NetworkCancelBean sxNetworkCancelBean = new NetworkCancelBean();
                    if (cordovaResCode.isOK) {
                        for (int i = 0; i < args.length(); i++) {
                            JSONObject jsob = args.getJSONObject(i);
                            Iterator<String> keys = jsob.keys();
                            while (keys.hasNext()) {
                                String strkey = keys.next();
                                if(strkey.equals("cancelTag")){
                                    String cancelTag = jsob.getString("cancelTag");
                                    cordovaResCode = cordovaContest.judgeString(cancelTag);
                                    if(cordovaResCode.isOK){
                                        VolleyUtil.getQueue(activity).cancelAll(this);
                                        sendPluginSucces(activity,callbackContext,cordovaResCode.toString());
                                    }else {
                                        cordovaResCode = cordovaContest.setResultWithErrResultType(1, "cancelTag");
                                        sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                    }
                                }else {
                                    cordovaResCode = cordovaContest.setResultWithErrResultType(1, "cancelTag");
                                    sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                }
                            }
                        }
                    } else {
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


    private void repuestNetwork(final String url, final int method, final CallbackContext callbackContext){
        RequestManager.getInstance(activity).sendRequest(new GetCordovaNetworkRequest(url, method, new HashMap<String, String>()/*这个参数自己编写*/)
                .withResponse(SucDetailDataBean.class, new AppCallback<SucDetailDataBean>() {
                    @Override
                    public void callback(SucDetailDataBean sucDetailDataBean) {

//                        return false;
                    }

                    @Override
                    public void callbackString(String str) {
                        Log.e("1111111无弹框","str : " + str);
                            if (str != null && !str.equals("")) {
                                sendPluginSucces(activity,callbackContext,str);
                            } else {
                                sendPluginSucces(activity,callbackContext,str);
                            }
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("1111111无弹框错误","str : ");
//                        CordovaUtils.ShowMessageDialog(activity,1,"网络错误");
//                        ToastAlone.showToast(activity,"网络错误",0);
                        sendPluginError(activity,callbackContext, CordovaUtils.RESCODE_REQUEST_FAILED+":3", CordovaUtils.RESMSG_REQUEST_FAILED);
//                        return false;
                    }
                }));
    }

    private void repuestshowLoadingNetwork(final String url, final int method, final CallbackContext callbackContext){
        RequestManager.getInstance(activity).sendRequest(new GetCordovaNetworkRequest(url, method, new HashMap<String, String>()/*这个参数自己编写*/)
                .withResponse(SucDetailDataBean.class, new AppCallback<SucDetailDataBean>() {
                    @Override
                    public void callback(SucDetailDataBean sucDetailDataBean) {

//                        return false;
                    }

                    @Override
                    public void callbackString(String str) {
                        Log.e("2222222有弹框","str : " + str);
                        if (str != null && !str.equals("")) {
                            sendPluginSucces(activity,callbackContext,str);
                        } else {
                            sendPluginSucces(activity,callbackContext,str);
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                        Log.e("2222222有弹框错误","str : ");
//                        CordovaUtils.ShowMessageDialog(activity,1,"网络错误");
//                        ToastAlone.showToast(activity,"网络错误",0);
                        sendPluginError(activity,callbackContext, CordovaUtils.RESCODE_REQUEST_FAILED+":4", CordovaUtils.RESMSG_REQUEST_FAILED);
//                        return false;
                    }
                }), new LoadingDialogImpl(activity,"努力加载中..."));

    }

}
