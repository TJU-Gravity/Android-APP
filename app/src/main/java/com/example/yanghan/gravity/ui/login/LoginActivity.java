package com.example.yanghan.gravity.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import studio.carbonylgroup.textfieldboxes.SimpleTextChangedWatcher;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import android.os.Bundle;

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

        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
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
                mViewModel.user.password=theNewText;
                mViewModel.isValiad=!isError;
            }
        });

    }




}
