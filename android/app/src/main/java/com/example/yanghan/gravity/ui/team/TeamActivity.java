package com.example.yanghan.gravity.ui.team;

import android.content.Intent;
import android.os.Bundle;

import com.example.yanghan.gravity.ui.baseClass.BaseReactActivity;

public class TeamActivity extends BaseReactActivity {
    @Override
    protected String getMainModulePath() {
        return "index";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mReactRootView.startReactApplication(mReactInstanceManager, "TeamList");
        setContentView(mReactRootView);
    }
}
