package com.example.intergalactictrucking.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.intergalactictrucking.utils.UtilsDialog;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment {

    protected ProgressDialog progressDialog;
    protected AlertDialog alertDialog;

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

    protected void setupViewById() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(contentResource(), container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(view);
        setupViewById();
        setupViewModel();
        setupView();
    }

    @Override
    public void onDestroy() {
        UtilsDialog.dismissLoading(progressDialog);
        UtilsDialog.dismissDialog(alertDialog);
        super.onDestroy();
    }
}
