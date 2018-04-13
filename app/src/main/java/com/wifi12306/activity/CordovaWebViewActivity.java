package com.wifi12306.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.perry.activity.BaseCompatActivity;
import com.perry.http.utils.NetUtil;
import com.wifi12306.R;
import com.wifi12306.plugins.PluginUntil;

import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaInterfaceImpl;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.PluginEntry;
import org.apache.cordova.engine.SystemWebChromeClient;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewClient;
import org.apache.cordova.engine.SystemWebViewEngine;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;


/**
 * 添加调用cordova 5.3的最新版本
 *
 * @author 李鹏军
 */
@SuppressLint("SetJavaScriptEnabled")
public class CordovaWebViewActivity extends BaseCompatActivity {

    private static final String TAG = "CordovaWebViewActivity";
    /**assets目录下的文件**/
    public static String dir_assets_h5 = "file:///android_asset/";

    /**
     * 网页载入控件
     **/
    private SystemWebView webView;
    // Read from config.xml:
    protected CordovaPreferences preferences;
    protected CordovaInterfaceImpl cordovaInterface = new CordovaInterfaceImpl(this) {
        @Override
        public Object onMessage(String id, Object data) {
            if ("onPageFinished".equals(id)) {
//          onPageFinishedUrl.add((String) data);
            } else if ("onReceivedError".equals(id)) {
                JSONObject d = (JSONObject) data;
                try {
                    onReceivedError(d.getInt("errorCode"), d.getString("description"), d.getString("url"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if ("exit".equals(id)) {
                finish();
            }
            return super.onMessage(id, data);
        }
    };
    private CordovaWebView cordovaWebView;
    private SystemWebViewEngine systemWebViewEngine;

    private ProgressBar progressBar;

    private ImageView iv_loading, iv_loading_error;
    private MyCordovaChromeClient chromeClient;
    private MyCordovaWebViewClient webViewClient;

    /**
     * 传递参数
     **/
    private String msgType = null;//类型
    private String msgTitle = null;//标题
    private String msgUrl = null;//链接地址
    private String loaddata;

    //加载显示
    private LinearLayout ll_loading, ll_loading_failed;
    private TextView tv_detial, tv_refresh;
    //新闻和公告过来 标题写死
    private boolean newTitle;

    private String exploration = "0";

    public static List<PluginEntry> pluginEntries;

    public static List<String> listclass;

    private String shareUrl = null;
    String title;
    String context;
    String pluginTitle;


    private void loadingDialogStop() {
        iv_loading.clearAnimation();
        ll_loading.setVisibility(View.GONE);
    }


    private int clearCacheFolder(File dir, long numDays) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, numDays);
                    }
                    if (child.lastModified() < numDays) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }

    private static final String APP_CACAHE_DIRNAME = "/webcache";

    /**
     * 清除WebView缓存
     */
    public void clearWebViewCache() {
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieManager.getInstance().removeSessionCookie();
        CookieSyncManager.getInstance().sync();
        CookieSyncManager.getInstance().startSync();
        webView.clearHistory();
        webView.clearView();
        //清理Webview缓存数据库
        try {
            deleteDatabase("webview.db");
            deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //WebView 缓存文件
        File appCacheDir = new File(getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME);
//		Log.e(TAG, "appCacheDir path=" + appCacheDir.getAbsolutePath());

        File webviewCacheDir = new File(getCacheDir().getAbsolutePath() + "/webviewCache");
//		Log.e(TAG, "webviewCacheDir path=" + webviewCacheDir.getAbsolutePath());

        //删除webview 缓存目录
        if (webviewCacheDir.exists()) {
            deleteFile(webviewCacheDir);
        }
        //删除webview 缓存 缓存目录
        if (appCacheDir.exists()) {
            deleteFile(appCacheDir);
        }
        clearCacheFolder(webviewCacheDir, System.currentTimeMillis());
        clearCacheFolder(appCacheDir, System.currentTimeMillis());
    }

    /**
     * 递归删除 文件/文件夹
     *
     * @param file
     */
    public void deleteFile(File file) {

        Log.i(TAG, "delete file path=" + file.getAbsolutePath());

        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        } else {
            Log.e(TAG, "delete file no exists " + file.getAbsolutePath());
        }
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestory   false");
        if (msgUrl != null && (msgUrl.startsWith("http://") || msgUrl.startsWith("https://") || msgUrl.startsWith("www."))) {
            clearWebViewCache();
        }
        super.onDestroy();
        cordovaWebView.handleDestroy();
        systemWebViewEngine.destroy();
        cordovaWebView = null;
        cordovaInterface = null;
    }
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_cordova_webview;
    }

    @Override
    public void findView() {
        getWindow().setFlags(//强制打开GPU渲染
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        PluginUntil.addPlugin();
        ConfigXmlParser parser = new ConfigXmlParser();
        parser.parse(this);
        preferences = parser.getPreferences();
        webView = (SystemWebView) findViewById(R.id.webview_ipsa);
        systemWebViewEngine = new SystemWebViewEngine(webView);
        cordovaWebView = new CordovaWebViewImpl(systemWebViewEngine);
        pluginEntries = parser.getPluginEntries();
        listclass = parser.getPluginclass();
        try {
            cordovaWebView.init(cordovaInterface, pluginEntries, parser.getPreferences());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /** 添加缓存H5页面功能 */
        // 开启 DOM storage API 功能
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);//设置缓冲大小，我设的是8M
        //使用cache资源,如果没有cache从网络上获取。或者使用LOAD_DEFAULT、LOAD_CACHE_ELSE_NETWORK
//        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        //设置 缓存模式
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        progressBar = (ProgressBar) findViewById(R.id.progressbar_top);
        iv_loading = (ImageView) findViewById(R.id.iv_loading);
        ll_loading = (LinearLayout) findViewById(R.id.ll_loading);
        ll_loading_failed = (LinearLayout) findViewById(R.id.ll_loading_fail);
        tv_detial = (TextView) findViewById(R.id.tv_detial);
        tv_refresh = (TextView) findViewById(R.id.tv_refresh);
        iv_loading_error = (ImageView) findViewById(R.id.iv_loading_error);
        tv_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重试
                initData();
            }
        });
    }
    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            Log.e(TAG,"InJavaScriptLocalObj showSource HTML:"+ html);
            // 拿到网页源代码再去截取 文章正文
//            final String replaceHtml = html.replace("", "");
            final String replaceHtml = html;
            if("<head></head><body></body>".equals(replaceHtml) || replaceHtml.length()<30){

            }else {
                //方案一：读取到url的html文件后修改替换对应的地址；替换成本地file:///android_asset/的文件后 实现重新load加载

//                webView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        webView.removeJavascriptInterface("local_obj");
//                        webView.clearHistory();
////                        webView.loadData(replaceHtml, "text/html; charset=UTF-8", null);//这种写法可以正确解码
////                        webView.loadDataWithBaseURL("file:///android_asset/", replaceHtml , "text/html", "utf-8", null);
//                        webView.loadDataWithBaseURL("file:///android_asset/", replaceHtml , "text/html; charset=UTF-8", null, null);
//                    }
//                });
            }
        }
    }
    @Override
    public void initView() {
//        findViewById(R.id.bar_back).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CordovaWebViewActivity.this.finish();
//            }
//        });
        initData();
    }

    protected void initData() {
        ll_loading_failed.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);

        progressBar.setVisibility(View.VISIBLE);
        ll_loading.setVisibility(View.VISIBLE);

        //1.先判断有没有下载zip包  2.如果没有去下载 3.下载完成解压 4 打开链接
        Intent intent = getIntent();
        loaddata = intent.getStringExtra("LoadData");
        Log.i("MyWebView","LoadData:"+loaddata);
        newTitle = intent.getBooleanExtra("title", false);
        msgTitle = intent.getStringExtra("msgTitle");
        exploration = intent.getStringExtra("explor");

        msgUrl = intent.getStringExtra("url");
        Log.i("MyWebView",":msgUrl"+msgUrl);
        if (msgUrl!=null){
            if (msgUrl.length() > 5){
                String film = msgUrl.substring(0,4);
                String apply = msgUrl.substring(0,5);
                        if (film.equals("film") || apply.equals("apply")){

                        }else {
                            if (!NetUtil.checkNet(CordovaWebViewActivity.this)) {
                                progressBar.setVisibility(View.GONE);
                                ll_loading.setVisibility(View.GONE);
                                webView.setVisibility(View.GONE);
                                ll_loading_failed.setVisibility(View.VISIBLE);
                                tv_detial.setText("网络连接失败  点击");
                                return;
                            }
                        }
            }else {
//                return;
            }
        }else {
            //自采编
//            return;
        }
//        msgUrl ="https://eximages.12306.cn/wificloud/task-distribution/file/20171211_190b54a0-03c2-42a4-a88f-f8d0a0d4fbc6.mp4";
//        msgUrl ="http://7sbs1m.com1.z0.glb.clouddn.com/index.html";
        webViewClient = new MyCordovaWebViewClient(systemWebViewEngine);
        chromeClient = new MyCordovaChromeClient(systemWebViewEngine);

        if (!TextUtils.isEmpty(loaddata)) {
            webView.loadData(loaddata, "text/html; charset=UTF-8", null);//这种写法可以正确解码
            Log.i("MyWebView",loaddata);
        } else if (!TextUtils.isEmpty(msgUrl)) {
                if (msgUrl.startsWith("http://") || msgUrl.startsWith("https://") || msgUrl.startsWith("www.") || msgUrl.startsWith("file://")) {
                cordovaWebView.loadUrl(msgUrl);//加载原生的webView.loadUrl
                Log.i("MyWebView",msgUrl);
            } else {

            }
        }
        loadingDialogStart();
        webView.setWebChromeClient(chromeClient);
        webView.setWebViewClient(webViewClient);//此设置会让加载页面延迟3秒显示；目前还未发现为何cordova的处理机制会导致这个问题发生，
    }

    private void loadingDialogStart() {
        ll_loading_failed.setVisibility(View.GONE);
        ll_loading.setVisibility(View.VISIBLE);
//        AnimationDrawable animationDrawable = (AnimationDrawable) iv_loading.getBackground();
//        animationDrawable.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e(TAG, "webView.canGoBack():" + webView.canGoBack());
        title = "";
        PluginTitle = false;
        //getWindow().getAttributes().softInputMode==WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED
        //判断隐藏软键盘是否弹出
        if (getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE) {
            //隐藏软键盘
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            return true;
        } else {
            if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
				webView.goBack();// 返回前一个页面
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    boolean isFinishTitle = false;

    private class MyCordovaChromeClient extends SystemWebChromeClient {
        public MyCordovaChromeClient(SystemWebViewEngine engine) {
            super(engine);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            progressBar.setProgress(newProgress);
            if (newProgress == 100) {
                loadingDialogStop();
                progressBar.setVisibility(View.GONE);
            } else {
                if (!progressBar.isShown()) {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }

    public String getFromAssets(String fileName) {
        try {
            //Return an AssetManager instance for your application's package
            InputStream is = getAssets().open(fileName + ".txt");
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            String text = new String(buffer);
            return text;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    String host;

    private class MyCordovaWebViewClient extends SystemWebViewClient {
        public MyCordovaWebViewClient(SystemWebViewEngine parentEngine) {
            super(parentEngine);
        }
        //方案二：通过 shouldInterceptRequest 方法替换对应的文件
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            log("shouldInterceptRequest url:"+url);
            if(url.contains("QIUToken.json")){ //加载指定.js时 引导服务端加载本地Assets/www文件夹下的cordova.js
                try {
//                    return new WebResourceResponse("application/x-javascript","utf-8",getBaseContext().getAssets().open("QIUToken.min.json"));
                    WebResourceResponse webResourceResponse = new WebResourceResponse("application/json","utf-8",getBaseContext().getAssets().open("QIUToken.min.json"));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        log("QIUToken.json webResourceResponse:" + webResourceResponse.getMimeType() + "," + webResourceResponse.getEncoding() + "," + webResourceResponse.getReasonPhrase()+","+webResourceResponse.getStatusCode());
                    }

                    return webResourceResponse;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(url.contains("SoccerGambling.json")){ //加载指定.js时 引导服务端加载本地Assets/www文件夹下的cordova.js
                try {//text/html
//                    application/json
//                    application/json;charset=utf-8
                    WebResourceResponse webResourceResponse = new WebResourceResponse("application/json","utf-8",getBaseContext().getAssets().open("SoccerGambling.min.json"));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        log("SoccerGambling.json webResourceResponse:" + webResourceResponse.getMimeType() + "," + webResourceResponse.getEncoding() + "," + webResourceResponse.getReasonPhrase()+","+webResourceResponse.getStatusCode());
                    }
                    return webResourceResponse;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return super.shouldInterceptRequest(view, url);
        }
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                log("shouldInterceptRequest WebResourceRequest request:"+request.getUrl().toString());
//                if(request.getUrl().toString().contains("cordova.js")){ //加载指定.js时 引导服务端加载本地Assets/www文件夹下的cordova.js
//                    try {
//                        return new WebResourceResponse("application/x-javascript","utf-8",getBaseContext().getAssets().open("www/cordova.js"));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
            return super.shouldInterceptRequest(view, request);
        }
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.e(TAG,"shouldOverrideUrlLoading url:"+url);
            if (url.endsWith(".apk")) {
                Uri uri = Uri.parse(url);
                Intent viewIntent = new Intent(Intent.ACTION_VIEW, uri);
                CordovaWebViewActivity.this.startActivity(viewIntent);
                return true;
            }
            if (url.startsWith("tel:")) {
            } else if (url.startsWith("mailto")) {
            } else if(url.startsWith("baiduboxlite://")){

            }
            else if(url.startsWith("http://") || url.startsWith("https://") ||url.startsWith("file:///")){
                view.loadUrl(url);
                Log.i("MyWebView",url);
//                return true;
            }else{
                Log.e(TAG,"抱歉走到 else 了 url:"+url);
            }
            // 如果不需要其他对点击链接事件的处理返回true，否则返回false
//			return true;
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            Log.e(TAG, "onLoadResource   " + url);
            super.onLoadResource(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            PluginTitle = false;
            Log.e(TAG, "加载前 ： " + url);
            if (url.startsWith("file:///")) {
                Log.e(TAG, "   true");
            } else if (url.startsWith("http://") || url.startsWith("https://")) {
                try {
                    URI uri = new URI(url);
                    host = uri.getHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "   true");
            }
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            webView.clearCache(true);
            super.onPageFinished(view, url);
            Log.e(TAG, "加载后 ： " + url);
            isFinishTitle = false;
            if("about:blank".equals(url)){
                Log.e(TAG,"onPageFinished :"+url);
                return;
            }
            //显示加载的url的html
            view.loadUrl("javascript:window.local_obj.showSource(document.getElementsByTagName('html')[0].innerHTML);");
//            view.loadUrl("javascript:window.local_obj.showSource('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
//            view.loadUrl("javascript:window.local_obj.showSource('<body>'+document.getElementsByTagName('html')[0].innerHTML+'</body>');");
//				if(webView.canGoForward() && SiXinApplication.jumpUSer){
//					webView.goForward();
//					SiXinApplication.jumpUSer = false;
//				}
            if (url.startsWith("file:///")) {
            } else if (url.startsWith("http://") || url.startsWith("https://")) {
                try {
                    URI uri = new URI(url);
                    host = uri.getHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            shareUrl = url;
            context = view.getTitle();
            if (context != null && !context.equals("")) {
                if (context.endsWith(".html")) {
                    setTitle("");
                } else {
                    setTitle(context);
                }
            } else if (msgTitle != null && !msgTitle.equals("")) {
                setTitle(msgTitle);
            } else {
                setTitle("");
            }
            if (newTitle) {
                if (msgUrl.startsWith("http://") || msgUrl.startsWith("https://") || msgUrl.startsWith("wwww.")) {
                    setTitle("详情");
                } else {
                    setTitle(context);
                }
            } else {
                    try {
                        URI uri = new URI(msgUrl);
                        URI htmluri = new URI(shareUrl);
                        if (uri.getHost().equals(htmluri.getHost())) {
                            if (title != null && !title.equals("")) {
                                setTitle(title);
                            } else {
                                if (newTitle) {
                                    setTitle("详情");
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            }

            if (loaddata != null && !loaddata.equals("") && loaddata.equals("2")) {
                setTitle(context);
            }

            if (PluginTitle) {
                setTitle(pluginTitle);
            }
            if (!("").equals(exploration) && exploration != null && !exploration.equals("1")) {
                if (msgType != null && !("").equals(msgType) && msgType.equals("4")) {
                    setTitle("详情");
                }
            }
            loadingDialogStop();
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            // TODO Auto-generated method stub
            super.onReceivedError(view, errorCode, description, failingUrl);
            Log.e(TAG,"onReceivedError errorCode:"+errorCode+",description:"+description+",failingUrl:"+failingUrl);
            if(failingUrl.startsWith("baiduboxlite://")){
                Log.e(TAG,"返回了 failingUrl.startsWith(baiduboxlite://)");
                return;
            }
            webView.setVisibility(View.GONE);
            ll_loading_failed.setVisibility(View.VISIBLE);
//            iv_loading_error.setImageResource(R.drawable.webview_load_noclient);
            tv_detial.setText("网络连接失败  点击");
        }

    }

    /**
     * Called when an activity you launched exits, giving you the requestCode you started it with,
     * the resultCode it returned, and any additional data from it.
     *
     * @param requestCode The request code originally supplied to startActivityForResult(),
     *                    allowing you to identify who this result came from.
     * @param resultCode  The integer result code returned by the child activity through its setResult().
     * @param intent      An Intent, which can return result data to the caller (various data can be attached to Intent "extras").
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        super.onActivityResult(requestCode, resultCode, intent);

        cordovaInterface.onActivityResult(requestCode, resultCode, intent);
    }

    private boolean PluginTitle = false;

    /**
     * Report an error to the HOST application. These errors are unrecoverable (i.e. the main resource is unavailable).
     * The errorCode parameter corresponds to one of the ERROR_* constants.
     *
     * @param errorCode   The error code corresponding to an ERROR_* value.
     * @param description A String describing the error.
     * @param failingUrl  The url that failed to load.
     */
    public void onReceivedError(final int errorCode, final String description, final String failingUrl) {
        // If errorUrl specified, then load it
    }

    /**
     * Display an error dialog and optionally exit application.
     */
    public void displayError(final String title, final String message, final String button, final boolean exit) {
        this.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(CordovaWebViewActivity.this);
                    dlg.setMessage(message);
                    dlg.setTitle(title);
                    dlg.setCancelable(false);
                    dlg.setPositiveButton(button,
                        new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                if (exit) {
                                    finish();
                                    Log.i("WebViewf","finish.....12");
                                }
                            }
                        });
                    dlg.create();
                    dlg.show();
                } catch (Exception e) {
                    finish();
                    Log.i("WebViewf","finish.....962");
                }
            }
        });
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        // Capture requestCode here so that it is captured in the setActivityResultCallback() case.
        cordovaInterface.setActivityResultRequestCode(requestCode);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            super.startActivityForResult(intent, requestCode, options);
        }
    }



}