package com.example.yanghan.gravity.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.databinding.ItemPostViewBinding;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder > {
    private List<PostItemViewModel> postList;
    private LayoutInflater layoutInflater;
    private PostAdapterListener listener;

    public PostAdapter(List<PostItemViewModel> postList, PostAdapterListener listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemPostViewBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_post_view, parent, false);


        MyViewHolder myViewHolder=new MyViewHolder(binding);
        binding.invalidateAll();
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.binding.setViewModel(postList.get(position));
        holder.binding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onPostClicked(postList.get(position));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public final ItemPostViewBinding binding;

        public MyViewHolder(final ItemPostViewBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;

        }

    }

    public interface PostAdapterListener {
        void onPostClicked(PostItemViewModel post);
    }




}
