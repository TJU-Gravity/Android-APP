package com.example.yanghan.gravity.ui.main.reply;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yanghan.gravity.data.model.Reply;
import com.example.yanghan.gravity.data.model.ReplyResult;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

public class ReplyItemViewModel extends ViewModel {
 public ReplyResult reply=new ReplyResult();

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


}
