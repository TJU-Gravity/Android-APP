package com.example.yanghan.gravity.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;


import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.example.yanghan.gravity.BuildConfig;
import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.data.managers.LoginManager;
import com.example.yanghan.gravity.data.managers.SearchManager;
import com.example.yanghan.gravity.ui.commonInterface.ContextService;
import com.example.yanghan.gravity.ui.me.MeActivity;
import com.example.yanghan.gravity.ui.news.NewsFragment;
import com.example.yanghan.gravity.ui.main.MainFragment;

import com.example.yanghan.gravity.ui.setting.SettingFragment;
import com.example.yanghan.gravity.ui.team.TeamFragment;



import com.facebook.react.ReactInstanceManager;


import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity implements ContextService , DefaultHardwareBackBtnHandler {
    private  Drawer result=null;
    private AccountHeader headerResult=null;
    private SearchView searchView;

    private LoginManager loginManager=new LoginManager();
    private User user=null;

    private final int OVERLAY_PERMISSION_REQ_CODE = 1;  // Choose any value

    private ReactInstanceManager mReactInstanceManager;
    public ReactInstanceManager getReactInstanceManager()
    {
        return mReactInstanceManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(getContext())) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }
        }
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setCurrentActivity(this)
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("app")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
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
                            }else  if (drawerItem.getIdentifier() == 3) {
                                //如果登陆，跳转团队界面
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

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                }
            }
        }
        mReactInstanceManager.onActivityResult(this, requestCode, resultCode, data);
    }
}
