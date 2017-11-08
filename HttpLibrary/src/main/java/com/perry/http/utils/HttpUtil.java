package com.perry.http.utils;

import android.util.Log;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Created by issuser on 5/11/16.
 */
public class HttpUtil {

    //appkey
    public static final String Appkey = "ceaa74b3-3df0-432d-8e88-fe2aab969f52";
    public static final String AppSecrity = "F8E2FCEA3C7A40318EDBF7E23904FEEC";

//    sign加密规则：
//    参数key经过排序后拼接：如key=value&key=value进行md5（不包含sign）

    /**
     * 参数签名
     *
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String sign(Map<String ,String> map)
    {
        try
        {
            if (null == map || map.size() <= 0)
            {
                return null;
            }
            Object[] keys = map.keySet().toArray();
            Arrays.sort(keys);
            StringBuffer sbuf = new StringBuffer();
            for (int i = 0; i < keys.length; i++)
            {
                String key = keys[i].toString().trim();
                if (key.equals("file") || key.equals("sign"))
                {
                    continue;
                }
                sbuf.append(key);
                sbuf.append("=");
                sbuf.append(map.get(key).length() > 0 ? map.get(key) : "");
                sbuf.append("&");
            }
            if (sbuf.length() <= 0)
                return null;
//            String sign = sbuf.delete(sbuf.lastIndexOf("&"), sbuf.length()).toString();
//            sign = URLEncoder.encode(sign, "utf-8");
            Log.e("subf       :       ", sbuf.toString());
            return MD5Util.md5Hex((sbuf.delete(sbuf.lastIndexOf("&"), sbuf.length()).toString()+ AppSecrity).getBytes(Charset.forName("UTF-8")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public static Map<String,String> signHttpParams(Map<String, String> params) {

        if(params == null){
            params = new HashMap<>();
        }
        params.put("AppKey", Appkey);
        params.put("randomStr", String.valueOf(System.currentTimeMillis()).substring(0,8));
        params.put("uuid", UUID.randomUUID().toString());
//        Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
//        now.getTime();
//        new Date().getTime();
        params.put("timeStamp", System.currentTimeMillis() + "");
//        params.put("timeStamp","1464576720000");

        String sign = HttpUtil.sign(params);

        params.put("sign",sign);


        return params;
    }


    /**
     * 参数签名
     *
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String pluginsign(Map<String ,String> map)
    {
        try
        {
            if (null == map || map.size() <= 0)
            {
                return null;
            }
            Object[] keys = map.keySet().toArray();
            Arrays.sort(keys);
            StringBuffer sbuf = new StringBuffer();
            for (int i = 0; i < keys.length; i++)
            {
                String key = keys[i].toString().trim();
                if (key.equals("file") || key.equals("sign"))
                {
                    continue;
                }
                sbuf.append(key);
                sbuf.append("=");
                //没有以下判断代码会导致html使用插件时网络异常现象
                String value = map.get(key);
                if(value != null) {
                    sbuf.append(value.length() > 0 ? value : "");
                }else{
                    sbuf.append("");
                }
                sbuf.append("&");
            }
            if (sbuf.length() <= 0) {
                return null;
            }
            return MD5Util.md5Hex(sbuf.delete(sbuf.lastIndexOf("&"), sbuf.length()).toString());
//            return MD5Util.md5Hex((sbuf.delete(sbuf.lastIndexOf("&"), sbuf.length()).toString()+ SiXinApplication.pluginsecurity).getBytes(Charset.forName("UTF-8")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public static Map<String,String> pluginsignHttpParams(Map<String, String> params) {
        if(params == null){
            params = new HashMap<>();
        }
//        params.put("AppKey", pluginappUUID);
        params.put("randomStr", String.valueOf(System.currentTimeMillis()).substring(0,8));
        params.put("uuid", UUID.randomUUID().toString());
        params.put("timeStamp", System.currentTimeMillis() + "");

        String sign = HttpUtil.pluginsign(params);
        params.put("sign",sign);
        return params;
    }

    /**
     * get请求添加签名
     * @param map
     * @param url
     * @return
     */
    public static String SignHttpGetUrl(Map<String,String> map, String url){

        Map<String,String> params = HttpUtil.signHttpParams(map);

        StringBuffer signUrl = new StringBuffer();
        signUrl.append(url+"?");

        for (Iterator i = params.keySet().iterator(); i.hasNext();) {
            Object obj = i.next();
            System.out.println(obj);// 循环输出key
            System.out.println("key=" + obj + " value=" + params.get(obj));

            if (i.hasNext()){
                signUrl.append(obj+"="+params.get(obj)+"&");
            }else{
                signUrl.append(obj+"="+params.get(obj));
            }

        }



        return signUrl.toString();
    }

}
