package com.example.yanghan.gravity.ui.login;

import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import studio.carbonylgroup.textfieldboxes.SimpleTextChangedWatcher;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import android.os.Bundle;


import android.util.Log;
import android.widget.Toast;


import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.databinding.ActivityLoginBinding;

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

    }

    public void invaliad()
    {

    }
    public void error()
    {
        Toast.makeText(this,"网络错误",Toast.LENGTH_SHORT).show();
    }


}
