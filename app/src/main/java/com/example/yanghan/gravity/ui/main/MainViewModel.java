package com.example.yanghan.gravity.ui.main;

import android.content.Intent;
import android.util.Log;
import android.view.View;


import com.example.yanghan.gravity.data.model.Page;
import com.example.yanghan.gravity.data.model.Post;
import com.example.yanghan.gravity.data.model.Result;
import com.example.yanghan.gravity.data.other.LoginManager;
import com.example.yanghan.gravity.data.other.RequestManeger;
import com.example.yanghan.gravity.ui.commonInterface.ContextService;
import com.example.yanghan.gravity.ui.commonInterface.RecyclerViewService;
import com.example.yanghan.gravity.ui.main.newPost.NewPostActivity;
import com.example.yanghan.gravity.ui.main.postDetail.PostDetailActivity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainViewModel extends ViewModel implements PostAdapter.PostAdapterListener{
    // TODO: Implement the ViewModel
    public final ArrayList<PostItemViewModel> postsArrayList = new ArrayList<>();
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
        listParam.size=10;
        getPosts(listParam);

    }


    public ArrayList<PostItemViewModel> getPostList() {
        Log.e("get","newsList");
        return postsArrayList;

    }


    public void loadMore()
    {
        Log.e("loading","post");
        ListParam listParam=new ListParam();
        listParam.page=1;
        listParam.size=10;
        getPosts(listParam);

    }

    public static class ListParam
    {
        String sortedBy="";

        Integer page;
        Integer size;
    }

    private void getPosts(ListParam listParam)
    {

        RequestManeger requestManeger=new RequestManeger();
        requestManeger.post("http://192.168.1.100:8080/post/list", listParam, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e( "onFailure: ", e.toString());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Result result=Result.mapper(response.body().string());


                try{
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList<Post> newData=mapper.convertValue(result.data.list, new TypeReference<ArrayList<Post>>() { });
                    result.data.list=newData;
                    page=result.data;
                    Log.e("onResponse: ",String.valueOf(newData.size()) );
                    for (Object post:newData)
                    {
                        PostItemViewModel a = new PostItemViewModel();
                        a.post=(Post) post;
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
