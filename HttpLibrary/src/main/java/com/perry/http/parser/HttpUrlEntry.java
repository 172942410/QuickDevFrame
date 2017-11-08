package com.perry.http.parser;

import java.io.Serializable;

/**
 * 初始化加载的http url 请求对象
 *
 * Created by lipengjun on 2016/11/29.
 */

public final class HttpUrlEntry implements Serializable {
    public final String urlName;
    public final String urlAddress;
    public final String urlTitle;
    public HttpUrlEntry(String urlName, String urlAddress, String urlTitle) {
        this.urlName = urlName;
        this.urlAddress = urlAddress;
        this.urlTitle = urlTitle;
    }

    @Override
    public String toString() {
        return "HttpUrlEntry{" +
                "urlName='" + urlName + '\'' +
                ", urlAddress='" + urlAddress + '\'' +
                ", urlTitle='" + urlTitle + '\'' +
                '}';
    }
}
