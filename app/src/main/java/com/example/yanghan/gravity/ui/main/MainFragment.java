package com.example.yanghan.gravity.ui.main;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.example.yanghan.gravity.MainActivity;
import com.example.yanghan.gravity.databinding.FragmentMainBinding;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.ui.commonInterface.ContextService;
import com.example.yanghan.gravity.ui.commonInterface.RecyclerViewService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;


public class MainFragment extends Fragment implements PostAdapter.PostAdapterListener,ContextService,RecyclerViewService
{

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

        FloatingActionButton btn=binding.getRoot().findViewById(R.id.floatingActionButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e( "onClick: ", "????");
                mViewModel.onAddBtnClicked(v);
            }
        });

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




        // TODO: Use the ViewModel
    }


    @Override
    public void  onPostClicked(PostItemViewModel post){

    }

    @Override
    public void notifyDataChanged() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void stopLoading() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setPullLoadMoreCompleted();
            }
        });

    }
}
