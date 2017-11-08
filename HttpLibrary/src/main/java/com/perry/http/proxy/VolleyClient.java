package com.perry.http.proxy;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.perry.http.Listener.AppCallback;
import com.perry.http.Listener.LoadingInterface;
import com.perry.http.utils.VolleyUtil;
import com.perry.http.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by isoftstone on 16/3/11.
 */
public class VolleyClient extends AbsClient {

    public static int DEFAULT_VALUE_TIMEOUT_INTERVAL = 5;                                       //默认网络请求超时时间

    public static final String TAG = VolleyClient.class.getSimpleName();

    public VolleyClient(Context context, String url) {
        super(context, url);
    }

    @Override
    public <T> void request(int method, final Map<String, String> params, Map<String, File> fileParams, final Class<T> cls, final AppCallback<T> callback, final LoadingInterface li) {
        if (null != li) {
            li.start();
        }

        VolleyUtil.getQueue(mContext).cancelAll(this);

        StringRequest request = null;

        Response.Listener<String> listener = new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                try {
//                        //服务器未转码， 这里需转码
//                        s = new String(s.getBytes("iso8859-1"), "UTF-8");
//                    Log.e(TAG,"read json: " + s);
                    LogUtil.e(TAG,"read json:"+s);
                    T t = mGson.fromJson(s, cls);
                    callback.callbackString(s);
                    callback.callback(t);
                } catch (Exception e) {
//                    callback.onError(new Exception(e.toString()));
                    Log.e(TAG, "请处理此异常", e);
                } finally {
                    if (null != li) {
                        li.finish();
                    }
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (null != li) {
                    li.finish();
                }

                if (error instanceof TimeoutError) {
                    callback.onError(new Exception("加载超时"));
                } else if (error instanceof NoConnectionError) {
                    callback.onError(new Exception("网络连接失败,请检查网络设置!"));
                } else {
                    callback.onError(new Exception(error.toString()));
                }

            }
        };

        if (method == Request.Method.GET) {
            request = new StringRequest(method, mUrl + formatParameter(params), listener, errorListener);
        } else {
            request = new StringRequest(method, mUrl, listener, errorListener) {
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    return params;
//                }
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<String, String>();
                    headers.put("Accept", "application/json;charset=utf-8");
                    headers.put("content-type", "application/json");
                    return headers;
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    byte[] body = null;
                    Iterator<String> iterator=  params.keySet().iterator();
                    JSONObject object = new JSONObject();
                    if(!iterator.hasNext()){
                        //如果参数为空
                        try {
                            body = object.toString().getBytes("utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }else {
                        String bodyStr = "";
                        while (iterator.hasNext()) {
                            String key = iterator.next();
                            String value = params.get(key);
//                            Log.e(TAG, "getBody key:" + key + ",value:" + value);
                            try {
                                if(value.startsWith("[")&&value.endsWith("]")){
                                    JSONArray jsonArray = new JSONArray(value);
                                    object.put(key, jsonArray);
                                }else{
                                    object.put(key, value);
                                }
                                bodyStr = object.toString();
//                                Log.e(TAG,"bodyStr:"+bodyStr);
                                body = bodyStr.getBytes("utf-8");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e(TAG,"发送http请求的body内容："+bodyStr);
                    }
//                    try {
//                        object.put("from_station", "VNP");
//                        object.put("to_station", "AOH");
//                        object.put("queryDate", "2016-11-30");
////                        StringEntity entity = new StringEntity(object.toString(), "utf-8");
//                        String str = new String(object.toString());
//
//                        body = object.toString().getBytes("utf-8");
//                        System.out.println("======object.toString()====" + object.toString());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                    return body;
                }
            };
        }
        request.setRetryPolicy(new DefaultRetryPolicy(){
            @Override
            public int getCurrentRetryCount() {
                return 1;
            }
        });
//        request.setRetryPolicy(new DefaultRetryPolicy(DEFAULT_VALUE_TIMEOUT_INTERVAL * 1000, 1, 1.0f){
//            @Override
//            public int getCurrentRetryCount() {
//                return 1;
//            }
//        });

        request.setTag(this);

        VolleyUtil.getQueue(mContext).add(request);
    }

    @Override
    public <T> void Pluginrequest(int method, final Map<String, String> params, Map<String, File> fileParams, final Class<T> cls, final AppCallback<T> callback, final LoadingInterface li, boolean timeout) {
        if (null != li) {
            li.start();
        }

        VolleyUtil.getQueue(mContext).cancelAll(this);

        StringRequest request = null;

//        request.setTag("my tag");
//        if(timeout){
//            request.setRetryPolicy(new DefaultRetryPolicy(SiXinApplication.timeoutInterval * 1000, 1, 1.0f));
//        }

        Response.Listener<String> listener = new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                try {

//                        //服务器未转码， 这里需转码
//                        s = new String(s.getBytes("iso8859-1"), "UTF-8");

                    Log.e(TAG,"read json: " + s);
//                    Log.e(TAG, "read json: " + s);
                    T t = mGson.fromJson(s, cls);
                    callback.callbackString(s);
                    callback.callback(t);
                } catch (Exception e) {
//                    callback.onError(new Exception(e.toString()));
                    Log.e(TAG, "请处理此异常", e);
                } finally {
                    if (null != li) {
                        li.finish();
                    }
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.d("abd", "Error: " + error
//                        + ">>" + error.networkResponse.statusCode
//                        + ">>" + error.networkResponse.data
//                        + ">>" + error.getCause()
//                        + ">>" + error.getMessage());
                if (null != li) {
                    li.finish();
                }

                if (error instanceof TimeoutError) {
                    callback.onError(new Exception("加载超时"));
                } else if (error instanceof NoConnectionError) {
                    callback.onError(new Exception("网络连接失败,请检查网络设置!"));
                } else {
                    callback.onError(new Exception("网络错误"));
                }

            }
        };

        if (method == Request.Method.GET) {
            Log.d("abd","" + mUrl + formatParameter(params));
            request = new StringRequest(mUrl + formatParameter(params), listener, errorListener);
        } else {
            request = new StringRequest(method, mUrl, listener, errorListener) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return params;
                }
            };
        }

        if(timeout){
            request.setRetryPolicy(new DefaultRetryPolicy(){
                @Override
                public int getCurrentRetryCount() {
                    return 1;
                }
            });
//            request.setRetryPolicy(new DefaultRetryPolicy(DEFAULT_VALUE_TIMEOUT_INTERVAL * 1000, 1, 1.0f){
//
//                @Override
//                public int getCurrentRetryCount() {
//                    return 1;
//                }
//            });
        }

        request.setTag(this);

        VolleyUtil.getQueue(mContext).add(request);
    }

    @Override
    public void cancel() {
        VolleyUtil.getQueue(mContext).cancelAll(this);
    }


}
