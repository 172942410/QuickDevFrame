package com.perry.http.utils;

import android.util.Log;

/**
 * Created by lipengjun on 2017/2/20.
 */

public class LogUtil {

    /**
     * 截断输出日志
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0) {
//            return;
            tag = "LogUtil";
        }

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize ) { // 长度小于等于限制直接打印
            Log.e(tag, msg);
        }else {
            while (msg.length() > segmentSize ) { // 循环分段打印日志
                String logContent = msg.substring(0, segmentSize );
                Log.e(tag, logContent);
                msg = msg.replace(logContent, "");
            }
            Log.e(tag, msg);// 打印剩余日志
        }
    }
}
