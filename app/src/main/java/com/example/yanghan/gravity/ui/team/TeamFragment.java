package com.example.yanghan.gravity.ui.team;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yanghan.gravity.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TeamFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private  SimpleAdapter adapter;
    //private RefreshListAdapter adapter;
    private LoadMoreListView loadMoreListView;
    private View v;
    List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>(); //存储数据的数组列表

    //存储图片和文字
    List<Integer>imagelist=new ArrayList<Integer>();
    List<String>groupnamelist=new ArrayList<String>();

    private TeamViewModel mViewModel;

    public static TeamFragment newInstance() {
        return new TeamFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_team, container, false);
        InitView();


        //loadMoreListView = (LoadMoreListView) v.findViewById(R.id.google_and_loadmore_refresh_listview);
        //loadMoreListView.setOnRefreshListener(this);

        swipeRefreshLayout.setOnRefreshListener(this);

        //getData(); //query data from a database

        adapter = new SimpleAdapter(getActivity()
                , listitem
                , R.layout.items_group
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

    private void InitView(){
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipe_refresh_layout);
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
    }






    @Override
    public void onRefresh() {
        //因为本例中没有从网络获取数据，因此这里使用Handler演示4秒延迟来从服务器获取数据的延迟现象，以便于大家
        // 能够看到listView正在刷新的状态。在现实使用时只需要使用run（）{}方法中的代码就行了。
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取最新的list数据
                setRefreshData();
                //通知界面显示，
                adapter.notifyDataSetChanged();
                // 通知listview刷新数据完毕,让listview停止刷新
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 600);
    }


//    @Override
//    public void onLoadingMore() {
//
//        //因为本例中没有从网络获取数据，因此这里使用Handler演示4秒延迟来从服务器获取数据的延迟现象，以便于大家
//        // 能够看到listView正在刷新的状态。大家在现实使用时只需要使用run（）{}方法中的代码就行了。
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //获取最新的list数据
//                setRefreshData();
//                //通知界面显示，
//                adapter.notifyDataSetChanged();
//                // 通知listview刷新数据完毕,让listview停止刷新,取消加载跟多
//                loadMoreListView.loadMoreComplete();
//            }
//        }, 6000);
//    }


    private void setRefreshData() {

        //这里只是模拟3个列表项数据，在现实开发中，listview中的数据都是从服务器获取的。
//        for (int i = 0; i < 3; i++) {
//            ListViewItem item = new ListViewItem();
//            item.setUserImg(R.mipmap.ic_launcher);
//            item.setUserName("seven" + i);
//            item.setUserComment("这是一个下拉刷新，上拉加载更多的ListView");
//            items.add(item);
//        }
    }

}