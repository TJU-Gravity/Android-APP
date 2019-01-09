package com.example.yanghan.gravity.ui.login;

import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import studio.carbonylgroup.textfieldboxes.SimpleTextChangedWatcher;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
import android.widget.Toast;


import com.example.yanghan.gravity.MainActivity;

import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.data.other.LoginManager;
import com.example.yanghan.gravity.databinding.ActivityLoginBinding;
import com.example.yanghan.gravity.databinding.ActivityMeBinding;
import com.example.yanghan.gravity.ui.me.MeViewModel;
import com.example.yanghan.gravity.ui.team.TeamActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel mViewModel;
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mViewModel =new LoginViewModel(this);

        binding=DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setViewModel(mViewModel);

        final TextFieldBoxes username = findViewById(R.id.username_box);
        final TextFieldBoxes password = findViewById(R.id.password_box);
        username.setSimpleTextChangeWatcher(new SimpleTextChangedWatcher() {
            @Override
            public void onTextChanged(String theNewText, boolean isError) {
                mViewModel.user.username=theNewText;
                mViewModel.isValiad=!isError;
            }
        });

        password.setSimpleTextChangeWatcher(new SimpleTextChangedWatcher() {
            @Override
            public void onTextChanged(String theNewText, boolean isError) {

                mViewModel.user.pwd=theNewText;
                mViewModel.isValiad=!isError;
            }
        });

    }

    public void loginFailed()
    {
        Log.e("loginFaild","!");
        AlertDialog alertDialog1 = new AlertDialog.Builder(this)
                .setTitle("登录失败")//标题
                .setMessage("用户名或密码错误")//内容
                .setIcon(R.mipmap.ic_launcher)//图标
                .create();
        alertDialog1.show();


    }
    public void loginSucceed()
    {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        this.onBackPressed();
        Intent intent = new Intent(LoginActivity.this, TeamActivity.class);
        startActivity(intent);
    }

    public void invaliad()
    {

    }
    public void error()
    {
        Toast.makeText(this,"网络错误",Toast.LENGTH_SHORT).show();
    }


}
