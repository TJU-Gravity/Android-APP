package com.example.yanghan.gravity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.data.other.LoginManager;
import com.example.yanghan.gravity.data.other.SearchManager;
import com.example.yanghan.gravity.ui.commonInterface.ContextService;
import com.example.yanghan.gravity.ui.me.MeActivity;
import com.example.yanghan.gravity.ui.news.NewsFragment;
import com.example.yanghan.gravity.ui.main.MainFragment;

import com.example.yanghan.gravity.ui.setting.SettingFragment;
import com.example.yanghan.gravity.ui.team.TeamFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity implements ContextService {
    private  Drawer result=null;
    private AccountHeader headerResult=null;
    private SearchView searchView;
    private LoginManager loginManager=new LoginManager();
    private User user=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        initUser();

        //------------------------------------------------
        //侧边栏——简约
        IProfile profile;
        if(user!=null)
        {
            profile = new ProfileDrawerItem().withName(user.nickname).withEmail(user.email);
        }
        else
        {
            profile = new ProfileDrawerItem().withName("未登录");
        }

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(getResources().getDrawable(R.drawable.header_test))
                .withCompactStyle(false)
                .addProfiles(
                        profile)
                .withTextColor(ContextCompat.getColor(this,R.color.md_black_1000))
                .build();

        PrimaryDrawerItem home = new PrimaryDrawerItem().withName("HOME").withIcon(R.drawable.gravity_inact).withIdentifier(1).withSelectable(false);
        PrimaryDrawerItem news = new PrimaryDrawerItem().withName("NEWS").withIcon(R.drawable.news_inact).withIdentifier(2).withSelectable(false);
        PrimaryDrawerItem team = new PrimaryDrawerItem().withName("TEAM").withIcon(R.drawable.team_fixedxxxhdpi).withIdentifier(3).withSelectable(false);
        PrimaryDrawerItem me = new PrimaryDrawerItem().withName("ME").withIcon(R.drawable.me_inact).withIdentifier(4).withSelectable(false);
        PrimaryDrawerItem setting=new PrimaryDrawerItem().withName("SETTING").withIdentifier(10).withSelectable(false);

        DrawerBuilder drawerBuilder = new DrawerBuilder();
        drawerBuilder.withActivity(this);
        drawerBuilder.withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true);
        drawerBuilder.addDrawerItems(
                home,
                new DividerDrawerItem(),
                news,
                team,
                me,
                new DividerDrawerItem(),
                setting)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Log.e("click","true!!!!");

                        if (drawerItem != null) {

                            if (drawerItem.getIdentifier() == 1) {
                                changeFragment(new MainFragment());
                            } else if (drawerItem.getIdentifier() == 2) {
                                changeFragment(new NewsFragment());
                            }else if (drawerItem.getIdentifier() == 3) {
                                changeFragment(new TeamFragment());
                            }else if (drawerItem.getIdentifier() == 4) {

                                Intent intent = new Intent(MainActivity.this, MeActivity.class);
                                startActivity(intent);

                            }else if (drawerItem.getIdentifier() == 10) {
                                changeFragment(new SettingFragment());
                            }


                        }

                        return false;
                    }
                });


        result = drawerBuilder.build();


        result.addStickyFooterItem(new PrimaryDrawerItem().withName("Gravity"));

        //------------------------------------------------

        if (savedInstanceState == null) {
            changeFragment(new MainFragment());
        }

    }
    void initUser()
    {
        if(loginManager.isLogin(this))
        {
            user=loginManager.getCurrentUser(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initUser();
        IProfile profile;
        if(user!=null)
        {
            profile = new ProfileDrawerItem().withName(user.nickname).withEmail(user.email);
        }
        else
        {
            profile = new ProfileDrawerItem().withName("未登录");
        }
        headerResult.clear();
        headerResult.addProfiles(profile);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
//        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
  //      outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    private void changeFragment(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast like print
                Log.e( "onQueryTextSubmit: ","!" );
                SearchManager searchManager=new SearchManager();
                searchManager.searchUser(query,(ContextService) getContext());
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);

                return false;
            }
        });
        return true;

    }


    @Override
    public Context getContext() {
        return this;
    }
}
