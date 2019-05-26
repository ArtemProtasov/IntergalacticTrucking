package com.example.intergalactictrucking.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public abstract class BaseFragment extends Fragment {

//    protected FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    protected DatabaseReference databaseReference = null;
//    protected FirebaseAuth firebaseAuth = null;


    protected abstract int contentResource();

    protected abstract void setupView();

    protected View getProgress() {
        return null;
    }

    protected ViewGroup getViewGroup() {
        return null;
    }

    protected void setupViewModel() {

    }

    protected DatabaseReference setupDatabase() {
        return null;
    }

    protected FirebaseAuth setupFirebaseAuth() {
        return null;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(contentResource(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        firebaseAuth = setupFirebaseAuth();
//        databaseReference = setupDatabase();
        setupViewModel();
        setupView();
    }
}
