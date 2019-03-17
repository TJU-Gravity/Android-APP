package com.example.yanghan.gravity.data.managers;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.yanghan.gravity.ui.MainActivity;
import com.example.yanghan.gravity.data.model.ResponseResult;
import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.ui.commonInterface.ContextService;
import com.example.yanghan.gravity.ui.visitingCard.VisitingCardActivity;
import com.example.yanghan.gravity.ui.visitingCard.VisitingCardViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import androidx.lifecycle.ViewModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchManager {

    public static class Result extends ResponseResult
    {
        public User data;
    }
    public static class RequestBody
    {
        public String username;
    }
    public void getUserInfo(final String username, final ContextService contextService, final ViewModel viewModel)
    {
        RequestManeger requestManeger=new RequestManeger();
        RequestBody requestBody=new RequestBody();
        requestBody.username=username;
        requestManeger.post("/user/detail", requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure: ",e.toString() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try
                {
                    ObjectMapper mapper=new ObjectMapper();
                    Result result=mapper.readValue(response.body().string(),Result.class);
                    if(result.data!=null)
                    {
                        Log.e("onResponse: ", "found User");
                        ((VisitingCardViewModel)viewModel).user=result.data;
                        ((Activity)contextService.getContext()).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((VisitingCardActivity)contextService.getContext()).binding.invalidateAll();

                            }
                        });

                    }
                    else
                    {
                        Log.e("onResponse: ", "cannot find");
                        ((Activity)contextService.getContext()).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(contextService.getContext(),"未查找到用户",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }catch (Exception e)
                {
                    Log.e("onResponse: ", e.toString());
                }
            }
        });
    }
        public void searchUser(final String username, final ContextService contextService)
    {

        RequestManeger requestManeger=new RequestManeger();
        RequestBody requestBody=new RequestBody();
        requestBody.username=username;
        requestManeger.post("/user/detail", requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure: ",e.toString() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try
                {
                    ObjectMapper mapper=new ObjectMapper();
                    Result result=mapper.readValue(response.body().string(),Result.class);
                    if(result.data!=null)
                    {
                        Log.e("onResponse: ", "found User");
                        ((MainActivity)contextService.getContext()).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(contextService.getContext(),VisitingCardActivity.class);
                                intent.putExtra("username",username);
                                ((MainActivity)contextService.getContext()).startActivity(intent);

                            }
                        });

                    }
                    else
                    {
                        Log.e("onResponse: ", "cannot find");
                        ((MainActivity)contextService.getContext()).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(contextService.getContext(),"未查找到用户",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }catch (Exception e)
                {
                    Log.e("onResponse: ", e.toString());
                }
            }
        });
    }
}
