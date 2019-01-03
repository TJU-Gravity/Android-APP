package com.example.yanghan.gravity.ui.main.reply;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.data.model.Reply;
import com.example.yanghan.gravity.data.other.LoginManager;
import com.example.yanghan.gravity.data.other.RequestManeger;
import com.example.yanghan.gravity.ui.commonInterface.MultiResponse;
import com.example.yanghan.gravity.ui.main.postDetail.PostDetailActivity;
import com.example.yanghan.gravity.ui.main.postDetail.PostDetailViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import studio.carbonylgroup.textfieldboxes.SimpleTextChangedWatcher;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class FabFragment extends AAH_FabulousFragment implements MultiResponse {


    private String text="";
    public static FabFragment newInstance() {
        FabFragment f = new FabFragment();
        return f;
    }


    @Override

    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.fragment_reply, null);
        RelativeLayout rl_content = (RelativeLayout) contentView.findViewById(R.id.rl_content);
        LinearLayout ll_buttons = (LinearLayout) contentView.findViewById(R.id.ll_buttons);
        contentView.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFilter("closed");
            }
        });


        final TextFieldBoxes body = contentView.findViewById(R.id.body_box);


        body.setSimpleTextChangeWatcher(new SimpleTextChangedWatcher() {
            @Override
            public void onTextChanged(String theNewText, boolean isError) {
                text=theNewText;

            }
        });
        contentView.findViewById(R.id.btn_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();

            }
        });
        //params to set
        setAnimationDuration(300); //optional; default 500ms
        setPeekHeight(300); // optional; default 400dp

        setViewMain(rl_content); //necessary; main bottomsheet view
        setMainContentView(contentView); // necessary; call at end before super
        super.setupDialog(dialog, style); //call super at last
    }

    private void commit()
    {
        LoginManager loginManager=new LoginManager();
        Reply reply=new Reply();
        reply.posterid=loginManager.getCurrentUser(getActivity()).username;
        reply.postingtime=new Date();
        reply.postid=((PostDetailViewModel)((PostDetailActivity)getActivity()).getViewModel()).postDetail.post.postId;
        reply.replybody=text;
        RequestManeger requestManeger=new RequestManeger();
        requestManeger.post("http://118.25.41.237:8080/reply/add", reply, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure: ",e.toString() );
                failed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                succeed();

            }
        });
    }

    @Override
    public void failed() {
        Toast.makeText(getContext(), "回复失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void succeed() {

        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getContext(), "回复成功", Toast.LENGTH_SHORT).show();
                closeFilter("closed");
            }});

    }

    @Override
    public void error() {

    }
}
