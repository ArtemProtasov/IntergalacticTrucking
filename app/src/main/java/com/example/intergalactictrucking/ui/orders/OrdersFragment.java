package com.example.intergalactictrucking.ui.orders;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;

public class OrdersFragment extends BaseFragment {

    private NavController navController;

    @Override
    protected int contentResource() {
        return R.layout.fragment_orders;
    }

    @Override
    protected void setupView() {
        navController = Navigation.findNavController(getActivity(), R.id.main_nav_fragment);

    }
}
