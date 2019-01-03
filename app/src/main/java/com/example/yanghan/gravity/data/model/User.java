package com.example.yanghan.gravity.data.model;



import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;

public class User
{
    public String username="";
    public String nickname="";
    public String pwd="";
    public String gender="";
    public String status="";
    public String headshot="";
    public String loc="";
    public String phonenumber="";
    public String userprivileges="";
    public String introduction="";
    public String email="123@example.com";


    public void saveUser(Context context,boolean login) {
//获取sharedPreferences对象
        SharedPreferences sharedPreferences = context.getSharedPreferences("person", 0);
        //获取editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        //存储键值对
        editor.putString("username", username);
        editor.putString("nickname", nickname);
        editor.putString("password", pwd);
        editor.putString("gender", gender);
        editor.putString("headshot", headshot);
        editor.putString("email", email);
        editor.putString("introduction", introduction);


        editor.putBoolean("login", login);

        editor.commit();//提交修改


    }

    public boolean loadUser(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("person", 0);
        //获取editor对象
       username=sharedPreferences.getString("username","");
        nickname=sharedPreferences.getString("nickname","");
       pwd=sharedPreferences.getString("password","");
        gender=sharedPreferences.getString("gender","");
        headshot=sharedPreferences.getString("headshot","");
        email=sharedPreferences.getString("email","");
        introduction=sharedPreferences.getString("introduction","");


       Log.e("username",username);
       return sharedPreferences.getBoolean("login",false);
    }



}
