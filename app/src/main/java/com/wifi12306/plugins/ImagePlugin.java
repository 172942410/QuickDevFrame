

package com.wifi12306.plugins;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wifi12306.bean.plugin.CordovaResCode;
import com.wifi12306.plugins.utils.CordovaContest;
import com.wifi12306.plugins.utils.CordovaUtils;
import com.wifi12306.utils.ConsUtil;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图像接口 插件调用 plugin
 * <br>use javascript call like:
 * <br>cordova.exec(succ, fail, 'SXImagePlugin', 'chooseImage',[])
 * <br> 方法名:chooseImage  &nbsp;&nbsp;//拍照或从手机相册选择
 * <br> 方法名:chooseAvatarImage &nbsp;&nbsp;//头像上传图片选择
 * <br> 方法名:previewImage  &nbsp;&nbsp;//图片预览
 * <br> 方法名:uploadImage &nbsp;&nbsp;//上传图片
 * <br> 方法名:uploadImages  &nbsp;&nbsp;//批量上传
 * <br> 方法名:downloadImage &nbsp;&nbsp;//下载图片
 * <br> 方法名:downloadImages &nbsp;&nbsp;//批量下载图片
 * <br> 当点击客户端取消按钮时: 传递给plugin的success方法回调参数为JSONArray字符串:
 * <br>["cancel"]
 * <br> 拍照或从手机相册选择 chooseImage 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> 当sourceType为0或者2时，sizeType为0时：
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "originalLocalIds":["",""]              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "compressedLocalIds":["",""]         &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 当sourceType为0或者2时，sizeType为1或者2时：
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "originalLocalId":""            &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "compressedLocalId":""            &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 当sourceType为1时，sizeType为0时：
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "originalLocalId":""            &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "compressedLocalId":""            &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 当sourceType为1时，sizeType为1或者2时：
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "localId":""            &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 头像上传图片选择 chooseImage 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> 当sizeType为0时：
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "originalLocalId":""              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "compressedLocalId":""          &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 当sizeType为1或者2时：
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "localId":""              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 图片预览 chooseImage 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "errCode":"0"              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "errMsg":"OK"          &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 上传图片 chooseImage 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "serverId":""              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 批量上传 uploadImages 当客户端请求成功后: 传递给plugin的success方法数据为Arraylist的jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "serverIds":["",""]              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "failedLocalIds":["",""]         &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 下载图片 chooseImage 当客户端请求成功后: 传递给plugin的success方法数据为jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "localId":""              &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 批量下载图片 downloadImages 当客户端请求成功后: 传递给plugin的success方法数据为Arraylist的jsonObject字符串:
 * <br> {
 * <br>  &nbsp;&nbsp;&nbsp;  "localIds":["",""]              &nbsp;&nbsp;//不可为空
 * <br>  &nbsp;&nbsp;&nbsp;  "failedServerIds":["",""]      &nbsp;&nbsp;//不可为空
 * <br> }
 * <br> 当回调客户端页面的Intent传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:intent is null"
 * <br> 当回调客户端页面的ArrayList<String> users传递为空时,传递给plugin的error方法数据为字符串:
 * <br> "error:users is null"
 * <br>
 *
 * <br>Created by issuser on 16/5/16.
 */
public class ImagePlugin extends BasePlugin {
    private static final String TAG = "SXImagePlugin";
    private CallbackContext callbackContext;
    private Activity activity;
    private CordovaResCode cordovaResCode;
    private CordovaContest cordovaContest;
    private int sizeType;// 返回的图片质量，默认0 原图和压缩图都返回，1 原图，2 压缩图
    private int sourceType;// 图片来源，默认0 相机和相册，1 相机，2 相册
    private boolean chooseAvatarImage = false;//头像选择图片
    private boolean chooseImage = false;//相册选择图片
    private boolean uploadImage = false;//上传单张
    private boolean uploadImages = false;//上传多张
    private boolean downloadImage = false;//下载单张
    private boolean downloadImages = false;//下载多张
    private List<String> serverIds; //上传成功集合
    private List<String> failedLocalIds;//上传失败的集合
    private List<String> downlocalIds; //下载成功集合
    private List<String> downfailedLocalIds;//下载失败的集合
    private JSONArray loaclIds;//H5传过来要上传的数组
    private JSONArray downserverIds;//H5传过来要下载的数组
    private int allSize;//失败和成功总集合大小
    public static final int loadSuccessed = 10079;//下载图片成功
    private final int loadFailed = 10078;//下载图片失败
    private final int loadExists = 10077;//图片存在
    private static final int DOWNLOAD_PREPARE = 0;
    private static final int DOWNLOAD_WORK = 1;
    private static final int DOWNLOAD_OK = 2;
    private static final int DOWNLOAD_ERROR =3;
    private String name;
    private static final int UPLOAD_IMAGES =4;
    private static int filednum;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case ConsUtil.what_dismisloading:
                    break;
                case ConsUtil.what_chatPercentUpdate:
                    if(uploadImage){
//                        if(loadingDialog != null){
                            doUpdatePercent(msg);
//                        }
                    }
                    break;

                case ConsUtil.what_chatUploadImageSuccessed://单张上传
                    Bundle chatimage = msg.getData();
                    String json = chatimage.getString("json");
                    String content = null;
                    Map<Object, Object> map = new HashMap<>();
                    if(uploadImage){ //单张上传
                        if(isCancel){
                            map.put("serverId",content);
                            sendPluginSuccess(activity,callbackContext,map);
                        }
                        dismissLoadingDialog();
                    }else if(uploadImages){  // 多张上传
                        serverIds.add(content);
                        map.put("serverIds",serverIds);
                        map.put("failedLocalIds", failedLocalIds);
                        if(isCancel){
                            allSize = serverIds.size() + failedLocalIds.size();
                            if(loaclIds.length() == allSize){
                                sendPluginSuccess(activity,callbackContext,map);
                            }
                        }
                    }
                    break;
                // 更新消息发送失败状态
                case ConsUtil.what_chatUpdateSendFailed:
//                    sendPluginError(activity,callbackContext, CordovaUtils.RESCODE_UPLOAD_FILE_FAILED, CordovaUtils.RESMSG_UPLOAD_IMAGE_FAILED);
                    Bundle bundle = msg.getData();
                    String filepath = bundle.getString("filepath");
                    failedLocalIds.add(filepath);
                    allSize = serverIds.size() + failedLocalIds.size();
                    Map<Object, Object> upmap = new HashMap<>();
                    if(uploadImage){
                        dismissLoadingDialog();
                        upmap.put("serverId",filepath);
                        sendPluginError(activity,callbackContext,upmap);
                    }else if(uploadImages){
//                        if(loadingDialog != null){
                            if(serverIds.size() + failedLocalIds.size() == loaclIds.length()){
                                dismissLoadingDialog();
                            }
//                        }
                        upmap.put("failedLocalIds", failedLocalIds);
                        upmap.put("serverIds",serverIds);
                        if(loaclIds.length() == allSize){
                            sendPluginError(activity,callbackContext,upmap);
                        }
                    }
                    break;
                case DOWNLOAD_PREPARE:

                    break;
                case DOWNLOAD_WORK:
                    if(downloadImage){
//                        if(loadingDialog != null){
                            doUpdatePercent(msg);
//                        }
                    }
                    break;
                case ConsUtil.what_progress:
                    Log.e(TAG,"走没走 : " + "what_progress");
                    Bundle probundle = msg.getData();
                    Float profloat = probundle.getFloat("progress");
                    Message promsg = new Message();
                    if(downloadImage){
                        promsg.arg1 = profloat.intValue();
//                        if(loadingDialog != null){
                            doUpdatePercent(promsg);
//                        }
                    }
                    break;

                case DOWNLOAD_OK:
                    Bundle dowmload = msg.getData();
                    String url = dowmload.getString("url");

                    Map<Object, Object> downmap = new HashMap<>();
                    if(downloadImage){
                        if(isCancel){
                            downmap.put("localId",url);
                            sendPluginSuccess(activity,callbackContext,downmap);
                        }
                        dismissLoadingDialog();
                    }else if (downloadImages){
                        downlocalIds.add(url);
                        if(downlocalIds.size() > 0){
                        }
                        downmap.put("localIds",downlocalIds);
                        downmap.put("failedServerIds",downfailedLocalIds);
                        if(isCancel){
                            int down = downlocalIds.size() + downfailedLocalIds.size();
                            if(downserverIds.length() == down){
                                sendPluginSuccess(activity,callbackContext,downmap);
                            }
                        }
                    }
                    break;
                case DOWNLOAD_ERROR:
//                    sendPluginError(activity,callbackContext, CordovaUtils.RESCODE_DOWNLOAD_FILE_FAILED, CordovaUtils.RESMSG_DOWNLOAD_IMAGE_FAILED);
                    Bundle dowmloaderr = msg.getData();
                    String errurl = dowmloaderr.getString("url");
                    downfailedLocalIds.add(errurl);
                    Map<Object, Object> errmap = new HashMap<>();
                    int err = downlocalIds.size() + downfailedLocalIds.size();
                    if(downloadImage){
                        errmap.put("localId",errurl);
                        sendPluginError(activity,callbackContext,errmap);
                        dismissLoadingDialog();
                    }else if (downloadImages){
//                        if(loadingDialog != null){
                            if(downlocalIds.size() + downfailedLocalIds.size() == downserverIds.length()){
                                dismissLoadingDialog();
                            }
//                        }
                        errmap.put("failedServerIds",downfailedLocalIds);
                        errmap.put("localIds",downlocalIds);
                        if(downserverIds.length() == err){
                            sendPluginError(activity,callbackContext,errmap);
                        }
                    }
                    break;

                case UPLOAD_IMAGES:

                    for (int j = 0; j < loaclIds.length(); j++) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String uploads_url;
                    }

                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        this.activity = this.cordova.getActivity();
        isCancel = true;
        cordovaResCode = new CordovaResCode();
        cordovaContest = new CordovaContest();
        SimpleDateFormat formatter = new SimpleDateFormat(CordovaUtils.X_STRING_FILE_NAME_FORMAT);
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        CameraPhoto_Num = formatter.format(curDate);
        name = CordovaUtils.SX_PLUGIN_IMAGE_NAME_FORMAT + CameraPhoto_Num;
        serverIds = new ArrayList<>();
        failedLocalIds = new ArrayList<>();
        originalLocalIds = new ArrayList<>();
        compressedLocalIds  = new ArrayList<>();
        localIds = new ArrayList<>();
        downlocalIds = new ArrayList<>();
        downfailedLocalIds = new ArrayList<>();
        if(PluginUntil.getList().contains(action)){
            if(action.equals(PluginUntil.chooseImage)){
                chooseImage = true;
                chooseAvatarImage = false;
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    for (int i = 0; i < args.length(); i++) {
                        JSONObject jsonObject = args.getJSONObject(i);
                        Object objectcount = jsonObject.opt("count");
                        if(objectcount != null && !objectcount.equals("")){
                            Object objcount = cordovaContest.defualt(objectcount,1);
                            if(objcount != null && !objcount.equals("")){
                            }else {
                            }
                        }else {
                        }
                        Object objectsizeType = jsonObject.opt("sizeType");
                        if(objectsizeType != null && !objectsizeType.equals("")){
                            Object objsizeType = cordovaContest.defualt(objectsizeType,1);
                            if(objsizeType != null && !objsizeType.equals("")){
                                sizeType = CordovaContest.judgeIntMax(objectsizeType, 0, 0, 2);
                            }else {
                                sizeType = 0;
                            }
                        }else {
                            sizeType = 0;
                        }

                        Object objectsourceType = jsonObject.opt("sourceType");
                        if(objectsourceType != null && !objectsourceType.equals("")){
                            Object objsourceType = cordovaContest.defualt(objectsourceType,1);
                            if(objsourceType != null && !objsourceType.equals("")){
                                sourceType = CordovaContest.judgeIntMax(objectsourceType, 0, 0, 2);
                            }else {
                                sourceType = 0;
                            }
                        }else {
                            sourceType = 0;
                        }
                        originalLocalIds = new ArrayList<>();
                        compressedLocalIds = new ArrayList<>();
                        localIds = new ArrayList<>();
                        ConsUtil.creatAppDir(activity);
                        if(sourceType == 0){
                            takePic(callbackContext);
                            return true;
                        }else if(sourceType == 1){
                            boolean iscame = CordovaUtils.cameraIsCanUse();
                            if(iscame){
                                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                File f = new File(ConsUtil.SX_STRING_PLUGIN_IMG_FOLDER, name + ".jpg");
                                imageUri = Uri.fromFile(f);
                                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                cordova.startActivityForResult(this, intentCamera, ConsUtil.REQ_FROM_CAMERA);
                                return true;
                            }else {
                            }

                        }else if(sourceType == 2){
                            // 从相簿中获得照片
//                            Intent mIntent = new Intent(activity, AlbumActivity.class);
//                            mIntent.putExtra(ConsUtil.pluginKey, ConsUtil.pluginValue);
//                            cordova.startActivityForResult(this, mIntent, ConsUtil.REQ_FROM_MULTIPHOTO_SELECTED);
                            return true;
                        }
                    }
                }
            }else if (action.equals(PluginUntil.chooseAvatarImage)){
                chooseAvatarImage = true;
                chooseImage = false;
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    for (int i = 0; i < args.length(); i++) {
                        JSONObject jsonObject = args.getJSONObject(i);
                        Object objectsizeType = jsonObject.opt("sizeType");
                        if(objectsizeType != null && !objectsizeType.equals("")){
                            Object objsizeType = cordovaContest.defualt(objectsizeType,1);
                            if(objsizeType != null && !objsizeType.equals("")){
                                sizeType = CordovaContest.judgeIntMax(objectsizeType, 0, 0, 2);
                            }else {
                                sizeType = 0;
                            }
                        }else {
                            sizeType = 0;
                        }
                        Object objectsourceType = jsonObject.opt("sourceType");
                        if(objectsourceType != null && !objectsourceType.equals("")){
                            Object objsourceType = cordovaContest.defualt(objectsourceType,1);
                            if(objsourceType != null && !objsourceType.equals("")){
                                sourceType = CordovaContest.judgeIntMax(objectsourceType, 0, 0, 2);
                            }else {
                                sourceType = 0;
                            }
                        }else {
                            sourceType = 0;
                        }
                        ConsUtil.creatAppDir(activity);
                        if(sourceType == 0){
                            takePic(callbackContext);
                            return true;
                        }else if(sourceType == 1){
                            Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            File f = new File(ConsUtil.SX_STRING_PLUGIN_IMG_FOLDER, name + ".jpg");
                            imageUri = Uri.fromFile(f);
                            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                            cordova.startActivityForResult(this, intentCamera, ConsUtil.REQ_FROM_CAMERA);
                            return true;
                        }else if(sourceType == 2){
                            // 从相簿中获得照片
                            Intent mIntent = new Intent(Intent.ACTION_PICK);
                            mIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                            cordova.startActivityForResult(this,mIntent, ConsUtil.REQ_FROM_MULTIPHOTO_SELECTED);
                            return true;
                        }
                    }
                }
            }else if (action.equals(PluginUntil.previewImage)){
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    int position = 0;
                    for (int i = 0; i < args.length(); i++) {
                        JSONObject jsonObject = args.getJSONObject(i);
                        String str = null;
                        Object objstr = jsonObject.opt("current");
                        if(objstr != null && !objstr.equals("")){
                            Object objcur = cordovaContest.defualt(objstr,0);
                            if(objcur != null && !objcur.equals("")){
                                str = objcur.toString();
                            }else {
                                position = 0;
                            }
                        }else {
                            position = 0;
                        }
                        Object objArray = jsonObject.opt("urls");
                        if(objArray != null && !objArray.equals("")){
                            String appId = objArray.toString();
                            cordovaResCode = cordovaContest.judgeNull(appId,"urls");
                            if(cordovaResCode.isOK){
                                boolean objAy = cordovaContest.necessary(objArray,3);
                                if(objAy){
                                    JSONArray jsonArray = jsonObject.getJSONArray("urls");
                                    if(jsonArray.length() > 0){
                                        Object arr = jsonArray.get(0);
                                        boolean flag = false;
                                        if(str != null && !str.equals("")){
                                            flag = jsonArray.toString().replace("\\", "").contains(str.toString());
                                        }
                                        for (int j = 0; j < jsonArray.length(); j++) {
                                            String url = jsonArray.get(j).toString();
                                            if(flag){
                                                if(url.equals(str)){
                                                    position = j;
                                                }
                                            }
                                        }
                                        if(str != null && !str.equals("")){
                                            if(!flag){
                                            }
                                        }

//                                        Intent preViewIntent = new Intent(activity, PreviewBigPicActivity.class);
//                                        preViewIntent.putExtra(ConsUtil.pluginKey, ConsUtil.pluginValue);
//                                        Bundle b = new Bundle();
//                                        b.putSerializable("bigpics", picList);
//                                        b.putInt("position", position);
//                                        preViewIntent.putExtras(b);
//                                        cordova.startActivityForResult(this,preViewIntent, ConsUtil.REQ_FROM_MULTIPHOTO_SELECTED);
                                    }else {
                                        cordovaResCode = cordovaContest.setResultWithErrResultType(1,"urls");
                                        sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                        return true;
                                    }

                                }else {
                                    cordovaResCode = cordovaContest.setResultWithErrResultType(0,"urls");
                                    sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                    return true;
                                }
                            }else {
                                cordovaResCode = cordovaContest.setResultWithErrResultType(1,"urls");
                                sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                return true;
                            }
                        }else {
                            cordovaResCode = cordovaContest.setResultWithErrResultType(1,"urls");
                            sendPluginError(activity,callbackContext,cordovaResCode.toString());
                            return true;
                        }
                        return true;
                    }
                }
            }else if (action.equals(PluginUntil.uploadImage)){
                uploadImage = true;
                uploadImages = false;
                downloadImage = false;
                downloadImages = false;
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    for (int i = 0; i < args.length(); i++) {
                        JSONObject json = args.getJSONObject(i);
                        boolean isShowProgressTips;
                        Object objshow = json.opt("isShowProgressTips");
                        if(objshow != null && !objshow.equals("")){
                            Object objshowpro = cordovaContest.defualt(objshow,2);
                            if(objshowpro != null && !objshowpro.equals("")){
                                isShowProgressTips = cordovaContest.conversionBoolean(objshowpro.toString(), CordovaUtils.DEFAULT_BOOL_SHOW_PROGRESS_TIPS);
                            }else {
                                isShowProgressTips = false;
                            }
                        }else {
                            isShowProgressTips = false;
                        }
                        final String loaclId;
                        Object objloca = json.opt("localId");
                        if(objloca != null && !objloca.equals("")){
                            loaclId = objloca.toString();
                            cordovaResCode = cordovaContest.judgeNull(loaclId,"localId");
                            if(cordovaResCode.isOK){
                                boolean objlocaId = cordovaContest.necessary(objloca,0);
                                if(objlocaId){
                                    if(isShowProgressTips){
                                        createLoadingDialog("上传图片中...", true, "", handler);
                                    }
                                    new Thread() {
                                        @Override
                                        public void run() {
//                                            UpLoadFileBean upfile = new UpLoadFileBean();
//                                            upfile.filepath = loaclId;
//                                            upfile.filetype = ConsUtil.Chat_pic;
//                                            TaskManager.getInstance().addUploadThread(upfile, handler);
                                        }
                                    }.start();

                                }else {
                                    cordovaResCode = cordovaContest.setResultWithErrResultType(0,"localId");
                                    sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                    return true;
                                }
                            }else {
                                cordovaResCode = cordovaContest.setResultWithErrResultType(1,"localId");
                                sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                return true;
                            }
                        }else {
                            cordovaResCode = cordovaContest.setResultWithErrResultType(1,"localId");
                            sendPluginError(activity,callbackContext,cordovaResCode.toString());
                            return true;
                        }
                    }
                    return true;
                }
            }else if (action.equals(PluginUntil.uploadImages)){
                uploadImage = false;
                uploadImages = true;
                downloadImage = false;
                downloadImages = false;
                serverIds.clear();
                failedLocalIds.clear();
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                boolean isShowProgressTips;
                if(Api){
                    for (int i = 0; i < args.length(); i++) {
                        JSONObject json = args.getJSONObject(i);
                        Object objshow = json.opt("isShowProgressTips");
                        if(objshow != null && !objshow.equals("")){
                            Object objshowpro = cordovaContest.defualt(objshow,2);
                            if(objshowpro != null && !objshowpro.equals("")){
                                isShowProgressTips = cordovaContest.conversionBoolean(objshowpro.toString(), CordovaUtils.DEFAULT_BOOL_SHOW_PROGRESS_TIPS);
                            }else {
                                isShowProgressTips = false;
                            }
                        }else {
                            isShowProgressTips = false;
                        }
                        Object objloas = json.opt("localIds");
                        if(objloas != null && !objloas.equals("")){
                            String appId = objloas.toString();
                            cordovaResCode = cordovaContest.judgeNull(appId,"localIds");
                            if(cordovaResCode.isOK){
                                boolean objlocas = cordovaContest.necessary(objloas,3);
                                if(objlocas){
                                    loaclIds = json.getJSONArray("localIds");
                                    if(isShowProgressTips){
                                        createLoadingDialog("正在上传...", true, String.valueOf(loaclIds.length()), handler);
                                    }
                                    if(loaclIds != null && loaclIds.length() > 0){
                                        Object ob = loaclIds.get(0);
                                        if(ob.toString() != null && ob.toString().length() > 0 ){
                                            new Thread(){
                                                @Override
                                                public void run() {
                                                    handler.sendEmptyMessage(UPLOAD_IMAGES);
                                                }
                                            }.start();

                                        }else {
                                            cordovaResCode = cordovaContest.setResultWithErrResultType(1,"localIds");
                                            sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                            return true;
                                        }
                                    }else {
                                        cordovaContest.setNoParameterResult();
                                        sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                        return true;
                                    }
                                }else {
                                    cordovaResCode = cordovaContest.setResultWithErrResultType(0,"localIds");
                                    sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                    return true;
                                }
                            }else {
                                cordovaResCode = cordovaContest.setResultWithErrResultType(1,"localIds");
                                sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                return true;
                            }
                        }else {
                            cordovaResCode = cordovaContest.setResultWithErrResultType(1,"localIds");
                            sendPluginError(activity,callbackContext,cordovaResCode.toString());
                            return true;
                        }
                    }
                    return true;
                }
            }else if (action.equals(PluginUntil.downloadImage)){
                uploadImage = false;
                uploadImages = false;
                downloadImage = true;
                downloadImages = false;
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    for (int i = 0; i < args.length(); i++) {
                        JSONObject json = args.getJSONObject(i);
                        boolean isShowProgressTips;
                        Object objshow = json.opt("isShowProgressTips");
                        if(objshow != null && !objshow.equals("")){
                            Object objshowpro = cordovaContest.defualt(objshow,2);
                            if(objshowpro != null && !objshowpro.equals("")){
                                isShowProgressTips = cordovaContest.conversionBoolean(objshowpro.toString(), CordovaUtils.DEFAULT_BOOL_SHOW_PROGRESS_TIPS);
                            }else {
                                isShowProgressTips = false;
                            }
                        }else {
                            isShowProgressTips = false;
                        }
                        final String serverid;
                        Object objloca = json.opt("serverId");
                        if(objloca != null && !objloca.equals("")){
                            serverid = objloca.toString();
                            cordovaResCode = cordovaContest.judgeNull(serverid,"serverId");
                            if(cordovaResCode.isOK){
                                boolean objlocaId = cordovaContest.necessary(objloca,0);
                                if(objlocaId){
                                    if(isShowProgressTips){
                                        createLoadingDialog("下载图片中...", true, "", handler);
                                    }
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            String voicename = serverid.substring(serverid.lastIndexOf("/") + 1);
//                                            HttpDownloader downloadvoice = new HttpDownloader();
//                                            int result = downloadvoice.downpluginfile(serverid, ConsUtil.SX_STRING_PLUGIN_IMAGES_FOLDER, voicename, handler);
//                                            if (ConsUtil.what_loadsuccessful == result) {
//                                                Log.e(TAG,"成功" + result);
//                                                Message msg = new Message();
//                                                msg.what = DOWNLOAD_OK;
//                                                Bundle bundle = new Bundle();
//                                                bundle.putString("url",serverid);
//                                                msg.setData(bundle);
//                                                handler.sendMessage(msg);
//                                            } else {
//                                                Log.e(TAG,"失败" + result);
//                                                Message msg = new Message();
//                                                msg.what = DOWNLOAD_ERROR;
//                                                Bundle bundle = new Bundle();
//                                                bundle.putString("url",serverid);
//                                                msg.setData(bundle);
//                                                handler.sendMessage(msg);
//                                            }
                                        }
                                    }).start();
                                }else {
                                    cordovaResCode = cordovaContest.setResultWithErrResultType(0,"localIds");
                                    sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                    return true;
                                }
                            }else {
                                cordovaResCode = cordovaContest.setResultWithErrResultType(1,"localIds");
                                sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                return true;
                            }
                        }else {
                            cordovaResCode = cordovaContest.setResultWithErrResultType(1,"localIds");
                            sendPluginError(activity,callbackContext,cordovaResCode.toString());
                            return true;
                        }

                    }
                    return true;
                }
            }else if (action.equals(PluginUntil.downloadImages)){
                downlocalIds.clear();
                downfailedLocalIds.clear();
                uploadImage = false;
                uploadImages = false;
                downloadImage = false;
                downloadImages = true;
                boolean Api = getAccessAPIpermissions(activity,callbackContext);
                if(Api){
                    for (int i = 0; i < args.length(); i++) {
                        JSONObject json = args.getJSONObject(i);
                        boolean isShowProgressTips;
                        Object objshow = json.opt("isShowProgressTips");
                        if(objshow != null && !objshow.equals("")){
                            Object objshowpro = cordovaContest.defualt(objshow,2);
                            if(objshowpro != null && !objshowpro.equals("")){
                                isShowProgressTips = cordovaContest.conversionBoolean(objshowpro.toString(), CordovaUtils.DEFAULT_BOOL_SHOW_PROGRESS_TIPS);
                            }else {
                                isShowProgressTips = false;
                            }
                        }else {
                            isShowProgressTips = false;
                        }
                        Object objserv = json.opt("serverIds");
                        if(objserv != null && !objserv.equals("")){
                            String appId = objserv.toString();
                            cordovaResCode = cordovaContest.judgeNull(appId,"serverIds");
                            if(cordovaResCode.isOK){
                                boolean objserves = cordovaContest.necessary(objserv,3);
                                if(objserves){
                                    downserverIds = json.getJSONArray("serverIds");
                                    Object objdowm = downserverIds.get(0);
                                    if(objdowm.toString() != null && objdowm.toString().length() > 0){
                                        if(isShowProgressTips){
                                            createLoadingDialog("正在下载...", true, String.valueOf(downserverIds.length()), handler);
                                        }
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if(downserverIds != null && downserverIds.length() > 0){
                                                    for (int j = 0; j < downserverIds.length(); j++) {
                                                        String serverid = null;
                                                        try {
                                                            serverid = downserverIds.get(j).toString();
                                                            String voicename = serverid.substring(serverid.lastIndexOf("/") + 1);
//                                                            HttpDownloader downloadvoice = new HttpDownloader();
//                                                            int result = downloadvoice.downpluginfile(serverid, ConsUtil.SX_STRING_PLUGIN_IMAGES_FOLDER, voicename, handler);
//                                                            if (ConsUtil.what_loadsuccessful == result) {
//                                                                Log.e(TAG,"成功" + result);
//                                                                Message msg = new Message();
//                                                                msg.what = DOWNLOAD_OK;
//                                                                Bundle bundle = new Bundle();
//                                                                bundle.putString("url",serverid);
//                                                                msg.setData(bundle);
//                                                                handler.sendMessage(msg);
//                                                            } else {
//                                                                Log.e(TAG,"失败" + result);
//                                                                Message msg = new Message();
//                                                                msg.what = DOWNLOAD_ERROR;
//                                                                Bundle bundle = new Bundle();
//                                                                bundle.putString("url",serverid);
//                                                                msg.setData(bundle);
//                                                                handler.sendMessage(msg);
//                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }

                                                    }
                                                }else {
                                                    sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                                }
                                            }
                                        }).start();
                                    }else {
                                        cordovaResCode = cordovaContest.setResultWithErrResultType(1,"serverIds");
                                        sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                        return true;
                                    }
                                }else {
                                    cordovaResCode = cordovaContest.setResultWithErrResultType(0,"serverIds");
                                    sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                    return true;
                                }
                            }else {
                                cordovaResCode = cordovaContest.setResultWithErrResultType(1,"serverIds");
                                sendPluginError(activity,callbackContext,cordovaResCode.toString());
                                return true;
                            }
                        }else {
                            cordovaResCode = cordovaContest.setResultWithErrResultType(1,"serverIds");
                            sendPluginError(activity,callbackContext,cordovaResCode.toString());
                            return true;
                        }

                    }
                    return true;
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
     * 给hand发送消息
     * @param what
     */
    private void sendMessage(int what) {
        Message m = new Message();
        m.what = what;
        handler.sendMessage(m);
    }

    //上传进度条进度
    public void doUpdatePercent(Message msg) {
        int percent = msg.arg1;
        Log.e(TAG, "进度:" + percent);
//        if(loadingDialog != null){
//            loadingDialog.setPluginLoadingMessage(percent);
//        }

    }

    private boolean isCancel = true;
    /**
     * 创建加载框并设置超时
     */
    public void createLoadingDialog(String loadingmsg, boolean progress, String allsize, Handler handler) {
    }

    /**
     * 取消加载框
     */
    public boolean dismissLoadingDialog() {
        return false;
    }

    private RelativeLayout Relayout_poptakeorcheckpic;
    private LinearLayout lilayoutPop;
    private Dialog dialog;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void takePic(final CallbackContext callbackContext){

    }


    /**
     半透明背景出现的动画
     */
    private void propetyAnim(RelativeLayout iv) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv, "alpha", 0, 0.2f, 0.5f, 0.7f, 0.9f, 1);
        animator.setDuration(500);
        animator.setRepeatCount(0);
        animator.start();
        iv.setVisibility(View.VISIBLE);
    }

    /**
     * 半透明背景消失的动画
     * @param iv
     */
    public static void propetyAnim2(final RelativeLayout iv){
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv,"alpha",1,0.9f,0.7f,0.5f,0.2f,0);
        animator.setDuration(200);
        animator.setRepeatCount(0);
        animator.start();
    }

    public String CameraPhoto_Num;
    private Uri outUri;
    private Bitmap photo;
    //相机原图
    private String bigFilePath;
    private String failbigFilePath;
    //相机压缩图
    private String smallFilePath;
    private String failsmallFilePath;
    private Uri imageUri;
    //原图是否保存成功
    private boolean b;
    //压缩图是否保存成功
    private boolean small;
    //原图数组
    private List<String> originalLocalIds;
    //压缩图数组
    private List<String> compressedLocalIds;
    //原图或压缩图
    private List<String> localIds;
    //相册原图
    private String path_big;
    //相册压缩图
    private String path_compress;
    //裁剪之前的保存成功原图
    private String afterFilePath;
    //裁剪之前的保存失败原图
    private String afterfailFilePath;
    //裁剪之后的压缩成功图
    private String aftercompress;
    //裁剪之后的压缩失败图
    private String afterfailcompress;
    //拍照uri
    private Uri originaluri;

    private void sendPath(){
        if(chooseAvatarImage){ //头像选择
            if(sizeType == 0){  // 返回的图片质量，默认0 原图和压缩图都返回，1 原图，2 压缩图
                if(afterFilePath != null && !afterFilePath.equals("")){
                    Map<Object, Object> map = new HashMap<>();
                    map.put("originalLocalId", afterFilePath);
                    map.put("compressedLocalId", aftercompress);
                    sendPluginSuccess(activity,callbackContext,map);
                    afterFilePath = null;
                    aftercompress = null;
                }else if(afterfailFilePath != null && !afterfailFilePath.equals("")){
                    Map<Object, Object> map = new HashMap<>();
                    map.put("originalLocalId", afterfailFilePath);
                    map.put("compressedLocalId", afterfailcompress);
                    sendPluginSuccess(activity,callbackContext,map);
                    afterfailFilePath = null;
                    afterfailcompress = null;
                }
            }else if(sizeType == 1){  // 返回的图片质量，默认0 原图和压缩图都返回，1 原图，2 压缩图
                if(afterFilePath != null && !afterFilePath.equals("")){
                    Map<Object, Object> map = new HashMap<>();
                    map.put("localId", afterFilePath);
                    sendPluginSuccess(activity,callbackContext,map);
                    afterFilePath = null;
                }else if(afterfailFilePath != null && !afterfailFilePath.equals("")){
                    Map<Object, Object> map = new HashMap<>();
                    map.put("localId", afterfailFilePath);
                    sendPluginSuccess(activity,callbackContext,map);
                    afterfailFilePath = null;
                }
            }else if (sizeType ==2){  // 返回的图片质量，默认0 原图和压缩图都返回，1 原图，2 压缩图
                if(aftercompress != null && !aftercompress.equals("")){
                    Map<Object, Object> map = new HashMap<>();
                    map.put("localId", aftercompress);
                    sendPluginSuccess(activity,callbackContext,map);
                    aftercompress = null;
                }else if(afterfailcompress != null && !afterfailcompress.equals("")){
                    Map<Object, Object> map = new HashMap<>();
                    map.put("localId", afterfailcompress);
                    sendPluginSuccess(activity,callbackContext,map);
                    afterfailcompress = null;
                }
            }
        }else if(chooseImage){ //图片选择
            if(sourceType == 0 || sourceType == 2){// 当sourceType为0或者2时，sizeType为0时返回数组originalLocalIds和compressedLocalIds；sizeType为1或者2时返回数组localIds
                if(sizeType == 0){
                    Map<Object, Object> map = new HashMap<>();
                    map.put("originalLocalIds", originalLocalIds);
                    map.put("compressedLocalIds", compressedLocalIds);
                    sendPluginSuccess(activity,callbackContext,map);
                }else if(sizeType == 1 || sizeType == 2){
                    Map<Object, Object> map = new HashMap<>();
                    map.put("localIds", localIds);
                    sendPluginSuccess(activity,callbackContext,map);
                }
            }else if (sourceType == 1){// 当sourceType为1时，sizeType为0时返回originalLocalId和compressedLocalId；sizeType为1或者2时返回localId
                if(sizeType == 0){
                    Map<Object, Object> map = new HashMap<>();
                    map.put("originalLocalId", bigFilePath);
                    map.put("compressedLocalId", smallFilePath);
                    sendPluginSuccess(activity,callbackContext,map);
                    bigFilePath = null;
                    smallFilePath = null;
                }else if(sizeType == 1){
                    Map<Object, Object> map = new HashMap<>();
                    map.put("localId", bigFilePath);
                    sendPluginSuccess(activity,callbackContext,map);
                    bigFilePath = null;
                }else if (sizeType ==2){
                    Map<Object, Object> map = new HashMap<>();
                    map.put("localId", smallFilePath);
                    sendPluginSuccess(activity,callbackContext,map);
                    smallFilePath = null;
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
            if(requestCode == ConsUtil.REQ_FROM_CAMERA){
                if(resultCode == 0){
                    sendPluginCancel(activity,callbackContext, CordovaUtils.RESMSG_CANCEL_CHOOSE_IMAGE);
                    return;
                }
//                SimpleDateFormat formatter = new SimpleDateFormat(CordovaUtils.X_STRING_FILE_NAME_FORMAT);
//                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
//                String CameraPhoto_Num = formatter.format(curDate);
//                name = CordovaUtils.SX_PLUGIN_IMAGE_NAME_FORMAT + CameraPhoto_Num;
                if(chooseAvatarImage){
                    File f = new File(ConsUtil.SX_STRING_PLUGIN_IMG_FOLDER, name + ".jpg");
                    outUri = Uri.fromFile(f);
                    cropImageUri(originaluri, outUri, CordovaUtils.MAX_VALUE_COMPRESSED_AVATAR_RESOLUTION, CordovaUtils.MAX_VALUE_COMPRESSED_AVATAR_RESOLUTION, ConsUtil.REQ_FROM_PHOTOEDIT);
                }else {
                    Bundle bundle = intent.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
                    FileOutputStream b = null;
                    String filename = ConsUtil.SX_STRING_PLUGIN_IMG_FOLDER + name + ".jpg";
                }
            }else if (requestCode == ConsUtil.REQ_FROM_MULTIPHOTO_SELECTED) {//SXImagePlugin 选取相册路径传给H5
                    sendPluginCancel(activity,callbackContext, CordovaUtils.RESMSG_CANCEL_CHOOSE_IMAGE);

            }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    /**
     * 解码uri为bitmap
     *
     * @param uri
     * @return
     */
    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    /**
     * 裁剪头像大图
     *
     * @param uri
     *            文件地址
     * @param outputX
     *            输出文件的宽度
     * @param outputY
     *            输出文件的高度
     * @param requestCode
     */
    private void cropImageUri(Uri uri, Uri outUri, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setAction("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        if (null != outUri) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        cordova.startActivityForResult(this,intent, requestCode);
    }


}