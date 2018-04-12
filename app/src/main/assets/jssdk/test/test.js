var requestParams = {
    type: 2,
    version: '1.0.0',
    device: 0
};

var params = {
    url: '',  // 网络请求地址（必传）
    method: 0,  // 请求方法，0 Get，1 Post，2 MultipartPost（content type = @"multipart/form-data" 类型）默认0
    requestParams: requestParams,  // 网络请求参数
    cancelTag: '',  // 用于取消网络请求的标记
    isShowLoading: true,  // 是否显示加载loading 默认true
    isUseStaticParams: false,  // 是否使用接口configRequest传递的静态参数，默认true，staticParams为全局变量，如重新设置则覆盖之前的数值，不设置值时为空
    isCloseSignVerification: false   //是否关闭签名验证，默认false，如果接口configRequest设置了开启验证签名,可通过该参数关闭当前网络请求的签名验证
};

sx.startRequest({
    params: params,
    success: function (res) {
        // 返回请求服务器的结果
    },
    cancel: function (err) {
        // 网络请求被取消时回调该函数
    },
    fail: function (err) {
    }
});
