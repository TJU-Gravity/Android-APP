package com.example.yanghan.gravity.ui.team;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yanghan.gravity.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

public class GroupMessageChangeActivity extends AppCompatActivity {
    private Button button;
    private ExtendedEditText name;
    private ExtendedEditText match;
    private ExtendedEditText profile;
    private TextFieldBoxes matchconnect;
    private Drawer result = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_message_change);
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



//信息修改确认点击事件
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //这里只进行了非空的判断
                if(name.getText().toString().trim().isEmpty()||match.getText().toString().trim().isEmpty()||profile.getText().toString().trim().isEmpty()){
                    Toast.makeText(GroupMessageChangeActivity.this, "信息不能为空！", Toast.LENGTH_SHORT).show();

                }
                else{
                    GroupMessageActivity.groupname=name.getText().toString();
                    GroupMessageActivity.teamprofile=profile.getText().toString();
                    Intent intent=new Intent(GroupMessageChangeActivity.this,GroupMessageActivity.class);
                    startActivity(intent);

                }
            }
        });






//取消关联赛事点击事件
        matchconnect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if( GroupMessageActivity.associatedevent!="暂无赛事关联")
                showNormalDialog();

            }
        });

    }


//初始化控件
   private void init(){
       button=(Button)findViewById(R.id.button);
       name=(ExtendedEditText)findViewById(R.id.edit_note);
       match=(ExtendedEditText)findViewById(R.id.match_edit_note);
       profile=(ExtendedEditText)findViewById(R.id.profile_note);
       matchconnect=(TextFieldBoxes)findViewById(R.id.match_edit_box);
       name.setText(GroupMessageActivity.groupname);
       match.setText(GroupMessageActivity.associatedevent);
       profile.setText(GroupMessageActivity.teamprofile);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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



//确认弹窗
    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(GroupMessageChangeActivity.this);
        normalDialog.setTitle("是否取消关联该赛事？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do remove the match
                        GroupMessageActivity.associatedevent="暂无赛事关联";
                        refresh();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do nothing
                    }
                });
        // 显示
        normalDialog.show();
    }


//刷新界面
    private void refresh() {
        finish();
        Intent intent = new Intent(GroupMessageChangeActivity.this, GroupMessageChangeActivity.class);
        startActivity(intent);
    }

}
