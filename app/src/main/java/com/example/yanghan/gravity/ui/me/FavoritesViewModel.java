package com.example.yanghan.gravity.ui.me;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.library.CircleImageView;
import com.example.yanghan.gravity.data.model.News;

import java.util.ArrayList;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
            news.Title="Title";
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
            a.Title="Title"+Integer.toString(i);
            favoritesArrayList.add(a);
        }
        mAdapter.notifyDataSetChanged();
    }
}
