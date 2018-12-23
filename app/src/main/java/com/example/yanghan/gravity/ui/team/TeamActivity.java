package com.example.yanghan.gravity.ui.team;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.yanghan.gravity.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TeamActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener, LoadMoreListView.OnRefreshListener{
    private SwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreListView loadMoreListView;
    //对象数据集合
    private ArrayList<ListViewItem> items;
    //listview的数据加载器adapter
    private RefreshListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        initView();
        initEvent();
        initData();

        FloatingActionButton fab = ( FloatingActionButton )findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO CREATE A NEW TEAM
                Intent intent=new Intent(TeamActivity.this,GroupCreateActivity.class);
                startActivity(intent);


            }
        });
    }


    private void initData() {
        items = new ArrayList<ListViewItem>();

        //这里只是模拟10个列表项数据，在现实开发中，listview中的数据都是从服务器获取的。
        for (int i = 0; i < 10; i++) {
            ListViewItem item = new ListViewItem();
            item.setUserImg(R.mipmap.ic_launcher);
            item.setUserName("seven" + i);
            item.setUserComment("这是google官方一个下拉刷新ListView+自定义ListView上拉加载跟多");
            items.add(item);
        }

        //为listview配置adapter
        adapter = new RefreshListAdapter(TeamActivity.this, items);
        loadMoreListView.setAdapter(adapter);
    }

    /**
     * 给控件添加事件
     */
    private void initEvent() {
        swipeRefreshLayout.setOnRefreshListener(this);

        //设置圆圈进度条的背景颜色
//        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(
//                getResources().getColor(R.color.colorPrimary));

        //设置进度条变化的颜色
//        swipeRefreshLayout.setColorSchemeResources(
//                R.color.firstColor,
//                R.color.secondColor,
//                R.color.thirdColor,
//                R.color.forthColor);
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_google_and_loadmore_refresh_layout);
        loadMoreListView = (LoadMoreListView) findViewById(R.id.google_and_loadmore_refresh_listview);
        loadMoreListView.setOnRefreshListener(this);
    }

    /**
     * 触发了下拉刷新事件，就会执行onRefresh()方法，这样就能在这个
     * 方法中去执行网络获取最新的数据，然后再刷新到listview上面
     */
    @Override
    public void onRefresh() {
        //因为本例中没有从网络获取数据，因此这里使用Handler演示4秒延迟来从服务器获取数据的延迟现象，以便于大家
        // 能够看到listView正在刷新的状态。大家在现实使用时只需要使用run（）{}方法中的代码就行了。
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
        }, 6000);
    }

    /**
     * 模拟加载刷新数据
     */
    private void setRefreshData() {
        //这里只是模拟3个列表项数据，在现实开发中，listview中的数据都是从服务器获取的。
        for (int i = 0; i < 3; i++) {
            ListViewItem item = new ListViewItem();
            item.setUserImg(R.mipmap.ic_launcher);
            item.setUserName("seven" + i);
            item.setUserComment("这是一个下拉刷新，上拉加载更多的ListView");
            items.add(item);
        }
    }

    /**
     * ”加载更多“ 的回调接口方法
     */
    @Override
    public void onLoadingMore() {

        //因为本例中没有从网络获取数据，因此这里使用Handler演示4秒延迟来从服务器获取数据的延迟现象，以便于大家
        // 能够看到listView正在刷新的状态。大家在现实使用时只需要使用run（）{}方法中的代码就行了。
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取最新的list数据
                setRefreshData();
                //通知界面显示，
                adapter.notifyDataSetChanged();
                // 通知listview刷新数据完毕,让listview停止刷新,取消加载跟多
                loadMoreListView.loadMoreComplete();
            }
        }, 6000);
    }


}
