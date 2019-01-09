package com.example.yanghan.gravity.ui.news;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yanghan.gravity.data.model.News;

import com.example.yanghan.gravity.data.other.NewsListManager;
import com.example.yanghan.gravity.data.other.RequestManeger;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsListViewModel extends ViewModel {

    public List<News> news=new ArrayList<News>();
    private NewsListManager newsListManager=new NewsListManager();
    Context context;

    public NewsListViewModel(Context context)
    {
        this.context=context;
    }

    //请求赛事列表
    public void initNews(Context context)
    {
        Log.e("init","newslist");

        //newsListManager.list(context,this);
    }



    @BindingAdapter({"bind:imageUrl"})                  //加载赛事图片
    public static void loadImage(ImageView view, String imageUrl) {
        Log.e("url",imageUrl);

        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(new RequestOptions().error(new ColorDrawable(Color.GRAY)))
                .into(view);
    }

}
