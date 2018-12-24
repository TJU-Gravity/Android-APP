package com.example.yanghan.gravity.ui.team;

import android.app.Activity;
import android.util.Log;

import com.example.yanghan.gravity.data.model.Team;
import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.data.other.LoginManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;



 class LoginResponse
{

    String code="";
    String data="";
    String message="";
}
public class TeamListCallBack implements Callback {
    @Override
    public void onFailure(Call call, IOException e) {
        Log.e("request",e.toString());

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
//
        Log.e("all the data2: ",response.body().string());
        ObjectMapper mapper = new ObjectMapper();
       LoginResponse loginResponse=null;
//        loginResponse=mapper.readValue(response.body().string(),LoginResponse.class);
//        Log.e("all the data: ",loginResponse.toString());
//        Log.e("codeforme:",loginResponse.code);
//        Log.e("message",loginResponse.message);
//        Log.e("data",mapper.writeValueAsString(loginResponse.data));





    }
}

