package com.example.yanghan.gravity.ui.news;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yanghan.gravity.data.model.News;

import com.example.yanghan.gravity.data.managers.NewsListManager;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

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
