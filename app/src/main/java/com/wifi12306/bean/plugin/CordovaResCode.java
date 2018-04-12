package com.wifi12306.bean.plugin;

import java.util.Map;

/**
 * Created by issuser on 16/5/11.
 */
public class CordovaResCode {

    public boolean isOK;
    public String paramsJsonStr;
    public Map<String, Object> paramsDic;
    public Map<String, Object> resultDic;
    public String resultJsonStr;
    public String resCode = "0";
    public String resMsg = "OK";

    @Override
    public String toString() {
        return "{" +
                "resCode='" + resCode + '\'' +
                ", resMsg='" + resMsg + '\'' +
                '}';
    }
}
