package com.gravity.worlf.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.gravity.worlf.myapplication.Home.FirstPageFragment;
import com.gravity.worlf.myapplication.Me.MeFragment;
import com.gravity.worlf.myapplication.News.NewsFragment;
import com.gravity.worlf.myapplication.Team.TeamFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.personal_page:
                    transaction.replace(R.id.content,new MeFragment());
                    transaction.commit();
                    return true;
                case R.id.team_page:
                    transaction.replace(R.id.content,new TeamFragment());
                    transaction.commit();
                    return true;
                case R.id.match_page:
                    transaction.replace(R.id.content,new NewsFragment());
                    transaction.commit();
                    return true;
                case R.id.first_page:
                    transaction.replace(R.id.content,new FirstPageFragment());
                    transaction.commit();
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefaultFragment();
        mTextMessage = ( TextView ) findViewById(R.id.message);
        BottomNavigationView navigation = ( BottomNavigationView ) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;

    //设置默认进来是tab 显示的页面
    private void setDefaultFragment(){
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content,new FirstPageFragment());
        transaction.commit();
    }


}
