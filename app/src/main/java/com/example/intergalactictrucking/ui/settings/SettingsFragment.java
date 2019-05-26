package com.example.intergalactictrucking.ui.settings;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;

public class SettingsFragment extends BaseFragment {

    private NavController navController;

    Button buttonchangeName;
    Button buttonchangePassword;
    Button buttonlogOut;

    @Override
    protected int contentResource() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void setupView() {

        navController = Navigation.findNavController(getActivity(), R.id.main_nav_fragment);

        buttonchangeName = getView().findViewById(R.id.changeName);
        buttonchangePassword = getView().findViewById(R.id.changePassword);
        buttonlogOut = getView().findViewById(R.id.logOut);

        buttonlogOut.setOnClickListener(v -> {

        });
    }
}
