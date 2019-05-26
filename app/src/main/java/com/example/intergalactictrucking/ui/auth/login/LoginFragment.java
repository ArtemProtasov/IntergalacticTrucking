package com.example.intergalactictrucking.ui.auth.login;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;

public class LoginFragment extends BaseFragment {

    private NavController navController;

    EditText editTextLogin;
    EditText editTextPassword;
    Button logInButton;
    TextView textViewSignUpButton;

    @Override
    protected int contentResource() {
        return R.layout.fragment_login;
    }

    @Override
    protected void setupViewModel() {
        super.setupViewModel();
    }

    @Override
    protected void setupView() {

        navController = Navigation.findNavController(getActivity(), R.id.auth_nav_fragment);

        editTextLogin = getView().findViewById(R.id.inputLogin);
        editTextPassword = getView().findViewById(R.id.inputPassword);
        logInButton = getView().findViewById(R.id.logInButton);
        textViewSignUpButton = getView().findViewById(R.id.signUpButton);

        textViewSignUpButton.setOnClickListener(v ->
                navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        );

    }
}
