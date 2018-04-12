package com.wifi12306.plugins;

import android.util.Log;

/**
 * Created by lipengjun on 2017/12/13.
 * JS 注入 视频播放的横竖屏
 */

public class JsInjectOrientationVideo {
    private static final String TAG = "JsInjectOrientationVideo";

    /**
     * Js注入
     * @param url 加载的网页地址
     * @return 注入的js内容，若不是需要适配的网址则返回空javascript
     */
    public static String fullScreenByJs(String url){
//        http://op.inews.qq.com/m/20171212V0CL1W00?refer=100000158&chl_code=1494449&h=0
        String refer = referParser(url);
        log("执行全屏JS refer:"+refer);
        if (null != refer) {
            return "javascript:document.getElementsByClassName('" + referParser(url) + "')[0].addEventListener('click',function(){local_obj.playing();return false;});";
        }else {
            return "javascript:";
        }
    }


    public static String clickFullScreenByJs(String url){
//        http://op.inews.qq.com/m/20171212V0CL1W00?refer=100000158&chl_code=1494449&h=0
        String refer = referParser(url);
        log("执行全屏JS refer:"+refer);
        if (null != refer) {
            return "javascript:document.getElementsByClassName('" + referParser(url) + "')[0].click();";
        }else {
            return "javascript:";
        }
    }

    private static void log(String msg) {
        Log.d(TAG,msg);
    }

    /**
     * 对不同的视频网站分析相应的全屏控件
     * @param url 加载的网页地址
     * @return 相应网站全屏按钮的class标识
     */
    public static String referParser(String url){
        if (url.contains("letv")) {
            return "hv_ico_screen";               //乐视Tv
        }else if (url.contains("youku")) {
            return "x-zoomin";                    //优酷
        }else if (url.contains("bilibili")) {
            return "icon-widescreen";             //bilibili
        }else if (url.contains("qq")) {
            return "tvp_fullscreen_button";       //腾讯视频
        }

        return null;
    }
}
