package com.example.yanghan.gravity.ui.login.register;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.data.model.ResponseResult;
import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.data.other.RequestManeger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import studio.carbonylgroup.textfieldboxes.SimpleTextChangedWatcher;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class NewUserActivity extends AppCompatActivity {
    private Drawer result = null;
    private User user=new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        result = new DrawerBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .withFullscreen(true)
                .build();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        final TextFieldBoxes usernameBox = findViewById(R.id.username_box);

        usernameBox.setSimpleTextChangeWatcher(new SimpleTextChangedWatcher() {
            @Override
            public void onTextChanged(String theNewText, boolean isError) {
                user.username=theNewText;

            }
        });

        final TextFieldBoxes nicknameBox = findViewById(R.id.nickname_box);

        nicknameBox.setSimpleTextChangeWatcher(new SimpleTextChangedWatcher() {
            @Override
            public void onTextChanged(String theNewText, boolean isError) {
                user.nickname=theNewText;

            }
        });

        final TextFieldBoxes passwordBox = findViewById(R.id.password_box);

        passwordBox.setSimpleTextChangeWatcher(new SimpleTextChangedWatcher() {
            @Override
            public void onTextChanged(String theNewText, boolean isError) {
                user.pwd=theNewText;

            }
        });

        final TextFieldBoxes emailBox = findViewById(R.id.email_box);

        emailBox.setSimpleTextChangeWatcher(new SimpleTextChangedWatcher() {
            @Override
            public void onTextChanged(String theNewText, boolean isError) {
                user.email=theNewText;

            }
        });

        final TextFieldBoxes introBox = findViewById(R.id.introduction_box);

        introBox.setSimpleTextChangeWatcher(new SimpleTextChangedWatcher() {
            @Override
            public void onTextChanged(String theNewText, boolean isError) {
                user.introduction=theNewText;

            }
        });


        findViewById(R.id.btn_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });

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
    public void commit()
    {
        final RequestManeger requestManeger=new RequestManeger();
        requestManeger.post("http://118.25.41.237:8080/user/add", user, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e( "onFailure: ",e.toString());
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getContext(), "注册失败，用户名必须唯一", Toast.LENGTH_SHORT).show();
                    }});
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ObjectMapper objectMapper=new ObjectMapper();
                ResponseResult responseResult=objectMapper.readValue(response.body().string(),ResponseResult.class);
                if(responseResult.code==200) {
                    runOnUiThread(new Runnable() {
                        public void run() {

                            Toast.makeText(getContext(), "注册成功", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                    });
                }
                else
                {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getContext(), "注册失败，用户名必须唯一", Toast.LENGTH_SHORT).show();
                        }});
                }
            }
        });
    }
    public Context getContext()
    {
        return this;
    }
}
