package com.example.intergalactictrucking.ui.auth.login;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;
import com.example.intergalactictrucking.utils.UtilsDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        checkInput();

        editTextLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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

    private void checkInput() {
        logInButton.setEnabled(!editTextLogin.getText().toString().isEmpty() && !editTextPassword.getText().toString().isEmpty() && isEmailValid(editTextLogin.getText().toString()));
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
