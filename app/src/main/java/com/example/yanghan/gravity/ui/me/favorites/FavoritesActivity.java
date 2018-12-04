package com.example.yanghan.gravity.ui.me.favorites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.databinding.ActivityFavoritesBinding;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

public class FavoritesActivity extends AppCompatActivity
{
    private Drawer result = null;
    private FavoritesViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private FavoritesAdapter mAdapter;
    ActivityFavoritesBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        mViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);

        binding=DataBindingUtil.setContentView(this,R.layout.activity_favorites);

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

        initRecyclerView();


    }

    private void initRecyclerView() {

        mRecyclerView = binding.recyclerView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter = new FavoritesAdapter(mViewModel.getNewsList(),mViewModel);
        mRecyclerView.setAdapter(mAdapter);
        mViewModel.setAdapter(mAdapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
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



}
