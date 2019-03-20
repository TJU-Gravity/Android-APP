package com.example.yanghan.gravity.ui.team;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.yanghan.gravity.MainApplication;
import com.example.yanghan.gravity.data.managers.LoginManager;
import com.example.yanghan.gravity.ui.MainActivity;
import com.example.yanghan.gravity.ui.baseClass.BaseReactActivity;
import com.facebook.react.BuildConfig;


import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;


import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.shell.MainReactPackage;


public class TeamFragment extends Fragment  {


    private final int OVERLAY_PERMISSION_REQ_CODE = 1;  // Choose any value
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LoginManager loginManager=new LoginManager();
        Log.w("RootView","start");
        Bundle bundle=new Bundle();
        bundle.putString("user",loginManager.getCurrentUser(getActivity()).username);
        mReactRootView.startReactApplication(mReactInstanceManager, "TeamList", bundle);
        ((BaseReactActivity) (getActivity())).mReactRootView=this.mReactRootView;
        return mReactRootView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mReactInstanceManager =
                ((BaseReactActivity)getActivity()).mReactInstanceManager;
       mReactRootView=new ReactRootView(getContext());


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel

    }

    protected void sendEvent(String eventName,
                             @Nullable WritableMap params) {
        if (((MainApplication) getActivity().getApplication()).getReactContext() != null) {
            ((MainApplication) getActivity().getApplication()).getReactContext()
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, params);
        }
    }



}
