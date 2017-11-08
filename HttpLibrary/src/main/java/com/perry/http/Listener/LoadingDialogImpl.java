package com.perry.http.Listener;

import android.app.Activity;

import com.perry.http.view.IssLoadingDialog;


/**
 * Created by isoftstone on 16/3/4.
 */
public class LoadingDialogImpl implements LoadingInterface {

    private IssLoadingDialog dialog;

    private String loadMsg;


    public LoadingDialogImpl(Activity act, String msg) {
        dialog = new IssLoadingDialog(act);
        dialog.setLoadingMessage(msg);
        loadMsg = msg;

    }

    public void setMessage(String msg){
        dialog.setLoadingMessage(msg);
    }
    @Override
    public String getDesc() {
        return loadMsg;
    }

    @Override
    public void start() {
        try {
            dialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void finish() {
        dialog.dismiss();
    }

    public boolean isShowing(){
       return dialog.isShowing();
    }
}
