package com.perry.http.request;


import com.perry.http.manager.Request;

import java.util.Map;

/**
 * Created by lipengjun on 18/4/12.
 */
public class GetCordovaNetworkRequest extends Request {

    private String url;

    private int method;

    private Map<String, String> plmap;


    public GetCordovaNetworkRequest(String url, int method, Map<String, String> map) {
        this.url = url;
        this.method = method;
        this.plmap = map;
    }

    @Override
    public String url() {
        return url;
    }

    @Override
    public int method() {
        if(method == 1){
            return RequestMethod.POST;
        }else if (method == 0){
            return RequestMethod.GET;
        }
        return RequestMethod.GET;
    }

    @Override
    public Map<String, String> getParameter() {
        return plmap;
    }

}
