package com.example.intergalactictrucking.ui.settings;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends BaseFragment {

    private NavController navController;
    private FirebaseAuth firebaseAuth;

    Button buttonchangeName;
    Button buttonchangePassword;
    Button buttonlogOut;

    @Override
    protected int contentResource() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void setupViewById() {
        navController = Navigation.findNavController(getActivity(), R.id.main_nav_fragment);

        buttonchangeName = getView().findViewById(R.id.changeName);
        buttonchangePassword = getView().findViewById(R.id.changePassword);
        buttonlogOut = getView().findViewById(R.id.logOut);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void setupView() {
        buttonlogOut.setOnClickListener(v -> {
            firebaseAuth.signOut();
            navController.navigate(R.id.action_settingsFragment_to_authActivity);
        });
    }
}
