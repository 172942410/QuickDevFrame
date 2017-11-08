package com.perry.http.proxy;

import android.content.Context;
import android.widget.Toast;


import com.perry.http.Listener.AppCallback;
import com.perry.http.Listener.LoadingInterface;
import com.perry.http.Listener.ProgressCallback;
import com.perry.http.uploadfile.SxHttpClient;
import com.perry.http.utils.NetUtil;

import java.io.File;
import java.util.Map;

/**
 * Created by isoftstone on 16/3/11.
 */
public class SxClient extends AbsClient {

    public SxClient(Context context, String url) {
        super(context, url);
    }

    @Override
    public <T> void request(int method, Map<String, String> params, Map<String, File> fileParams, final Class<T> cls, final AppCallback<T> callback, final LoadingInterface li) {

        if (!NetUtil.checkNet(mContext)) {
            Toast.makeText(mContext, " 请检查网络连接状况  ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (li != null) {
            li.start();
        }
        SxHttpClient client = new SxHttpClient(mContext);

        client.setProgressListener(new SxHttpClient.ProgressListener() {
            @Override
            public void onSuccess(String json) {
                if (li != null) {
                    li.finish();
                }
                T t = mGson.fromJson(json, cls);
                callback.callback(t);

            }

            @Override
            public void onFailure(Exception ex) {
                if (li != null) {
                    li.finish();
                }
                callback.onError(ex);
            }

            @Override
            public void onProcess(long current, long total) {
                if (callback instanceof ProgressCallback) {
                    ProgressCallback p = (ProgressCallback) callback;
                    p.onProgress((int) current, (int) total);
                }
            }
        });

        client.uploadFile(mUrl, params, fileParams, true);
    }

    @Override
    public <T> void Pluginrequest(int method, Map<String, String> params, Map<String, File> fileParams, Class<T> cls, AppCallback<T> callback, LoadingInterface li, boolean timeout) {

    }

    @Override
    public void cancel() {

    }
}
