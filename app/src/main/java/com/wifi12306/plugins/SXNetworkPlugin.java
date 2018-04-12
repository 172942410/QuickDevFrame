package com.wifi12306.plugins;


import android.app.Activity;
import android.app.DownloadManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import com.wifi12306.utils.MD5Util;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


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
 * <p>
 * <br>Created by issuser on 16/5/16.
 */
public class SXNetworkPlugin extends BasePlugin {

    private CordovaContest cordovaContest;
    private CordovaResCode cordovaResCode;
    private Activity activity;
    private NetworkStartBean sxNetworkStartBean;

    @Override
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        activity = this.cordova.getActivity();
        cordovaContest = new CordovaContest();
        cordovaResCode = new CordovaResCode();
        if (PluginUntil.getList().contains(action)) {
            if (action.equals(PluginUntil.downLoadApp)) {//下载 app
                for (int i = 0; i < args.length(); i++) {
                    JSONObject jsob = args.getJSONObject(i);
                    String url = jsob.optString("turnUrl");
                    {
                        DownloadManager downloadManager = (DownloadManager) activity.getSystemService(Activity.DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                        //设置允许使用的网络类型，这里是移动网络和wifi都可以
                        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                        //在通知栏中显示
                        request.setVisibleInDownloadsUi(true);
                        //表示允许MediaScanner扫描到这个文件，默认不允许。
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        /*设置下载后文件存放的位置,如果sdcard不可用，那么设置这个将报错，因此最好不设置如果sdcard可用，下载后的文件
                        在/mnt/sdcard/Android/data/packageName/files目录下面，如果sdcard不可用,设置了下面这个将报错，
                        不设置，下载后的文件在/cache这个  目录下面*/
                        request.setDestinationInExternalFilesDir(activity, null, url.substring(url.lastIndexOf("/") + 1));
                        request.setDestinationInExternalFilesDir(activity, null, MD5Util.getMD5(url) + ".apk");
                        long id = downloadManager.enqueue(request);
                    }
                }
                return true;
            } else if (action.equals(PluginUntil.configRequest)) {//配置网络环境
                boolean Api = getAccessAPIpermissions(activity, callbackContext);
                if (Api) {
                    cordovaResCode = cordovaContest.initWithDictionaryParameters(args);
                    if (cordovaResCode.isOK) {
                        NetworkConfigBean sxNetworkConfigBean = new NetworkConfigBean();
                        for (int i = 0; i < args.length(); i++) {
                            JSONObject jsob = args.getJSONObject(i);
                            Object objshow = jsob.opt("staticParams");
                            if (objshow != null && !objshow.equals("")) {
                                Object objshowpro = cordovaContest.defualt(objshow, 4);
                                if (objshowpro != null && !objshowpro.equals("")) {
                                    JSONObject jso = jsob.getJSONObject("staticParams");
                                    Iterator<String> keys = jso.keys();
                                    while (keys.hasNext()) {
                                        String strkey = keys.next();
                                    }
                                } else {
                                }
                            } else {
                            }
                            Object objtime = jsob.opt("timeoutInterval");
                            if (objtime != null && !objtime.equals("")) {
                                Object objshowpro = cordovaContest.defualt(objtime, 1);
                                if (objshowpro != null && !objshowpro.equals("")) {
                                    if (Integer.valueOf(objshowpro.toString()) >= CordovaUtils.MIN_VALUE_TIMEOUT_INTERVAL && Integer.valueOf(objshowpro.toString()) <= CordovaUtils.MAX_VALUE_TIMEOUT_INTERVAL) {
                                    } else {
                                    }
                                } else {
                                }
                            } else {
                            }
                            boolean isValidatesSecureCertificate;
                            Object objisValidate = jsob.opt("isValidatesSecureCertificate");
                            if (objisValidate != null && !objisValidate.equals("")) {
                                Object objshowpro = cordovaContest.defualt(objisValidate, 2);
                                if (objshowpro != null && !objshowpro.equals("")) {
                                    isValidatesSecureCertificate = Boolean.valueOf(objshowpro.toString());
                                } else {
                                    isValidatesSecureCertificate = true;
                                }
                            } else {
                                isValidatesSecureCertificate = true;
                            }
                            Object objisValidatesign = jsob.opt("isValidatesSign");
                            if (objisValidatesign != null && !objisValidatesign.equals("")) {
                                Object objshowpro = cordovaContest.defualt(objisValidatesign, 2);
                                if (objshowpro != null && !objshowpro.equals("")) {
                                } else {
                                }
                            } else {
                            }
                        }
                        sendPluginSucces(activity, callbackContext, cordovaResCode.toString());
                    } else {
                        sendPluginError(activity, callbackContext, cordovaResCode.toString());
                    }
                }
            } else if (action.equals(PluginUntil.startRequest)) {
                //请求网络接口'''']
                boolean netWork = CordovaUtils.isNetworkAvailable(activity);
                if (netWork) {
                    cordovaResCode = cordovaContest.initWithDictionaryParameters(args);
                    Map<String, String> params = new HashMap();
                    if (cordovaResCode.isOK) {
                        sxNetworkStartBean = new NetworkStartBean();
                        for (int i = 0; i < args.length(); i++) {
                            JSONObject jsob = args.getJSONObject(i);
                            Object objshow = jsob.opt("isShowLoading");
                            if (objshow != null && !objshow.equals("")) {
                                Object objshowpro = cordovaContest.defualt(objshow, 2);
                                if (objshowpro != null && !objshowpro.equals("")) {
                                    sxNetworkStartBean.isShowLoading = Boolean.valueOf(objshowpro.toString());
                                } else {
                                    sxNetworkStartBean.isShowLoading = CordovaUtils.DEFAULT_BOOL_SHOW_LOADING;
                                }
                            } else {
                                sxNetworkStartBean.isShowLoading = CordovaUtils.DEFAULT_BOOL_SHOW_LOADING;
                            }
                            Object objmethod = jsob.opt("method");
                            if (objmethod != null && !objmethod.equals("")) {
                                Object objshowpro = cordovaContest.defualt(objmethod, 1);
                                if (objshowpro != null && !objshowpro.equals("")) {
                                    sxNetworkStartBean.method = Integer.valueOf(objshowpro.toString());
                                } else {
                                    sxNetworkStartBean.method = 0;
                                }
                            } else {
                                sxNetworkStartBean.method = 0;
                            }
                            Object objisuser = jsob.opt("isUseStaticParams");
                            if (objisuser != null && !objisuser.equals("")) {
                                Object objshowpro = cordovaContest.defualt(objisuser, 2);
                                if (objshowpro != null && !objshowpro.equals("")) {
                                    sxNetworkStartBean.isUseStaticParams = Boolean.valueOf(objshowpro.toString());

                                } else {
                                    sxNetworkStartBean.isUseStaticParams = CordovaUtils.DEFAULT_BOOL_USE_STATIC_PARAMS;
                                }
                            } else {
                                sxNetworkStartBean.isUseStaticParams = CordovaUtils.DEFAULT_BOOL_USE_STATIC_PARAMS;
                            }
                            Object objappid = jsob.opt("url");
                            if (objappid != null && !objappid.equals("")) {
                                String appId = objappid.toString();
                                cordovaResCode = cordovaContest.judgeNull(appId, "url");
                                if (cordovaResCode.isOK) {

                                    boolean strappid = cordovaContest.necessary(objappid, 0);
                                    if (strappid) {
                                        sxNetworkStartBean.url = appId;
                                    } else {
                                        cordovaResCode = cordovaContest.setResultWithErrResultType(0, "url");
                                        sendPluginError(activity, callbackContext, cordovaResCode.toString());
                                        return true;
                                    }
                                } else {
                                    cordovaResCode = cordovaContest.setResultWithErrResultType(1, "url");
                                    sendPluginError(activity, callbackContext, cordovaResCode.toString());
                                    return true;
                                }
                            } else {
                                cordovaResCode = cordovaContest.setResultWithErrResultType(1, "url");
                                sendPluginError(activity, callbackContext, cordovaResCode.toString());
                                return true;
                            }
                            Object objcancel = jsob.opt("cancelTag");
                            if (objcancel != null && !objcancel.equals("")) {
                                Object objshowpro = cordovaContest.defualt(objcancel, 0);
                                if (objshowpro != null && !objshowpro.equals("")) {
                                    sxNetworkStartBean.cancelTag = objshowpro.toString();
                                } else {
                                    sxNetworkStartBean.cancelTag = "0";
                                }
                            } else {
                                sxNetworkStartBean.cancelTag = "0";
                            }
                            Object objrequest = jsob.opt("requestParams");
                            if (objrequest != null && !objrequest.equals("")) {
                                Object objshowpro = cordovaContest.defualt(objrequest, 4);
                                if (objshowpro != null && !objshowpro.equals("")) {
                                    JSONObject jso = jsob.getJSONObject("requestParams");
                                    Iterator<String> keys = jso.keys();
                                    while (keys.hasNext()) {
                                        String strkey = keys.next();
                                        params.put(strkey, jso.getString(strkey));
                                    }
                                } else {

                                }
                            } else {

                            }
                            Object objisclose = jsob.opt("isCloseSignVerification");
                            if (objisclose != null && !objisclose.equals("")) {
                                Object objshowpro = cordovaContest.defualt(objisclose, 2);
                                if (objshowpro != null && !objshowpro.equals("")) {
                                } else {
                                }
                            } else {
                            }
                        }
                        try {
                            if (sxNetworkStartBean.isShowLoading) {
                                repuestshowLoadingNetwork(sxNetworkStartBean.url, sxNetworkStartBean.method, params, callbackContext);
                            } else {
                                repuestNetwork(sxNetworkStartBean.url, sxNetworkStartBean.method, params, callbackContext);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //                requestNetworkData(sxNetworkStartBean.url,sxNetworkStartBean.method,false,callbackContext);
                        return true;
                    } else {
                        sendPluginError(activity, callbackContext, cordovaResCode.toString());
                    }
//                    }
                } else {
                    //没网的时候
                    Toast.makeText(activity,"网络不见了！\n请检查网络后重试！",Toast.LENGTH_SHORT).show();
                    sendPluginError(activity, callbackContext, CordovaUtils.RESCODE_NO_NETWORK, CordovaUtils.RESMSG_NO_NETWORK);
                }
            } else if (action.equals(PluginUntil.cancelRequest)) {
                //请求取消网络接口
                boolean Api = getAccessAPIpermissions(activity, callbackContext);
                if (Api) {
                    cordovaResCode = cordovaContest.initWithDictionaryParameters(args);
                    NetworkCancelBean sxNetworkCancelBean = new NetworkCancelBean();
                    if (cordovaResCode.isOK) {
                        for (int i = 0; i < args.length(); i++) {
                            JSONObject jsob = args.getJSONObject(i);
                            Iterator<String> keys = jsob.keys();
                            while (keys.hasNext()) {
                                String strkey = keys.next();
                                if (strkey.equals("cancelTag")) {
                                    String cancelTag = jsob.getString("cancelTag");
                                    cordovaResCode = cordovaContest.judgeString(cancelTag);
                                    if (cordovaResCode.isOK) {
                                        VolleyUtil.getQueue(activity).cancelAll(this);
                                        sendPluginSucces(activity, callbackContext, cordovaResCode.toString());
                                    } else {
                                        cordovaResCode = cordovaContest.setResultWithErrResultType(1, "cancelTag");
                                        sendPluginError(activity, callbackContext, cordovaResCode.toString());
                                    }
                                } else {
                                    cordovaResCode = cordovaContest.setResultWithErrResultType(1, "cancelTag");
                                    sendPluginError(activity, callbackContext, cordovaResCode.toString());
                                }
                            }
                        }
                    } else {
                        sendPluginError(activity, callbackContext, cordovaResCode.toString());
                    }
                }
            }
            //20180128
            else if (action.equals(PluginUntil.getPermission)) {
                boolean Api = getAccessAPIpermissions(activity, callbackContext);
                cordovaResCode = cordovaContest.initWithDictionaryParameters(args);

                cordovaResCode = cordovaContest.judgeString("getPermission");
                if (cordovaResCode.isOK){
                    String regChannel = "3";//中铁行
                    Map<String, String> map = new HashMap<>();
                    map.put("regChannel", "" + regChannel);
                    JsonObject obj = new JsonObject();
                    obj.addProperty("regChannel", "" + regChannel);
                    Gson gson = new Gson();
                    String str = gson.toJson(obj);
                    cordovaResCode.isOK = true;
                    cordovaResCode.resCode = "0";
                    cordovaResCode.resMsg = str;
//                    callbackContext.success(str);
                    sendPluginSucces(activity, callbackContext, str);
                }else {
                    cordovaResCode = cordovaContest.setResultWithErrResultType(1, "getPermission");
                    sendPluginError(activity, callbackContext, cordovaResCode.toString());
                }

            }
            else if(action.equals(PluginUntil.popMonthCard)){

                return true;
            }


        } else {
            cordovaResCode = cordovaContest.setResultWithErrResultType(2, action);
            sendPluginError(activity, callbackContext, cordovaResCode.toString());
            return true;
        }
        return super.execute(action, args, callbackContext);
    }


    private void repuestNetwork(final String url, final int method, final Map<String, String> params, final CallbackContext callbackContext) {
        RequestManager.getInstance(activity).sendRequest(new GetCordovaNetworkRequest(url, method, params)
            .withResponse(SucDetailDataBean.class, new AppCallback<SucDetailDataBean>() {
                @Override
                public void callback(SucDetailDataBean sucDetailDataBean) {
//                    return false;
                }

                @Override
                public void callbackString(String str) {
                    Log.e("1111111无弹框", "url : " + url);
                    Log.e("1111111无弹框", "str : " + str);
                    if (str != null && !str.equals("")) {
                        sendPluginSucces(activity, callbackContext, str);
                    } else {
                        sendPluginSucces(activity, callbackContext, str);
                    }
                }

                @Override
                public void onError(Exception e) {
                    Log.e("1111111无弹框错误", "str : ");
//                        CordovaUtils.ShowMessageDialog(activity,1,"网络错误");
//                        ToastAlone.showToast(activity,"网络错误",0);
                    sendPluginError(activity, callbackContext, CordovaUtils.RESCODE_REQUEST_FAILED, CordovaUtils.RESMSG_REQUEST_FAILED);
//                    return true;
                }
            }));
    }

    private void repuestshowLoadingNetwork(final String url, final int method, Map<String, String> params, final CallbackContext callbackContext) {
        RequestManager.getInstance(activity).sendRequest(new GetCordovaNetworkRequest(url, method, params)
            .withResponse(SucDetailDataBean.class, new AppCallback<SucDetailDataBean>() {
                @Override
                public void callback(SucDetailDataBean sucDetailDataBean) {
//                    return false;
                }

                @Override
                public void callbackString(String str) {
                    Log.e("2222222有弹框", "str : " + str);
                    if (str != null && !str.equals("")) {
                        sendPluginSucces(activity, callbackContext, str);
                    } else {
                        sendPluginSucces(activity, callbackContext, str);
                    }
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                    Log.e("2222222有弹框错误", "str : ");
//                        CordovaUtils.ShowMessageDialog(activity,1,"网络错误");
//                        ToastAlone.showToast(activity,"网络错误",0);
                    sendPluginError(activity, callbackContext, CordovaUtils.RESCODE_REQUEST_FAILED, CordovaUtils.RESMSG_REQUEST_FAILED);
//                    return true;
                }
            }), new LoadingDialogImpl(activity, "努力加载中..."));

    }






    /**
     * 去掉url中的路径，留下请求参数部分
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String TruncateUrlPage(String strURL)
    {
        String strAllParam=null;
        String[] arrSplit=null;

        strURL=strURL.trim();

        arrSplit=strURL.split("[?]");
        if(strURL.length()>1)
        {
            if(arrSplit.length>1)
            {
                if(arrSplit[1]!=null)
                {
                    strAllParam=arrSplit[1];
                }
            }
        }
        Log.d("SONG", "strAllParam  =  : " + strAllParam.toString());
        return strAllParam;
    }
    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     * @param URL  url地址
     * @return  url请求参数部分
     */
    public static Map<String, String> URLRequest(String URL)
    {
        Map<String, String> mapRequest = new HashMap<String, String>();

        String[] arrSplit=null;

        String strUrlParam=TruncateUrlPage(URL);
        if(strUrlParam==null)
        {
            return mapRequest;
        }
        //每个键值为一组 www.2cto.com
        arrSplit=strUrlParam.split("[&]");
        for(String strSplit:arrSplit)
        {
            String[] arrSplitEqual=null;
            arrSplitEqual= strSplit.split("[=]");

            //解析出键值
            if(arrSplitEqual.length>1)
            {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

            }
            else
            {
                if(arrSplitEqual[0]!="")
                {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }
}
