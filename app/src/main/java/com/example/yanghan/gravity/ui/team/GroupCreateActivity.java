package com.example.yanghan.gravity.ui.team;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.print.PrinterId;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanghan.gravity.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

public class GroupCreateActivity extends AppCompatActivity {
    private String TeamName;
    private String TeamMatch;
    private String TeamMember;
    private String TeamProfile;
    private EditText name;
    private EditText match;
    private EditText member;
    private EditText profile;
    private Button button;
    public static boolean ChangeorCreate;
    private Drawer result = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_create);
        Toolbar group_toolbar = (Toolbar) findViewById(R.id.group_toolbar);
        setSupportActionBar(group_toolbar);
        result = new DrawerBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .withFullscreen(true)
                .build();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        init();
        button.setOnClickListener(new View.OnClickListener(){
             @Override
            public void onClick(View v) {
               //这里只进行了非空的判断
                 if(name.getText().toString().trim().isEmpty()||match.getText().toString().trim().isEmpty()||member.getText().toString().trim().isEmpty()||profile.getText().toString().trim().isEmpty()){
                     Toast.makeText(GroupCreateActivity.this, "信息不能为空！", Toast.LENGTH_SHORT).show();

                 }
                 else{
                     ChangeMessage();
                     SavetoDB();
                     Intent intent=new Intent(GroupCreateActivity.this,GroupMessageActivity.class);
                     startActivity(intent);
                     // GroupCreateActivity.this.finish();
                 }
             }
        });
    }

    private void init(){

        name=(EditText)findViewById(R.id.nameEdit);
        match=(EditText)findViewById(R.id.matchEdit);
        member=(EditText)findViewById(R.id.memberEdit);
        profile=(EditText)findViewById(R.id.profileEdit);
        button=(Button)findViewById(R.id.button);
        if(ChangeorCreate){
            name.setText(GroupMessageActivity.groupname);
            match.setText(GroupMessageActivity.associatedevent);
            profile.setText(GroupMessageActivity.teamprofile);
        }
    }
    private void SavetoDB(){
        //TODO 储存到数据库
    }

    private void ChangeMessage(){
        GroupMessageActivity.groupname=name.getText().toString();
        GroupMessageActivity.associatedevent=match.getText().toString();
        GroupMessageActivity.teamprofile=profile.getText().toString();
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
