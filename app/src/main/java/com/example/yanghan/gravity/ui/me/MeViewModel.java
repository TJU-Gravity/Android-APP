package com.example.yanghan.gravity.ui.me;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.library.CircleImageView;
import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.data.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.squareup.picasso.Picasso;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;


public class MeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public User user;
    public Image headshot;


    public MeViewModel()
    {
        super();

        try {
            ObjectMapper objectMapper=new ObjectMapper();

            String testJson =
                    "{ \"username\" : \"Rebecca\" }";

            user=objectMapper.readValue(testJson,User.class);


        }
        catch (Exception e)
        {
            Log.e("json","EXM?");
        }

        Log.e("init","Me");
        //user=objectMapper.readValue(new File("/Users/yanghan/Documents/Gravity/app/sampledata"),User.class);



    }
    public String getImageUrl() {
        // The URL will usually come from a model (i.e Profile)
        return "https://upload.wikimedia.org/wikipedia/commons/f/fe/Michelle_Borromeo_Actor_Headshots_30.jpg";
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(CircleImageView view, String imageUrl) {
        Log.e("url",imageUrl);

        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(new RequestOptions().override(96, 96).error(new ColorDrawable(Color.GRAY)))
                .into(view);


    }





}
