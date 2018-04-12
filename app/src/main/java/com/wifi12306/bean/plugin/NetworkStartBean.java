package com.wifi12306.bean.plugin;

/**
 * Created by issuser on 16/5/11.
 */
public class NetworkStartBean {
    public boolean isShowLoading;
    public int method;
    public boolean isUseStaticParams;
    public String url;
    public String cancelTag;

    public requestParams requestparams = new requestParams();

    public class requestParams{
        public String device;
        public String type;
        public String version;
    }
}
