package com.perry.http.proxy;

import android.content.Context;

import com.google.gson.Gson;
import com.perry.http.Listener.AppCallback;
import com.perry.http.Listener.LoadingInterface;

import java.io.File;
import java.util.Map;

/**
 * Created by isoftstone on 16/3/11.
 */
public abstract class AbsClient {

       protected Context mContext;

       protected String mUrl;

       protected Gson mGson;

       public AbsClient(Context context, String url) {
              this.mContext = context;
              mUrl = url;

              mGson = new Gson();
       }

       public String formatParameter(Map<String, String> params) {

              StringBuilder sb = new StringBuilder();

              if (params != null && params.size() > 0) {
                     sb.append("?");
//                     sb.append("&");
                     for (Map.Entry<String, String> entry : params.entrySet()) {
                            sb.append(entry.getKey() + "=" + entry.getValue());
                            sb.append("&");
                     }
                     sb.deleteCharAt(sb.length() - 1);
              }
              return sb.toString();
       }

       public abstract <T> void request(int method, Map<String, String> params, Map<String, File> fileParams, Class<T> cls, AppCallback<T> callback, LoadingInterface li);

       public abstract <T> void Pluginrequest(int method, Map<String, String> params, Map<String, File> fileParams, Class<T> cls, AppCallback<T>callback, LoadingInterface li, boolean timeout);

       public abstract void cancel();
}
