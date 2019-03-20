package com.example.yanghan.gravity;

import android.app.Application;

import com.example.yanghan.gravity.data.RNmodule.CustomToastPackage;

import com.facebook.react.ReactApplication;
import in.sriraman.sharedpreferences.RNSharedPreferencesReactPackage;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swmansion.reanimated.ReanimatedPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.oblador.vectoricons.VectorIconsPackage;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication  {
    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }
        private ReactContext mReactContext;

        public ReactContext getReactContext() {
            return mReactContext;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new ReanimatedPackage(),
                    new VectorIconsPackage(),
                    new RNGestureHandlerPackage(),
                    new CustomToastPackage(),
                    new RNSharedPreferencesReactPackage()

            );
        }
        @Override
        protected String getJSMainModuleName() {
            return "index";
        }

    };
    private ReactContext mReactContext;

    public ReactContext getReactContext() {
        return mReactContext;
    }

    private final ReactInstanceManager.ReactInstanceEventListener mReactInstanceEventListener = new ReactInstanceManager.ReactInstanceEventListener() {
        @Override
        public void onReactContextInitialized(ReactContext context) {
            mReactContext = context;
        }
    };
    @Override
    public ReactNativeHost getReactNativeHost() {

        return mReactNativeHost;
    }
    private void registerReactInstanceEventListener() {
        mReactNativeHost.getReactInstanceManager().addReactInstanceEventListener(mReactInstanceEventListener);
    }

    private void unRegisterReactInstanceEventListener() {
        mReactNativeHost.getReactInstanceManager().removeReactInstanceEventListener(mReactInstanceEventListener);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        registerReactInstanceEventListener();
    }



}
