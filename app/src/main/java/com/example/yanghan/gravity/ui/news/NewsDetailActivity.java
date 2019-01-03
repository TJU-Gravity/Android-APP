package com.example.yanghan.gravity.ui.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.data.model.News;
import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.data.other.LoginManager;
import com.example.yanghan.gravity.data.other.NewsDetailManager;
import com.example.yanghan.gravity.generated.callback.OnClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;


public class NewsDetailActivity extends AppCompatActivity {

    private Drawer result = null;
    boolean isLike=false;
    private NewsDetailManager newsDetailManager=new NewsDetailManager();
    private LoginManager loginManager=new LoginManager();

    FloatingActionButton floatingActionButton1;
    FloatingActionButton floatingActionButton2;
    ImageView imageView;
    TextView textViewtitle;
    TextView textViewsponsor;
    TextView textViewhits;

    News news=new News();
    User user=new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        //获取上个页面传参newsid
        news.newsid=getIntent().getIntExtra("newsid",1);
        news.title=getIntent().getStringExtra("title");
        news.newsbody=getIntent().getStringExtra("newsbody");
        news.editorid=getIntent().getStringExtra("editorid");
        news.sponsor=getIntent().getStringExtra("sponsor");
        news.poster=getIntent().getStringExtra("poster");
        news.hits=getIntent().getIntExtra("hits",0);

        Log.e("newsid",news.title);

        newsDetailManager.newsdetail(getApplicationContext(),news,this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_newsdetail);
        setSupportActionBar(toolbar);

         textViewtitle=(TextView)findViewById(R.id.news_title);
         textViewsponsor=(TextView)findViewById(R.id.contest_sponsor);
         textViewhits=(TextView)findViewById(R.id.contest_hits);

         textViewtitle.setText(news.title);
        textViewsponsor.setText(news.sponsor);
        textViewhits.setText(String.valueOf(news.hits));

        imageView=(ImageView)findViewById(R.id.news_image) ;
        Glide.with(imageView.getContext())
                .load(news.poster)
                .apply(new RequestOptions().error(new ColorDrawable(Color.GRAY)))
                .into(imageView);

        WebView webView=(WebView)findViewById(R.id.webview_newsdetail);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient()); //确保跳转到另一个网页时仍然在当前WebView显示

        // 导入本地html
        //webView.loadUrl("file:///android_asset/www/contest.html");

           //Log.e("html",news.newsbody);
           //加载HTML字符串进行显示
           webView.loadDataWithBaseURL(null,news.newsbody,"text/html", "utf-8",null);
           webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


        FloatingActionButton floatingActionButton1=(FloatingActionButton)findViewById(R.id.like_btn);
        FloatingActionButton floatingActionButton2=(FloatingActionButton)findViewById(R.id.unlike_btn);

            floatingActionButton1.show();
            floatingActionButton2.hide();

//关注
        floatingActionButton1.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            FloatingActionButton floatingActionButton1=(FloatingActionButton)findViewById(R.id.like_btn);
            FloatingActionButton floatingActionButton2=(FloatingActionButton)findViewById(R.id.unlike_btn);
            floatingActionButton1.hide();
            floatingActionButton2.show();

            newsDetailManager.like(getApplicationContext(),user,news);
        }
        });
//取消关注
        floatingActionButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FloatingActionButton floatingActionButton1=(FloatingActionButton)findViewById(R.id.like_btn);
                FloatingActionButton floatingActionButton2=(FloatingActionButton)findViewById(R.id.unlike_btn);
                floatingActionButton2.hide();
                floatingActionButton1.show();

                newsDetailManager.unlike(getApplicationContext(),user,news);
            }
        });

        result = new DrawerBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .withFullscreen(true)
                .build();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.news_detail_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.share:
                //nViewModel.onClickShare(this);
                return true;
            case R.id.like_btn:
              //  nViewModel.onClickLike(this);
                return true;
            case R.id.unlike_btn:
              //  nViewModel.onClickUnLike(this);
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
    protected void onStart() {
        super.onStart();
        if(!loginManager.isLogin(this)){
            loginManager.loginPage(this);
        }else{
            user=loginManager.getCurrentUser(this);
        }

    }

    public void initnews(News news){
        this.news=news;
        Log.e("newsdetail",news.title);
        Log.e("newsdetail",news.sponsor);
        Log.e("newsdetail",String.valueOf(news.hits));
        //textViewtitle.setText( String.valueOf(news.title));
        //textViewsponsor.setText(news.sponsor);
        //textViewhits.setText(String.valueOf(news.hits));
      //  NewsViewModel.loadImage(imageView ,news.poster);
    }

}