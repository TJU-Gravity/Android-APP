package com.example.yanghan.gravity.ui.visitingCard;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.library.CircleImageView;
import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.data.other.SearchManager;
import com.example.yanghan.gravity.ui.commonInterface.ContextService;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

public class VisitingCardViewModel extends ViewModel {
    public User user=new User();
    private ContextService contextService;
    public VisitingCardViewModel(ContextService contextService)
    {
        this.contextService=contextService;
        initUser();
    }

    private void initUser()
    {
        String username=((VisitingCardActivity)contextService.getContext()).getIntent().getStringExtra("username");
        SearchManager searchManager=new SearchManager();
        searchManager.getUserInfo(username,contextService,this);

    }
    public String getImageUrl() {

        return "http://gravity-image-1256225215.cos.ap-shanghai.myqcloud.com/headshot/default.jpg";

    }
    public void invite(View v)
    {

    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(CircleImageView view, String imageUrl) {
        if(imageUrl!=null)

            Glide.with(view.getContext())
                    .load(imageUrl)
                    .apply(new RequestOptions().override(96, 96).error(new ColorDrawable(Color.GRAY)))
                    .into(view);
    }
}
