package com.example.yanghan.gravity.data.other;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.yanghan.gravity.data.model.News;
import com.example.yanghan.gravity.data.model.Result;
import com.example.yanghan.gravity.ui.news.NewsDetailActivity;
import com.example.yanghan.gravity.ui.news.NewsFragment;
import com.example.yanghan.gravity.ui.news.NewsViewModel;


import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.yanghan.gravity.data.other.RequestManeger.JSON;

public class NewsListManager {

    public Context context=null;
    public List<News> newslist=new ArrayList<>();
    public NewsFragment newsFragment=null;

    //跳转详情页面
   public void detailPage(Context context,News news)
    {
        Intent intent = new Intent(context,NewsDetailActivity.class);
        intent.putExtra("newsid",news.newsid);//传参
        intent.putExtra("title",news.title);
        intent.putExtra("newsbody",news.newsbody);
        intent.putExtra("editorid",news.editorid);
        intent.putExtra("sponsor",news.sponsor);
        intent.putExtra("poster",news.poster);
        intent.putExtra("hits",news.hits);//传参

        context.startActivity(intent);
    }

    public static class ListRequest
    {
        String orderBy="default";
        Integer pages=1;
        Integer size=12;
    }

    public class ListCallback implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e("request",e.toString());

            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //newsListViewModel.error();
                }
            });
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            Result result=Result.mapper(response.body().string());

            if(result!=null&&result.code==400)
            {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      //  newsListViewModel.listFailed();
                    }
                });
            }
            else {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList<News> newData=mapper.convertValue(result.data.list, new TypeReference<ArrayList<News>>() { });
                    result.data.list=newData;
                    Log.e("onResponse: ",String.valueOf(newData.size()) );
                    for (Object news:newData)
                    {
                        newslist.add((News)news);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("newslist","newslist");
                        newsFragment.initlist(newslist);
                    }
                });
            }
        }
    }

    //请求newslist
    public void list(Context context,NewsFragment newsFragment)
    {

        this.context=context;
        this.newsFragment=newsFragment;
        //http请求
        ListRequest listRequest=new ListRequest();
        ObjectMapper mapper = new ObjectMapper();
        String json="";

        try
        {
            json=mapper.writeValueAsString(listRequest);
            Log.e("list",json);
        }
        catch (Exception e)
        {
            Log.e("list","json");
        }

        RequestManeger requestManeger=new RequestManeger();
        ListCallback callback=new ListCallback();
        requestManeger.post("http://118.25.41.237:8080/news/list",json,callback);
    }
}
