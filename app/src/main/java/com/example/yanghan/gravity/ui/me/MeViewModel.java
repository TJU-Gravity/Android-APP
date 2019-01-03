package com.example.yanghan.gravity.ui.me;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.library.CircleImageView;
import com.example.yanghan.gravity.BR;
import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.data.other.LoginManager;
import com.example.yanghan.gravity.ui.me.edit.EditActivity;
import com.example.yanghan.gravity.ui.me.favorites.FavoritesActivity;
import com.fasterxml.jackson.databind.ObjectMapper;



import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;


public class MeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public User user=new User();
    private LoginManager loginManager=new LoginManager();
    public MeViewModel()
    {
        super();


    }

    public void initUser(Context context)
    {
        Log.e("init","user");

        if(user.loadUser(context));//http请求后废弃


    }
    public String getImageUrl() {

        return "http://gravity-image-1256225215.cos.ap-shanghai.myqcloud.com/headshot/default.jpg";

    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(CircleImageView view, String imageUrl) {
       if(imageUrl!=null)

        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(new RequestOptions().override(96, 96).error(new ColorDrawable(Color.GRAY)))
                .into(view);
    }

    public void onClickEditBtn(Context context)
    {
        Log.e("click","editBtn");

        Intent intent = new Intent(context, EditActivity.class);
        context.startActivity(intent);
    }
    public void onClickFavorites(View v)
    {
        Log.e("click","FavoritesBtn");

        Intent intent = new Intent(v.getContext(), FavoritesActivity.class);
        v.getContext().startActivity(intent);
    }
    public void onClickLogout(Context context)
    {
        Log.e("click","logout");
        loginManager.logout(context,user);

    }

}
