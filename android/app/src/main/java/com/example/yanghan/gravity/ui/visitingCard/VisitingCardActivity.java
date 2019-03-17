package com.example.yanghan.gravity.ui.visitingCard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import android.content.Context;
import android.os.Bundle;
import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.databinding.ActivityVisitingCardBinding;
import com.example.yanghan.gravity.ui.commonInterface.ContextService;


public class VisitingCardActivity extends AppCompatActivity implements ContextService {
    private VisitingCardViewModel mViewModel;
    public ActivityVisitingCardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiting_card);
        mViewModel = new VisitingCardViewModel(this);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_visiting_card);
        binding.setViewModel(mViewModel);

    }

    @Override
    public Context getContext() {
        return this;
    }
}
