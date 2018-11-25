package com.example.yanghan.gravity.ui.team;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.Toast;
import com.example.yanghan.gravity.MainActivity;
import com.example.yanghan.gravity.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TeamFragment extends Fragment{


    List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>(); //存储数据的数组列表

   //存储图片
    List<Integer>imagelist=new ArrayList<Integer>();
    List<String>groupnamelist=new ArrayList<String>();

    private TeamViewModel mViewModel;

    public static TeamFragment newInstance() {
        return new TeamFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.team_fragment, container, false);

        //写死的数据，用于测试
        //插入队伍头像图片
        imagelist.add(R.mipmap.ic_launcher);
        imagelist.add(R.mipmap.ic_launcher);
        imagelist.add(R.mipmap.ic_launcher);
        //插入队伍名称
        groupnamelist.add("公牛队");
        groupnamelist.add("火箭队");
        groupnamelist.add("湖人队");


        for (int i = 0; i < imagelist.size(); i++)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("group_logo", imagelist.get(i));
            map.put("group_name", groupnamelist.get(i));
            listitem.add(map);
        }
        //getData(); //query data from a database

        SimpleAdapter adapter = new SimpleAdapter(getActivity()
                , listitem
                , R.layout.group_items
                , new String[]{"group_logo","group_name"}
                , new int[]{R.id.group_logo, R.id.group_name});
        // 第一个参数是上下文对象
        // 第二个是listitem
        // 第三个是指定每个列表项的布局文件
        // 第四个是指定Map对象中定义的两个键（这里通过字符串数组来指定）
        // 第五个是用于指定在布局文件中定义的id（也是用数组来指定）

        ListView listView = (ListView) v.findViewById(R.id.team_group);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//设置监听器
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              //  Toast.makeText(getActivity(), map.get("group_name").toString(), Toast.LENGTH_LONG).show();

                Intent intent=new Intent(getActivity(),GroupMessageActivity.class);
                startActivity(intent);

            }
        });
        FloatingActionButton fab = ( FloatingActionButton )v.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO CREATE A NEW TEAM
                GroupCreateActivity.ChangeorCreate=false;
                Intent intent=new Intent(getActivity(),GroupCreateActivity.class);
                startActivity(intent);

//                Snackbar.make(view, "创建新队伍", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });



        return v;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TeamViewModel.class);
        // TODO: Use the ViewModel
    }

//    private void init(){
//
//    }
}
