package com.example.yanghan.gravity.ui.main.postDetail;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yanghan.gravity.data.model.Post;
import com.example.yanghan.gravity.ui.main.PostItemViewModel;
import com.example.yanghan.gravity.ui.main.reply.ReplyAdapter;
import com.example.yanghan.gravity.ui.main.reply.ReplyItemViewModel;
import com.example.yanghan.gravity.ui.me.favorites.FavoritesItemViewModel;

import java.util.ArrayList;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

public class PostDetailViewModel extends ViewModel implements ReplyAdapter.ReplyAdapterListener{
    public Post post=new Post();

    public ArrayList<ReplyItemViewModel> replyArrayList=new ArrayList<>();
    PostDetailViewModel()
    {
        initPost();
        initReply();
    }

    public void initPost()
    {
        post.title="大学生数学建模竞赛招募公告";
        post.postBody="我们打算参加。。。现有成员。。。需要成员。。。。欢迎加入我们的团队。。。";
        post.posterUsername="Rebecca";

    }
    public void initReply()
    {
        for (int i = 1; i < 10; i++) {
            ReplyItemViewModel post = new ReplyItemViewModel();
            post.post.title="大学生数学建模竞赛招募公告"+Integer.toString(i);
            post.post.description="我们打算参加。。。现有成员。。。需要成员。。。。欢迎加入我们的团队。。。";
            post.post.posterUsername="Rebecca";
            replyArrayList.add(post);
            Log.e("init",String.valueOf(i));
        }

    }
    public ArrayList<ReplyItemViewModel> getReplyList() {
        Log.e("get","newsList");
        return replyArrayList;

    }

    public String getImageUrl() {
        // The URL will usually come from a model (i.e Profile)
        return "https://publicqn.saikr.com/2018/09/27/contest5bac5de7664065.396650141538022894236.jpg?imageView2/2/w/1080";
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Log.e("url",imageUrl);

        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(new RequestOptions().override(25,25).error(new ColorDrawable(Color.GRAY)).centerCrop())
                .into(view);


    }

    @Override
    public void onReplyClicked(ReplyItemViewModel Reply) {

    }
}
