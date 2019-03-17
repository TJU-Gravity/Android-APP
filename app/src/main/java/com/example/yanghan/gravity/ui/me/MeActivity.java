package com.example.yanghan.gravity.ui.me;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.data.managers.LoginManager;
import com.example.yanghan.gravity.databinding.ActivityMeBinding;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

public class MeActivity extends AppCompatActivity {
    private Drawer result = null;
    private MeViewModel mViewModel;
    ActivityMeBinding binding;
    private  LoginManager loginManager=new LoginManager();
    boolean tryToLogin=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        mViewModel = new MeViewModel();
        binding=DataBindingUtil.setContentView(this,R.layout.activity_me);
        binding.setViewModel(mViewModel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        result = new DrawerBuilder()
             .withActivity(this)
             .withSavedInstance(savedInstanceState)
             .withFullscreen(true)
             .build();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        }

    @Override
    protected void onStart() {
        super.onStart();
      getUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.me_page_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.edit:
                mViewModel.onClickEditBtn(this);
                return true;
            case R.id.logout:
                tryToLogin=false;
                mViewModel.onClickLogout(this);
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


    private void getUser()
    {

        if(!loginManager.isLogin(this)) {
            if (tryToLogin)
            {
                loginManager.loginPage(this);
                tryToLogin=false;
            }
            else
            {
                tryToLogin=true;
                onBackPressed();
            }
        }
        else
        {
            mViewModel.initUser(this);
            binding.invalidateAll();
        }


    }
    public MeViewModel getViewModel() {
        return mViewModel;
    }
}