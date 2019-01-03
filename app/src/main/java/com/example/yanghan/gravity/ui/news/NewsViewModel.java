package com.example.yanghan.gravity.ui.news;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yanghan.gravity.data.model.News;
import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.data.other.NewsDetailManager;
import com.example.yanghan.gravity.data.other.RequestManeger;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public User user=new User();
    public News news=new News();
    public NewsViewModel()
    {
        super();
    }
    private NewsDetailManager newsDetailManager=new NewsDetailManager();
    //请求赛事详情
    public void initNews(Context context,int newsid)
    {
        Log.e("init","news");
        news.newsid=newsid;
         // if(news.loadNews(context));//http请求后废弃
        //newsDetailManager.newsdetail(context,news,this);
    }

    public String getImageUrl() {

        return news.poster;

    }

                 //加载赛事图片
    public static void loadImage(ImageView view, String imageUrl) {
        Log.e("url",imageUrl);

        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(new RequestOptions().error(new ColorDrawable(Color.GRAY)))
                .into(view);
    }



    //是否已经收藏?
    public boolean isLike(Context context)
    {
        News user=new News();

        //boolean isLogin=user.loadUser(context);
        //Log.e("username",user.username);
        //Log.e("isLogin",Boolean.toString(isLogin));
        //return  isLike;
        return false;
    }



    //分享
    public void onClickShare(Context context)
    {
        Log.e("click","shareBtn");
    }

    //收藏
    public void onClickLike(Context context)
    {
        Log.e("click","LikeBtn");

        //newsDetailManager.like();
    }

    //取消收藏
    public void onClickUnLike(Context context)
    {
        Log.e("click","UnLikeBtn");
        //newsDetailManager.unlike();
    }

}
