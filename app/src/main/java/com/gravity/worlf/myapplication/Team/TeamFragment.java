package com.gravity.worlf.myapplication.Team;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gravity.worlf.myapplication.R;


public class TeamFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onStart() {
        TextView textView = getView().findViewById(R.id.messagetext);
        textView.setText("这是团队页面");
        super.onStart();
    }


}
