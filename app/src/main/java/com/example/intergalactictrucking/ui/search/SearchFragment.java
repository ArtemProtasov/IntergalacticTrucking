package com.example.intergalactictrucking.ui.search;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.intergalactictrucking.R;
import com.example.intergalactictrucking.base.BaseFragment;

import butterknife.BindView;

public class SearchFragment extends BaseFragment {

    private NavController navController;


    EditText editTextwhence;
    EditText editTextwhere;
    EditText editTextweight;
    EditText editTextvolume;
    EditText editTextbodytype;
    EditText editTextshipmentdate;
    Button buttonclean;
    Button buttonsavefilters;
    Button buttonsearchshipments;

    @Override
    protected int contentResource() {
        return R.layout.fragment_search;
    }

    @Override
    protected void setupView() {
        navController = Navigation.findNavController(getActivity(), R.id.main_nav_fragment);

        editTextwhence = getView().findViewById(R.id.whence);
        editTextwhere = getView().findViewById(R.id.where);
        editTextweight = getView().findViewById(R.id.weight);
        editTextvolume = getView().findViewById(R.id.volume);
        editTextbodytype = getView().findViewById(R.id.bodytype);
        editTextshipmentdate = getView().findViewById(R.id.shipmentdate);
        buttonclean = getView().findViewById(R.id.clean);
        buttonsavefilters = getView().findViewById(R.id.savefilters);
        buttonsearchshipments = getView().findViewById(R.id.searchshipments);

        buttonsearchshipments.setOnClickListener(v -> {

        });

    }
}
