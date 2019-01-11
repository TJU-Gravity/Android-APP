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
import android.widget.Toast;


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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, LoadMoreListView.OnRefreshListener{
    private SwipeRefreshLayout swipeRefreshLayout;
        private LoadMoreListView loadMoreListView;
    //对象数据集合
    private ArrayList<ListViewItem> items;
    //listview的数据加载器adapter
    private RefreshListAdapter adapter;
    private Drawer result = null;
    public static List<TeamList> teamlist=new ArrayList<>();
    public LoadMoreListView listView=null;
   public static TeamList list=new TeamList();
   public static Team team =new Team();
    boolean tryToLogin=true;
    private int listCount=0;


    class TeamListResponse {
        @JsonProperty(value = "code")
        String code = "";
        @JsonProperty(value = "data")
        String data = null;
        @JsonProperty(value = "message")
        String message = "";
    }


    public class TeamListCallBack implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e("request", e.toString());

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            try{
                JSONObject object = new JSONObject(response.body().string());
                String code = object.getString("code");
                String data = object.getString("data");
                Log.e("teamname ", data);
                String message=object.getString("message");
                if("200".equals(code)){
                    try {
                        teamlist.clear();
                        JSONArray jsonArray = new JSONArray(data);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object1 = jsonArray.getJSONObject(i);
                            TeamList list=new TeamList();
                            list.teamID= Integer.parseInt(object1.getString("teamid"));
                            list.teamName = object1.getString("teamname");
                            list.headshot = object1.getString("headshot");
                            teamlist.add(list);
                      //      Log.e("teamna ", teamlist.get(0).teamName);
                         }
                        Log.e("team  ",String.valueOf(teamlist.size()));
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                initData();
                                // Stuff that updates the UI

                            }
                        });

                        }catch(Exception e){
                        Log.e("error ", e.getMessage());

                        }

                    }
                else{
                    Toast.makeText(TeamActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                }





            }catch (Exception e){
                Log.e("error ", "error");
            }

        }
    }

    public class TeamMessageCallBack implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e("request", e.toString());

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            try
            {
               // Log.e("result: ",response.body().string());
                team.TeamMumber.clear();
                JSONObject object = new JSONObject(response.body().string());
                String json= object.getString("data");
                String code=object.getString("code");
                if("200".equals(code)){
                    JSONObject object2 = new JSONObject(json);
                    String list=object2.getString("userlist");
                    list = list.replace('"',' ');
                    list = list.replace('[',' ');
                    list = list.replace(']',' ').trim();

                    String [] userlist=list.split(",");
                    for(int i=0;i<userlist.length;i++)
                    {
                        team.TeamMumber.add(userlist[i]);
                    }

                    team.teamname= object2.getString("teamname");
                    team.introduction=object2.getString("introduction");
                    team.captainid=object2.getString("captainName");
                    team.teamid=Integer.parseInt(object2.getString("teamid"));
                    team.memberNum=Integer.parseInt(object2.getString("membernum"));
                    GroupMessageActivity.groupname=team.teamname;
                    GroupMessageActivity.teamprofile=team.introduction;
                    Intent intent=new Intent(TeamActivity.this,GroupMessageActivity.class);
                    startActivity(intent);
                }
                else{
                      Toast.makeText(TeamActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                }

            }catch (Exception e)
            {
                Log.e("error:",e.getMessage());
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LoginManager loginManager=new LoginManager();
        if(!loginManager.isLogin(this)) {
            if (tryToLogin)
            {
                loginManager.loginPage(this);
                tryToLogin=false;
            }
            else
            {
                tryToLogin=true;
                onBackPressed();
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        String json="";
        try
        {
            json=mapper.writeValueAsString(loginManager.getCurrentUser(this));
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


        initView();
        initEvent();
        //initData();



        listView = (LoadMoreListView) findViewById(R.id.google_and_loadmore_refresh_listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//设置监听器
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem listViewItem=(ListViewItem) listView.getItemAtPosition(position);
                // String title=map.get("teamname");
                list.teamID=Integer.parseInt(listViewItem.getUserComment());
//                Toast.makeText(getApplicationContext(),
//                        "你选择了第"+position+"个Item，+itemContent的值是:"+content,
//                        Toast.LENGTH_SHORT).show();

                //  Toast.makeText(TeamActivity.this, map.get("group_name").toString(), Toast.LENGTH_LONG).show();



                Log.e("","enter here");
                String json=String.valueOf(list.teamID);
//                        String json="\""+String.valueOf(list.teamID)+"\"";
                //String json="29";
                RequestManeger requestManeger=new RequestManeger();
                TeamMessageCallBack callback=new TeamMessageCallBack();
                requestManeger.post("http://118.25.41.237:8080/team/teamDetail",json,callback);






            }



        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);



        Toolbar team_toolbar = (Toolbar) findViewById(R.id.team_toolbar);
        setSupportActionBar(team_toolbar);
        result = new DrawerBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .withFullscreen(true)
                .build();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);


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
        listCount=0;
        Log.e("initdata  ",String.valueOf(teamlist.size()));
        items = new ArrayList<ListViewItem>();

        //这里只是模拟10个列表项数据，在现实开发中，listview中的数据都是从服务器获取的。
        for (int i = 0; i < teamlist.size()&&i<5; i++) {
            ListViewItem item = new ListViewItem();
            item.setUserImg(R.mipmap.ic_launcher);
         //   Log.e("team  ",teamlist.get(i).teamName);
            item.setUserName(teamlist.get(i).teamName);
            item.setUserComment(String.valueOf(teamlist.get(i).teamID));
            items.add(item);
            listCount++;
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
        if(listCount<teamlist.size()){
            for (int i = listCount; i < (3+listCount)&&i<teamlist.size(); i++) {
                ListViewItem item = new ListViewItem();
                item.setUserImg(R.mipmap.ic_launcher);
                item.setUserName(teamlist.get(i).teamName);
                item.setUserComment(String.valueOf(teamlist.get(i).teamID));
                items.add(item);
                listCount++;
            }
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
