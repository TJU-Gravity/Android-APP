package com.example.yanghan.gravity.ui.main.reply;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.databinding.ItemReplyViewBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.MyViewHolder > {
    private ArrayList<ReplyItemViewModel> replyList=new ArrayList<>();
    private LayoutInflater layoutInflater;
    private ReplyAdapterListener listener;

    public ReplyAdapter(ArrayList<ReplyItemViewModel> replyList, ReplyAdapterListener listener) {
        this.replyList = replyList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemReplyViewBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_reply_view, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.binding.setViewModel(replyList.get(position));


    }

    @Override
    public int getItemCount() {
        return replyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemReplyViewBinding binding;

        public MyViewHolder(final ItemReplyViewBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;

        }
    }

    public interface ReplyAdapterListener {
        void onReplyClicked(ReplyItemViewModel Reply);
    }


}
