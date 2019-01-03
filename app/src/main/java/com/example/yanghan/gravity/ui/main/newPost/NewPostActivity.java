package com.example.yanghan.gravity.ui.main.newPost;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.ui.commonInterface.ContextService;
import com.example.yanghan.gravity.ui.commonInterface.MultiResponse;
import com.example.yanghan.gravity.databinding.ActivityNewPostBinding;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import studio.carbonylgroup.textfieldboxes.SimpleTextChangedWatcher;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class NewPostActivity extends AppCompatActivity  implements MultiResponse,ContextService {
    private Drawer result = null;
    private NewPostViewModel mViewModel;
    private ActivityNewPostBinding binding;
    public Spinner spinner;
    public ArrayAdapter<String> arrAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        mViewModel = new NewPostViewModel(this,this);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_new_post);
        binding.setViewModel(mViewModel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        result = new DrawerBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .withFullscreen(true)
                .build();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        final TextFieldBoxes username = findViewById(R.id.title_box);
        final TextFieldBoxes password = findViewById(R.id.body_box);
        username.setSimpleTextChangeWatcher(new SimpleTextChangedWatcher() {
            @Override
            public void onTextChanged(String theNewText, boolean isError) {
                mViewModel.post.title=theNewText;

            }
        });

        password.setSimpleTextChangeWatcher(new SimpleTextChangedWatcher() {
            @Override
            public void onTextChanged(String theNewText, boolean isError) {
                mViewModel.post.postBody=theNewText;

            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);

        //数据


        //适配器
        arrAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mViewModel.teamList);
        //设置样式
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arrAdapter);

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
    }

    @Override
    public void failed() {
        runOnUiThread(new Runnable() {
            public void run() {
                Log.e("Faild", "!");
                AlertDialog alertDialog1 = new AlertDialog.Builder(getContext())
                        .setTitle("发帖失败")//标题
                        .setMessage("内容不合法")//内容
                        .setIcon(R.mipmap.ic_launcher)//图标
                        .create();
                alertDialog1.show();
            }});

    }

    @Override
    public void succeed() {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getContext(), "发帖成功", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }});


    }

    @Override
    public void error() {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
