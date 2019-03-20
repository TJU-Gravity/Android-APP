package com.example.yanghan.gravity.ui.news;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanghan.gravity.ui.MainActivity;
import com.example.yanghan.gravity.ui.team.TeamFragment;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;

public class NewsFragment extends Fragment {

    private final int OVERLAY_PERMISSION_REQ_CODE = 1;  // Choose any value
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    public static TeamFragment newInstance() {
        return new TeamFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mReactRootView.startReactApplication(mReactInstanceManager, "NewsList", null);

        return mReactRootView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mReactInstanceManager =
                ((MainActivity)getActivity()).mReactInstanceManager;
        mReactRootView = new ReactRootView(getContext());


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel

    }



}
