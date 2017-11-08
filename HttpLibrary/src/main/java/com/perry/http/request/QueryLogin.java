package com.perry.http.request;


import com.perry.http.manager.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 */
public class QueryLogin extends Request {

    private String loginName;
    private String password;

    public QueryLogin(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    @Override
    public int method() {
        return RequestMethod.POST;
    }

//    {"from_station":"VNP","to_station":"AOH","queryDate":"2016-11-30"}

    @Override
    public Map<String, String> getParameter() {
        Map<String, String> map = new HashMap<String,String>();
        map.put("loginName",loginName);
        map.put("password",password);
        return map;
    }

}
