/**
 * 司信JS-SDK Demo
 * Created by iPhuan on 16/5/16.
 * Build 20160802
 */


// 监听客户端即将从后台恢复接口
//sx.appWillEnterForeground(function () {
//    alert('appWillEnterForeground接口调用提示:\n' + 'App进入后台后通过appDidEnterBackground接口设置msg值为：' + msg);
//});
//
//var msg;
// 监听客户端已经进入后台接口
//sx.appDidEnterBackground(function () {
//    msg = 'OK'
//});


////only for android
//// 监听安卓用户点击返回物理键事件接口
//sx.clickOnBackButton(function () {
//     alert('用户点击了返回按钮');
//});

// 监听安卓用户点击菜单物理键事件接口
//sx.clickOnMenuButton(function () {
//     alert('用户点击了菜单按钮');
//});

//是否为本地网页开关
var isLocalWebPage = true;

// 监听客户端插件加载完毕接口
sx.deviceReady(function () {

//    document.addEventListener("backbutton", eventBackButton, false);
//    document.addEventListener("volumeupbutton", eventvolumeupButton, false);
//    document.addEventListener("volumedownbutton", eventvolumedownButton, false);
      document.addEventListener("resume", eventMenuButton, false); //菜单键
//    document.addEventListener('pause', this.onPause, false);
//    document.addEventListener('resume', this.onResume, false);

    if (isLocalWebPage) {
        apiTest();
    } else {
        //通过config接口注入权限验证配置
        sx.config({
            debug: false,  // 是否打开调试模式，默认false不打开，true开启调试模式调用所有api的返回值会在客户端alert出来
            isGetUserInfo: true,  // 是否调用接口11中获取用户信息的接口，默认false不调用
            appId: '43',  // 机构号（必传）
            timestamp: 1414587457,  // 用于生成签名的时间戳（必传）
            nonceStr: 'Wm3WZYTPz0wzccnW',  // 用于生成签名的随机字符串（必传）
            singnature: 'a16e3ac3146157156dd7746631c32377b9c9ee21'   // 签名（必传）
        });
    }
});

function eventMenuButton(){
    alert('menu');
};

// 通过error接口处理失败验证
sx.error(function (err) {
    alertResultMsg(false, '获取访问插件Api权限', err);
});

// 通过ready接口处理成功验证
sx.ready(function (res) {
    alertResultMsg(true, '获取访问插件Api权限', res);
    apiTest();
});


function apiTest() {

    //加载时改变标题
    var params = {
        title: '司信JS-SDK'  // 需要显示的导航条标题（必传）
    };
    sx.changeNavTitle({
        params: params
    });

    //加载时开启自定义分享
    // var shareParams = getShareContent();
    // sx.onMenuShareCustomContent({
    //     params: shareParams
    // });

    //加载时开启摇一摇功能
    // sx.onShake({
    //     trigger: function (res) {
    //         alertResultMsg(true, '触发摇一摇事件', res);
    //     }
    // });

    // 1 基础接口
    // 1.1 判断当前客户端是否支持指定JS接口
    document.querySelector('#checkPluginApi').onclick = (function () {
        var apiList = [
            'ShareonMenuShareCustomContent',
            'Audio|playVoice',
            'Audio|uploadVoice',
            'UserInfo|getUserInfo',
            'Device|getNetworkType' //没有该接口
        ];
        var params = {
            apiList: apiList  // 需要验证的aip数组（必传）
        };

        sx.checkPluginApi({
            params: params,
            success: function (res) {
                // 通过接口名去取
                var checkResult = res.playVoice;

                alertResultMsg(true, '检查客户端是否支持指定JS接口', res);
            },
            fail: function (err) {
                alertResultMsg(false, '检查客户端是否支持指定JS接口', err);
            }
        });
    });


    // 1.2 开启调试模式接口
    document.querySelector('#onDebug').onclick = (function () {
        sx.onDebug({
            success: function (res) {
                alertResultMsg(true, '开启调试模式', res);
            },
            fail: function (err) {
                alertResultMsg(false, '开启调试模式', err);
            }
        });
    });


    // 1.3 关闭调试模式接口
    document.querySelector('#closeDebug').onclick = (function () {
        sx.closeDebug({
            success: function (res) {
                alertResultMsg(true, '关闭调试模式', res);
            },
            fail: function (err) {
                alertResultMsg(false, '关闭调试模式', err);
            }
        });
    });


    // 1.4 获取插件版本号接口
    document.querySelector('#getPluginSDKVersion').onclick = (function () {
        sx.getPluginSDKVersion({
            success: function (res) {
                var version = res.version;  // 插件版本号

                alertResultMsg(true, '获取插件版本号', res);
            },
            fail: function (err) {
                alertResultMsg(false, '获取插件版本号', err);
            }
        });
    });


    // 2 网络请求接口
    // 2.1 配置网络请求接口
    document.querySelector('#configRequest').onclick = (function () {
        var staticParams = {
            sysType: 'iOS',
            sysVer: '9.3.1',
            devicemModel: 'iPhone8,2'
        };

        //调用该接口时必须有一个参数有值,否则调用接口失败
        var params = {
             staticParams: staticParams,  //静态请求参数，适用于网络请求接口每次需要传递固定的一些参数
            timeoutInterval: 0.1,  //超时时间，单位秒，整形值，范围5~120，默认20s
            isValidatesSecureCertificate: false,  //是否验证安全证书，默认true
            isValidatesSign: true  //是否进行签名验证，默认false，客户端网络请求时对请求参数进行加密生成签名和服务器进行验证
        };

        sx.configRequest({
            params: params,
            success: function (res) {
                alertResultMsg(true, '配置网络请求', res);
            },
            fail: function (err) {
                alertResultMsg(false, '配置网络请求', err);
            }
        });
    });

    var cancelTag = '';
    // 2.2 通过司信客户端请求网络接口
    document.querySelector('#startRequest').onclick = (function () {
        var requestParams = {
            type: 2,
            version: '1.0.0',
            device: 0
        };

        var params = {
            //'http://172.16.8.10:8081//user/updateDirectory.do'
//            url: {},  // 网络请求地址（必传）
            method: 0,  // 请求方法，0 Get，1 Post，2 MultipartPost（content type = @"multipart/form-data" 类型）默认0
            requestParams: requestParams,  // 网络请求参数
            cancelTag: 'updateDirectory',  // 用于取消网络请求的标记
            isShowLoading: true,  // 是否显示加载loading 默认true
            isUseStaticParams: false,  // 是否使用接口configRequest传递的静态参数，默认true，staticParams为全局变量，如重新设置则覆盖之前的数值，不设置值时为空
            isCloseSignVerification: false  //是否关闭签名验证，默认false，如果接口configRequest设置了开启签名验证,可通过该参数关闭当前网络请求的签名验证
        };

        cancelTag = params.cancelTag;

        sx.startRequest({
            params: params,
            success: function (res) {
                // 返回请求服务器的结果
                alertResultMsg(true, '网络请求', res);
            },
            cancel: function (err) {
                alerMsg('网络请求已取消', err);
            },
            fail: function (err) {
                alertResultMsg(false, '网络请求', err);
            }
        });
    });


    // 2.2 取消网络请求接口
    document.querySelector('#cancelRequest').onclick = (function () {
        var params = {
            cancelTag: cancelTag  // 用于取消网络请求的标记（必传）
        };

        sx.cancelRequest({
            params: params,
            success: function (res) {
                alertResultMsg(true, '取消网络请求操作', res);
            },
            fail: function (err) {
                alertResultMsg(false, '取消网络请求操作', err);
            }
        });
    });


    // 3 分享接口
    // 3.1 默认分享接口
    document.querySelector('#share').onclick = (function () {
        sx.share({
            trigger: function (res) {
                alerMsg('用户点击了分享按钮', res);
            },
            success: function (res) {
                alertResultMsg(true, '分享', res);
            },
            cancel: function (err) {
                alerMsg('用户已取消分享', err);
            },
            fail: function (err) {
                alertResultMsg(false, '分享', err);
            }
        });
    });


    function getShareContent() {
        var params = {
            title: 'iPSA',  // 分享标题（不传默认从网页获取）
            desc: 'iPSA管理系统',  // 分享描叙
            link: 'http://ipsapro.isoftstone.com/portal/',  // 分享链接，当分享的页面为本地页面时，需要传递相对路径，同时开发者还可以在url后面传参数，如/index.html?id=456&title=sixin（不传默认从网页获取）
            imgUrl: 'http://ipsapro.isoftstone.com/Portal/images/avatar/43205.jpg',  // 分享的头图链接
            type: 2,  // 分享类型，默认0 链接；1 音乐；2 视频
            dataUrl: 'http://ipsapro.isoftstone.com/portal/43205.mp4'  // 如果type是音乐或视频，则需提供数据链接（type为1或2时必传）
        };
        return params;
    }

    // 3.2 自定义内容分享接口
    document.querySelector('#shareCustomContent').onclick = (function () {
        var params = getShareContent();

        sx.shareCustomContent({
            params: params,
            // debugCallbackType:'trigger',  // Debug模式下需要指定回调的函数，参数值可以传'success','enable','trigger','cancel','refuse','fail'，该参数只在Debug模式下生效
            trigger: function (res) {
                alerMsg('用户点击了分享按钮', res);
            },
            success: function (res) {
                alertResultMsg(true, '自定义分享', res);
            },
            cancel: function (err) {
                alerMsg('用户已取消自定义分享', err);
            },
            fail: function (err) {
                alertResultMsg(false, '自定义分享', err);
            }
        });
    });

    if (isLocalWebPage) {
        // 自定义内容分享本地网页接口
        document.querySelector('#shareCustomContent-local').onclick = (function () {
            var params = {
                title: '自定义分享本地网页',  // 分享标题（不传默认从网页获取）
                desc: '通过shareCustomContent接口分享本地网页',  // 分享描叙
                link: '/html/sharePage.html?id=55&title=JS-SDK&desc=shareCustomContent',  // 分享链接，当分享的页面为本地页面时，需要传递相对路径，同时开发者还可以在url后面传参数，如/index.html?id=456&title=sixin（不传默认从网页获取）
                imgUrl: 'http://jssdk.issmobile.com/JS-SDK/test/images/icon.png'  // 分享的头图链接
            };

            sx.shareCustomContent({
                params: params,
                trigger: function (res) {
                    alerMsg('用户点击了分享按钮', res);
                },
                success: function (res) {
                    alertResultMsg(true, '自定义分享', res);
                },
                cancel: function (err) {
                    alerMsg('用户已取消自定义分享', err);
                },
                fail: function (err) {
                    alertResultMsg(false, '自定义分享', err);
                }
            });
        });
    }

    // 3.3 开启自定义内容分享功能接口
    document.querySelector('#onMenuShareCustomContent').onclick = (function () {
        var params = getShareContent();

        sx.onMenuShareCustomContent({
            params: params,
            enable: function (res) {
                alertResultMsg(true, '开启自定义分享', res);
            },
            trigger: function (res) {
                alerMsg('用户点击了分享按钮', res);
            },
            success: function (res) {
                alertResultMsg(true, '自定义分享', res);
            },
            cancel: function (err) {
                alerMsg('用户已取消自定义分享', err);
            },
            fail: function (err) {
                alertResultMsg(false, '开启自定义分享', err);
            }
        });
    });


    // 3.4 关闭自定义内容分享功能接口
    document.querySelector('#closeMenuShareCustomContent').onclick = (function () {
        sx.closeMenuShareCustomContent({
            success: function (res) {
                alertResultMsg(true, '关闭自定义分享', res);
            },
            fail: function (err) {
                alertResultMsg(false, '关闭自定义分享', err);
            }
        });
    });


    var images = {
        localId: '',
        serverId: '',
        localIds: [],
        serverIds: []
    };

    // 4 图像接口
    // 4.1 拍照或从手机相册中选图接口
    document.querySelector('#chooseImage').onclick = (function () {
        var params = {
            count: 9,  // 图片最多选择数量，默认9张，最大传值9
            sizeType: 0,  // 返回的图片质量，默认0 原图和压缩图都返回，1 原图，2 压缩图
            sourceType: 0    // 图片来源，默认0 相机和相册；1 相机；2 相册
        };

        sx.chooseImage({
            params: params,
            success: function (res) {
                var originalLocalIds = res.originalLocalIds;  // 返回选定照片的本地原图ID列表，localId可以作为img标签的src属性显示图片
                var compressedLocalIds = res.compressedLocalIds;  // 返回选定照片的本地压缩图ID列表

                images.localIds = originalLocalIds;
                images.localId = originalLocalIds[0];

                document.querySelector('.originalImage').innerHTML = '<img src=\"' + originalLocalIds[0] + '\" style=\'width: 100%\'/>';
                document.querySelector('.compressedImage').innerHTML = '<img src=\"' + compressedLocalIds[0] + '\" style=\'width: 100%\'/>';


                alertResultMsg(true, '拍照或从手机相册中选图', res);
            },
            cancel: function (err) {
                alerMsg('用户已取消拍照或从手机相册选图', err);
            },
            refuse: function (err) {
                alerMsg('用户已拒绝允许访问相机或相册', err);
            },
            fail: function (err) {
                alertResultMsg(false, '拍照或从手机相册中选图', err);
            }
        });
    });


    // 4.2 头像图片选择接口
    document.querySelector('#chooseAvatarImage').onclick = (function () {
        var params = {
            sizeType: 0,
            sourceType: 0
        };

        sx.chooseAvatarImage({
            params: params,
            success: function (res) {
                var originalLocalId = res.originalLocalId;  // 返回选定照片的本地原图ID，localId可以作为img标签的src属性显示图片
                var compressedLocalId = res.compressedLocalId;  // 返回选定照片的本地压缩图ID

                images.localId = originalLocalId;
                document.querySelector('.originalImage').innerHTML = '<img src=\"' + originalLocalId + '\" style=\'width: 100%\'/>';
                document.querySelector('.compressedImage').innerHTML = '<img src=\"' + compressedLocalId + '\" style=\'width: 100%\'/>';


                alertResultMsg(true, '头像上传图片选择', res);
            },
            cancel: function (err) {
                alerMsg('用户已取消头像上传图片选择', err);
            },
            refuse: function (err) {
                alerMsg('用户已拒绝允许访问相机或相册', err);
            },
            fail: function (err) {
                alertResultMsg(false, '头像上传图片选择', err);
            }
        });
    });


    // 4.3 图片预览接口
    document.querySelector('#previewImage').onclick = (function () {
        var urls = [];
        //'http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_0.jpg',
        //'http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_1.jpg',
        //'http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_2.jpg',
        //'http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_3.jpg',
        //'http://jssdk.issmobile.com/JS-SDK/test/PluginFiles/Images/SXIMG_20160719114315_4.jpg'
        var params = {
            current: 'http://img1.imgtn.bdimg.com/it/u=2566372841,1576710032&fm=11&gp=0.jpg',  // 当前显示图片的链接,如果不传则默认使用数组第一张图片
            urls: urls  // 需要浏览的图片url数组（必传）
        };

        sx.previewImage({
            params: params,
            success: function (res) {
                alertResultMsg(true, '图片预览', res);
            },
            fail: function (err) {
                alertResultMsg(false, '图片预览', err);
            }
        });
    });


    // 4.4 上传图片接口
    document.querySelector('#uploadImage').onclick = (function () {
        if (images.localId == '') {
            alert('请先使用 chooseImage或chooseAvatarImage 接口选择图片');
            return;
        }

        var params = {
            localId: images.localId,  // 需要上传的图片的本地ID，由chooseImage或chooseAvatarImage接口获得（必传）
            isShowProgressTips: true   // 是否显示进度提示，默认true显示，false不显示
        };

        sx.uploadImage({
            params: params,
            success: function (res) {
                var serverId = res.serverId;  // 返回图片上传至服务器端的ID


                images.serverId = serverId;

                alertResultMsg(true, '上传图片', res);
            },
            cancel: function (err) {
                //用户取消上传图片后回调该函数
                alerMsg('用户已取消图片上传操作', err);
            },
            fail: function (err) {
                alertResultMsg(false, '上传图片', err);
            }
        });
    });


    // 4.5 批量上传图片接口
    document.querySelector('#uploadImages').onclick = (function () {
        if (images.localIds.length == 0) {
            alert('请先使用 chooseImage 接口选择图片');
            return;
        }

        var params = {
            localIds: images.localIds,  // 需要上传的图片的本地ID数组，由chooseImage接口获得，最大上传数量不超过9张（必传）
            isShowProgressTips: true
        };

        sx.uploadImages({
            params: params,
            success: function (res) {
                var serverIds = res.serverIds;  // 返回图片上传至服务器端的ID列表
                var failedLocalIds = res.failedLocalIds;  // 返回上传失败的图片本地ID列表

                images.serverId = serverIds[0];
                images.serverIds = serverIds;

                if (failedLocalIds.length > 0) {
                    alerMsg('您有' + failedLocalIds.length + '张图片上传失败', res);
                } else {
                    alertResultMsg(true, '批量上传图片', res);
                }
            },
            cancel: function (err) {
                //用户取消上传图片后回调该函数
                alerMsg('用户已取消图片上传操作', err);
            },
            fail: function (err) {
                alertResultMsg(false, '批量上传图片', err);
            }
        });
    });


    // 4.6 下载图片接口
    document.querySelector('#downloadImage').onclick = (function () {
        if (images.serverId == '') {
            alert('请先使用 uploadImage或uploadImages 上传图片');
            return;
        }

        var params = {
            serverId: images.serverId,  // 需要下载的图片的服务器端ID，由uploadImage或uploadImages接口获得（必传）
            isShowProgressTips: true
        };

        sx.downloadImage({
            params: params,
            success: function (res) {
                var localId = res.localId;  // 返回图片下载后的本地ID

                alertResultMsg(true, '下载图片', res);
            },
            cancel: function (err) {
                //用户取消下载图片后回调该函数
                alerMsg('用户已取消图片下载操作', err);
            },
            fail: function (err) {
                alertResultMsg(false, '下载图片', err);
            }
        });
    });


    // 4.7 批量下载图片接口
    document.querySelector('#downloadImages').onclick = (function () {
        if (images.serverIds.length == 0) {
            alert('请先使用 uploadImages 上传图片');
            return;
        }

        var params = {
            serverIds: images.serverIds,  // 需要下载的图片的服务器端ID数组，由uploadImages接口获得，最大下载数量不超过9张（必传）
            isShowProgressTips: true
        };

        sx.downloadImages({
            params: params,
            success: function (res) {
                var localIds = res.localIds;  // 返回图片下载后的本地ID列表
                var failedServerIds = res.failedServerIds;  // 返回下载失败的图片服务器ID列表

                if (failedServerIds.length > 0) {
                    alerMsg('您有' + failedServerIds.length + '张图片下载失败', res);
                } else {
                    alertResultMsg(true, '批量下载图片', res);
                }
            },
            cancel: function (err) {
                //用户取消下载图片后回调该函数
                alerMsg('用户已取消图片下载操作', err);
            },
            fail: function (err) {
                alertResultMsg(false, '批量下载图片', err);
            }
        });
    });


    var voice = {
        localId: '',
        serverId: ''
    };

    // 5 音频接口
    // 5.1 开始录音和监听录音自动停止接口
    document.querySelector('#onRecord').onclick = (function () {
        var params = {
            maxRecordingTime: 10  // 录音最大时长，默认60s,范围1~60整数
        };

        sx.onRecord({
            params: params,
            enable: function (res) {
                //录音和监听成功后回调该函数
                alertResultMsg(true, '开始录音和监听录音自动停止', res);
            },
            success: function (res) {
                //录音自动停止后回调该函数
                var localId = res.localId;  // 返回音频存储在本地的ID

                voice.localId = localId;
                alerMsg('录音已到达最大时长', res);
            },
            refuse: function (err) {
                alerMsg('用户已拒绝允许访问麦克风', err);
            },
            fail: function (err) {
                alertResultMsg(false, '开始录音和监听录音自动停止', err);
            }
        });
    });


    // 5.2 停止录音接口
    document.querySelector('#stopRecord').onclick = (function () {
        sx.stopRecord({
            success: function (res) {
                var localId = res.localId;  // 返回音频存储在本地的ID

                voice.localId = localId;
                alertResultMsg(true, '停止录音', res);
            },
            fail: function (err) {
                alertResultMsg(false, '停止录音', err);
            }
        });
    });


    // 5.3 播放语音和监听语音自动播放完毕接口
    document.querySelector('#onPlayVoice').onclick = (function () {
        if (voice.localId == '') {
            alert('请先使用 startRecord 接口录制一段声音');
            return;
        }

        var params = {
            localId: voice.localId  // 需要播放的音频的本地ID，由stopRecord或onVoiceRecordEnd接口获得（必传）
        };

        sx.onPlayVoice({
            params: params,
            enable: function (res) {
                //播放和监听成功后回调该函数
                alertResultMsg(true, '播放语音和监听语音播放完毕', res);
            },
            success: function (res) {
                //语音自动播放完毕后回调该函数
                alerMsg('语音播放完毕', res);
            },
            fail: function (err) {
                alertResultMsg(false, '播放语音', err);
            }
        });
    });


    // 5.4 暂停播放接口
    document.querySelector('#pauseVoice').onclick = (function () {
        if (voice.localId == '') {
            alert('请先使用 startRecord 接口录制一段声音');
            return;
        }

        var params = {
            localId: voice.localId  // 需要暂停的音频的本地ID，由stopRecord或onVoiceRecordEnd接口获得（必传）
        };

        sx.pauseVoice({
            params: params,
            success: function (res) {
                alertResultMsg(true, '暂停播放', res);
            },
            fail: function (err) {
                alertResultMsg(false, '暂停播放', err);
            }
        });
    });


    // 5.5 停止播放接口
    document.querySelector('#stopVoice').onclick = (function () {
        if (voice.localId == '') {
            alert('请先使用 startRecord 接口录制一段声音');
            return;
        }

        var params = {
            localId: voice.localId  // 需要停止的音频的本地ID，由stopRecord或onVoiceRecordEnd接口获得（必传）
        };

        sx.stopVoice({
            params: params,
            success: function (res) {
                alertResultMsg(true, '停止播放', res);
            },
            fail: function (err) {
                alertResultMsg(false, '停止播放', err);
            }
        });
    });


    // 5.8 上传语音接口
    document.querySelector('#uploadVoice').onclick = (function () {
        if (voice.localId == '') {
            alert('请先使用 startRecord 接口录制一段声音');
            return;
        }

        var params = {
            localId: voice.localId,  // 需要上传的音频的本地ID，由stopRecord或onVoiceRecordEnd接口获得（必传）
            isShowProgressTips: true
        };

        sx.uploadVoice({
            params: params,
            success: function (res) {
                var serverId = res.serverId;  // 返回上传后音频的服务器端ID

                voice.serverId = serverId;
                alertResultMsg(true, '上传语音', res);
            },
            cancel: function (err) {
                //用户取消上传语音后回调该函数
                alerMsg('用户已取消语音上传操作', err);
            },
            fail: function (err) {
                alertResultMsg(false, '上传语音', err);
            }
        });
    });


    // 5.9 下载语音接口
    document.querySelector('#downloadVoice').onclick = (function () {
        if (voice.serverId == '') {
            alert('请先使用 uploadVoice 上传声音');
            return;
        }

        var params = {
            serverId: voice.serverId,  // 需要下载的音频的服务器端ID，由uploadVoice接口获得（必传）
            isShowProgressTips: true
        };

        sx.downloadVoice({
            params: params,
            success: function (res) {
                var localId = res.localId;  // 返回下载后音频的本地ID

                alertResultMsg(true, '下载语音', res);
            },
            cancel: function (err) {
                //用户取消下载语音后回调该函数
                alerMsg('用户已取消语音下载操作', err);
            },
            fail: function (err) {
                alertResultMsg(false, '下载语音', err);
            }
        });
    });


    // 6 地理位置
    // 6.1 获取地理位置接口
    document.querySelector('#getLocation').onclick = (function () {
        var params = {
            coordinateType: 1,    // 坐标类型，默认GPS坐标“wgs84”，如果想使用国测局gcj02坐标，可以传1
            isGetAddress: true   // 是否获取地址，默认false不获取
        };

        sx.getLocation({
            params: params,
            success: function (res) {
                var latitude = res.latitude;   // 纬度，浮点数，范围为90 ~ -90
                var longitude = res.longitude;   // 经度，浮点数，范围为180 ~ -180
                var altitude = res.altitude;  // 海拔，浮点数
                var speed = res.speed;  // 速度，以米/每秒计
                var altitudeAccuracy = res.altitudeAccuracy;  // 海拔精度，当为负值的时候说明altitude无效
                var accuracy = res.accuracy;  // 位置精度，正数
                var address = res.address;  // 详细地址，当isGetAddress为true时返回

                alertResultMsg(true, '获取地理位置', res);
            },
            refuse: function (err) {
                alerMsg('用户已拒绝允许访问位置', err);
            },
            fail: function (err) {
                alertResultMsg(false, '获取地理位置', err);
            }
        });
    });


    // 6.2 使用司信内置地图查看位置接口
    document.querySelector('#openLocation').onclick = (function () {
        // 坐标类型gcj02
        var params = {
            latitude: 40.044400036241754,    // 纬度，浮点数，范围为90 ~ -90（必传）
            longitude: 116.28491337681341,    // 经度，浮点数，范围为180 ~ -180（必传）
            name: '软通动力',    // 位置名 （必传）
            address: '北京市海淀区西北旺东路10号院东区16号楼',    // 地址详情说明
            scale: 0    // 地图缩放级别,整形值,范围从0~30。默认为0不缩放  当传值格式不对时使用默认值
        };


        sx.openLocation({
            params: params,
            success: function (res) {
                alertResultMsg(true, '打开地理位置', res);
            },
            fail: function (err) {
                alertResultMsg(false, '打开地理位置', err);
            }
        });
    });


    // 7 摇一摇
    // 7.1 开启和监听摇一摇事件接口
    document.querySelector('#onShake').onclick = (function () {
        sx.onShake({
            enable: function (res) {
                alertResultMsg(true, '开启和监听摇一摇事件', res);
            },
            trigger: function (res) {
                alertResultMsg(true, '触发摇一摇事件', res);
            },
            fail: function (err) {
                alertResultMsg(false, '开启和监听摇一摇事件', err);
            }
        });
    });


    // 7.2 关闭摇一摇功能接口
    document.querySelector('#closeShake').onclick = (function () {
        sx.closeShake({
            success: function (res) {
                alertResultMsg(true, '关闭摇一摇', res);
            },
            fail: function (err) {
                alertResultMsg(false, '关闭摇一摇', err);
            }
        });
    });


    // 8 二维码接口
    // 8.1 调起司信扫一扫接口
    document.querySelector('#scanQRCode').onclick = (function () {
        var params = {
            needHandleResult: true,  // 扫描结果是否需要由司信处理，默认true返回结果的同时并由司信处理，false只返回结果
            scanType: 0  // 扫码类型，可以指定扫二维码还是一维码，默认0二者都有，1 二维码，2 一维码
        };

        sx.scanQRCode({
            params: params,
            success: function (res) {
                var scanResult = res.scanResult;  // 扫描结果

                alertResultMsg(true, '扫一扫', res);
            },
            cancel: function (err) {
                alerMsg('用户已取消二维码扫描', err);
            },
            fail: function (err) {
                alertResultMsg(false, '扫一扫', err);
            }
        });
    });

    // 8.2 生成二维码接口
    document.querySelector('#generateQRCode').onclick = (function () {
        var params = {
            string: 'http://112.33.6.27:6060/sixin/file/apk/download_index.html',  // 需要生成二维码的字符串（必传）
            resolution: 828,  // 分辨率，整型值，值范围100~1280，默认值400
            iconUrl: 'http://v1.qzone.cc/avatar/201507/03/12/09/55960b1135ab7551.jpg%21200x200.jpg'  // 需要添加图标的icon链接，传值后自动在二维码上添加icon（icon客户端自动圆角）
        };

        sx.generateQRCode({
            params: params,
            success: function (res) {
                var localId = res.localId;  // 返回二维码图片存储在本地的ID，localId可以作为img标签的src属性显示图片

                document.querySelector('.qrCodeImage').innerHTML = '<img src=\"' + localId + '\" style=\'width: 100%\'/>';
                // document.querySelector('.qrCodeImage').innerHTML = '<img src=\"data:image/png;base64,' + localId + '\" style=\'width: 100%\'/>';

                alertResultMsg(true, '生成二维码', res);
            },
            fail: function (err) {
                alertResultMsg(false, '生成二维码', err);
            }
        });
    });


    // 9 界面操作
    // 9.1 改变导航条标题接口
    document.querySelector('#changeNavTitle').onclick = (function () {
        var params = {
            title: 'iPSA'  // 需要显示的导航条标题（必传）
        };

        sx.changeNavTitle({
            params: params,
            success: function (res) {
                alertResultMsg(true, '改变导航条标题', res);
            },
            fail: function (err) {
                alertResultMsg(false, '改变导航条标题', err);
            }
        });
    });


    // 9.2 隐藏右上角菜单按钮接口
    document.querySelector('#hideOptionMenu').onclick = (function () {
        sx.hideOptionMenu({
            success: function (res) {
                alertResultMsg(true, '隐藏右上角菜单按钮', res);
            },
            fail: function (err) {
                alertResultMsg(false, '隐藏右上角菜单按钮', err);
            }
        });
    });


    // 9.3 显示右上角菜单按钮接口
    document.querySelector('#showOptionMenu').onclick = (function () {
        sx.showOptionMenu({
            success: function (res) {
                alertResultMsg(true, '显示右上角菜单按钮', res);
            },
            fail: function (err) {
                alertResultMsg(false, '显示右上角菜单按钮', err);
            }
        });
    });


    // 9.4 批量隐藏功能按钮接口
    document.querySelector('#hideMenuItems').onclick = (function () {
        var menuList = ['2', '3'];
        var params = {
            menuList: menuList  // 需要隐藏的菜单ID 详细菜单列表见附录（必传）
        };

        sx.hideMenuItems({
            params: params,
            success: function (res) {
                alertResultMsg(true, '批量隐藏功能按钮', res);
            },
            fail: function (err) {
                alertResultMsg(false, '批量隐藏功能按钮', err);
            }
        });
    });


    // 9.5 批量显示功能按钮接口
    document.querySelector('#showMenuItems').onclick = (function () {
        var menuList = ['2', '3'];
        var params = {
            menuList: menuList  // 需要显示的菜单ID 详细菜单列表见附录（必传）
        };

        sx.showMenuItems({
            params: params,
            success: function (res) {
                alertResultMsg(true, '批量显示功能按钮', res);
            },
            fail: function (err) {
                alertResultMsg(false, '批量显示功能按钮', err);
            }
        });
    });


    // 9.6 显示所有功能按钮接口
    document.querySelector('#showAllMenuItems').onclick = (function () {
        sx.showAllMenuItems({
            success: function (res) {
                alertResultMsg(true, '显示所有功能按钮', res);
            },
            fail: function (err) {
                alertResultMsg(false, '显示所有功能按钮', err);
            }
        });
    });


    // 9.7 关闭当前网页窗口接口
    document.querySelector('#closeWindow').onclick = (function () {
        var handlerResult = {
            voteTitle: '',
            voteType: 1,
            voteStatus: '已结束'
        };

        // 开发者在关闭页面的同时可以给客户端传递参数，适用于内部开发者使用
        var params = {
            needCompletionHandler: true,  //窗口关闭后需要执行其他事情，默认false
            handlerResult: handlerResult  //窗口关闭后执行其他事情传给客户端的结果数据，该参数必须为对象
        };

        sx.closeWindow({
            params: params,
            success: function (res) {
                alertResultMsg(true, '关闭当前网页窗口', res);
            },
            fail: function (err) {
                alertResultMsg(false, '关闭当前网页窗口', err);
            }
        });
    });

    // 9.8 通过司信客户端alert信息接口
    document.querySelector('#alertMessage').onclick = (function () {
        var params = {
            title: '关闭网页提示',  //alert标题
            message: '是否关闭当前网页预览?',  //alert详细提示信息
            confirmTitle: '关闭',  //确认按钮标题
            cancelTitle: '取消'  //取消按钮标题（confirmTitle和cancelTitle两都不传默认显示“确认”和“取消”，二传一只显示一个按钮）
        };

        sx.alertMessage({
            params: params,
            success: function (res) {
                //点击确认按钮后回调该函数
                sx.closeWindow();
            },
            cancel: function (err) {
                //点击取消按钮后回调该函数
                alerMsg('用户点击alert取消按钮', err);
            },
            fail: function (err) {
                alertResultMsg(false, 'alert信息', err);
            }
        });
    });

    // 9.9 通过司信客户端显示提示信息接口
    document.querySelector('#showMessage').onclick = (function () {
        var params = {
            message: '司信JS SDK',  //提示信息（必传）
            type: 0  //提示框显示类型，默认0显示带有警示感叹号图标的提示框，1显示带有√ 图标的提示框
        };

        sx.showMessage({
            params: params,
            success: function (res) {
                if (sx.isDebug) {
                    alertResultMsg(true, '提示信息', res);
                }
            },
            fail: function (err) {
                alertResultMsg(false, '提示信息', err);
            }
        });
    });

    // 9.10 通过司信客户端显示loading接口
    document.querySelector('#showLoading').onclick = (function () {
        var params = {
            message: '努力加载中...'  //loading提示信息（不传默认显示“加载中...”）
        };

        sx.showLoading({
            params: params,
            success: function (res) {
                if (sx.isDebug) {
                    alertResultMsg(true, '显示loading', res);
                }
            },
            cancel: function (err) {
                //用户关闭loading提示时回调该函数
                alerMsg('用户已取消当前loading显示', err);
            },
            fail: function (err) {
                alertResultMsg(false, '显示loading', err);
            }
        });

        setTimeout(function () {
            sx.hideLoading();
        }, 3000);
    });

    // 9.11 通过司信客户端隐藏loading接口
    document.querySelector('#hideLoading').onclick = (function () {
        sx.hideLoading({
            success: function (res) {
                alertResultMsg(true, '隐藏loading', res);
            },
            fail: function (err) {
                alertResultMsg(false, '隐藏loading', err);
            }
        });
    });


    // 10 进入机构号
    // 10.1 判断用户是否关注该机构号接口
    document.querySelector('#checkIfUserFollowInstitution').onclick = (function () {
        var params = {
            institutionId: '1000033'  // 机构号ID（必传）
        };

        sx.checkIfUserFollowInstitution({
            params: params,
            success: function (res) {
                var isUserFollow = res.isUserFollow;  // 检查结果true或者false

                alertResultMsg(true, '判断用户是否关注该机构号', res);
            },
            fail: function (err) {
                alertResultMsg(false, '判断用户是否关注该机构号', err);
            }
        });
    });


    // 10.2 查看机构号接口
    document.querySelector('#viewInstitutionInfo').onclick = (function () {
        var params = {
            institutionId: '1000033'  // 机构号ID（必传）
        };

        sx.viewInstitutionInfo({
            params: params,
            success: function (res) {
                alertResultMsg(true, '查看机构号', res);
            },
            fail: function (err) {
                alertResultMsg(false, '查看机构号', err);
            }
        });
    });


    // 10.3 给机构号发信息接口
    document.querySelector('#sendMsgToInstitution').onclick = (function () {
        var params = {
            institutionId: '1000033'  // 机构号ID（必传）
        };

        sx.sendMsgToInstitution({
            params: params,
            success: function (res) {
                alertResultMsg(true, '给机构号发送信息操作', res);
            },
            fail: function (err) {
                alertResultMsg(false, '给机构号发送信息操作', err);
            }
        });
    });


    var userInfo = {
        userId: '',
        orgId: ''
    }

    // 11 获取用户及公司信息
    // 11.1 获取司信当前用户接口
    document.querySelector('#getUserInfo').onclick = (function () {
        var params = {
            isGetDetailInfo: true  // 是否获取详细用户信息，默认false只获取基本信息
        };
        sx.getUserInfo({
            params: params,
            success: function (res) {
                // res为User对象，isGetDetailInfo为false时只返回基本信息userId，headImgUrl，name字段;为true时返回详细用户信息
                var userId = res.userId;  // 用户ID
                var headImgUrl = res.headImgUrl;  // 用户头像小图地址
                var name = res.name;  // 姓名

                // isGetDetailInfo为true时才返回以下字段
                var largeHeadImgUrl = res.largeHeadImgUrl;  // 用户头像大图地址
                var sex = res.sex;  // 性别
                var mobilePhone = res.mobilePhone;  // 手机号码
                var telphone = res.telphone;  // 座机号码
                var email = res.email;  // 电子邮件
                var sign = res.sign;  // 个性签名
                var employeeNum = res.employeeNum;  // 员工号
                var company = res.company;  // 员工所在公司名
                var department = res.department;  // 员工所在部门
                var post = res.post;  // 员工职务

                alertResultMsg(true, '获取用户信息', res);
            },
            fail: function (err) {
                alertResultMsg(false, '获取用户信息', err);
            }
        });
    });


    // 11.2 获取司信当前用户常用联系人接口
    document.querySelector('#getUserRecentContacts').onclick = (function () {
        sx.getUserRecentContacts({
            success: function (res) {
                var contacts = res.contacts;  // 最近联系人列表,无最近联系人时返回空数组

                if (contacts.length > 0) {
                    var contact = contacts[0];
                    // contact为最近联系人对象
                    var userId = contact.userId;  // 用户ID
                    var headImgUrl = contact.headImgUrl;  // 用户头像地址
                    var name = contact.name;  // 姓名

                    userInfo.userId = userId;
                }

                alertResultMsg(true, '获取用户最近联系人', res);
            },
            fail: function (err) {
                alertResultMsg(false, '获取用户最近联系人', err);
            }
        });
    });


    // 11.3 获取员工信息接口
    document.querySelector('#getEmployeeInfo').onclick = (function () {
        if (userInfo.userId == '') {
            alert('请先使用 getUserRecentContacts 获取需要查询的联系人userId');
            return;
        }

        var params = {
            userId: userInfo.userId  // 员工用户ID，由接口getUserRecentContacts获取（必传）
        };

        sx.getEmployeeInfo({
            params: params,
            success: function (res) {
                // res为User对象
                var userId = res.userId;  // 用户ID
                var headImgUrl = res.headImgUrl;  // 用户头像小图地址
                var largeHeadImgUrl = res.largeHeadImgUrl;  // 用户头像大图地址
                var name = res.name;  // 姓名
                var sex = res.sex;  // 性别
                var mobilePhone = res.mobilePhone;  // 手机号码
                var telphone = res.telphone;  // 座机号码
                var email = res.email;  // 电子邮件
                var sign = res.sign;  // 个性签名
                var employeeNum = res.employeeNum;  // 员工号
                var company = res.company;  // 员工所在公司名
                var department = res.department;  // 员工所在部门
                var post = res.post;  // 员工职务

                alertResultMsg(true, '获取员工信息', res);
            },
            fail: function (err) {
                alertResultMsg(false, '获取员工信息', err);
            }
        });
    });


    // 11.4 获取司信通讯录接口
    document.querySelector('#chooseContacts').onclick = (function () {
        var params = {
            selectType: 0  // 联系人支持多选和单选，默认0多选，1单选
        };

        sx.chooseContacts({
            params: params,
            success: function (res) {
                // 当selectType为0时返回联系人列表，每个元素是一个userInfo对象，同接口getUserInfo返回的User对象一致，当selectType为1时返回一个User对象
                var contacts = res.contacts;

                alertResultMsg(true, '选择联系人', res);
            },
            cancel: function (err) {
                alerMsg('用户已取消选择联系人', err)
            },
            fail: function (err) {
                alertResultMsg(false, '选择联系人', err);
            }
        });
    });


    // 11.5 选取公司组织接口
    document.querySelector('#chooseOrganization').onclick = (function () {
        sx.chooseOrganization({
            success: function (res) {
                // res为Organization对象
                var orgId = res.orgId;  // 组织ID
                var name = res.name;  // 组织名称
                var memberNumber = res.memberNumber;  // 组织下所属人数
                var parentId = res.parentId;  // 该组织的上一级组织ID


                userInfo.orgId = parentId;

                alertResultMsg(true, '选择组织', res);
            },
            cancel: function (err) {
                alerMsg('用户已取消选择组织', err);
            },
            fail: function (err) {
                alertResultMsg(false, '选择组织', err);
            }
        });
    });


    // 11.6 获取组织信息接口
    document.querySelector('#getOrganizationInfo').onclick = (function () {
        if (userInfo.orgId == '') {
            alert('请先使用 chooseOrganization 获取需要查询的组织orgId');
            return;
        }

        var params = {
            orgId: userInfo.orgId  // 组织ID，由接口chooseOrganization parentId属性获取（必传）
        };

        sx.getOrganizationInfo({
            params: params,
            success: function (res) {
                // res为Organization对象
                var orgId = res.orgId;  // 组织ID
                var name = res.name;  // 组织名称
                var memberNumber = res.memberNumber;  // 组织下所属人数
                var parentId = res.parentId;  // 该组织的上一级组织ID

                alertResultMsg(true, '获取组织信息', res);
            },
            fail: function (err) {
                alertResultMsg(false, '获取组织信息', err);
            }
        });
    });


    // 11.7 查看员工信息接口
    document.querySelector('#viewEmployeeInfo').onclick = (function () {
        var params = {
            userId: '43205'  // 员工用户ID (必传)
        };

        sx.viewEmployeeInfo({
            params: params,
            success: function (res) {
                alertResultMsg(true, '查看员工信息操作', res);
            },
            fail: function (err) {
                alertResultMsg(false, '查看员工信息操作', err);
            }
        });
    });


    // 11.8 给员工发送消息接口
    document.querySelector('#sendMsgToEmployee').onclick = (function () {
        var params = {
            userId: '43205'  // 员工用户ID (必传)
        };

        sx.sendMsgToEmployee({
            params: params,
            success: function (res) {
                alertResultMsg(true, '给指定员工发送消息操作', res);
            },
            fail: function (err) {
                alertResultMsg(false, '给指定员工发送消息操作', err);
            }
        });
    });


    // 12 设备信息
    // 12.1 获取设备网络状态接口
    document.querySelector('#getDeviceNetworkType').onclick = (function () {
        sx.getDeviceNetworkType({
            success: function (res) {
                var networkType = res.networkType  // 设备当前使用的网络类型 值分别为“Disconnected”，“Wifi”，“4G”，“3G”，“2G”

                alertResultMsg(true, '获取网络状态', res);
            },
            fail: function (err) {
                alertResultMsg(false, '获取网络状态', err);
            }
        });
    });


    // 12.2 获取设备系统类型接口
    document.querySelector('#getDeviceSystemType').onclick = (function () {
        sx.getDeviceSystemType({
            success: function (res) {
                var sysType = res.sysType;  // 系统类型，iOS系统值为“iOS”，安卓为“Android”

                alertResultMsg(true, '获取系统类型', res);
            },
            fail: function (err) {
                alertResultMsg(false, '获取系统类型', err);
            }
        });
    });


    // 12.3 获取设备系统版本号接口
    document.querySelector('#getDeviceSystemVersion').onclick = (function () {
        sx.getDeviceSystemVersion({
            success: function (res) {
                var sysVersion = res.sysVersion;  // 系统版本号

                alertResultMsg(true, '获取系统版本号', res);
            },
            fail: function (err) {
                alertResultMsg(false, '获取系统版本号', err);
            }
        });
    });


    // 12.4 获取设备机型接口
    document.querySelector('#getDeviceModel').onclick = (function () {
        sx.getDeviceModel({
            success: function (res) {
                var model = res.model;  // 机型，iOS平台会直接返回类似“iPhone8,2”的标记，需要开发者自行处理

                alertResultMsg(true, '获取设备机型', res);
            },
            fail: function (err) {
                alertResultMsg(false, '获取设备机型', err);
            }
        });
    });


    // 12.5 获取设备唯一标识接口
    document.querySelector('#getDeviceUniqueID').onclick = (function () {
        sx.getDeviceUniqueID({
            success: function (res) {
                var uniqueId = res.uniqueId;  // 设备唯一标识

                alertResultMsg(true, '获取设备唯一标识', res);
            },
            fail: function (err) {
                alertResultMsg(false, '获取设备唯一标识', err);
            }
        });
    });


    // 12.6 获取设备信息接口
    document.querySelector('#getDeviceInfo').onclick = (function () {
        sx.getDeviceInfo({
            success: function (res) {
                // 返回设备信息接口中以上所有信息
                var networkType = res.networkType;
                var sysType = res.sysType;
                var sysVersion = res.sysVersion;
                var model = res.model;
                var uniqueId = res.uniqueId;

                alertResultMsg(true, '获取设备信息', res);
            },
            fail: function (err) {
                alertResultMsg(false, '获取设备信息', err);
            }
        });
    });


    // 13 数据缓存接口
    // 13.1 使用司信客户端缓存数据接口
    var dataCacheKey = 'userInfo';
    document.querySelector('#backupData').onclick = (function () {
        var backupData = {
            name: '张三',
            sex: '男',
            age: '18'
        };

        var params = {
            key: dataCacheKey,  // 缓存数据使用的key值，必须为字符串类型 (必传)
            value: backupData,  // 需要缓存的数据，支持数字、数组、对象以及多媒体文件图片和语言的缓存，当为多媒体文件时传递文件存储的localId，由4或者5中的接口得到 (必传)
            type: 0  //缓存的数据类型，默认0 数字、数组或者对象，1 图片，2 语音
        };

        sx.backupData({
            params: params,
            success: function (res) {
                alertResultMsg(true, '缓存数据', res);
            },
            fail: function (err) {
                alertResultMsg(false, '缓存数据', err);
            }
        });
    });

    // 缓存多媒体文件示例
    var imageCacheKey = 'imageLocalId';
    document.querySelector('#backupData-image').onclick = (function () {
        if (images.localId == '') {
            alert('请先使用 chooseImage或chooseAvatarImage 接口选择图片');
            return;
        }

        var params = {
            key: imageCacheKey,  // 缓存数据使用的key值，必须为字符串类型 (必传)
            value: images.localId,  // 需要缓存的数据，支持数字、数组、对象以及多媒体文件图片和语言的缓存，当为多媒体文件时传递文件存储的localId，由4或者5中的接口得到 (必传)
            type: 1  //缓存的数据类型，默认0 数字、数组或者对象，1 图片，2 语音
        };

        sx.backupData({
            params: params,
            success: function (res) {
                alertResultMsg(true, '缓存图片', res);
            },
            fail: function (err) {
                alertResultMsg(false, '缓存图片', err);
            }
        });
    });

    // 13.2 使用司信客户端读取缓存接口
    document.querySelector('#restoreData').onclick = (function () {
        var params = {
            key: dataCacheKey,  // 读取缓存数据的key值 (必传)
            type: 0  //该缓存数据的类型，默认0 数字、数组或者对象，1 图片，2 语音
        };

        sx.restoreData({
            params: params,
            success: function (res) {
                var value = res.value;  //当数据类型为图片或者语音时,会返回该多媒体文件缓存在客户端的localId

                alertResultMsg(true, '读取数据缓存', res);
            },
            fail: function (err) {
                alertResultMsg(false, '读取数据缓存', err);
            }
        });
    });

    // 读取多媒体文件缓存示例
    document.querySelector('#restoreData-image').onclick = (function () {
        var params = {
            key: imageCacheKey,  // 读取缓存数据的key值 (必传)
            type: 1  //该缓存数据的类型，默认0 数字、数组或者对象，1 图片，2 语音  (注意:多媒体文件最多缓存时间为30天)
        };

        sx.restoreData({
            params: params,
            success: function (res) {
                var localId = res.value;  //当数据类型为图片或者语音时,会返回该多媒体文件缓存在客户端的localId
                document.querySelector('.cacheImage').innerHTML = '<img src=\"' + localId + '\" style=\'width: 100%\'/>';

                alertResultMsg(true, '读取图片缓存', res);
            },
            fail: function (err) {
                alertResultMsg(false, '读取图片缓存', err);
            }
        });
    });

    // 13.3 移除司信客户端缓存数据接口
    document.querySelector('#removeData').onclick = (function () {
        var params = {
            key: dataCacheKey,  // 读取缓存数据的key值 (必传)
            type: 0  //该缓存数据的类型，默认0 数字、数组或者对象，1 图片，2 语音
        };

        sx.removeData({
            params: params,
            success: function (res) {
                alertResultMsg(true, '移除缓存数据', res);
            },
            fail: function (err) {
                alertResultMsg(false, '移除缓存数据', err);
            }
        });
    });

    // 13.4 移除当前应用在司信客户端所有缓存数据接口
    document.querySelector('#removeAllData').onclick = (function () {
        sx.removeAllData({
            success: function (res) {
                alertResultMsg(true, '移除所有缓存数据', res);
            },
            fail: function (err) {
                alertResultMsg(false, '移除所有缓存数据', err);
            }
        });
    });


    // 14 其他附加接口
    // 14.1 获取司信客户端版本号接口
    document.querySelector('#getAppVersion').onclick = (function () {
        sx.getAppVersion({
            success: function (res) {
                var version = res.version;  // 司信版本号

                alertResultMsg(true, '获取客户端版本信息', res);
            },
            fail: function (err) {
                alertResultMsg(false, '获取客户端版本信息', err);
            }
        });
    });
}

/******************************************************************/
function alerMsg(title, res) {
    var str = JSON.stringify(res);
    alert(title + '\n' + str);
}

function alertResultMsg(isSuccess, title, res) {
    var str = JSON.stringify(res);
    var message = isSuccess ? '成功！' : '失败！';
    alert(title + message + '\n' + str);
}
