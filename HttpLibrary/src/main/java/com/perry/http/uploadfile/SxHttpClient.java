package com.perry.http.uploadfile;

import android.content.Context;
import android.os.Handler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by rqyuea on 2015/10/20 0020.
 */
public class SxHttpClient {


    private static ExecutorService exec = new ThreadPoolExecutor(0, 5, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    private ProgressListener progressListener;

    private Handler mHandler;



    public  SxHttpClient(){

    }

    public SxHttpClient(Context context) {
        mHandler = new Handler();//(context.getMainLooper());
    }


    public void setProgressListener(ProgressListener listener) {
        this.progressListener = listener;
    }

    public interface ProgressListener {
        void onSuccess(String json);

        void onFailure(Exception ex);

        void onProcess(long current, long total);
    }


    public String uploadFile(String strUrl, Map<String, String> params, File file) {
        Map<String, File> fileParams = new HashMap<>();
        fileParams.put("file", file);
        return uploadFile(strUrl, params, fileParams);
    }

    public String uploadFile(String strUrl, Map<String, String> params,
                             Map<String, File> fileParams) {

        return uploadFile(strUrl, params, fileParams, false);

    }

    /**
     * @param strUrl 传递的普通参数
     * @param params   需要上传的文件名
     * @throws IOException
     */
    public String uploadFile(final String strUrl, final Map<String, String> params,
                             final Map<String, File> fileParams, boolean sync) {


        if (sync) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    upload(strUrl, params, fileParams, mHandler);
                }
            });

        } else {
            return upload(strUrl, params, fileParams, null);
        }


        return null;
    }


    private String upload(String strUrl, Map<String, String> params, Map<String, File> fileParams, Handler handler) {

        try {

            File file = null;
            String name = "";
            for (String key : fileParams.keySet()) {
                name = key;
                file = fileParams.get(key);
                break;
            }
            long fileLen = file.length();

            String BOUNDARY = "----WebKitFormBoundaryT1HoybnYeFOGFlBR";

            StringBuilder sb = new StringBuilder();
            /**
             * 普通的表单数据
             */
            if (params != null)
                for (String key : params.keySet()) {
                    sb.append("--" + BOUNDARY + "\r\n");
                    sb.append("Content-Disposition: form-data; name=\"" + key
                            + "\"" + "\r\n");
                    sb.append("\r\n");
                    sb.append(params.get(key) + "\r\n");
                }
            /**
             * 上传文件的头
             */
            sb.append("--" + BOUNDARY + "\r\n");
            sb.append("Content-Disposition: form-data; name=\"" + name
                    + "\"; filename=\"" + file.getName() + "\"" + "\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
//            sb.append("\r\n");

            byte[] headerInfo = sb.toString().getBytes("UTF-8");
            byte[] endInfo = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            conn.setRequestProperty("Content-Length", String
                    .valueOf(headerInfo.length + fileLen
                            + endInfo.length));
            conn.setDoOutput(true);
            conn.setDoInput(true);

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            InputStream in = new FileInputStream(file);
            out.write(headerInfo);

            int writed = 0;

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
                writed += len;
                if (mHandler == null) {
                    progressListener.onProcess(writed, fileLen);
                } else {

                   final int w = writed;
                    final long flen = fileLen;
                    boolean post = handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressListener.onProcess(w, flen);
                        }
                    });
                }
            }


            out.write(endInfo);
            in.close();
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line, content = "";

            while ((line = reader.readLine()) != null) {
                content += line;
            }

            System.out.println("line:" + line);
            if (handler == null) {
                progressListener.onSuccess(content);
            } else {
                final String cont = content;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressListener.onSuccess(cont);
                    }
                });
            }
            return content;

        } catch (Exception e) {
            e.printStackTrace();
            if (handler == null) {
                progressListener.onFailure(e);
            } else {
                final Exception e1 = e;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressListener.onFailure(e1);
                    }
                });
            }
        }

        return null;
    }
}



