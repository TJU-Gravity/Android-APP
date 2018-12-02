package com.example.yanghan.gravity.data.other;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.yanghan.gravity.MainActivity;
import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.ui.login.LoginActivity;
import com.example.yanghan.gravity.ui.me.MeActivity;

import androidx.appcompat.app.AppCompatActivity;

public class LoginManager {

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
        User user=new User();
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

    public void login(Context context,User user)
    {
        //http请求

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
