package com.example.yanghan.gravity.ui.main;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yanghan.gravity.data.model.News;
import com.example.yanghan.gravity.data.model.Post;
import com.example.yanghan.gravity.data.model.PostResult;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

public class PostItemViewModel extends ViewModel {
 public PostResult post=new PostResult();

 public String getImageUrl() {
  // The URL will usually come from a model (i.e Profile)
  return "http://gravity-image-1256225215.cos.ap-shanghai.myqcloud.com/headshot/default.jpg";
 }

 @BindingAdapter({"bind:imageUrl"})
 public static void loadImage(ImageView view, String imageUrl) {
  if(imageUrl!=null&&!imageUrl.equals("")) {
   Glide.with(view.getContext())
           .load(imageUrl)
           .apply(new RequestOptions().override(25, 25).error(new ColorDrawable(Color.GRAY)).centerCrop())
           .into(view);
  }

 }


}
