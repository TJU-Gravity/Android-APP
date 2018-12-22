package com.example.yanghan.gravity.ui.team;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yanghan.gravity.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TeamFragment extends Fragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {



    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private int mCount = 1;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;


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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        //获取mRecyclerView对象
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        //代码设置scrollbar无效？未解决！
        mRecyclerView.setVerticalScrollBarEnabled(true);
        //设置下拉刷新是否可见
        //mPullLoadMoreRecyclerView.setRefreshing(true);
        //设置是否可以下拉刷新
        //mPullLoadMoreRecyclerView.setPullRefreshEnable(true);
        //设置是否可以上拉刷新
        //mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
        //显示下拉刷新
        mPullLoadMoreRecyclerView.setRefreshing(true);
        //设置上拉刷新文字
        mPullLoadMoreRecyclerView.setFooterViewText("loading");
        //设置上拉刷新文字颜色
        //mPullLoadMoreRecyclerView.setFooterViewTextColor(R.color.white);
        //设置加载更多背景色
        //mPullLoadMoreRecyclerView.setFooterViewBackgroundColor(R.color.colorBackground);
        mPullLoadMoreRecyclerView.setLinearLayout();

        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        //getData();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TeamViewModel.class);
        // TODO: Use the ViewModel
    }

    private void InitView(){

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


    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerViewAdapter.addAllData(setList());
                        Log.e("number=","here!");

                       mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                        Log.e("number=","place2");
                    }
                });

            }
        }, 1000);

    }

    public void clearData() {
        mRecyclerViewAdapter.clearData();
        mRecyclerViewAdapter.notifyDataSetChanged();
    }


    private List<Map<String, Object>> setList() {
        List<Map<String, Object>> dataList = new ArrayList<>();
        int start = 6 * (mCount - 1);
        for (int i = start; i < 6 * mCount; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("group_logo", imagelist.get(i));
            map.put("group_name", groupnamelist.get(i));
            dataList.add(map);
        }
        return dataList;

    }

    @Override
    public void onRefresh() {
        Log.e("wxl", "onRefresh");
        setRefresh();
       // getData();
    }

    @Override
    public void onLoadMore() {
        Log.e("wxl", "onLoadMore");
        mCount = mCount + 1;
      //  getData();
    }

    private void setRefresh() {
        mRecyclerViewAdapter.clearData();
        mCount = 1;
    }



}