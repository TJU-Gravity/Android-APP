package com.example.yanghan.gravity.ui.news;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.example.yanghan.gravity.R;

public class NewsDetailActivity extends AppCompatActivity {

    //public static final String EXTRA_NAME = "contest_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Intent intent = getIntent();

    }

}