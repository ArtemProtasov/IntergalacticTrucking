package com.example.intergalactictrucking.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class BaseActivity extends AppCompatActivity {

//    protected FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    protected DatabaseReference databaseReference = null;

    protected abstract int contentResource();

    protected abstract void setupView();

    protected void setupViewModel() {

    }

    protected DatabaseReference setupDatabase() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentResource());
        setupView();
//        databaseReference = setupDatabase();
        setupViewModel();
    }
}