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
<<<<<<< HEAD
    public User user=new User();
=======
    public User user;
>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c
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

    public void initUser(Context context)
    {
        Log.e("init","user");

        user.loadUser(context);//http请求后废弃

        //user=loginManager.getCurrentUser(this);


    }
    public String getImageUrl() {

<<<<<<< HEAD
        return user.headshot;

=======
        // return user.headshot;
        return "https://upload.wikimedia.org/wikipedia/commons/f/fe/Michelle_Borromeo_Actor_Headshots_30.jpg";
>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(CircleImageView view, String imageUrl) {
        Log.e("url",imageUrl);

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
