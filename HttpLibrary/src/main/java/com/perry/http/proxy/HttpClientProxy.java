package com.perry.http.proxy;

import android.content.Context;
import android.util.Log;


import com.perry.http.Listener.AppCallback;
import com.perry.http.Listener.LoadingInterface;
import com.perry.http.utils.HttpUtil;
import com.android.volley.Request;
import java.io.File;
import java.util.Map;

/**
 * http请求代理类 <br>
 * 把server返回的json直接生成相应的bean <br>
 * Created by rqyuea on 2015/12/23 0023.
 */
public class HttpClientProxy {

    private static final String TAG = "HttpClientProxy";
    private String mUrl;

    private Context mContext;

    AbsClient mClient;

    public HttpClientProxy(String url, Context context) {
        mUrl = url;
        mContext = context;
    }

    public <T> void doGet(final Map<String, String> params, final Class<T> cls, final AppCallback<T> callback) {
        request(Request.Method.GET, params, cls, callback);
    }

    public <T> void doPost(final Map<String, String> params, final Class<T> cls, final AppCallback<T> callback) {
        request(Request.Method.POST, params, cls, callback);
    }

    public <T> void request(int method, final Map<String, String> params, final Class<T> cls, final AppCallback<T> callback) {
        request(method, params, cls, callback, null);
    }

    public <T> void request(int method, Map<String, String> params, Map<String, File> fileParams, Class<T> cls, AppCallback<T> callback, LoadingInterface li) {
        if (fileParams == null) {
            mClient = new VolleyClient(mContext, mUrl);
//            mClient = new XUtilsClient(mContext, mUrl);
        } else {
            mClient = new SxClient(mContext, mUrl);
        }

//        params.put("name", "123");
        //服务端无要求的参数加密
//        params = HttpUtils.signHttpParams(params);
        Log.e(TAG,"请求服务器地址:"+mUrl+" ,params:"+params.toString());
        mClient.request(method, params, fileParams, cls, callback, li);
    }

    public <T> void request(int method, Map<String, String> params, Map<String, File> fileParams, Class<T> cls, AppCallback<T> callback, LoadingInterface li, boolean isValidatesSign) {
        if (fileParams == null) {
            mClient = new VolleyClient(mContext, mUrl);
//            mClient = new XUtilsClient(mContext, mUrl);
        } else {
            mClient = new SxClient(mContext, mUrl);
        }
        if(isValidatesSign) {
                //服务端要求的参数加密
                params = HttpUtil.pluginsignHttpParams(params);
                mClient.Pluginrequest(method, params, fileParams, cls, callback, li, true);
        }else {
            mClient.Pluginrequest(method, params, fileParams, cls, callback, li, true);
        }
    }

    public <T> void request(int method, final Map<String, String> params, final Class<T> cls, final AppCallback<T> callback, final LoadingInterface li) {
        request(method, params, null, cls, callback, li);

    }


    public void cancel() {
        mClient.cancel();
    }


}
