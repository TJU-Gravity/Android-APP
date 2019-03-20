package com.example.yanghan.gravity.ui.team;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.yanghan.gravity.ui.baseClass.BaseReactActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TeamDetailActivity extends BaseReactActivity {
    @Override
    protected String getMainModulePath() {
        return "index";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle=new Bundle();
        bundle.putString("user",intent.getStringExtra("user"));


        mReactRootView.startReactApplication(mReactInstanceManager, "TeamDetail", bundle);
        setContentView(mReactRootView);
    }
}
