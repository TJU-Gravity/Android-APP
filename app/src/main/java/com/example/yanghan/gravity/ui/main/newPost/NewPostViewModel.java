package com.example.yanghan.gravity.ui.main.newPost;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.data.model.Post;
import com.example.yanghan.gravity.data.model.ResponseResult;
import com.example.yanghan.gravity.data.model.Team;
import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.ui.commonInterface.ContextService;
import com.example.yanghan.gravity.data.other.LoginManager;
import com.example.yanghan.gravity.ui.commonInterface.MultiResponse;
import com.example.yanghan.gravity.data.other.RequestManeger;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import androidx.lifecycle.ViewModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewPostViewModel extends ViewModel {
    public Post post=new Post();
    private MultiResponse multiResponse;
    public ArrayList<String> teamList = new ArrayList<String>();
    public ArrayList<Team> teams= new ArrayList<>();
    public int teamid=0;
    ContextService contextService;

    private LoginManager loginManager=new LoginManager();

    NewPostViewModel(MultiResponse multiResponse,ContextService contextService)
    {
        this.multiResponse=multiResponse;
        this.contextService=contextService;
        getTeams();

    }
    public static class Param
    {
        public String username;
    }
    public static class Result extends ResponseResult
    {
        public ArrayList<Team> data;
    }
    public void getTeams()
    {
        LoginManager loginManager=new LoginManager();
        Param param=new Param();
        param.username=loginManager.getCurrentUser(contextService.getContext()).username;
        RequestManeger requestManeger=new RequestManeger();
        requestManeger.post("http://192.168.1.101:8080/team/myteams", param, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure: ", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ObjectMapper mapper=new ObjectMapper();
                Result result=mapper.readValue(response.body().string(),Result.class);
                teamList.add("不选择团队");
                for (Team team:result.data)
                {
                    teamList.add(team.teamname+"("+team.teamid+")");
                }

                teams=result.data;
                ((NewPostActivity)contextService.getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((NewPostActivity)contextService.getContext()).arrAdapter.notifyDataSetChanged();
                        ((NewPostActivity)contextService.getContext()).spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
                            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                       int arg2, long arg3) {
                               if(arg2>0)
                                   teamid=teams.get(arg2-1).teamid;

                            }

                            public void onNothingSelected(AdapterView<?> arg0) {
                                // TODO Auto-generated method stub

                            }
                        });

                    }
                });

            }
        });
    }

    public void commit(View v)
    {
        post.posterID=loginManager.getCurrentUser(v.getContext()).username;
        post.hits=0;
        post.lastPost=new Date();
        post.postingTime=new Date();
        post.postType=0;
        post.teamID=teamid;
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

        requestManeger.post("http://192.168.1.101:8080/post/add", json, new Callback() {
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
