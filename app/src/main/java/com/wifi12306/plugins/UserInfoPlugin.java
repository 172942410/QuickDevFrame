package com.wifi12306.plugins;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.wifi12306.bean.plugin.CordovaResCode;
import com.wifi12306.bean.plugin.GetUserInfoBean;
import com.wifi12306.plugins.utils.CordovaContest;
import com.wifi12306.plugins.utils.CordovaUtils;
import com.wifi12306.utils.ConsUtil;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户信息 插件调用 plugin
 * <br>use javascript call like:
 * <br>cordova.exec(succ, fail, 'SXUserInfoPlugin', 'getUserInfo',[])
 * <br> 方法名:getUserInfo  &nbsp;&nbsp;//获取司信当前用户信息
 * <br> 方法名:getUserRecentContacts &nbsp;&nbsp;//获取司信当前用户最近联系人接口
 * <br> 方法名:getEmployeeInfo  &nbsp;&nbsp;//获取员工信息
 * <br> 方法名:chooseContacts &nbsp;&nbsp;//选择联系人
 * <br> 方法名:chooseOrganization  &nbsp;&nbsp;//选择组织架构
 * <br> 方法名:getOrganizationInfo &nbsp;&nbsp;//获取组织架构
 * <br> 方法名:viewEmployeeInfo  &nbsp;&nbsp;//给指定员工发消息
 * <br> 方法名:sendMsgToEmployee &nbsp;&nbsp;//给指定员工发消息
 * <br> 当点击客户端取消按钮时: 传递给plugin的success方法回调参数为JSONArray字符串:
 * <br> ["cancel"]
 * <br> 获取司信当前用户信息 getUserInfo 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "userId":"33209"              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "headImgUrl":""          &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "largeHeadImgUrl":""        &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "name":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "sex":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "mobilePhone":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "telphone":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "email":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "sign":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "employeeNum":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "company":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "department":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "post":""   &nbsp;&nbsp;//可能为空
 * <br> }
 * <br> 获取司信当前用户最近联系人接口 getUserRecentContacts 当客户端请求成功后: 传递给plugin的success方法数据为Arraylist的jsonObject字符串:
 * <br> {
 * <br> contacts:[{
 * <br>  &nbsp;&nbsp;&nbsp;  "userId":"33209"              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "headImgUrl":""          &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "name":""        &nbsp;&nbsp;//可能为空
 * <br> },
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "userId":""              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "headImgUrl":""          &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "name":""        &nbsp;&nbsp;//可能为空
 * <br> }]
 * <br> }
 * <br> 获取员工信息 getEmployeeInfo 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "userId":"33209"              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "headImgUrl":""          &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "largeHeadImgUrl":""        &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "name":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "sex":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "mobilePhone":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "telphone":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "email":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "sign":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "employeeNum":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "company":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "department":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "post":""   &nbsp;&nbsp;//可能为空
 * <br> }
 * <br> 选择联系人 chooseContacts 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br> contacts:[
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "userId":"33209"              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "headImgUrl":""          &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "largeHeadImgUrl":""        &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "name":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "sex":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "mobilePhone":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "telphone":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "email":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "sign":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "employeeNum":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "company":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "department":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "post":""   &nbsp;&nbsp;//可能为空
 * <br> },{
 * <br>  &nbsp;&nbsp;&nbsp;  "userId":"33209"              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "headImgUrl":""          &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "largeHeadImgUrl":""        &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "name":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "sex":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "mobilePhone":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "telphone":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "email":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "sign":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "employeeNum":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "company":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "department":""   &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "post":""   &nbsp;&nbsp;//可能为空
 * <br> }]
 * <br> }
 * <br> 选择组织架构 chooseOrganization 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "orgId":""              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "name":""          &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "memberNumber":""        &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "parentId":""   &nbsp;&nbsp;//可能为空
 * <br> }
 * <br> 获取组织架构 getOrganizationInfo 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "orgId":""              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "name":""          &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "memberNumber":""        &nbsp;&nbsp;//可能为空
 * <br>  &nbsp;&nbsp;&nbsp;  "parentId":""   &nbsp;&nbsp;//可能为空
 * <br> }
 * <br> 跳转员工详情页 viewEmployeeInfo 当客户端请求成功后: 传递给plugin的success方法数据为Arraylist的jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 给指定员工发消息 sendMsgToEmployee 当客户端请求成功后: 传递给plugin的success方法数据为Arraylist的jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 当回调客户端页面的Intent传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:intent is null"
 * <br> 当回调客户端页面的ArrayList<String> users传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:users is null"
 * <br>
 * <br>Created by issuser on 16/5/16.
 */
public class UserInfoPlugin extends BasePlugin {
    private static final String TAG = "SXUserInfoPlugin";
    public static CallbackContext callbackContext;
    private Activity activity;

    CordovaContest cordovaContest;
    CordovaResCode cordovaResCode;
    private String selectType;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if(selectType.equals("0")){
                        Map<String, List<GetUserInfoBean>> map = new HashMap<>();
                        map.put("contacts", sxGetUserInfoBeens);
                        sendPluginSucces(activity,callbackContext,map.toString());
                        sxGetUserInfoBeens.clear();
                    }else {
                        sendPluginSucces(activity,callbackContext,sxGetUserInfoBean.toString());
                        sxGetUserInfoBeens.clear();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        cordovaResCode = new CordovaResCode();
        cordovaContest = new CordovaContest();
        activity = this.cordova.getActivity();
        if(PluginUntil.getList().contains(action)){
            if (action.equals(PluginUntil.getUserInfo)) {
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
//                if(SiXinApplication.isGetUserInfo){
                    cordovaResCode = cordovaContest.initWithDictionaryParameters(args);
                    if(cordovaResCode.isOK){
                        for (int i = 0; i < args.length(); i++) {
                            JSONObject jsonObject = args.getJSONObject(i);
                            Object objget = jsonObject.opt("isGetDetailInfo");
                            boolean booget = cordovaContest.judgeEmpty(objget,2);
                            boolean getDetai;
                            if(booget){
                                getDetai = Boolean.valueOf(objget.toString());
                            }else {
                                getDetai = CordovaUtils.DEFAULT_BOOL_GET_DETALL_USER_INFO;
                            }
                            if(getDetai){
                            }else {
                            }
                        }
                    }else {
                    sendPluginError(activity,callbackContext,CordovaUtils.RESCODE_NO_PARAMETER,CordovaUtils.RESMSG_NO_PARAMETER);
                        return true;
                    }
                }
            } else if (action.equals(PluginUntil.getUserRecentContacts)) {
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
            } else if (action.equals(PluginUntil.getEmployeeInfo)) {
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    String userId = null;
                    for (int i = 0; i < args.length(); i++) {
                        JSONObject jsob = args.getJSONObject(i);
                        Object objnonce = jsob.opt("userId");
                        if(objnonce != null && !objnonce.equals("")){
                            String time = objnonce.toString();
                            cordovaResCode = cordovaContest.judgeNull(time,"userId");
                            if(cordovaResCode.isOK){
                                boolean strnonce = cordovaContest.necessary(objnonce,0);
                                if(strnonce){
                                    userId = time;
                                }else {
                                    cordovaResCode = cordovaContest.setResultWithErrResultType(0,"userId");
                                    sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                    return true;
                                }
                            }else {
                                cordovaResCode = cordovaContest.setResultWithErrResultType(1,"userId");
                                sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                return true;
                            }
                        }else {
                            cordovaResCode = cordovaContest.setResultWithErrResultType(1,"userId");
                            sendPluginError(activity,callbackContext,cordovaResCode.toString());
                            return true;
                        }
                    }
                    cordovaResCode = cordovaContest.judgeString(userId);
                        sendPluginError(activity,callbackContext, CordovaUtils.RESCODE_INVALID_PARAMETER_FORMAT, CordovaUtils.RESCODE_INVALID_USERID);
                    }
                }
            } else if (action.equals(PluginUntil.chooseContacts)) {
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    cordovaResCode = cordovaContest.initWithDictionaryParameters(args);
                    if(cordovaResCode.isOK){
                        for (int i = 0; i < args.length(); i++) {
                            JSONObject jsob = args.getJSONObject(i);
                            Object objnonce = jsob.opt("selectType");
                            if(objnonce != null && !objnonce.equals("")){
                                Object strnonce = cordovaContest.defualt(objnonce,1);
                                if(strnonce != null && !strnonce.equals("")){
                                    String time = strnonce.toString();
                                    cordovaResCode = cordovaContest.judgeNull(time,"selectType");
                                    if(cordovaResCode.isOK){
                                        selectType = time;
                                    }else {
                                        selectType = "0";
                                    }
                                }else {
                                    selectType = "0";
                                }
                            }else {
                                selectType = "0";
                            }
                        }
                        if (selectType != null && !selectType.equals("") && selectType.equals("0")) {
//                            Intent intent = new Intent(cordova.getActivity(), ContactActivity.class);
//                            intent.putExtra("tab_type", ConsUtil.d_tab_type_f8);
//                            cordova.startActivityForResult(this, intent, ConsUtil.REQ_FROM_CONTANTS_TO_SEARCHACT);
                            return true;
                        } else if (selectType != null && !selectType.equals("") && selectType.equals("1")) {
//                            Intent intent = new Intent(cordova.getActivity(), ContactActivity.class);
//                            intent.putExtra("tab_type", ConsUtil.d_tab_type_f9);
//                            cordova.startActivityForResult(this, intent, ConsUtil.REQ_FROM_CONTANTS_TO_SEARCHACT);
                            return true;
                        }else {
//                            Intent intent = new Intent(cordova.getActivity(), ContactActivity.class);
//                            intent.putExtra("tab_type", ConsUtil.d_tab_type_f8);
//                            cordova.startActivityForResult(this, intent, ConsUtil.REQ_FROM_CONTANTS_TO_SEARCHACT);
                            return true;
                        }
                    }else {
                        sendPluginError(activity,callbackContext,cordovaResCode.toString());
                    }

                }
            } else if (action.equals(PluginUntil.chooseOrganization)) {
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
//                    Intent intent = new Intent(cordova.getActivity(), ContactOrgStructureActivity.class);
//                    intent.putExtra("tab_type", ConsUtil.d_tab_type_f8);
//                    cordova.startActivityForResult(this, intent, ConsUtil.PLUGIN_ORG_STRUCTURE);
                    return true;
                }
            } else if (action.equals(PluginUntil.getOrganizationInfo)) {
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    String orgId = null;
                    for (int i = 0; i < args.length(); i++) {
                        JSONObject jsob = args.getJSONObject(i);
                        Object objnonce = jsob.opt("orgId");
                        if(objnonce != null && !objnonce.equals("")){
                            String time = objnonce.toString();
                            cordovaResCode = cordovaContest.judgeNull(time,"orgId");
                            if(cordovaResCode.isOK){
                                boolean strnonce = cordovaContest.necessary(objnonce,0);
                                if(strnonce){
                                    orgId = time;
                                }else {
                                    cordovaResCode = cordovaContest.setResultWithErrResultType(0,"orgId");
                                    sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                    return true;
                                }
                            }else {
                                cordovaResCode = cordovaContest.setResultWithErrResultType(1,"orgId");
                                sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                return true;
                            }
                        }else {
                            cordovaResCode = cordovaContest.setResultWithErrResultType(1,"orgId");
                            sendPluginError(activity,callbackContext,cordovaResCode.toString());
                            return true;
                        }
                    }
                        sendPluginError(activity,callbackContext, CordovaUtils.RESCODE_INVALID_PARAMETER_FORMAT, CordovaUtils.RESCODE_INVALID_USERID);
                }
            } else if (action.equals(PluginUntil.viewEmployeeInfo)) {
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    String userId = null;
                    for (int i = 0; i < args.length(); i++) {
                        JSONObject jsob = args.getJSONObject(i);
                        Object objnonce = jsob.opt("userId");
                        if(objnonce != null && !objnonce.equals("")){
                            String time = objnonce.toString();
                            cordovaResCode = cordovaContest.judgeNull(time,"userId");
                            if(cordovaResCode.isOK){
                                boolean strnonce = cordovaContest.necessary(objnonce,0);
                                if(strnonce){
                                    userId = time;
                                }else {
                                    cordovaResCode = cordovaContest.setResultWithErrResultType(0,"userId");
                                    sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                    return true;
                                }
                            }else {
                                cordovaResCode = cordovaContest.setResultWithErrResultType(1,"userId");
                                sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                return true;
                            }
                        }else {
                            cordovaResCode = cordovaContest.setResultWithErrResultType(1,"userId");
                            sendPluginError(activity,callbackContext,cordovaResCode.toString());
                            return true;
                        }
                    }
                }
            } else if (action.equals(PluginUntil.sendMsgToEmployee)) {
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    String userId = null;
                    for (int i = 0; i < args.length(); i++) {
                        JSONObject jsob = args.getJSONObject(i);
                        Object objnonce = jsob.opt("userId");
                        if(objnonce != null && !objnonce.equals("")){
                            String time = objnonce.toString();
                            cordovaResCode = cordovaContest.judgeNull(time,"userId");
                            if(cordovaResCode.isOK){
                                boolean strnonce = cordovaContest.necessary(objnonce,0);
                                if(strnonce){
                                    userId = time;
                                }else {
                                    cordovaResCode = cordovaContest.setResultWithErrResultType(0,"userId");
                                    sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                    return true;
                                }
                            }else {
                                cordovaResCode = cordovaContest.setResultWithErrResultType(1,"userId");
                                sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                return true;
                            }
                        }else {
                            cordovaResCode = cordovaContest.setResultWithErrResultType(1,"userId");
                            sendPluginError(activity,callbackContext,cordovaResCode.toString());
                            return true;
                        }
                    }
            }
        }else {
            cordovaResCode = cordovaContest.setResultWithErrResultType(2,action);
            sendPluginError(activity,callbackContext,cordovaResCode.toString());
            return true;
        }
        return super.execute(action, args, callbackContext);
    }

    private GetUserInfoBean sxGetUserInfoBean = null;
    private List<GetUserInfoBean> sxGetUserInfoBeens = new ArrayList<>();
    //多选联系人id
    private ArrayList<String> usersID;
    //单选联系人id
    private ArrayList<String> userID;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == ConsUtil.REQ_FROM_CONTANTS_TO_SEARCHACT) {
            if (null != intent) {
                ArrayList<String> users = intent.getStringArrayListExtra("users");
                ArrayList<String> user = intent.getStringArrayListExtra("user");
                if (users != null) {
                    usersID = intent.getStringArrayListExtra("usersID");
                    if (usersID != null && usersID.size() > 0) {
                        for (int i = 0; i < usersID.size(); i++) {
                            String id = usersID.get(i);
                            }

                        }
                        if (usersID.size() == sxGetUserInfoBeens.size()) {
                            Message meg = new Message();
                            meg.what = 1;
                            handler.handleMessage(meg);
                        }

                    }
                }else {
                    }
                }else {
                    sendPluginCancel(activity,callbackContext, CordovaUtils.RESMSG_CANCEL_CHOOSE_CONTACTS);
                }
        //选择架构部门
    }

}