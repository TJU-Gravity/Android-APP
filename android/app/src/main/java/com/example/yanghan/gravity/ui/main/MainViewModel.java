package com.example.yanghan.gravity.ui.main;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.yanghan.gravity.data.model.Page;
import com.example.yanghan.gravity.data.model.PostResult;
import com.example.yanghan.gravity.data.model.Result;
import com.example.yanghan.gravity.data.managers.LoginManager;
import com.example.yanghan.gravity.data.managers.RequestManeger;
import com.example.yanghan.gravity.ui.commonInterface.ContextService;
import com.example.yanghan.gravity.ui.commonInterface.RecyclerViewService;
import com.example.yanghan.gravity.ui.main.newPost.NewPostActivity;
import com.example.yanghan.gravity.ui.main.postDetail.PostDetailActivity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import androidx.lifecycle.ViewModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainViewModel extends ViewModel implements PostAdapter.PostAdapterListener{
    // TODO: Implement the ViewModel
    public  ArrayList<PostItemViewModel> postsArrayList = new ArrayList<>();
    private Page page;
    private LoginManager loginManager=new LoginManager();

    private ContextService contextService;
    private RecyclerViewService recyclerViewService;




    public MainViewModel(MainFragment f)
    {
        super();

        Log.e("init","Post");

        contextService=f;
        recyclerViewService=f;

    }

    public void initPost()
    {
        ListParam listParam=new ListParam();
        listParam.page=1;
        listParam.size=8;
        getPosts(listParam);

    }


    public ArrayList<PostItemViewModel> getPostList() {
        Log.e("get","postList");
        return postsArrayList;

    }
    public void refresh()
    {
        postsArrayList = new ArrayList<>();
        initPost();

    }


    public void loadMore()
    {
        if(page.hasNextPage) {
            Log.e("loading", "post");
            ListParam listParam = new ListParam();
            listParam.page = page.nextPage;
            listParam.size = page.size;
            getPosts(listParam);
        }
        else
        {
            Toast.makeText(contextService.getContext(), "到底了", Toast.LENGTH_SHORT).show();
            recyclerViewService.stopLoading();
        }

    }

    public static class ListParam
    {
        public String sortedBy="";
        public Integer page;
        public Integer size;
    }

    private void getPosts(ListParam listParam)
    {

        RequestManeger requestManeger=new RequestManeger();
        requestManeger.post("/post/list", listParam, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e( "onFailure: ", e.toString());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Result result=Result.mapper(response.body().string());


                try{
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList<PostResult> newData=mapper.convertValue(result.data.list, new TypeReference<ArrayList<PostResult>>() { });
                    result.data.list=newData;
                    page=result.data;

                    for (PostResult post:newData)
                    {
                       post.genDescription();
                        PostItemViewModel a = new PostItemViewModel();
                        a.post=(PostResult) post;
                        postsArrayList.add(a);

                    }

                }
                catch (Exception e)
                {
                    Log.e("onResponse: ", e.toString());
                }

                recyclerViewService.notifyDataChanged();
                recyclerViewService.stopLoading();

            }
        });

    }


    @Override
    public void onPostClicked(PostItemViewModel post)
    {

        Intent intent = new Intent(contextService.getContext(),PostDetailActivity.class);

        intent.putExtra("ID",post.post.postId);
        contextService.getContext().startActivity(intent);

    }
    public void onAddBtnClicked(View v)
    {
        Log.e("onBtnClicked: ",v.toString() );
        if(loginManager.isLogin(contextService.getContext()))
        {
            Intent intent = new Intent(contextService.getContext(),NewPostActivity.class);
           contextService.getContext().startActivity(intent);
        }
        else
        {
            loginManager.loginPage(contextService.getContext());
        }

    }


}
