package com.example.intergalactictrucking.ui.history;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;

public class HistoryFragment extends BaseFragment {

    private NavController navController;
    private HistoryController historyController;
    private EpoxyRecyclerView epoxyRecyclerViewHistory;

    @Override
    protected int contentResource() {
        return R.layout.fragment_history;
    }

    @Override
    protected void setupViewById() {
        epoxyRecyclerViewHistory = getView().findViewById(R.id.epoxyRecyclerViewHistory);
    }

    @Override
    protected void setupView() {
        navController = Navigation.findNavController(getActivity(), R.id.main_nav_fragment);

        historyController = new HistoryController();
        epoxyRecyclerViewHistory.setController(historyController);

    }
}