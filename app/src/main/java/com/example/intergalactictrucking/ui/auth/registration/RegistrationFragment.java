package com.example.intergalactictrucking.ui.auth.registration;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;

import butterknife.BindView;

public class RegistrationFragment extends BaseFragment {

    private NavController navController;

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
    protected void setupView() {

        navController = Navigation.findNavController(getActivity(), R.id.auth_nav_fragment);

        editTextinputLogin = getView().findViewById(R.id.inputLogin);
        editTextinputPassword = getView().findViewById(R.id.inputPassword);
        editTextinputRepeatPassword = getView().findViewById(R.id.inputRepeatPassword);
        editTextinputCity = getView().findViewById(R.id.inputCity);
        editTextinputNameCompany = getView().findViewById(R.id.inputNameCompany);
        checkBoxinputFace = getView().findViewById(R.id.inputFace);
        buttonsignUp = getView().findViewById(R.id.signUp);

        buttonsignUp.setOnClickListener(v -> {

        });
    }
}
