package com.example.yanghan.gravity.ui.me.favorites;

import android.util.Log;

import java.util.ArrayList;

import androidx.lifecycle.ViewModel;

public class FavoritesViewModel extends ViewModel  implements FavoritesAdapter.FavoritesAdapterListener
{

    public final ArrayList<FavoritesItemViewModel> favoritesArrayList = new ArrayList<>();
    private FavoritesAdapter mAdapter;
    public FavoritesViewModel()
    {
        super();
        initNews();
        Log.e("init","Favorites");

    }
    private void initNews()
    {
        for (int i = 1; i < 10; i++) {
            FavoritesItemViewModel news = new FavoritesItemViewModel();
            news.news.title="大学生数学建模竞赛"+Integer.toString(i);
            favoritesArrayList.add(news);
        }

        Log.e("init","");
    }
    public void setAdapter(FavoritesAdapter a)
    {
        this.mAdapter=a;
    }


    public ArrayList<FavoritesItemViewModel> getNewsList() {
        Log.e("get","newsList");
        return favoritesArrayList;

    }


    @Override
    public void onNewsClicked(FavoritesItemViewModel news) {
        Log.e("click","news");
        for (int i = 1; i < 10; i++) {
            FavoritesItemViewModel a = new FavoritesItemViewModel();
            a.news.title="contestNews"+Integer.toString(i);
            favoritesArrayList.add(a);
        }
        mAdapter.notifyDataSetChanged();
    }


}
