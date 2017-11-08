package com.perry.http.proxy;

import android.content.Context;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.perry.http.Listener.AppCallback;
import com.perry.http.Listener.LoadingInterface;

import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by isoftstone on 16/3/11.
 */
public class XUtilsClient extends AbsClient {

    public static int DEFAULT_VALUE_TIMEOUT_INTERVAL = 20;                                       //默认网络请求超时时间

    public static final String TAG = XUtilsClient.class.getSimpleName();

    public XUtilsClient(Context context, String url) {
        super(context, url);
    }

    @Override
    public <T> void request(int method, final Map<String, String> params, Map<String, File> fileParams, final Class<T> cls, final AppCallback<T> callback, final LoadingInterface li) {
        if (null != li) {
            li.start();
        }
        HttpUtils http = new HttpUtils();
//        http.configCookieStore(cookieStore);
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.configTimeout(60000);
        RequestParams paramsXutils = new RequestParams();
        Iterator<String> iterator=  params.keySet().iterator();
        JSONObject object = new JSONObject();
        while(iterator.hasNext()){
            String key = iterator.next();
            String value = params.get(key);
            Log.e(TAG,"key:"+key+",value:"+value);
            try {
                object.put(key, value);
                StringEntity entity = new StringEntity(object.toString(), "utf-8");
                paramsXutils.setBodyEntity(entity);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        paramsXutils.setHeader("Accept", "application/json;charset=utf-8");
        paramsXutils.setHeader("content-type", "application/json");
        RequestCallBack requestCallBack = new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    Log.e(TAG,"read json: " + responseInfo.result);
                    T t = mGson.fromJson(responseInfo.result, cls);
                    callback.callbackString(responseInfo.result);
                    callback.callback(t);
                } catch (Exception e) {
                    callback.onError(new Exception(e.toString()));
                    Log.e(TAG, "请处理此异常", e);
                } finally {
                    if (null != li) {
                        li.finish();
                    }
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                if (null != li) {
                    li.finish();
                }
                Log.e(TAG,"s:"+s);
                callback.onError(e);
            }
        };

        if (method == 0) {
            http.send(HttpRequest.HttpMethod.GET, mUrl+ formatParameter(params),paramsXutils, requestCallBack );
        } else if(method == 1){
//            paramsXutils.setBodyEntity(entity);
            http.send(HttpRequest.HttpMethod.POST, mUrl, paramsXutils, requestCallBack);
        }
    }

    @Override
    public <T> void Pluginrequest(int method, final Map<String, String> params, Map<String, File> fileParams, final Class<T> cls, final AppCallback<T> callback, final LoadingInterface li, boolean timeout) {
        if (null != li) {
            li.start();
        }
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.configTimeout(60000);
        RequestParams paramsXutils = new RequestParams();
        StringEntity entity = null;
        try {
            entity = new StringEntity(params.toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        paramsXutils.setBodyEntity(entity);
        paramsXutils.setHeader("Accept", "application/json;charset=utf-8");
        paramsXutils.setHeader("content-type", "application/json");
        System.out.println("======object.toString()====" + params.toString());
        RequestCallBack requestCallBack = new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    Log.e(TAG,"read json: " + responseInfo.result);
                    T t = mGson.fromJson(responseInfo.result, cls);
                    callback.callbackString(responseInfo.result);
                    callback.callback(t);
                } catch (Exception e) {
                    callback.onError(new Exception(e.toString()));
                    Log.e(TAG, "请处理此异常", e);
                } finally {
                    if (null != li) {
                        li.finish();
                    }
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {
                if (null != li) {
                    li.finish();
                }
                Log.e(TAG,"s:"+s);
                callback.onError(e);
            }
        };
        if (method == 0) {
            http.send(HttpRequest.HttpMethod.GET, mUrl+ formatParameter(params),paramsXutils, requestCallBack );
        } else if(method == 1){
            paramsXutils.setBodyEntity(entity);
            http.send(HttpRequest.HttpMethod.POST, mUrl, paramsXutils, requestCallBack);
        }
    }

    @Override
    public void cancel() {
//        VolleyUtil.getQueue(mContext).cancelAll(this);

    }


}
