package com.example.yanghan.gravity.data.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.ui.login.LoginActivity;

import com.example.yanghan.gravity.ui.login.LoginViewModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;




public class LoginManager {


    Context context=null;
    public static User user=null;
    LoginViewModel loginViewModel=null;

    //是否登录
    public boolean isLogin(Context context)
    {
        User user=new User();

        boolean isLogin=user.loadUser(context);
        Log.e("username",user.username);
        Log.e("isLogin",Boolean.toString(isLogin));
        return  isLogin;
    }

    //得到当前用户
    public User getCurrentUser(Context context)
    {

        user=new User();
        user.loadUser(context);
        //http请求
        return user;
    }

    //跳转登录页面
    public void loginPage(Context context)
    {
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }


    public static class LoginReponse
    {
        @JsonProperty(value = "code")
        String code="";
        @JsonProperty(value = "data")
        User data=null;
        @JsonProperty(value = "message")
        String message="";
    }

    public class LoginCallback implements Callback
    {

        @Override
        public void onFailure(Call call, IOException e) {
            Log.e("request",e.toString());

            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loginViewModel.error();
                }
            });
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            LoginReponse loginReponse=null;
            loginReponse=mapper.readValue(response.body().string(),LoginReponse.class);

            if(loginReponse!=null&&loginReponse.code=="400")
            {
                ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loginViewModel.loginFailed();
                }
            });


            }
            else {
                loginReponse.data.saveUser(context,true);
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loginViewModel.loginSucceed();
                    }
                });


            }

        }
    }

    public void login(Context context, User user,LoginViewModel loginViewModel)
    {
        this.user=user;
        this.context=context;
        this.loginViewModel=loginViewModel;
        user.saveUser(context,false);
        //http请求
        ObjectMapper mapper = new ObjectMapper();
        String json="";
        try
        {
            json=mapper.writeValueAsString(user);
            Log.e("login",json);
        }
        catch (Exception e)
        {
            Log.e("login","json");
        }

        RequestManeger requestManeger=new RequestManeger();
        String response="";
        LoginCallback callback=new LoginCallback();


        requestManeger.post("/user/login",json,callback);
        user.saveUser(context,true);
        Activity a=(Activity)context;
        a.onBackPressed();

    }

    public boolean logout(Context context,User user)
    {
        user.saveUser(context,false);
        loginPage(context);
        return false;
    }


}
