package com.example.intergalactictrucking.ui.auth.registration;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;

import butterknife.BindView;

public class RegistrationFragment extends BaseFragment {

    @BindView(R.id.inputLogin)
    EditText editTextinputLogin;
    @BindView(R.id.inputPassword)
    EditText editTextinputPassword;
    @BindView(R.id.inputRepeatPassword)
    EditText editTextinputRepeatPassword;
    @BindView(R.id.signUpButton)
    Button buttonsignUpButton;

    @Override
    protected int contentResource() {
        return R.layout.fragment_registration;
    }

    @Override
    protected void setupView() {
        buttonsignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
