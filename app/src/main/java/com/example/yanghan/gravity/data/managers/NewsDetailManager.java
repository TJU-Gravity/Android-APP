package com.example.yanghan.gravity.data.managers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.yanghan.gravity.data.model.News;
import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.ui.news.NewsDetailActivity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsDetailManager {
    Context context=null;
    News news=new News();
    User user=new User();
NewsDetailActivity newsDetailActivity=null;

    //是否收藏
    public boolean isLike(Context context)
    {
        News user=new News();
        return false;
    }

    public static class NewsDetailRequest
    {
        int id=0;
    }
    public static class NewsDetailReponse
    {
        String code="";
        News data=new News();
        String message="";
    }

    public static class LikeRequest
    {
        String username="";
        int newsid=0;
    }
    public static class LikeReponse
    {
        String code="";
        String data=null;
        String message="";
    }

    public class NewsDetailCallback implements Callback
    {

        @Override
        public void onFailure(Call call, IOException e) {
            Log.e("request",e.toString());

            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   // NewsViewModel.error();
                }
            });
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            NewsDetailReponse newsDetailReponse=null;
            newsDetailReponse=mapper.readValue(response.body().string(),NewsDetailManager.NewsDetailReponse.class);

            if(newsDetailReponse!=null&&newsDetailReponse.code.equals("400"))
            {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      //  loginViewModel.loginFailed();
                    }
                });
            }
            else {
                news=newsDetailReponse.data;
                newsDetailActivity.initnews(news);

            }
        }
    }

    public class LikeCallback implements Callback
    {

        @Override
        public void onFailure(Call call, IOException e) {
            Log.e("request",e.toString());

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            NewsDetailManager.LikeReponse likeReponse=null;
            likeReponse=mapper.readValue(response.body().string(),NewsDetailManager.LikeReponse.class);

            if(likeReponse!=null&&likeReponse.code.equals("400"))
            {

            }
            else {

            }
        }
    }

    public void like(Context context, User user,News news){
        this.user=user;
        this.news=news;
        this.context=context;

        //http请求
        LikeRequest likeRequest=new LikeRequest();
        likeRequest.newsid=news.newsid;
        likeRequest.username=user.username;
        ObjectMapper mapper = new ObjectMapper();
        String json="";
        try
        {
            json=mapper.writeValueAsString(likeRequest);
            Log.e("like",json);
        }
        catch (Exception e)
        {
            Log.e("like","json");
        }

        RequestManeger requestManeger=new RequestManeger();
        LikeCallback callback=new LikeCallback();
        requestManeger.post("http://118.25.41.237:8080/usernews/add",json,callback);
    }

    public void unlike(Context context, User user,News news){
        this.user=user;
        this.news=news;
        this.context=context;

        //http请求
        LikeRequest likeRequest=new LikeRequest();
        likeRequest.newsid=news.newsid;
        likeRequest.username=user.username;
        ObjectMapper mapper = new ObjectMapper();
        String json="";
        try
        {
            json=mapper.writeValueAsString(likeRequest);
            Log.e("unlike",json);
        }
        catch (Exception e)
        {
            Log.e("unlike","json");
        }

        RequestManeger requestManeger=new RequestManeger();
        LikeCallback callback=new LikeCallback();
        requestManeger.post("http://118.25.41.237:8080/usernews/delete",json,callback);
    }

    public void newsdetail(Context context,News news,NewsDetailActivity newsDetailActivity){
        this.news=news;
        this.context=context;
        this.newsDetailActivity=newsDetailActivity;
        //http请求
        NewsDetailRequest newsDetailRequest=new NewsDetailRequest();
        newsDetailRequest.id=news.newsid;
        ObjectMapper mapper = new ObjectMapper();
        String json="";
        try
        {
            json=mapper.writeValueAsString(newsDetailRequest);
            Log.e("detail",json);
        }
        catch (Exception e)
        {
            Log.e("detail","json");
        }

        RequestManeger requestManeger=new RequestManeger();
        NewsDetailCallback callback=new NewsDetailCallback();
        requestManeger.post("http://118.25.41.237:8080/news/detail",json,callback);
    }
}
