package com.example.yanghan.gravity.ui.me;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.library.CircleImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

public class FavoritesItemViewModel extends ViewModel {
    public String Title;
    public String imageUrl;
    public String getImageUrl() {
        // The URL will usually come from a model (i.e Profile)
        return "https://upload.wikimedia.org/wikipedia/commons/f/fe/Michelle_Borromeo_Actor_Headshots_30.jpg";
    }


}
