package com.example.yanghan.gravity.ui.login;

<<<<<<< HEAD
import androidx.appcompat.app.AlertDialog;
=======
>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import studio.carbonylgroup.textfieldboxes.SimpleTextChangedWatcher;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;
import android.widget.Toast;
=======
>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c

import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.databinding.ActivityLoginBinding;
import com.example.yanghan.gravity.databinding.ActivityMeBinding;
import com.example.yanghan.gravity.ui.me.MeViewModel;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel mViewModel;
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

<<<<<<< HEAD
        mViewModel =new LoginViewModel(this);
=======
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c
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
<<<<<<< HEAD
                mViewModel.user.pwd=theNewText;
=======
                mViewModel.user.password=theNewText;
>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c
                mViewModel.isValiad=!isError;
            }
        });

    }
<<<<<<< HEAD
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
=======



>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c

}
