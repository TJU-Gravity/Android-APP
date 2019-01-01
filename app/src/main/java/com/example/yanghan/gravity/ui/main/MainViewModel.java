package com.example.yanghan.gravity.ui.main;

import android.content.Intent;
import android.util.Log;


import com.example.yanghan.gravity.MainActivity;
import com.example.yanghan.gravity.ui.main.postDetail.PostDetailActivity;
import com.example.yanghan.gravity.ui.me.MeActivity;

import java.util.ArrayList;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel implements PostAdapter.PostAdapterListener{
    // TODO: Implement the ViewModel
    public final ArrayList<PostItemViewModel> postsArrayList = new ArrayList<>();
    private PostAdapter mAdapter;
    private MainFragment mainFragment;


    public MainViewModel(MainFragment f)
    {
        super();

        Log.e("init","Post");
        mainFragment=f;

    }

    public void initPost()
    {
        for (int i = 1; i < 10; i++) {
            PostItemViewModel post = new PostItemViewModel();
            post.post.title="大学生数学建模竞赛招募公告"+Integer.toString(i);
            post.post.description="我们打算参加。。。现有成员。。。需要成员。。。。欢迎加入我们的团队。。。";
            post.post.posterUsername="Rebecca";
            postsArrayList.add(post);
            Log.e("init",String.valueOf(i));
        }

    }

    public void setAdapter(PostAdapter a)
    {
        this.mAdapter=a;
    }


    public ArrayList<PostItemViewModel> getPostList() {
        Log.e("get","newsList");
        return postsArrayList;

    }


    public void loadMore()
    {
        Log.e("loading","news");

        for (int i = 1; i < 10; i++) {
            PostItemViewModel a = new PostItemViewModel();
            a.post.title="contestNews"+Integer.toString(i);
            postsArrayList.add(a);
        }
        mAdapter.notifyDataSetChanged();
        //mainFragment.setPullLoadMoreCompleted();
    }

    @Override
    public void onPostClicked(PostItemViewModel post)
    {

        Intent intent = new Intent(mainFragment.getActivity(),PostDetailActivity.class);
        mainFragment.getActivity().startActivity(intent);

    }
}
