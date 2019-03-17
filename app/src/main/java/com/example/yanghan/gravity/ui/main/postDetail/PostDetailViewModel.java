package com.example.yanghan.gravity.ui.main.postDetail;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yanghan.gravity.data.model.PostDetail;
import com.example.yanghan.gravity.data.model.Reply;
import com.example.yanghan.gravity.data.model.ReplyResult;
import com.example.yanghan.gravity.data.managers.RequestManeger;
import com.example.yanghan.gravity.ui.commonInterface.ContextService;
import com.example.yanghan.gravity.ui.commonInterface.MultiResponse;
import com.example.yanghan.gravity.ui.commonInterface.RecyclerViewService;
import com.example.yanghan.gravity.ui.main.reply.FabFragment;
import com.example.yanghan.gravity.ui.main.reply.ReplyAdapter;
import com.example.yanghan.gravity.ui.main.reply.ReplyItemViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PostDetailViewModel extends ViewModel implements ReplyAdapter.ReplyAdapterListener{
    public PostDetail postDetail=new PostDetail();
    public int teamVisibility=View.GONE;
    private ContextService contextService;
    private MultiResponse multiResponse;
    private RecyclerViewService recyclerViewService;

    public ArrayList<ReplyItemViewModel> replyArrayList=new ArrayList<>();
    PostDetailViewModel(ContextService contextService)
    {
        this.contextService=contextService;
        multiResponse=(MultiResponse) contextService;
        recyclerViewService=(RecyclerViewService) contextService;
        initPost();

    }



    public static class Body
    {
        Integer ID=0;
    }
    public static class Result
    {
        public String code;
        public PostDetail data;
        public String message;

    }
    public void initPost()
    {
        Body body=new Body();

        body.ID= ((PostDetailActivity)contextService.getContext()).getIntent().getIntExtra("ID",0);
        final RequestManeger requestManeger=new RequestManeger();
        requestManeger.post("/post/detail", body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure: ", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try{

                    ObjectMapper mapper = new ObjectMapper();
                    Result result= mapper.readValue(response.body().string(),Result.class);
                    postDetail=result.data;

                    for (Reply reply:postDetail.replies)
                    {
                        ReplyItemViewModel replyItemViewModel = new ReplyItemViewModel();
                        replyItemViewModel.reply=(ReplyResult) reply;
                        replyArrayList.add(replyItemViewModel);

                    }
                    if (postDetail.team!=null)
                        teamVisibility= View.VISIBLE;
                    multiResponse.succeed();
                    recyclerViewService.stopLoading();
                }
                catch (Exception e)
                {
                    Log.e("onResponse: ", e.toString());
                }

            }
        });

    }

    public void goToTeamCard(View v)
    {
        Log.e("goToTeamCard: ", "!");
    }
    public void refresh()
    {
        replyArrayList=new ArrayList<>();
        initPost();

    }
    public ArrayList<ReplyItemViewModel> getReplyList() {
        Log.e("get","newsList");
        return replyArrayList;

    }

    public String getImageUrl() {
        // The URL will usually come from a model (i.e Profile)
        return "http://gravity-image-1256225215.cos.ap-shanghai.myqcloud.com/headshot/default.jpg";
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
    public void onBtnClicked(View v)
    {
        FabFragment dialogFrag = FabFragment.newInstance();
        dialogFrag.setParentFab((FloatingActionButton)v);
        dialogFrag.show(((PostDetailActivity)v.getContext()).getSupportFragmentManager(), dialogFrag.getTag());
    }
}
