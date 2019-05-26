package com.example.intergalactictrucking.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int contentResource();

    protected abstract void setupView();

    protected void setupViewModel() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(contentResource());
        setupView();
        setupViewModel();
    }
}