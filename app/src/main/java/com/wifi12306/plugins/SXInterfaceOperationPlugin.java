package com.wifi12306.plugins;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;


/**
 * 9	界面操作 插件调用 plugin
 * <br>use javascript call like:
 * <br>cordova.exec(succ, fail, 'SXInterfaceOperationPlugin', 'changeNavTitle',[])
 * <br> 方法名:changeNavTitle  &nbsp;&nbsp;//改变导航条标题
 * <br> 方法名:hideOptionMenu &nbsp;&nbsp;//隐藏右上角菜单
 * <br> 方法名:showOptionMenu  &nbsp;&nbsp;//显示右上角菜单
 * <br> 方法名:hideMenuItems &nbsp;&nbsp;//批量隐藏功能按钮
 * <br> 方法名:showMenuItems  &nbsp;&nbsp;//批量显示功能按钮
 * <br> 方法名:showAllMenuItems &nbsp;&nbsp;//显示所有功能按钮
 * <br> 方法名:setOptionMenuWithShareEvent  &nbsp;&nbsp;//设置右上角按钮点击
 * <br> 方法名:setOptionMenuWihtDefaultEvent &nbsp;&nbsp;//设置右上角按钮点击
 * <br> 方法名:closeWindow  &nbsp;&nbsp;//关闭当前网页窗口
 * <br> 当点击客户端取消按钮时: 传递给plugin的success方法回调参数为JSONArray字符串:
 * <br>["cancel"]
 * <br> 改变导航条标题 changeNavTitle 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 隐藏右上角菜单 hideOptionMenu 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 显示右上角菜单 showOptionMenu 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 批量隐藏功能按钮 hideMenuItems 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 批量显示功能按钮 showMenuItems 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 显示所有功能按钮 showAllMenuItems 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 设置右上角按钮点击 setOptionMenuWithShareEvent 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 设置右上角按钮点击 setOptionMenuWihtDefaultEvent 当客户端请求成功后: 传递给plugin的success方法数据为Arraylist的jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 关闭当前网页窗口 closeWindow 当客户端请求成功后: 传递给plugin的success方法数据为Arraylist的jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 当回调客户端页面的Intent传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:intent is null"
 * <br> 当回调客户端页面的ArrayList<String> users传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:users is null"
 * <br>
 * <p>
 * <br>Created by issuser on 16/5/16.
 */
public class SXInterfaceOperationPlugin extends BasePlugin {

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
//        return super.execute(action, args, callbackContext);
        return true;
    }
}
