package com.example.yanghan.gravity.ui.main.newPost;

import android.util.Log;
import android.view.View;

import com.example.yanghan.gravity.data.model.Post;
import com.example.yanghan.gravity.ui.commonInterface.ContextService;
import com.example.yanghan.gravity.data.other.LoginManager;
import com.example.yanghan.gravity.ui.commonInterface.MultiResponse;
import com.example.yanghan.gravity.data.other.RequestManeger;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;

import androidx.lifecycle.ViewModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewPostViewModel extends ViewModel {
    public Post post=new Post();
    private MultiResponse multiResponse;


    private LoginManager loginManager=new LoginManager();

    NewPostViewModel(MultiResponse multiResponse,ContextService contextService)
    {
        this.multiResponse=multiResponse;

    }

    public void commit(View v)
    {
        post.posterID=loginManager.getCurrentUser(v.getContext()).username;
        post.hits=0;
        post.lastPost=new Date();
        post.postingTime=new Date();
        post.postType=0;
        RequestManeger requestManeger=new RequestManeger();
        ObjectMapper mapper = new ObjectMapper();
        String json="";
        try
        {
            json=mapper.writeValueAsString(post);
            Log.e("json",json);
        }
        catch (Exception e)
        {
            Log.e("json",e.toString());
        }

        requestManeger.post("http://192.168.1.100:8080/post/add", json, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e( "onFailure: ",e.toString() );

                        multiResponse.failed();
            }



            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("onResponse: ", response.toString());

                        multiResponse.succeed();
            }

        });

    }


}
