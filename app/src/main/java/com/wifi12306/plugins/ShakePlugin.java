package com.wifi12306.plugins;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.wifi12306.bean.plugin.CordovaResCode;
import com.wifi12306.plugins.utils.CordovaContest;
import com.wifi12306.plugins.utils.CordovaUtils;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;


/**
 * 摇一摇接口 插件调用 plugin
 * <br>use javascript call like:
 * <br>cordova.exec(succ, fail, 'SXShakePlugin', 'onShake',[])
 * <br> 方法名:onShake  &nbsp;&nbsp;//开启和监听摇一摇
 * <br> 方法名:closeShake &nbsp;&nbsp;//关闭摇一摇
 * <br> 当点击客户端取消按钮时: 传递给plugin的success方法回调参数为JSONArray字符串:
 * <br>["cancel"]
 * <br> 开启和监听摇一摇 onShake 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 关闭摇一摇 closeShake 当客户端请求成功后: 传递给plugin的success方法数据为Arraylist的jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"//ID              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMSg":"OK"//ID              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 当回调客户端页面的Intent传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:intent is null"
 * <br> 当回调客户端页面的ArrayList<String> users传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:users is null"
 * <br>
 *
 * <br>Created by issuser on 16/5/16.
 */
public class ShakePlugin extends BasePlugin {
    private static final String TAG = "SXShakePlugin";
    private static CallbackContext callbackContext;
    private CordovaContest cordovaContest;
    private CordovaResCode cordovaResCode;
    private static Activity activity;
    public static SensorManager mSensorManager;
    private Sensor mSensorm;
    private static final int SENSOR_SHAKE = 10;
    private static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SENSOR_SHAKE:
//                    Toast.makeText(activity, "检测到摇晃，执行操作！", Toast.LENGTH_SHORT).show();
                    sendPluginTrigger(activity,callbackContext, CordovaUtils.RESMSG_TRIGGER_SHAKE);
                    break;

            }
            super.handleMessage(msg);
        }
    };
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        activity = this.cordova.getActivity();
        cordovaContest = new CordovaContest();
        cordovaResCode = new CordovaResCode();
        if(PluginUntil.getList().contains(action)){
            if(action.equals(PluginUntil.onShake)){
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    //TODO 执行摇一摇操作
                    mSensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
                    mSensorm = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                    if(mSensorm == null){
//                    ToastAlone.showToast(activity,"该手机暂不支持摇一摇功能!",Toast.LENGTH_SHORT);
                        cordovaResCode.resCode = CordovaUtils.RESCODE_SHAKE_DISABLED;
                        cordovaResCode.resMsg = CordovaUtils.RESMSG_LOCATION_SERVICES_DISABLED;
                        sendPluginError(activity,callbackContext,cordovaResCode.toString());
                    }else {
                        mSensorManager.registerListener(sensorEventListener, mSensorm, SensorManager.SENSOR_DELAY_NORMAL);
                    }
                    sendPluginEnable(activity,callbackContext, CordovaUtils.RESMSG_ENABLE_SHAKE);
                    return true;
                }
            }else if (action.equals(PluginUntil.closeShake)){
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    sendPluginSucces(activity,callbackContext);
                    mSensorManager.unregisterListener(sensorEventListener);
                }
            }
        }else {
            cordovaResCode = cordovaContest.setResultWithErrResultType(2,action);
            sendPluginError(activity,callbackContext,cordovaResCode.toString());
            return true;
        }
        return super.execute(action, args, callbackContext);
    }

    /**
     * 重力感应监听
     */
    public static SensorEventListener sensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // 传感器信息改变时执行该方法
            float[] values = event.values;
            float x = values[0]; // x轴方向的重力加速度，向右为正
            float y = values[1]; // y轴方向的重力加速度，向前为正
            float z = values[2]; // z轴方向的重力加速度，向上为正
            Log.i(TAG, "x轴方向的重力加速度" + x +  "；y轴方向的重力加速度" + y +  "；z轴方向的重力加速度" + z);
            // 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。
            int medumValue = 19;// 如果不敏感请自行调低该数值,低于10的话就不行了,因为z轴上的加速度本身就已经达到10了
            if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {
//				vibrator.vibrate(200);
				Message msg = new Message();
				msg.what = SENSOR_SHAKE;
				handler.sendMessage(msg);
//                ToastAlone.showToast(activity,"摇一摇成功1!!!", Toast.LENGTH_SHORT);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

}
