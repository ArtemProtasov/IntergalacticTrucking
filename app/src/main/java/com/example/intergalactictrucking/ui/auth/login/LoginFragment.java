package com.example.intergalactictrucking.ui.auth.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;

public class LoginFragment extends BaseFragment {

    @BindView(R.id.inputLogin)
    EditText editTextLogin;
    @BindView(R.id.inputPassword)
    EditText editTextPassword;
    @BindView(R.id.logInButton)
    Button logInButton;
    @BindView(R.id.signUpButton)
    TextView textViewSignUpButton;


    @Override
    protected int contentResource() {
        return R.layout.fragment_login;
    }

    @Override
    protected void setupView() {
        textViewSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
