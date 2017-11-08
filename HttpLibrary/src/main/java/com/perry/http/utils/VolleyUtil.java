package com.perry.http.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by rqyuea on 2015/12/23 0023.
 */
public class VolleyUtil {

    private static RequestQueue requestQueue = null;

    public static RequestQueue getQueue(Context context) {

        if (null == requestQueue) {
                synchronized (VolleyUtil.class) {
                    if (null == requestQueue) {
                        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                    }
                }
        }
        return requestQueue;
    }

}
