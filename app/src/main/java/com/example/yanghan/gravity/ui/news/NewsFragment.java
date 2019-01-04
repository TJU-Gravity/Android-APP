package com.example.yanghan.gravity.ui.news;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.data.model.News;
import com.example.yanghan.gravity.data.other.NewsListManager;
import com.example.yanghan.gravity.data.other.RequestManeger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsFragment extends Fragment {

    List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>(); //存储数据的数组列表
    List<Integer>imagelist=new ArrayList<Integer>();
    List<String>contestlist=new ArrayList<String>();
    List<News> newslist=new ArrayList<>();
    private NewsListManager newsListManager=new NewsListManager();
    private SimpleAdapter adapter;
    private ListView listView;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);

        //init();
        newsListManager.list(this.getContext(),this);
        Log.e("listnum", String.valueOf(newslist.size()));

        //int rh=this.getResources().getIdentifier("ic_launcher", "drawable");

        imagelist.add(R.drawable.contest_1);
        imagelist.add(R.drawable.contest_2);
        imagelist.add(R.drawable.contest_3);



        adapter = new  SimpleAdapter(getActivity()
                , listitem
                , R.layout.item_news
                , new String[]{"contest_image","contest_name"}
                , new int[]{R.id.contest_image, R.id.contest_name});

        listView = (ListView) v.findViewById(R.id.contest_group);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//设置监听器
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News newsitem=newslist.get(position);
                newsListManager.detailPage(getActivity(),newsitem);
                //Intent intent = new Intent(getContext(),NewsDetailActivity.class);
                //intent.putExtra("newsid",newsitem.newsid);//传参
                //startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

public void initlist(List<News> news){
    for (Object newsitem:news)
    {
        newslist.add((News)newsitem);
    }
    getData();

}

private void getData(){
    for (Object newsitem:newslist) {
        Log.e("contest_name", ((News)newsitem).title);
        contestlist.add(((News)newsitem).title);
    }

    for (int i = 0; i < newslist.size(); i++)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contest_image", imagelist.get(i));
        map.put("contest_name", contestlist.get(i));
        listitem.add(map);
    }
    adapter.notifyDataSetChanged();
}
}
