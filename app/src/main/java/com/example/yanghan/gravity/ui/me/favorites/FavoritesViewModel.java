package com.example.yanghan.gravity.ui.me.favorites;

import android.util.Log;
import android.widget.Toast;

import com.example.yanghan.gravity.data.model.News;
import com.example.yanghan.gravity.data.model.Page;
import com.example.yanghan.gravity.data.model.Result;
import com.example.yanghan.gravity.data.managers.LoginManager;
import com.example.yanghan.gravity.data.managers.RequestManeger;
import com.example.yanghan.gravity.ui.commonInterface.RecyclerViewService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import androidx.lifecycle.ViewModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FavoritesViewModel extends ViewModel  implements FavoritesAdapter.FavoritesAdapterListener
{

    public final ArrayList<FavoritesItemViewModel> favoritesArrayList = new ArrayList<>();
    private FavoritesAdapter mAdapter;
    private FavoritesActivity favoritesActivity;
    private Page page;
    private RecyclerViewService recyclerViewService;

    public FavoritesViewModel(FavoritesActivity f)
    {
        super();

        Log.e("init","Favorites");
        favoritesActivity=f;

        recyclerViewService=(RecyclerViewService)f;
        initNews();


    }
    public static class ListParam
    {
        String username="";
        int page;
        int size;
    }
    private void initNews()
    {
        ListParam listParam=new ListParam();
        LoginManager loginManager=new LoginManager();
        listParam.username=loginManager.getCurrentUser(favoritesActivity).username;
        listParam.page=1;
        listParam.size=8;
        getNews(listParam);

    }

    private void getNews(ListParam listParam)
    {
        RequestManeger requestManeger=new RequestManeger();
        requestManeger.post("http://118.25.41.237:8080/usernews/favorites", listParam, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e( "onFailure: ", e.toString());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Result result=Result.mapper(response.body().string());
                Log.e("onResponse: ",String.valueOf(result.data.list.size()) );

                try{
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList<News> newData=mapper.convertValue(result.data.list, new TypeReference<ArrayList<News>>() { });
                    result.data.list=newData;
                    page=result.data;

                    for (Object news:newData)
                    {
                        FavoritesItemViewModel a = new FavoritesItemViewModel();
                        a.news=(News) news;
                        favoritesArrayList.add(a);

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
    public void setAdapter(FavoritesAdapter a)
    {
        this.mAdapter=a;
    }


    public ArrayList<FavoritesItemViewModel> getNewsList() {
        Log.e("get","newsList");
        return favoritesArrayList;

    }


    public void loadMore()
    {
        if(page.hasNextPage)
        {
            ListParam listParam = new ListParam();
            LoginManager loginManager = new LoginManager();
            listParam.username = loginManager.getCurrentUser(favoritesActivity).username;
            listParam.page = page.nextPage;
            listParam.size = page.size;
            getNews(listParam);
        }
        else
        {
            Toast.makeText(favoritesActivity, "到底了", Toast.LENGTH_SHORT).show();
            recyclerViewService.stopLoading();
        }

    }
    @Override
    public void onNewsClicked(FavoritesItemViewModel news) {
        Log.e("click","news");

    }


}
