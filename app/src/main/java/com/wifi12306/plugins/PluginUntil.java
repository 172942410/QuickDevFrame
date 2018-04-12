package com.wifi12306.plugins;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by issuser on 16/7/28.
 */
public class PluginUntil {

    public static String getAppVersion = "getAppVersion";
    public static String onRecord = "onRecord";
    public static String stopRecord = "stopRecord";
    public static String onPlayVoice = "onPlayVoice";
    public static String pauseVoice = "pauseVoice";
    public static String stopVoice = "stopVoice";
    public static String uploadVoice = "uploadVoice";
    public static String downloadVoice = "downloadVoice";
    public static String onDebug = "onDebug";
    public static String closeDebug = "closeDebug";
    public static String getPluginSDKVersion = "getPluginSDKVersion";
    public static String checkPluginApi = "checkPluginApi";
    public static String config = "config";
    public static String getDeviceNetworkType = "getDeviceNetworkType";
    public static String getDeviceSystemType = "getDeviceSystemType";
    public static String getDeviceSystemVersion = "getDeviceSystemVersion";
    public static String getDeviceModel = "getDeviceModel";
    public static String getDeviceUniqueID = "getDeviceUniqueID";
    public static String getDeviceInfo = "getDeviceInfo";
    public static String chooseImage = "chooseImage";
    public static String chooseAvatarImage = "chooseAvatarImage";
    public static String previewImage = "previewImage";
    public static String uploadImage = "uploadImage";
    public static String uploadImages = "uploadImages";
    public static String downloadImage = "downloadImage";
    public static String downloadImages = "downloadImages";
    public static String checkIfUserFollowInstitution = "checkIfUserFollowInstitution";
    public static String viewInstitutionInfo = "viewInstitutionInfo";
    public static String sendMsgToInstitution = "sendMsgToInstitution";
    public static String changeNavTitle = "changeNavTitle";
    public static String hideOptionMenu = "hideOptionMenu";
    public static String showOptionMenu = "showOptionMenu";
    public static String hideMenuItems = "hideMenuItems";
    public static String showMenuItems = "showMenuItems";
    public static String showAllMenuItems = "showAllMenuItems";
    public static String closeWindow = "closeWindow";
    public static String alertMessage = "alertMessage";
    public static String showMessage = "showMessage";
    public static String showLoading = "showLoading";
    public static String hideLoading = "hideLoading";
    public static String backupData = "backupData";
    public static String restoreData = "restoreData";
    public static String removeData = "removeData";
    public static String removeAllData = "removeAllData";
    public static String getLocation = "getLocation";
    public static String openLocation = "openLocation";
    public static String configRequest = "configRequest";
    public static String startRequest = "startRequest";
    public static String cancelRequest = "cancelRequest";
    public static String scanQRCode = "scanQRCode";
    public static String generateQRCode = "generateQRCode";
    public static String onShake = "onShake";
    public static String closeShake = "closeShake";
    public static String share = "share";
    public static String shareCustomContent = "shareCustomContent";
    public static String onMenuShareCustomContent = "onMenuShareCustomContent";
    public static String closeMenuShareCustomContent = "closeMenuShareCustomContent";
    public static String getUserInfo = "getUserInfo";
    public static String getUserRecentContacts = "getUserRecentContacts";
    public static String getEmployeeInfo = "getEmployeeInfo";
    public static String chooseContacts = "chooseContacts";
    public static String chooseOrganization = "chooseOrganization";
    public static String getOrganizationInfo = "getOrganizationInfo";
    public static String viewEmployeeInfo = "viewEmployeeInfo";
    public static String sendMsgToEmployee = "sendMsgToEmployee";
    public static String pay = "pay";
    public static String downLoadApp = "downLoadApp";
    public static String connectWIFI = "connectWIFI";
    public static String getPermission = "getPermission";
    public static String writeActionLog = "writeActionLog";

    public static String showLogin = "showLogin";

    public static List<String> list;

    public static void addPlugin(){
        list = new ArrayList<>();
        list.add(getAppVersion);
        list.add(onRecord);
        list.add(stopRecord);
        list.add(onPlayVoice);
        list.add(pauseVoice);
        list.add(stopVoice);
        list.add(uploadVoice);
        list.add(downloadVoice);
        list.add(onDebug);
        list.add(closeDebug);
        list.add(getPluginSDKVersion);
        list.add(checkPluginApi);
        list.add(config);
        list.add(getDeviceNetworkType);
        list.add(getDeviceSystemType);
        list.add(getDeviceSystemVersion);
        list.add(getDeviceModel);
        list.add(getDeviceUniqueID);
        list.add(getDeviceInfo);
        list.add(chooseImage);
        list.add(chooseAvatarImage);
        list.add(previewImage);
        list.add(uploadImage);
        list.add(uploadImages);
        list.add(downloadImage);
        list.add(downloadImages);
        list.add(checkIfUserFollowInstitution);
        list.add(viewInstitutionInfo);
        list.add(sendMsgToInstitution);
        list.add(changeNavTitle);
        list.add(hideOptionMenu);
        list.add(showOptionMenu);
        list.add(hideMenuItems);
        list.add(showMenuItems);
        list.add(showAllMenuItems);
        list.add(closeWindow);
        list.add(alertMessage);
        list.add(showMessage);
        list.add(showLoading);
        list.add(hideLoading);
        list.add(backupData);
        list.add(restoreData);
        list.add(removeData);
        list.add(removeAllData);
        list.add(getLocation);
        list.add(openLocation);
        list.add(configRequest);
        list.add(startRequest);
        list.add(cancelRequest);
        list.add(scanQRCode);
        list.add(generateQRCode);
        list.add(onShake);
        list.add(closeShake);
        list.add(share);
        list.add(shareCustomContent);
        list.add(onMenuShareCustomContent);
        list.add(closeMenuShareCustomContent);
        list.add(getUserInfo);
        list.add(getUserRecentContacts);
        list.add(getEmployeeInfo);
        list.add(chooseContacts);
        list.add(chooseOrganization);
        list.add(getOrganizationInfo);
        list.add(viewEmployeeInfo);
        list.add(sendMsgToEmployee);
        list.add(pay);
        list.add(downLoadApp);
        list.add(connectWIFI);
        list.add(getPermission);
        list.add(writeActionLog);
        list.add(showLogin);
    }

    public static List<String> getList(){
        return list;
    }

}
