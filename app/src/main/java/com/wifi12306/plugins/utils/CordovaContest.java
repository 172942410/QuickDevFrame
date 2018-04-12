package com.wifi12306.plugins.utils;

import android.app.Activity;

import com.wifi12306.bean.plugin.CordovaResCode;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by issuser on 16/5/11.
 */
public class CordovaContest {

    static CordovaResCode cordovarescode = new CordovaResCode();

    public void initWithSuccessResult(){
        setSuccessResult();
    }

    private void setSuccessResult(){
        cordovarescode.resCode = CordovaUtils.ERRCODE_SUCCESS;
        cordovarescode.resMsg = CordovaUtils.ERRMSG_SUCCESS;
        cordovarescode.isOK = true;
    }

    public void initWithSuccessResultResCode(String resCode, String resMsg){
        cordovarescode.resCode = resCode;
        cordovarescode.resMsg = resMsg;
        cordovarescode.isOK = true;
    }

    public void initWithFailedResultResCode(String resCode, String resMsg){
        cordovarescode.resCode = resCode;
        cordovarescode.resMsg = resMsg;
        cordovarescode.isOK = false;
    }

    public void initWithResCode(String resCode, String resMsg){
        cordovarescode.resCode = resCode;
        cordovarescode.resMsg = resMsg;
    }

    public CordovaResCode initWithDictionaryParameters(JSONArray array) throws JSONException {
        return initWithDictionaryParameters(array,true);
    }


    private CordovaResCode initWithDictionaryParameters(JSONArray arguments, boolean isHasRequiredParams) throws JSONException {
        cordovarescode = new CordovaResCode();
        if (arguments.length() == 0) {
            if (isHasRequiredParams) {
                setNoParameterResult();
            }else{
                setSuccessResult();
            }
            return cordovarescode;
        }else {
            Object j =  arguments.get(0);
            if(j.toString().equals("{}")){
                if (isHasRequiredParams) {
                    setNoParameterResult();
                }else{
                    setSuccessResult();
                }
                return cordovarescode;
            }
            cordovarescode.paramsDic = getMapForJson(j.toString());
            if (cordovarescode.paramsDic == null) {
                setNoParameterResult();
                return cordovarescode;
            }
            setSuccessResult();
        }

        return cordovarescode;
    }

    private void setResultWithErrResultType(int type){
        setResultWithErrResultType(type,null);
    }

    public CordovaResCode setResultWithErrResultType(int type, String key){
        switch (type) {
            case 0:
                cordovarescode.resCode = CordovaUtils.RESCODE_INVALID_PARAMETER_FORMAT;
                if (key!= null && !key.equals("")) {
                    cordovarescode.resMsg = CordovaUtils.RESMSG_INVALID_PARAMETER_FORMAT + "(error key:" + key + ")";
                }else{
                    cordovarescode.resMsg = CordovaUtils.RESMSG_INVALID_PARAMETER_FORMAT;
                }
                return cordovarescode;
            case 1:
                cordovarescode.resCode = CordovaUtils.RESCODE_REQUIRED_PARAMETER_MISSING;
                if (key!= null && !key.equals("")) {
                    cordovarescode.resMsg = CordovaUtils.RESMSG_REQUIRED_PARAMETER_MISSING + "(error key:" +key + ")";
                }else{
                    cordovarescode.resMsg = CordovaUtils.RESMSG_REQUIRED_PARAMETER_MISSING;
                }
                return cordovarescode;
            case 2:
                cordovarescode.resCode = CordovaUtils.RESCODE_NOT_FOUND_PLUGIN_METHOD;
                if (key!= null && !key.equals("")) {
                    cordovarescode.resMsg = CordovaUtils.RESMSG_NOT_FOUND_PLUGIN_METHOD + "(error key:" + key + ")";
                }else{
                    cordovarescode.resMsg = CordovaUtils.RESMSG_NOT_FOUND_PLUGIN_METHOD;
                }
                return cordovarescode;
            default:
                break;
        }
        cordovarescode.isOK = false;
        return null;
    }

    public CordovaResCode judgeNull(String str, String value){
        if(str == null || str.equals("null") || str.equals("undefined") || str.equals("")){
            cordovarescode = setResultWithErrResultType(1, value);
            cordovarescode.isOK = false;
            return cordovarescode;
        }else{
            cordovarescode.isOK = true;
        }
        return cordovarescode;
    }


    public void setNoParameterResult(){
        cordovarescode.resCode = CordovaUtils.RESCODE_REQUIRED_PARAMETER_MISSING;
        cordovarescode.resMsg = CordovaUtils.RESMSG_REQUIRED_PARAMETER_MISSING;
        cordovarescode.isOK = false;
    }

    /**
     * Json 转成 Map<>
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> getMapForJson(String jsonStr){
        JSONObject jsonObject ;
        try {
            jsonObject = new JSONObject(jsonStr);

            Iterator<String> keyIter= jsonObject.keys();
            String key;
            Object value ;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Json 转成 List<Map<>>
     * @param jsonStr
     * @return
     */
    public static List<Map<String, Object>> getlistForJson(String jsonStr){
        List<Map<String, Object>> list = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            JSONObject jsonObj ;
            list = new ArrayList<Map<String,Object>>();
            for(int i = 0 ; i < jsonArray.length() ; i ++){
                jsonObj = (JSONObject)jsonArray.get(i);
                list.add(getMapForJson(jsonObj.toString()));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 判断是否获得api权限
     */

    public CordovaResCode getAccessAPIpermissions(boolean isAccessAPI){
        if(isAccessAPI){
            setSuccessResult();
            return cordovarescode;
        }else {
            cordovarescode.isOK = false;
            cordovarescode.resCode = CordovaUtils.RESCODE_NOT_AUTHORIZED;
            cordovarescode.resMsg = CordovaUtils.RESMSG_NOT_AUTHORIZED;
            return cordovarescode;
        }
    }

    /**
     * 验证String字符串为空
     * @param string
     * @return
     */
    public CordovaResCode judgeString(String string){
        if(string != null && !string.equals("")){
            setSuccessResult();
            return cordovarescode;
        }else {
            cordovarescode.resCode = CordovaUtils.RESCODE_REQUIRED_PARAMETER_MISSING;
            cordovarescode.resMsg = CordovaUtils.RESMSG_REQUIRED_PARAMETER_MISSING;
            cordovarescode.isOK = false;
            return cordovarescode;
        }
    }

    /**
     * 验证String字符串为空
     * @param string
     * @return
     */
    public CordovaResCode judgeObject(Object string){
        if(string != null && !string.equals("")){
            setSuccessResult();
            return cordovarescode;
        }else {
            cordovarescode.resCode = CordovaUtils.RESCODE_REQUIRED_PARAMETER_MISSING;
            cordovarescode.resMsg = CordovaUtils.RESMSG_REQUIRED_PARAMETER_MISSING;
            return cordovarescode;
        }
    }


    //Plugin发送给H5成功信息
    public void sendPluginSucces(CallbackContext callbackContext, String str, Activity activity){
        JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(str.toString());
                callbackContext.success(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    //Plugin发送给H5错误信息
    public void sendPluginError(CallbackContext callbackContext, String str, Activity activity){
        JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(str.toString());
                callbackContext.error(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }

    }

    //无效的参数格式
    public CordovaResCode invalidParameter(){
        cordovarescode.resCode = CordovaUtils.RESCODE_INVALID_PARAMETER_FORMAT;
        cordovarescode.resMsg = CordovaUtils.RESMSG_INVALID_PARAMETER_FORMAT;
        return cordovarescode;
    }

    public static Object judgeInt(Object object, String key){
        CordovaResCode cordovaResCode = new CordovaResCode();
        if(object != null && !object.equals("")){
            return object;
        }else {
            cordovaResCode.resCode = CordovaUtils.RESCODE_REQUIRED_PARAMETER_MISSING;
            if (key!= null && !key.equals("")) {
                cordovaResCode.resMsg = CordovaUtils.RESMSG_REQUIRED_PARAMETER_MISSING + "(error key:" + key + ")";
            }else{
                cordovaResCode.resMsg = CordovaUtils.RESMSG_REQUIRED_PARAMETER_MISSING;
            }
            return cordovaResCode;
        }
    }

    public static boolean conversionBoolean(String value, boolean defult){
        if(value.equals("true")){
            return true;
        }else if(value.equals("false")){
            return false;
        }
        return defult;
    }

    public static boolean judgeBoolean(Object object , boolean defult){
        if (object instanceof Integer) {
            int value = ((Integer) object).intValue();
            if(value == 1){
                return true;
            }else if(value == 0){
                return false;
            }
        } else if (object instanceof String) {
            String str = (String) object;
            if(str.equals("1")){
                return true;
            }else if(str.equals("0")){
                return false;
            }
        } else if (object instanceof Boolean) {
            boolean booleanValue = ((Boolean) object).booleanValue();
            return booleanValue;
        }
        return defult;
    }

    public static int judgeIntMax(Object object , int defult, int minValue, int maxValue) {
        if (object instanceof Integer) {
            int value = ((Integer) object).intValue();
            if(value >= minValue && value <= maxValue){
                return value;
            }
        }else if(object instanceof String){
            String str = (String) object;
            int instr = Integer.valueOf(str);
            if(instr >= minValue && instr <= maxValue){
                return instr;
            }
        }
        return defult;
    }

    public static int judgeString(Object object){
        if (object instanceof Integer) {
            int value = ((Integer) object).intValue();
            return value;
        }else if(object instanceof String){
            String str = (String) object;
            int instr = Integer.valueOf(str);
            return instr;
        }
        return 1;
    }

    public static CordovaResCode judgeDouble(Object object){
        if(object != null){
            cordovarescode.isOK = true;
            return cordovarescode;
        }else {
            cordovarescode.resCode = CordovaUtils.RESCODE_REQUIRED_PARAMETER_MISSING;
            cordovarescode.resMsg = CordovaUtils.RESMSG_REQUIRED_PARAMETER_MISSING;
            return cordovarescode;
        }
    }

    public static int defaultInt(Object object, int defaul){
        if (object instanceof Integer) {
            int value = ((Integer) object).intValue();
            return value;
        }else if(object instanceof String){
            String str = (String) object;
            int instr = Integer.valueOf(str);
            return instr;
        }
        return defaul;
    }

    public static Object defualt(Object desc, int type) {
        String str = desc.getClass().getSimpleName();
        if (str.equals("String")) {
            if (type != 0){
                desc = "";
            }
        } else if (str.equals("Integer")) {
            if (type != 1){
                desc = "";
            }
        } else if (str.equals("Boolean")) {
            if (type != 2){
                desc = "";
            }
        } else if (str.equals("JSONArray")) {
            if (type != 3){
                desc = "";
            }
        } else if (str.equals("JSONObject")) {
            if (type != 4){
                desc = "";
            }
        }else if (str.equals("Double")) {
            if (type != 5){
                desc = "";
            }
        }else if (str.equals("Float")) {
            if (type != 6){
                desc = "";
            }
        }
        return desc;
    }

    public boolean necessary(Object desc, int type){
        String str = desc.getClass().getSimpleName();
        if (str.equals("String")) {
            if (type == 0){
                return true;
            }
        } else if (str.equals("Integer")) {
            if (type == 1){
                return true;
            }
        } else if (str.equals("Boolean")) {
            if (type == 2){
                return true;
            }
        } else if (str.equals("JSONArray")) {
            if (type == 3){
                return true;
            }
        } else if (str.equals("JSONObject")) {
            if (type == 4){
                return true;
            }
        }else if (str.equals("Double")) {
            if (type == 5){
                return true;
            }
        }
        return false;
    }
    public static boolean judgeEmpty(Object str, int type){
        if(str != null && !str.equals("")){
            Object objlution = defualt(str,type);
            if(objlution != null && !objlution.equals("")){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

}



