package com.example.intergalactictrucking.ui.onCar;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;

public class OnCarFragment extends BaseFragment {

    private NavController navController;

    @Override
    protected int contentResource() {
        return R.layout.fragment_oncar;
    }

    @Override
    protected void setupView() {
        navController = Navigation.findNavController(getActivity(), R.id.main_nav_fragment);

    }
}
