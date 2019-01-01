package com.example.yanghan.gravity.ui.team;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.data.model.Team;
import com.example.yanghan.gravity.data.model.TeamList;
import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.data.other.LoginManager;
import com.example.yanghan.gravity.data.other.RequestManeger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

import java.io.IOException;
import java.util.ArrayList;

public class TeamActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, LoadMoreListView.OnRefreshListener{
    private SwipeRefreshLayout swipeRefreshLayout;
        private LoadMoreListView loadMoreListView;
    //对象数据集合
    private ArrayList<ListViewItem> items;
    //listview的数据加载器adapter
    private RefreshListAdapter adapter;
    private Drawer result = null;


    class TeamListResponse {

        String code = "";
        String data = "";
        String message = "";
    }


    public class TeamListCallBack implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e("request", e.toString());

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
//
            Log.e("all the data2: ", response.body().string());
            ObjectMapper mapper = new ObjectMapper();
            TeamListResponse loginResponse = null;
//        loginResponse=mapper.readValue(response.body().string(),LoginResponse.class);
//        Log.e("all the data: ",loginResponse.toString());
//        Log.e("codeforme:",loginResponse.code);
//        Log.e("message",loginResponse.message);
//        Log.e("data",mapper.writeValueAsString(loginResponse.data));


        }
    }

    public class TeamMessageCallBack implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e("request", e.toString());

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
//
            Log.e("all the data2: ", response.body().string());
            ObjectMapper mapper = new ObjectMapper();
            TeamListResponse loginResponse = null;
//        loginResponse=mapper.readValue(response.body().string(),LoginResponse.class);
//        Log.e("all the data: ",loginResponse.toString());
//        Log.e("codeforme:",loginResponse.code);
//        Log.e("message",loginResponse.message);
//        Log.e("data",mapper.writeValueAsString(loginResponse.data));


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        User UserForTeam=new User();
        UserForTeam.loadUser(this);
        ObjectMapper mapper = new ObjectMapper();
        String json="";
        try
        {
            json=mapper.writeValueAsString(UserForTeam);
            Log.e("login",json);
        }
        catch (Exception e)
        {
           // Log.e("login","json");
        }

        RequestManeger requestManeger=new RequestManeger();
        String response="";
        TeamListCallBack callback=new TeamListCallBack();
        requestManeger.post("http://118.25.41.237:8080/team/myTeamList",json,callback);







     //   LoginManager.loginPage(TeamActivity.this);


        Toolbar team_toolbar = (Toolbar) findViewById(R.id.team_toolbar);
        setSupportActionBar(team_toolbar);
        result = new DrawerBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .withFullscreen(true)
                .build();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        initView();
        initEvent();
        initData();



        LoadMoreListView listView = (LoadMoreListView) findViewById(R.id.google_and_loadmore_refresh_listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//设置监听器
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //  Toast.makeText(getActivity(), map.get("group_name").toString(), Toast.LENGTH_LONG).show();
                TeamList teamList=new TeamList();
                if(teamList.teamID==0)
                {
                    Team team=new Team();
                    team.teamID=teamList.teamID;
                    team.teamName=teamList.teamName;
                    ObjectMapper mapper = new ObjectMapper();
                    String json="";
                    try
                    {
                        json=mapper.writeValueAsString(team);
                        Log.e("login",json);
                    }
                    catch (Exception e)
                    {
                        // Log.e("login","json");
                    }

                    RequestManeger requestManeger=new RequestManeger();
                    String response="";
                    TeamMessageCallBack callback=new TeamMessageCallBack();
                    requestManeger.post("http://118.25.41.237:8080/team/teamDetail",json,callback);

                }
                Intent intent=new Intent(TeamActivity.this,GroupMessageActivity.class);
                startActivity(intent);

            }
        });




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
            item.setUserName("第"+i+"支队伍");
            //item.setUserComment("这是google官方一个下拉刷新ListView+自定义ListView上拉加载跟多");
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
        }, 200);
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
           // item.setUserComment("这是一个下拉刷新，上拉加载更多的ListView");
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
        }, 200);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_grouptoolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
        // this.finish();
    }
}
