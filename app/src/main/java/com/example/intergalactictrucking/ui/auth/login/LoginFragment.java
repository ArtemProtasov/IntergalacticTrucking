package com.example.intergalactictrucking.ui.auth.login;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;
import com.example.intergalactictrucking.utils.UtilsDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class LoginFragment extends BaseFragment {

    private NavController navController;
    private FirebaseAuth firebaseAuth;

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
    protected void setupViewById() {
        navController = Navigation.findNavController(getActivity(), R.id.auth_nav_fragment);

        editTextLogin = getView().findViewById(R.id.inputLogin);
        editTextPassword = getView().findViewById(R.id.inputPassword);
        logInButton = getView().findViewById(R.id.logIn);
        textViewSignUpButton = getView().findViewById(R.id.signUpButton);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void setupView() {

        textViewSignUpButton.setOnClickListener(v ->
                navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        );

        logInButton.setOnClickListener(v -> {
            firebaseAuth.signInWithEmailAndPassword(editTextLogin.getText().toString(), editTextPassword.getText().toString())
                    .addOnCompleteListener(getActivity(), task -> {
                        if(task.isSuccessful()) {
                            navController.navigate(R.id.action_loginFragment_to_mainActivity2);
                        } else {
                            UtilsDialog.showBasicDialog(getActivity(), "Ok", "Авторизация не удалась. Повторите попытку...").show();
                        }
                    });
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null) {
            navController.navigate(R.id.action_loginFragment_to_mainActivity2);
        }
    }
}
