package com.example.yanghan.gravity.ui.me;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.data.model.News;
import com.example.yanghan.gravity.databinding.FavoritesActivityBinding;
import com.example.yanghan.gravity.databinding.ItemFavoritesViewBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyViewHolder > {
    private List<FavoritesItemViewModel> newsList;
    private LayoutInflater layoutInflater;
    private FavoritesAdapterListener listener;

    public FavoritesAdapter(List<FavoritesItemViewModel> newsList, FavoritesAdapterListener listener) {
        this.newsList = newsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemFavoritesViewBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_favorites_view, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.binding.setViewModel(newsList.get(position));
        holder.binding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onNewsClicked(newsList.get(position));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemFavoritesViewBinding binding;

        public MyViewHolder(final ItemFavoritesViewBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;

        }
    }

    public interface FavoritesAdapterListener {
        void onNewsClicked(FavoritesItemViewModel news);
    }

}
