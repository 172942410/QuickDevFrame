
package com.perry.http.parser;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 接口预加载注册模式
 *
 *
 */
public class ConfigXmlParser {
    private static String TAG = "ConfigXmlParser";

//    private String launchUrl = "file:///android_asset/www/index.html";
//    private CordovaPreferences prefs = new CordovaPreferences();
    private static ArrayList<HttpUrlEntry> httpUrlEntries = new ArrayList<HttpUrlEntry>();
    private static List<String> requestclass = new ArrayList<String>();

//    public CordovaPreferences getPreferences() {
//        return prefs;
//    }

    public static ArrayList<HttpUrlEntry> getPluginEntries() {
        return httpUrlEntries;
    }

    public static List<String> getRequestclass() {
        return requestclass;
    }

//    public String getLaunchUrl() {
//        return launchUrl;
//    }

    public void parse(Context action,String xmlName) {
        // First checking the class namespace for config.xml
        int id = action.getResources().getIdentifier(xmlName, "xml", action.getClass().getPackage().getName());
        if (id == 0) {
            // If we couldn't find config.xml there, we'll look in the namespace from AndroidManifest.xml
            id = action.getResources().getIdentifier(xmlName, "xml", action.getPackageName());
            if (id == 0) {
                Log.e(TAG, "res/xml/"+xmlName+".xml 文件未找到，无法读取初始化请求地址信息!");
                return;
            }
        }
        parse(action.getResources().getXml(id));
    }
    public void parse(Context action) {
        // First checking the class namespace for config.xml
        int id = action.getResources().getIdentifier("httpurl", "xml", action.getClass().getPackage().getName());
        if (id == 0) {
            // If we couldn't find config.xml there, we'll look in the namespace from AndroidManifest.xml
            id = action.getResources().getIdentifier("httpurl", "xml", action.getPackageName());
            if (id == 0) {
                Log.e(TAG, "res/xml/httpurl.xml 文件未找到，无法读取初始化请求地址信息!");
                return;
            }
        }
        parse(action.getResources().getXml(id));
    }

    boolean insideFeature = false;
    /* 地址名称，url地址，url类型*/
    String urlName = "", urlAddress = "", paramType = "",urlTitle = "";
//    boolean onload = false;
    private static String urlHeader;
    public void parse(XmlPullParser xml) {
        int eventType = -1;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                handleStartTag(xml);
            }
            else if (eventType == XmlPullParser.END_TAG)
            {
                handleEndTag(xml);
            }
            try {
                eventType = xml.next();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG,"httpUrlEntries:"+httpUrlEntries.toString());
    }

    public void handleStartTag(XmlPullParser xml) {
        String strNode = xml.getName();
        if (strNode.equals("feature")) {
            //Check for supported feature sets  aka. plugins (Accelerometer, Geolocation, etc)
            //Set the bit for reading params
            insideFeature = true;
            urlName = xml.getAttributeValue(null, "name");
        }
        else if (insideFeature && strNode.equals("param")) {
            paramType = xml.getAttributeValue(null, "type");
            urlTitle = xml.getAttributeValue(null,"title");
            if (paramType.equals("http-url"))
                urlAddress = xml.getAttributeValue(null,"value");
            else if (paramType.equals("http-suffix")){
                urlAddress = xml.getAttributeValue(null,"value");
                urlAddress = urlHeader + urlAddress;
            }
//                onload = "true".equals(xml.getAttributeValue(null, "value"));
        }
//        else if (strNode.equals("preference")) {
//            String name = xml.getAttributeValue(null, "name").toLowerCase(Locale.ENGLISH);
//            String value = xml.getAttributeValue(null, "value");
//            prefs.set(name, value);
//        }
        else if (strNode.equals("header")) {
            if(TextUtils.isEmpty(urlHeader)) {
                urlHeader = xml.getAttributeValue(null, "url");
                Log.e(TAG,",新解析的 urlHeader："+urlHeader);
            }else{
                Log.e(TAG,",已经存在的 urlHeader："+urlHeader);
            }
            if (urlHeader != null) {
//                setStartUrl(src);
            }
        }
    }

    public void handleEndTag(XmlPullParser xml) {
        String strNode = xml.getName();
        if (strNode.equals("feature")) {
            requestclass.add(urlName);
            httpUrlEntries.add(new HttpUrlEntry(urlName, urlAddress, urlTitle));

            urlName = "";
            urlAddress = "";
            insideFeature = false;
//            onload = false;
        }
    }

    public static String getUrlHeader(){

        return urlHeader;
    }

    /**
     * 在 applicatioin 中赋值调用了
     * @param hostUrl
     */
    public static void setUrlHeader(String hostUrl){
        urlHeader = hostUrl;
    }
//    private void setStartUrl(String src) {
//        Pattern schemeRegex = Pattern.compile("^[a-z-]+://");
//        Matcher matcher = schemeRegex.matcher(src);
//        if (matcher.find()) {
//            launchUrl = src;
//        } else {
//            if (src.charAt(0) == '/') {
//                src = src.substring(1);
//            }
//            launchUrl = "file:///android_asset/www/" + src;
//        }
//    }
}
