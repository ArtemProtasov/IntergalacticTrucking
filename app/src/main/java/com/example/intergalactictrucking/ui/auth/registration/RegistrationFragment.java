package com.example.intergalactictrucking.ui.auth.registration;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;
import com.example.intergalactictrucking.utils.UtilsDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationFragment extends BaseFragment {

    private NavController navController;
    private FirebaseAuth firebaseAuth;

    EditText editTextinputLogin;
    EditText editTextinputPassword;
    EditText editTextinputRepeatPassword;
    EditText editTextinputCity;
    EditText editTextinputNameCompany;
    CheckBox checkBoxinputFace;
    Button buttonsignUp;

    @Override
    protected int contentResource() {
        return R.layout.fragment_registration;
    }

    @Override
    protected void setupViewById() {
        navController = Navigation.findNavController(getActivity(), R.id.auth_nav_fragment);

        editTextinputLogin = getView().findViewById(R.id.inputLogin);
        editTextinputPassword = getView().findViewById(R.id.inputPassword);
        editTextinputRepeatPassword = getView().findViewById(R.id.inputRepeatPassword);
        editTextinputCity = getView().findViewById(R.id.inputCity);
        editTextinputNameCompany = getView().findViewById(R.id.inputNameCompany);
        checkBoxinputFace = getView().findViewById(R.id.inputFace);
        buttonsignUp = getView().findViewById(R.id.signUp);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void setupView() {
        buttonsignUp.setOnClickListener(v -> {
            if(editTextinputPassword.getText().toString() == editTextinputRepeatPassword.getText().toString()) {
                firebaseAuth.createUserWithEmailAndPassword(editTextinputLogin.getText().toString(), editTextinputPassword.getText().toString())
                        .addOnCompleteListener(getActivity(), task -> {
                            if (task.isSuccessful()) {
                                navController.navigate(R.id.action_loginFragment_to_mainActivity2);
                            } else {
                                UtilsDialog.showBasicDialog(getActivity(), "Ok", "Регистрация не удалась. Повторите попытку...");
                            }
                        });
            } else {
                UtilsDialog.showBasicDialog(getActivity(), "Ok", "Пароли не совпадают!");
            }
        });
    }
}
