package com.example.intergalactictrucking.ui.auth;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseActivity;

public class AuthActivity extends BaseActivity {

    private NavController navController;

    @Override
    protected int contentResource() {
        return R.layout.activity_auth;
    }

    @Override
    protected void setupView() {
        navController = Navigation.findNavController(this, R.id.auth_nav_fragment);
    }
}
