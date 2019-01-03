package com.example.yanghan.gravity.ui.main.postDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.databinding.ActivityPostDetailBinding;
import com.example.yanghan.gravity.ui.commonInterface.ContextService;
import com.example.yanghan.gravity.ui.commonInterface.MultiResponse;
import com.example.yanghan.gravity.ui.commonInterface.RecyclerViewService;
import com.example.yanghan.gravity.ui.main.reply.ReplyAdapter;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class PostDetailActivity extends AppCompatActivity implements ContextService ,MultiResponse,RecyclerViewService {

    private PostDetailViewModel mViewModel;
    public ActivityPostDetailBinding binding;
    private Drawer result = null;
    private PullLoadMoreRecyclerView mRecyclerView;
    public ReplyAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);


        mViewModel = new PostDetailViewModel(this);

        binding=DataBindingUtil.setContentView(this,R.layout.activity_post_detail);

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

    public ViewModel getViewModel()
    {
        return  mViewModel;
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
    private void initRecyclerView() {
        mRecyclerView = binding.recyclerView;


        mAdapter=new ReplyAdapter(mViewModel.getReplyList(),mViewModel);

        mRecyclerView.setAdapter(mAdapter);



        mRecyclerView.setLinearLayout();

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

                mViewModel.refresh();
            }

            @Override
            public void onLoadMore() {
                Log.e("LoadMore","!");
                //mViewModel.loadMore();
                mRecyclerView.setPullLoadMoreCompleted();
            }
        });

    }



    @Override
    public Context getContext() {
        return this;

    }

    @Override
    public void failed() {

    }

    @Override
    public void succeed() {
        runOnUiThread(new Runnable() {
            public void run() {
                binding.invalidateAll();
                mAdapter.notifyDataSetChanged();
            }});

    }

    @Override
    public void error() {

    }

    @Override
    public void stopLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        });
    }


    @Override
    public void notifyDataChanged() {

    }
}
