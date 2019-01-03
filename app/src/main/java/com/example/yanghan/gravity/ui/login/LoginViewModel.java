package com.example.yanghan.gravity.ui.login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.data.other.LoginManager;

import androidx.lifecycle.ViewModel;


public class LoginViewModel extends ViewModel {
    public User user=new User();
    private LoginManager loginManager=new LoginManager();
    boolean isValiad=false;
    Context context;
    LoginViewModel(Context context)
    {
        this.context=context;
    }


    public void login(View v)
    {
        Log.e("click","login");

        Log.e("username",user.username);
        Activity a=(LoginActivity) v.getContext();
        if(isValiad)
        {
            loginManager.login(v.getContext(),user,this);
        }
        //不合法处理

    }
    public void loginFailed()
    {
        ((LoginActivity)context).loginFailed();
    }
    public void loginSucceed()
    {
        ((LoginActivity)context).loginSucceed();
    }
    public void error()
    {
        ((LoginActivity)context).error();
    }
}
