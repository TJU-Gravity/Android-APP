package com.example.yanghan.gravity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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

public class MainActivity extends AppCompatActivity {
    private  Drawer result=null;
    private AccountHeader headerResult=null;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        //------------------------------------------------
        //侧边栏——简约

        IProfile profile = new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(getResources().getDrawable(R.drawable.headshot));
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
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
                //UserFeedback.show( "SearchOnQueryTextSubmit: " + query);
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
<<<<<<< HEAD

=======
>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c
                return false;
            }
        });
        return true;

    }
}
