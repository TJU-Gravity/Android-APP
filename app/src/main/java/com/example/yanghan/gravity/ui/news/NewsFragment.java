package com.example.yanghan.gravity.ui.news;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yanghan.gravity.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsFragment extends Fragment {

    List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>(); //存储数据的数组列表
    List<Integer>imagelist=new ArrayList<Integer>();
    List<String>contestlist=new ArrayList<String>();
    private NewsViewModel mViewModel;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);

        imagelist.add(R.drawable.contest_1);
        imagelist.add(R.drawable.contest_2);
        imagelist.add(R.drawable.contest_3);

        contestlist.add("同济大学129歌会");
        contestlist.add("同济大学新格尔杯程序设计大赛");
        contestlist.add("同济大学嘉定之星歌手大赛");

        for (int i = 0; i < imagelist.size(); i++)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("contest_image", imagelist.get(i));
            map.put("contest_name", contestlist.get(i));
            listitem.add(map);
        }

        SimpleAdapter adapter = new  SimpleAdapter(getActivity()
                , listitem
                , R.layout.item_news
                , new String[]{"contest_image","contest_name"}
                , new int[]{R.id.contest_image, R.id.contest_name});

        ListView listView = (ListView) v.findViewById(R.id.contest_group);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//设置监听器
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),NewsDetailActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
    }

    /*********************************
     * 跳转赛事详情*
     * ↓↓↓↓↓↓*
     **********************************

     public void click(View v)
     {
     Context context = v.getContext();
     Intent intent=new Intent();
     intent.setClass(context, NewsDetailActivity.class);
     //intent.putExtra(NewsDetailActivity.EXTRA_NAME, "TFBoys");
     startActivity(intent);
     }
     */

}
