/**
 * 司信jsixin-debug
 * Created by iPhuan on 16/5/16.
 * Build 20160722
 */

var JSDEBUGSDK_BUILD_VERSION = '1.0.0';


var PLUGIN_USER_INFO = 'SXUserInfoPlugin';

var RESCODE_SUCCESS = '0';
var RESCODE_ENABLE = '01';
var RESCODE_CANCEL = '03';
var RESCODE_NOT_AUTHORIZED = '40000';
var RESCODE_NOT_AUTHORIZED_FOR_USERINFO = '40001';
var RESCODE_NO_PARAMETER = '40100';
var RESCODE_INVALID_PARAMETER_FORMAT = '40101';
var RESCODE_REQUIRED_PARAMETER_MISSING = '40102';
var RESCODE_NO_NETWORK = '41000';
var RESCODE_REQUEST_FAILED = '41001';
var RESCODE_TOO_MANY_IMAGES = '41003';
var RESCODE_ALL_MENUID_INVALID = '41012';
var RESCODE_READ_CACHE_FAILED = '41017';


var RESMSG_SUCCESS = 'OK';
var RESMSG_ENABLE_CUSTIOM_SHARE = 'Custom share enabled';
var RESMSG_ENABLE_SHAKE = 'Shake enabled';
var RESMSG_ENABLE_RECORD = 'Recording success and listen to the recording end enabled';
var RESMSG_ENABLE_PLAY_VOICE = 'Playing success and listen to the playing end enabled';
var RESMSG_NOT_AUTHORIZED = 'No access to the JS-SDK api';
var RESMSG_NOT_AUTHORIZED_FOR_USERINFO = 'No access to the user info JS-SDK api';
var RESMSG_NO_PARAMETER = 'No parameters';
var RESMSG_INVALID_PARAMETER_FORMAT = 'Invalid parameter format';
var RESMSG_REQUIRED_PARAMETER_MISSING = 'Required parameter missing';
var RESMSG_NO_NETWORK = 'No network';
var RESMSG_REQUEST_FAILED = 'Request failed';
var ESMSG_TOO_MANY_IMAGES = 'Images number is more than the maximum limit';
var RESMSG_ALL_MENUID_INVALID = 'The ‘menuList’ has no available menuId';
var RESMSG_CANCEL_ALERT_MESSAGE = 'User has been tapped on cancel button';
var RESMSG_READ_CACHE_DATA_FAILED = 'Data cache not found';


var PrototypeString = '[object String]';
var PrototypeArray = '[object Array]';
var PrototypeObject = '[object Object]';

var SX_STRING_SEPARATED_MARK = '|';

var SX_VALUE_PLUGIN_SDK_VERSION = '1.0.0';

var DEFAULT_VALUE_TIMEOUT_INTERVAL = 20;                 //默认网络请求超时时间
var DEFAULT_VALUE_IMG_COUNT = 9;                         //默认图片选取数量
var DEFAULT_VALUE_RECORD_TIME = 60;                      //默认录音时长
var DEFAULT_VOICE_PLAY_TIME = 10;                        //默认测试播放语音时长


var DEFAULT_BOOL_DEBUG = false;                          //默认不开启调试模式
var DEFAULT_BOOL_GET_USERINFO = false;                   //默认不调用获取用户信息接口
var DEFAULT_BOOL_VALIDATES_SECURE_CERTIFICATE = true;    //默认开启验证网络请求安全证书
var DEFAULT_BOOL_VALIDATES_SIGN = false;                 //默认网络请求时不进行加密签名验证
var DEFAULT_BOOL_SHOW_LOADING = true;                    //默认开启网络请求时显示loading
var DEFAULT_BOOL_USE_STATIC_PARAMS = true;               //默认网络请求时使用静态参数
var DEFAULT_BOOL_CLOSE_SIGN_VERIFICATION = false;        //默认单个网络请求时不关闭加密签名验证
var DEFAULT_BOOL_GET_ADDRESS = false;                    //默认获取位置时不获取地址
var DEFAULT_BOOL_NEED_COMPLETION_HANDLER = false;        //默认关闭窗口后不做其他处理


var MIN_VALUE_TIMEOUT_INTERVAL = 5;                      //最小网络请求超时时间


var MAX_VALUE_TIMEOUT_INTERVAL = 120;                    //最大网络请求超时时间
var MAX_VALUE_IMG_COUNT = 9;                             //最大图片选取数量
var MAX_VALUE_RECORD_TIME = 60;                          //最大录音时长


var SX_OPTION_MENU_ITEMS_IDS = ['1', '2', '3', '4'];


//请求方法
var requestMethodType = {
    SXRequestMethodTypeGet: 0,
    SXRequestMethodTypePost: 1,
    SXRequestMethodTypeMultipartPost: 2
};

//参数错误结果类型
var errResultType = {
    SXErrResultTypeInvalidParameterFormat: 0,
    SXErrResultTypeRequiredParameterMissing: 1
};

//分享类型
var shareType = {
    SXShareTypeLink: 0,
    SXShareTypeMusic: 1,
    SXShareTypeVideo: 2
};

//选取图片质量类型
var optionsSizeType = {
    SXOptionsSizeTypeOriginalAndCompressed: 0,
    SXOptionsSizeTypeOriginal: 1,
    SXOptionsSizeTypeCompressed: 2
};

//选取图片来源类型
var optionsSourceType = {
    SXOptionsSourceTypeCameraAndAlbum: 0,
    SXOptionsSourceTypeCamera: 1,
    SXOptionsSourceTypeAlbum: 2
};

//坐标类型
var coordinateType = {
    SXCoordinateTypeWGS84: 0,
    SXCoordinateTypeGCJ02: 1
};

//联系人选择类型
var contactsSelectType = {
    SXContactsSelectTypeMultiselect: 0,
    SXContactsSelectTypeSingleSelect: 1
};

//缓存数据类型
var cacheType = {
    SXCacheTypeObject: 0,
    SXCacheTypeImage: 1,
    SXCacheTypeVoice: 2
};


var DEFAULT_TYPE_REQUEST_METHOD = requestMethodType.SXRequestMethodTypeGet;             //默认请求方法
var DEFAULT_TYPE_SHARE = shareType.SXShareTypeLink;                                     //默认分享类型
var DEFAULT_TYPE_OPTION_SIZE = optionsSizeType.SXOptionsSizeTypeOriginalAndCompressed;  //默认图片质量类型
var DEFAULT_TYPE_OPTION_SOURCE = optionsSourceType.SXOptionsSourceTypeCameraAndAlbum;   //默认图片来源类似
var DEFAULT_TYPE_COORDINATE = coordinateType.SXCoordinateTypeWGS84;                     //默认获取地理位置的坐标类型
var DEFAULT_TYPE_CONTACTS_SELECT = contactsSelectType.SXContactsSelectTypeMultiselect;  //默认联系人选择方式
var DEFAULT_TYPE_CACHE = cacheType.SXCacheTypeObject;  //默认缓存数据类型


function jsonData() {
    this.success = '{"resCode":"0","resMsg":"OK"}';
    this.enable = '{"resCode":"01","resMsg":"enable"}';
    this.trigger = '{"resCode":"02","resMsg":"trigger"}';
    this.cancel = '{"resCode":"03","resMsg":"cancel"}';
    this.refuse = '{"resCode":"04","resMsg":"refuse"}';
    this.fail = '{"resCode":"400","resMsg":"fail"}';


    this.chooseImage = '{"compressedLocalIds":["http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_0_compressed.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_1_compressed.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_2_compressed.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_3_compressed.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_4_compressed.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_5_compressed.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_6_compressed.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_7_compressed.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_8_compressed.jpg"],"originalLocalIds":["http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_0.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_1.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_2.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_3.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_4.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_5.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_6.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_7.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_8.jpg"]}';
    this.chooseAvatarImage = '{"originalLocalId":"http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114553.jpg","compressedLocalId":"http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114553_compressed.jpg"}';
    this.uploadImage = '{"serverId":"http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160603191747_0.jpg"}';
    this.uploadImages = '{"failedLocalIds":[],"serverIds":["http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_0.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_1.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_2.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_3.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_4.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_5.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_6.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_7.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_8.jpg"]}';
    this.downloadImage = '{"localId":"http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_0.jpg"}';
    this.downloadImages = '{"failedServerIds":[],"localIds":["http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_0.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_1.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_2.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_3.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_4.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_5.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_6.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_7.jpg","http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_8.jpg"]}';

    this.stopRecord = '{"localId":"/var/mobile/Containers/Data/Application/8E0CE979-E2B3-4B9F-9615-69FDCA49255F/Library/Caches/PluginFiles/Voice/SXVOICE_20160606173717.spx"}';
    this.uploadVoice = '{"serverId":"http://106.120.205.116:680/M00/02/FB/rBAICldVRQuAEm58AAAYgcyFee4262.spx"}';
    this.downloadVoice = '{"localId":"/var/mobile/Containers/Data/Application/8E0CE979-E2B3-4B9F-9615-69FDCA49255F/Library/Caches/PluginFiles/Voice/SXVOICE_e73a506ef84a91a8b30719043ca4360a.spx"}';

    this.getLocationForWGS84 = '{"address":"中国北京市海淀区西北旺东路10号院东区16号楼","speed":"-1","longitude":"116.2788420767982","latitude":"40.04304359514102","accuracy":"65","altitude":"54.73360824584961","altitudeAccuracy":"10"}';
    this.getLocationForGCJ02 = '{"address":"中国北京市海淀区西北旺东路10号院东区16号楼","speed":"-1","longitude":"116.2849747122341","latitude":"40.04434131667794","accuracy":"65","altitude":"55.26587295532227","altitudeAccuracy":"10"}';

    this.scanQRCode = '{"scanResult":"http://112.33.6.27:6060/sixin/file/apk/download_index.html"}';
    this.generateQRCode = '{"localId":"http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719143525.png"}';
    this.generateQRCodeWithoutIcon = '{"localId":"http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719143215.png"}';

    this.checkIfUserFollowInstitution = '{"isUserFollow":true}';

    this.getUserInfo = '{"employeeNum":"43205","headImgUrl":"http://106.120.205.116:680/M00/02/C2/rBAIClc9McWAaBrTAAW8trTBLr8381_80x80.jpg","post":"高级软件工程师（M）","sex":"男","company":"软通动力集团","largeHeadImgUrl":"http://106.120.205.116:680/M00/02/C2/rBAIClc9McWAaBrTAAW8trTBLr8381.jpg","userId":"43205","telphone":"","mobilePhone":"158xxxxxxxx","department":"Bank第三能力品牌业务实施一部","email":"xxxxxxx@isoftstone.com","name":"贺xx","sign":""}';
    this.getUserRecentContacts = '{"contacts":[{"headImgUrl":"http://106.120.205.116:680/M00/02/EF/rBAICldP4CKAZa3eAAKiQyhWBrY685_80x80.jpg","userId":"93607","name":"郝xx"},{"headImgUrl":"http://106.120.205.116:680/M00/02/EF/rBAICldP3oWAZ4_sAAaDODNtMGY623_80x80.jpg","userId":"43177","name":"胡xx"},{"headImgUrl":"http://106.120.205.116:680/M00/02/E6/rBAICldGrDWABzA9AAWMfXseANM449_80x80.jpg","userId":"43203","name":"傅xx"}]}';
    this.getEmployeeInfo = '{"employeeNum":"93607","headImgUrl":"http://106.120.205.116:680/M00/02/EF/rBAICldP4CKAZa3eAAKiQyhWBrY685_80x80.jpg","post":"助理测试工程师","sex":"男","company":"软通动力集团","largeHeadImgUrl":"http://106.120.205.116:680/M00/02/EF/rBAICldP4CKAZa3eAAKiQyhWBrY685.jpg","userId":"93607","telphone":"","mobilePhone":"185xxxxxxxx","department":"Bank第三能力品牌业务实施一部","email":"hlhaoa@isoftstone.com","name":"郝xx","sign":""}';
    this.chooseContacts = '{"contacts":[{"employeeNum":"","headImgUrl":"http://172.16.8.10/M00/02/7A/rBAIClcVtGaAP8LiAATjBslCrVk629_80x80.jpg","post":"测试经理（M）","sex":"","company":"","largeHeadImgUrl":"","userId":"43203","telphone":"","mobilePhone":"131xxxxxxxx","department":"Bank第三能力品牌业务实施一部","email":"","name":"傅xx","sign":""},{"employeeNum":"","headImgUrl":"","post":"高级UI工程师","sex":"","company":"","largeHeadImgUrl":"","userId":"48807","telphone":"","mobilePhone":"158xxxxxxxx","department":"Bank第三能力品牌业务实施一部","email":"","name":"平xx","sign":""}]}';
    this.chooseOrganization = '{"memberNumber":"81","parentId":"CD310","name":"Bank第三能力品牌业务实施一部","orgId":"15129"}';
    this.getOrganizationInfo = '{"memberNumber":"83","parentId":"CD300","name":"Bank第三能力品牌业务实施管理部","orgId":"CD310"}';

    this.getDeviceNetworkType = '{"networkType":"Wifi"}';
    this.getDeviceSystemType = '{"sysType":"iOS"}';
    this.getDeviceSystemVersion = '{"sysVersion":"9.3.2"}';
    this.getDeviceModel = '{"model":"iPhone8,2"}';
    this.getDeviceUniqueID = '{"uniqueId":"4B091A7D-3C43-4526-A194-C0CDC45789F2"}';
    this.getDeviceInfo = '{"sysType":"iOS","networkType":"Wifi","sysVersion":"9.3.2","model":"iPhone8,2","uniqueId":"4B091A7D-3C43-4526-A194-C0CDC45789F2"}';

    this.getAppVersion = '{"version":"2.0.1"}';
}

var jsonData = new jsonData();


var resultMsg = {
    success: JSON.parse(jsonData.success),
    enable: JSON.parse(jsonData.enable),
    trigger: JSON.parse(jsonData.trigger),
    cancel: JSON.parse(jsonData.cancel),
    refuse: JSON.parse(jsonData.refuse),
    fail: JSON.parse(jsonData.fail),
    notAuthorized: function () {
        return this.getResultWithResCodeAndResMsg(RESCODE_NOT_AUTHORIZED, RESMSG_NOT_AUTHORIZED);
    },
    notAuthorizedForUserInfo: function () {
        return this.getResultWithResCodeAndResMsg(RESCODE_NOT_AUTHORIZED_FOR_USERINFO, RESMSG_NOT_AUTHORIZED_FOR_USERINFO);
    },
    getResultWithResCodeAndResMsg: function (resCode, resMsg) {
        return {resCode: resCode, resMsg: resMsg}
    }
};


function requestConfigOptions() {
    this.staticParams = undefined;
    this.timeoutInterval = DEFAULT_VALUE_TIMEOUT_INTERVAL;
    this.isValidatesSecureCertificate = DEFAULT_BOOL_VALIDATES_SECURE_CERTIFICATE;
    this.isValidatesSign = DEFAULT_BOOL_VALIDATES_SIGN;
}

function needCancelRequest() {
    this.request = undefined;
    this.failedCallBack = undefined;
}

var dataEnvironment = {
    requestConfig: new requestConfigOptions(),
    needCancelRequests: {},
    isDebug: DEFAULT_BOOL_DEBUG,
    isGetUserInfo: true,
    isGetApiAccess: true,
    isRecordingHasStopped: false,
    isPlayingHasStopped: false
};


var debugData = {

    config: function (params) {
        if (!navigator.onLine) {
            return resultMsg.getResultWithResCodeAndResMsg(RESCODE_NO_NETWORK, RESMSG_NO_NETWORK);
        }
        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkParameterForKey('timestamp');
        result.checkStringParametersForKeys(['appId', 'nonceStr', 'singnature']);
        if (result.isOk) {
            dataEnvironment.isDebug = utils.getBoolValue(params.debug, DEFAULT_BOOL_DEBUG);
            dataEnvironment.isGetApiAccess = true;
            dataEnvironment.isGetUserInfo = utils.getBoolValue(params.isGetUserInfo, DEFAULT_BOOL_GET_USERINFO);
            return resultMsg.success;
        } else {
            dataEnvironment.isGetApiAccess = false;
            dataEnvironment.isGetUserInfo = false;
            return result.resultObject;
        }
    },


    checkPluginApi: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkStringArrayParameterForKey('apiList');
        if (result.isOk) {
            var apiList = params.apiList;
            var checkResult = {};
            for (var i in apiList) {
                var api = apiList[i];
                var fristCharacter = api.substr(0, 1);
                var lastCharacter = api.substr(api.length - 1, 1);
                if (api.indexOf(SX_STRING_SEPARATED_MARK) < 0 || fristCharacter == SX_STRING_SEPARATED_MARK || lastCharacter == SX_STRING_SEPARATED_MARK) {
                    result.setResultWithErrResultTypeAndErrKey(errResultType.SXErrResultTypeInvalidParameterFormat, api);
                    return result.resultObject();
                }

                var components = api.split(SX_STRING_SEPARATED_MARK);
                if (components.length != 2) {
                    result.setResultWithErrResultTypeAndErrKey(errResultType.SXErrResultTypeInvalidParameterFormat, api);
                    return result.resultObject();
                }

                var methodName = components[1];
                checkResult[methodName] = !!sx[methodName];
            }
            return checkResult;
        } else {
            return result.resultObject();
        }
    },

    onDebug: function (params) {
        dataEnvironment.isDebug = true;
        return resultMsg.success;
    },

    closeDebug: function (params) {
        dataEnvironment.isDebug = false;
        return resultMsg.success;

    },

    getPluginSDKVersion: function (params) {
        var versionObj = {};
        versionObj.version = SX_VALUE_PLUGIN_SDK_VERSION;
        return versionObj;
    },

    configRequest: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params);
        if (result.isOk) {
            var staticParams = params.staticParams;
            if (staticParams != undefined) {
                result.checkObjectParameterForKey('staticParams');
                if (!result.isOk) {
                    return result.resultObject();
                }
            }

            dataEnvironment.requestConfig.staticParams = staticParams;
            dataEnvironment.requestConfig.timeoutInterval = utils.getIntValue(params.timeoutInterval, DEFAULT_VALUE_TIMEOUT_INTERVAL, MIN_VALUE_TIMEOUT_INTERVAL, MAX_VALUE_TIMEOUT_INTERVAL);
            dataEnvironment.requestConfig.isValidatesSecureCertificate = utils.getBoolValue(params.isValidatesSecureCertificate, DEFAULT_BOOL_VALIDATES_SECURE_CERTIFICATE);
            dataEnvironment.requestConfig.isValidatesSign = utils.getBoolValue(params.isValidatesSign, DEFAULT_BOOL_VALIDATES_SIGN);

            return resultMsg.success;
        } else {
            return result.resultObject();
        }
    },

    startRequest: function (obj) {
        if (!navigator.onLine) {
            return resultMsg.getResultWithResCodeAndResMsg(RESCODE_NO_NETWORK, RESMSG_NO_NETWORK);
        }

        var params;
        if (obj != undefined) {
            params = obj.params;
        }

        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkStringParameterForKey('url');
        if (result.isOk) {
            var url = params.url;
            var method = utils.getTypeValue(params.method, DEFAULT_TYPE_REQUEST_METHOD, requestMethodType.SXRequestMethodTypeMultipartPost);
            var cancelTag = params.cancelTag;

            var isUseStaticParams = utils.getBoolValue(params.isUseStaticParams, DEFAULT_BOOL_USE_STATIC_PARAMS);
            var requestParams = params.requestParams;

            if (isUseStaticParams) {
                requestParams = utils.addStaticRequestParams(requestParams, dataEnvironment.requestConfig.staticParams)
            }


            var isValidatesSign = dataEnvironment.requestConfig.isValidatesSign;
            if (isValidatesSign) {
                var isCloseSignVerification = utils.getBoolValue(params.isCloseSignVerification, DEFAULT_BOOL_CLOSE_SIGN_VERIFICATION);
                isValidatesSign = !isCloseSignVerification;
            }


            if (isValidatesSign) {
                requestParams = utils.addSignToRequestParams(requestParams);
            }

            var isShowLoading = utils.getBoolValue(params.isShowLoading, DEFAULT_BOOL_SHOW_LOADING);
            if (isShowLoading) {
                utils.showLoading();
            }

            utils.ajaxRequest({
                url: url,
                method: method,
                params: requestParams,
                cancelTag: cancelTag,
                success: function (response) {
                    if (isShowLoading) {
                        utils.hideLoading();
                    }

                    handleDebugResult(obj, JSON.parse(response));
                },
                fail: function (err) {
                    if (isShowLoading) {
                        utils.hideLoading();
                    }
                    handleDebugResult(obj, err);
                }
            });
        } else {
            return result.resultObject();
        }
    },

    cancelRequest: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkStringParameterForKey('cancelTag');
        if (result.isOk) {
            var res = resultMsg.success;

            var cancelTag = params.cancelTag;
            var cancelRequest = dataEnvironment.needCancelRequests[cancelTag];
            if (cancelRequest == undefined) {
                return res;
            }

            var request = cancelRequest.request;
            if (request == undefined) {
                return res;
            }

            if (request.readyState != XMLHttpRequest.DONE && request.readyState != XMLHttpRequest.UNSENT) {

                request.abort();

                var failedCallBack = cancelRequest.failedCallBack;
                if (failedCallBack) {
                    failedCallBack(resultMsg.cancel);
                }
            }

            return res;
        }
        else {
            return result.resultObject();
        }
    },


    share: function (params) {
        return resultMsg.success;
    },

    shareCustomContent: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params, false);
        if (result.isOk) {
            params = params || {};
            var type = utils.getTypeValue(params.type, DEFAULT_TYPE_SHARE, shareType.SXShareTypeVideo);
            ;
            if (type != shareType.SXShareTypeLink) {
                result.checkStringParameterForKey('dataUrl');
            }
        }
        return result.resultObject();
    },

    onMenuShareCustomContent: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params, false);
        if (result.isOk) {
            params = params || {};
            var type = utils.getTypeValue(params.type, DEFAULT_TYPE_SHARE, shareType.SXShareTypeVideo);
            if (type != shareType.SXShareTypeLink) {
                result.checkStringParameterForKey('dataUrl');
                if (!result.isOk) {
                    return result.resultObject();
                }
            }
            return resultMsg.getResultWithResCodeAndResMsg(RESCODE_ENABLE, RESMSG_ENABLE_CUSTIOM_SHARE);
        } else {
            return result.resultObject();
        }
    },

    closeMenuShareCustomContent: function (params) {
        return resultMsg.success;
    },


    chooseImage: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params, false);
        if (result.isOk) {
            params = params || {};
            var count = utils.getIntValue(params.count, DEFAULT_VALUE_IMG_COUNT, 1, MAX_VALUE_IMG_COUNT);
            var sizeType = utils.getTypeValue(params.sizeType, DEFAULT_TYPE_OPTION_SIZE, optionsSizeType.SXOptionsSizeTypeCompressed);
            var sourceType = utils.getTypeValue(params.sourceType, DEFAULT_TYPE_OPTION_SOURCE, optionsSourceType.SXOptionsSourceTypeAlbum);
            var debugData = JSON.parse(jsonData.chooseImage);

            var resultData = {};
            if (sourceType == optionsSourceType.SXOptionsSourceTypeCamera) {
                if (sizeType == optionsSizeType.SXOptionsSizeTypeOriginalAndCompressed) {
                    resultData.originalLocalId = debugData.originalLocalIds[0];
                    resultData.compressedLocalId = debugData.compressedLocalIds[0];
                    return resultData;
                } else {
                    resultData.localId = debugData.originalLocalIds[0];
                }

            } else {
                if (count < MAX_VALUE_IMG_COUNT) {
                    var deleteCount = MAX_VALUE_IMG_COUNT - count;
                    debugData.originalLocalIds.splice(debugData.originalLocalIds.length - deleteCount, deleteCount)
                    debugData.compressedLocalIds.splice(debugData.compressedLocalIds.length - deleteCount, deleteCount)
                }
                if (sizeType == optionsSizeType.SXOptionsSizeTypeOriginalAndCompressed) {
                    resultData = debugData;
                } else {
                    resultData.localIds = debugData.originalLocalIds;
                }
            }
            return resultData;
        } else {
            return result.resultObject();
        }
    },

    chooseAvatarImage: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params, false);
        if (result.isOk) {
            params = params || {};
            var sizeType = utils.getTypeValue(params.sizeType, DEFAULT_TYPE_OPTION_SIZE, optionsSizeType.SXOptionsSizeTypeCompressed);
            var debugData = JSON.parse(jsonData.chooseAvatarImage);
            if (sizeType == optionsSizeType.SXOptionsSizeTypeOriginalAndCompressed) {
                return debugData;
            } else {
                var resultData = {};
                resultData.localId = debugData.originalLocalId;
                return resultData;
            }
        } else {
            return result.resultObject();
        }
    },

    previewImage: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkStringArrayParameterForKey('urls');
        return result.resultObject();
    },

    uploadImage: function (params) {
        if (!navigator.onLine) {
            return resultMsg.getResultWithResCodeAndResMsg(RESCODE_NO_NETWORK, RESMSG_NO_NETWORK);
        }
        return utils.getCommonDebugData(params, 'localId', 'uploadImage');
    },

    uploadImages: function (params) {
        if (!navigator.onLine) {
            return resultMsg.getResultWithResCodeAndResMsg(RESCODE_NO_NETWORK, RESMSG_NO_NETWORK);
        }
        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkStringArrayParameterForKey('localIds');
        if (result.isOk) {
            if (params.localIds.length > MAX_VALUE_IMG_COUNT) {
                return resultMsg.getResultWithResCodeAndResMsg(RESCODE_TOO_MANY_IMAGES, ESMSG_TOO_MANY_IMAGES);
            }

            var debugData = JSON.parse(jsonData.uploadImages);
            var deleteCount = debugData.serverIds.length - params.localIds.length;
            if (deleteCount > 0) {
                debugData.serverIds.splice(debugData.serverIds.length - deleteCount, deleteCount)

            } else if (deleteCount < 0) {
                for (var i = 1; i <= -deleteCount; i++) {
                    debugData.serverIds.push(debugData.serverIds[0]);
                }
            }
            return debugData;
        } else {
            return result.resultObject();
        }
    },

    downloadImage: function (params) {
        if (!navigator.onLine) {
            return resultMsg.getResultWithResCodeAndResMsg(RESCODE_NO_NETWORK, RESMSG_NO_NETWORK);
        }
        return utils.getCommonDebugData(params, 'serverId', 'downloadImage');
    },

    downloadImages: function (params) {
        if (!navigator.onLine) {
            return resultMsg.getResultWithResCodeAndResMsg(RESCODE_NO_NETWORK, RESMSG_NO_NETWORK);
        }
        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkStringArrayParameterForKey('serverIds');
        if (result.isOk) {
            if (params.localIds.length > MAX_VALUE_IMG_COUNT) {
                return resultMsg.getResultWithResCodeAndResMsg(RESCODE_TOO_MANY_IMAGES, ESMSG_TOO_MANY_IMAGES);
            }
            var debugData = JSON.parse(jsonData.downloadImages);
            var deleteCount = debugData.localIds.length - params.serverIds.length;
            if (deleteCount > 0) {
                debugData.localIds.splice(debugData.localIds.length - deleteCount, deleteCount)

            } else if (deleteCount < 0) {
                for (var i = 1; i <= -deleteCount; i++) {
                    debugData.localIds.push(debugData.localIds[0]);
                }
            }
            return debugData;
        } else {
            return result.resultObject();
        }
    },


    onRecord: function (obj) {
        var params;
        if (obj != undefined) {
            params = obj.params;
        }

        var result = new argumentsResult();
        result.initWithParams(params, false);
        if (result.isOk) {
            params = params || {};

            var maxRecordingTime = utils.getIntValue(params.maxRecordingTime, DEFAULT_VALUE_RECORD_TIME, 1, MAX_VALUE_RECORD_TIME);

            setTimeout(function () {
                if (!dataEnvironment.isRecordingHasStopped) {
                    var res = JSON.parse(jsonData.stopRecord);
                    handleDebugResult(obj, res)
                }
            }, maxRecordingTime * 1000);

            return resultMsg.getResultWithResCodeAndResMsg(RESCODE_ENABLE, RESMSG_ENABLE_RECORD);
        }
        return result.resultObject();
    },

    stopRecord: function (params) {
        dataEnvironment.isRecordingHasStopped = true;
        var debugData = JSON.parse(jsonData.stopRecord);
        return debugData;
    },

    onPlayVoice: function (obj) {
        var params;
        if (obj != undefined) {
            params = obj.params;
        }
        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkStringParameterForKey('localId');
        if (result.isOk) {
            setTimeout(function () {
                if (!dataEnvironment.isPlayingHasStopped) {
                    var res = resultMsg.success;
                    handleDebugResult(obj, res)
                }
            }, DEFAULT_VOICE_PLAY_TIME * 1000);

            return resultMsg.getResultWithResCodeAndResMsg(RESCODE_ENABLE, RESMSG_ENABLE_PLAY_VOICE);
        } else {
            return result.resultObject();
        }
    },

    pauseVoice: function (params) {
        dataEnvironment.isPlayingHasStopped = true;
        return debugData.getControlPlayDebugData(params);
    },

    stopVoice: function (params) {
        dataEnvironment.isPlayingHasStopped = true;
        return debugData.getControlPlayDebugData(params);
    },

    getControlPlayDebugData: function (params) {
        return utils.getCommonDebugData(params, 'localId');
    },

    uploadVoice: function (params) {
        if (!navigator.onLine) {
            return resultMsg.getResultWithResCodeAndResMsg(RESCODE_NO_NETWORK, RESMSG_NO_NETWORK);
        }
        return utils.getCommonDebugData(params, 'localId', 'uploadVoice');
    },

    downloadVoice: function (params) {
        if (!navigator.onLine) {
            return resultMsg.getResultWithResCodeAndResMsg(RESCODE_NO_NETWORK, RESMSG_NO_NETWORK);
        }
        return utils.getCommonDebugData(params, 'serverId', 'downloadVoice');
    },


    getLocation: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params, false);
        if (result.isOk) {
            params = params || {};
            var isGetAddress = utils.getBoolValue(params.isGetAddress, DEFAULT_BOOL_GET_ADDRESS);
            if (!navigator.onLine && isGetAddress) {
                return resultMsg.getResultWithResCodeAndResMsg(RESCODE_NO_NETWORK, RESMSG_NO_NETWORK);
            }

            var locCoordinateType = utils.getTypeValue(params.coordinateType, DEFAULT_TYPE_COORDINATE, coordinateType.SXCoordinateTypeGCJ02);
            var locjsonData = jsonData.getLocationForWGS84;
            if (locCoordinateType == coordinateType.SXCoordinateTypeGCJ02) {
                locjsonData = jsonData.getLocationForGCJ02;
            }
            var debugData = JSON.parse(locjsonData);

            if (!isGetAddress) {
                delete debugData.address;
            }
            return debugData;
        } else {
            return result.resultObject();
        }
    },

    openLocation: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkStringParameterForKey('name');
        result.checkParametersForKeys(['latitude', 'longitude']);
        var latitude = params.latitude;
        if (latitude < -90 || latitude > 90) {
            result.setResultWithErrResultTypeAndErrKey(errResultType.SXErrResultTypeInvalidParameterFormat, 'latitude');
            return result.resultObject();
        }

        var longitude = params.longitude;
        if (longitude < -180 || longitude > 180) {
            result.setResultWithErrResultTypeAndErrKey(errResultType.SXErrResultTypeInvalidParameterFormat, 'longitude');
        }
        return result.resultObject();
    },


    onShake: function (params) {
        return resultMsg.getResultWithResCodeAndResMsg(RESCODE_ENABLE, RESMSG_ENABLE_SHAKE);
    },

    closeShake: function (params) {
        return resultMsg.success;
    },


    scanQRCode: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params, false);
        if (result.isOk) {
            var debugData = JSON.parse(jsonData.scanQRCode);
            return debugData;
        } else {
            return result.resultObject();
        }
    },
    generateQRCode: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkStringParameterForKey('string');

        if (result.isOk) {
            var debugData = JSON.parse(jsonData.generateQRCodeWithoutIcon);
            if (params.iconUrl) {
                debugData = JSON.parse(jsonData.generateQRCode);
            }
            return debugData;
        } else {
            return result.resultObject();
        }
    },


    changeNavTitle: function (params) {
        return utils.getCommonDebugData(params, 'title');
    },

    hideOptionMenu: function (params) {
        return resultMsg.success;
    },

    showOptionMenu: function (params) {
        return resultMsg.success;
    },

    hideMenuItems: function (params) {
        return debugData.getHideOrShowMenuItemsDebugData(params);
    },

    showMenuItems: function (params) {
        return debugData.getHideOrShowMenuItemsDebugData(params);
    },

    getHideOrShowMenuItemsDebugData: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkStringArrayParameterForKey('menuList');
        if (result.isOk) {
            var menuIDList = SX_OPTION_MENU_ITEMS_IDS;
            var menuList = params.menuList;
            var availableMenuIdList = [];
            for (var key in menuList) {
                var menuId = menuList[key];
                if (utils.isElementInArray(menuId, menuIDList)) {
                    availableMenuIdList.push(menuId);
                }
            }
            if (availableMenuIdList.length == 0) {
                result.setFailedResultWithResCodeAndResMsg(RESCODE_ALL_MENUID_INVALID, RESMSG_ALL_MENUID_INVALID);
            }
        }
        return result.resultObject();
    },

    showAllMenuItems: function (params) {
        return resultMsg.success;
    },

    closeWindow: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params, false);
        if (result.isOk) {
            params = params || {};
            var needCompletionHandler = utils.getBoolValue(params.needCompletionHandler, DEFAULT_BOOL_NEED_COMPLETION_HANDLER);
            if (needCompletionHandler) {
                var handlerResult = params.handlerResult;
                if (handlerResult != undefined) {
                    result.checkObjectParameterForKey('handlerResult');
                    if (!result.isOk) {
                        return result.resultObject();
                    }
                }
            }
            utils.closeWindow();
        }
        return result.resultObject();
    },
    alertMessage: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params);
        if (result.isOk) {
            var title = params.title;
            var message = params.message;

            if (!confirm(title + '\n' + message)) {
                result.setFailedResultWithResCodeAndResMsg(RESCODE_CANCEL, RESMSG_CANCEL_ALERT_MESSAGE);
            }
        }
        return result.resultObject();
    },

    showMessage: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkStringParameterForKey('message');
        return result.resultObject();
    },

    showLoading: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params, false);
        return result.resultObject();
    },
    hideLoading: function (params) {
        return resultMsg.success;
    },


    checkIfUserFollowInstitution: function (params) {
        return utils.getCommonDebugData(params, 'institutionId', 'checkIfUserFollowInstitution');
    },

    viewInstitutionInfo: function (params) {
        return utils.getCommonDebugData(params, 'institutionId');
    },

    sendMsgToInstitution: function (params) {
        return utils.getCommonDebugData(params, 'institutionId');
    },


    getUserInfo: function (params) {
        return JSON.parse(jsonData.getUserInfo);
    },

    getUserRecentContacts: function (params) {
        return JSON.parse(jsonData.getUserRecentContacts);
    },

    getEmployeeInfo: function (params) {
        return utils.getCommonDebugData(params, 'userId', 'getEmployeeInfo');
    },

    chooseContacts: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params, false);
        if (result.isOk) {
            params = params || {};
            var debugData = JSON.parse(jsonData.chooseContacts);
            var selectType = utils.getTypeValue(params.selectType, DEFAULT_TYPE_CONTACTS_SELECT, contactsSelectType.SXContactsSelectTypeSingleSelect);
            if (selectType == contactsSelectType.SXContactsSelectTypeMultiselect) {
                return debugData;
            } else {
                var contacts = debugData.contacts;
                return contacts[0];
            }
        } else {
            return result.resultObject();
        }
    },

    chooseOrganization: function (params) {
        return JSON.parse(jsonData.chooseOrganization);
    },

    getOrganizationInfo: function (params) {
        return utils.getCommonDebugData(params, 'orgId', 'getOrganizationInfo');
    },
    viewEmployeeInfo: function (params) {
        return utils.getCommonDebugData(params, 'userId');
    },
    sendMsgToEmployee: function (params) {
        return utils.getCommonDebugData(params, 'userId');
    },


    getDeviceNetworkType: function (params) {
        return JSON.parse(jsonData.getDeviceNetworkType);
    },

    getDeviceSystemType: function (params) {
        return JSON.parse(jsonData.getDeviceSystemType);
    },

    getDeviceSystemVersion: function (params) {
        return JSON.parse(jsonData.getDeviceSystemVersion);
    },

    getDeviceModel: function (params) {
        return JSON.parse(jsonData.getDeviceModel);
    },

    getDeviceUniqueID: function (params) {
        return JSON.parse(jsonData.getDeviceUniqueID);
    },

    getDeviceInfo: function (params) {
        return JSON.parse(jsonData.getDeviceInfo);
    },


    backupData: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkStringParameterForKey('key');
        result.checkParameterForKey('value');
        if (result.isOk) {
            var cacheValue = params.value;
            var cacheKey = params.key;
            cacheKey = hex_md5(window.location.href) + '_' + cacheKey;
            window.localStorage[cacheKey] = JSON.stringify(cacheValue);
        }
        return result.resultObject();
    },

    restoreData: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkStringParameterForKey('key');
        if (result.isOk) {
            var dataCacheType = utils.getTypeValue(params.type, DEFAULT_TYPE_CACHE, cacheType.SXCacheTypeVoice);
            var cacheKey = params.key;
            cacheKey = hex_md5(window.location.href) + '_' + cacheKey;
            var cacheValue = window.localStorage[cacheKey];
            if (cacheValue == undefined) {
                return resultMsg.getResultWithResCodeAndResMsg(RESCODE_READ_CACHE_FAILED, RESMSG_READ_CACHE_DATA_FAILED);
            }

            var debugData = {};
            debugData.value = JSON.parse(cacheValue);

            return debugData;
        }
        return result.resultObject();
    },

    removeData: function (params) {
        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkStringParameterForKey('key');
        if (result.isOk) {
            var cacheKey = params.key;
            cacheKey = hex_md5(window.location.href) + '_' + cacheKey;
            window.localStorage.removeItem(cacheKey);
        }
        return result.resultObject();
    },

    removeAllData: function (params) {
        var appUUID = hex_md5(window.location.href);
        for (var key in window.localStorage) {
            if (key.indexOf(appUUID) > -1) {
                window.localStorage.removeItem(key);
            }
        }
        return resultMsg.success;
    },


    getAppVersion: function (params) {
        return JSON.parse(jsonData.getAppVersion);
    }
};


debugData.startRequest.needHandleCallback = true;
debugData.onRecord.needHandleCallback = true;
debugData.onPlayVoice.needHandleCallback = true;


var utils = {
    getBoolValue: function (value, defaultValue) {
        if (value == true || value == false) {
            return !!value;
        }

        return defaultValue;
    },

    getTypeValue: function (value, defaultValue, maxValue) {
        return this.getIntValue(value, defaultValue, 0, maxValue);
    },

    getIntValue: function (value, defaultValue, minValue, maxValue) {
        if (value == undefined) {
            return defaultValue;
        }

        if (this.isInteger(value) && value >= minValue && value <= maxValue) {
            return parseInt(value);
        }
        return defaultValue;
    },

    isInteger: function isInteger(value) {
        return value % 1 === 0;
    },

    ajaxRequest: function (options) {
        options = options || {};
        options.method = options.method || DEFAULT_TYPE_REQUEST_METHOD;
        // options.dataType = options.dataType || "json";
        var params = this.formatRequestParams(options.params);

        var timeout;
        //创建  - 第一步
        var request = new XMLHttpRequest();

        //接收 - 第三步
        request.onreadystatechange = function () {
            var status = request.status;

            if (request.readyState == XMLHttpRequest.DONE) {
                if (timeout) {
                    clearTimeout(timeout);
                }

                if (status >= 200 && status < 300 || status == 304) {
                    if (options.success) {
                        options.success(request.responseText);
                    }
                } else {
                    if (options.fail) {
                        var result = new argumentsResult();
                        var errMsg = RESMSG_REQUEST_FAILED + ' (XMLHttpRequest status:\'' + request.status + '\')';
                        result.setFailedResultWithResCodeAndResMsg(RESCODE_REQUEST_FAILED, errMsg)
                        options.fail(result.resultObject());
                    }
                }
            }
        };

        //连接 和 发送 - 第二步
        switch (options.method) {
            case requestMethodType.SXRequestMethodTypeGet:
                request.open("GET", options.url + "?" + params, true);
                request.send(null);
                break;
            case requestMethodType.SXRequestMethodTypePost:
                request.open("POST", options.url, true);
                //设置表单提交时的内容类型
                request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                request.send(params);
                break;
            case requestMethodType.SXRequestMethodTypeMultipartPost:
                request.open("POST", options.url, true);
                //设置表单提交时的内容类型
                request.setRequestHeader("Content-Type", "multipart/form-data");
                request.send(params);
                break;
            default:
                break;
        }

        var cancelTag = options.cancelTag;
        if (cancelTag != undefined) {
            var cancelRequest = new needCancelRequest();
            cancelRequest.request = request;
            cancelRequest.failedCallBack = options.fail;
            dataEnvironment.needCancelRequests[cancelTag] = cancelRequest;
        }

        timeout = setTimeout(function () {
            request.abort();

            if (options.fail) {
                var result = new argumentsResult();
                var errMsg = RESMSG_REQUEST_FAILED + ' (Network request has timed out)';
                result.setFailedResultWithResCodeAndResMsg(RESCODE_REQUEST_FAILED, errMsg)
                options.fail(result.resultObject());
            }
        }, dataEnvironment.requestConfig.timeoutInterval * 1000);
    },

    formatRequestParams: function (requestParams) {
        var arr = [];
        for (var name in requestParams) {
            arr.push(encodeURIComponent(name) + "=" + encodeURIComponent(requestParams[name]));
        }
        // arr.push(("v=" + Math.random()).replace(".",""));
        return arr.join("&");
    },

    addStaticRequestParams: function (currentParams, staticParams) {
        for (key in staticParams) {
            currentParams[key] = staticParams[key];
        }
        return currentParams;
    },


    addSignToRequestParams: function (params) {
        var requestParams = {
            AppKey: 'ceaa74b3-3df0-432d-8e88-fe2aab969f52',
            randomStr: this.random(),
            timeStamp: new Date().getTime(),
            uuid: 'ba786e9c-c9e6-4802-9740-d72f7121d1f0'
        };
        for (key in params) {
            requestParams[key] = params[key];
        }
        requestParams.sign = this.sign(requestParams);
        return requestParams;
    },

    random: function () {
        var num = '';
        for (var i = 0; i < 8; i++) {
            num += Math.floor(Math.random() * 10);
        }
        return num;
    },

    sign: function (params) {
        var arr = [];
        var str = "";
        for (key in params) {
            if (key != "file") {
                arr.push(key);
            }
        }
        var sortArr = arr.sort();
        for (i in sortArr) {
            var paramsKey = sortArr[i];
            str = str + sortArr[i] + "=" + params[paramsKey] + "&"
        }
        return hex_md5(str.substr(0, str.length - 1));
    },

    showLoading: function () {

    },

    hideLoading: function () {
    },

    getCommonDebugData: function (params, checkKey, apiName) {
        var result = new argumentsResult();
        result.initWithParams(params);
        result.checkStringParameterForKey(checkKey);
        if (!apiName) {
            return result.resultObject();
        }

        if (result.isOk) {
            var debugData = JSON.parse(jsonData[apiName]);
            return debugData;
        } else {
            return result.resultObject();
        }
    },
    isElementInArray: function (element, array) {
        for (var key in array) {
            if (element == array[key]) {
                return true;
            }
        }
        return false;
    },
    closeWindow: function () {
        var userAgent = navigator.userAgent;
        if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Chrome") != -1) {
            window.location.href = "about:blank";
            window.close();
        } else {
            window.opener = null;
            window.open("", "_self");
            window.close();
        }
    },
    handleResult: function () {
    }
};


var errResultType = {
    SXErrResultTypeInvalidParameterFormat: 0,
    SXErrResultTypeRequiredParameterMissing: 1
};

function argumentsResult() {
    this.isOk = true;
    this.resCode = RESCODE_SUCCESS;
    this.resMsg = RESMSG_SUCCESS;
    this.params = undefined;
    this.resultObject = function () {
        return resultMsg.getResultWithResCodeAndResMsg(this.resCode, this.resMsg);
    };
    this.initWithParams = function (params, isHasRequiredParams) {
        if (isHasRequiredParams == undefined) {
            isHasRequiredParams = true;
        }

        if (params == undefined) {
            if (isHasRequiredParams) {
                this.setNoParameterResult();
            }
            return;
        }

        this.params = params;

        if (typeof params != 'object') {
            this.setResultWithErrResultType(errResultType.SXErrResultTypeInvalidParameterFormat);
            return;
        }
        if (Object.getOwnPropertyNames(params).length == 0) {
            this.setNoParameterResult();
        }
    };


    this.setNoParameterResult = function () {
        this.setFailedResultWithResCodeAndResMsg(RESCODE_NO_PARAMETER, RESMSG_NO_PARAMETER);
    };


    this.setResultWithErrResultType = function (type) {
        this.setResultWithErrResultTypeAndErrKey(type, null)
    };

    this.setResultWithErrResultTypeAndErrKey = function (type, errKey) {
        switch (type) {
            case errResultType.SXErrResultTypeInvalidParameterFormat:
                this.setFailedResultWithResCodeAndResMsg(RESCODE_INVALID_PARAMETER_FORMAT, RESMSG_INVALID_PARAMETER_FORMAT);
                break;
            case errResultType.SXErrResultTypeRequiredParameterMissing:
                this.setFailedResultWithResCodeAndResMsg(RESCODE_REQUIRED_PARAMETER_MISSING, RESMSG_REQUIRED_PARAMETER_MISSING);
                break;
            default:
                break;
        }

        if (errKey && '' != errKey) {
            this.resMsg = this.resMsg + ' (error key:\'' + errKey + '\')';
        }

    };

    this.setSuccessResultWithResCodeAndResMsg = function (resCode, resMsg) {
        this.isOk = true;
        this.resCode = resCode;
        this.resMsg = resMsg;
    };
    this.setFailedResultWithResCodeAndResMsg = function (resCode, resMsg) {
        this.isOk = false;
        this.resCode = resCode;
        this.resMsg = resMsg;
    };


    this.checkParameterForKey = function (key) {
        if (!this.isOk) {
            return false;
        }
        var value = this.params[key];

        switch (typeof value) {
            case 'string':
                if ('' == value) {
                    this.setResultWithErrResultTypeAndErrKey(errResultType.SXErrResultTypeRequiredParameterMissing, key);
                    return false;
                }
                break;
            case 'object':
                if (Object.getOwnPropertyNames(value).length == 0 || value.length == 0) {
                    this.setResultWithErrResultTypeAndErrKey(errResultType.SXErrResultTypeRequiredParameterMissing, key);
                    return false;
                }
                break;
            case 'undefined':
                this.setResultWithErrResultTypeAndErrKey(errResultType.SXErrResultTypeRequiredParameterMissing, key);
                return false;
                break;
            default:
                break;
        }
        return true;
    };

    this.checkStringParameterForKey = function (key) {
        return this.checkParameterForKeyWithPrototype(key, PrototypeString);
    };

    this.checkArrayParameterForKey = function (key) {
        return this.checkParameterForKeyWithPrototype(key, PrototypeArray);
    };

    this.checkStringArrayParameterForKey = function (key) {
        if (!this.checkArrayParameterForKey(key)) {
            return false;
        }

        var array = this.params[key];
        for (var i in array) {
            var value = array[i];
            var type = typeof value;
            if (type != 'string' || '' == value) {
                this.setResultWithErrResultTypeAndErrKey(errResultType.SXErrResultTypeInvalidParameterFormat, key);
                return false;
            }
        }
        return true;
    };

    this.checkObjectParameterForKey = function (key) {
        return this.checkParameterForKeyWithPrototype(key, PrototypeObject);
    };

    this.checkParameterForKeyWithPrototype = function (key, prototype) {
        if (!this.checkParameterForKey(key)) {
            return false;
        }

        var value = this.params[key];
        var type = Object.prototype.toString.call(value);
        if (type != prototype) {
            this.setResultWithErrResultTypeAndErrKey(errResultType.SXErrResultTypeInvalidParameterFormat, key);
            return false;
        }
        return true;
    };

    this.checkParametersForKeys = function (keys) {
        for (var index in keys) {
            if (!this.checkParameterForKey(keys[index])) {
                return false;
            }
        }
        return true;
    };

    this.checkStringParametersForKeys = function (keys) {
        for (var index in keys) {
            if (!this.checkStringParameterForKey(keys[index])) {
                return false;
            }
        }
        return true;
    };

    this.checkArrayParametersForKeys = function (keys) {
        for (var index in keys) {
            if (!this.checkArrayParameterForKey(keys[index])) {
                return false;
            }
        }
        return true;
    };

    this.checkObjectParametersForKeys = function (keys) {
        for (var index in keys) {
            if (!this.checkObjectParameterForKey(keys[index])) {
                return false;
            }
        }
        return true;
    };
}


//******************************************************//

function sendDebugResult(pluginName, methodName, obj) {
    var res;

    if (!dataEnvironment.isGetApiAccess) {
        res = resultMsg.notAuthorized();
        handleDebugResult(obj, res);
        return;
    }

    if (pluginName == PLUGIN_USER_INFO && !dataEnvironment.isGetUserInfo) {
        res = resultMsg.notAuthorizedForUserInfo();
        handleDebugResult(obj, res);
        return;
    }


    if (obj != undefined && obj.debugCallbackType) {
        var resultMsgFunction = resultMsg[obj.debugCallbackType];
        if (resultMsgFunction) {
            res = resultMsgFunction;
            handleDebugResult(obj, res);
            return;
        }
    }

    var debugDataFunction = debugData[methodName];
    if (debugDataFunction.needHandleCallback) {
        res = debugDataFunction(obj)
    } else {
        var params;
        if (obj != undefined) {
            params = obj.params;
        }
        res = debugDataFunction(params)
    }

    if (res) {
        handleDebugResult(obj, res);
    }
}

function handleDebugResult(obj, res) {
    alertDebugResult(res);
    utils.handleResult(obj, res);
}

function alertDebugResult(res) {
    if (dataEnvironment.isDebug) {
        alert('Debug模式\n' + JSON.stringify(res));
    }
}

