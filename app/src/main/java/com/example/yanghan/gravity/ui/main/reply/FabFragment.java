package com.example.yanghan.gravity.ui.main.reply;

import android.app.Dialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.example.yanghan.gravity.R;

public class FabFragment extends AAH_FabulousFragment {
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

        //params to set
        setAnimationDuration(300); //optional; default 500ms
        setPeekHeight(300); // optional; default 400dp

        setViewMain(rl_content); //necessary; main bottomsheet view
        setMainContentView(contentView); // necessary; call at end before super
        super.setupDialog(dialog, style); //call super at last
    }
}
