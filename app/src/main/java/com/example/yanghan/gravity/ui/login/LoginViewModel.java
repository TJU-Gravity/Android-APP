package com.example.yanghan.gravity.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.View;

import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.data.other.LoginManager;
import com.example.yanghan.gravity.ui.login.register.NewUserActivity;
import com.example.yanghan.gravity.ui.me.favorites.FavoritesActivity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
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


    public void register(View v)
    {
        Log.e("register: ", "!");
        Intent intent = new Intent(v.getContext(), NewUserActivity.class);
        v.getContext().startActivity(intent);

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
