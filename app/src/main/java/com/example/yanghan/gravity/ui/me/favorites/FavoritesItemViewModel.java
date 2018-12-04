package com.example.yanghan.gravity.ui.me.favorites;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.library.CircleImageView;
import com.example.yanghan.gravity.data.model.News;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

public class FavoritesItemViewModel extends ViewModel {
    public News news=new News();


    public String getImageUrl() {
        // The URL will usually come from a model (i.e Profile)
        return "https://upload.wikimedia.org/wikipedia/commons/f/fe/Michelle_Borromeo_Actor_Headshots_30.jpg";
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Log.e("url",imageUrl);

        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(new RequestOptions().override(96, 96).error(new ColorDrawable(Color.GRAY)).centerCrop())
                .into(view);


    }



}
