package com.example.yanghan.gravity.ui.login;

import android.util.Log;
import android.view.View;

import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.data.other.LoginManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    public User user=new User();
    private LoginManager loginManager=new LoginManager();
    boolean isValiad=false;


    public void login(View v)
    {
        Log.e("click","login");
        Log.e("username",user.username);
        if(isValiad)
            loginManager.login(v.getContext(),user);
        //不合法处理

    }
}
