package com.example.yanghan.gravity.data.managers;

import android.util.Log;

import com.example.yanghan.gravity.core.Configration;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.Call;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestManeger {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();



   public void post(String url, String json,Callback callback)  {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(Configration.baseURL+url)
                .post(body)
                .build();


        Call call= client.newCall(request);
        call.enqueue(callback);

    }

    public void post(String url, Object o,Callback callback)  {
        ObjectMapper mapper = new ObjectMapper();
        String json="";
        try
        {
            json=mapper.writeValueAsString(o);
            Log.e("json",json);
        }
        catch (Exception e)
        {
            Log.e("json",e.toString());
        }

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(Configration.baseURL+url)
                .post(body)
                .build();

        Call call= client.newCall(request);
        call.enqueue(callback);

    }

}
