package com.example.yanghan.gravity.ui.team;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.yanghan.gravity.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class GroupMessageActivity extends AppCompatActivity {
    private TextView group_name_message;
    private TextView associated_event_message;
    private TextView team_profile_message;
    public static String groupname="火箭队";
    public static String associatedevent="NBA联赛";
    public static String teamprofile="简介： "+"这是一支十分强大的篮球队伍，深受人们喜爱，曾经创造了非常多的辉煌";
   private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_message);

        Toolbar group_toolbar = (Toolbar) findViewById(R.id.toolbar_team);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(group_toolbar);
        result = new DrawerBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .withFullscreen(true)
                .build();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);



        init();


        //TODO something
        GridView gridView = (GridView) findViewById(R.id.team_member);

        //图片数据
        int[] images = {R.drawable.header, R.drawable.header1, R.drawable.headshot,
                R.drawable.header, R.drawable.header1, R.drawable.headshot
               };
        //图片编号

        //初始化数据
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("ItemImage", images[i]);
            //如果只需要显示图片，可以不用这一行，需要同时将from和to中的相关内容删去
            data.add(map);
        }
        String[] from = {"ItemImage"};
        int[] to = {R.id.imageView};

        //实例化一个适配器
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.items_group_member,from,to);
        //为GridView设置适配器
        gridView.setAdapter(adapter);




    }

 private void init(){
     group_name_message=(TextView)findViewById(R.id.GroupNameMessage);
     associated_event_message=(TextView)findViewById(R.id.AssociatedEventMessage);
     team_profile_message=(TextView)findViewById(R.id.TeamProfileMessage);
     groupname="火箭队";
     associatedevent="NBA联赛";
     teamprofile="     "+"这是一支十分强大的篮球队伍，深受人们喜爱，曾经创造了非常多的辉煌";
     group_name_message.setText(groupname);
     associated_event_message.setText(associatedevent);
     team_profile_message.setText(teamprofile);
 }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_grouptoolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_change:
               SwitchToChangePage();
               FillTheBlank();
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
    }

 private void SwitchToChangePage(){
     GroupCreateActivity.ChangeorCreate=true;
     Intent intent=new Intent(GroupMessageActivity.this,GroupCreateActivity.class);
     startActivity(intent);
     //this.finish();
 }

 private void FillTheBlank(){
        //TODO fill message work
 }
}
