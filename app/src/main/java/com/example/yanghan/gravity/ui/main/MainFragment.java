package com.example.yanghan.gravity.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanghan.gravity.databinding.FragmentMainBinding;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.ui.me.favorites.FavoritesAdapter;
import com.example.yanghan.gravity.ui.me.favorites.FavoritesItemViewModel;
import com.example.yanghan.gravity.ui.me.favorites.FavoritesViewModel;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;


public class MainFragment extends Fragment implements PostAdapter.PostAdapterListener {

    private MainViewModel mViewModel;

    private PullLoadMoreRecyclerView mRecyclerView;
    private PostAdapter mAdapter;
    private FragmentMainBinding binding;



    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_main,container, false);

        binding.setViewModel(mViewModel);






        return binding.getRoot();

    }


    private void initRecyclerView() {
        mRecyclerView = binding.recyclerView;


        mAdapter=new PostAdapter(mViewModel.getPostList(),mViewModel);

        mRecyclerView.setAdapter(mAdapter);



        mRecyclerView.setLinearLayout();

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                Log.e("Refresh","!");

            }

            @Override
            public void onLoadMore() {
                Log.e("LoadMore","!");
                mViewModel.loadMore();
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mViewModel = new MainViewModel(this);
        mViewModel.initPost();
        initRecyclerView();
        mViewModel.setAdapter(mAdapter);


        // TODO: Use the ViewModel
    }


    @Override
    public void  onPostClicked(PostItemViewModel post){

    }
}
