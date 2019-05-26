package com.example.intergalactictrucking;

import android.support.design.widget.BottomNavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.intergalactictrucking.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private NavController navController;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected int contentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupView() {
        navController = Navigation.findNavController(this, R.id.main_nav_fragment);
        bottomNavigationView = findViewById(R.id.navigation);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }
}
