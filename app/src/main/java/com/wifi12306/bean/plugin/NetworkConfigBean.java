package com.wifi12306.bean.plugin;

/**
 * Created by issuser on 16/5/11.
 */
public class NetworkConfigBean {
    public int timeoutInterval;
    public boolean isValidatesSecureCertificate;
    public staticParams s = new staticParams();

    public class staticParams{
        public String sysVer;
        public String devicemModel;
        public String sysType;
    }
}
