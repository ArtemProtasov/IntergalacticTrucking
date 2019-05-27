package com.example.intergalactictrucking.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int contentResource();

    protected abstract void setupView();

    protected void setupViewModel() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentResource());
        setupView();
        setupViewModel();
    }
}