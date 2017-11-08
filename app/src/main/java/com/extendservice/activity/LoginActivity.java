package com.extendservice.activity;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.extendservice.R;
import com.extendservice.bean.LoginBean;
import com.perry.activity.BaseCompatActivity;
import com.perry.http.Listener.AppCallback;
import com.perry.http.manager.RequestManager;
import com.perry.http.request.QueryLogin;
import com.perry.view.ToastAlone;

/**
 * 登录页面
 */

public class LoginActivity extends BaseCompatActivity implements View.OnClickListener{


    private EditText usernameET;
    private EditText passwordET;
    private String username;
    private String password;
    private RequestManager requestManager;

    @Override
    public int initLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void findView() {
        requestManager = RequestManager.getInstance(this);
        findViewById(R.id.login).setOnClickListener(this);
        usernameET = (EditText) findViewById(R.id.username);
        passwordET = (EditText) findViewById(R.id.password);
    }

    @Override
    public void initView() {

    }

    @Override
    public void onClick(View v) {
        if (!checkUserInfo()){
            return;
        }
        requestLogin();
    }

    /**
     * 用户名非空验证
     *
     * @return
     */
    private boolean checkUserInfo() {
//        username = usernameET.getText().toString().trim();
//        password = passwordET.getText().toString().trim();
        username = "shlcpsry1";
        password = "ztc123456";
        if ("".equals(username) || "".equals(password)) {
            Toast.makeText(LoginActivity.this, "用户名或密码不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void requestLogin(){
        showCustomProcessDialog("正在登录...");
        requestManager.sendRequest(new QueryLogin(username,password).withResponse(LoginBean.class, new AppCallback<LoginBean>() {
            @Override
            public void callback(LoginBean loginBean) {
                dismissPostingDialog();
                if (null != loginBean){
                    if (loginBean.statusId==0){
                        LoginBean.LoginDataBean dataBean = loginBean.data;
                        Intent intent = new Intent(LoginActivity.this,ChooseUserActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        ToastAlone.showToast(LoginActivity.this,loginBean.msg,Toast.LENGTH_SHORT).show();
                    }
                }else {
                    ToastAlone.showToast(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void callbackString(String str) {

            }

            @Override
            public void onError(Exception e) {
                dismissPostingDialog();
                ToastAlone.showToast(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }));
    }

}
