package com.example.yanghan.gravity.data.other;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.yanghan.gravity.MainActivity;
import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.ui.login.LoginActivity;
<<<<<<< HEAD
import com.example.yanghan.gravity.ui.login.LoginViewModel;
import com.example.yanghan.gravity.ui.me.MeActivity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;



import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginManager {

    Context context=null;
    User user=null;
    LoginViewModel loginViewModel=null;

=======
import com.example.yanghan.gravity.ui.me.MeActivity;

import androidx.appcompat.app.AppCompatActivity;

public class LoginManager {

>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c
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

<<<<<<< HEAD

=======
>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c
        return user;
    }

    //跳转登录页面
    public void loginPage(Context context)
    {
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

<<<<<<< HEAD
    public static class LoginReponse
    {
        String code="";
        User data=null;
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

            if(loginReponse!=null&&loginReponse.code.equals("400"))
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
        requestManeger.post("http://118.25.41.237:8080/user/login",json,callback);


=======
    public void login(Context context,User user)
    {
        //http请求

        user.saveUser(context,true);
        Activity a=(Activity)context;
        a.onBackPressed();
>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c
    }

    public boolean logout(Context context,User user)
    {
        user.saveUser(context,false);
        loginPage(context);
        return false;
    }


}
