package com.perry.http.Listener;

/**
 * Created by rqyuea on 2015/12/23 0023.
 */
public interface AppCallback<T> {
    /**
     * 自动解析成bean；如果字段名称和服务器返回名称有变动就会出现数据异常信息
     * @param t
     */
    void callback(T t);

    /**
     * 手动解析json字符串
     * @param str
     */
    void callbackString(String str);
    void onError(Exception e);
}
